package com.turkcell.playcell.gamingplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.turkcell.playcell.gamingplatform.api.controller.GameListController;
import com.turkcell.playcell.gamingplatform.api.controller.UserApiController;

import io.micrometer.core.instrument.MeterRegistry;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableAutoConfiguration
@EnableCaching
@EnableSwagger2
public class SpringBootAppStarter {  
    public static void main(String[] args) {
    	SpringApplication.run(SpringBootAppStarter.class, args);
    }
    
	@Bean
	MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
		return registry -> registry.config().commonTags("application", "gamingplatform-api");
	}
} 