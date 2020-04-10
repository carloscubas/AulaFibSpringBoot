package br.bliblioteca.livros.exception;

public class LivroNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LivroNotFoundException(String message) {
		super(message);

	}

}
