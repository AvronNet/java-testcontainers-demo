package com.avronnet.listings.configuration;

import io.lettuce.core.RedisURI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.url:#{null}}")
    private String redisUrl;

    Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Bean
    LettuceConnectionFactory lettuceConnectionFactory() {
        if (redisUrl == null) {
            var redisConfig = new RedisStandaloneConfiguration();
            redisConfig.setHostName("localhost");
            redisConfig.setPort(6379);
            redisConfig.setPassword("eYVX7EwVmmxKPCDmwMtyKVge8oLd2t81");
            return new LettuceConnectionFactory(redisConfig);
        }

        logger.info("Redis URL: " + redisUrl);
        var redisUri = RedisURI.create(redisUrl);
        return new LettuceConnectionFactory(LettuceConnectionFactory.createRedisConfiguration(redisUri));
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory());
        return template;
    }
}
