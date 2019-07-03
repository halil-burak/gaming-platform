package com.turkcell.playcell.gamingplatform.som.config;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class SomApplicationProperties {
	
	@Value("${som.client.username}")
	private String username;

	@Value("${som.client.applicationId}")
	private BigInteger applicationId;

	@Value("${som.client.createOrder.uri}")
	private String uri;
	
	@Value("${custom.server.location}")
	private String serverLocation;
	
	@Value("${som.client.isturkcell.uri}")
	private String isTurkcellUri;

}
