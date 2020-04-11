package br.bliblioteca.livros.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.bliblioteca.livros.model.Livro;
import br.bliblioteca.livros.service.LivrosService;

@Controller
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	LivrosService livroService;

	@GetMapping("/list")
	public ModelAndView livros() {
		ModelAndView modelAndView = new ModelAndView("livros/list");

		List<Livro> listaLivros = livroService.listaTodosLivros();
		modelAndView.addObject("livros", listaLivros);

		return modelAndView;
	}

	@GetMapping("/novo")
	public ModelAndView createForm() {
		ModelAndView modelAndView = new ModelAndView("livros/form");
		return modelAndView;
	}

	@PostMapping(value = "/gravar")
	public ModelAndView create(Livro livro) {

		return new ModelAndView("redirect:/livros/list");
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("livros/form");
		return modelAndView;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		return new ModelAndView("redirect:/livros/list");
	}
}
