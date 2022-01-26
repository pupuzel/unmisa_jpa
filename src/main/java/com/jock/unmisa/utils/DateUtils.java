package com.jock.unmisa.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	
	private final static ZoneId zone = ZoneId.of("Asia/Seoul");
	
	private final static DateTimeFormatter yyyymmddhhmiss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	private final static DateTimeFormatter yyyymmdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	private final static DateTimeFormatter hhmiss = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	public static LocalDateTime now() {
		return LocalDateTime.now(zone);
	}
	
	public static String getDateTime() {
		return LocalDateTime.now(zone).format(yyyymmddhhmiss);
	}
	
	public static String getDate() {
		return LocalDateTime.now(zone).format(yyyymmdd);
	}
	
	public static String getTime() {
		return LocalDateTime.now(zone).format(hhmiss);
	}
}
