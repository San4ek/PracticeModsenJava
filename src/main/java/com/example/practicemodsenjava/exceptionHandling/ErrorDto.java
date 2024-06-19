package com.example.practicemodsenjava.exceptionHandling;

import java.time.LocalTime;

public record ErrorDto (
    LocalTime time,
    String message
){}
