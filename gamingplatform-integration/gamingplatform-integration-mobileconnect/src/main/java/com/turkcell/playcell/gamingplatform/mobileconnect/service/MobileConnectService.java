package com.turkcell.playcell.gamingplatform.mobileconnect.service;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.playcell.gamingplatform.mobileconnect.config.MobConApplicationProperties;
import com.turkcell.playcell.gamingplatform.mobileconnect.model.MobileConnectTokenResponse;
import com.turkcell.playcell.gamingplatform.mobileconnect.model.MobileConnectUserInfoResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MobileConnectService {
	
	@Autowired
	private MobConApplicationProperties applicationProperties;

	public MobileConnectUserInfoResponse authenticateMobileConnect(String code, String redirectUrl) {
		
		MobileConnectTokenResponse mobileConnectTokenResponse = requestAccessToken(code, redirectUrl);
		
		if (!ObjectUtils.isEmpty(mobileConnectTokenResponse.getError())) {
			return new MobileConnectUserInfoResponse().setError_message(mobileConnectTokenResponse.getError());
		}
		
		MobileConnectUserInfoResponse userInfoResponse = requestUserInfo(mobileConnectTokenResponse.getAccess_token(), mobileConnectTokenResponse.getToken_type());
		
		log.info("authenticateMobileConnect->mobile connect user info response phone number:{}", userInfoResponse.getPhone_number());
		
		return userInfoResponse;
	}

	private MobileConnectTokenResponse requestAccessToken(String code, String redirectUrl) {
		
		ResponseEntity<String> response = null;
		
		try {
			
			if (ObjectUtils.isEmpty(code)) 
			{
				return new MobileConnectTokenResponse().setError("requestAccessToken-> authcode is not Valid!");
			}
			
			if (ObjectUtils.isEmpty(redirectUrl)) 
			{
				return new MobileConnectTokenResponse().setError("requestAccessToken-> redirectUrl is not Valid!");
			}

			if (!checkParameter()) 
			{
				return new MobileConnectTokenResponse().setError("requestAccessToken-> Fast-Login Configuration is not Valid Or Empty!");
			}

			HttpHeaders headers = new HttpHeaders();
			//headers.set(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString((applicationProperties.getClientId() + ":" + applicationProperties.getClientSecret()).getBytes(StandardCharsets.UTF_8)));
			headers.setBasicAuth(applicationProperties.getClientId(), applicationProperties.getClientSecret(), StandardCharsets.UTF_8);
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.setAccessControlAllowOrigin("mobcon.turkcell.com.tr");
			
			MultiValueMap<String, String> formPostData = new LinkedMultiValueMap<String, String>() {{
				add("grant_type", "authorization_code");
				add("code", code);
				add("redirect_uri", redirectUrl);
			}};
			
			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formPostData, headers);
			
			RestTemplate restTemplate = new RestTemplate();
			
			response = restTemplate.exchange(
					applicationProperties.getTokenUrl(),
		            HttpMethod.POST,
		            requestEntity,
		            String.class);

			//response = new RestTemplate().postForEntity(applicationProperties.getTokenUrl(), new HttpEntity<>(formPostData, headers), String.class);
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			MobileConnectTokenResponse mobileConnectTokenResponse = mapper.readValue(response.getBody(), MobileConnectTokenResponse.class);

			log.debug("requestAccessToken-> response.getStatusCode:{} mcTokenResponse.getError:{}", response.getStatusCode(), mobileConnectTokenResponse.getError());
			
			if (response.getStatusCode() != HttpStatus.OK || mobileConnectTokenResponse.getError() != null) 
			{
				log.error("requestAccessToken->MobileConnectResponse response={}, error={}, description={}", response, mobileConnectTokenResponse.getError(), mobileConnectTokenResponse.getError_description());
				return mobileConnectTokenResponse.setError("requestAccessToken-> TokenGet is not valid. " + mobileConnectTokenResponse.getError_description() +
						" access token request error status code:" + response.getStatusCode());
			}
			
			if (ObjectUtils.isEmpty(mobileConnectTokenResponse.getAccess_token())) {
				log.error("requestAccessToken->Unknown message type message:{}", response);
				return mobileConnectTokenResponse.setError("requestAccessToken-> TokenGet is not valid. Communicate with MC.");
			}
			
			return mobileConnectTokenResponse;

		} catch (HttpClientErrorException ex) {
			return new MobileConnectTokenResponse().setError("requestAccessToken->Bad Request or timeout code  Response=" + response + ", response body=" +
					ex.getResponseBodyAsString() + ", errorMessage=" + ex.getMessage());

		} catch (Exception ex) {
			String errorMEssage = "requestAccessToken->Bad Request or timeout code PhoneNumber Response=" + response + ", errorMessage=" + ex.getMessage();
			return new MobileConnectTokenResponse().setError(errorMEssage);
		}
	}

	private boolean checkParameter() {
		
		if (ObjectUtils.isEmpty(applicationProperties.getClientId()) || ObjectUtils.isEmpty(applicationProperties.getClientSecret()) || ObjectUtils.isEmpty(applicationProperties.getTokenUrl()) || ObjectUtils.isEmpty(applicationProperties.getUserInfoUrl())) {
			return false;
		}
		
		return true;
	}

	private MobileConnectUserInfoResponse requestUserInfo(String accessToken, String tokenType) {
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			
			headers.set(HttpHeaders.AUTHORIZATION, tokenType + " " + accessToken);
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			
			ResponseEntity<String> response = new RestTemplate().exchange(applicationProperties.getUserInfoUrl(), HttpMethod.GET, new HttpEntity<>(headers), String.class);

			if (!response.getStatusCode().is2xxSuccessful()) {
				log.error("requestUserInfo-> mobile connect userInfo error status:{} body:{} ", response.getStatusCode(), response.getBody());
				return new MobileConnectUserInfoResponse().setError_message("requestUserInfo-> MC userInfo error status:" + response.getStatusCode() + " body:" + response.getBody());
			}
			
			if (response.getBody() == null) {
				log.error("requestUserInfo-> response body is null ");
				return new MobileConnectUserInfoResponse().setError_message("requestUserInfo-> MC response body is null ");
			}

			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(response.getBody(), MobileConnectUserInfoResponse.class);
			
		} catch (Exception ex) {
			log.error("requestUserInfo->  request error:\n" + ex.getLocalizedMessage());
			return new MobileConnectUserInfoResponse().setError_message("requestUserInfo-> UserInfo  request error:\n" + ex.getLocalizedMessage());
		}
	}

	public String addingRegionCodeToNumber(String phoneNumber) {
		
		if (phoneNumber.length() <= 10) {
			return "+90" + phoneNumber;
		}
		
		return phoneNumber;
	}
}
