package br.com.workmade.algamoneybackendapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.workmade.algamoneybackendapi.model.Categoria;
import br.com.workmade.algamoneybackendapi.service.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService catService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Categoria> categoria(@PathVariable Long id){
		Categoria cat = catService.findById(id);
		return ResponseEntity.ok(cat);
	}
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listar(){
		List<Categoria> findAll = this.catService.findAll();
		return  ResponseEntity.ok(findAll);
	}
	

}
