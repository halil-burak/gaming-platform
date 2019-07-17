package com.turkcell.playcell.gamingplatform.common.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class CommonApplicationProperties {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Integer redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;
    
    @Value("${spring.redis.sentinel.master}")
	String sentinelMaster;
    
	@Value(value = "${spring.redis.sentinel.nodes}")
	Set<String> sentinelNodes;
    
    @Value("${cache.expirations.time}")
    private Integer cacheExpirationTime;
}
