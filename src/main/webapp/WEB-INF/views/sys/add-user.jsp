<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>Crystal工作室 - 修改密码</title>
<meta charset="utf-8">
<link type="text/css" href="js/jqueryui/jquery-ui-1.10.2.custom.css" rel="stylesheet"/>
<link rel="stylesheet" media="all" type="text/css" href="js/jqueryui/jquery-ui-timepicker-addon.css" />
<script type="text/javascript" src="js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-1.10.2.custom.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="js/jqueryui/jquery-ui-sliderAccess.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//提交
	$("#addUserBtn").click(function(){
		if(verifyAddUser()){
			$("#addUserForm").submit();
		}
	});
	
	/** 验证密码*/
	function verifyAddUser(){
		var userName = $("#userName").val();
		var password = $("#password").val();//新密码
		var rePassword = $("#rePassword").val();//重复新密码
		if(userName == null || userName == ''){
			alert("用户名不能为空！");
			return false;
		}else if(password == null || password == ''){
			alert("密码不能为空！");
			return false;
		}else if(rePassword == null || rePassword == ''){
			alert("重复密码不能为空！");
			return false;
		}else if(password!=rePassword){
			alert("密码与重复密码不相等！");
			return false;
		}
		return true;
	}
});

if(${AddUserStatus=='100'}){
	alert("用户名不能为空！");
}
if(${AddUserStatus=='101'}){
	alert("密码不能为空！");
}
if(${AddUserStatus=='200'}){
	alert("添加用户成功！");
}
if(${AddUserStatus=='500'}){
	alert("添加用户失败！");
}
</script>
</head>
<body>
<div id="main_container">
	 <jsp:include page="/common/header1.jsp"></jsp:include>
    
<div class="main_content">
	    <jsp:include page="/common/header.jsp"></jsp:include>
	    <div class="center_content">
	    <div style="padding:left;margin-left:25%;">
		<form action="add-user.html" method="post" id="addUserForm" class="niceform">
		<div style="padding:left;padding-top:15px;margin-left:30%;font-size:15px;">添加用户</div>
		<fieldset>
				<dl>
					<dt><label for="userName">用户名称:</label></dt>
					<dd>
						<input type="text" size="30"  name="userName" id="userName" autocomplete="off"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
					<dt><label for="password">密码:</label></dt>
					<dd>
						<input type="password" size="30"  name="password" id="password" placeholder="请输入密码"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
					<dt><label for="rePassword">重复密码:</label></dt>
					<dd>
						<input type="password" size="30"  name="rePassword" id="rePassword" placeholder="重复输入密码"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
					<dt><label for="authority">用户类别:</label></dt>
					<dd>
						<select name="authority">
							<option value="1">管理员</option>
							<option value="2">老师</option>
						</select>
                     </dd>
                 </dl>
                 <dl>
                 	 <dd>
                     <a href="javascript:void();" id="addUserBtn" class="bt_blue"><span class="bt_blue_lft"></span><strong>确定</strong><span class="bt_blue_r"></span></a>
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
