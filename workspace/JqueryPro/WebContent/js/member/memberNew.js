/**
 * 
 */


var loginId = "";

$(document).ready(function() {
	
	// 취미,직업, 기념일코드 세팅
	initLikeSelect();
	initJobSelect();
	initMemorialSelect();

	
	// 이메일 수신동의 default 세팅(N)
	$("#recvEmail_N").prop("checked",true);
	
	// 우편번호찾기 화면-시 세팅
	initCitySelect();
});
	// 취미코드 조회해서 세팅
	function initLikeSelect(){
		$.ajax({
			url : "/JqueryPro/CodeServlet"
			,type : "post"
			,data : {
				"groupCode" : "A01"
			} // 취미코드
		,dataType : "json",
		success : function(data) {
//			console.log(data);
			makeLikeSelect(data);
//			alert("성공");
		}
		,error : function(xhr) {
			console.error(xhr);
			alert("오류");
		}
		});
	}
	// 직업코드 조회해서 세팅
function initJobSelect(){
	$.ajax({
		url : "/JqueryPro/CodeServlet"
		,type : "post"
		,data : {
			"groupCode" : "A02"
		} // 직업코드
		
		,dataType : "json"
		,success : function(data) {
//			console.log(data);
			makeJobSelect(data);
//			alert("성공");
		}
		,error : function(xhr) {
			console.error(xhr);
			alert("오류");

		}
	});
}
	// 기념일코드 조회해서 세팅
function initMemorialSelect(){
	$.ajax({
		url : "/JqueryPro/CodeServlet"
		,type : "post"
		,data : {
			"groupCode" : "A03"
		} // 취미코드
		,dataType : "json",
		success : function(data) {
//			console.log(data);
			makeMemorialTypeSelect(data);
//			alert("성공");
		}
		,error : function(xhr) {
			console.error(xhr);
			alert("오류");
		}
	});
	// 광고메일 수신 여부 기본값 세팅 - 미수신
}

function makeJobSelect(data) {
	var strHtml = "";
	//방법1
	// 		$("#memJob").html();
	//방법2
	// 		$("#memJob").empty();
	// 		$("#memJob").append();

	for (i = 0; i < data.length; i++) {
		strHtml += "<option value='" 
				+ data[i].value 
				+ "'>" + data[i].name
				+ "</option>";
	}
	// 		console.log(strHtml);
	$("#memJob").html(strHtml);
}

function makeMemorialTypeSelect(data) {
	var strHtml = "";
	for (i = 0; i < data.length; i++) {
		strHtml += "<option value='" 
				+ data[i].value 
				+ "'>" 
				+ data[i].name
				+ "</option>";
	}
//	console.log(strHtml);
	$("#memMemorialType").html(strHtml);
}

function makeLikeSelect(data) {
	var strHtml = "";

	for (i = 0; i < data.length; i++) {
		strHtml += "<label for='memLike" + data[i].description
				+ "'><input type='checkbox' id='memLike" + data[i].description
				+ "' name='memLike' value='" + data[i].value + "'>"
				+ data[i].name + "</label>";
	}
	// 		console.log(strHtml);
	$("#memLikediv").html(strHtml);
}

// DB에서 중복검사 수행
function checkId() {
	var memId = $("#memId").val();
	alert(memId);
	// 빈값 확인
	if (isEmpty(memId)) {
//		console.log(memId);
		alert("ID 값이 입력되지 않았습니댜");
		$("#memId").focus();
		return;
	}
	 // 		유효성 검사 - 영어소문자와 숫자로 구성, 3글자 이상 10글지 이하
	 		var regExp = /^[a-z0-9]{3,10}$/;
//	 		console.log(regExp);
	 		if (!regExp.test(memId)) {
	 			alert("ID값이 유효하지 않습니다");
	 			$("#memId").focus();
	 			return;
	 		}
	// DB에서 중복검사 수행
	
	$.ajax({
		url : "/JqueryPro/MemberServlet",
		type : "post",
		data : {
			'memId' : memId,
			'flag' : 'CHKID'
		},
		dataType : "json",
		success : function(data) {
//			console.log(data);
//			console.log(data.resultCnt);
			// 				console.log(typeof data.resultCnt); // String
			if (data.resultCnt == 0) {
				alert("사용가능!!");
			} else {
				alert("중복!!");
			}
		},
		error : function(xhr) {
			console.error(xhr);
		}
	});
}

function initCitySelect(){
	$.ajax({
		url : "/JqueryPro/ZipServlet"
		,type : "post"
//		,data : {
//			"groupCode" : "A01"
//		} 
	,dataType : "json",
	success : function(data) {
		console.log(data);
		makeSidoSelect(data);
		alert("성공");
	}
	,error : function(xhr) {
		console.error(xhr);
		alert("오류");
	}
	});
}

function makeSidoSelect(data){
	// 방법1)
	var strHtml = "<option value=''>선택하세요</option>";
	for (i = 0; i < data.length; i++) {
		strHtml += "<option value=" 
				+ data[i].value 
				+ ">" 
				+ data[i].sido
				+ "</option>";
	}
	console.log(strHtml);
	$("#Sido").html(strHtml);
	// 방법2)
	//setGugun();
	// 방법3)
	//트리거로 Change호출
}

function makeGugunSelect(data){
	if($("#Sido").val()!=''){
		$("#Gugun").prop("disabled", false);
	}
	var strHtml = "<option value=''>선택하세요</option>";
	for (i = 0; i < data.length; i++) {
		strHtml += "<option value=" 
				+ data[i].value 
				+ ">" 
				+ data[i].gugun
				+ "</option>";
	}
	console.log(strHtml);
	$("#Gugun").html(strHtml);

}

function makeDongSelect(data){
	if($("#Gugun").val()!=''){
		$("#Dong").prop("disabled", false);
	}
	var strHtml = "<option value=''>선택하세요</option>";
	for (i = 0; i < data.length; i++) {
		strHtml += "<option value=" 
				+ data[i].value 
				+ ">" 
				+ data[i].dong
				+ "</option>";
	}
	console.log(strHtml);
	$("#Dong").html(strHtml);
}
function makeZipSelect(data){
	var strHtml = "<option value=''>선택하세요</option>";
	for (i = 0; i < data.length; i++) {
		strHtml += "<option value=" 
				+ data[i].value 
				+ ">" 
				+ data[i].dong
				+ "</option>";
	}
	console.log(strHtml);
//	$("#Dong").html(strHtml);
}
function setGugun(){
	var param;
	param = {"sido" : $("#Sido").val()
			,"flag" : "GUGUN"
			};
	$.ajax({
		url : "/JqueryPro/ZipServlet"
		,type : "post"
		,data : param
	,dataType : "json"
	,success : function(data) {
		console.log(data);
		makeGugunSelect(data);
//		alert("성공");
	}
	,error : function(xhr) {
		console.error(xhr);
		alert("오류");
	}
	});
}

function setDong(){
	var param;
	param = {"sido" : $("#Sido").val()
			,"gugun" : $("#Gugun").val()
			,"flag" : "DONG"
			};
	$.ajax({
		url : "/JqueryPro/ZipServlet"
		,type : "post"
		,data : param
	,dataType : "json",
	success : function(data) {
		console.log(data);
		makeDongSelect(data);
//		alert("성공");
	}
	,error : function(xhr) {
		console.error(xhr);
		alert("오류");
	}
	});
}

function setZip(){
	var param;
	param = {"sido" : $("#Sido").val()
			,"gugun" : $("#Gugun").val()
			,"dong" : $("#Dong").val()
			,"flag" : "ZIP"
			};
	
	$.ajax({
		url : "/JqueryPro/ZipServlet"
		,type : "post"
		,data : param
	,dataType : "json",
	success : function(data) {
		console.log(data);
		makeZipSelect(data);
//		alert("성공");
	}
	,error : function(xhr) {
		console.error(xhr);
		alert("오류");
	}
	});
}