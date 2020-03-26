package com.docappoint.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.docappoint.bean.ExceptionRespone;

@Component
public class ResponseUtil {

	public ResponseEntity<ExceptionRespone> buildErrorResponseEntity(ExceptionRespone exceptionResponse
																	,HttpStatus httpStatus){
		
		return new ResponseEntity<ExceptionRespone>(exceptionResponse,httpStatus);
	}
}
