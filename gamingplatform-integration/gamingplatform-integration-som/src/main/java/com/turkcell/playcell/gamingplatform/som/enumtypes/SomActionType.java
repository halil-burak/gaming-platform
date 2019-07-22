package com.turkcell.playcell.gamingplatform.som.enumtypes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@RequiredArgsConstructor
public enum SomActionType {

	// PACKAGE CREATE ORDER ACTION TYPE
	CREATE(new BigInteger("1")),
	// PACKAGE CANCEL ORDER ACTION TYPE
	CANCEL(new BigInteger("4"));
	
    @Getter
    private final BigInteger value;
}
