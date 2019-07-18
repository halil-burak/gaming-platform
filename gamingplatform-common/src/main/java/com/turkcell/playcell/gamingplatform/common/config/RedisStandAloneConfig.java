package com.turkcell.playcell.gamingplatform.common.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@EnableCaching
@Configuration
@Profile("redis-standalone")
public class RedisStandAloneConfig extends CachingConfigurerSupport {
	
	@Autowired
	private CommonApplicationProperties applicationProperties;
	
	@Bean
    public LettuceConnectionFactory redisConnectionFactory() {

    	RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
    	
    	redisConf.setHostName(applicationProperties.getRedisHost());
    	redisConf.setPort(applicationProperties.getRedisPort());
    	redisConf.setPassword(RedisPassword.of(applicationProperties.getRedisStandAlonePassword()));
        
    	return new LettuceConnectionFactory(redisConf);
	}
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(applicationProperties.getRedisHost(), 
				applicationProperties.getRedisPort());
		redisStandaloneConfiguration.setPassword(applicationProperties.getRedisStandAlonePassword());

		return new JedisConnectionFactory(redisStandaloneConfiguration);
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
