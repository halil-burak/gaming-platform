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
public class BusinessInteractionError {

    private BigInteger notificationId;

    private Boolean systemWillRetry;

    private String errorType;

    private String userText;

    private BigInteger errorId;

    private String errorDetail;
    
}
