package com.turkcell.playcell.gamingplatform.common.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
@Profile("redis-sentinel")
public class RedisSentinelConfig extends CachingConfigurerSupport {
	
	@Autowired
	private CommonApplicationProperties applicationProperties;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
    	
		RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration(applicationProperties.getSentinelMaster(), 
				applicationProperties.getSentinelNodes());
		
		sentinelConfig.setPassword(applicationProperties.getRedisSentinelPassword());

		return new LettuceConnectionFactory(sentinelConfig);
    }
    
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration(applicationProperties.getSentinelMaster(), 
				applicationProperties.getSentinelNodes());

		sentinelConfig.setPassword(applicationProperties.getRedisSentinelPassword());

		return new JedisConnectionFactory(sentinelConfig);
	}
    
    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
    	
    	RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
    			.entryTtl(Duration.ofSeconds(applicationProperties.getCacheExpirationTime()));	
 	
    	return cacheConfig;
    }
    
    @Bean
    public RedisCacheManager cacheManager() {
 	
    	RedisCacheManager rcm = RedisCacheManager.builder(redisConnectionFactory())
    			.cacheDefaults(cacheConfiguration())
    			.transactionAware()
    			.build();
 	
    	return rcm;
    }
    
}
