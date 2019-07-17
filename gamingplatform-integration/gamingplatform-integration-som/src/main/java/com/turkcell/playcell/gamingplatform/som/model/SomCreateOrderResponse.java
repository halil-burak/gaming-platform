package com.turkcell.playcell.gamingplatform.som.model;

import java.math.BigInteger;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class SomCreateOrderResponse extends WsLog {
	
    private BigInteger orderId;

    private BigInteger productId;

    private Integer action;

    private Boolean _continue;
    
    private BusinessInteraction businessInteraction;

    @Override
    public String getLoggable() {
        return this.toString();
    }

    @Override
    public String getTransactionId() {
        return null;
    }
    
    @Override
	public Boolean isError() {
		return businessInteraction != null;
	}

}
