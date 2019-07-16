package com.turkcell.playcell.gamingplatform.som.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Builder
@Getter
@Setter
@ToString
public class BusinessInteractionApproval {
	
    private BigInteger notificationId;

    private String messageText;

    private String approveLabel;

    private String rejectLabel;

    private BigInteger criteriaId;

}
