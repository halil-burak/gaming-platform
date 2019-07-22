package com.turkcell.playcell.gamingplatform.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public enum StatusTypes {
	
	PENDING("PENDING"),
	DONE("DONE"),
	ERROR("ERROR"),
	SUCCESS("SUCCESS"),
	ERROR_WITH_NO_RETRY("ERROR_WITH_NO_RETRY"),
	ERROR_MAX_RETRY("ERROR_MAX_RETRY"),
	READY_TO_PROCESS("READY_TO_PROCESS"),
	PROCESSING("PROCESSING")
	;

	@Getter
	private final String value;

	private static final Map<String, StatusTypes> lookup = new HashMap<>();

	static {
		for (StatusTypes type : StatusTypes.values()) {
			lookup.put(type.getValue(), type);
		}
	}

	public static StatusTypes get(String invitationStatus) {
		return lookup.get(invitationStatus);
	}

}
