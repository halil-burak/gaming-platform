package com.turkcell.playcell.gamingplatform.som.enumtypes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@RequiredArgsConstructor
public enum SomActionType {

	CREATE(new BigInteger("1"));
	
    @Getter
    private final BigInteger value;
}
