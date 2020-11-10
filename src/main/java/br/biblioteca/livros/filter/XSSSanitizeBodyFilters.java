package br.biblioteca.livros.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.owasp.esapi.ESAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(2)
public class XSSSanitizeBodyFilters implements Filter {
    private final static Logger LOG = LoggerFactory.getLogger(XSSSanitizeBodyFilters.class);
    
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        LOG.info("Initializing filter :{}", this);
    }

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		SanitizationRequestWrapper sanitizeRequest = new SanitizationRequestWrapper(request);
		boolean sanityze = false;
		if (!Objects.isNull(sanitizeRequest.getBody())) {
			try {
				sanityze = true;
				sanitizeJson(sanitizeRequest);
			} catch (ParseException e) {
				LOG.error("Unable to Sanitize the provided JSON .");
			}
		}
		chain.doFilter(sanityze ? sanitizeRequest : req, resp);
	}
	
    @Override
    public void destroy() {
        LOG.warn("Destructing filter :{}", this);
    }

	private void sanitizeJson(SanitizationRequestWrapper sanitizeRequest) throws IOException, ParseException {
		JSONParser parser= new JSONParser();
		Object obj = parser.parse(sanitizeRequest.getReader());
		Map <String, Object> map = convertToMap((JSONObject)obj);
        sanitizeRequest.setBody((new JSONObject(map)).toString().getBytes());
	}
	
	private Map<String, Object> convertToMap(JSONObject jsonObject) {
        Map<String, Object> map = new HashMap<>();
        List<Object> mapArr = null;
        for (Object key : jsonObject.keySet()) {
            if (jsonObject.get(key) instanceof JSONObject) {
                map.put((String) key, convertToMap((JSONObject) jsonObject.get(key)));
            } else if (jsonObject.get(key) instanceof JSONArray) {
                mapArr = new ArrayList<>();
                JSONArray jArray = (JSONArray) jsonObject.get(key);
                for (int i = 0; i < jArray.size(); i++) {
                    if (jArray.get(i) instanceof JSONObject || jArray.get(i) instanceof JSONArray) {
                        mapArr.add(convertToMap((JSONObject) jArray.get(i)));
                    } else {
                        mapArr.add(jArray.get(i));
                    }
                }
                map.put((String) key, mapArr);
            } else {
                map.put((String) key, stripXSS(jsonObject.get(key).toString()));
            }
        }
        return map;
    }
	
	  private String stripXSS(String value) {
	        if (value != null) {
	        	
	        	value = ESAPI.encoder().canonicalize(value);
	 
	            // Avoid null characters
	            value = value.replaceAll("", "");
	 
	            // Avoid anything between script tags
	            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
	            value = scriptPattern.matcher(value).replaceAll("");
	 
	            // Avoid anything in a src='...' type of expression
	            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	            value = scriptPattern.matcher(value).replaceAll("");
	 
	            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	            value = scriptPattern.matcher(value).replaceAll("");
	 
	            // Remove any lonesome </script> tag
	            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
	            value = scriptPattern.matcher(value).replaceAll("");
	 
	            // Remove any lonesome <script ...> tag
	            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	            value = scriptPattern.matcher(value).replaceAll("");
	 
	            // Avoid eval(...) expressions
	            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	            value = scriptPattern.matcher(value).replaceAll("");
	 
	            // Avoid expression(...) expressions
	            scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	            value = scriptPattern.matcher(value).replaceAll("");
	 
	            // Avoid javascript:... expressions
	            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
	            value = scriptPattern.matcher(value).replaceAll("");
	 
	            // Avoid vbscript:... expressions
	            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
	            value = scriptPattern.matcher(value).replaceAll("");
	 
	            // Avoid onload= expressions
	            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	            value = scriptPattern.matcher(value).replaceAll("");
	        }
	        return value;
	    }

}
