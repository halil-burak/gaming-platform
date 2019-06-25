package com.turkcell.playcell.platform.model;

import com.turkcell.playcell.platform.enumtypes.ResponseCodeStrings;

public class BaseResponse {
	
	public Meta meta = new Meta();
	
	// Meta Information Fields
	public void setMessage(String message) {
		this.meta.message = message;
	}

	public void setSuccess(Boolean status) {
		this.meta.success = status;
	}
	
	public void setReturnCode(ResponseCodeStrings code) {
		this.meta.returnCode = code;
	}
	
}

class Meta {
	public Boolean success;
	public ResponseCodeStrings returnCode;
	public String message;
}
