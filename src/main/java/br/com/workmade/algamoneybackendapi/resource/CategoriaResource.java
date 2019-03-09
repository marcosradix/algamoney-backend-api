package br.com.workmade.algamoneybackendapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.workmade.algamoneybackendapi.event.CreateResourceEvent;
import br.com.workmade.algamoneybackendapi.model.Categoria;
import br.com.workmade.algamoneybackendapi.service.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService catService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	

	@GetMapping(value="/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
	public ResponseEntity<Categoria> buscarCategoria(@PathVariable Long id){
		Categoria cat = catService.findById(id);
		return ResponseEntity.ok(cat);
	}
	
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
	@GetMapping
	public ResponseEntity<List<Categoria>> listarCategorias(){
		List<Categoria> findAll = this.catService.findAll();
		return  ResponseEntity.ok(findAll);
	}
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Categoria> salvarCategoria(@RequestBody @Valid Categoria categoria, HttpServletResponse response) {
		Categoria save = this.catService.save(categoria);
		this.publisher.publishEvent(new CreateResourceEvent(this, response, save.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')")
	@RequestMapping(method= RequestMethod.PUT, value="/{id}")
	public ResponseEntity<Categoria> atualizarCategoria(@RequestBody @Valid Categoria categoria, @PathVariable Long id){
		categoria.setCodigo(id);
		Categoria save = this.catService.update(categoria);
		 return ResponseEntity.ok().body(save);
	}
}
