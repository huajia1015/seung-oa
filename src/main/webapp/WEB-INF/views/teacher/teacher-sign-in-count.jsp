<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>Crystal工作室 - 老师考勤统计</title>
<meta charset="utf-8">
<link type="text/css" href="js/jqueryui/jquery-ui-1.10.2.custom.css" rel="stylesheet"/>
<link rel="stylesheet" media="all" type="text/css" href="js/jqueryui/jquery-ui-timepicker-addon.css" />
<script type="text/javascript" src="js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-1.10.2.custom.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-sliderAccess.js"></script>
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
	
	//提交
	$("#teachSignBtn").click(function(){
		if(verifySignCount()){
			$("#pageForm_ID").submit();
		}
	});
	
	/* 验证老师考勤统计*/
	function verifySignCount(){
		var teacher = $("#teacher").val();//老师
		var datetime = $("#datetime").val();//开始日期
		var datetime2 = $("#datetime2").val();//结束日期
		if(teacher==null || teacher==''){
			alert("请选择老师！");
			return false;
		}else if(datetime==null || datetime==''){
			alert("请选择开始日期！");
			return false;
		}else if(datetime2==null || datetime2==''){
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
		<!-- 老师考勤统计 -->
		<div style="padding:left;margin-left:20%;">
		<form action="teacher-sign-in-count.html" method="post" id="pageForm_ID" class="niceform">
		<fieldset>
				<dl>
					<dt><label for="teacher">老师:</label></dt>
					<dd>
					 	<select size="1" name="teacher" id="teacher">
							<option value="">请选择</option>
							<c:forEach items="${users}" var="uRow">
								<c:if test="${uRow.userName == signIn.teacher }">
								<option value="${uRow.userName }" selected="selected">${uRow.userName }</option>
								</c:if>
								<c:if test="${uRow.userName != signIn.teacher }">
								<option value="${uRow.userName }">${uRow.userName }</option>
								</c:if>
							</c:forEach>
						</select>
                     </dd>
                 </dl>
                 <dl>
					<dt><label for="startAtStr">时间:</label></dt>
					<dd>
                            <input type="text" id="datetime" size="30" name="startAtStr" readonly="readonly" <c:if test="${signIn.startAt != null }">value="<fmt:formatDate value="${signIn.startAt }" pattern="yyyy-MM-dd"/>"</c:if> placeholder="请选择开始日期"/><span style="color:red">*</span>
                     </dd>
                     <dd>
                            <input type="text" id="datetime2" size="30" name="endAtStr" readonly="readonly" <c:if test="${signIn.endAt != null }">value="<fmt:formatDate value="${signIn.endAt }" pattern="yyyy-MM-dd"/>"</c:if> placeholder="请选择结束日期"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
                 	<dd style="margin-left:10%;">
                 		<a href="javascript:void();" id="teachSignBtn" class="bt_blue"><span class="bt_blue_lft"></span><strong>查 询</strong><span class="bt_blue_r"></span></a>
                 	</dd>
                 </dl>
        </fieldset>
		</form>
		</div>
		<!-- 列表 -->
	<table id="rounded-corner" summary="2007 Major IT Companies' Profit">
    <thead>
    	<tr>
        		<th scope="col" class="rounded">老师</th>
        		<th scope="col" class="rounded">课程</th>
            <th scope="col" class="rounded">总课时</th>
            <th scope="col" class="rounded">操作</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${teacherSignInCount}" var="row">
			<tr>
				<td>${row.teacher }</td>
				<td>${row.courseCode }</td>
				<td>${row.lesson }小时</td>
				<td><a href="select-teacher-sign-in.html?courseCode=${row.courseCode}&teacher=${row.teacher}&startAtStr=${signIn.startAtStr}&endAtStr=${signIn.endAtStr}" target="_blank">考勤时细</a></td>
			</tr>
	</c:forEach>
    </tbody>
    </table>
		<!-- 无记录 -->
		<c:if test="${empty teacherSignInCount}">
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
