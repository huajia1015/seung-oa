<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>Crystal工作室 - 添加课程</title>
<meta charset="utf-8">
<link type="text/css" href="js/jqueryui/jquery-ui-1.10.2.custom.css" rel="stylesheet"/>
<link rel="stylesheet" media="all" type="text/css" href="js/jqueryui/jquery-ui-timepicker-addon.css" />
<script type="text/javascript" src="js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-1.10.2.custom.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-sliderAccess.js"></script>
<script type="text/javascript">
$(document).ready(function(){
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
	$("#addCourseBtn").click(function(){
		if(verifyAddCourse()){
			$("#addCourseForm").submit();
		}
	});
	
	/** 验证添加课程*/
	function verifyAddCourse(){
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
		<form action="add-course.html" method="post" id="addCourseForm" class="niceform">
		<div style="padding:left;padding-top:15px;margin-left:30%;font-size:15px;">添加课程</div>
		<fieldset>
				<dl>
					<dt><label for="courseCode">课程代码:</label></dt>
					<dd>
						<input type="text" size="30"  name="courseCode" id="courseCode" autocomplete="off" placeholder="请输入课程代码"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
					<dt><label for="course">课程名称:</label></dt>
					<dd>
						<input type="text" size="30"  name="course" id="course" placeholder="请输入课程名称"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
					<dt><label for="teacher">老师:</label></dt>
					<dd>
						<select size="1"  name="teacher" id="teacher">
							<option value="">请选择</option>
							<c:forEach items="${users}" var="uRow">
							<option value="${uRow.userName }">${uRow.userName }</option>
							</c:forEach>
						</select><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
					<dt><label for="term">学期:</label></dt>
					<dd>
						<input type="text" size="30"   name="term" id="term" placeholder="请输入学期"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                  <dl>
					<dt><label for="schoolDate">日期:</label></dt>
					<dd>
						<input type="text" size="30"  name="schoolDate" id="schoolDate" placeholder="请输入日期 如：周六"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
					<dt><label for="time">时间:</label></dt>
					<dd>
						<input type="text" size="30"  name="time" id="time" placeholder="请输入时间 如：9:00-11:00"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
                 	 <dd>
                     <a href="javascript:void();" id="addCourseBtn" class="bt_blue"><span class="bt_blue_lft"></span><strong>保 存</strong><span class="bt_blue_r"></span></a>
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
