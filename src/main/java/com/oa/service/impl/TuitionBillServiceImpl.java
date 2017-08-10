package com.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.dao.ITuitionBillDao;
import com.oa.domain.TuitionBill;
import com.oa.domain.TuitionBillDetail;
import com.oa.page.Page;
import com.oa.service.ITuitionBillService;
/**
 * 学费单 - Service实现
 * @author dwen
 * 2014-5-11
 */
@Service
public class TuitionBillServiceImpl implements ITuitionBillService {
	
	@Autowired
	ITuitionBillDao tuitionBillDao;

	@Override
	public Page<TuitionBill> selectTuitionBills(TuitionBill tuitionBill) {
		int count = tuitionBillDao.selectTuitionBillCount(tuitionBill);
		tuitionBill.repaginate(count);
		List<TuitionBill> list = tuitionBillDao.selectTuitionBills(tuitionBill);
		tuitionBill.setList(list);
		return tuitionBill;
	}

	@Override
	public TuitionBill selectTuitionBillById(long id) {
		return tuitionBillDao.selectTuitionBillById(id);
	}

	@Override
	public void updateTuitionBill(TuitionBill tuitionBill) {
		tuitionBillDao.updateTuitionBill(tuitionBill);
	}

	@Override
	public long addTuitionBill(TuitionBill tuitionBill) {
		return tuitionBillDao.addTuitionBill(tuitionBill);
	}

	@Override
	public int addTuitionBillDetail(TuitionBillDetail tuitionBillDetail) {
		return tuitionBillDao.addTuitionBillDetail(tuitionBillDetail);
	}

	@Override
	public List<TuitionBillDetail> selectTuitionBillDetailById(long id) {
		return tuitionBillDao.selectTuitionBillDetailById(id);
	}

	@Override
	public void updateTuitionBillDetail(TuitionBillDetail tuitionBillDetail) {
		tuitionBillDao.updateTuitionBillDetail(tuitionBillDetail);
	}

	@Override
	public TuitionBill selectTuitionBillExist(TuitionBill tuitionBill) {
		return tuitionBillDao.selectTuitionBillExist(tuitionBill);
	}

}
