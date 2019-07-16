package com.turkcell.playcell.gamingplatform.som.util;

import java.util.Random;
import java.util.UUID;

public class UUIDUtil {

    public static String getId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String getRandomIdWithLength(final int length){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        if(uuid.length()< length ){
            throw  new RuntimeException("UUIDUtil: length must be smaller than " + uuid.length());
        }
        return uuid.substring(0,length);

    }

	public static String getNumericRandomIdWithLength(final int length) {
		StringBuilder sb = new StringBuilder();
		Random generator = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(generator.nextInt(10));
		}
		return sb.toString();
	}
	
}
