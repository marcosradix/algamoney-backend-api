package br.com.workmade.algamoneybackendapi.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourcesExceptionHandler implements IResourcesExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(ObjectNotFoundException.class)
	@Override
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(ObjectSaveUpdateException.class)
	@Override
	public ResponseEntity<StandardError> objectSaveUpdateException(ObjectSaveUpdateException e,
			HttpServletRequest request) {
		StandardError error = new StandardError(
				HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}


	@Override
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<StandardError> unrecognizedPropertyException(HttpMessageNotReadableException e,
			HttpServletRequest request) {
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(),
				this.messageSource.getMessage("unreconized.fields", null, LocaleContextHolder.getLocale()),
				System.currentTimeMillis());
		System.out.println("erro " + e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@Override
	public ResponseEntity<MethodArgumentNotValidExceptionErro> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
		   List<Map<String, String>> mapList = new ArrayList<>();
		   for (ObjectError error : e.getBindingResult().getAllErrors()) {
		       String defaultMessage = error.getDefaultMessage();
			   String fieldError = ((FieldError) error).getField();
			   Map<String, String> map = new HashMap<>();
			   map.put("defaultMessage", defaultMessage);
			   map.put("fieldError", fieldError);
			   mapList.add(map);
		   }
		MethodArgumentNotValidExceptionErro error = new MethodArgumentNotValidExceptionErro(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				this.messageSource.getMessage("null.fields", null, LocaleContextHolder.getLocale()), mapList);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@Override
	public ResponseEntity<StandardError> httpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
		StandardError error = new StandardError(HttpStatus.METHOD_NOT_ALLOWED.value(), "O verbo HTTP informado não é suportado pelo endpoint, ou falta parâmetro de URL", System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
	}
	@ExceptionHandler(PessoaInativaException.class)
	@Override
	public ResponseEntity<StandardError> pessoaInativaException(PessoaInativaException e, HttpServletRequest request) {
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}












