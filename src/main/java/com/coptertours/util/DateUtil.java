package com.coptertours.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

	public static String formatDate(Date date) {
		return DATE_FORMAT.format(date);
	}

	public static Date addMonths(Date date1, int months) {
		Calendar calendar = createCalendar(date1);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}

	public static int findDaysBetweenDates(Date date1, Date date2) {
		BigDecimal diff = new BigDecimal(date2.getTime() - date1.getTime());
		BigDecimal result = diff.divide(new BigDecimal(1000 * 60 * 60 * 24l), 0, RoundingMode.CEILING);
		return result.intValue();
	}

	private static Calendar createCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
}
