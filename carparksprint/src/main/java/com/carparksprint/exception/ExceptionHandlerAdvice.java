package com.carparksprint.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(DeleteParkingException.class)
	public ResponseEntity<String> deleteEmployee(DeleteParkingException dex)
	{
		return  new ResponseEntity<>(dex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<String> someThingWrong(CustomException cex)
	{
		return  new ResponseEntity<>(cex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ParkingNotFoundException.class)
	public ResponseEntity<String> parkingNotFound(ParkingNotFoundException pex)
	{
		return  new ResponseEntity<>(pex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<String> adminError(AdminException aex)
	{
		return  new ResponseEntity<>(aex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
}
