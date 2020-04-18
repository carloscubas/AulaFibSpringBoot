package br.biblioteca.livros.conversor;

import java.util.List;
import java.util.stream.Collectors;

import br.biblioteca.livros.dto.LivroDTO;
import br.biblioteca.livros.model.Livro;

public class LivroConverter {

	public static LivroDTO toDTO(Livro livro) {
		LivroDTO dto = new LivroDTO();
		dto.setTitulo(livro.getNome());
		dto.setPaginas(livro.getQuantidadePaginas());
		dto.setAutor(livro.getAutor() != null ? livro.getAutor().getNome() : null);
		return dto;
	}

	public static List<LivroDTO> toDTO(List<Livro> livros) {
		return livros.stream().map(l -> toDTO(l)).collect(Collectors.toList());
	}
}
