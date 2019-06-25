package com.turkcell.playcell.gamingplatform.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class commonApplicationProperties {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Integer redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;
    
    @Value("${cache.expirations.time}")
    private Integer cacheExpirationTime;

}
