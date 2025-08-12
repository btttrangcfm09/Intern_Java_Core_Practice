package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // resolve MethodArgumentValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        Map<String,String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->{
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        });
        ErrorResponse errorResponse = new ErrorResponse(400, "Validation Failed", "Invalid resquest due to input error", fieldErrors);
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    // resolve ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(404, "Resource Not Found", "Can't found resource");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // resolve DuplicateResourceException
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResource(DuplicateResourceException ex){
        ErrorResponse errorResponse = new ErrorResponse(404, "Duplicate Resource Exception", "Resource is duplicated");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }}
