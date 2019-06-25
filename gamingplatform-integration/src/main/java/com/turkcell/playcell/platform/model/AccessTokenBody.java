package com.turkcell.playcell.platform.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class AccessTokenBody {
	
	private String redirectURL = new String();
	private String accessCode = new String();

	// for deserialisation
	public AccessTokenBody(){			
	}

	@JsonCreator
	public AccessTokenBody(@JsonProperty("accesscode") String code, @JsonProperty("redirecturl")String url) {
		this.setRedirectURL(url);
		this.setAccessCode(code);
	}

	public String getRedirectURL() {
		return redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
}
