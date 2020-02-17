package com.cadolop.timescalculatoremployee.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * The util class date format
 *
 * @author Carlos Adolfo Lopez R
 * @version 1.0
 * @since 2020-02-15
 */
public class DateUtil {

	/**
	 * This method convert a string date to date
	 * 
	 * @param sDate the string date format dd/MM/yyyy or dd-MM-yyyy or dd.MM.yyy.
	 * @return the date.
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
	 * This method convert date to local date class
	 * 
	 * @param date the date to convert.
	 * @return the date in local date class.
	 */
	public static LocalDate convertDateToLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
}