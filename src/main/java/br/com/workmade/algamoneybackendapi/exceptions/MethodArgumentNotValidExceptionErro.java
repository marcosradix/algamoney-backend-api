package br.com.workmade.algamoneybackendapi.exceptions;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MethodArgumentNotValidExceptionErro implements Serializable{
	private static final long serialVersionUID = 2258242360129168631L;
	private Long timeStamp;
	private Integer status;
	private String erro;
	private  List<Map<String, String>>  errors;


}
