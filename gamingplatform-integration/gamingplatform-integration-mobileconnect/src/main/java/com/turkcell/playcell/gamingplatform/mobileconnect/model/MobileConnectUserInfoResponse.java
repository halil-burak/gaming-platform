package com.turkcell.playcell.gamingplatform.mobileconnect.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MobileConnectUserInfoResponse {
	
	private String phone_number;
	private Boolean phone_number_verified;
	private String email;
	private String error_message;

}
