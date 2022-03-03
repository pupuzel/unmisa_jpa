package com.jock.unmisa.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
	
	private final static ZoneId zone = ZoneId.of("Asia/Seoul");
	
	private final static DateTimeFormatter yyyymmddhhmiss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private final static DateTimeFormatter yyyymmdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private final static DateTimeFormatter hhmiss = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	public final static SimpleDateFormat formatYMD = new SimpleDateFormat("yyyyMMdd");
	
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
	
	public static long diffDay(String startDay, String endDay) throws Exception{
		Date format1 = formatYMD.parse(startDay);
		Date format2 = formatYMD.parse(endDay);
		
		//�γ�¥ ������ �ð� ����(ms)�� �Ϸ� ������ ms(24��*60��*60��*1000�и���) �� ������.
        long diffDay = (format1.getTime() - format2.getTime()) / (24*60*60*1000);
        
        return diffDay;
	}
}
