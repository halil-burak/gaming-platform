package com.turkcell.playcell.gamingplatform.api.config;

import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class ApplicationProperties {
	
	@Value("${perm.token}")
    private String permToken;
	
	@Value("${perm.token.expiry}")
    private String permTokenExpiry;

    @Value("${temp.token}")
    private String tempToken;
    
	@Value("${temp.token.expiry}")
    private String tempTokenExpiry;

    @Value("${auth.key}")
    private String authKey;

    @Value("${mobcon.key}")
    private String mobconKey;
    
    @Value("${cache.expirations.time}")
    private Integer cacheExpirationTime;
    
    @Value("${subscriptioninfo.url}")
    private String subscriptionInfoUrl;
    
    @Value("${default.locale.language}")
    private Locale localeLanguage;

    @Value("${cdn.private.key}")
    private String cdnPrivateKey;
    
    @Bean
    public ModelMapper modelMapper() {
       ModelMapper modelMapper = new ModelMapper();
       return modelMapper;
    }

}
