/**
 * 
 */


// init-select  
/**
 * 
 * @param groupCode 해당 그룹코드
 * @param title 넣고자 하는 요소의 title값
 * @returns
 */
function initSelect(groupCode, title) {
	alert("initSelect 실행중" +"groupCode : "+groupCode +"title : "+ title);
	
	var param = {"groupCode" : groupCode};
	$.ajax({
		url : "/JqueryPro/CodeServlet"
		,type : "post"
		,data : param
		,dataType : "json",
		success : function(data) {
			// console.log(data);
			makeSelect(data, title);
//			alert("성공");
		},
		error : function(xhr) {
			console.error(xhr);
			alert("오류");
		}
	});
}

function makeSelect(data, title) {
	var strHtml = "";
	for (i = 0; i < data.length; i++) {
		strHtml += "<option value=" + data[i].cnt + ">" + data[i].codeName
		+ "</option>";
	}
	$("select[title='"+title+"'").html(strHtml);
	
}

//init-check
function initCheck(groupCode, title) {
	alert("initCheck 실행중    , groupCode : "+groupCode +"title : "+ title);
	
	var param = {"groupCode" : groupCode};
	$.ajax({
		url : "/JqueryPro/CodeServlet"
		,type : "post"
		,data : param
		,dataType : "json",
		success : function(data) {
			// console.log(data);
			makeCheck(data, title);
//			alert("성공");
		},
		error : function(xhr) {
			console.error(xhr);
			alert("오류");
		}
	});
}

function makeCheck(data, title) {
	var strHtml = "";
	for (i = 0; i < data.length; i++) {
		strHtml += "<label for='"+title+"'" + data[i].description
		+ "'><input type='checkbox' id='"+title+"'" + data[i].description
		+ "' name='"+title+"' value='" + data[i].cnt + "'>"
		+ data[i].codeName + "</label>";
	}
	$("div[title='"+title+"'").html(strHtml);
	
}