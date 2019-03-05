package br.com.workmade.algamoneybackendapi.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.workmade.algamoneybackendapi.model.Lancamento;
import br.com.workmade.algamoneybackendapi.repository.ILancamentoRepositoryQuery;
import br.com.workmade.algamoneybackendapi.repository.filter.LancamentoFilter;


public class LancamentoRepositoryImpl implements ILancamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Lancamento> query = this.entityManager.createQuery(criteria);
		adicionarRestricoesDePaginacaoNaQuery(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter)) ;
	}
	
	private void adicionarRestricoesDePaginacaoNaQuery(TypedQuery<Lancamento> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalDeRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual  * totalDeRegistroPorPagina;
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalDeRegistroPorPagina);
		
	}

	private Long total(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return this.entityManager.createQuery(criteria).getSingleResult();
	}



	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(StringUtils.isNotEmpty(lancamentoFilter.getDescricao()) ) {
			predicates.add(
					builder.like(builder.lower(root.get("descricao")),
					"%" +lancamentoFilter.getDescricao().toLowerCase()+"%"));
		}
		
		if(lancamentoFilter.getDataVencimentoDe() != null ) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataVencimento"),
			lancamentoFilter.getDataVencimentoDe()));
		}
		
		if(lancamentoFilter.getDataVencimentoAte() != null ) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataVencimento"),
			lancamentoFilter.getDataVencimentoAte()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}


}
