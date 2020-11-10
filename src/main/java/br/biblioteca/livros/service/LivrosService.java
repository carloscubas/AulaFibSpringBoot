package br.biblioteca.livros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.biblioteca.livros.exception.LivroNotFoundException;
import br.biblioteca.livros.model.Livro;
import br.biblioteca.livros.repository.LivroRepository;

@Service
public class LivrosService {

	@Autowired
	LivroRepository livroRepository;

	public List<Livro> listaTodosLivros() {
		return livroRepository.listaLivros();
	}

	public Long salvarLivro(Livro livro) {
		return livroRepository.save(livro).getId();
	}

	public Livro buscaLivro(Long id) {
		return livroRepository.findById(id).orElseThrow(() -> new LivroNotFoundException());
	}

	public void excluiLivro(Long id) {
		livroRepository.deleteById(id);
	}

}
