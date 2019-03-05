package br.com.workmade.algamoneybackendapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.workmade.algamoneybackendapi.model.Lancamento;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, ILancamentoRepositoryQuery {

}
