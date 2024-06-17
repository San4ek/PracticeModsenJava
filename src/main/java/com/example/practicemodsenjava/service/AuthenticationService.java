package com.example.practicemodsenjava.service;

import com.example.practicemodsenjava.model.dto.request.LoginRequest;
import com.example.practicemodsenjava.model.dto.request.SignupRequest;
import com.example.practicemodsenjava.model.dto.response.JwtResponse;
import com.example.practicemodsenjava.model.entity.User;
import com.example.practicemodsenjava.model.enums.Role;
import com.example.practicemodsenjava.repository.UserRepository;
import com.example.practicemodsenjava.service.impl.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public JwtResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.login(), loginRequest.password())
        );

        User user = userRepository
                        .findByLogin(loginRequest.login())
                        .orElseThrow();

        String jwt = jwtService.generateToken(user);
        return new JwtResponse(jwt);


    }

    public JwtResponse signUp(SignupRequest signupRequest) {
        // TODO add personal info
        User user = User.builder()
                .login(signupRequest.login())
                .email(signupRequest.email())
                .password(passwordEncoder.encode(signupRequest.password()))
                .role(Role.CUSTOMER)
                .build();

        userRepository.save(user);

        String jwt = jwtService.generateToken(user);
        return new JwtResponse(jwt);

    }
}

