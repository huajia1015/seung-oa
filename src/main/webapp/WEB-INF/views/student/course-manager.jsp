<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>Crystal工作室 - 课程管理</title>
<script type="text/javascript" src="js/jquery/jquery-1.7.2.js"></script>
<meta charset="utf-8">
</head>
<body>
<div id="main_container">
    <jsp:include page="/common/header1.jsp"></jsp:include>
    
<div class="main_content">
	    <jsp:include page="/common/header.jsp"></jsp:include>
	    <div class="center_content"> 
	<div style="padding:3px;">
	<table id="rounded-corner" summary="2007 Major IT Companies' Profit">
    <thead>
    	<tr>
        		<th scope="col" class="rounded">课程代码</th>
        		<th scope="col" class="rounded">课程名称</th>
            <th scope="col" class="rounded">老师</th>
            <th scope="col" class="rounded">学期</th>
            <th scope="col" class="rounded">日期</th>
            <th scope="col" class="rounded">时间</th>
            <th scope="col" class="rounded">操&nbsp;&nbsp;作</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="row">
			<tr>
				<td>${row.courseCode }</td>
				<td>${row.course }</td>
				<td>${row.teacher }</td>
				<td>${row.term }</td>
				<td>${row.schoolDate }</td>
				<td>${row.time }</td>
				<td><a href="update-course-page.html?id=${row.id }">修改</a> | <a href="del-course.html?id=${row.id }">删除</a></td>
			</tr>
			</c:forEach>
    </tbody>
    </table>
    </div>
    
		<div style="float:left;margin-left:45%">
		<a href="add-course-page.html" class="bt_blue"><span class="bt_blue_lft"></span><strong>添 加</strong><span class="bt_blue_r"></span></a>
		</div>
		<!-- 分页 -->
		<c:if test="${!empty page.list }">
		<div class="pagination">
		<form action="course-manager.html" method="post" id="pageForm_ID">
		<input type="hidden" id="current_ID" name="current" value="${page.current }"/>
		<pg:page url="course-manager.html"></pg:page>
		</form>
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
