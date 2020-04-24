package br.biblioteca.livros.conversor;

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

}
