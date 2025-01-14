package com.eazykart.orders.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.eazykart.orders.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception, WebRequest webRequest){
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage(),LocalDateTime.now());
		return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(OrderAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto> handleOrderAlreadyExistsException(OrderAlreadyExistsException exception, WebRequest webRequest){
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),HttpStatus.BAD_REQUEST,exception.getMessage(),LocalDateTime.now());
		return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleOrderNotFoundException(OrderNotFoundException exception, WebRequest webRequest){
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),HttpStatus.NOT_FOUND,exception.getMessage(),LocalDateTime.now());
		return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
		
	}
}
