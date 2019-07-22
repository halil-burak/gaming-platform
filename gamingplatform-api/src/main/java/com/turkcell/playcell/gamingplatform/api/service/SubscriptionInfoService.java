package com.turkcell.playcell.gamingplatform.api.service;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.playcell.gamingplatform.api.config.ApplicationProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionInfoService {
	
	private final ApplicationProperties applicationProperties;
	
    private void setTimeout(RestTemplate restTemplate, int timeout) {
        restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());
        
        SimpleClientHttpRequestFactory rf = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
        rf.setReadTimeout(timeout);
        rf.setConnectTimeout(timeout);
    }
	
	public String getSubscriptionInfo(String phoneNumber, String token) {
		return requestSubscriptionInfo(phoneNumber,token);
	}
	
	private String requestSubscriptionInfo(String phoneNumber, String token) {
		
		try {
			
			if (ObjectUtils.isEmpty(phoneNumber)) {
				log.error("requestSubscriptionInfo: phone number field is empty !!");
				return null;
			}
			
			if (ObjectUtils.isEmpty(token)) {
				log.error("requestSubscriptionInfo: token field is empty !!");
				return null;
			}
			
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	        httpHeaders.setAccessControlAllowOrigin("playcell.turkcell.com.tr");
	        httpHeaders.set("turkcell-playcell-msisdn", phoneNumber);
	        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	        httpHeaders.setBearerAuth(token);
	        
	        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
	        
	        RestTemplate restTemplate = new RestTemplate();
	        setTimeout(restTemplate, 5000);
	        
	        ResponseEntity<String> responseEntity = restTemplate.exchange(applicationProperties.getSubscriptionInfoUrl() + phoneNumber, HttpMethod.GET, requestEntity, String.class);
	        
	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode data = mapper.readTree(responseEntity.getBody());
	        
	        if (data.get("data") != null) { 
	        	String userTariff = data.get("data").get("data").get("SubscriptionType").asText();
	        
	        	if(userTariff != null && userTariff.equals("None")) {
	        		log.info("requestSubscriptionInfo: Package Information is None...");
	        		userTariff = null;
	        	}
	        
	        	log.info("requestSubscriptionInfo: Package Information is " + userTariff);
	        	return userTariff;
	        }
	        return null;
	        
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return null;
		}		
	}
}
