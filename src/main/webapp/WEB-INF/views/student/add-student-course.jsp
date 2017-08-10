<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>Crystal工作室 - 为学生添加课程</title>
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
	$("#addStudentCourseBtn").click(function(){
		if(verifyAddStudentCourse()){
			$("#addStudentCourseForm").submit();
		}
	});
	
	/** 验证添加学生*/
	function verifyAddStudentCourse(){
		var studentNameStr = $("#studentNameStr").val();//学生姓名
		var courseCode = $("#courseCode").val();//课程代号
		var datetime = $("#datetime").val();//报名日期
		if(studentNameStr == null || studentNameStr ==''){
			alert("姓名不能为空！");
			return false;
		}else if(studentNameStr.length>20){
			alert("姓名限20个字符！");
			return false;
		}else if(courseCode == null || courseCode ==''){
			alert("课程不能为空！");
			return false;
		}else if(courseCode.length>20){
			alert("课程限20个字符！");
			return false;
		}else if(datetime == null || datetime ==''){
			alert("报名日期不能为空！");
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
		<form action="add-student-course.html" method="post" id="addStudentCourseForm" class="niceform">
		<div style="padding:left;padding-top:15px;margin-left:30%;font-size:15px;">添加学生课程</div>
		<fieldset>
				<dl>
					<dt><label for="courseCode">姓名:</label></dt>
					<dd>
						<select size="1" name="studentNameStr" id="studentNameStr">
						<option value="">请选择</option>
							<c:forEach items="${students}" var="sRow">
							<option value="${sRow.id}_${sRow.studentName }">${sRow.studentName }</option>
							</c:forEach>
						</select><span style="color:red">*</span>
                     </dd>
                   </dl>
                   <dl>
                     <dt><label for="courseCode">课程:</label></dt>
					<dd>
						<input type="text" size="30" name="courseCode" id="courseCode" autocomplete="off"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
                     <dt><label for="courseCode">报名日期:</label></dt>
					<dd>
						<input type="text" size="30" id="datetime" name="startAtStr" placeholder="请选择报名日期"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
                     <dt><label for="courseCode">考试成绩:</label></dt>
					<dd style="width:350px;">
						<input type="text"  size="50" name="score" id="score"/>
                     </dd>
                 </dl>
                 <dl>
					<dd>
                     <a href="javascript:void();" id="addStudentCourseBtn" class="bt_blue"><span class="bt_blue_lft"></span><strong>保 存</strong><span class="bt_blue_r"></span></a>
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
