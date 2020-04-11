package br.bliblioteca.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.bliblioteca.livros.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

}
