package br.biblioteca.livros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.biblioteca.livros.model.Avaliacao;
import br.biblioteca.livros.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

	@Autowired
	AvaliacaoRepository avaliacaoRepository;

	public Long salvaAvaliacao(Avaliacao avaliacao) {
		return avaliacaoRepository.save(avaliacao).getId();
	}

}
