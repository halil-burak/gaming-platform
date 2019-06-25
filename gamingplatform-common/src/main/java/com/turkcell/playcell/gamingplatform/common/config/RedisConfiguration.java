package com.turkcell.playcell.gamingplatform.common.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@EnableCaching
@Configuration
//@PropertySource("classpath:application-gamingplatform-api.properties")
public class RedisConfiguration extends CachingConfigurerSupport {
	
	@Autowired
	private commonApplicationProperties applicationProperties;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
    	
    	/*RedisSentinelConfiguration redisSentConf = new RedisSentinelConfiguration();
    	
    	redisSentConf.master("master-redis")
		.sentinel(System.getProperty("redis.sentinel.node.1.host"), Integer.parseInt(System.getProperty("redis.sentinel.node.1.port")))
		.sentinel(System.getProperty("redis.sentinel.node.2.host"), Integer.parseInt(System.getProperty("redis.sentinel.node.2.port")))
		.sentinel(System.getProperty("redis.sentinel.node.3.host"), Integer.parseInt(System.getProperty("redis.sentinel.node.3.port")))
		.setPassword(RedisPassword.of(password));*/
    	
    	RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
    	
    	redisConf.setHostName(applicationProperties.getRedisHost());
    	redisConf.setPort(applicationProperties.getRedisPort());
    	redisConf.setPassword(RedisPassword.of(applicationProperties.getRedisPassword()));
        
    	return new LettuceConnectionFactory(redisConf);
    }
    
    /*@Bean
    LettucePoolingClientConfiguration lettucePoolConfig(ClientOptions options, ClientResources dcr){
        return LettucePoolingClientConfiguration.builder()
                .poolConfig(new GenericObjectPoolConfig())
                .clientOptions(options)
                .clientResources(dcr)
                .build();
    }*/
    
    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
    	
    	RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
    			.entryTtl(Duration.ofSeconds(applicationProperties.getCacheExpirationTime()))
    			.disableCachingNullValues();	
 	
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
