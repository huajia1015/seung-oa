<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>Crystal工作室 - 老师考勤查询</title>
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
		beforeShow: function () { 
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
		beforeShow: function () { 
			setTimeout( 
			function () { 
				$('#ui-datepicker-div').css("z-index", 9999999); 
			}, 100); 
		} 
	});
	
	//提交
	$("#teacherSignBtn").click(function(){
		if(verifyTeacherSign()){
			$("#pageForm_ID").submit();
		}
	});
	
	/** 验证老师签到查询条件*/
	function verifyTeacherSign(){
		var courseCode = $("#courseCode").val();//课程
		var teacher = $("#teacher").val();//老师
		var datetime = $("#datetime").val();//开始日期
		var datetime2 = $("#datetime2").val();//结束日期
		if((courseCode == null || courseCode == '') && (teacher == null || teacher == '')){
			alert("课程和老师必须选择其中一项！");
			return false;
		}else if(datetime == null || datetime == ''){
			alert("开始日期不为空！");
			return false;
		}else if(datetime2 == null || datetime2 == ''){
			alert("结束日期不为空！");
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
	    <!-- 老师考勤查询 -->
	    <div style="padding:left;">
		<form action="select-teacher-sign-in.html" method="post" id="pageForm_ID" class="niceform">
		<input type="hidden" id="current_ID" name="current" value="${page.current }"/>
		<fieldset>
				<dl style="margin-left:10%;">
					<dt><label for="courseCode">课程:</label></dt>
					<dd>
                            <input type="text" size="30" name="courseCode" id="courseCode" autocomplete="off" value="${signIn.courseCode }" size="20" /><span style="color:red">*</span>
                     </dd>
                     <dt><label for="teacher">老师:</label></dt>
                     <dd><select size="1" name="teacher" id="teacher">
                                	<option value="">请选择</option>
								<c:forEach items="${users}" var="uRow">
									<c:if test="${uRow.userName==signIn.teacher }">
										<option value="${uRow.userName }" selected="selected">${uRow.userName }</option>
									</c:if>
									<c:if test="${uRow.userName!=signIn.teacher }">
										<option value="${uRow.userName }" >${uRow.userName }</option>
									</c:if>
								</c:forEach>
                            </select>
                      </dd>
                 </dl>
                  <dl style="margin-left:10%;">
                  	<dt><label for="datetime">时间:</label></dt>
                      <dd>
                            <input type="text" size="30"  id="datetime" name="startAtStr" readonly="readonly" <c:if test="${signIn.startAt != null }">value="<fmt:formatDate value="${signIn.startAt }" pattern="yyyy-MM-dd"/>"</c:if><c:if test="${signIn.startAt == null }">value="<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd"/>"</c:if> placeholder="请选择开始日期"/>
                     </dd>
                     <dt style="width:10px;"><label for="datetime2">到:</label></dt>
                      <dd>
                            <input type="text" size="30"  id="datetime2" name="endAtStr" readonly="readonly" <c:if test="${signIn.endAt != null }">value="<fmt:formatDate value="${signIn.endAt }" pattern="yyyy-MM-dd"/>"</c:if><c:if test="${signIn.endAt == null }">value="<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd"/>"</c:if> placeholder="请选择结束日期"/>
                     </dd>
                 </dl>
                  <dl>
                  	<dd style="margin-left:30%;">
                  		<a href="javascript:void();" id="teacherSignBtn" class="bt_blue"><span class="bt_blue_lft"></span><strong>查 询</strong><span class="bt_blue_r"></span></a>
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
            <th scope="col" class="rounded">上课人数</th>
            <th scope="col" class="rounded">日期</th>
            <th scope="col" class="rounded">到岗时间</th>
            <th scope="col" class="rounded">上课时间</th>
            <th scope="col" class="rounded">下课时间</th>
            <th scope="col" class="rounded">状态</th>
            <th scope="col" class="rounded">备注</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="row">
	    	<tr>
	    			<td>${row.teacher }</td>
				<td>${row.courseCode }</td>
				<td>
					<c:if test="${row.peopleNum > 0}"><a href="select-student-sign-in.html?status=${row.status}&courseCode=${row.courseCode}&signDateStr=<fmt:formatDate value="${row.signDate}" pattern="yyyy-MM-dd"/>" target="_blank">${row.peopleNum}</a></c:if>
					<c:if test="${row.peopleNum <= 0}">${row.peopleNum}</c:if>
				</td>
				<td><fmt:formatDate value="${row.signDate}" pattern="yyyy-MM-dd"/></td>
				<td>${row.comeTime}</td>
				<td>${row.classOn}</td>
				<td>${row.classOff}</td>
				<td><c:if test="${row.status=='1' }">正常</c:if><c:if test="${row.status=='2' }">迟到</c:if></td>
				<td>${row.remarks}</td>
        </tr>
        </c:forEach>
        </tbody>
</table>
		
		<!-- 分页 -->
		<c:if test="${!empty page.list }">
		<div class="pagination">
		<pg:page url="select-teacher-sign-in.html"></pg:page>
        </div>
		</c:if>
		<!-- 无记录 -->
		<c:if test="${empty page.list}">
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
