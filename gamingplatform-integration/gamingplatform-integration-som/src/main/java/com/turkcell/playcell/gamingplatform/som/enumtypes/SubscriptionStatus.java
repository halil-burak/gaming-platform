package com.turkcell.playcell.gamingplatform.som.enumtypes;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SubscriptionStatus {
	
    ACTIVE("ACTIVE"),
    SUSPEND("SUSPEND"),
    INACTIVE("INACTIVE");

    @Getter
    private final String value;

    private static final Map<String, SubscriptionStatus> lookup = new HashMap<>();

    static {
        for (SubscriptionStatus type : SubscriptionStatus.values()) {
            lookup.put(type.getValue(), type);
        }
    }

    public static SubscriptionStatus get(String suspensionStatus) {
        return lookup.get(suspensionStatus);
    }

}
