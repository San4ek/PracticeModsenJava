package by.modsen.practice.group11.model.entity;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Component;

@Component
@RedisHash()
public class AccessToken {

    private String token;

    private boolean isValid;

}
