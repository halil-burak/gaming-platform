package com.turkcell.playcell.gamingplatform.api.controller;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.playcell.gamingplatform.api.config.ApplicationProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BaseController {
	
	@Autowired
	private ApplicationProperties applicationProperties;
    
	protected String getPhoneNumber(HttpServletRequest request) {
		return Optional.ofNullable(request.getHeader("turkcell-playcell-msisdn")).orElse(null);
	}
	
	protected boolean checkMsisdnFormat(String msisdn) {
		
		if (msisdn != null) {
			if (msisdn.matches("[0-9]+")) {
				if (msisdn.length() == 10) {
					log.info("MSISDN Format Check is successfully done.");
					return true;
				}
			}
		}

		return false;
	}
	
	protected String checkAcceptLanguageHeader(HttpServletRequest request) {
		
        String acceptLang = request.getHeader("Accept-Language");
        
        log.info("checkAcceptLanguageHeader : " + acceptLang);

        if (acceptLang != null) {
        	
        	if (acceptLang.contains(";")) {
        		String [] authTokenParts = acceptLang.split(";");	
        		acceptLang = authTokenParts[0];
        	}

        	if (acceptLang.contains("-")) {
        		return acceptLang.substring(0, acceptLang.indexOf("-"));
        	} else {
        		return acceptLang;
        	}
        }

        return applicationProperties.getLocaleLanguage().toLanguageTag();
	}
	
	protected boolean checkAuthorizationHeader(HttpServletRequest request) {
		
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Basic ")) {
        
        	String [] authTokenParts = authHeader.split(" ");
	    	if (authTokenParts[1].compareTo(applicationProperties.getAuthKey()) == 0) {
	    		log.info("Basic Authorization is successfully done.");
	    		return true;
	    	}
        }

        return false;
	}
    
	protected String getAuthorizationHeader(HttpServletRequest request) {
		
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
        
        	String [] authTokenParts = authHeader.split(" ");
	    	return authTokenParts[1];
        }

        return null;
	}
    
    protected String extractLanguageCookie(HttpServletRequest request) {
    	
        Cookie[] cookies = request.getCookies();
        
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("language"))
                    return c.getValue();
            }
        }
        
        return applicationProperties.getLocaleLanguage().toLanguageTag();
    }
}
