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
	$("#modifyPwdBtn").click(function(){
		if(verifyModifyPwd()){
			$("#modifyPwdForm").submit();
		}
	});
	
	/** 验证密码*/
	function verifyModifyPwd(){
		var password = $("#password").val();//旧密码
		var newPassword = $("#newPassword").val();//新密码
		var reNewPassword = $("#reNewPassword").val();//重复新密码
		if(password == null || password == ''){
			alert("旧密码不能为空！");
			return false;
		}else if(newPassword == null || newPassword == ''){
			alert("新密码不能为空！");
			return false;
		}else if(reNewPassword == null || reNewPassword == ''){
			alert("重复新密码不能为空！");
			return false;
		}else if(newPassword!=reNewPassword){
			alert("新密码与重复密码不相等！");
			return false;
		}
		return true;
	}
});

/** 修改密码返回结果*/
if(${ModifyPwdStatus=='100'}){
	alert("旧密码不正确！");
}
if(${ModifyPwdStatus=='101'}){
	alert("新密码与重复新密码不一致！");
}
if(${ModifyPwdStatus=='500'}){
	alert("修改密码失败！");
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
		<form action="modify-pwd.html" method="post" id="modifyPwdForm" class="niceform">
		<div style="padding:left;padding-top:15px;margin-left:30%;font-size:15px;">修改密码</div>
		<fieldset>
				<dl>
					<dt><label for="password">旧密码:</label></dt>
					<dd>
						<input type="password" size="30"  name="password" id="password" autocomplete="off" placeholder="请输入原始密码"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
					<dt><label for="newPassword">新密码:</label></dt>
					<dd>
						<input type="password" size="30"  name="newPassword" id="newPassword" placeholder="请输入新密码"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
					<dt><label for="reNewPassword">重复新密码:</label></dt>
					<dd>
						<input type="password" size="30"  name="reNewPassword" id="reNewPassword" placeholder="重复输入新密码"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
                 	 <dd>
                     <a href="javascript:void();" id="modifyPwdBtn" class="bt_blue"><span class="bt_blue_lft"></span><strong>修改密码</strong><span class="bt_blue_r"></span></a>
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
