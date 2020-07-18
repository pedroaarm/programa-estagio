package com.aiko.apiolhovivo.exception;

import java.util.Date;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.aiko.apiolhovivo.util.ErrorDetails;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public final ResponseEntity<ErrorDetails> handleUserNotFoundException(NotFoundException ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(
    		HttpStatus.NOT_FOUND,
    		ex.getMessage(),
    		request.getDescription(false));
    
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(InternalServerErrorException.class)
  public final ResponseEntity<ErrorDetails> handleInternalServerErrorException(InternalServerErrorException ex, WebRequest request){
	  ErrorDetails errorDetails = new ErrorDetails(
			  HttpStatus.INTERNAL_SERVER_ERROR, 
			  ex.getMessage(),
			  request.getDescription(false));
	  return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  @ExceptionHandler(BadRequestException.class)
  public final ResponseEntity<ErrorDetails> handlerBadRequestException(BadRequestException ex, WebRequest request){
	  ErrorDetails errorDetails = new ErrorDetails(
			  HttpStatus.BAD_REQUEST, 
			  ex.getMessage(),
			  request.getDescription(false));
	  return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }
  
 
  
	  
  
}

