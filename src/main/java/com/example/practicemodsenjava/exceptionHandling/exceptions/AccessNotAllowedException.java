package com.example.practicemodsenjava.exceptionHandling.exceptions;

public class AccessNotAllowedException extends RuntimeException{
    public AccessNotAllowedException(){
        super("Access not allowed");
    }
}