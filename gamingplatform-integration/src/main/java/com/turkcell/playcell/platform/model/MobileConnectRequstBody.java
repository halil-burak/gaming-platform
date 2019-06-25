package com.turkcell.playcell.platform.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MobileConnectRequstBody {
	
	@JsonProperty("msisdn")
	public String Msisdn = "";
	
	@JsonProperty("accessToken")
	public String AccessToken = "";
	
	@JsonProperty("requestHeader")
	public MobConInfo requestHeader = new MobConInfo();
	
	public MobileConnectRequstBody(String msisdn, String AccessToken) {
		this.Msisdn = msisdn;
		this.AccessToken = AccessToken;
	}
	
	public void setAccessToken (String token) {
		this.AccessToken = token;
	}
	
	public String getAccessToken () {
		return this.AccessToken;
	}
	
	public void setTransactionDate (String Date) {
		this.requestHeader.transactionDate = Date;
	}
	
	public String getTransactionId() {
		return this.requestHeader.transactionId;
	}
	
	public String getApplication() {
		return this.requestHeader.application;
	}
	
	public String getUserId() {
		return this.requestHeader.userId;
	}
	
	public String getOperationName() {
		return this.requestHeader.operationName;
	}
}

class MobConInfo {
	
	//@Autowired
    //private ApplicationProperties applicationProperties = new ApplicationProperties();
	
	//@Autowired
	//private Environment env;
	
	//@JsonProperty("transactionId")
	//public String transactionId = applicationProperties.turkcelltokenvalidationtransactionid();
	@JsonProperty("transactionId")
	//public String transactionId = new String(applicationProperties.turkcelltokenvalidationtransactionid());
	public String transactionId = new String("20173737372222");
	@JsonProperty("application")
	//public String application = new String(applicationProperties.turkcelltokenvalidationapplicationid());
	public String application = new String("app");
	@JsonProperty("userId")
	//public String userId = new String(applicationProperties.turkcelltokenvalidationuserid());
	public String userId = new String("user");
	@JsonProperty("operationName")
	//public String operationName = new String(applicationProperties.turkcelltokenvalidationoperationname());
	public String operationName = new String("consentManagement");
	@JsonProperty("transactionDate")
	public String transactionDate = "";
	
}
