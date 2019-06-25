package com.turkcell.playcell.platform.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.turkcell.playcell.platform.enumtypes.ResponseCodeStrings;
import com.turkcell.playcell.platform.model.AccessTokenResponse;
import com.turkcell.playcell.platform.model.BaseResponse;
import com.turkcell.playcell.platform.model.SuccessResponse;

@RestController
public class BaseController {
    
	@GetMapping("")
    public String home() {
        return "Mobcon playcell gateway";
    }
	
	public BaseResponse prepareErrorResponse (String message, ResponseCodeStrings returnCode) {
		
		BaseResponse response = new BaseResponse ();
		
		response.setSuccess(false);
		response.setMessage(message);
		response.setReturnCode(returnCode);
		
		return response;
	}
	
	public SuccessResponse prepareSuccessResponse (Map<String, String> map, String message, ResponseCodeStrings successCode) throws JsonProcessingException {
		
		SuccessResponse response = new SuccessResponse (map);
		
		response.setSuccess(true);
		response.setMessage(message);
		response.setReturnCode(successCode);
		
		return response;
	}
	
	public AccessTokenResponse prepareSuccessTokenResponse (String token, String msisdn, String message) {
		
		AccessTokenResponse response = new AccessTokenResponse();
		
		response.setAccessToken(token);
		response.setMsisdn(msisdn);
		response.setSuccess(true);
		response.setMessage(message);
		response.setReturnCode(ResponseCodeStrings.TOKEN_SUCCESS);
		
		return response;
	}
	
}
