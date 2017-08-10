<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>Crystal工作室 - 学生签到统计</title>
<meta charset="utf-8">
<link type="text/css" href="js/jqueryui/jquery-ui-1.10.2.custom.css" rel="stylesheet"/>
<link rel="stylesheet" media="all" type="text/css" href="js/jqueryui/jquery-ui-timepicker-addon.css" />
<script type="text/javascript" src="js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-1.10.2.custom.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-sliderAccess.js"></script>

<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/autocomplete/jquery.autocomplete.js"></script>
<link rel="stylesheet" href="js/autocomplete/jquery.autocomplete.css"/>
<script type="text/javascript">
$(document).ready(function(){
	//开始时间
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
	//结束时间
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
	
	/** 提交*/
	$("#selectBtn").click(function(){
		if(verifyStudentSignIn()){
			$("#pageForm_ID").submit();
		}
	});
	
	/** 验证学生签到查询条件*/
	function verifyStudentSignIn(){
		var courseCode = $("#courseCode").val();//课程
		var studentName = $("#studentName").val();//姓名
		var datetime = $("#datetime").val();//开始日期
		var datetime2 = $("#datetime2").val();//结束日期
		if((courseCode == null || courseCode == '') && (studentName == null || studentName == '')){
			alert("课程和姓名必须选择其中一项！");
			return false;
		}else if(datetime == null || datetime == ''){
			alert("请选择开始日期！");
			return false;
		}else if(datetime2 == null || datetime2 == ''){
			alert("请选择结束日期！");
			return false;
		}
		return true;
	}
	
});

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
		<form action="student-sign-in-count.html" method="post" id="pageForm_ID" class="niceform">
		<fieldset>
				<dl  style="margin-left:10%;">
					<dt><label for="courseCode">课程:</label></dt>
					<dd>
						<input type="text" size="30" name="courseCode" id="courseCode" autocomplete="off" value="${signIn.courseCode }"/><span style="color:red">*</span>
                     </dd>
                     <dt><label for="courseCode">学生:</label></dt>
					<dd>
						<input type="text" size="30" name="studentName" id="studentName" autocomplete="off" <c:if test="${signIn.studentName!=null && signIn.studentName!='' }">value="${signIn.studentName}"</c:if>/> 
                     </dd>
                 </dl>
                 <dl  style="margin-left:10%;">
					<dt><label for="courseCode">时间:</label></dt>
					<dd>
						<input type="text" size="30" id="datetime" name="startAtStr" readonly="readonly" <c:if test="${signIn.startAt != null }">value="<fmt:formatDate value="${signIn.startAt }" pattern="yyyy-MM-dd"/>"</c:if> placeholder="请选择开始日期"/><span style="color:red">*</span>
                     </dd>
					<dd>
						<input type="text" size="30" id="datetime2" name="endAtStr" readonly="readonly" <c:if test="${signIn.endAt != null }">value="<fmt:formatDate value="${signIn.endAt }" pattern="yyyy-MM-dd"/>"</c:if> placeholder="请选择结束日期"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
                 	<dd style="margin-left:30%;">
                     <a href="javascript:void();" id="selectBtn" class="bt_blue"><span class="bt_blue_lft"></span><strong>查 询</strong><span class="bt_blue_r"></span></a>
                     </dd>
                 </dl>
        </fieldset>
		</form>
		</div>
<!-- 列表 -->
	<table id="rounded-corner" summary="2007 Major IT Companies' Profit">
    <thead>
    	<tr>
        		<th scope="col" class="rounded">学生</th>
        		<th scope="col" class="rounded">课程</th>
            <th scope="col" class="rounded">总课时</th>
            <th scope="col" class="rounded">操作</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${studentSignIns}" var="row">
			<tr>
				<td>${row.studentName }</td>
				<td>${row.courseCode }</td>
				<td>${row.lesson }小时</td>
				<td><a href="select-student-sign-in.html?courseCode=${row.courseCode}&studentName=${row.studentName}&startAtStr=${signIn.startAtStr}&endAtStr=${signIn.endAtStr}" target="_blank">签到时细</a></td>
			</tr>
	</c:forEach>
    </tbody>
    </table>
		
		<!-- 无记录 -->
		<c:if test="${empty studentSignIns}">
		<div class="pagination"><span style="color:red">无查询记录！</span></div>
		</c:if>
		</div>   <!--end of center content -->               
		<div class="clear"></div>
</div> <!--end of main content-->

<!-- footer -->
<jsp:include page="/common/footer.jsp"></jsp:include>
</div>

</body>
</html>
