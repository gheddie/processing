package de.gravitex.processing.core.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DAOUtils {
	
	private static Logger logger = Logger.getLogger(DAOUtils.class);
	
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String formatDateForDB(Date date) {
		return df.format(date);
	}

	public static Date parseDBDate(String dateFromDB) {
		if (dateFromDB == null) {
			return null;
		}
		try {
			return df.parse(dateFromDB);
		} catch (ParseException e) {
			logger.error(e);
			return null;
		}
	}
}
