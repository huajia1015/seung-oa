<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>Crystal工作室 - 学生管理</title>
<script type="text/javascript" src="js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/autocomplete/jquery.autocomplete.js"></script>
<link rel="stylesheet" href="js/autocomplete/jquery.autocomplete.css"/>
<meta charset="utf-8">
<script type="text/javascript">
$(document).ready(function(){
/** 提交*/
$("#studentManagerBtn").click(function(){
	if(verifyStudentManager()){
		$("#pageForm_ID").submit();
	}
});

/** 验证学生签到查询条件*/
function verifyStudentManager(){
	var courseCode = $("#courseCode").val();//课程
	var studentName = $("#studentName").val();//姓名
	if((courseCode == null || courseCode == '') && (studentName == null || studentName == '')){
		alert("课程和姓名必须选择其中一项！");
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
		<form action="select-student-course.html" method="post" id="pageForm_ID" class="niceform">
		<input type="hidden" id="current_ID" name="current" value="${page.current }"/>
		<fieldset>
				<dl style="margin-left:10%;">
					<dt><label for="courseCode">课程:</label></dt>
					<dd>
					<input type="text" size="30"  name="courseCode" id="courseCode" autocomplete="off" value="${studentCourse.courseCode }"/><span style="color:red">*</span>
                     </dd>
                     <dt><label for="courseCode">学生:</label></dt>
					<dd>
						<input type="text" size="30" name="studentName" id="studentName" autocomplete="off"  <c:if test="${studentCourse.studentName != null || studentCourse.studentName != ''}">value="${studentCourse.studentName }"</c:if> ></input>
                     </dd>
                 </dl>
                 <dl>
                 	<dd style="margin-left:30%;">
                     <a href="javascript:void();" id="studentManagerBtn" class="bt_blue"><span class="bt_blue_lft"></span><strong>查 询</strong><span class="bt_blue_r"></span></a>
                     </dd>
                 </dl>
         </fieldset>
		</form>
		</div>
		
	<!-- 列表 -->
	<table id="rounded-corner" summary="2007 Major IT Companies' Profit">
    <thead>
    	<tr>
        		<th scope="col" class="rounded">姓名</th>
        		<th scope="col" class="rounded">课程</th>
            <th scope="col" class="rounded">报名日期</th>
            <th scope="col" class="rounded">考试成绩</th>
            <th scope="col" class="rounded">操 作</th>
        </tr>
    </thead>
    <tbody>
    		<c:forEach items="${page.list}" var="row">
			<tr>
				<td><a href="select-student-by-id.html?id=${row.studentId }" target="_blank">${row.studentName }</a></td>
				<td>${row.courseCode }</td>
				<td><fmt:formatDate value="${row.startAt }" pattern="yyyy-MM-dd"/></td>
				<td>${row.score }</td>
				<td><a href="update-student-course-page.html?id=${row.id}">修改</a> | <a href="del-student-course.html?id=${row.id}">删除</a></td>
			</tr>
		</c:forEach>
    </tbody>
    </table>
		
		<!-- 分页 -->
		<c:if test="${!empty page.list }">
		<div class="pagination">
		<pg:page url="select-student-course.html"></pg:page>
		</div>
		</c:if>
		<!-- 无记录 -->
		<c:if test="${empty page.list}">
		<div class="pagination"><span style="color:red">无查询记录！</span></div>
		</c:if>
		<div style="float:left;margin-left:35%">
		<a href="add-student-page.html" class="bt_blue"><span class="bt_blue_lft"></span><strong>添加学生基本信息</strong><span class="bt_blue_r"></span></a>
		<a href="add-student-course-page.html" class="bt_blue"><span class="bt_blue_lft"></span><strong>添加学生课程</strong><span class="bt_blue_r"></span></a>
		</div>
		</div>   <!--end of center content -->               
		<div class="clear"></div>
</div> <!--end of main content-->

<!-- footer -->
<jsp:include page="/common/footer.jsp"></jsp:include>
</div>

</body>
</html>
