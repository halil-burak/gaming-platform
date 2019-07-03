package com.turkcell.playcell.gamingplatform;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.micrometer.core.instrument.MeterRegistry;


@SpringBootApplication
@EnableAutoConfiguration
public class SpringBootAppStarter {  
    public static void main(String[] args) {
    	SpringApplication.run(SpringBootAppStarter.class, args);
    }
    
	@Bean
	MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
		return registry -> registry.config().commonTags("application", "gamingplatform-integration-som");
	}
} 