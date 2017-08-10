package com.oa.util;

import java.util.regex.Pattern;

import com.oa.log.SysLog;

public class ObjectUtils {
    private ObjectUtils() {
    }

    public static final String EMPTY_STRING = "";

    /**
     * 判断对象是否为null
     * 
     * @param obj 判断对象
     * @return true/false
     */
    public static boolean isNull(Object obj) {
        return obj == null ? true : false;
    }

    /**
     * 判断对象是否不为null
     * 
     * @param obj 判断对象
     * @return true/false
     */
    public static boolean isNotNull(Object obj) {
        return obj != null ? true : false;
    }

    /**
     * <pre>
     * ObjectUtils.equals(new Object(), null) = false;
     * ObjectUtils.equals(null, new Object()) = false;
     * ObjectUtils.equals(null, null) = false;
     * </pre>
     * 比较两个对象是否相等
     * @param obj1 对象1
     * @param obj2 对象2
     * @return
     */
    public static boolean equals(Object obj1, Object obj2) {
        if (isNotNull(obj1)) {
            return obj1.equals(obj2);
        } else if (isNotNull(obj2)) {
            return obj2.equals(obj1);
        }
        return false;
    }

    /**
     * 数字或数字字符串转Long对象
     * @param input
     * @param defaultVal
     * @return
     */
    public static Long parseLong(Object input, Long defaultVal) {
        if (input instanceof String) {
            String temp = input == null ? null : (String) input;
            try {
                if (!StringUtils.hasText(temp)) {
                    return defaultVal;
                }
                if (Pattern.matches("(^\\d+(\\.\\d+)?$|^\\d+(\\,\\d+)+(\\.\\d+)?$)", temp)) {
                    temp = temp.replaceAll(",", "");
                    return Double.valueOf(temp).longValue();
                }
            } catch (NumberFormatException e) {
            		SysLog.error("parse Long error![input=" + input + "] : "+e.getMessage());
                return defaultVal;
            }
        } else if (input instanceof Long) {
            return input == null ? defaultVal : (Long) input;
        } else if (input instanceof Number) {
            return input == null ? defaultVal : ((Number)input).longValue();
        } else {
            SysLog.info("parseLong: input parameter["+input == null ? null : input.toString()+"] is not a number or number string!");
        }

        return defaultVal;
    }

    /**
     * 数字或数字字符串转Long对象
     * @param input
     * @return
     */
    public static Long parseLong(Object input) {
        if (input instanceof String) {
            String temp = input == null ? null : (String) input;
            try {
                if (!StringUtils.hasText(temp)) {
                    return Long.MIN_VALUE;
                }
                if (Pattern.matches("(^\\d+(\\.\\d+)?$|^\\d+(\\,\\d+)+(\\.\\d+)?$)", temp)) {
                    temp = temp.replaceAll(",", "");
                    return Long.valueOf(temp);
                }
            } catch (NumberFormatException e) {
                SysLog.error("parse Long error![input=" + input + "] : "+e.getMessage());
                return Long.MIN_VALUE;
            }
        } else if (input instanceof Long) {
            return input == null ? Long.MIN_VALUE : (Long) input;
        } else if (input instanceof Number) {
            return input == null ? Long.MIN_VALUE :  ((Number) input).longValue();
        } else {
            SysLog.info("parseLong: input parameter["+input == null ? null : input.toString()+"] is not a number or number string!");
        }

        return Long.MIN_VALUE;
    }
    
    /**
     * 数字或数字字符串转Double对象
     * @param input
     * @param defaultVal
     * @return
     */
    public static Double parseDouble(Object input, Double defaultVal) {
        if (input instanceof String) {
            String temp = input == null ? null : (String) input;
            try {
                if (!StringUtils.hasText(temp)) {
                    return defaultVal;
                }
                if (Pattern.matches("(^\\d+(\\.\\d+)?$|^\\d+(\\,\\d+)+(\\.\\d+)?$)", temp)) {
                    temp = temp.replaceAll(",", "");
                    return Double.valueOf(temp);
                }
            } catch (NumberFormatException e) {
                SysLog.error("parse Long error![input=" + input + "]: "+e.getMessage());
                return defaultVal;
            }
        } else if (input instanceof Double) {
            return input == null ? defaultVal : (Double) input;
        } else if (input instanceof Number) {
            return input == null ? defaultVal : ((Number) input).doubleValue();
        } else {
            SysLog.info("parseLong: input parameter["+input == null ? null : input.toString()+"] is not a number or number string!");
        }

        return defaultVal;
    }

    /**
     * 数字或数字字符串转Double对象
     * @param input
     * @return
     */
    public static Double parseDouble(Object input) {
        if (input instanceof String) {
            String temp = input == null ? null : (String) input;
            try {
                if (!StringUtils.hasText(temp)) {
                    return Double.MIN_VALUE;
                }
                if (Pattern.matches("(^\\d+(\\.\\d+)?$|^\\d+(\\,\\d+)+(\\.\\d+)?$)", temp)) {
                    temp = temp.replaceAll(",", "");
                    return Double.valueOf(temp);
                }
            } catch (NumberFormatException e) {
                SysLog.error("parse Long error![input=" + input + "]: "+ e.getMessage());
                return Double.MIN_VALUE;
            }
        } else if (input instanceof Double) {
            return input == null ? Double.MIN_VALUE : (Double) input;
        } else if (input instanceof Number) {
            return input == null ? Double.MIN_VALUE : ((Number) input).doubleValue();
        } else {
            SysLog.info("parseLong: input parameter["+input == null ? null : input.toString()+"] is not a number or number string!");
        }

        return Double.MIN_VALUE;
    }
    
    
    /**
     * 判断字符串是否为数字,如果是,返回Integer,否则返回缺省值
     * 	<pre>
     * 	ObjectUtils.parseInteger(null,0) = 0;
     *  ObjectUtils.parseInteger("100",0) = 100;
     *  </pre>
     * @param numberString 数字字符串
     * @param defaultVal 缺省值
     * @return Integer
     */
    public static Integer parseInteger(String numberStr, Integer defaultVal) {
        if (!StringUtils.hasText(numberStr)) {
            return defaultVal;
        }
        if (numberStr.matches("(^\\d+(\\.\\d+)?$|^\\d+(\\,\\d+)+(\\.\\d+)?$)")) {
        		numberStr = numberStr.replaceAll(",", "");
            return Integer.valueOf(numberStr);
        } else {
            throw new NumberFormatException(numberStr);
        }
    }
    
    /**
     * 转换整型对象
     * <pre>
     * 	ObjectUtils.parseInteger(null) = 0;
     * 	ObjectUtils.parseInteger(100) = 100;
     * </pre>
     * @param number
     * @return  Integer
     */
    public static Integer parseInteger(Object number) {
        if(isNull(number)) {
            return 0;
        }
        if (number.toString().matches("(^\\d+(\\.\\d+)?$|^\\d+(\\,\\d+)+(\\.\\d+)?$)")) {
            return Integer.valueOf(number.toString().replaceAll(",", ""));
        } else {
            throw new NumberFormatException(number.toString());
        }
    }

}
