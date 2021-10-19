package mz.gov.misau.sigsmi.ws.exception.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import mz.gov.misau.sigsmi.ws.exception.resource.DenidOperationAndInvalidResourceException;
import mz.gov.misau.sigsmi.ws.ui.model.response.MensagemErro;

@ControllerAdvice
public class MyApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<MensagemErro> errors = new ArrayList<>();
		for(FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			String mensagem = messageSource.getMessage(fieldError,LocaleContextHolder.getLocale());
			
			errors.add(new MensagemErro(HttpStatus.BAD_REQUEST.value(),
					mensagem, LocalDateTime.now()));
		}
		
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		MensagemErro error = new MensagemErro(HttpStatus.BAD_REQUEST.value(), 
				messageSource.getMessage("fieldFormatNotValid.format", null, 
						LocaleContextHolder.getLocale()), LocalDateTime.now());
		
		return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({DenidOperationAndInvalidResourceException.class})
	public ResponseEntity<Object> handleDenidOperationAndInvalidResourceException(DenidOperationAndInvalidResourceException ex){
		MensagemErro error = new MensagemErro(HttpStatus.BAD_REQUEST.value(), 
				messageSource.getMessage("operacao.operacao-negada",null, 
						LocaleContextHolder.getLocale()),LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}











