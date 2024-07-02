package by.modsen.practice.group11.config;

import by.modsen.practice.group11.repository.PersonalInfoRepository;
import by.modsen.practice.group11.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class InitConfiguration {
    private final UserRepository userRepository;
    private final PersonalInfoRepository personalInfoRepository;
    private final PasswordEncoder passwordEncoder;

    public void init() {

    }
}
