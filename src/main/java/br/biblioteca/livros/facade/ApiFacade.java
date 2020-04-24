package br.biblioteca.livros.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.biblioteca.livros.dto.AvaliacaoDTO;
import br.biblioteca.livros.model.Livro;
import br.biblioteca.livros.service.AvaliacaoService;
import br.biblioteca.livros.service.LivrosService;

@Service
public class ApiFacade {

	@Autowired
	LivrosService livrosService;

	@Autowired
	AvaliacaoService avaliacaoServico;

	public List<Livro> listaTodosLivros() {
		return livrosService.listaTodosLivros();
	}

	public Long salvarAvaliacao(Long idLivro, AvaliacaoDTO avaliacaoDTO) {
		Livro livro = livrosService.buscaLivro(idLivro);

		avaliacaoServico.salvaAvaliacao(avaliacao);

		return 1l;

	}

}
