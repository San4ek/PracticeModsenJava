package by.modsen.practice.group11.config;

import by.modsen.practice.group11.model.UserJwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class UserHolderConfiguration {

    @Bean
    @RequestScope
    public UserJwt takeUserJwtFromSecurityContext() {
        return (UserJwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
