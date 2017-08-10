<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>登录</title>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#loginBtn").click(function(){
		if(verifyLogin()){
			$("#loginForm").submit();//登录提交
		}
	});
});

/** 换验证码 */
function changeCode(){
	$("#checkCodeImg").attr('src',"loginCheckCode?_sed="+new Date().getTime());
	$("#checkCode").val('');
}

/** 验证登录 */
function verifyLogin(){
	var userName = $("#userName").val();//用户名
	var password = $("#password").val();//密码
	var checkCode = $("#checkCode").val();//验证码
	if(null == userName || userName == ''){
		alert("用户名不能为空！");
		return false;
	}else if(userName.length > 20){
		alert("用户名限20个字符！");
		return false;
	}else if(null == password || password == ''){
		alert("密码不能为空！");
		return false;
	}else if(password.length > 18){
		alert("密码长度限18个字符！");
		return false;
	}else if(null == checkCode || checkCode == ''){
		alert("验证码不能为空！");
		return false;
	}else if(checkCode.length>4){
		alert("超过验证码长度！");
		return false;
	}
	return true;
	
}

/** 验证用户登录信息 */
var LoginStatus = <%=request.getParameter("LoginStatus") %> ;
if(LoginStatus == '100'){
	alert("验证码不能为空！");
}
if(LoginStatus == '101'){
	alert("验证码错误！");
}
if(LoginStatus == '102'){
	alert("用户名不能为空！");
}
if(LoginStatus == '103'){
	alert("密码不能为空！");
}
if(LoginStatus == '104'){
	alert("用户名或密码错误！");
}

//修改密码结果
var ModifyPwdStatus = <%=request.getParameter("ModifyPwdStatus") %> ;
if(ModifyPwdStatus=='200'){
	alert("修改密码成功！请重新登录系统！");
}
</script>

<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/ddaccordion.js"></script>
<script type="text/javascript">
ddaccordion.init({
	headerclass: "submenuheader", //Shared CSS class name of headers group
	contentclass: "submenu", //Shared CSS class name of contents group
	revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
	mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
	collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [], //index of content(s) open by default [index1, index2, etc] [] denotes no content
	onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
	animatedefault: false, //Should contents open by default be animated into view?
	persiststate: true, //persist state of opened contents within browser session?
	toggleclass: ["", ""], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["suffix", "<img src='images/plus.gif' class='statusicon' />", "<img src='images/minus.gif' class='statusicon' />"], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
	oninit:function(headers, expandedindices){ //custom code to run when headers have initalized
		//do nothing
	},
	onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
		//do nothing
	}
})
</script>

<script type="text/javascript" src="js/jconfirmaction.jquery.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		$('.ask').jConfirmAction();
	});
	
</script>

<script language="javascript" type="text/javascript" src="js/niceforms.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="css/niceforms-default.css" />

</head>
<body>
<div id="main_container">
	<div class="header_login">
    <div class="logo"><a href="#"><img src="images/logo.gif" alt="" title="" border="0" /></a></div>
    </div>
         <div class="login_form">
         <h3>系统登录</h3>
         <a href="#" class="forgot_pass">Forgot password</a> 
         <form action="login.html" method="post" class="niceform" id="loginForm">
                <fieldset>
                    <dl>
                        <dt><label for="userName" >用户名:</label></dt>
                        <dd style="width:350px;"><input type="text" name="userName" id="userName" size="54" autocomplete="off"/></dd>
                    </dl>
                    <dl>
                        <dt><label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码:</label></dt>
                        <dd style="width:350px;"><input type="password" name="password" id="password" size="54" autocomplete="off"/></dd>
                    </dl>
                   <dl>
                        	<dt><label for="checkCode">验证码:</label></dt>
                        	<dd style="width:350px;"><input type="text" id="checkCode" name="checkCode" placeholder="验证码" size="24" autocomplete="off"/>
                        	<a href="javascript:changeCode()">
	             		<img id="checkCodeImg" width="80" height="28" style="vertical-align: bottom" src="./loginCheckCode?_sed=<%=new java.util.Date().getTime()%>" />
	             		</a>
	             		</dd>
                    </dl>
                     <dl class="submit">
                     	<dd>
	                    <input type="button" id="loginBtn"  value="登 录" />
	                    </dd>
                     </dl>
                </fieldset>
         </form>
         </div>  
    <div class="footer_login">
    	<div class="left_footer_login">IN ADMIN PANEL | Powered by <a href="http://indeziner.com">INDEZINER</a></div>
    	<div class="right_footer_login"><a href="http://indeziner.com"><img src="images/indeziner_logo.gif" alt="" title="" border="0" /></a></div>
    </div>
</div>		
</body>
</html>