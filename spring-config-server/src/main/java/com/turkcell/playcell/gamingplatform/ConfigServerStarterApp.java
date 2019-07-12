package com.turkcell.playcell.gamingplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerStarterApp
{
	public static void main(String[] args) {
    	SpringApplication.run(ConfigServerStarterApp.class, args);
    }
}
