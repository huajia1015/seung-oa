<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>Crystal工作室 - 添加学费单</title>
<meta charset="utf-8">
<script type="text/javascript" src="js/jquery/jquery-1.7.2.js"></script>
<link rel="stylesheet" href="js/kalendajs/build/kalendae.css" type="text/css" charset="utf-8">
<script src="js/kalendajs/build/kalendae.standalone.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" href="css/tuition-bill.css" type="text/css" charset="utf-8">
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/autocomplete/jquery.autocomplete.js"></script>
<link rel="stylesheet" href="js/autocomplete/jquery.autocomplete.css"/>
<script type="text/javascript">
$(document).ready(function(){
	<c:forEach items="${studentCourses}" var="row" varStatus="status">
	//多选日期
	var picker = new Kalendae.Input('date_'+${status.index},{
		months:1,
		mode:'multiple',
		multipleDelimiter:',',
		titleFormat:'YYYY,MM'
	});
	</c:forEach>
	
	//提交
	$("#addTuitionBillBtn").click(function(){
		if(verifyAddTuitionBill()){
			$("#addTuitionBillForm").submit();
		}
	});
	
	
	/* 验证添加学费单*/
	function verifyAddTuitionBill(){
		var studentName = $("#studentName_id").val();
		var grade = $("#grade").val();
		var startMonth = $("#startMonth").val();
		var endMonth = $("#endMonth").val();
		var totalFee = $("#totalFee").val();
		var remarks = $("#remarks").val();
		if(studentName == null || studentName == ''){
			alert("姓名不能为空！");
			return false;
		}else if(studentName.length>20){
			alert("姓名限20个字符！");
			return false;
		}else if(grade == null || grade == ''){
			alert("年级不能为空！");
			return false;
		}else if(startMonth == null || startMonth == ''){
			alert("开始月份不能为空！");
			return false;
		}else if(endMonth == null || endMonth == ''){
			alert("结束月份不能为空！");
			return false;
		}else if(totalFee == null || totalFee == ''){
			alert("总计不能为空！");
			return false;
		}else if(remarks.length>200){
			alert("备注限200个字符！");
			return false;
		}
		return true;
	}
});

/** 生成学费单返回结果*/
if(${addTuitionResult==true}){
	alert("成功生成学费单！");
}
if(${addTuitionResult=='500'}){
	alert("该学生的学费单已生成过，不能重复生成！");
}
</script>
</head>
<body>
<div id="main_container">
    <jsp:include page="/common/header1.jsp"></jsp:include>
    
<div class="main_content">
	    <jsp:include page="/common/header.jsp"></jsp:include>
	    <div class="center_content">  
	    <div style="padding:left;margin-left:5%;">
		<form action="select-student-course-tuition.html" method="post">
		<br/>
		<table class="table_1">
			<tr>
				<td colspan="5" align="center">西象教育学费单生成</td>
			</tr>
			<tr>
				<td>学生姓名：</td>
				<td>
				<input type="text" id="studentName" size="26" name="studentName" autocomplete="off"/><font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" value="查询"/>
				</td>
				<!-- <td>年级：</td>
				<td>
					<select size="1" name="grade">
							<option value="">请选择</option>
							<option value="K3">K3</option>
							<option value="G1">G1</option>
							<option value="G2">G2</option>
							<option value="G3">G3</option>
							<option value="G4">G4</option>
							<option value="G5">G5</option>
							<option value="G6">G6</option>
							<option value="G7">G7</option>
							<option value="G8">G8</option>
							<option value="G9">G9</option>
							<option value="S1">S1</option>
							<option value="S2">S2</option>
							<option value="S3">S3</option>
					</select><span style="color:red">*</span>
				</td> -->
				<!-- <td><input type="submit" value="查询"/></td> -->
			</tr>
		</table>
		</form>
		<br/>
		<!-- 学费单 -->
		<form action="add-tuition-bill.html" method="post" id="addTuitionBillForm">
		<input type="hidden" name="studentId" value="${studentObj.id }"/>
		<table class="table_1">
			<tr>
				<td colspan="8" align="center">西象教育学费单</td>
			</tr>
			<tr><td colspan="8">&nbsp;</td></tr>
			<tr>
				<td>姓名：</td>
				<td><input type="text" id="studentName_id" name="studentName" readonly="readonly" value="${studentObj.studentName }" autocomplete="off"/><font color="red">*</font></td>
				<td>年级：</td>
				<td><input type="text" id="grade" name="grade" readonly="readonly" value="${studentObj.grade }"/><font color="red">*</font></td>
				<td>年月：</td>
				<td>
					<select name="startMonth" id="startMonth">
						<option value="">请选择</option>
						<c:forEach items="${fullYearMonthArr}" var="row">
						<option value="${row}">${row}</option>
						</c:forEach>
					</select><font color="red">*</font>
				</td>
				<td>至：</td>
				<td>
					<select name="endMonth" id="endMonth">
						<option value="">请选择</option>
						<c:forEach items="${fullYearMonthArr}" var="row">
						<option value="${row}">${row}</option>
						</c:forEach>
					</select><font color="red">*</font>
				</td>
			</tr>
		</table>
		<table class="table_1">
			<tr>
				<td align="center">课程</td>
				<td align="center">日期</td>
				<td align="center">学费</td>
			</tr>
			<c:forEach items="${studentCourses}" var="row" varStatus="status">
			<tr>
				<td>${row.courseCode }
				<input type="hidden" name="details[${status.index}].courseCode" value="${row.courseCode }"/><font color="red">*</font>
				</td>
				<td>
					<input type="text" size="80"  id="date_${status.index}" name="details[${status.index}].classDate"/><font color="red">*</font>
				</td>
				<td><input type="text" name="details[${status.index}].tuitionFee"/><font color="red">*</font></td>
			</tr>
			</c:forEach>
		</table>
		<table class="table_1">
			<tr>
				<td>书费：</td>
				<td><input type="text" name="bookFee" id="bookFee"/></td>
				<td>材料费：</td>
				<td><input type="text" name="materialFee" id="materialFee"/></td>
				<td>总计：</td>
				<td><input type="text" name="totalFee" id="totalFee"/><font color="red">*</font></td>
			</tr>
			<tr>
				<td>备注：</td>
				<td colspan="5"><textarea rows="10" cols="50" name="remarks" id="remarks"></textarea></td>
			</tr>
			<tr>
				<td colspan="6" align="center"><input type="button" value="保存" id="addTuitionBillBtn"/></td>
			</tr>
		</table>
		</form>
		</div>
		</div>   <!--end of center content -->               
		<div class="clear"></div>
</div> <!--end of main content-->

<!-- footer -->
<jsp:include page="/common/footer.jsp"></jsp:include>
</div>

</body>
</html>
