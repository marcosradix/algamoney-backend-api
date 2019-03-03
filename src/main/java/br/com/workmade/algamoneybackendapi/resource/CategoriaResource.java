package br.com.workmade.algamoneybackendapi.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Categoria> salvarCategoria(@RequestBody @Valid Categoria categoria, HttpServletResponse response) {
		Categoria save = this.catService.save(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(save.getCodigo())
				.toUri();
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}
	@RequestMapping(method= RequestMethod.PUT)
	public ResponseEntity<Categoria> atualizarCategoria(@RequestBody @Valid Categoria categoria){
		Categoria save = this.catService.update(categoria);
		 return ResponseEntity.ok().body(save);
	}
}
