package br.com.workmade.algamoneybackendapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.workmade.algamoneybackendapi.event.CreateResourceEvent;
import br.com.workmade.algamoneybackendapi.model.Pessoa;
import br.com.workmade.algamoneybackendapi.service.PessoaService;

@RestController
@RequestMapping(value="/pessoas")
public class PessoaResource {	
	
	@Autowired
	private PessoaService pesService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Pessoa> buscarPessoa(@PathVariable Long id){
		Pessoa cat = pesService.findById(id);
		return ResponseEntity.ok(cat);
	}
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> listarPessoas(){
		List<Pessoa> findAll = this.pesService.findAll();
		return  ResponseEntity.ok(findAll);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Pessoa> salvarPessoa(@RequestBody @Valid Pessoa pessoa, HttpServletResponse response) {
		Pessoa save = this.pesService.save(pessoa);
		this.publisher.publishEvent(new CreateResourceEvent(this, response, save.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}
	@RequestMapping(method= RequestMethod.PUT, value="/{id}")
	public ResponseEntity<Pessoa> atualizarPessoa(@RequestBody @Valid Pessoa pessoa, @PathVariable Long id){
		pessoa.setCodigo(id);
		Pessoa save = this.pesService.update(pessoa);
		 return ResponseEntity.ok().body(save);
	}
	
	@RequestMapping(method= RequestMethod.PUT, value="/{id}/ativo")
	public ResponseEntity<Void> atualizarPessoaParcial(@RequestBody Boolean isAtivo, @PathVariable Long id){
		 this.pesService.atualizarPropriedadeAtivo(id, isAtivo);
		 return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method= RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
			this.pesService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}















