package by.modsen.practice.group11.utils;

import by.modsen.practice.group11.model.entity.TokenRefresh;
import by.modsen.practice.group11.repository.TokenRefreshRepository;
import by.modsen.practice.group11.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RefreshTokenUtils {

    @Value("${app.token-refresh.lifetime}")
    private Long refreshTokenLifeTime;

    private final TokenRefreshRepository refreshTokenRepository;

    private final UserRepository userRepository;

    public Optional<TokenRefresh> findByStringRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByToken(refreshToken);
    }

    public TokenRefresh takeOrCreateActualRefreshToken(UUID userId) {
        return refreshTokenRepository.findByUserId(userId).map(tokenRefresh -> {
            try {
                tokenRefresh = verifyExpiration(tokenRefresh);
            } catch (RuntimeException ex) {
                tokenRefresh = createRefreshToken(userId);
            }
            return tokenRefresh;
        }).orElseGet(() -> createRefreshToken(userId));
    }

    public TokenRefresh createRefreshToken(UUID userId) {
        TokenRefresh refreshToken = new TokenRefresh();

        refreshToken.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenLifeTime * 1000));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public TokenRefresh verifyExpiration(TokenRefresh token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(/*token.getToken(), */"Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

    @Transactional
    public void deleteByUserId(UUID userId) {
        refreshTokenRepository.deleteByUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
    }
}
