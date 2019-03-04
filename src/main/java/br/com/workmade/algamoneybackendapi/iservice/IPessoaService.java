package br.com.workmade.algamoneybackendapi.iservice;

import java.util.List;

import br.com.workmade.algamoneybackendapi.model.Pessoa;

public interface IPessoaService {
	
	Pessoa findById(Long id);
	List<Pessoa> findAll();
	Pessoa save(Pessoa pessoa);
	Pessoa update(Pessoa pessoa);
	void deleteById(Long id);
	Pessoa atualizarPropriedadeAtivo(Long id, Boolean isAtivo);

}
