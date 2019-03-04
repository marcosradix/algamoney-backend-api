package br.com.workmade.algamoneybackendapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lancamento")
@Data
@NoArgsConstructor
public class Lancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	@NotNull(message="{field.nome.notEmpty.message}")
	private String descricao;

	@NotNull(message="{field.nome.notEmpty.message}")
	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;
	
	@Column(name = "data_pagamento")
	private LocalDate dataPagamento;

	@NotNull(message="{field.nome.notEmpty.message}")
	private BigDecimal valor;
	private String observacao;

	@NotNull(message="{field.nome.notEmpty.message}")
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_lancamento")
	private TipoLancamento tipo;

	@NotNull(message="{field.nome.notEmpty.message}")
	@ManyToOne
	@JoinColumn(name = "codigo_categoria")
	private Categoria categoria;
	@NotNull(message="{field.nome.notEmpty.message}")
	@ManyToOne
	@JoinColumn(name = "codigo_pessoa")
	private Pessoa pessoa;

}
