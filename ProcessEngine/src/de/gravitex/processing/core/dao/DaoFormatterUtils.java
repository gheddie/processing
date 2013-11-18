package de.gravitex.processing.core.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DaoFormatterUtils {
	
	private static Logger logger = Logger.getLogger(DaoFormatterUtils.class);
	
	private static final SimpleDateFormat dfDatabase = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String formatDateForDB(Date date) {
		return dfDatabase.format(date);
	}

	public static Date parseDBDate(String dateFromDB) {
		if (dateFromDB == null) {
			return null;
		}
		try {
			return dfDatabase.parse(dateFromDB);
		} catch (ParseException e) {
			logger.error(e);
			return null;
		}
	}
	
	public static String formatTimePeriod(long milliSeconds) {
		
		long dividerHours = 1000*60*60;
		long dividerMinutes = 1000*60;
		long dividerSeconds = 1000;
		
		//h
		long hours = milliSeconds / dividerHours;
		long restOfHours = milliSeconds - (hours*dividerHours);
		
		//m
		long minutes = restOfHours / dividerMinutes;
		long restOfMinutes = restOfHours - (minutes*dividerMinutes);
		
		//s
		long seconds = restOfMinutes / dividerSeconds;
		
		return hours + " Stunden, " + minutes + " Minuten, " + seconds + " Sekunden";
	}
}
