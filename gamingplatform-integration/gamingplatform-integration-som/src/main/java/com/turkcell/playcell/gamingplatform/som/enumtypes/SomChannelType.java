package com.turkcell.playcell.gamingplatform.som.enumtypes;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SomChannelType {
	
    IPHONE("IOS",new BigInteger("15")),
    ANDROID("ANDROID",new BigInteger("16")),
    MOBILE("MOBILE",new BigInteger("36")),
    WEB("WEB",new BigInteger("37"));

    @Getter
    private final String name;
    @Getter
    private final BigInteger value;

    private static final Map<String, SomChannelType> lookup = new HashMap<>();

    static {
        for (SomChannelType type : SomChannelType.values()) {
            lookup.put(type.getName(), type);
        }
    }

    public static SomChannelType  getType(String typeString) {
        SomChannelType type = lookup.get(typeString);
        return type == null ? SomChannelType.ANDROID : type;
    }

}
