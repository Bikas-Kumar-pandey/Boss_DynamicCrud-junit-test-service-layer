package com.bosch.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.Entity;

@ControllerAdvice
public class GlobalException{



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) throws Exception{
        ErrorResponse errorResponse = ErrorResponse.builder().errorMessage(ex.getMessage()).build();

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
