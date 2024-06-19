package com.example.practicemodsenjava.exceptionHandling;

import java.time.OffsetTime;

public record ErrorDto (
    OffsetTime time,
    String message
){ }
