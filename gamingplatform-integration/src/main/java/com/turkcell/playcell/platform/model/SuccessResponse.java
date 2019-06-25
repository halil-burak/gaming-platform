package com.turkcell.playcell.platform.model;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SuccessResponse extends BaseResponse {
	
	private String body = new String();
	
	public SuccessResponse() {
		body = "";
	}
	
	public SuccessResponse(Map<String, String> map) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		body = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
	}
	
	public void setBody(String info) {
		body = info;
	}
	
	public String getBody() {
		return body;
	}

}
