package br.com.workmade.algamoneybackendapi.iservice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.workmade.algamoneybackendapi.model.Lancamento;
import br.com.workmade.algamoneybackendapi.repository.filter.LancamentoFilter;

public interface ILancamentoService {
	
	Lancamento findById(Long id);
	List<Lancamento> findAll();
	Lancamento save(Lancamento lancamento);
	Lancamento update(Lancamento lancamento);
	void deleteById(Long id);
	Page<Lancamento> findByQuery(LancamentoFilter lancamentoFilter, Pageable pageable);

}
