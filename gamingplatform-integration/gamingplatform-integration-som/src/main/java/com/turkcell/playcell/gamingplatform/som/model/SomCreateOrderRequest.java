package com.turkcell.playcell.gamingplatform.som.model;

import com.turkcell.playcell.gamingplatform.som.enumtypes.SomActionType;
import com.turkcell.playcell.gamingplatform.som.enumtypes.SomChannelType;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SomCreateOrderRequest extends WsLog {
	
    private String offerId;

    private String transactionId;

    private String msisdn;

    private SomActionType somActionType;
    
    private SomChannelType somChannelType;

	@Override
	public String getLoggable() {
		return this.toString();
	}

}
