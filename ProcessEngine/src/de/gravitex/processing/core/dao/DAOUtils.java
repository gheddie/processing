package de.gravitex.processing.core.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DAOUtils {
	
	private static Logger logger = Logger.getLogger(DAOUtils.class);
	
	private static final SimpleDateFormat df_write = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	private static final SimpleDateFormat df_read = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String formatDateForDB(Date date) {
		return df_write.format(date);
	}

	public static Date parseDBDate(String dateFromDB) {
		try {
			return df_read.parse(dateFromDB);
		} catch (ParseException e) {
			logger.error(e);
			return null;
		}
	}
}
