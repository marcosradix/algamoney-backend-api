package br.com.workmade.algamoneybackendapi.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5310311961324041391L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
