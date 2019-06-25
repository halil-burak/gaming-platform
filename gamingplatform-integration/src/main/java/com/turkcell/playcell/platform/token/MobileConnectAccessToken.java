package com.turkcell.playcell.platform.token;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MobileConnectAccessToken {
	
	private String authKey = null;
	
	HttpHeaders headers = new HttpHeaders();
	
	public MobileConnectAccessToken (String authKey) {
		this.authKey = authKey;
	}
	
	private void prepareUserInfoHeaders () {
		
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setAccessControlAllowOrigin("mobcon.turkcell.com.tr");
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		if (this.authKey != null) {
			headers.setBearerAuth(this.authKey);
		}
		
	}
	
	private String prepareUserInfoandSend () {
		
		try {
			
			this.prepareUserInfoHeaders();
			
			RestTemplate restTemplate = new RestTemplate();
			
		    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		    
		    ResponseEntity<String> responseEntity = restTemplate.exchange(
		    		"https://mobcon.turkcell.com.tr/mobileconnect/userinfo",
		            HttpMethod.GET,
		            requestEntity,
		            String.class);
		    
		    HttpStatus statusCode = responseEntity.getStatusCode();
		    
		    if (statusCode == HttpStatus.OK) {
		    	
		        String response = responseEntity.getBody();
		        
		        ObjectMapper mapper = new ObjectMapper();  
		        JsonNode rootResponseNode = mapper.readTree(response).path("phone_number_verified");
		        
		        if (!rootResponseNode.isNull()) {
		        	if (rootResponseNode.asText().compareTo("true") == 0) {
		        		JsonNode phoneNumberNode = mapper.readTree(response).path("phone_number");
			        	return phoneNumberNode.asText();
		        	}
		        }
		    }
		    
		} catch (Exception ex) {
			// Do nothing here
			return null;
		}
		
		return null;
	}
	
	public String validateUserInfo () {
		return prepareUserInfoandSend();
	}

}
