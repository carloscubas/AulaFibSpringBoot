package br.biblioteca.livros.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class SanitizeFilter implements Filter {

    private final static Logger LOG = LoggerFactory.getLogger(SanitizeFilter.class);

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        LOG.info("Initializing filter :{}", this);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	
    	String path = ((HttpServletRequest) request).getServletPath();
        if (path.toLowerCase().startsWith("/webjars")) {
            request.getRequestDispatcher(path).forward(request, response);
        } else {
            HttpServletRequest req = new XSSRequestWrapper((HttpServletRequest) request);
            LOG.info("Starting Sanitization for req :{}", req.getRequestURI());
            chain.doFilter(req, response);
            LOG.info("Finish Sanitization for req :{}", req.getRequestURI());
        }
    	
    }

    @Override
    public void destroy() {
        LOG.warn("Destructing filter :{}", this);
    }
}
