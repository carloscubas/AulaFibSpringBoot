package br.biblioteca.livros.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.biblioteca.livros.converters.LivroConverter;
import br.biblioteca.livros.dtos.LivroDTO;
import br.biblioteca.livros.model.Livro;
import br.biblioteca.livros.service.LivrosService;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	LivrosService livroService;

	@GetMapping("/livros/list")
	public ResponseEntity<List<LivroDTO>> listaLivros() {
		List<Livro> listaLivros = livroService.listaTodosLivros();
		return ResponseEntity.ok(LivroConverter.toDTO(listaLivros));
	}

}
