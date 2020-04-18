package br.biblioteca.livros.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LivroDTO {

	private String nome;

	private int paginas;

	private String autor;

	@JsonProperty("width")
	private AutorDTO autorDto;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public AutorDTO getAutorDto() {
		return autorDto;
	}

	public void setAutorDto(AutorDTO autorDto) {
		this.autorDto = autorDto;
	}

}