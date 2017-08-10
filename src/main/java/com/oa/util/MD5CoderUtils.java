package com.oa.util;

import org.apache.commons.codec.digest.DigestUtils;


/**
 * MD5加密工具类
 * @author Dwen
 * @version v 0.1 2013-8-15 下午04:52:18
 */
public class MD5CoderUtils {
	/**
	 * MD5消息摘要
	 * @param data 待做摘要处理的数据
	 * @return byte[] 消息摘要
	 * @throws Exception
	 */
	public static byte[] encodeMD5(String data) throws Exception{
		//执行消息摘要
		return DigestUtils.md5(data);
	}
	
	/**
	 * MD5消息摘要
	 * @param data 待做摘要处理的数据
	 * @return 消息摘要
	 * @throws Exception
	 */
	public static String encodeMD5Hex(String data) throws Exception{
		//执行消息摘要
		return DigestUtils.md5Hex(data);
	}
	
	/**
	 * 入口
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String str = "123456";
		//获取摘要信息
		String data1 = MD5CoderUtils.encodeMD5Hex(str);
		String data2 = MD5CoderUtils.encodeMD5Hex(str);
		System.out.println("原文：\t"+str);
		System.out.println("MD5Hex-1 :\t"+data1);
		System.out.println("MD5Hex-2 :\t"+data2);
		System.err.println(data1.equals(data2));
		//MD5Hex-1 :	ab39a41394aee9820cd2234b741ee312
	}

}
