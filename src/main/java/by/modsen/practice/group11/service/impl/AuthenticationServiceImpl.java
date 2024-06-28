package by.modsen.practice.group11.service.impl;

import by.modsen.practice.group11.model.UserJwt;
import by.modsen.practice.group11.model.dto.request.LoginRequest;
import by.modsen.practice.group11.model.dto.request.SignUpRequest;
import by.modsen.practice.group11.model.dto.request.TokenRefreshRequest;
import by.modsen.practice.group11.model.dto.response.JwtResponse;
import by.modsen.practice.group11.model.dto.response.MessageResponse;
import by.modsen.practice.group11.model.dto.response.TokenRefreshResponse;
import by.modsen.practice.group11.model.entity.PersonalInfo;
import by.modsen.practice.group11.model.entity.TokenRefresh;
import by.modsen.practice.group11.model.entity.User;
import by.modsen.practice.group11.model.enums.LinePattern;
import by.modsen.practice.group11.model.enums.Role;
import by.modsen.practice.group11.repository.PersonalInfoRepository;
import by.modsen.practice.group11.repository.UserRepository;
import by.modsen.practice.group11.service.AuthenticationService;
import by.modsen.practice.group11.service.exception.ResourceStateException;
import by.modsen.practice.group11.utils.AccessTokenUtils;
import by.modsen.practice.group11.utils.RefreshTokenUtils;
import by.modsen.practice.group11.utils.StringFormatMatcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

// ToDo: Add exceptions

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PersonalInfoRepository personalInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AccessTokenUtils accessTokenUtils;
    private final RefreshTokenUtils refreshTokenUtils;
    private final RedisTemplate<String, String> redisTemplate;
    private final StringFormatMatcher stringFormatMatcher;

    @Override
    public JwtResponse loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserJwt userJwt = (UserJwt) authentication.getPrincipal();
        String accessToken = accessTokenUtils.generateAccessToken(userJwt);
        TokenRefresh refreshToken = refreshTokenUtils.takeOrCreateActualRefreshToken(userJwt.getId());
        return new JwtResponse(accessToken, refreshToken.getToken());
    }

    @Override
    public MessageResponse signUpUser(SignUpRequest signUpRequest) {
        LinePattern linePattern = stringFormatMatcher.isLoginOrUsername(signUpRequest.login());

        if (linePattern != LinePattern.LOGIN) {
            throw new ResourceStateException(HttpStatus.CONFLICT.value() * 100 + 11, "Request login is not valid");
        }

        linePattern = stringFormatMatcher.isLoginOrUsername(signUpRequest.email());

        if (linePattern != LinePattern.EMAIL) {
            throw new ResourceStateException(HttpStatus.CONFLICT.value() * 100 + 12, "Request email is not valid");
        }

        if (userRepository.existsByLogin(signUpRequest.login())) {
            return new MessageResponse("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.email())) {
            return new MessageResponse("Error: Email is already in use!");
        }

        // Create new user's account
        PersonalInfo personalInfo = PersonalInfo.builder()
                .birthday(signUpRequest.birthday())
                .fullName(signUpRequest.fullName())
                .gender(signUpRequest.gender())
                .build();
        personalInfoRepository.save(personalInfo);
        userRepository.save(User.builder()
                .email(signUpRequest.email())
                .login(signUpRequest.login())
                .password(passwordEncoder.encode(signUpRequest.password()))
                .role(Role.ROLE_CUSTOMER)
                .personalInfo(personalInfo)
                .build());

        return new MessageResponse("User registered successfully!");
    }

    @Override
    public MessageResponse logoutUser(UserJwt userJwt) {

        UUID userId = userJwt.getId();
        refreshTokenUtils.deleteByUserId(userId);
        redisTemplate.delete(userId.toString());
        return new MessageResponse("Log out successful!");
    }

    @Override
    public TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest) {
        String requestRefreshToken = tokenRefreshRequest.refreshToken();

        return refreshTokenUtils.findByStringRefreshToken(requestRefreshToken)
                .map(refreshTokenUtils::verifyExpiration)
                .map(TokenRefresh::getUser)
                .map(user -> {
                    String token = accessTokenUtils.generateAccessTokenFromUsername(user.getLogin());

                    return new TokenRefreshResponse(token, requestRefreshToken);
                })
                .orElseThrow(() -> new RuntimeException(String.format("Refresh token %s is not in database!", requestRefreshToken)));
    }
}
