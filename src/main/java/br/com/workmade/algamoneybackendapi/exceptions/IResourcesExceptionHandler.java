package br.com.workmade.algamoneybackendapi.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface IResourcesExceptionHandler {
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request);
	
	public ResponseEntity<StandardError> objectSaveUpdateException(ObjectSaveUpdateException e, HttpServletRequest request);
	
	public ResponseEntity<StandardError> unrecognizedPropertyException(HttpMessageNotReadableException e, HttpServletRequest request);
	public ResponseEntity<MethodArgumentNotValidExceptionErro> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request);
}
