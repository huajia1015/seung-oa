package com.oa.domain;

import java.util.Date;

import com.oa.page.Page;
/**
 * 学生考勤表 
 * @author Dwen
 * @version v 0.1 2013-8-3 下午04:51:12
 */
public class StudentSignIn extends Page<StudentSignIn>{

	/**  */
	private static final long serialVersionUID = 5606527981410799799L;
	/** 学生考勤id*/
	private Long id;
	/** 学生id*/
	private Long studentId;
	/** 学生姓名 */
	private String studentName;
	/** 课程 */
	private String course;
	/** 课程代码 */
	private String courseCode;
	/**日期 */
	private Date signDate;
	/** 时间段 */
	private String time;
	/** 课时 */
	private Double lesson;
	/** 状态 {默认为0，签到为1，缺勤为2}*/
	private String status;
	/** 创建日期 */
	private Date createAt;
	/** 更新日期 */
	private Date updateAt;
	
	/** 开始日期 */
	private Date startAt;
	/** 结束日期 */
	private Date endAt;
	/** 开始日期字符串 */
	private String startAtStr;
	/** 结束日期字符串 */
	private String endAtStr;
	/** 操作人 */
	private String op;
	/** 备注*/
	private String remarks;
	
	/** 日期  String*/
	private String schoolDateStr;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	public Double getLesson() {
		return lesson;
	}
	public void setLesson(Double lesson) {
		this.lesson = lesson;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
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
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
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
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getSchoolDateStr() {
		return schoolDateStr;
	}
	public void setSchoolDateStr(String schoolDateStr) {
		this.schoolDateStr = schoolDateStr;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
