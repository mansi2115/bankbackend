package com.cap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.cap.controller.RequestResponse;

@ControllerAdvice
public class BankExceptionHandler  {



 @ExceptionHandler(InvalidInputException.class)

	public final ResponseEntity<RequestResponse> handleInvalidInputException(InvalidInputException ex, WebRequest req) {

	        RequestResponse result = new RequestResponse(false,null);
	        result.setErrors(ex.getMessage());
			return new ResponseEntity<RequestResponse>(result, HttpStatus.NOT_ACCEPTABLE);

	}

 @ExceptionHandler(InvalidActivityException.class)

	public final ResponseEntity<RequestResponse> handleInvalidActivityException(InvalidActivityException ex, WebRequest req) {
	 			RequestResponse result = new RequestResponse(false,null);
	 			result.setErrors(ex.getMessage());
			return new ResponseEntity<RequestResponse>(result, HttpStatus.NOT_ACCEPTABLE);

	}
 @ExceptionHandler(Exception.class)
 public final ResponseEntity<RequestResponse> handleException(Exception ex, WebRequest req) {

     RequestResponse result = new RequestResponse(false,null);
     result.setErrors(ex.getMessage());
		return new ResponseEntity<RequestResponse>(result, HttpStatus.NOT_ACCEPTABLE);

}
}
