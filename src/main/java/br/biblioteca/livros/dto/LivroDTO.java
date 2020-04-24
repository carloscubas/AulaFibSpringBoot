package br.biblioteca.livros.dto;

import java.util.ArrayList;
import java.util.List;

public class LivroDTO {

	private String titulo;

	private int paginas;

	private String autor;

	private List<AvaliacaoDTO> avaliacoes = new ArrayList<AvaliacaoDTO>();

	public List<AvaliacaoDTO> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<AvaliacaoDTO> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

}
