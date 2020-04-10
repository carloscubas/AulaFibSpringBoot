package br.bliblioteca.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.bliblioteca.livros.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository <Livro, Long> { 
	
}