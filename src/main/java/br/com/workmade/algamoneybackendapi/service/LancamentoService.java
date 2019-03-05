package br.com.workmade.algamoneybackendapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.workmade.algamoneybackendapi.exceptions.ObjectNotFoundException;
import br.com.workmade.algamoneybackendapi.exceptions.ObjectSaveUpdateException;
import br.com.workmade.algamoneybackendapi.exceptions.PessoaInativaException;
import br.com.workmade.algamoneybackendapi.iservice.ILancamentoService;
import br.com.workmade.algamoneybackendapi.model.Lancamento;
import br.com.workmade.algamoneybackendapi.model.Pessoa;
import br.com.workmade.algamoneybackendapi.repository.LancamentoRepository;
import br.com.workmade.algamoneybackendapi.repository.filter.LancamentoFilter;

@Service
public class LancamentoService implements ILancamentoService{

	private static final String OBJETO_NAO_ENCONTRADO_ID = "Objeto não encontrado! Id : ";

	private static final String DEVE_PASSAR_UM_ID_PARA_ATUALIZAR_UMA_LANCAMENTO = "Você deve passar um id para atualizar uma lançamento : ";

	private static final String NAO_PASSAR_UM_ID_PARA_SALVAR_UMA_LANCAMENTO = "Você não deve passar um id para salvar uma lançamento : ";

	private static final String USUARIO_INATIVO = " Não é possível salvar lançamento para um usuário inativo.";

	@Autowired
	private LancamentoRepository lancRepo;
	
	@Autowired
	private PessoaService pesService;
	
	@Override
	public Lancamento findById(Long id) {
		Lancamento findOne = this.lancRepo.findOne(id);
		Optional<Lancamento> lancamento = Optional.ofNullable(findOne);
		return lancamento.orElseThrow(() -> new ObjectNotFoundException(
				OBJETO_NAO_ENCONTRADO_ID+id +": "+Lancamento.class.getName()));
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
					NAO_PASSAR_UM_ID_PARA_SALVAR_UMA_LANCAMENTO+ Lancamento.class.getName()); 
		}
		Pessoa pessoa = this.pesService.findById(lancamento.getPessoa().getCodigo());
		if(!pessoa.getIsAtivo()) {
			throw new PessoaInativaException(USUARIO_INATIVO + Pessoa.class.getName());
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
					DEVE_PASSAR_UM_ID_PARA_ATUALIZAR_UMA_LANCAMENTO+ Lancamento.class.getName()); 
		}
		return this.lancRepo.save(lancamento);
	}

	@Override
	public void deleteById(Long id) {
		this.findById(id);
		this.lancRepo.delete(id);
	}

	@Override
	public Page<Lancamento> findByQuery(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return this.lancRepo.filtrar(lancamentoFilter, pageable);
	}
	
}




