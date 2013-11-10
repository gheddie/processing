package de.gravitex.processing.core.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DAOUtils {
	
	private static final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	public static String formatDateForDB(Date date) {
		return df.format(date);
	}
}
