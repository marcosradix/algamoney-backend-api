package br.com.workmade.algamoneybackendapi.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="pessoa")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long codigo;
	@NotNull(message="{field.nome.notEmpty.message}")
	private String nome;
	@NotNull(message="{field.nome.notEmpty.message}")
	@Column(name="ativo")
	private Boolean isAtivo;
	@Embedded
	private Endereco endereco;

}
