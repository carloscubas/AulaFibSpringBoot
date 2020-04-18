package br.biblioteca.livros.api;

import static br.biblioteca.livros.conversor.LivroConverter.toDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.biblioteca.livros.dto.LivroDTO;
import br.biblioteca.livros.model.Livro;
import br.biblioteca.livros.service.LivrosService;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	LivrosService livrosService;

	@GetMapping("/livros/list")
	public ResponseEntity<List<LivroDTO>> livros() {
		List<Livro> listaLivros = livrosService.listaTodosLivros();
		return ResponseEntity.ok(toDTO(listaLivros));
	}

}
