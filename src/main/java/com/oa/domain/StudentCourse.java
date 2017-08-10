package com.oa.domain;

import java.util.Date;

import com.oa.page.Page;
/**
 * 学生课程关系表
 * @author Dwen
 * @version v 0.1 2013-8-3 下午04:52:55
 */
public class StudentCourse extends Page<StudentCourse> {

	/**  */
	private static final long serialVersionUID = 4769602913100948142L;
	/** id*/
	private Long id;
	/** 学生id*/
	private Long studentId;
	/** 学生姓名 */
	private String studentName;
	/**  课程id*/
	private Long courseId;
	/** 课程名称 */
	private String courseCode;
	/** 考试成绩 */
	private String score;
	/** 报名日期*/
	private Date startAt;
	/** 状态 {0为可用，1为不可用}*/
	private String status;
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
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public Date getStartAt() {
		return startAt;
	}
	public void setStartAt(Date startAt) {
		this.startAt = startAt;
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
	
}
