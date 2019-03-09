package br.com.workmade.algamoneybackendapi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="usuario")
@Data
public class Usuario {
	
	@Id
	@EqualsAndHashCode.Include
	private Long codigo;
	//@EqualsAndHashCode.Exclude 
	private String nome;
	//@EqualsAndHashCode.Exclude 
	private String email;
	//@EqualsAndHashCode.Exclude 
	private String senha;
	@ManyToMany(fetch=FetchType.EAGER) // sempre que busca o usuário vem as permissões
	@JoinTable(name="usuario_permissao",
	joinColumns= @JoinColumn(name="codigo_usuario"),
	inverseJoinColumns= @JoinColumn(name= "codigo_permissao"))
	//@EqualsAndHashCode.Exclude 
	private List<Permissao> permissoes;

}
