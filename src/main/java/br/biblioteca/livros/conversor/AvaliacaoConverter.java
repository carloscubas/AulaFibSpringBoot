package br.biblioteca.livros.conversor;

import java.util.List;
import java.util.stream.Collectors;

import br.biblioteca.livros.dto.AvaliacaoDTO;
import br.biblioteca.livros.model.Avaliacao;
import br.biblioteca.livros.model.Livro;

public class AvaliacaoConverter {

	public static Avaliacao toModel(AvaliacaoDTO avaliacaoDTO) {
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setComentario(avaliacaoDTO.getComentario());
		avaliacao.setNota(avaliacaoDTO.getNota());
		return avaliacao;
	}

	public static Avaliacao toModel(AvaliacaoDTO avaliacaoDTO, Livro livro) {
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setComentario(avaliacaoDTO.getComentario());
		avaliacao.setNota(avaliacaoDTO.getNota());
		avaliacao.setLivro(livro);
		return avaliacao;
	}

	public static AvaliacaoDTO toDTO(Avaliacao avaliacao) {
		AvaliacaoDTO dto = new AvaliacaoDTO();
		dto.setComentario(avaliacao.getComentario());
		dto.setNota(avaliacao.getNota());
		return dto;
	}

	public static List<AvaliacaoDTO> toDTO(List<Avaliacao> avaliacoes) {
		return avaliacoes.stream().map(a -> toDTO(a)).collect(Collectors.toList());
	}

}
