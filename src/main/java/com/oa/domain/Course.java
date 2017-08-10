package com.oa.domain;

import java.util.Date;

import com.oa.page.Page;
/**
 * 课程表 
 * @author Dwen
 * @version v 0.1 2013-8-3 下午04:38:53
 */
public class Course extends Page<Course>{

	/**  */
	private static final long serialVersionUID = 2866558034705030558L;
	
	/** 课程id */
	private Long id;
	/** 课程代码 */
	private String courseCode;
	/** 课程名称*/
	private String course;
	/** 上课老师*/
	private String teacher;
	/** 学期*/
	private String term;
	/** 上课日期 */
	private String schoolDate;
    /** 时间段 */
	private String time;
	/** 状态 {0为可用，1为不可用}*/
	private String status;
	/** 创建日期 */
	private Date createAt;
	/** 更新日期 */
	private Date updateAt;
	
	/** id数组*/
	private String[] ids;
	
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
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getSchoolDate() {
		return schoolDate;
	}
	public void setSchoolDate(String schoolDate) {
		this.schoolDate = schoolDate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
}
