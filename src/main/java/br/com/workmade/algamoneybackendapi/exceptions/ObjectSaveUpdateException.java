package br.com.workmade.algamoneybackendapi.exceptions;

public class ObjectSaveUpdateException extends RuntimeException {

	private static final long serialVersionUID = 8564465466788374018L;

	public ObjectSaveUpdateException(String msg) {
		super(msg);
	}
	
	public ObjectSaveUpdateException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
