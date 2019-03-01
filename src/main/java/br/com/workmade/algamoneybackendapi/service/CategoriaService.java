package br.com.workmade.algamoneybackendapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workmade.algamoneybackendapi.iservice.ICategoriaService;
import br.com.workmade.algamoneybackendapi.model.Categoria;
import br.com.workmade.algamoneybackendapi.repository.CategoriaRepository;

@Service
public class CategoriaService implements ICategoriaService{

	@Autowired
	private CategoriaRepository catRepo;
	
	@Override
	public Categoria findById(Long id) {
		return this.catRepo.findOne(id);
	}

	@Override
	public List<Categoria> findAll() {
		return this.catRepo.findAll();
	}

}
