package com.turkcell.playcell.platform.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.turkcell.playcell.platform.model.MobileConnectRequstBody;


public class MobconTokenValidate {
		
	HttpHeaders headers = new HttpHeaders();
	
	public Boolean validateClient(String msisdn, String accessToken) {
		
		try {
			
			if (this.validateToken(msisdn, accessToken)) {
				return true;
			}
							
			return false;
		} catch (Exception ex) {
			// Do nothing here
			return false;
		}
	}

	private Boolean validateToken (String msisdn, String accessToken) throws IOException {
		
		this.addHeader();
		
		RestTemplate restTemplate = new RestTemplate();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.createObjectNode();

		MobileConnectRequstBody reqBody = new MobileConnectRequstBody(msisdn, accessToken);
		
		ObjectNode reqheadernode = ((ObjectNode) rootNode).putObject("requestHeader");
		reqheadernode.put("transactionId", reqBody.getTransactionId());
		reqheadernode.put("application", reqBody.getApplication());
		reqheadernode.put("userId", reqBody.getUserId());
		reqheadernode.put("operationName", reqBody.getOperationName());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String DNow = format.format(new Date());
		reqheadernode.put("transactionDate", DNow);
		
		((ObjectNode) rootNode).put("msisdn", reqBody.Msisdn);
		((ObjectNode) rootNode).put("accessToken", reqBody.AccessToken);		
		
		String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
		
	    HttpEntity<String> requestEntity = new HttpEntity<>(jsonString, headers);
	    
	    ResponseEntity<String> responseEntity = restTemplate.exchange(
	    		"https://mobcon.turkcell.com.tr/mobileconnect/services/accessTokenValidation",
	            HttpMethod.POST,
	            requestEntity,
	            String.class);
	    
	    HttpStatus statusCode = responseEntity.getStatusCode();
	    
	    if (statusCode == HttpStatus.OK) {
	    	
	        String response = responseEntity.getBody();
	        
	        JsonNode rootResponseNode = mapper.readTree(response).path("accessTokenValid");
	        
	        if (!rootResponseNode.isNull()) {
	        	if (rootResponseNode.asInt() == 0) {
	        		return true;
	        	}
	        }
	    }
	    
		return false;
	}
	
	private void addHeader () {

		headers.setBasicAuth("playcell", "playcell1234", StandardCharsets.UTF_8);
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

	}
}
