package com.turkcell.playcell.gamingplatform.som.model;

import java.math.BigInteger;

public class SomCreateOrderResponse extends WsLog {
	
    private BigInteger orderId;

    private BigInteger productId;

    private Integer action; // TODO --- make enum

    private Boolean _continue;

    @Override
    public String getLoggable() {
        return this.toString();
    }

    @Override
    public String getTransactionId() {
        return null;
    }

}
