<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>Crystal工作室 - 学费单</title>
<meta charset="utf-8">
<script type="text/javascript" src="js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#addTuition_ID").click(function(){
		window.location = "add-tuition-bill-page.html";
	});
	
	/*var cbox ="";
	$("#manyTuition_ID").click(function(){
		$("input[name='tuitionCheckboxId']").each(function () {
			if ($(this).attr('checked')) {
				cbox+=$(this).val()+",";
			}
        });
		$("#manyTuitionForm").submit();
	});*/
	
	
});

/** 修改学费单返回结果*/
if(${updateTuitionBillResult==true}){
	alert("成功修改学费单！");
}
/** 判断是否选择学费单*/
if(${selectTuitionCheckbox==true}){
	alert("请选择学费单！");
}

</script>
<script type="text/javascript">
	function submitForm(){
	  var x=document.getElementById("manyTuitionForm");
	  x.target="_blank";
	  x.submit();
	}
</script>
</head>
<body>
<div id="main_container">
    <jsp:include page="/common/header1.jsp"></jsp:include>
    
<div class="main_content">
	    <jsp:include page="/common/header.jsp"></jsp:include>
	    <div class="center_content">  
	    <div style="padding:left;margin-left:5%;">
		<form action="tuition-bill.html" method="post" class="niceform" id="pageForm_ID">
		<input type="hidden" id="current_ID" name="current" value="${page.current }"/>
		<table style="width:95%;">
			<tr>
				<td colspan="10" align="center">学费单查询</td>
			</tr>
			<tr>
				<td>学生姓名：</td>
				<td><input type="text" size="15" name="studentName" value="${tuitionBill.studentName}"/></td>
				<td>年级：</td>
				<td>
				<select name="grade">
							<option value="">请选择</option>
							<option value="K3" <c:if test="${tuitionBill.grade == 'K3'}">selected='selected'</c:if>>K3</option>
							<option value="G1" <c:if test="${tuitionBill.grade == 'G1'}">selected='selected'</c:if>>G1</option>
							<option value="G2" <c:if test="${tuitionBill.grade == 'G2'}">selected='selected'</c:if>>G2</option>
							<option value="G3" <c:if test="${tuitionBill.grade == 'G3'}">selected='selected'</c:if>>G3</option>
							<option value="G4" <c:if test="${tuitionBill.grade == 'G4'}">selected='selected'</c:if>>G4</option>
							<option value="G5" <c:if test="${tuitionBill.grade == 'G5'}">selected='selected'</c:if>>G5</option>
							<option value="G6" <c:if test="${tuitionBill.grade == 'G6'}">selected='selected'</c:if>>G6</option>
							<option value="G7" <c:if test="${tuitionBill.grade == 'G7'}">selected='selected'</c:if>>G7</option>
							<option value="G8" <c:if test="${tuitionBill.grade == 'G8'}">selected='selected'</c:if>>G8</option>
							<option value="G9" <c:if test="${tuitionBill.grade == 'G9'}">selected='selected'</c:if>>G9</option>
							<option value="S1" <c:if test="${tuitionBill.grade == 'S1'}">selected='selected'</c:if>>S1</option>
							<option value="S2" <c:if test="${tuitionBill.grade == 'S2'}">selected='selected'</c:if>>S2</option>
							<option value="S3" <c:if test="${tuitionBill.grade == 'S3'}">selected='selected'</c:if>>S3</option>
					</select>
				</td>
				<td>状态：</td>
				<td>
				<select name="tuitionBillStatus">
					<option value="">请选择</option>
					<option value="0" <c:if test="${tuitionBill.tuitionBillStatus == '0'}">selected='selected'</c:if>>未收</option>
					<option value="1" <c:if test="${tuitionBill.tuitionBillStatus == '1'}">selected='selected'</c:if>>已收</option>
					<option value="2" <c:if test="${tuitionBill.tuitionBillStatus == '2'}">selected='selected'</c:if>>部分收讫</option>
				</select>
				</td>
				<td>年月</td>
				<td>
					<select name="startMonth">
						<option value="">请选择</option>
						<c:forEach items="${fullYearMonthArr}" var="row">
						<option value="${row}">${row}</option>
						</c:forEach>
					</select>
				</td>
				<td>至</td>
				<td>
					<select name="endMonth">
						<option value="">请选择</option>
						<c:forEach items="${fullYearMonthArr}" var="row">
						<option value="${row}">${row}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="10" align="center"><input type="submit" value="查询"/>&nbsp;&nbsp;&nbsp;&nbsp;<input id="addTuition_ID" type="button" value="学费单生成"/></td>
			</tr>
		</table>
		</form>
		<form action="many-tuition-bill.html" method="post" id="manyTuitionForm">
		<table id="rounded-corner2">
		<thead>
			<tr>
				<th scope="col" class="rounded">&nbsp;</th>
				<th scope="col" class="rounded">学生</th>
				<th scope="col" class="rounded">收费月份</th>
				<th scope="col" class="rounded">材料费</th>
				<th scope="col" class="rounded">书费</th>
				<th scope="col" class="rounded">总费用</th>
				<th scope="col" class="rounded">备注</th>
				<th scope="col" class="rounded">状态</th>
				<th scope="col" class="rounded">收费日期</th>
				<th scope="col" class="rounded">收费人</th>
				<th scope="col" class="rounded">收费类型</th>
				<th scope="col" class="rounded">操作</th>
			</tr>
		</thead>
			<c:forEach items="${page.list}" var="row">
			<tr>
				<td><input type="checkbox" name="tuitionCheckboxId" value="${row.id }"/></td>
				<td>${row.studentName}</td>
				<td>${row.startMonth }-${row.endMonth }</td>
				<td>${row.materialFee }</td>
				<td>${row.bookFee }</td>
				<td>${row.totalFee }</td>
				<td>
				<span title="${row.remarks}">
				<c:choose>  
				    <c:when test="${fn:length(row.remarks) > 12}">  
				        <c:out value="${fn:substring(row.remarks, 0, 12)}" />  
				    </c:when>  
				   <c:otherwise>  
				      <c:out value="${row.remarks}" />  
				    </c:otherwise>  
				</c:choose> 
				</span>
				</td>
				<td>
				<c:if test="${row.tuitionBillStatus=='0'}">未收</c:if>
				<c:if test="${row.tuitionBillStatus=='1'}">已收</c:if>
				<c:if test="${row.tuitionBillStatus=='2'}">部分收讫</c:if>
				</td>
				<td><fmt:formatDate value="${row.createAt }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${row.opFee }</td>
				<td>
				<c:if test="${row.feeType=='0'}">现金</c:if>
				<c:if test="${row.feeType=='1'}">网付</c:if>
				<c:if test="${row.feeType=='2'}">POS</c:if>
				</td>
				<td><a href="update-tuition-bill-page.html?id=${row.id }">修改</a>&nbsp;&nbsp;<a href="tuition-bill-detail.html?id=${row.id }" target="_blank">详细</a></td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="12" align="center">
				<input id="manyTuition_ID" type="button" value="学费单打印" onclick="submitForm()"/>
				<!--  <button type="submit" formtarget="_blank">学费单打印</button>-->
				</td>
			</tr>
		</table>
		</form>
		</div>
		<!-- 分页 -->
		<c:if test="${!empty page.list }">
		<div class="pagination">
		<pg:page url="tuition-bill.html"></pg:page>
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
