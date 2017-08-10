<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>Crystal工作室 - 老师考勤</title>
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
	
	/* 初始化到岗时间*/
	function initSignTime(){
		var signHourArr = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23];
		for(var x in signHourArr){
			//到岗时钟
			$("#signHour").append("<option value='"+signHourArr[x]+"'>"+signHourArr[x]+"</option>");
			//上课时钟
			$("#classOnHour").append("<option value='"+signHourArr[x]+"'>"+signHourArr[x]+"</option>");
			//下课时钟
			$("#classOffHour").append("<option value='"+signHourArr[x]+"'>"+signHourArr[x]+"</option>");
		}
		//到岗分钟
		var signMinutesArr = ['00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32',33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60];
		for(var x in signMinutesArr){
			$("#signMinutes").append("<option value='"+signMinutesArr[x]+"'>"+signMinutesArr[x]+"</option>");
		}
		//上/下课分钟
		var minutesArr = ['00',10,20,30,40,50];
		//上课分钟
		for(var x in minutesArr){
			$("#classOnMinutes").append("<option value='"+minutesArr[x]+"'>"+minutesArr[x]+"</option>");
		}
		//下课分钟
		for(var x in minutesArr){
			$("#classOffMinutes").append("<option value='"+minutesArr[x]+"'>"+minutesArr[x]+"</option>");
		}
	}
	initSignTime();
	
	/* 老师考勤*/
	$("#addSignBtn").click(function(){
		if(verifyAddTeacherSign()){
			$("#addTeacherSignForm").submit();//提交
		}
	});
	
	/* 验证老师考勤 */
	function verifyAddTeacherSign(){
		var courseCode = $("#courseCode").val();//课程
		var teacher = $("#teacher").val();//老师
		//到岗时间
		var signHour = $("#signHour").val();
		var signMinutes = $("#signMinutes").val();
		//上课时间
		var classOnHour = $("#classOnHour").val();
		var classOnMinutes = $("#classOnMinutes").val();
		//下课时间
		var classOffHour = $("#classOffHour").val();
		var classOffMinutes = $("#classOffMinutes").val();
		if(courseCode==null || courseCode==''){
			alert("请选择课程！");
			return false;
		}else if(teacher==null || teacher==''){
			alert("请选择老师！");
			return false;
		}else if(signHour==null || signHour =='' || signMinutes==null || signMinutes==''){
			alert("请选择到岗时间！");
			return false;
		}else if(classOnHour==null || classOnHour =='' || classOnMinutes==null || classOnMinutes==''){
			alert("请选择上课时间！");
			return false;
		}else if(classOffHour==null || classOffHour =='' || classOffMinutes==null || classOffMinutes==''){
			alert("请选择下课时间！");
			return false;
		}
		//组装时间格式 eg. 8:30
		$("#comeTimeID").val(signHour+':'+signMinutes);
		$("#classOnID").val(classOnHour+':'+classOnMinutes);
		$("#classOffID").val(classOffHour+':'+classOffMinutes);
		return true;
	}
	
	/** 添加老师考勤返回结果*/
	if(${addTeacherSignInSuccess==true}){
		alert("成功添加考勤！");
	}else if(${addTeacherSignInSuccess=="existCourse"}){
		alert("该课程不存在，请您重新输入！");
	}else if(${addTeacherSignInSuccess=="existTeacherSignIn"}){
		alert("当前考勤已存在，不能重复添加考勤！");
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
	    <div style="padding:left;margin-left:20%;">
		<form action="teacher-sign-in.html" method="post" id="addTeacherSignForm" class="niceform">
		<input type="hidden" name="comeTime" value="" id="comeTimeID"/>
		<input type="hidden" name="classOn" value="" id="classOnID"/>
		<input type="hidden" name="classOff" value="" id="classOffID"/>
		<div style="padding:left;padding-top:15px;margin-left:35%;font-size:15px;">添加老师考勤</div>
		<fieldset>
                    <dl>
                        <dt><label for="email">课程:</label></dt>
                        <dd><input type="text" size="30"  name="courseCode" id="courseCode" autocomplete="off"/><span style="color:red">*</span></dd>
                    </dl>
                     <dl>
                        <dt><label for="email">老师:</label></dt>
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
                        <dt><label for="email">日期:</label></dt>
                        <dd><input type="text"  id="datetime" name="signDateStr" readonly="readonly" value="<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd"/>" placeholder="请选择日期"/><span style="color:red">*</span></dd>
                    </dl>
                     <dl>
                        <dt><label for="email">到岗时间:</label></dt>
                        <dd>
                        		<select size="1" id="signHour"><option value="">请选择时</option></select>
                        </dd>
                         <dd>
                        		<select size="1" id="signMinutes" ><option value="">请选择分</option></select>
                        </dd>
                    </dl>
                    <dl>
                        <dt><label for="email">上课时间:</label></dt>
                        <dd>
                        		<select  size="1"  id="classOnHour" ><option value="">请选择时</option></select>
                        </dd>
                        <dd>
                        		<select size="1" id="classOnMinutes"><option value="">请选择分</option></select>
                        </dd>
                    </dl>
                    <dl>
                        <dt><label for="email">下课时间:</label></dt>
                        <dd>
                        		<select  size="1"  id="classOffHour"><option value="">请选择时</option></select>
                        </dd>
                        <dd>
                        		<select size="1" id="classOffMinutes"><option value="">请选择分</option></select>
                        </dd>
                    </dl>
                     <dl>
                        <dt><label for="email">状态:</label></dt>
                        <dd>
                        <select size="1"  name="status">
							<option value="1">正常</option>
							<option value="2">迟到</option>
						</select>
                        </dd>
                    </dl>
                    <dl>
                        <dt><label for="email">备注:</label></dt>
                        <dd style="width:350px;">
                        <input type="text" name="remarks" size="53"/>
                        </dd>
                    </dl>
                    <dl>
                    	<dd style="margin-left:15%;">
                    		<a href="javascript:void();" id="addSignBtn" class="bt_blue"><span class="bt_blue_lft"></span><strong>新增考勤</strong><span class="bt_blue_r"></span></a>
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
