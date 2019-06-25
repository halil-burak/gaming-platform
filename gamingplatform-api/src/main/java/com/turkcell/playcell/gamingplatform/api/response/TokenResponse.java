package com.turkcell.playcell.gamingplatform.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class TokenResponse {
	
	private String token;
	private String msisdn;
	   
}
