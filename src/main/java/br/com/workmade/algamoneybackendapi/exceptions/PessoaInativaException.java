package br.com.workmade.algamoneybackendapi.exceptions;

public class PessoaInativaException extends RuntimeException {

	private static final long serialVersionUID = -4885981844134178940L;

	public PessoaInativaException(String msg) {
		super(msg);
	}
	
	public PessoaInativaException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
