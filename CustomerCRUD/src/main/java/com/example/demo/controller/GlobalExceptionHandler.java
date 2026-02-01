package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.InvalidId;
import com.example.demo.exception.InvalidMobNumber;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidMobNumber.class)
	public ResponseEntity<?> invalidMobNumber(InvalidMobNumber e){
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(e.getMessage());
		
	}
	@ExceptionHandler(InvalidId.class)
	public ResponseEntity<?> invalidId(InvalidId i){
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(i.getMessage());
		
	}
}
