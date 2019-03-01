package br.com.workmade.algamoneybackendapi.iservice;

import java.util.List;

import br.com.workmade.algamoneybackendapi.model.Categoria;

public interface ICategoriaService {
	
	Categoria findById(Long id);
	List<Categoria> findAll();

}
