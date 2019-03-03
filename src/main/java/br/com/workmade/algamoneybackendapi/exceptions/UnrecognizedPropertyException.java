package br.com.workmade.algamoneybackendapi.exceptions;

public class UnrecognizedPropertyException extends RuntimeException {

	private static final long serialVersionUID = -5310311961324041391L;

	public UnrecognizedPropertyException(String msg) {
		super(msg);
	}
	
	public UnrecognizedPropertyException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
