package com.turkcell.playcell.gamingplatform.som.model;

import org.springframework.ws.client.WebServiceClientException;

public class SomServiceException extends WebServiceClientException  {

	private static final long serialVersionUID = 1026106248977462687L;

	public SomServiceException(String msg) {
        super(msg);
    }

    public SomServiceException(String msg, Throwable ex) {
        super(msg, ex);
    }

}
