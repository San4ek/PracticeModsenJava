package com.example.practicemodsenjava.exceptionHandling.exceptions;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(){
        super("Invalid request");
    }
}
