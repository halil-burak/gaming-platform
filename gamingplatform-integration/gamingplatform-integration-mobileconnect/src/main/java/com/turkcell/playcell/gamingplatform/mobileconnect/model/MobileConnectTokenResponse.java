package com.turkcell.playcell.gamingplatform.mobileconnect.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MobileConnectTokenResponse {
	
	private String access_token;
	private String token_type;
	private String expires_in;
	private String id_token;
	private String error;
	private String error_description;

}
