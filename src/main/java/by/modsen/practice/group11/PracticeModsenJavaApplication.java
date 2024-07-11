package by.modsen.practice.group11;

import by.modsen.practice.group11.config.InitConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableCaching
@RequiredArgsConstructor
public class PracticeModsenJavaApplication {

    private final InitConfiguration initConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(PracticeModsenJavaApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        initConfiguration.init();
    }
}
