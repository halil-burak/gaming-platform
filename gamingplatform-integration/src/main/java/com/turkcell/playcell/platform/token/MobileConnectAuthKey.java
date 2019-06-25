package com.turkcell.playcell.platform.token;

import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MobileConnectAuthKey {
	
	private String accessCode = null;
	
	HttpHeaders headers = new HttpHeaders();
	
	private String access_token = null;
	private String redirectURL = null;
	
	public static String mobconKey;
	
	public MobileConnectAuthKey(String code, String redirectURL) {
		
		this.accessCode = code;
		this.redirectURL = redirectURL;
		
	}
	
	private void prepareTokenHeaders () {
		
		headers.setBasicAuth("playcell", mobconKey, StandardCharsets.UTF_8);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setAccessControlAllowOrigin("mobcon.turkcell.com.tr");
		
	}
	
	private Boolean prepareAuthRequestandSend () {
		
		try {
			
			this.prepareTokenHeaders();
			
			RestTemplate restTemplate = new RestTemplate();
			
			MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<String, String>();
			bodyMap.add("grant_type", "authorization_code");
			bodyMap.add("code", this.accessCode);
			bodyMap.add("redirect_uri", this.redirectURL);
			
		    HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(bodyMap, headers);
		    
		    ResponseEntity<String> responseEntity = restTemplate.exchange(
		    		"https://mobcon.turkcell.com.tr/mobileconnect/oauth/token",
		            HttpMethod.POST,
		            requestEntity,
		            String.class);
		    
		    HttpStatus statusCode = responseEntity.getStatusCode();
		    
		    if (statusCode == HttpStatus.OK) {
		    	
		        String response = responseEntity.getBody();
		        
		        ObjectMapper mapper = new ObjectMapper();
		        JsonNode rootResponseNode = mapper.readTree(response).path("access_token");
		        
		        if (!rootResponseNode.isNull()) {
		        	this.access_token = rootResponseNode.asText();
		        	return true;	        	
		        }
		    }
		    
		} catch (Exception ex) {
			// Do nothing here
			return false;
		}

		return false;
	}
	
	public String getAccessToken () {
		this.prepareAuthRequestandSend();
		return this.access_token;
	}

}
