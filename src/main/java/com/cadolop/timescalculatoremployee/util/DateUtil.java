package com.cadolop.timescalculatoremployee.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * The class to
 *
 * @author Carlos Adolfo Lopez R
 * @version 1.0
 * @since 2020-02-15
 */
public class DateUtil {

	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	public static Date converStringToDate(String sDate) {
		Date date = null;
	    try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
		} catch (ParseException e) {
			try {
				date = new SimpleDateFormat("dd-MM-yyyy").parse(sDate);
			} catch (ParseException e1) {
				try {
					date = new SimpleDateFormat("dd.MM.yyyy").parse(sDate);
				} catch (ParseException e2) {
				    return date;
				}
			}
		}
	    return date;
	}
	
	/**
	 * This is the main method
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	public static LocalDate convertDateToLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
}