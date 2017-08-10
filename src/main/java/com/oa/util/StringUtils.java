package com.oa.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import com.oa.log.SysLog;

public class StringUtils {
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DecimalFormat numberFormat = new DecimalFormat("###,###.##");

	/**
	 * 判断字符串是否包含有意义字符
	 * <pre>
	 * StringUtils.hasText(" ") = false;
	 * StringUtils.hasText("test") = true;
	 * StringUtils.hasText(null) = false;
	 * StringUtils.hasText("  test") = true;
	 * </pre>
	 * @param str
	 * @return true/false
	 */
	public static boolean hasText(String str) {
	    if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
	}
	
	/**
	 * 判断字符串是否为空或null(去除空格)
	 * <pre>
	 * StringUtils.emptyOrNull(" ") = true;
	 * StringUtils.emptyOrNull(null) = true;
	 * StringUtils.emptyOrNull("test") = false;
	 * StringUtils.emptyOrNull(" test") = false;
	 * </pre>
	 * @param str
	 * @return
	 */
	public static boolean emptyOrNull(String str) {
	    return str == null || !hasText(str);
	}

	/**
	 * 判断字符串是否有长度
	 * <pre>
	 * StringUtils.hasLength(" ") = true;
	 * StringUtils.hasLength("") = false;
	 * StringUtils.hasLength("abc") = true;
	 * StringUtils.hasLength(null) = false;
	 * </pre>
	 * @param str
	 * @return
	 */
	public static boolean hasLength(String str) {
        return str != null && str.length() > 0;
    }

	/**
	 * 日期转字符串(yyyy-MM-dd HH:mm:ss)
	 * <pre>
	 * StringUtils.dateToString(new Date()) = "2011-06-13 14:00:00"
	 * </pre>
	 * @param date
	 * @return String(yyyy-MM-dd HH:mm:ss)
	 */
	public static synchronized String dateToString(Date date) {
		return format.format(date);
	}
	public static synchronized String dateToStringShort(Date date) {
	    SimpleDateFormat shortFormat = new SimpleDateFormat("yyyy-MM-dd");
	    return shortFormat.format(date);
	 }

	/**
	 * 格式数字
	 * <pre>
	 * StringUtils.formatNumber(new Long(100000)) = 100,000.00
	 * StringUtils.formatNumber(new Double(100000)) = 100,000.00
	 * StringUtils.formatNumber(new Integer(100000)) = 100,000.00
	 * StringUtils.formatNumber(new Float(100000)) = 100,000.00
	 * StringUtils.formatNumber(new Long(1)) = 1.00
	 * StringUtils.formatNumber(new Long(111)) = 111.00
	 * StringUtils.formatNumber(new Long(1111)) = 1,111.00
	 * </pre>
	 * @param num 数字
	 * @return 格式化数字字符串
	 */
	public static String formatNumber(Number num) {
		String fmtString = null;
		try {
			fmtString = numberFormat.format(num);
		} catch (IllegalArgumentException e) {
			throw e;
		}
		return fmtString;
	}

	/**
	 * 根据数字格式,格式化数字
	 * <pre>
	 * StringUtils.formatNumber(new Long(1000),"##,###.00") = 1,000.00
	 * StringUtils.formatNumber(new Long(100000),"#,####.00") = 10,0000.00
	 * </pre>
	 * @param num 数字
	 * @param fmtString 格式串
	 * @return
	 */
	public static String formatNumber(Number num, String fmtString) {
	    DecimalFormat customerFormat = new DecimalFormat(fmtString);
		fmtString = customerFormat.format(num);
		return fmtString;
	}
	
	/**
	 * 数字字符串转数字
	 * <pre>
	 * StringUtils.parseNumber("100") = 100;
	 * </pre>
	 * @param numberString 数字字符串
	 * @return Number
	 * @throws ParseException
	 */
	public static Number parseNumber(String numberString) {
        Number number = null;
        try {
            number = numberFormat.parse(numberString);
        } catch (ParseException e) {
            throw new IllegalArgumentException(numberString + " is not a number format!");
        }
        return number;
    }
	
	/**
     * 全角转半角
     * @param input
     * @return
     */
    public static String toDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }
    
    /**
     * 字符串比较(区分大小写)
     * @param val
     * @param compareVal
     * @return
     */
    public static boolean compare(String val, String compareVal) {
        if(!ObjectUtils.isNull(val) && val.equals(compareVal)) {
            return true;
        }
        return false;
    }
    
    /**
     * 字符串比较(不区分大小写)
     * @param val
     * @param compareVal
     * @return
     */
    public static boolean compareIgnoreCase(String val, String compareVal) {
        if(!ObjectUtils.isNull(val) && val.equalsIgnoreCase(compareVal)) {
            return true;
        }
        return false;
    }
    
    /**
     * 字符串转int
     * @param input
     * @return
     */
    public static Integer parseInt(String input, Integer defaultVal) {
        try {
            if (!StringUtils.hasText(input)) {
                return defaultVal;
            }
            if (Pattern.matches("(^\\d+(\\.\\d+)?$|^\\d+(\\,\\d+)+(\\.\\d+)?$)",input)) {
                input = input.replaceAll(",", "");
                return Double.valueOf(input).intValue();
            }
        } catch (NumberFormatException e) {
            SysLog.error("parse Long error![input="+input+"] :"+e.getMessage());
            return defaultVal;
        }
        return defaultVal;
    }
    
    /**
     * 处理整型字符串
     * <pre>
     * StringUtils.treatInt("000900") =  "900"
     * StringUtils.treatInt("900") = "900"
     * </pre>
     * @param str
     * @return
     */
	public static String treatInt(String str) {
		int result = 0;
		if (null == str) {
			return null;
		}
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				result = result * 10 + str.charAt(i) - 48;
			}
		}
		return String.valueOf(result);
	}
    
    /**
     * 字符串转long
     * @param input
     * @return
     */
    public static Long parseLong(String input) {
        try {
            if (!StringUtils.hasText(input)) {
                return Long.MIN_VALUE;
            }
            if (Pattern.matches("(^\\d+(\\.\\d+)?$|^\\d+(\\,\\d+)+(\\.\\d+)?$)",input)) {
                input = input.replaceAll(",", "");
                return Long.valueOf(input);
            }
        } catch (NumberFormatException e) {
            SysLog.error("parse Long error![input="+input+"] :" + e.getMessage());
            return Long.MIN_VALUE;
        }
        return Long.MIN_VALUE;
    }
    
    /**
     * 字符串转Long型
     * 
     * @param input
     * @param defaultVal
     * @return
     */
    public static Long parseLong(String input, Long defaultVal) {
        try {
            if (!StringUtils.hasText(input)) {
                return defaultVal;
            }
            if (Pattern.matches("(^\\d+(\\.\\d+)?$|^\\d+(\\,\\d+)+(\\.\\d+)?$)",input)) {
                input = input.replaceAll(",", "");
                return Long.valueOf(input);
            }
        } catch (NumberFormatException e) {
            SysLog.error("parse Long error![input="+input+"] :"+ e.getMessage());
            return defaultVal;
        }
        return defaultVal;
    }
    
    public static Double parseDouble(String input) {
        try {
            if (!StringUtils.hasText(input)) {
                return Double.MIN_VALUE;
            }
            if (Pattern.matches("(^\\d+(\\.\\d+)?$|^\\d+(\\,\\d+)+(\\.\\d+)?$)",input)) {
                input = input.replaceAll(",", "");
                return Double.valueOf(input);
            }
        } catch (NumberFormatException e) {
            SysLog.error("parse Long error![input="+input+"] :"+e.getMessage());
            return Double.MIN_VALUE;
        }
        return Double.MIN_VALUE;
    }
    
    public static Double parseDouble(String input, Double defaultVal) {
        try {
            if (!StringUtils.hasText(input)) {
                return defaultVal;
            }
            if (Pattern.matches("(^\\d+(\\.\\d+)?$|^\\d+(\\,\\d+)+(\\.\\d+)?$)",input)) {
                input = input.replaceAll(",", "");
                return Double.valueOf(input);
            }
        } catch (NumberFormatException e) {
            SysLog.error("parse Long error![input="+input+"] :"+ e.getMessage());
            return defaultVal;
        }
        return defaultVal;
    }
    
    /**
     * 
     * 空字符串处理
     * <pre>
	 * StringUtils.dealNull(new Integer(2),"0") = 2
	 * StringUtils.dealNull(null,"0") = 0
	 * </pre>
     * @param str
     * @param defaultValue
     * @return
     */
    public static String dealNull(Object str, String defaultValue) {
		return null != str ? str.toString() : defaultValue;
	}
    /**
     * 检查字符串是否不是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
     * <pre>
     * StringUtil.isBlank(null)      = false
     * StringUtil.isBlank("")        = false
     * StringUtil.isBlank(" ")       = false
     * StringUtil.isBlank("bob")     = true
     * StringUtil.isBlank("  bob  ") = true
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果为空白, 则返回<code>true</code>
     */
    public static boolean isNotBlank(String str) {
    	if (str != null) {
	        int length = str.length();
	        if (length == 0){
	            return false;
	        }
	        for (int i = 0; i < length; i++) {
	            if (!Character.isWhitespace(str.charAt(i))) {
	                return true;
	            }
	        }
	
	        return false;
    	}else{
    		return false;
    	}
    }
    
    /**
     * 过滤字符串中当前年和下一年
     * @param yearStr 
     * @return
     */
    public static String filterStringYear(String yearStr){
    	int year = DateUtil.getYear();
    	return yearStr.replace("/"+year, "").replace("/"+year+1, "");
    }
    
    
    public static void main(String[] a) {
//        System.out.println("+++++kv".replaceAll("(\\+|-)+", "$1"));
        System.out.println(filterStringYear("05/01/2014,05/02/2014,05/03/2014,05/04/2014,05/05/2014,05/06/2014,05/07/2014,05/08/2014,05/09/2014,05/10/2014,05/11/2014,05/12/2014,05/13/2014,05/14/2014,05/15/2014,05/16/2014,05/17/2014,05/18/2014"));
    }
}
