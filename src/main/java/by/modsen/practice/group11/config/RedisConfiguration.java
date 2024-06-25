package by.modsen.practice.group11.config;

import by.modsen.practice.group11.model.entity.AccessToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, AccessToken> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        final RedisTemplate<String, AccessToken> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new GenericToStringSerializer<>(AccessToken.class));
        return template;
    }
}
