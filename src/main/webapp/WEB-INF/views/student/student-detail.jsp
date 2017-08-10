<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>Crystal工作室 - 学生详细信息</title>
<meta charset="utf-8">
<script type="text/javascript" src="js/jquery/jquery-1.7.2.js"></script>
</head>
<body>
<div id="main_container">
    <jsp:include page="/common/header1.jsp"></jsp:include>
    
<div class="main_content">
	    <jsp:include page="/common/header.jsp"></jsp:include>
	    <div class="center_content"> 
	    <div style="padding:left;padding-top:15px;margin-left:45%;font-size:15px;">学生详细信息</div>
	<table id="rounded-corner" summary="2007 Major IT Companies' Profit">
    <thead>
			<tr>
				<td>姓名：</td>
				<td>${student.studentName }</td>
			</tr>
			<tr>
				<td>英文名：</td>
				<td>${student.enName }</td>
			</tr>
			<tr>
				<td>学校：</td>
				<td>${student.school }</td>
			</tr>
			<tr>
				<td>家庭住址：</td>
				<td>${student.address }</td>
			</tr>
			<tr>
				<td>联系方式1：</td>
				<td>${student.contactInfo }</td>
			</tr>
			<tr>
				<td>联系方式2：</td>
				<td>${student.contactInfo2 }</td>
			</tr>
			<tr>
				<td>QQ：</td>
				<td>${student.qq }</td>
			</tr>
    </thead>
    <tbody>
    </tbody>
    </table>
	</div>   <!--end of center content -->               
		<div class="clear"></div>
</div> <!--end of main content-->

<!-- footer -->
<jsp:include page="/common/footer.jsp"></jsp:include>
</div>

</body>
</html>
