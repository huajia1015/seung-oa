package com.oa.service;

import java.util.List;

import com.oa.domain.TuitionBill;
import com.oa.domain.TuitionBillDetail;
import com.oa.page.Page;

/**
 * 学费单 - Service层
 * @author dwen
 * 2014-5-11
 */
public interface ITuitionBillService {

	/**
	 * 查询学费单
	 * @param tuitionBill
	 * @return
	 */
	Page<TuitionBill> selectTuitionBills(TuitionBill tuitionBill);
	
	/**
	 * 查询学费单详细
	 * @param id
	 * @return
	 */
	TuitionBill selectTuitionBillById(long id);
	
	/**
	 * 查询学费单是否存在
	 * @param tuitionBill
	 * @return
	 */
	TuitionBill selectTuitionBillExist(TuitionBill tuitionBill);
	
	/**
	 * 修改学费单
	 * @param tuitionBill
	 */
	void updateTuitionBill(TuitionBill tuitionBill);
	
	/**
	 * 添加学费单
	 * @param tuitionBill
	 * @return
	 */
	long addTuitionBill(TuitionBill tuitionBill);
	
	/**
	 * 修改学费单明细
	 * @param tuitionBillDetail
	 */
	void updateTuitionBillDetail(TuitionBillDetail tuitionBillDetail);
	
	/**
	 * 添加学费单详细
	 * @param tuitionBillDetail
	 * @return
	 */
	int addTuitionBillDetail(TuitionBillDetail tuitionBillDetail);
	
	/**
	 * 查询学费单明细
	 * @param id
	 * @return
	 */
	List<TuitionBillDetail> selectTuitionBillDetailById(long id);
	
}
