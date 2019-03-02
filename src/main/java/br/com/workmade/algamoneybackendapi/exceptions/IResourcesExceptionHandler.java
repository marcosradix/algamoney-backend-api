package br.com.workmade.algamoneybackendapi.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

public interface IResourcesExceptionHandler {
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request);
	
	public ResponseEntity<StandardError> objectSaveUpdateException(ObjectSaveUpdateException e, HttpServletRequest request);
}
