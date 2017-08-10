package com.oa.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 学费详细单表
 * @author dwen
 * 2014-5-11
 */
public class TuitionBillDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	/** id */
	private Long id;
	/** 学费单id*/
	private Long tuitionBillId;
	/** 课程代码*/
	private String courseCode;
	/** 所有上课日期*/
	private String classDate;
	/** 学费*/
	private Double tuitionFee;
	/** 操作人*/
	private String op;
	/** 创建日期 */
	private Date createAt;
	/** 更新日期 */
	private Date updateAt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTuitionBillId() {
		return tuitionBillId;
	}
	public void setTuitionBillId(Long tuitionBillId) {
		this.tuitionBillId = tuitionBillId;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getClassDate() {
		return classDate;
	}
	public void setClassDate(String classDate) {
		this.classDate = classDate;
	}
	public Double getTuitionFee() {
		return tuitionFee;
	}
	public void setTuitionFee(Double tuitionFee) {
		this.tuitionFee = tuitionFee;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
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
	
	
}
