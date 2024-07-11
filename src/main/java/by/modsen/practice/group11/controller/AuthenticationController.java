package by.modsen.practice.group11.controller;

import by.modsen.practice.group11.model.UserJwt;
import by.modsen.practice.group11.model.dto.request.LoginRequest;
import by.modsen.practice.group11.model.dto.request.SignUpRequest;
import by.modsen.practice.group11.model.dto.request.TokenRefreshRequest;
import by.modsen.practice.group11.model.dto.response.JwtResponse;
import by.modsen.practice.group11.model.dto.response.MessageResponse;
import by.modsen.practice.group11.model.dto.response.TokenRefreshResponse;
import by.modsen.practice.group11.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Authentication controller")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(summary = "login")
    public ResponseEntity<JwtResponse> loginUser(
            @Valid @RequestBody LoginRequest loginRequest) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authenticationService.loginUser(loginRequest));
    }

    @PostMapping("/sign-up")
    @Operation(summary = "sign-up")
    public ResponseEntity<MessageResponse> signUpUser(
            @Valid @RequestBody SignUpRequest signupRequest) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authenticationService.signUpUser(signupRequest));
    }

    @PostMapping("/logout")
    @Operation(summary = "logout")
    public ResponseEntity<MessageResponse> logoutUser(
            @Valid @AuthenticationPrincipal UserJwt userJwt) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authenticationService.logoutUser(userJwt));
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "take refresh token")
    public ResponseEntity<TokenRefreshResponse> refreshToken(
            @Valid @RequestBody TokenRefreshRequest tokenRefreshRequest) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authenticationService.refreshToken(tokenRefreshRequest));
    }
}
