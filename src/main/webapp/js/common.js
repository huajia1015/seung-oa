$(document).ready(function(){
	/* 自动加载课程*/
	$("#courseCode").autocomplete("ajax-course.html", {
		httpMethod: "POST", 
		width: 150,
		max: 20,
		highlight: false,
		matchSubset:false,
		cacheLength:1,
		scroll: true,
		scrollHeight: 300,
		dataType:	"json",
		minchar: 1,//最小响应字符数量
		selectFirst:false,//默认不选中第1条
		//autoFocus:true,
		extraParams:{course : function(){ return $("#courseCode").val();}},
		parse : function(data) {
			if(!data) return [];
			var rows = [];
			for ( var i = 0; i < data.length; i++) {
				rows[rows.length] = {
					data : data[i],
					value : data[i].str,
					result : data[i].courseCode
				};
			}
			return rows;
		},
		formatItem : function(row, i, max) {
			return row.courseCode;
		}
	});
	
	/* 自动加载课程2*/
	$("#courseCode2").autocomplete("ajax-course.html", {
		httpMethod: "POST", 
		width: 150,
		max: 20,
		highlight: false,
		matchSubset:false,
		cacheLength:1,
		scroll: true,
		scrollHeight: 300,
		dataType:	"json",
		minchar: 1,//最小响应字符数量
		selectFirst:false,//默认不选中第1条
		//autoFocus:true,
		extraParams:{course : function(){ return $("#courseCode2").val();}},
		parse : function(data) {
			if(!data) return [];
			var rows = [];
			for ( var i = 0; i < data.length; i++) {
				rows[rows.length] = {
					data : data[i],
					value : data[i].str,
					result : data[i].courseCode
				};
			}
			return rows;
		},
		formatItem : function(row, i, max) {
			return row.courseCode;
		}
	});
	
	/* 自动加载学生*/
	$("#studentName").autocomplete("ajax-student.html", {
		httpMethod: "POST", 
		width: 150,
		max: 20,
		highlight: false,
		matchSubset:false,
		cacheLength:1,
		scroll: true,
		scrollHeight: 300,
		dataType:	"json",
		minchar: 1,//最小响应字符数量
		selectFirst:false,//默认不选中第1条
		//autoFocus:true,
		extraParams:{studentName : function(){ return $("#studentName").val();}},
		parse : function(data) {
			if(!data) return [];
			var rows = [];
			for ( var i = 0; i < data.length; i++) {
				rows[rows.length] = {
					data : data[i],
					value : data[i].str,
					result : data[i].studentName
				};
			}
			return rows;
		},
		formatItem : function(row, i, max) {
			return row.studentName;
		}
	});
});