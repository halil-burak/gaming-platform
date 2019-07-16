package com.turkcell.playcell.gamingplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration
@EnableScheduling
@SpringBootApplication
public class SpringBootAppStarter {  
	
    public static void main(String[] args) {
    	SpringApplication.run(SpringBootAppStarter.class, args);
    }
	
} 