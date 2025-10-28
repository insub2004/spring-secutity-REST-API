package nhn.academy.config.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> sessionRedisTemplate = new RedisTemplate<>();
        sessionRedisTemplate.setConnectionFactory(redisConnectionFactory);

        sessionRedisTemplate.setKeySerializer(new StringRedisSerializer());
        sessionRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        sessionRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //sessionRedisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        sessionRedisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));

        return sessionRedisTemplate;
    }


    // 기존 코드
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Object> sessionRedisTemplate = new RedisTemplate<>();
//        sessionRedisTemplate.setConnectionFactory(redisConnectionFactory);
//        sessionRedisTemplate.setKeySerializer(new StringRedisSerializer());
//        sessionRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//
//        sessionRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        sessionRedisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//        return sessionRedisTemplate;
//    }

}
