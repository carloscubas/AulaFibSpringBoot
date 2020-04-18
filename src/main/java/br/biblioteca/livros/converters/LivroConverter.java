package br.biblioteca.livros.converters;

import java.util.List;
import java.util.stream.Collectors;

import br.biblioteca.livros.dtos.LivroDTO;
import br.biblioteca.livros.model.Livro;

public class LivroConverter {

	public static LivroDTO toDTO(Livro livro) {
		LivroDTO dto = new LivroDTO();
		dto.setNome(livro.getNome());
		dto.setPaginas(livro.getQuantidadePaginas());
		dto.setAutor(livro.getAutor() != null ? livro.getAutor().getNome() : null);
		dto.setAutorDto(livro.getAutor() != null ? AutorConverter.toDTO(livro.getAutor()) : null);
		return dto;
	}

	public static List<LivroDTO> toDTO(List<Livro> livros) {
		return livros.stream().map(l -> toDTO(l)).collect(Collectors.toList());
	}

}
