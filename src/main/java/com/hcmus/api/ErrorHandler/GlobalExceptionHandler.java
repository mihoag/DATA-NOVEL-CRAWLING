package com.hcmus.api.ErrorHandler;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.expression.BeanExpressionContextAccessor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hcmus.api.ResponseData.ResponseError;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
    public ResponseError handleGenericException(HttpServletRequest request, Exception ex)
    {
	  ResponseError error = new ResponseError(new Date(), request.getServletPath(),ex.getMessage());
	  LOGGER.error(ex.getMessage(), ex);
	  return error;
    }
	
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseError handleBadRequestException(HttpServletRequest request, Exception ex) {
		ResponseError error = new ResponseError(new Date(), request.getServletPath(), ex.getMessage());
		error.setPath(request.getServletPath());
		LOGGER.error(ex.getMessage(), ex);
		return error;
	}	
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseError handleConstraintViolationException(HttpServletRequest request, Exception ex) {
		ConstraintViolationException violationException = (ConstraintViolationException) ex;
        
		String message = "";
		StringBuilder strBuilder = new StringBuilder();
		
		var constraintViolations = violationException.getConstraintViolations();
		constraintViolations.forEach(constraint -> {
			strBuilder.append((constraint.getPropertyPath() + ": " + constraint.getMessage())).append("\n");
		});
		
		message = strBuilder.toString();
		ResponseError error = new ResponseError(new Date(),request.getServletPath(), message);	
		LOGGER.error(ex.getMessage(), ex);
		return error;
	}	
	
	@ExceptionHandler({NovelNotFoundException.class, AuthorNotFoundException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
    public ResponseError handleNotFoundException(HttpServletRequest request, Exception ex)
    {
	  ResponseError error = new ResponseError(new Date(), request.getServletPath(),ex.getMessage());
	  LOGGER.error(ex.getMessage(), ex);
	  return error;
    }
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		// TODO Auto-generated method stub
		LOGGER.error(ex.getMessage(), ex);
		String message = "";
		StringBuilder strBuiler = new StringBuilder();
		
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		    fieldErrors.forEach(fieldError ->
		    {
		    	strBuiler.append(fieldError.getDefaultMessage()).append("\n");
		    });
	    message = strBuiler.toString();
		ResponseError error = new ResponseError(new Date(), ((ServletWebRequest)request).getRequest().getServletPath(), message);	  
		return new ResponseEntity<>(error, headers, status);
	}
}
