package br.biblioteca.livros.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "LIVRO")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name = "NOME")
	private String nome;

	@Min(10)
	@Column(name = "LIVRO_QUANTIDADE_PAGINAS")
	private int quantidadePaginas;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "autor_id")
	private Autor autor;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public int getQuantidadePaginas() {
		return quantidadePaginas;
	}

	public void setQuantidadePaginas(final int quantidadePaginas) {
		this.quantidadePaginas = quantidadePaginas;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(final Autor autor) {
		this.autor = autor;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder()//
				.append("Livro [")//
				.append("id=")//
				.append(id)//
				.append(",nome=\"")//
				.append(nome).append("\"")//
				.append(",quantidadePaginas=")//
				.append(quantidadePaginas)//
				.append("]");
		return builder.toString();
	}
}
