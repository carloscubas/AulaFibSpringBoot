package br.bliblioteca.livros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.bliblioteca.livros.model.Livro;
import br.bliblioteca.livros.repository.LivroRepository;

@Service
public class LivrosService {

	@Autowired
	LivroRepository livroRepository;

	public List<Livro> listaTodosLivros() {
		return livroRepository.listaLivros();
	}

}
