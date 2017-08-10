<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>Crystal工作室 - 修改学生课程</title>
<meta charset="utf-8">
<link type="text/css" href="js/jqueryui/jquery-ui-1.10.2.custom.css" rel="stylesheet"/>
<link rel="stylesheet" media="all" type="text/css" href="js/jqueryui/jquery-ui-timepicker-addon.css" />
<script type="text/javascript" src="js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-1.10.2.custom.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-sliderAccess.js"></script>
<link rel="stylesheet" href="css/common.css"/>

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
	$("#updateStudentCourseBtn").click(function(){
		if(verifyStudentCourse()){
			$("#updateStudentCourseForm").submit();
		}
	});
	
	/** 验证学生课程*/
	function verifyStudentCourse(){
		var studentName = $("#studentName").val();//学生姓名
		var courseCode = $("#courseCode").val();//课程代号
		var datetime = $("#datetime").val();//日期
		if(studentName == null || studentName ==''){
			alert("姓名不能为空！");
			return false;
		}else if(studentName.length>20){
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
		<form action="update-student-course.html" method="post" id="updateStudentCourseForm" class="niceform">
		<input type="hidden" name="id" value="${studentCourse.id}" />
		<input type="hidden" name="studentId" value="${studentCourse.studentId}" />
		<div style="padding:left;padding-top:15px;margin-left:30%;font-size:15px;">修改学生课程</div>
		<fieldset>
				<dl>
					<dt><label for="courseCode">姓名:</label></dt>
					<dd>
						<input type="text" size="30" name="studentName" id="studentName" value="${studentCourse.studentName }"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
					<dt><label for="courseCode">课程:</label></dt>
					<dd>
						<input type="text" size="30" name="courseCode" id="courseCode" autocomplete="off" value="${studentCourse.courseCode}"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                  <dl>
					<dt><label for="courseCode">报名日期:</label></dt>
					<dd>
						<input type="text" size="30" id="datetime" name="startAtStr" value="<fmt:formatDate value="${studentCourse.startAt }" pattern="yyyy-MM-dd"/>" placeholder="请选择报名日期"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
					<dt><label for="courseCode">考试成绩:</label></dt>
					<dd>
						<input type="text" size="30" name="score" value="${studentCourse.score }"/>
                     </dd>
                 </dl>
                 <dl>
                 	<dd>
                     <a href="javascript:void();" id="updateStudentCourseBtn" class="bt_blue"><span class="bt_blue_lft"></span><strong>修 改</strong><span class="bt_blue_r"></span></a>
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
