package by.modsen.practice.group11.filter;

import by.modsen.practice.group11.model.UserJwt;
import by.modsen.practice.group11.utils.AccessTokenUtils;
import by.modsen.practice.group11.utils.UserJwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
//@AllArgsConstructor(onConstructor_ = {@Autowired})
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private final AccessTokenUtils accessTokenUtils;

    private final UserJwtUtils userJwtUtils;

    private final RedisTemplate<String, String> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String accessToken = parseJwt(request);
            if (accessToken != null && accessTokenUtils.validateAccessToken(accessToken)) {

                String username = accessTokenUtils.getUserNameFromAccessToken(accessToken);
                UserDetails userDetails = userJwtUtils.loadUserByUsername(username);
                String userId = ((UserJwt) userDetails).getId().toString();

                if (Boolean.TRUE.equals(redisTemplate.hasKey(userId)) && Objects.equals(redisTemplate.opsForValue().get(userId), accessToken)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                            userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}
