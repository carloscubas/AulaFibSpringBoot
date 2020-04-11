package br.biblioteca.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.biblioteca.livros.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

}
