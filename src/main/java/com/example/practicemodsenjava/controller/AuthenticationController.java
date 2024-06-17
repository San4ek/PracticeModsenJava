package com.example.practicemodsenjava.controller;

import com.example.practicemodsenjava.model.dto.request.LoginRequest;
import com.example.practicemodsenjava.model.dto.request.SignupRequest;
import com.example.practicemodsenjava.model.dto.response.JwtResponse;
import com.example.practicemodsenjava.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtResponse login(
            @RequestBody @Valid LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtResponse signUp(
            @RequestBody @Valid SignupRequest signupRequest) {
        return authenticationService.signUp(signupRequest);
    }

}
