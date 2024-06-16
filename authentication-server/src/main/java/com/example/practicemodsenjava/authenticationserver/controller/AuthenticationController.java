package com.example.practicemodsenjava.authenticationserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.security.authentication.AuthenticationManager;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/authentication")
@AllArgsConstructor
public class AuthenticationController {
//    private final AuthenticationManager authService;


}
