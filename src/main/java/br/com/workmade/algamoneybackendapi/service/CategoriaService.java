package br.com.workmade.algamoneybackendapi.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workmade.algamoneybackendapi.exceptions.ObjectNotFoundException;
import br.com.workmade.algamoneybackendapi.exceptions.ObjectSaveUpdateException;
import br.com.workmade.algamoneybackendapi.iservice.ICategoriaService;
import br.com.workmade.algamoneybackendapi.model.Categoria;
import br.com.workmade.algamoneybackendapi.repository.CategoriaRepository;

@Service
public class CategoriaService implements ICategoriaService{

	@Autowired
	private CategoriaRepository catRepo;
	
	@Override
	public Categoria findById(Long id) {
		Categoria findOne = this.catRepo.findOne(id);
		Optional<Categoria> categoria = Optional.ofNullable(findOne);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id : "+id +": "+Categoria.class.getName()));
	}

	@Override
	public List<Categoria> findAll() {
		return this.catRepo.findAll();
	}

	@Override
	public Categoria save(Categoria categoria) {
		if (categoria.getCodigo() != null) {
			throw new 
			ObjectSaveUpdateException(
					"Você não deve passar um id para salvar uma categoria : "+ Categoria.class.getName()); 
		}
		return this.catRepo.save(categoria);
	}

	
	@Override
	public Categoria update(Categoria categoria) {
		if (categoria.getCodigo() != null) {
			this.findById(categoria.getCodigo());
		}else {
			throw new
			ObjectSaveUpdateException(
					"Você deve passar um id para atualizar uma categoria : "+ Categoria.class.getName()); 
		}
		return this.catRepo.save(categoria);
	}
}
