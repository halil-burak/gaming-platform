package com.turkcell.playcell.gamingplatform.api.exception.error;

import org.springframework.http.HttpStatus;

import com.turkcell.playcell.gamingplatform.api.enumtypes.ResponseCodeStrings;

public interface ErrorCode {
	ResponseCodeStrings code();
    HttpStatus httpStatus();
}
