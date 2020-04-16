package br.biblioteca.livros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.biblioteca.livros.model.Livro;
import br.biblioteca.livros.repository.LivroRepository;

@Service
public class LivrosService {

	@Autowired
	LivroRepository livroRepository;

	public List<Livro> listaTodosLivros() {
		return livroRepository.listaLivros();
	}

	public void salvarLivro(Livro livro) {
		livroRepository.save(livro);
	}

}
