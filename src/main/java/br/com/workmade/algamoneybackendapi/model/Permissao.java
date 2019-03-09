package br.com.workmade.algamoneybackendapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="permissao")
@Data
public class Permissao {
	
	@Id
	@EqualsAndHashCode.Include
	private Long codigo;
	private String descricao;

}
