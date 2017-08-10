<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>Crystal工作室 - 学费单修改</title>
<meta charset="utf-8">
<script type="text/javascript" src="js/jquery/jquery-1.7.2.js"></script>
<link rel="stylesheet" href="js/kalendajs/build/kalendae.css" type="text/css" charset="utf-8">
<script src="js/kalendajs/build/kalendae.standalone.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" href="css/tuition-bill.css" type="text/css" charset="utf-8">
<script type="text/javascript">
$(document).ready(function(){
	<c:forEach items="${tuitionBillDetails}" var="row" varStatus="status">
	//多选日期
	var picker = new Kalendae.Input('date_'+${status.index},{
		months:1,
		mode:'multiple',
		multipleDelimiter:',',
		//format:'YYYY-MM-DD',
		titleFormat:'YYYY,MM'
	});
	</c:forEach>
	
	//提交
	$("#updateTuitionBillBtn").click(function(){
		if(verifyUpdateTuitionBill()){
			$("#updateTuitionBillForm").submit();
		}
	});
	
	
	/* 验证修改学费单*/
	function verifyUpdateTuitionBill(){
		var studentName = $("#studentName").val();
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

/** 修改学费单返回结果*/
if(${updateTuitionBillResult==true}){
	alert("成功修改学费单！");
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
		<!-- 学费单 -->
		<form action="update-tuition-bill.html" method="post" id="updateTuitionBillForm">
		<input type="hidden" name="studentId" value="${tuitionBill.studentId}"/>
		<input type="hidden" name="id" value="${tuitionBill.id}"/>
		<input type="hidden" name="grade" value="${tuitionBill.grade}"/>
		<table class="table_1">
			<tr>
				<td colspan="7" align="center">学费单</td>
			</tr>
			<tr>
				<td>学生：<input type="text" name="studentName" id="studentName" readonly="readonly" value="${tuitionBill.studentName }"/><span style="color:red">*</span></td>
				<td>年级：
				<select id="grade" disabled="disabled">
							<option value="K3" <c:if test="${tuitionBill.grade =='K3'}">selected='selected'</c:if>>K3</option>
							<option value="G1" <c:if test="${tuitionBill.grade =='G1'}">selected='selected'</c:if>>G1</option>
							<option value="G2" <c:if test="${tuitionBill.grade =='G2'}">selected='selected'</c:if>>G2</option>
							<option value="G3" <c:if test="${tuitionBill.grade =='G3'}">selected='selected'</c:if>>G3</option>
							<option value="G4" <c:if test="${tuitionBill.grade =='G4'}">selected='selected'</c:if>>G4</option>
							<option value="G5" <c:if test="${tuitionBill.grade =='G5'}">selected='selected'</c:if>>G5</option>
							<option value="G6" <c:if test="${tuitionBill.grade =='G6'}">selected='selected'</c:if>>G6</option>
							<option value="G7" <c:if test="${tuitionBill.grade =='G7'}">selected='selected'</c:if>>G7</option>
							<option value="G8" <c:if test="${tuitionBill.grade =='G8'}">selected='selected'</c:if>>G8</option>
							<option value="G9" <c:if test="${tuitionBill.grade =='G9'}">selected='selected'</c:if>>G9</option>
							<option value="S1" <c:if test="${tuitionBill.grade =='S1'}">selected='selected'</c:if>>S1</option>
							<option value="S2" <c:if test="${tuitionBill.grade =='S2'}">selected='selected'</c:if>>S2</option>
							<option value="S3" <c:if test="${tuitionBill.grade =='S3'}">selected='selected'</c:if>>S3</option>
					</select><span style="color:red">*</span>
				</td>
				<td>收费类型：
				<select name="feeType" id="feeType">
					<option value="0" <c:if test="${tuitionBill.feeType=='0'}">selected='selected'</c:if>>现金</option>
					<option value="1" <c:if test="${tuitionBill.feeType=='1'}">selected='selected'</c:if>>网付</option>
					<option value="2" <c:if test="${tuitionBill.feeType=='2'}">selected='selected'</c:if>>POS</option>
				</select><span style="color:red">*</span>
				</td>
				<td>状态：
				<select name="tuitionBillStatus" id="tuitionBillStatus">
					<option value="0" <c:if test="${tuitionBill.tuitionBillStatus=='0'}">selected='selected'</c:if>>未收</option>
					<option value="1" <c:if test="${tuitionBill.tuitionBillStatus=='1'}">selected='selected'</c:if>>已收</option>
					<option value="2" <c:if test="${tuitionBill.tuitionBillStatus=='2'}">selected='selected'</c:if>>部分收讫</option>
				</select><span style="color:red">*</span>
				</td>
				<td>
					年月<select name="startMonth" id="startMonth">
						<option value="">请选择</option>
						<c:forEach items="${fullYearMonthArr}" var="row">
						<option value="${row}" <c:if test="${row ==tuitionBill.startMonth}">selected='selected'</c:if>>${row}</option>
						</c:forEach>
					</select><span style="color:red">*</span>
				</td>
				<td>
					至：<select name="endMonth" id="endMonth">
						<option value="">请选择</option>
						<c:forEach items="${fullYearMonthArr}" var="row">
						<option value="${row}" <c:if test="${row ==tuitionBill.endMonth}">selected='selected'</c:if>>${row}</option>
						</c:forEach>
					</select><span style="color:red">*</span>
				</td>
			</tr>
		</table>
		<table class="table_1">
			<tr>
				<td align="center">课程</td>
				<td align="center">日期</td>
				<td align="center">学费</td>
			</tr>
			<c:forEach items="${tuitionBillDetails}" var="row" varStatus="status">
				<tr>
					<td>
					<input type="hidden" name="details[${status.index}].id" value="${row.id }"/>
					<input type="text" name="details[${status.index}].courseCode" value="${row.courseCode }"/><span style="color:red">*</span>
					</td>
					<td>
					<input type="text" size="70" id="date_${status.index}" name="details[${status.index}].classDate" value="${row.classDate }"/><span style="color:red">*</span>
					</td>
					<td>
					<input type="text" name="details[${status.index}].tuitionFee" value="${row.tuitionFee }"/><span style="color:red">*</span>
					</td>
				</tr>
			</c:forEach>
		</table>
		<table class="table_1">
			<tr>
				<td>书费</td>
				<td><input type="text" name="bookFee" id="bookFee" value="${tuitionBill.bookFee }"/></td>
				<td>材料费</td>
				<td><input type="text" name="materialFee" id="materialFee" value="${tuitionBill.materialFee }"/></td>
				<td>总计</td>
				<td><input type="text" name="totalFee" id="totalFee" value="${tuitionBill.totalFee }"/><span style="color:red">*</span></td>
			</tr>
			<tr>
				<td>备注：</td>
				<td colspan="5">
				<textarea rows="10" cols="50" name="remarks" id="remarks">${tuitionBill.remarks }</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="6" align="center"><input type="button" id="updateTuitionBillBtn" value="保存"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="返回" onclick="javascript:history.go(-1);"/></td>
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
