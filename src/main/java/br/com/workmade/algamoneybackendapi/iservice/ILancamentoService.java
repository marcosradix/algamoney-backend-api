package br.com.workmade.algamoneybackendapi.iservice;

import java.util.List;

import br.com.workmade.algamoneybackendapi.model.Lancamento;

public interface ILancamentoService {
	
	Lancamento findById(Long id);
	List<Lancamento> findAll();
	Lancamento save(Lancamento lancamento);
	Lancamento update(Lancamento lancamento);
	void deleteById(Long id);

}
