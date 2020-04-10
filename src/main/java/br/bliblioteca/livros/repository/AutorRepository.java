package br.bliblioteca.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.bliblioteca.livros.model.Autor;

public interface AutorRepository  extends JpaRepository <Autor, Long> { 
	
	
}
