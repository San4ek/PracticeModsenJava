package by.modsen.practice.group11.service;

import by.modsen.practice.group11.model.dto.request.LoginRequest;
import by.modsen.practice.group11.model.dto.request.SignUpRequest;
import by.modsen.practice.group11.model.dto.request.TokenRefreshRequest;
import by.modsen.practice.group11.model.dto.response.MessageResponse;
import by.modsen.practice.group11.model.dto.response.TokenRefreshResponse;
import by.modsen.practice.group11.model.entity.User;
import by.modsen.practice.group11.model.enums.Role;
import by.modsen.practice.group11.service.impl.JwtService;
import by.modsen.practice.group11.model.dto.response.JwtResponse;
import by.modsen.practice.group11.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

public interface AuthenticationService{
    JwtResponse loginUser(LoginRequest loginRequest);

    MessageResponse signUpUser(SignUpRequest signupRequest);

    MessageResponse logoutUser();

    TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest);
}

