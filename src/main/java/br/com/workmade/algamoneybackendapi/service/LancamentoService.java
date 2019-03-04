package br.com.workmade.algamoneybackendapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workmade.algamoneybackendapi.exceptions.ObjectNotFoundException;
import br.com.workmade.algamoneybackendapi.exceptions.ObjectSaveUpdateException;
import br.com.workmade.algamoneybackendapi.iservice.ILancamentoService;
import br.com.workmade.algamoneybackendapi.model.Lancamento;
import br.com.workmade.algamoneybackendapi.repository.LancamentoRepository;

@Service
public class LancamentoService implements ILancamentoService{

	@Autowired
	private LancamentoRepository lancRepo;
	
	@Override
	public Lancamento findById(Long id) {
		Lancamento findOne = this.lancRepo.findOne(id);
		Optional<Lancamento> lancamento = Optional.ofNullable(findOne);
		return lancamento.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id : "+id +": "+Lancamento.class.getName()));
	}

	@Override
	public List<Lancamento> findAll() {
		return this.lancRepo.findAll();
	}

	@Override
	public Lancamento save(Lancamento lancamento) {
		if (lancamento.getCodigo() != null) {
			throw new 
			ObjectSaveUpdateException(
					"Você não deve passar um id para salvar uma lançamento : "+ Lancamento.class.getName()); 
		}
		return this.lancRepo.save(lancamento);
	}

	
	@Override
	public Lancamento update(Lancamento lancamento) {
		if (lancamento.getCodigo() != null) {
			this.findById(lancamento.getCodigo());
		}else {
			throw new
			ObjectSaveUpdateException(
					"Você deve passar um id para atualizar uma lançamento : "+ Lancamento.class.getName()); 
		}
		return this.lancRepo.save(lancamento);
	}

	@Override
	public void deleteById(Long id) {
		this.findById(id);
		this.lancRepo.delete(id);
	}
	
}




