package com.turkcell.playcell.gamingplatform.som.model;

import com.turkcell.playcell.gamingplatform.som.enumtypes.SomActionType;

public class SomCreateOrderRequest extends WsLog {
	
    private String offerId;

    private String transactionId;

    private String msisdn;

    private SomActionType somActionType;

	@Override
	public String getLoggable() {
		return this.toString();
	}

	@Override
	public String getTransactionId() {
		// TODO Auto-generated method stub
		return null;
	}

}
