package com.zy.redisconfig;

import com.zy.entity.Students;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {
    @Bean
    @ConditionalOnMissingBean(name = "sRedisTemplate")
    public RedisTemplate<Object, String> sRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, String> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(new StringRedisSerializer());
        return template;
    }
    @Bean
    @ConditionalOnMissingBean(name = "studentsRedisTemplate")
    public RedisTemplate<Object, Students> studentsRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Students> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Students> js = new Jackson2JsonRedisSerializer<>(Students.class);
        template.setDefaultSerializer(js);
        return template;
    }
}
