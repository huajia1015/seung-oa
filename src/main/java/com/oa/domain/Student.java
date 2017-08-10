package com.oa.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生表
 * @author Dwen
 * @version v 0.1 2013-8-3 下午04:28:44
 */
public class Student implements Serializable{

	/**  */
	private static final long serialVersionUID = 6985942354207331919L;
	/** 学生id*/
	private Long id;
	/** 学生姓名 */
	private String studentName;
	/** 英文名*/
	private String enName;
	/** 学校*/
	private String school;
	/** 家庭住址*/
	private String address;
	/** 联系方式1 */
	private String contactInfo;
	/** 联系方式2 */
	private String contactInfo2;
	/** qq号*/
	private String qq;
	/** 学生状态 {0为可用，1为不可用}*/
	private String status;
	/** 创建日期 */
	private Date createAt;
	/** 更新日期 */
	private Date updateAt;
	/** 年级*/
	private String grade;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getContactInfo2() {
		return contactInfo2;
	}
	public void setContactInfo2(String contactInfo2) {
		this.contactInfo2 = contactInfo2;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
}
