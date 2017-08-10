<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>Crystal工作室 - 学费单详细</title>
<meta charset="utf-8">
<script type="text/javascript" src="js/jquery/jquery-1.7.2.js"></script>
<link rel="stylesheet" href="css/tuition-bill.css" type="text/css" charset="utf-8">
<script type="text/javascript">
$(document).ready(function(){
	
	
});

</script>
</head>
<body>
		<table class="table_2">
				<tr>
					<td colspan="7" align="center" style="border:0"><h2>西象教育学费单</h2></td>
				</tr>
				<tr>
					<td align="right" style="width:50px;border:0">姓名</td>
					<td align="center" style="width:100px;border:0">${tuitionBill.studentName}</td>
					<td align="right" style="border:0">年级</td>
					<td colspan="2" align="center" style="border:0">${tuitionBill.grade}</td>
					<td align="right" style="border:0">月份</td>
					<td align="center" style="width:120px;border:0">${tuitionBill.startMonth}-${tuitionBill.endMonth}</td>
				</tr>
				<tr>
					<td colspan="2" align="center">课程</td>
					<td colspan="4" align="center">上课时间</td>
					<td align="center">学费(元)</td>
				</tr>
				<c:forEach items="${tuitionBillDetails}" var="row">
				<tr>
					<td align="center" colspan="2">${row.courseCode }</td>
					<td colspan="4" style="word-break:break-all">${row.classDate }</td>
					<td align="center">${row.tuitionFee }</td>
				</tr>
				</c:forEach>
				<tr>
					<td align="right" colspan="6" style="border:0">学费合计(元)：</td>
					<td align="center" style="border:0">${tuitionTotal}</td>
				</tr>
				<tr>
					<td align="right" colspan="6" style="border:0">材料费(元)：</td>
					<td align="center" style="border:0">${tuitionBill.materialFee}</td>
				</tr>
				<tr>
					<td align="right" colspan="6" style="border:0">书费(元)：</td>
					<td align="center" style="border:0">${tuitionBill.bookFee}</td>
				</tr>
				<tr>
					<td align="right" colspan="6" style="border:0">总计(元)：</td>
					<td align="center" style="border:0">${tuitionBill.totalFee}</td>
				</tr>
				<tr>
					<td  colspan="4" style="border:0">打印日期：${printDate}</td>
					<td colspan="3" align="right" style="border:0">最后付款日期：${nextMonthDate }</td>
				</tr>
				<tr>
					<td colspan="7" style="border:0">感谢您对我们的支持，如有问题请及时与我们联系，祝你生活愉快O(∩_∩)O~</td>
				</tr>
				<tr>
					<td align="right" colspan="7" style="border:0">电话：68900157</td>
				</tr>
		</table>
</body>
</html>
