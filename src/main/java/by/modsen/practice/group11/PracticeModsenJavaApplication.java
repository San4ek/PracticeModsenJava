package by.modsen.practice.group11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PracticeModsenJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeModsenJavaApplication.class, args);
    }

}
