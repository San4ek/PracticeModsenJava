package by.modsen.practice.group11.service;

import by.modsen.practice.group11.model.UserJwt;
import by.modsen.practice.group11.model.dto.request.LoginRequest;
import by.modsen.practice.group11.model.dto.request.SignUpRequest;
import by.modsen.practice.group11.model.dto.request.TokenRefreshRequest;
import by.modsen.practice.group11.model.dto.response.JwtResponse;
import by.modsen.practice.group11.model.dto.response.MessageResponse;
import by.modsen.practice.group11.model.dto.response.TokenRefreshResponse;

public interface AuthenticationService{
    JwtResponse loginUser(LoginRequest loginRequest);

    MessageResponse signUpUser(SignUpRequest signupRequest);

    MessageResponse logoutUser(UserJwt userJwt);

    TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest);
}

