package by.modsen.practice.group11.utils;

import by.modsen.practice.group11.model.UserJwt;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AccessTokenUtils {

    private static final Logger logger = LoggerFactory.getLogger(AccessTokenUtils.class);

    @Value("${app.token-access.key-secret}")
    private String accessTokenKeySecret;

    @Value("${app.token-access.lifetime}")
    private Long accessTokenLifetime;

    public String generateAccessToken(UserJwt userJwt) {
        return generateAccessTokenFromUsername(userJwt.getUsername());
    }

    public String generateAccessTokenFromUsername(String login) {
        return Jwts.builder().setSubject(login).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenLifetime * 1000)).signWith(SignatureAlgorithm.HS512, accessTokenKeySecret)
                .compact();
    }

    public String getUserNameFromAccessToken(String accessToken) {
        return Jwts.parser().setSigningKey(accessTokenKeySecret).parseClaimsJws(accessToken).getBody().getSubject();
    }

    public boolean validateAccessToken(String accessToken) {
        try {
            Jwts.parser().setSigningKey(accessTokenKeySecret).parseClaimsJws(accessToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
