package com.oa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oa.domain.Student;
import com.oa.domain.StudentCourse;
import com.oa.domain.TuitionBill;
import com.oa.domain.TuitionBillDetail;
import com.oa.domain.User;
import com.oa.log.SysLog;
import com.oa.page.Page;
import com.oa.service.IStudentService;
import com.oa.service.ITuitionBillService;
import com.oa.util.DateUtil;
import com.oa.util.StringUtils;

/**
 * 学费单 - Controller
 * @author dwen
 * 2014-5-11
 */
@Controller
public class TuitionBillController extends BaseController{
	
	@Autowired
	private ITuitionBillService tuitionBillService;
	@Autowired
	IStudentService studentService;

	/**
	 * 学费单查询 - 页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/tuition-bill-page")
	public ModelAndView tuitionBillPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("fullYearMonthArr", DateUtil.getFullYearMonthArr());
		mav.setViewName("tuition/tuition-bill");
		return mav;
	}
	
	/**
	 * 学费单查询
	 * @param request
	 * @return
	 */
	@RequestMapping("/tuition-bill")
	public ModelAndView tuitionBill(HttpServletRequest request,TuitionBill tuitionBill) {
		ModelAndView mav = new ModelAndView();
		//当前页码数
		String current = request.getParameter("current");
		if (StringUtils.hasText(current)) {
			tuitionBill.setCurrent(current);
		}
		Page<TuitionBill> page = tuitionBillService.selectTuitionBills(tuitionBill);
		mav.addObject("page", page);//分页
		mav.addObject("tuitionBill", tuitionBill);
		mav.addObject("fullYearMonthArr", DateUtil.getFullYearMonthArr());
		mav.setViewName("tuition/tuition-bill");
		return mav;
	}
	
	/**
	 * 添加学费单 - 页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/add-tuition-bill-page")
	public ModelAndView addTuitionBillPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("fullYearMonthArr", DateUtil.getFullYearMonthArr());
		mav.setViewName("tuition/add-tuition-bill");
		return mav;
	}
	
	/**
	 * 添加学费单
	 * @param request
	 * @return
	 */
	@RequestMapping("/add-tuition-bill")
	public ModelAndView addTuitionBill(HttpServletRequest request,TuitionBill tuitionBill) {
		ModelAndView mav = new ModelAndView();
		try {
			//查询学费单是否存在
			TuitionBill tuitionBill2 = tuitionBillService.selectTuitionBillExist(tuitionBill);
			if (null==tuitionBill2) {
				//获得用户session
				User user = this.getSessionUser(request);
				tuitionBill.setOp(user.getUserName());
				if (null!= tuitionBill.getDetails() && tuitionBill.getDetails().size()>0) {
					//TODO service事务控制
					tuitionBillService.addTuitionBill(tuitionBill);
					for (int i = 0; i < tuitionBill.getDetails().size(); i++) {
						TuitionBillDetail tuitionBillDetail = tuitionBill.getDetails().get(i);
						tuitionBillDetail.setTuitionBillId(tuitionBill.getId());
						tuitionBillDetail.setOp(user.getUserName());
						//TODO 添加学费单详细
						tuitionBillService.addTuitionBillDetail(tuitionBillDetail);
					}
					mav.addObject("addTuitionResult", true);
				}else{
					mav.addObject("addTuitionResult", false);
				}
			}else{
				mav.addObject("addTuitionResult", "500");
			}
		} catch (Exception e) {
			mav.addObject("addTuitionResult", false);
			SysLog.error("添加学费单异常！"+e.getMessage());
		}
		mav.addObject("fullYearMonthArr", DateUtil.getFullYearMonthArr());
		mav.setViewName("tuition/add-tuition-bill");
		return mav;
	}
	
	/**
	 * 查询当前学生课程
	 * @param request
	 * @return
	 */
	@RequestMapping("/select-student-course-tuition")
	public ModelAndView selectStudentCourse(HttpServletRequest request,Student student) {
		List<StudentCourse> studentCourses = null;
		Student studentObj = null;
		ModelAndView mav = new ModelAndView();
		if (StringUtils.isNotBlank(student.getStudentName())) {
			studentObj = studentService.selectStudentByName(student);
			if (null!=studentObj) {
				studentCourses = studentService.selectStudentCourseByName(studentObj.getStudentName());
			}
		}
		mav.addObject("studentObj", studentObj);
		mav.addObject("studentCourses", studentCourses);
		mav.addObject("fullYearMonthArr", DateUtil.getFullYearMonthArr());
		mav.setViewName("tuition/add-tuition-bill");
		return mav;
	}
	
	/**
	 * 修改学费单 - 页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/update-tuition-bill-page")
	public ModelAndView updateTuitionBillPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String id = request.getParameter("id");//id
		try {
			if (StringUtils.isNotBlank(id)) {
				TuitionBill tuitionBill= tuitionBillService.selectTuitionBillById(Long.valueOf(id.trim()));
				mav.addObject("tuitionBill", tuitionBill);
				List<TuitionBillDetail> tuitionBillDetails = tuitionBillService.selectTuitionBillDetailById(tuitionBill.getId());
				mav.addObject("tuitionBillDetails", tuitionBillDetails);
				mav.addObject("fullYearMonthArr", DateUtil.getFullYearMonthArr());
			}
		} catch (NumberFormatException e) {
			SysLog.error("学费单异常！"+e.getMessage());
		}
		mav.setViewName("tuition/update-tuition-bill");
		return mav;
	}
	
	/**
	 * 修改学费单
	 * @param request
	 * @return
	 */
	@RequestMapping("/update-tuition-bill")
	public ModelAndView updateTuitionBill(HttpServletRequest request,TuitionBill tuitionBill) {
		ModelAndView mav = new ModelAndView();
		try {
			//获得用户session
			User user = this.getSessionUser(request);
			tuitionBill.setOp(user.getUserName());
			tuitionBill.setOpFee(user.getUserName());
			if (null != tuitionBill.getDetails() && tuitionBill.getDetails().size()>0) {
				//修改学费单
				tuitionBillService.updateTuitionBill(tuitionBill);
				for (int i = 0; i < tuitionBill.getDetails().size(); i++) {
					TuitionBillDetail tuitionBillDetail = tuitionBill.getDetails().get(i);
					tuitionBillDetail.setOp(user.getUserName());
					//修改学费单详细
					tuitionBillService.updateTuitionBillDetail(tuitionBillDetail);
				}
				mav.addObject("updateTuitionBillResult", true);
			}else{
				mav.addObject("updateTuitionBillResult", false);
			}
		} catch (Exception e) {
			mav.addObject("updateTuitionBillResult", false);
			SysLog.error("修改学费单失败！"+e.getMessage());
		}
		mav.setViewName("tuition/tuition-bill");
		return mav;
	}
	
	/**
	 * 打印多个学费单
	 * @param request
	 * @return
	 */
	@RequestMapping("/many-tuition-bill")
	public ModelAndView manyTuitionBillPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<TuitionBill> list = new ArrayList<TuitionBill>();
		//获得id
		String[] tuitionCheckboxIds = request.getParameterValues("tuitionCheckboxId");
		if (null != tuitionCheckboxIds && tuitionCheckboxIds.length>0) {
			for (String ids : tuitionCheckboxIds) {
				//查询学费单
				TuitionBill tuitionBill= tuitionBillService.selectTuitionBillById(Long.valueOf(ids));
				//查询学费单详细
				List<TuitionBillDetail> tuitionBillDetails =tuitionBillService.selectTuitionBillDetailById(tuitionBill.getId());
				double tuitionTotal = 0;//学费合计
				for (TuitionBillDetail tuitionBillDetail : tuitionBillDetails) {
					tuitionTotal = tuitionTotal + tuitionBillDetail.getTuitionFee();
					tuitionBillDetail.setClassDate(StringUtils.filterStringYear(tuitionBillDetail.getClassDate()));
				}
				tuitionBill.setTuitionTotal(tuitionTotal);
				tuitionBill.setPrintDate(DateUtil.getCurrTime());
				tuitionBill.setNextMonthDate(DateUtil.getNextMonthTime());
				tuitionBill.setDetails(tuitionBillDetails);
				list.add(tuitionBill);
			}
			mav.addObject("tuitionBillList", list);
			mav.setViewName("tuition/many-tuition-bill");
		}else{
			mav.addObject("selectTuitionCheckbox", true);
			mav.addObject("fullYearMonthArr", DateUtil.getFullYearMonthArr());
			mav.setViewName("tuition/tuition-bill");
		}
		return mav;
	}
	
	/**
	 * 学费单详细
	 * @param request
	 * @return
	 */
	@RequestMapping("/tuition-bill-detail")
	public ModelAndView tuitionBillDetail(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			//查询学费单
			TuitionBill tuitionBill= tuitionBillService.selectTuitionBillById(Long.valueOf(id.trim()));
			mav.addObject("tuitionBill", tuitionBill);
			//查询学费单详细
			List<TuitionBillDetail> tuitionBillDetails =tuitionBillService.selectTuitionBillDetailById(tuitionBill.getId());
			double tuitionTotal = 0;//学费合计
			for (TuitionBillDetail tuitionBillDetail : tuitionBillDetails) {
				tuitionTotal = tuitionTotal + tuitionBillDetail.getTuitionFee();
				tuitionBillDetail.setClassDate(StringUtils.filterStringYear(tuitionBillDetail.getClassDate()));
			}
			mav.addObject("tuitionTotal", tuitionTotal);
			mav.addObject("tuitionBillDetails", tuitionBillDetails);
			mav.addObject("printDate", DateUtil.getCurrTime());//打印日期
			mav.addObject("nextMonthDate", DateUtil.getNextMonthTime());//最后付款日期 当前系统时间后退一个月
		}
		mav.setViewName("tuition/tuition-bill-detail");
		return mav;
	}
	
}
