<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<!-- header -->
<div class="header">
	    <div class="logo"><a href="index.html"><img src="images/logo.gif" alt="" title="" border="0" /></a></div>
	    <div class="right_header">欢迎 <c:out value="${sessionScope.CRYSTAL_USER_SESSION.userName }"></c:out> | <a href="modify-pwd-page.html">修改密码</a>| <a href="add-user-page.html">添加用户</a> | <a href="logout.html" class="logout">注销</a></div>
	    <div id="clock_a"></div>
</div>

