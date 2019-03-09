package br.com.workmade.algamoneybackendapi.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.workmade.algamoneybackendapi.event.CreateResourceEvent;
import br.com.workmade.algamoneybackendapi.model.Lancamento;
import br.com.workmade.algamoneybackendapi.repository.filter.LancamentoFilter;
import br.com.workmade.algamoneybackendapi.service.LancamentoService;

@RestController
@RequestMapping(value="/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoService lancService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO')")
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<Lancamento> buscarLancamento(@PathVariable Long id){
		Lancamento lanc = lancService.findById(id);
		return ResponseEntity.ok(lanc);
	}
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Lancamento>> filtrarLancamentos(LancamentoFilter lancamentoFilter, Pageable  pageable ){
		Page<Lancamento> findAll = this.lancService.findByQuery(lancamentoFilter, pageable);
		return  ResponseEntity.ok(findAll);
	}
	
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Lancamento> salvarLancamento(@RequestBody @Valid Lancamento lancamento, HttpServletResponse response) {
		Lancamento save = this.lancService.save(lancamento);
		this.publisher.publishEvent(new CreateResourceEvent(this, response, save.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO')")
	@RequestMapping(method= RequestMethod.PUT, value="/{id}")
	public ResponseEntity<Lancamento> atualizarLancamento(@RequestBody @Valid Lancamento lancamento, @PathVariable Long id){
		lancamento.setCodigo(id);
		Lancamento save = this.lancService.update(lancamento);
		 return ResponseEntity.ok().body(save);
	}
	@PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO')")
	@RequestMapping(method= RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
			this.lancService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
