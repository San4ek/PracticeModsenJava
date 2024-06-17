package com.example.practicemodsenjava.authenticationserver.controller;

import com.example.practicemodsenjava.authenticationserver.repository.OrderIdRepository;
import com.example.practicemodsenjava.authenticationserver.repository.UserRepository;
import com.example.practicemodsenjava.authenticationserver.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.security.authentication.AuthenticationManager;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/api/authentication")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final OrderIdRepository orderIdRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;
}
