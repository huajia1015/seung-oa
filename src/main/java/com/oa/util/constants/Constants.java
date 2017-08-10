package com.oa.util.constants;
/**
 * 常量 - 工具类
 * 
 * @author Dwen
 * @version v 0.1 2013-8-1 上午10:24:47
 */
public class Constants {

	/** 用户session*/
	public static final String CRYSTAL_USER_SESSION = "CRYSTAL_USER_SESSION";
	/** 登录验证码 */
    public static final String LOGIN_CHECK_CODE               = "login_check_code";
    /** 已经过滤了的请求 */
    public static final String FILTERED_REQUEST 				  = "FILTERED_REQUEST";
    
    /** 已经登录*/
    public static final String HAD_LOGIN = "1";
    /** 末登录 */
    public static final String HAD_LOGOUT = "0";
    
    /** 管理员角色 */
    public static final String MANAGER_ROLE  = "1";
    /** 老师角色 */
    public static final String TEACHER_ROLE  = "2";
    
    /** 默认为2小时*/
    public static final Double HOURS_2 = 2D;
    
    /** 学生签到*/
    public static final String STUDENT_SIGN_YES  = "1";
    /** 学生缺勤*/
    public static final String STUDENT_SIGN_NO  = "2";
}
