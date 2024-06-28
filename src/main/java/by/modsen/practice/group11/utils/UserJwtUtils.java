package by.modsen.practice.group11.utils;

import by.modsen.practice.group11.model.UserJwt;
import by.modsen.practice.group11.model.entity.User;
import by.modsen.practice.group11.model.enums.LinePattern;
import by.modsen.practice.group11.repository.UserRepository;
import by.modsen.practice.group11.service.exception.ResourceStateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserJwtUtils implements UserDetailsService {
    private final UserRepository userRepository;
    private final StringFormatMatcher stringFormatMatcher;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LinePattern linePattern = stringFormatMatcher.isLoginOrUsername(username);

        User user;

        if (linePattern == LinePattern.LOGIN) {
            user = userRepository.findByLogin(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with login: " + username));
        } else if (linePattern == LinePattern.EMAIL){
            user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        } else {
            throw new ResourceStateException(HttpStatus.CONFLICT.value() * 100 + 11, "Username credential is not recognized");
        }

        return UserJwt.build(user);
    }
}
