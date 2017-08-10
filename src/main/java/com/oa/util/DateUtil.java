package com.oa.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.oa.log.SysLog;

/**
 * 日期工具类
 * 
 */
public class DateUtil {

    private static String     datePattern = "yyyy-MM-dd";
    /** yyyy-MM-dd */
    private static DateFormat dateFormat  = new SimpleDateFormat(datePattern);
    /** yyyy-MM-dd HH:mm */
    private static DateFormat dateFormat2  = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    /** yyyy-MM-dd HH:mm:ss */
    private static DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /** yyyyMMdd*/
    private static String     dateFormat4 = "yyyyMMdd";
    /** HH:mm */
    private static String     timePattern = "HH:mm";
    

    /**
     * Return 缺省的日期格式 (yyyy/MM/dd)
     * 
     * @return 在页面中显示的日期格式
     */
    public static String getDatePattern() {
        return datePattern;
    }

    /**
     * 根据日期格式，返回日期按datePattern格式转换后的字符串
     * 
     * @param aDate
     *            日期对象
     * @return 格式化后的日期的页面显示字符串
     */
    public static final String getDate(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(datePattern);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    public static final Date strToDate(String str) throws ParseException {
        if (str == null || str.trim().length() == 0)
            return null;
        return dateFormat.parse(str.trim());
    }

    /**
     * 按照日期格式，将字符串解析为日期对象
     * 
     * @param aMask
     *            输入字符串的格式
     * @param strDate
     *            一个按aMask格式排列的日期的字符串描述
     * @return Date 对象
     * @see java.text.SimpleDateFormat
     * @throws ParseException
     */
    public static final Date convertStringToDate(String aMask, String strDate)
                                                                              throws ParseException {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);

        SysLog.logDebug("converting '" + strDate + "' to date with mask '" + aMask + "'");

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            // log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    /**
     * This method returns the current date time in the format: yyyy/MM/dd HH:MM
     * a
     * 
     * @param theTime
     *            the current time
     * @return the current date/time
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(timePattern, theTime);
    }

    /**
     * This method returns the current date in the format: yyyy-MM-dd
     * 
     * @return the current date
     * @throws ParseException
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(datePattern);

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    /**
     * This method generates a string representation of a date's date/time in
     * the format you specify on input
     * 
     * @param aMask
     *            the date pattern the string is in
     * @param aDate
     *            a date object
     * @return a formatted string representation of the date
     * 
     * @see java.text.SimpleDateFormat
     */
    public static final String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            SysLog.error("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * 根据日期格式，返回日期按datePattern格式转换后的字符串
     * 
     * @param aDate
     * @return
     */
    public static final String convertDateToString(Date aDate) {
        return getDateTime(datePattern, aDate);
    }

    /**
     * 按照日期格式，将字符串解析为日期对象
     * 
     * @param strDate
     *            (格式 yyyy-MM-dd)
     * @return
     * 
     * @throws ParseException
     */
    public static Date convertStringToDate(String strDate) throws ParseException {
        Date aDate = null;

        try {
            SysLog.logDebug("converting date with pattern: " + datePattern);

            aDate = convertStringToDate(datePattern, strDate);
        } catch (ParseException pe) {
            SysLog.error("Could not convert '" + strDate + "' to a date, throwing exception");
            pe.printStackTrace();
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());

        }

        return aDate;
    }

    /**
     * 时间相加
     * 
     * @param date
     * @param day
     * @return
     */
    public static Date dateAdd(Date date, int day) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }

    /**
     * 获取两个日期之间的天数
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static long dateDiffer(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) / (1000 * 3600 * 24);
    }

    /**
     * 取当前日期时间
     * @return yyyy-mm-dd hh:mm:ss  2009-12-12 12:12:12
     */
    public static String getNowDateTime() {
        DateFormat dataAndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar rightNow = Calendar.getInstance();
        return dataAndTime.format(rightNow.getTime());
    }

    /**
     * 取当前日期时间
     * @return yyyy-mm-dd hh:mm:ss  2009-12-12 12:12:12
     */
    public static String getNowDateTime(String pattern) {
        DateFormat dataAndTime = new SimpleDateFormat(pattern);
        Calendar rightNow = Calendar.getInstance();
        return dataAndTime.format(rightNow.getTime());
    }


    /**
     * 获当前年
     * 
     * @return 年 2012
     */
    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获当前月
     * @return 月 2
     */
    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }


    /**
     * 获取一段年的列表
     * @param startYear
     * @param endYear
     * @return List 2001 2002,2003,2004
     */
    public static List<String> getConditionYearList(int startYear, int endYear) {
        List<String> list = new ArrayList<String>();
        if (startYear > endYear) {
            return list;
        }
        list.add(Integer.toString(startYear));
        for (int i = 1; i <= (endYear - startYear); i++) {
            list.add(Integer.toString(startYear + i));
        }
        return list;
    }

    /**
     * 取得相差的天数
     * 
     * @param startDate
     * @param endDate
     * 
     * @return
     */
    public static long countDays(String startDate, String endDate) {
        Date tempDate1 = null;
        Date tempDate2 = null;
        long days = 0;

        try {
            tempDate1 = DateUtil.string2Date(startDate);

            tempDate2 = DateUtil.string2Date(endDate);
            days = (tempDate2.getTime() - tempDate1.getTime()) / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * yyyy-MM-dd 日期字符转换为时间
     * 
     * @param stringDate
     * 
     * @return
     * 
     * @throws ParseException
     */
    public static final Date string2Date(String stringDate) throws ParseException {
        if (stringDate == null) {
            return null;
        }

        return dateFormat.parse(stringDate);
    }

    /**
     * 获取当前时间(yyyy-MM-dd)
     * @author tang.xiaojun
     * @return
     */
    public static final String getCurrTime() {
        Calendar cal = Calendar.getInstance();
        StringBuffer sb = new StringBuffer();
        sb.append(cal.get(Calendar.YEAR) + "-");
        sb.append((cal.get(Calendar.MONTH) + 1) + "-");
        sb.append(cal.get(Calendar.DAY_OF_MONTH));
        return sb.toString();
    }
    
    /**
     * 获取下个月时间
     * @return
     */
    public static final String getNextMonthTime() {
        Calendar cal = Calendar.getInstance();
        StringBuffer sb = new StringBuffer();
        sb.append(cal.get(Calendar.YEAR) + "-");
        sb.append((cal.get(Calendar.MONTH) + 2) + "-");
        sb.append(cal.get(Calendar.DAY_OF_MONTH));
        return sb.toString();
    }

    /**
     * 判断2个日期大小(0:date1>date2;1:date1<date2)
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static int compare_date(String DATE1, String DATE2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        int result = 0;
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                result = 0;
            } else if (dt1.getTime() < dt2.getTime()) {
                result = 1;
            } else {
                result = 2;
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }
    
    /**
     * 分钟转小时
     * @param startDate 开始时间 yyyy-MM-dd HH:mm
     * @param endDate 结束时间 yyyy-MM-dd HH:mm
     * @return 小时 eg.2.0
     * @throws ParseException
     */
	public static double minutesToHour(String startDate, String endDate)
			throws ParseException {
		long end = dateFormat2.parse(endDate).getTime();
		long start = dateFormat2.parse(startDate).getTime();
		double m = (end - start) / 60000;// 分
		BigDecimal bd = new BigDecimal(m/60);
		//保留一位小数
		return bd.setScale(1,BigDecimal.ROUND_FLOOR).doubleValue();//时
	}
	
	/**
	 * 获得当前年所有年月份
	 * @return
	 */
	public static String[] getFullYearMonthArr(){
		String[] str = new String[12];
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		str[0]= year+"01";
		str[1]= year+"02";
		str[2]= year+"03";
		str[3]= year+"04";
		str[4]= year+"05";
		str[5]= year+"06";
		str[6]= year+"07";
		str[7]= year+"08";
		str[8]= year+"09";
		str[9]= year+"10";
		str[10]= year+"11";
		str[11]= year+"12";
		return str;
	}
    
    public static void main(String[] args) {
    	String[] arr = DateUtil.getFullYearMonthArr();
    	for (String string : arr) {
			System.out.println(string);
		}
    	
    		try {
				double d = minutesToHour("2013-08-15  8:00", "2013-08-15  10:40");
				System.out.println(d);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
	}
}
