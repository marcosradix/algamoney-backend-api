package br.com.workmade.algamoneybackendapi.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StandardError implements Serializable{
	private static final long serialVersionUID = 2258242360129168631L;
	private Integer status;
	private String message;
	private Long timeStamp;
}
