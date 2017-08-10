package com.oa.domain;

import java.util.Date;

import com.oa.page.Page;
/**
 * 老师考勤表
 * @author Dwen
 * @version v 0.1 2013-8-3 下午04:52:24
 */
public class TeacherSignIn extends Page<TeacherSignIn> {

	/**  */
	private static final long serialVersionUID = -7163385101240634148L;
	/** 考勤id*/
	private Long id;
	/** 课程代号*/
	private String courseCode;
	/** 老师*/
	private String teacher;
	/** 状态 {正常1，迟到2}*/
	private String status;
	/** 考勤日期*/
	private Date  signDate;
	/** 到岗时间*/
	private String comeTime;
	/** 上课时间*/
	private String classOn;
	/** 下课时间*/
	private String classOff;
	/** 课时*/
	private Double lesson;
	/** 备注 */
	private String remarks;
	/** 创建日期*/
	private Date createAt;
	/** 更新日期*/
	private Date updateAt;
	/** 操作人 */
	private String op;
	
	/** 上课人数 */
	private Integer peopleNum;
	/** 开始日期 */
	private Date startAt;
	/** 结束日期 */
	private Date endAt;
	/** 开始日期字符串 */
	private String startAtStr;
	/** 结束日期字符串 */
	private String endAtStr;
	/** 考勤日期字符串*/
	private String  signDateStr;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	public String getClassOn() {
		return classOn;
	}
	public void setClassOn(String classOn) {
		this.classOn = classOn;
	}
	public String getClassOff() {
		return classOff;
	}
	public void setClassOff(String classOff) {
		this.classOff = classOff;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComeTime() {
		return comeTime;
	}
	public void setComeTime(String comeTime) {
		this.comeTime = comeTime;
	}
	public Double getLesson() {
		return lesson;
	}
	public void setLesson(Double lesson) {
		this.lesson = lesson;
	}
	public Date getStartAt() {
		return startAt;
	}
	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}
	public Date getEndAt() {
		return endAt;
	}
	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public String getStartAtStr() {
		return startAtStr;
	}
	public void setStartAtStr(String startAtStr) {
		this.startAtStr = startAtStr;
	}
	public String getEndAtStr() {
		return endAtStr;
	}
	public void setEndAtStr(String endAtStr) {
		this.endAtStr = endAtStr;
	}
	public String getSignDateStr() {
		return signDateStr;
	}
	public void setSignDateStr(String signDateStr) {
		this.signDateStr = signDateStr;
	}
	public Integer getPeopleNum() {
		return peopleNum;
	}
	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	
}
