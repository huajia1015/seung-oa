<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>通知  - Crystal工作室</title>
<meta charset="utf-8">
</head>

<body>
<div>
<div>页面发生错误!</div>
 <div>
   	<% exception.printStackTrace(new PrintWriter(out));%>
   </div>
</div>
</body>
</html>
