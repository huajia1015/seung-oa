package com.oa.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户表
 * @author Dwen
 * @version v 0.1 2013-8-1 上午10:04:30
 */
public class User implements Serializable{

	/**  */
	private static final long serialVersionUID = -1042459574633051139L;

	/** 用户id*/
	private Long id;
	/** 用户名称*/
	private String userName;
	/** 密码*/
	private String password;
	/** 状态 默认0为可用，1为不可用*/
	private String status;
	/** 用户权限 {管理员为1，老师为2}*/
    private String authority;
    /** 登录ip*/
    private String ip;
    /** 是否登录 {0表示末登录，1表示登录}*/
    private String isLogin;
    /** 登录时间*/
    private Date loginDate;
    /** 登出时间*/
    private Date logoutDate;
	/** 创建时间*/
	private Date createAt;
	/** 更新时间*/
	private Date updateAt;
	
	
	/** 验证码*/
	private String checkCode;
	
	public User(){}//默认构造方法
	
	public User(String authority){
		this.authority = authority;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public Date getLogoutDate() {
		return logoutDate;
	}
	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	
}
