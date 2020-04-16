package br.biblioteca.livros.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.biblioteca.livros.repository.AutorRepository;
import br.biblioteca.livros.repository.LivroRepository;

@Service
public class LivroFacade {

	@Autowired
	LivroRepository livroRepositroy;

	@Autowired
	AutorRepository autorRepository;

}
