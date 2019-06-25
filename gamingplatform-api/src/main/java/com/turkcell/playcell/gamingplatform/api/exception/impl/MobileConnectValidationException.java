package com.turkcell.playcell.gamingplatform.api.exception.impl;

import com.turkcell.playcell.gamingplatform.api.exception.BaseRuntimeException;
import com.turkcell.playcell.gamingplatform.api.exception.error.imp.ApiErrorCode;

public class MobileConnectValidationException extends BaseRuntimeException {
	
	private static final long serialVersionUID = -5036151966102404707L;

	public MobileConnectValidationException() {
		super(ApiErrorCode.MOBILE_CONNECT_VALIDATION_ERROR);
	}

	public MobileConnectValidationException(Throwable t) {
		super(ApiErrorCode.MOBILE_CONNECT_VALIDATION_ERROR, t);
	}
}
