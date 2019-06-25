package com.turkcell.playcell.gamingplatform.api.exception;

import com.turkcell.playcell.gamingplatform.api.exception.error.ErrorCode;

import lombok.Getter;

@Getter
public abstract class BaseRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 7798188493246895976L;

    private final transient ErrorCode errorCode;
    private Object[] params;

    public BaseRuntimeException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public BaseRuntimeException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }


    public BaseRuntimeException(ErrorCode errorCode, Throwable t) {
        super(t);
        this.errorCode = errorCode;
    }

    public BaseRuntimeException(ErrorCode errorCode, Object... params) {
        this.errorCode = errorCode;
        this.params = params;
    }

    /*public BaseRuntimeException(ErrorCode errorCode, String message, Object... params) {
        this.errorCode = errorCode;
        this.message = message;
        this.params = params;
        if (params != null && params.length > 0) {
            this.message = String.format(message, (Object[]) params);
        }
    }
    
    public BaseRuntimeException(String message, ErrorCode errorCode, Object... params) {
    	this(errorCode, message, params);
    }*/
    
}
