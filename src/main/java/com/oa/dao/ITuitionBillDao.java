package com.oa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oa.domain.TuitionBill;
import com.oa.domain.TuitionBillDetail;
/**
 * 学费单 - Dao层
 * @author dwen
 * 2014-5-11
 */
@Repository
public interface ITuitionBillDao {

	/**
	 * 查询学费单
	 * @param tuitionBill
	 * @return
	 */
	List<TuitionBill> selectTuitionBills(TuitionBill tuitionBill);
	
	/**
	 * 学费单 - 总数
	 * @param tuitionBill
	 * @return
	 */
	int selectTuitionBillCount(TuitionBill tuitionBill);
	
	/**
	 * 查询学费单
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
	 * 查询学费单明细
	 * @param id
	 * @return
	 */
	List<TuitionBillDetail> selectTuitionBillDetailById(long id);
	
	/**
	 * 修改学费单
	 * @param tuitionBill
	 */
	void updateTuitionBill(TuitionBill tuitionBill);
	
	/**
	 * 修改学费单明细
	 * @param tuitionBill
	 */
	void updateTuitionBillDetail(TuitionBillDetail tuitionBillDetail);
	
	/**
	 * 添加学费单
	 * @param tuitionBill
	 * @return
	 */
	long addTuitionBill(TuitionBill tuitionBill);
	
	/**
	 * 添加学费单详细
	 * @param tuitionBillDetail
	 * @return
	 */
	int addTuitionBillDetail(TuitionBillDetail tuitionBillDetail);
	
}
