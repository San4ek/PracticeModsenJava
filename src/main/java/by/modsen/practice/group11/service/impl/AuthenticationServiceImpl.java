package by.modsen.practice.group11.service.impl;

import by.modsen.practice.group11.model.dto.request.LoginRequest;
import by.modsen.practice.group11.model.dto.request.SignUpRequest;
import by.modsen.practice.group11.model.dto.response.JwtResponse;
import by.modsen.practice.group11.model.entity.User;
import by.modsen.practice.group11.model.enums.Role;
import by.modsen.practice.group11.repository.UserRepository;
import by.modsen.practice.group11.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl  {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//    private final JwtService jwtService;

//    public JwtResponse login(LoginRequest loginRequest) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.login(), loginRequest.password())
//        );
//
//        User user = userRepository
//                .findByLogin(loginRequest.login())
//                .orElseThrow();
//
//        String jwt = jwtService.generateToken(user);
//        return new JwtResponse(jwt);
//
//
//    }
//
//    public JwtResponse signUp(SignUpRequest signupRequest) {
//        // TODO add personal info
//        User user = User.builder()
//                .login(signupRequest.login())
//                .email(signupRequest.email())
//                .password(passwordEncoder.encode(signupRequest.password()))
//                .role(Role.CUSTOMER)
//                .build();
//
//        userRepository.save(user);
//
//        String jwt = jwtService.generateToken(user);
//        return new JwtResponse(jwt);
//
//    }
}
