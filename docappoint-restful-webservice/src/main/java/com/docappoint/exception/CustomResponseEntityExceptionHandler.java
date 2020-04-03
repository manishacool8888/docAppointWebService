package com.docappoint.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.docappoint.responsebean.ExceptionRespone;
import com.docappoint.util.ResponseUtil;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler{

	@Autowired
	ResponseUtil responseUtil;
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionRespone> handleAllExceptions(Exception ex,WebRequest request){
		ExceptionRespone exceptionResponse = new ExceptionRespone(ex.getMessage(),request.getDescription(false));
		return responseUtil.buildErrorResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
