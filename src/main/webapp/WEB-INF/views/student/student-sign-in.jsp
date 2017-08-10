<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>Crystal工作室 - 学生签到</title>
<meta charset="utf-8">
<link type="text/css" href="js/jqueryui/jquery-ui-1.10.2.custom.css" rel="stylesheet"/>
<link rel="stylesheet" media="all" type="text/css" href="js/jqueryui/jquery-ui-timepicker-addon.css" />
<link rel="stylesheet" href="css/tuition-bill.css" type="text/css" charset="utf-8">
<script type="text/javascript" src="js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-1.10.2.custom.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-sliderAccess.js"></script>
<!-- <link rel="stylesheet" href="/css/common.css"/> -->

<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/autocomplete/jquery.autocomplete.js"></script>
<link rel="stylesheet" href="js/autocomplete/jquery.autocomplete.css"/>
<script type="text/javascript">
$(document).ready(function(){
	//日期
	$("#datetime").datetimepicker({
		showHour:false,
		showMinute:false,
		showSecond:false,
		showTime:false,
		changeMonth: true,
		changeYear: true,
		dateFormat: 'yy-mm-dd',
		timeFormat:'',
		beforeShow: function () { //置顶层
			setTimeout( 
			function () { 
				$('#ui-datepicker-div').css("z-index", 9999999); 
			}, 100); 
		} 
	});
	
	//日期2
	$("#datetime2").datetimepicker({
		showHour:false,
		showMinute:false,
		showSecond:false,
		showTime:false,
		changeMonth: true,
		changeYear: true,
		dateFormat: 'yy-mm-dd',
		timeFormat:'',
		beforeShow: function () { //置顶层
			setTimeout( 
			function () { 
				$('#ui-datepicker-div').css("z-index", 9999999); 
			}, 100); 
		} 
	});
	
	/* 签到全选\全不选 */
	$("#CheckAll").click(function(){
		var flag = $(this).prop("checked");
		$("input[name='subBox']").each(function(){
			$(this).prop("checked",flag);
		});
	});
	
	//提交
	$("#studentSignBtn").click(function(){
		if(verifyStudentSign()){
			$("#studentSignInForm").submit();
		}
	});
});

/** 验证学生签到查询*/
function verifyStudentSign(){
	var courseCode = $("#courseCode").val();//课程代号
	var datetime = $("#datetime").val();//日期
	if(null == courseCode || courseCode == ''){
		alert("请选择课程！");
		return false;
	}else if(null == datetime || datetime == ''){
		alert("日期不能为空！");
		return false;
	}
	return true;
}

/** 验证添加学生签到*/
function verifyAddStudentSign(){
	var studentName_ID = $("#studentName").val();
	var courseCode_ID = $("#courseCode2").val();
	var datetime2 = $("#datetime2").val();
	var time_ID = $("#time_ID").val();
	var lesson_ID = $("#lesson_ID").val();
	var remarks_ID = $("#remarks_ID").val();
	if(studentName_ID == null || studentName_ID ==''){
		alert("姓名不能为空！");
		return false;
	}else if(studentName_ID.length>20){
		alert("姓名限20个字符！");
		return false;
	}else if(courseCode_ID == null || courseCode_ID ==''){
		alert("课程不能为空！");
		return false;
	}else if(courseCode_ID.length>30){
		alert("课程限30个字符！");
		return false;
	}else if(datetime2 == null || datetime2 ==''){
		alert("日期不能为空！");
		return false;
	}else if(time_ID == null || time_ID ==''){
		alert("时间不能为空！");
		return false;
	}else if(lesson_ID == null || lesson_ID ==''){
		alert("课时不能为空！");
		return false;
	}else if(remarks_ID.length>200){
		alert("备注限200个字符！");
		return false;
	}
	return true;
}

//签到结果
if(${studentSignInSuccess==true}){
	alert("成功签到！");
}else if(${studentSignInSuccess==false}){
	alert("已签过到，不能重复签到！");
}
</script>
</head>
<body>
<div id="main_container">
    <jsp:include page="/common/header1.jsp"></jsp:include>
    
<div class="main_content">
	    <jsp:include page="/common/header.jsp"></jsp:include>
	    <div class="center_content">  
		<!-- 查询学生课程 -->
		<div style="padding:left;">
		<form action="student-sign-in-info.html" method="post" id="studentSignInForm" class="niceform">
		<fieldset>
				<dl style="margin-left:10%;">
					<dt><label for="courseCode">课程:</label></dt>
					<dd>
						<input type="text" size="30"  name="courseCode" id="courseCode" autocomplete="off" value="${courseVO.courseCode }"/><span style="color:red">*</span>
                     </dd>
                     <dt><label for="courseCode">日期:</label></dt>
					<dd>
						<input type="text" size="30" id="datetime" name="schoolDateStr" readonly="readonly"  <c:if test="${schoolDateStr !=null && schoolDateStr != '' }">value="${schoolDateStr}"</c:if><c:if test="${schoolDateStr ==null || schoolDateStr == '' }">value="<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd"/>"</c:if> " placeholder="请选择日期"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl style="margin-left:10%;">
					<dt><label for="courseCode">时间:</label></dt>
					<dd>
						<input type="text" size="30"  name="time" id="time" value="${time}" placeholder="如：9:00-12:00"/><span style="color:red">*</span>
                     </dd>
                     <dt><label for="courseCode">课时:</label></dt>
					<dd>
						<input type="text" size="30" id="lesson" name="lesson" value="${lesson}" placeholder="如：3"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
                 	<dd style="margin-left:30%;">
                     <a href="javascript:void();" id="studentSignBtn" class="bt_blue"><span class="bt_blue_lft"></span><strong>查 询</strong><span class="bt_blue_r"></span></a>
                     </dd>
                 </dl>
         </fieldset>
		</form>
		</div>
		<!-- 列表 -->
		<div>
		<form action="batch-student-sign-in.html" method="post">
		<!-- 签到全选 -->
		<!-- <div style="margin-left:2%;"><input type="checkbox" id="CheckAll"/>全选 |&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="批量签到"/></div> -->
		
		<input type="hidden" name="courseCode" value="${courseVO.courseCode}"></input>
		<input type="hidden" name="schoolDateStr" value="${schoolDateStr}"></input>
		<table id="rounded-corner" summary="2007 Major IT Companies' Profit">
	    <thead>
	    	<tr>
	    			<!-- <th scope="col" class="rounded">&nbsp;</th> -->
	        		<th scope="col" class="rounded">姓名</th>
	        		<th scope="col" class="rounded">课程</th>
	            <th scope="col" class="rounded">日期</th>
	            <th scope="col" class="rounded">时间</th>
	            <th scope="col" class="rounded">课时</th>
	            <th scope="col" class="rounded">备注</th>
	            <th scope="col" class="rounded">操 作</th>
	        </tr>
	    </thead>
	    <tbody>
	    <c:forEach items="${studentSignIns}" var="row">
			<tr>
				<%-- <td><input type="checkbox" name="subBox" value="${row.id}"/></td> --%>
				<td>${row.studentName }</td>
				<td>${row.courseCode }</td>
				<td>${schoolDateStr }</td>
				<td>${row.time }</td>
				<td>2.0小时</td>
				<td>${row.remarks }</td>
				<td><a href="student-sign-in.html?status=1&sid=${row.id}&courseCode=${row.courseCode}&schoolDateStr=${schoolDateStr}&time=${time}&lesson=${lesson}">签到</a> | <a href="student-sign-in.html?status=2&sid=${row.id}&courseCode=${row.courseCode}&schoolDateStr=${schoolDateStr}&time=${time}&lesson=${lesson}">缺勤</a></td>
			</tr>
		</c:forEach>
	    </tbody>
	    </table>
		</form>
		</div>
		<!-- 无记录 -->
		<c:if test="${empty studentSignIns}">
		<div class="pagination"><span style="color:red">无查询记录！</span></div>
		</c:if>
		<div style="padding:left;margin-left:5%;">
		<!-- 手动添加学生签到 -->
		<form action="add-student-sign-in.html" method="post" onSubmit="return verifyAddStudentSign()">
		<table class="table_1">
			<tr>
				<td colspan="3" align="center">临时添加学生签到</td>
			</tr>
			<tr>
				<td>姓名：<input type="text" size="26" id="studentName" name="studentName" autocomplete="off"/></td>
				<td>课程：<input type="text" size="26" id="courseCode2" name="courseCode" autocomplete="off"/></td>
				<td>日期：<input type="text" size="26" id="datetime2" name="schoolDateStr" placeholder="请选择日期"/></td>
			</tr>
			<tr>
				<td>时间：<input type="text" size="26" id="time_ID" name="time" placeholder="如：9:00-12:00"/></td>
				<td>课时：<input type="text" size="26" id="lesson_ID" name="lesson" placeholder="如：3"/></td>
				<td>备注：<input type="text" id="remarks_ID" name="remarks" size="35"/></td>
			</tr>
			<tr>
				<td colspan="3" align="center"><input type="submit" value="签 到"/></td>
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
