<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>Crystal工作室 - 添加学生</title>
<meta charset="utf-8">
<script type="text/javascript" src="js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//提交
	$("#addStudentBtn").click(function(){
		if(verifyAddStudent()){
			$("#addStudentForm").submit();
		}
	});
	
	/** 验证添加学生*/
	function verifyAddStudent(){
		var studentName = $("#studentName").val();//姓名
		var school = $("#school").val();//学校
		var contactInfo = $("#contactInfo").val();//联系方式1
		var grade = $("#grade").val();//年级
		if(studentName == null || studentName ==''){
			alert("姓名不能为空！");
			return false;
		}else if(studentName.length>20){
			alert("姓名限20个字符！");
			return false;
		}else if(school == null || school ==''){
			alert("学校不能为空！");
			return false;
		}else if(school.length>30){
			alert("学校限30个字符！");
			return false;
		}else if(grade == null || grade ==''){
			alert("年级不能为空！");
			return false;
		}else if(contactInfo == null || contactInfo ==''){
			alert("联系方式1不能为空！");
			return false;
		}else if(contactInfo.length>50){
			alert("联系方式1限50个字符！");
			return false;
		}
		return true;
	}
	
});

/** 添加学生返回结果*/
if(${returnResult==true}){
	alert("成功添加学生信息！");
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
		<form action="add-student.html" method="post" id="addStudentForm" class="niceform">
		<div style="padding:left;padding-top:15px;margin-left:30%;font-size:15px;">添加学生</div>
		<fieldset>
				<dl>
					<dt><label>姓名:</label></dt>
					<dd>
						<input type="text" size="30" name="studentName" id="studentName"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
					<dt><label>英文名:</label></dt>
					<dd>
						<input type="text" size="30" name="enName"/>
                     </dd>
                 </dl>
                  <dl>
					<dt><label>学校:</label></dt>
					<dd>
						<input type="text" size="30" name="school" id="school"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
					<dt><label>年级:</label></dt>
					<dd>
						<select size="1" name="grade" id="grade">
							<option value="">请选择</option>
							<option value="K3">K3</option>
							<option value="G1">G1</option>
							<option value="G2">G2</option>
							<option value="G3">G3</option>
							<option value="G4">G4</option>
							<option value="G5">G5</option>
							<option value="G6">G6</option>
							<option value="G7">G7</option>
							<option value="G8">G8</option>
							<option value="G9">G9</option>
							<option value="S1">S1</option>
							<option value="S2">S2</option>
							<option value="S3">S3</option>
					</select><span style="color:red">*</span>
                     </dd>
                 </dl>
                  <dl>
					<dt><label>家庭住址:</label></dt>
					<dd>
						<input type="text" size="30" name="address"/>
                     </dd>
                 </dl>
                 <dl>
					<dt><label>联系方式1:</label></dt>
					<dd>
						<input type="text" size="30" name="contactInfo" id="contactInfo"/><span style="color:red">*</span>
                     </dd>
                 </dl>
                 <dl>
					<dt><label>联系方式2:</label></dt>
					<dd>
						<input type="text" size="30" name="contactInfo2"/>
                     </dd>
                 </dl>
                 <dl>
					<dt><label>QQ:</label></dt>
					<dd>
						<input type="text" size="30" name="qq"/>
                     </dd>
                 </dl>
                 <dl>
                 	<dd>
                     <a href="javascript:void();" id="addStudentBtn" class="bt_blue"><span class="bt_blue_lft"></span><strong>保 存</strong><span class="bt_blue_r"></span></a>
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
