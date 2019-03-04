package br.com.workmade.algamoneybackendapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.workmade.algamoneybackendapi.event.CreateResourceEvent;
import br.com.workmade.algamoneybackendapi.model.Lancamento;
import br.com.workmade.algamoneybackendapi.service.LancamentoService;

@RestController
@RequestMapping(value="/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoService lancService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<Lancamento> buscarLancamento(@PathVariable Long id){
		Lancamento lanc = lancService.findById(id);
		return ResponseEntity.ok(lanc);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Lancamento>> listarLancamentos(){
		List<Lancamento> findAll = this.lancService.findAll();
		return  ResponseEntity.ok(findAll);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Lancamento> salvarLancamento(@RequestBody @Valid Lancamento lancamento, HttpServletResponse response) {
		Lancamento save = this.lancService.save(lancamento);
		this.publisher.publishEvent(new CreateResourceEvent(this, response, save.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}
	@RequestMapping(method= RequestMethod.PUT, value="/{id}")
	public ResponseEntity<Lancamento> atualizarLancamento(@RequestBody @Valid Lancamento lancamento, @PathVariable Long id){
		lancamento.setCodigo(id);
		Lancamento save = this.lancService.update(lancamento);
		 return ResponseEntity.ok().body(save);
	}
}
