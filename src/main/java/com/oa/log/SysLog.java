package com.oa.log;

import org.apache.log4j.Logger;

/**
 * 系统日志
 * @author Dwen
 * @version v 0.1 2013-7-17 下午06:23:58
 */
public class SysLog {

	/** 控制台日志 */
	private static Logger log = Logger.getLogger("outputInfo");
	/** 系统信息日志 */
	private static Logger sysInfo = Logger.getLogger("sysInfo");
	/** 系统错误日志 */
	private static Logger sysLogError = Logger.getLogger("sysError");

	public static void logDebug(String message) {
		log.debug(message);
	}

	public static void info(String message) {
		log.info(message);
	}

	public static void error(String message) {
		log.error(message);
	}

	public static void sysInfoDebug(String message) {
		sysInfo.debug(message);
	}

	public static void sysInfo(String message) {
		sysInfo.info(message);
	}

	public static void sysInfoError(String message) {
		sysInfo.error(message);
	}

	public static void sysLogFileError(String message) {
		sysLogError.error(message);
	}
}
