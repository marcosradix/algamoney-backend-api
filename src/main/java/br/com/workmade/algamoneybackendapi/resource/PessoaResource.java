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

import br.com.workmade.algamoneybackendapi.model.Pessoa;
import br.com.workmade.algamoneybackendapi.service.PessoaService;

@RestController
@RequestMapping(value="/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaService pesService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Pessoa> pessoa(@PathVariable Long id){
		Pessoa cat = pesService.findById(id);
		return ResponseEntity.ok(cat);
	}
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> listar(){
		List<Pessoa> findAll = this.pesService.findAll();
		return  ResponseEntity.ok(findAll);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Pessoa> salvarPessoa(@RequestBody @Valid Pessoa pessoa, HttpServletResponse response) {
		Pessoa save = this.pesService.save(pessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(save.getCodigo())
				.toUri();
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}
	@RequestMapping(method= RequestMethod.PUT)
	public ResponseEntity<Pessoa> atualizarPessoa(@RequestBody @Valid Pessoa pessoa){
		Pessoa save = this.pesService.update(pessoa);
		 return ResponseEntity.ok().body(save);
	}
}
