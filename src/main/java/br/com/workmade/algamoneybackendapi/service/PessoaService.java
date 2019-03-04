package br.com.workmade.algamoneybackendapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workmade.algamoneybackendapi.exceptions.ObjectNotFoundException;
import br.com.workmade.algamoneybackendapi.exceptions.ObjectSaveUpdateException;
import br.com.workmade.algamoneybackendapi.iservice.IPessoaService;
import br.com.workmade.algamoneybackendapi.model.Pessoa;
import br.com.workmade.algamoneybackendapi.repository.PessoaRepository;

@Service
public class PessoaService implements IPessoaService{

	@Autowired
	private PessoaRepository pesRepo;
	
	@Override
	public Pessoa findById(Long id) {
		Pessoa findOne = this.pesRepo.findOne(id);
		Optional<Pessoa> pessoa = Optional.ofNullable(findOne);
		return pessoa.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id : "+id +": "+Pessoa.class.getName()));
	}

	@Override
	public List<Pessoa> findAll() {
		return this.pesRepo.findAll();
	}

	@Override
	public Pessoa save(Pessoa pessoa) {
		if (pessoa.getCodigo() != null) {
			throw new 
			ObjectSaveUpdateException(
					"Você não deve passar um id para salvar uma pessoa : "+ Pessoa.class.getName()); 
		}
		return this.pesRepo.save(pessoa);
	}

	
	@Override
	public Pessoa update(Pessoa pessoa) {
		if (pessoa.getCodigo() != null) {
			this.findById(pessoa.getCodigo());
		}else {
			throw new
			ObjectSaveUpdateException(
					"Você deve passar um id para atualizar uma pessoa : "+ Pessoa.class.getName()); 
		}
		return this.pesRepo.save(pessoa);
	}

	@Override
	public void deleteById(Long id) {
		this.findById(id);
		this.pesRepo.delete(id);
	}
	@Override
	public Pessoa atualizarPropriedadeAtivo(Long id, Boolean isAtivo) {
		Pessoa pessoaEncontrada = this.findById(id);
		pessoaEncontrada.setIsAtivo(isAtivo);
		return this.pesRepo.save(pessoaEncontrada);
	}
	
	
	
}




