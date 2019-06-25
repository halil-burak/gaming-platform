package com.turkcell.playcell.gamingplatform.mobileconnect.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class MobConApplicationProperties {
	
	@Value("${login.service.mobileConnect.clientId}")
	private String clientId;

	@Value("${login.service.mobileConnect.clientSecret}")
	private String clientSecret;

	@Value("${login.service.mobileConnect.urlForToken}")
	private String tokenUrl;

	@Value("${login.service.mobileConnect.urlForUserInfo}")
	private String userInfoUrl;

}
