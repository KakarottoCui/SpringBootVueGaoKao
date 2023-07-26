package com.ivo.mas.system.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author fsy
 * 
 */
public class DateUtils {

	public static final String DatePattern = "yyyy-MM-dd";

	public static final String DateTimePattern = "yyyy-MM-dd HH:mm:ss";
	
	public static final String TimePattern = "HH:mm:ss";

	public static final String ShortTimePattern = "HH:mm";
	
	public static final String ShortDateTimePattern = "yyyy-MM-dd HH:mm";
	
	
	public static Date parseDate(String source) {
		return parse(source, DatePattern);
	}

	public static Date parseDateTime(String source) {
		return parse(source, DateTimePattern);
	}

	public static Date parse(String source, String patten) {
		if (StringUtils.isBlank(source))
			return null;
		DateFormat format;
		try {
			format = new SimpleDateFormat(patten);
		} catch (Exception e) {
			format = new SimpleDateFormat(DateTimePattern);
		}
		Date date;
		try {
			date = format.parse(source);
		} catch (Exception e) {
			date = null;
		}
		return date;
	}

	public static String formatDate(Date date) {
		return format(date, DatePattern);
	}

	public static String formatDateTime(Date date) {
		return format(date, DateTimePattern);
	}
	
	public static String formatShortDateTime(Date date) {
		return format(date, ShortDateTimePattern);
	}

	public static String format(Date date, String patten) {
		if (date == null)
			return "";
		DateFormat format;
		try {
			format = new SimpleDateFormat(patten);
		} catch (Exception e) {
			format = new SimpleDateFormat(DateTimePattern);
		}
		return format.format(date);
	}

	public static String getFormatBeforeOrAfterDate(int offset) {
		return getFormatBeforeOrAfterDay(offset, DatePattern);
	}

	public static String getFormatBeforeOrAfterDateTime(int offset) {
		return getFormatBeforeOrAfterDay(offset, DateTimePattern);
	}

	public static String getFormatBeforeOrAfterDay(int offset, String patten) {
		return format(getBeforeOrAfterDay(offset), patten);
	}

	public static Date getBeforeOrAfterDay(int offset) {
		if (offset == 0)
			return new Date();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, offset);
		return c.getTime();
	}

	public static Date getBeforeOrAfterDay(Date base, int offset) {
		if (offset == 0)
			return base;
		Calendar c = Calendar.getInstance();
		c.setTime(base);
		c.add(Calendar.DATE, offset);
		return c.getTime();
	}
	
	public static Date getBeforeOrAfterYear(int offset) {
		if (offset == 0)
			return new Date();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, offset);
		return c.getTime();
	}
	
	public static Date getBeforeOrAfterMinute(Date base, int offset) {
		if (offset == 0)
			return new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(base);
		c.add(Calendar.MINUTE, offset);
		return c.getTime();
	}
	
	//当月一号，加减年
	public static Date getMonthBeganWithYearOffset(int offset) {
	    Calendar c = Calendar.getInstance();
	  	c.add(Calendar.YEAR, offset);
	    c.add(Calendar.MONTH, 0);
	    c.set(Calendar.DAY_OF_MONTH,1);
	    c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	public static int getYear() {
		return getYear(new Date());
	}

	public static int getMonth() {
		return getMonth(new Date());
	}

	public static int getDayOfWeek() {
		return getDayOfWeek(new Date());
	}

	public static int getDayOfMonth() {
		return getDayOfMonth(new Date());
	}

	public static int getDayOfYear() {
		return getDayOfYear(new Date());
	}

	public static int getYear(Date date) {
		return get(date, Calendar.YEAR);
	}

	public static int getMonth(Date date) {
		return get(date, Calendar.MONTH) + 1;
	}

	public static int getDayOfWeek(Date date) {
		int day = get(date, Calendar.DAY_OF_WEEK);
		return day == 0 ? 7 : get(date, Calendar.DAY_OF_WEEK) - 1;
	}

	public static int getDayOfMonth(Date date) {
		return get(date, Calendar.DAY_OF_MONTH);
	}

	public static int getDayOfYear(Date date) {
		return get(date, Calendar.DAY_OF_YEAR);
	}

	private static Calendar getCalendar(Date date) {
		Calendar c = Calendar.getInstance();
		if (date != null)
			c.setTime(date);
		return c;
	}

	public static Date getDate(Date base) {
		Calendar c = Calendar.getInstance();
		c.setTime(base);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	
	private static int get(Date date, int field) {
		Calendar c = getCalendar(date);
		return c.get(field);
	}

	public static boolean compareDateTime(String s1, String s2) {
		return compare(parseDateTime(s1), parseDateTime(s2));
	}

	public static boolean compareDateTime(String s, Date date) {
		return compare(parseDateTime(s), date);
	}

	public static boolean compareDateTime(Date date, String s) {
		return compare(date, parseDateTime(s));
	}

	public static boolean compareDate(String s1, String s2) {
		return compare(parseDate(s1), parseDate(s2));
	}

	public static boolean compareDate(String s, Date date) {
		return compare(parseDate(s), date);
	}

	public static boolean compareDate(Date date, String s) {
		return compare(date, parseDate(s));
	}

	public static boolean compare(String s1, String patten1, String s2,
			String patten2) {
		return compare(parse(s1, patten1), parse(s2, patten2));
	}

	public static boolean compare(String s, String patten, Date date) {
		return compare(parse(s, patten), date);
	}

	public static boolean compare(Date date, String s, String patten) {
		return compare(date, parse(s, patten));
	}

	public static boolean compare(Date d1, Date d2) {
		if (d1 == null || d2 == null)
			throw new IllegalArgumentException();
		return d1.after(d2);
	}
	
	/**   
     * 计算两个日期之间相差的天数   
     * @param date1   
     * @param date2   
     * @return   
     */    
    public static int daysBetween(Date date1, Date date2)     
    {     
        long time1 = date1.getTime();                  
        long time2 = date2.getTime();         
        long between_days=(time2-time1)/(1000*3600*24);     
        return Integer.parseInt(String.valueOf(between_days));            
    }
    
    public static int daysBetween(String date1, String date2)     
    {     
    	Date date_1 = parseDate(date1);
    	Date date_2 = parseDate(date2);  
        return daysBetween(date_1, date_2);            
    }
    
    public static int daysBetween(String date1, Date date2)     
    {     
    	Date date_1 = parseDate(date1);
        return daysBetween(date_1, date2);            
    }
	
    public static int minsBetween(Date date1,Date date2)     
    {     
        long time1 = date1.getTime();                  
        long time2 = date2.getTime();         
        long between_mins=(time2-time1)/(1000*60);     
        return Integer.parseInt(String.valueOf(between_mins));            
    }
    
    /**
     * 比较年月
     */
    public static boolean extendMonth(Date date1, String date2){
    	int year1 = getYear(date1);
    	int month1 = getMonth(date1);
    	Date date = parseDate(date2);
    	int year2 = getYear(date);
    	int month2 = getMonth(date);
    	if(year1*100+month1 > year2*100+month2){
    		return true;
    	}
		return false;
    }
    
    public static boolean extendMonth(String date1_, String date2_){
    	Date date1 = parseDate(date1_);
    	int year1 = getYear(date1);
    	int month1 = getMonth(date1);
    	Date date2 = parseDate(date2_);
    	int year2 = getYear(date2);
    	int month2 = getMonth(date2);
    	if(year1*100+month1 > year2*100+month2){
    		return true;
    	}
		return false;
    }
    public static int getDifMonth(String startTime, String endTime){
    	Date startDate = parseDate(startTime);
    	Date endDate = parseDate(endTime);
    	return getDifMonth(startDate, endDate);
    }
    public static int getDifMonth(Date startDate, Date endDate){
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(startDate);
        end.setTime(endDate);
        int result = end.get(Calendar.MONTH) - start.get(Calendar.MONTH);
        int month = (end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12;
        return month + result;
    }

	public static int getDifMinutes(Date startDate, Date endDate){
		long l = endDate.getTime()-startDate.getTime();
		int min = (int) l/1000/60;
		return min;
	}

	public static Date ceil(long base, Date source) {
		if(source == null) return null;
		Date date = new Date();
		long time = source.getTime();
		long n = time % base;
		date.setTime(time - n + base);
		return date;
	}
	
	public static Date floor(long base, Date source) {
		if(source == null) return null;
		Date date = new Date();
		long time = source.getTime();
		long n = time % base;
		date.setTime(time - n);
		return date;
	}

	public static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}


}
