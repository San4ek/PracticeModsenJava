package com.example.practicemodsenjava.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorDto> handleNotFoundException(NoSuchElementException ex){
        return new ResponseEntity<>(new ErrorDto(LocalTime.now(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

}

