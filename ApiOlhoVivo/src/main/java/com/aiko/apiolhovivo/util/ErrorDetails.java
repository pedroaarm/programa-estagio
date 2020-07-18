package com.aiko.apiolhovivo.util;

import java.time.LocalDateTime;


import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorDetails {
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	  private LocalDateTime timestamp = LocalDateTime.now();
	  private HttpStatus httpstatus;
	  private String message;
	  private String details;





	public ErrorDetails(HttpStatus httpstatus, String message, String details) {

		this.httpstatus = httpstatus;
		this.message = message;
		this.details = details;
	}


	public HttpStatus getHttpstatus() {
		return httpstatus;
	}


	public void setHttpstatus(HttpStatus httpstatus) {
		this.httpstatus = httpstatus;
	}




	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	  
	  
}