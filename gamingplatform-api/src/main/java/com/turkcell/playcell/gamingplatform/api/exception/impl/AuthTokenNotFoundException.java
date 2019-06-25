package com.turkcell.playcell.gamingplatform.api.exception.impl;

import com.turkcell.playcell.gamingplatform.api.exception.BaseRuntimeException;
import com.turkcell.playcell.gamingplatform.api.exception.error.imp.ApiErrorCode;

public class AuthTokenNotFoundException extends BaseRuntimeException {
    public AuthTokenNotFoundException() {
        super(ApiErrorCode.AUTH_TOKEN_NOT_FOUND);
    }
}
