package com.turkcell.playcell.gamingplatform.api.util;

public class StackTraceUtils {

	public static String extractTurkcellStackTrace(Exception ex) {
		StringBuffer sb = new StringBuffer();
		for (StackTraceElement stackTraceElement : ex.getStackTrace()) {
			if (stackTraceElement.getClassName().contains("com.turkcell"))
				sb.append("\tat ").append(stackTraceElement.toString()).append("\n");
		}

		return sb.toString();
	}
	
}
