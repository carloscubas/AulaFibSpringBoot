package br.bliblioteca.livros.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.bliblioteca.livros.exception.LivroNotFoundException;
import br.bliblioteca.livros.model.Autor;
import br.bliblioteca.livros.model.Livro;
import br.bliblioteca.livros.repository.AutorRepository;
import br.bliblioteca.livros.repository.LivroRepository;

@Controller
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	LivroRepository livroRepository;

	@Autowired
	AutorRepository autorRepository;

	@GetMapping("/list")
	public ModelAndView livros() {
		ModelAndView modelAndView = new ModelAndView("livros/list");
		Iterable<Livro> livros = livroRepository.findAll();
		modelAndView.addObject("livros", livros);
		return modelAndView;
	}

	@GetMapping("/novo")
	public ModelAndView createForm(@ModelAttribute Livro livro) {
		ModelAndView modelAndView = new ModelAndView("livros/form");
		Iterable<Autor> autores = autorRepository.findAll();
		modelAndView.addObject("autores", autores);
		return modelAndView;
	}

	@PostMapping(value = "/gravar")
	public ModelAndView create(Livro livro) {
		livroRepository.save(livro);
		return new ModelAndView("redirect:/livros/list");
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		Livro livro = this.livroRepository.findById(id)
				.orElseThrow(() -> new LivroNotFoundException("Livro não encontrado"));
		Iterable<Autor> autores = autorRepository.findAll();
		ModelAndView modelAndView = new ModelAndView("livros/form");
		modelAndView.addObject("autores", autores);
		modelAndView.addObject("livro", livro);
		return modelAndView;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		return new ModelAndView("redirect:/livros/list");
	}
}
