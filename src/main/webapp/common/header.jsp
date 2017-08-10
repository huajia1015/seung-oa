<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.inc"%>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/clockp.js"></script>
<script type="text/javascript" src="js/clockh.js"></script> 
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
<div class="menu">
      <ul>
            <li><a href="student-sign-in-page.html" <c:if test="${tab=='1' }">class="current"</c:if> >学生签到</a></li>
            <li><a href="student-sign-in-count-page.html" <c:if test="${tab=='2' }">class="current"</c:if> >学生签到统计</a></li>
            <li>
                	<a href="select-student-sign-in-page.html" <c:if test="${tab=='3' }">class="current"</c:if> >学生签到查询</a> 
            </li>
            <li>
               	<a href="student-manager.html" <c:if test="${tab=='4' }">class="current"</c:if> >学生管理</a>
            </li>
            <li>
                  <a href="course-manager.html" <c:if test="${tab=='5' }">class="current"</c:if> >课程设置</a>
            </li>
            <li><a href="teacher-sign-in-page.html" <c:if test="${tab=='6' }">class="current"</c:if> >老师考勤</a></li>
            <li><a href="teacher-sign-in-count-page.html" <c:if test="${tab=='7' }">class="current"</c:if> >老师考勤统计</a></li>
            <li><a href="select-teacher-sign-in-page.html" <c:if test="${tab=='8' }">class="current"</c:if> >老师考勤查询</a></li>
            <li><a href="tuition-bill-page.html" <c:if test="${tab=='9' }">class="current"</c:if> >学费单管理</a></li>
       </ul>
</div> 

