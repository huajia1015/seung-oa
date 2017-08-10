<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>Crystal工作室 - 修改课程</title>
<meta charset="utf-8">
<link type="text/css" href="js/jqueryui/jquery-ui-1.10.2.custom.css" rel="stylesheet"/>
<link rel="stylesheet" media="all" type="text/css" href="js/jqueryui/jquery-ui-timepicker-addon.css" />
<script type="text/javascript" src="js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-1.10.2.custom.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-sliderAccess.js"></script>
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
	
	//提交
	$("#updateCourseBtn").click(function(){
		if(verifyCourse()){
			$("#updateCourseForm").submit();
		}
	});
	
	/** 验证课程*/
	function verifyCourse(){
		var courseCode = $("#courseCode").val();//课程代号
		var course = $("#course").val();//课程
		var teacher = $("#teacher").val();//老师
		var term = $("#term").val();//学期
		var schoolDate = $("#schoolDate").val();//日期 
		var time = $("#time").val();//时间
		if(courseCode == null || courseCode == ''){
			alert("课程代号不能为空！");
			return false;
		}else if(courseCode.length>20){
			alert("课程代号限20个字符！");
			return false;
		}else if(course == null || course == ''){
			alert("课程名称不能为空！");
			return false;
		}else if(course.length>20){
			alert("课程名称限20个字符！");
			return false;
		}else if(teacher == null || teacher == ''){
			alert("请选择课程老师！");
			return false;
		}else if(term == null || term == ''){
			alert("学期不能为空！");
			return false;
		}else if(term.length>20){
			alert("学期限20个字符！");
			return false;
		}else if(schoolDate == null || schoolDate == ''){
			alert("日期不能为空！");
			return false;
		}else if(schoolDate.length>20){
			alert("日期限20个字符！");
			return false;
		}else if(time == null || time == ''){
			alert("时间不能为空！");
			return false;
		}else if(time.length>20){
			alert("时间限20个字符！");
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
	    <div style="padding:left;margin-left:25%;">
		<form action="update-course.html" method="post" id="updateCourseForm" class="niceform">
		<input type="hidden" name="id" value="${course.id }"/>
		<div style="padding:left;padding-top:15px;margin-left:30%;font-size:15px;">修改课程</div>
		<fieldset>
				<dl>
					<dt><label for="courseCode">课程代码:</label></dt>
					<dd>
						<input type="text" size="30" name="courseCode" id="courseCode" value="${course.courseCode }"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
					<dt><label for="courseCode">课程名称:</label></dt>
					<dd>
						<input type="text" size="30" name="course" id="course" value="${course.course }"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
					<dt><label for="courseCode">老师:</label></dt>
					<dd>
						<select size="1" name="teacher" id="teacher">
						<c:forEach items="${users}" var="uRow">
						<c:if test="${uRow.userName == course.teacher }">
						<option value="${uRow.userName }" selected="selected">${uRow.userName }</option>
						</c:if>
						<c:if test="${uRow.userName != course.teacher }">
						<option value="${uRow.userName }">${uRow.userName }</option>
						</c:if>
						</c:forEach>
						</select>
					<span style="color:red">*</span>
                     </dd>
                 </dl>
                  <dl>
					<dt><label for="courseCode">学期:</label></dt>
					<dd>
						<input type="text" size="30" name="term" id="term" value="${course.term }"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                  <dl>
					<dt><label for="courseCode">日期:</label></dt>
					<dd>
						<input type="text"  size="30" name="schoolDate" id="schoolDate" value="${course.schoolDate}"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
					<dt><label for="courseCode">时间:</label></dt>
					<dd>
						<input type="text" size="30" name="time" id="time" value="${course.time }"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
                 	<dd>
                     <a href="javascript:void();" id="updateCourseBtn" class="bt_blue"><span class="bt_blue_lft"></span><strong>保 存</strong><span class="bt_blue_r"></span></a>
                     </dd>
                 </dl>
        </fieldset>
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
