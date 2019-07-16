package com.turkcell.playcell.gamingplatform.som.job;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.turkcell.playcell.gamingplatform.som.config.SomApplicationProperties;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ScheduledJobConfig {
	
	private final SomApplicationProperties somApplicationProperties;
	
	@Bean
	public ThreadPoolTaskExecutor customerProvisionApiThreadPoolTaskExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(somApplicationProperties.getCorePoolSize());
		threadPoolTaskExecutor.setMaxPoolSize(somApplicationProperties.getMaxPoolSize());
		threadPoolTaskExecutor.setQueueCapacity(Integer.MAX_VALUE);
		threadPoolTaskExecutor.setThreadNamePrefix("customer-provision-");
		return threadPoolTaskExecutor;
	}

}
