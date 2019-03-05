package br.com.workmade.algamoneybackendapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.workmade.algamoneybackendapi.model.Lancamento;
import br.com.workmade.algamoneybackendapi.repository.filter.LancamentoFilter;


public interface ILancamentoRepositoryQuery {

	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	
}
