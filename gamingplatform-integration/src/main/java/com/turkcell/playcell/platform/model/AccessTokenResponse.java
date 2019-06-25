package com.turkcell.playcell.platform.model;

public class AccessTokenResponse extends BaseResponse {

	public bodyClass body = new bodyClass();

	public AccessTokenResponse () {
	}
	
	public void setAccessToken(String accessToken) {
		this.body.token = accessToken;
	}
	
	public void setMsisdn(String msisdn) {
		this.body.msisdn = msisdn;
	}
	
}

class bodyClass {
	public String token;
	public String msisdn;
}
