package br.com.workmade.algamoneybackendapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long codigo;
	@NotNull(message="{field.nome.notEmpty.message}")
	@Size(min=3, max=20, message="{size.message}")
	private String nome;

}
