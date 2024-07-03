package by.modsen.practice.group11.config;

import by.modsen.practice.group11.model.entity.PersonalInfo;
import by.modsen.practice.group11.model.entity.User;
import by.modsen.practice.group11.model.enums.Gender;
import by.modsen.practice.group11.model.enums.Role;
import by.modsen.practice.group11.repository.PersonalInfoRepository;
import by.modsen.practice.group11.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class InitConfiguration {
    private final UserRepository userRepository;
    private final PersonalInfoRepository personalInfoRepository;
    private final PasswordEncoder passwordEncoder;


    public void init() {
        log.info(passwordEncoder.encode("12345678"));
        List<User> admins = userRepository.findAllByRole(Role.ROLE_ADMIN);

        if (admins.isEmpty()) {

            PersonalInfo personalInfo = PersonalInfo.builder()
                    .birthday(LocalDate.parse("23-01-2002", DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                    .fullName("Admin Adminovich")
                    .gender(Gender.MALE)
                    .build();

            personalInfoRepository.save(personalInfo);

            userRepository.save(User.builder().
                    id(null).
                    email("admin@gmail.com").
                    login("admin").
                    password(passwordEncoder.encode("12345678")).
                    role(Role.ROLE_ADMIN).
                    personalInfo(personalInfo).
                    build());

        }
    }
}

