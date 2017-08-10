package com.oa.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.oa.page.Page;
/**
 * 学费单表
 * @author dwen
 * 2014-5-11
 */
public class TuitionBill extends Page<TuitionBill>{

	private static final long serialVersionUID = 1L;
	/** id */
	private Long id;
	/** 学生id*/
	private Long studentId;
	/** 学生姓名*/
	private String studentName;
	/** 年级*/
	private String grade;
	/** 开始月份*/
	private String startMonth;
	/** 结束月份*/
	private String endMonth;
	/** 书费*/
	private Double bookFee;
	/** 材料费*/
	private Double materialFee;
	/** 总费用*/
	private Double totalFee;
	/** 学费是否已收，默认为0未收，1为已收，2为部分收讫*/
	private String tuitionBillStatus;
	/** 操作人*/
	private String op;
	/** 备注*/
	private String remarks;
	/** 创建日期 */
	private Date createAt;
	/** 更新日期 */
	private Date updateAt;
	/** 收费操作人*/
    private String opFee;
    /** 学费单收费类型 默认0为现金 1为网付 2为POS*/
    private String feeType;
	
	private List<TuitionBillDetail> details = null;
	/** 学费合计*/
	private Double tuitionTotal;
	/** 打印日期*/
	private String printDate;
	/** 最后付款日期*/
	private String nextMonthDate;
	
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
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}
	public String getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	public Double getBookFee() {
		return bookFee;
	}
	public void setBookFee(Double bookFee) {
		this.bookFee = bookFee;
	}
	public Double getMaterialFee() {
		return materialFee;
	}
	public void setMaterialFee(Double materialFee) {
		this.materialFee = materialFee;
	}
	public Double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}
	public String getTuitionBillStatus() {
		return tuitionBillStatus;
	}
	public void setTuitionBillStatus(String tuitionBillStatus) {
		this.tuitionBillStatus = tuitionBillStatus;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public List<TuitionBillDetail> getDetails() {
		if (null==details) {
			details = new ArrayList<TuitionBillDetail>();
		}
		return details;
	}
	public void setDetails(List<TuitionBillDetail> details) {
		this.details = details;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Double getTuitionTotal() {
		return tuitionTotal;
	}
	public void setTuitionTotal(Double tuitionTotal) {
		this.tuitionTotal = tuitionTotal;
	}
	public String getPrintDate() {
		return printDate;
	}
	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}
	public String getNextMonthDate() {
		return nextMonthDate;
	}
	public void setNextMonthDate(String nextMonthDate) {
		this.nextMonthDate = nextMonthDate;
	}
	public String getOpFee() {
		return opFee;
	}
	public void setOpFee(String opFee) {
		this.opFee = opFee;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	} 
	
}
