package br.biblioteca.livros;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.livros.controladores.LivroController;
import br.biblioteca.livros.model.Livro;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LivrosControllerTests {

	@LocalServerPort
	private int port;

	private LivroController livroController;

	@Before
	public void before() {
		livroController = new LivroController();
	}

	@Test
	public void contextLoads() {
		assertThat(livroController).isNotNull();
	}

	@Sql({ "classpath:schema.sql", "classpath:data.sql" })
	@Test
	public void bringListOfBooks() throws Exception {
		ModelAndView lista = livroController.livros();
		List<Livro> livros = (List<Livro>) lista.getModel().get("livros");
		assertEquals(1, livros.size());
	}

}
