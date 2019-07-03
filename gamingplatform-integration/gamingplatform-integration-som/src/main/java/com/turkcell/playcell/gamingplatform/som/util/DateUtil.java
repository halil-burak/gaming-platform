package com.turkcell.playcell.gamingplatform.som.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.time.DateUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtil {
	
	private static List<String> knownDateTimePatterns = Arrays.asList(
			"dd.MM.yyyy HH:mm:ss",
			"yyyy-MM-dd HH:mm:ss"
	);

	public static String todayFormat(String pattern) {
		return formatLocalDateTime(LocalDateTime.now(), pattern);
	}

	public static String formatNowAsDate() {
		return formatLocalDateTime(LocalDateTime.now(), "yyyy-MM-dd");
	}

	public static String formatNowAsDateTime() {
		return formatLocalDateTime(LocalDateTime.now(), "dd.MM.yyyy HH:mm:ss");
	}

	public static String formatLocalDateTimeAsDate(LocalDateTime localDateTime) {
		return formatLocalDateTime(localDateTime, "yyyy-MM-dd");
	}

	public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return localDateTime.format(formatter);
	}

	public static LocalDateTime parseLocalDateTimeBroadage(String dateTimeStr) {
		for (String pattern : knownDateTimePatterns) {
			try {
				LocalDateTime localDateTime = parseLocalDateTime(dateTimeStr, pattern);
				if (localDateTime != null)
					return localDateTime;
			} catch (DateTimeParseException dtpe) {
				//ignore this exception, we are trying all the formats
				log.debug("Broadage datetime parse error : {}", dtpe.getMessage());
			}
		}

		return null;
	}

	public static LocalDateTime parseLocalDateTime(String dateTimeStr, String pattern) {
		return dateTimeStr == null ? null : LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
	}

	public static double convertDateToScore(String date) {
		return Double.valueOf(date.replaceAll("[^0-9]", "").substring(0, 12));
	}

	public static String addOrRemoveMinuteToSystemDate(long minute, String pattern) {
		return Instant.now()
				.atZone(ZoneId.systemDefault())
				.minusMinutes(minute).format(DateTimeFormatter.ofPattern(pattern));
	}

	public static Date addMinutes(int minute, Date date) {
		return DateUtils.addMinutes(date, minute);
	}

	public static Optional<Date> parseDate(String pattern, String date) {
		try {
			return Optional.of(DateUtils.parseDate(date, pattern));
		} catch (ParseException e) {
			log.error("[Parse Exception at given date {}]", e);
			return Optional.empty();
		}
	}

	public static String getTodayAsString(){
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss:SS");
		Date date = new Date();

		return dateFormat.format(date);
	}

	public static Date parseTodayAsStringToDate(String dateAsString){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss:SS");
		Date date = null;

		try {
			date = sdf.parse(dateAsString);
		} catch (ParseException e) {
			log.error("Date could not be parsed. string-date:{}", dateAsString);
		}

		return date;
	}


	public static Date now() {
		return new Date(System.currentTimeMillis());
	}

	public static Date dateWithTime(long time) {
		return new Date(time);
	}


	public static String convertPatternToPattern(String date, String inComingPattern, String OutCommingPattern) {
		Optional<Date> date1 = DateUtil.parseDate(inComingPattern, date);
		if (date1.isPresent()) {
			LocalDateTime ldt = LocalDateTime.ofInstant(date1.get().toInstant(), ZoneId.systemDefault());
			return ldt.format(DateTimeFormatter.ofPattern(OutCommingPattern));
		} else {
			log.error("[Parse Exception at given date {}]",date);
			return null;
		}
	}

	public static String formatDate(Date date, String pattern){
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}


	public static void main(String[] args) {
		System.out.println(formatNowAsDateTime());
	}

	public static LocalDateTime dateToLocalDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static LocalDateTime stringDateToLocalDateTime(String dateString, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date1 = null;

		try {
			date1 = sdf.parse(dateString);
		} catch (ParseException e) {
			log.error("Date could not be parsed. string-date:{}", dateString);
			return null;
		}
		return DateUtil.dateToLocalDateTime(date1);
	}


}
