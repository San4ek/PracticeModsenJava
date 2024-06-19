package by.modsen.practice.group11.controller;

import by.modsen.practice.group11.model.dto.request.LoginRequest;
import by.modsen.practice.group11.model.dto.request.SignUpRequest;
import by.modsen.practice.group11.model.dto.request.TokenRefreshRequest;
import by.modsen.practice.group11.model.dto.response.JwtResponse;
import by.modsen.practice.group11.model.dto.response.MessageResponse;
import by.modsen.practice.group11.model.dto.response.TokenRefreshResponse;
import by.modsen.practice.group11.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.loginUser(loginRequest));
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageResponse> signUpUser(@Valid @RequestBody SignUpRequest signupRequest) {
        return ResponseEntity.ok(authenticationService.signUpUser(signupRequest));
    }

    @PostMapping("/signout")
    public ResponseEntity<MessageResponse> logoutUser() {
        return ResponseEntity.ok(authenticationService.logoutUser());
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@Valid @RequestBody TokenRefreshRequest tokenRefreshRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(tokenRefreshRequest));
    }
}