/**
 * 210416
 * 부모창 데이터 세팅
 * 부트스트랩 modal
 * input type="hidden"
 */

var loginId = "";

$(document).ready(function() {

	// select option 정리
	initCheck("A01","memLike");
	initSelect("A02","memJob");
	initSelect("A03","memMemorialType");

	// 우편번호찾기 화면-시 세팅
	 initCitySelect();
	
	// 이메일 수신동의 : default 세팅(N)
	$("#recvEmail_N").prop("checked", true);

	// 아래 두줄의 차이 
	// $("#tbZipResult tbody").dblclick(function(){
	//);
	 
	 $("#tbZipResult").on("dblclick", "tbody tr", function(){
		 // this ==> tr
		 console.log($(this));
		 
//		 alert("더블클릭중");
		 // ☆ 
		 
		 var zipcode = $(this).children("td:eq(0)").text();
		 var addr = $(this).children("td:eq(1)").text();
		 
		 console.log("addr");
		 console.log(addr);
		 console.log("zipcode");
		 console.log(zipcode);
		 
		 // 메인화면(부모창)의 우편번호, 주소 input에 데이터 세팅
		 $("#memZip").val(zipcode);
		 $("#memAdd1").val(addr);
		 
		 // on : 동적 요소 포함 ( e.g. 그 당시 테이블이 없어도 이 후에 생기게 되면 바인딩 O)(계속 추적하는 느낌?)  
		 // dblclick : 동적 요소 미포함   (e.g. 그 당시 테이블이 없다면 바인딩 X)
		 
		 
		 // 주소창 (모달창) 닫기
		 $("#zipModal").modal("hide");
	 });

});


function initCitySelect() {
	$.ajax({
		url : "/JqueryPro/ZipServlet"
		,type : "post"
		,dataType : "json"
		,success : function(data) {
//			console.log("initCitySelect--");
//			console.log(data);
			makeSidoSelect(data);
//			alert("성공");
		}
		,error : function(xhr) {
			console.error(xhr);
			alert("오류");
		}
	});
}

function makeSidoSelect(data) {
	// 방법1)
	var strHtml = "<option value=''>선택하세요</option>";
	for (i = 0; i < data.length; i++) {
		strHtml += "<option value=" + data[i].sido + ">" + data[i].sido
				+ "</option>";
	}
//	console.log(strHtml);
	$("#Sido").html(strHtml);
	// 방법2)
	// 방법3)
	// 트리거로 Change호출
}


function setGugun() {
	var param;
	param = {
		"sido" : $("#Sido").val(),
		"flag" : "GUGUN"
	};
	$.ajax({
		url : "/JqueryPro/ZipServlet"
		,type : "post"
		,data : param
		,dataType : "json"
		,success : function(data) {
			console.log(data);
			makeGugunSelect(data);
			// alert("성공");
		}
		,error : function(xhr) {
			console.error(xhr);
			alert("오류");
		}
	});
}





function makeGugunSelect(data) {
	if ($("#Sido").val() != '') {
		$("#Gugun").prop("disabled", false);
		// $("#Sido").children().eq($("#Sido").val()).html() : 도시이름
	}
	var strHtml = "<option val=''>선택하세요</option>";
	for (i = 0; i < data.length; i++) {
		strHtml += "<option val=" + data[i].value + ">" + data[i].gugun
				+ "</option>";
	}
	console.log(strHtml);
	$("#Gugun").html(strHtml);
//	setDong();
}

function setDong() {
	var param;
	param = {
		"sido" : $("#Sido").val(),
		"gugun" : $("#Gugun").val(),
		"flag" : "DONG"
	};
	$.ajax({
		url : "/JqueryPro/ZipServlet",
		type : "post",
		data : param,
		dataType : "json",
		success : function(data) {
			console.log(data);
			makeDongSelect(data);
			// alert("성공");
		},
		error : function(xhr) {
			console.error(xhr);
			alert("오류");
		}
	});
}

function makeDongSelect(data) {
	if ($("#Gugun").val() != '') {
		$("#Dong").prop("disabled", false);
	}
	var strHtml = "<option val=''>선택하세요</option>";
	for (i = 0; i < data.length; i++) {
		strHtml += "<option val=" + data[i].value + ">" + data[i].dong
				+ "</option>";
	}
	console.log(strHtml);
	$("#Dong").html(strHtml);
}

function setZip() {
	var param;
	param = {
		"sido" : $("#Sido").val()
		,"gugun" : $("#Gugun").val()
		,"dong" : $("#Dong").val()
		,"flag" : "ZIP"
	};

	$.ajax({
		url : "/JqueryPro/ZipServlet",
		type : "post",
		data : param,
		dataType : "json",
		success : function(data) {
			console.log(data);
			makeZipTable(data);
			// alert("성공");
		},
		error : function(xhr) {
			console.error(xhr);
			alert("오류");
		}
	});
}

function makeZipTable(data) {
//	alert("makeZipTable 실행중");
	// ☆ 
	$("#divZipResult").show();
	
//	$("#tbZipResult tbody").empty();
	
	var strHtml = "";
	for (i = 0; i < data.length; i++) {
		// <tr onclick = 'fntest( " 300-301", "대전" , "중구", "문화1동", "1번지" );'>
		
//		 strHtml += "<tr onclick='fntest( \"" + data[i].sido + "\",
//		 \""+data[i].gugun+"\"+, \""+data[i].dong+"\");'>" // "300-801
		
//		strHtml += "<tr onclick = 'fntest( " + data[i] + " );'>" // "300-801
//				+ "<td>" + data[i].zipcode + "</td>" + "<td>" + data[i].sido
//				+ " " + data[i].gugun + " " + data[i].dong + " "
//				+ data[i].bunji + "</td>" + "</tr>";
		
		if(data[i].bunji == "null"){
			data[i].bunji = "";
		}
		
		strHtml += "<tr>"
			+ "<td>" + data[i].zipcode + "</td>"
			+ "<td>" + data[i].sido + " "
			+ data[i].gugun + " "
			+ data[i].dong + " " 
			+ data[i].bunji + "</td>"
			+ "</tr>";
	}
	
	$("#tbZipResult tbody").html(strHtml);
	console.log(strHtml);
}

function openZip(){
	// 시 셀렉트박스 조회하고 초기화
	initCitySelect();
	// 테이블 초기화
	$("#tbZipResult tbody").empty();
	
	// 주소창(모달창) 여닫기
//	$("#zipModal").modal("hide");
	$("#zipModal").modal();
}

/*
 * ====================================================================
 */

function initSelect() {
	var strId = [];
	// var param;
	// param ={"flag" : "init"};
	$.ajax({
		url : "/JqueryPro/CodeServlet",
		type : "post"
		// ,data : param
		,
		dataType : "json",
		success : function(data) {
			// console.log(data);
			makeSelect(data);
//			alert("성공");
		},
		error : function(xhr) {
			console.error(xhr);
			alert("오류");
		}
	});

}

function makeSelect(data) {
	var strId;
	var idx;
		// data[i].get("value") : 그룹 코드 번호
		// data[i].get("name") : 그룹 코드 이름 e.g.취미코드
		// "취미코드".indexOf("코드") : 2
		// "취미코드".substr(0,2)
		$.ajax({
			url : "/JqueryPro/CodeServlet",
			type : "post"
			// ,data : {
			// "groupCode" : data[i].get("groupCode")
			// }
			,
			dataType : "json",
			success : function(data) {
				// console.log(data);
				var tmp = data;
				makemakeSelect(data);
				// alert("성공");
			},
			error : function(xhr) {
				console.error(xhr);
				alert("오류");
			}
		});
}
function makemakeSelect(data) {
	var strHtml = "";

	for (i = 0; i < data.length; i++) {
		
//		console.log(data[i].groupCodeName.indexOf("코드"));
		
		if (data[i].groupCodeName.indexOf("코드") != -1) {
			idx = data[i].groupCodeName.indexOf("코드");
			strId = data[i].groupCodeName.substr(0, idx);
			strHtml += "<option value=" + data[i].cnt + ">" + data[i].codeName
			+ "</option>";
			if (i != (data.length - 1)) {
				
				if (data[i].groupCode != data[i + 1].groupCode) {
					$("select[title='"+strId+"'").html(strHtml);
					strHtml = "";
				}
			} else {
				$("select[title='"+strId+"'").html(strHtml);
			}
		} else {
			idx = data[i].groupCodeName.indexOf("유형");
			strId = data[i].groupCodeName.substr(0, idx);
			// Like 어떻게 할건지?
			strHtml += "<label for='memLike" + data[i].description
			+ "'><input type='checkbox' id='memLike" + data[i].description
			+ "' name='memLike' value='" + data[i].cnt + "'>"
			+ data[i].codeName + "</label>";
			if (i != (data.length - 1)) {
				if (data[i].groupCode != data[i + 1].groupCode) {
					$("div[title='"+strId+"'").html(strHtml);
					console.log(strHtml);
					strHtml = "";
				}
			}

		}
	}
//	console.log(strHtml);
	
	// if($("select").attr("title").equlas(strId)){
	// $("select").attr("title").html(strHtml);
	// }
}

// DB에서 중복검사 수행
function checkId() {
	var memId = $("#memId").val();
	// 빈값 확인
	if (isEmpty(memId)) {
		// console.log(memId);
		alert("ID 값이 입력되지 않았습니댜");
		$("#memId").focus();
		return;
	}
	// 유효성 검사 - 영어소문자와 숫자로 구성, 3글자 이상 10글지 이하
	var regExp = /^[a-z0-9]{3,10}$/;
	// console.log(regExp);
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
			// console.log(data);
			// console.log(data.resultCnt);
			// console.log(typeof data.resultCnt); // String
			if (data.resultCnt == 0) {
				alert("사용가능한 ID입니다");
			} else {
				alert("중복된 ID입니다. 다시 입력해주세요");
			}
		},
		error : function(xhr) {
			console.error(xhr);
		}
	});
}

// 회원정보 저장하기
function save(){
	
	// 회원정보 유효성 체크
//	var result = validate();
//	if(!result){
//		return;
//	}
//	
	
//	// 사용자에게 컨펌.
	if(!confirm("저장하시겠습니까?")){
		return;
	}
	
	alert($("#fm").serialize());
	$("#formFlag").val("C");
	//DB에 저장하는 ajax 호출
	$.ajax({
		url : "/JqueryPro/MemberServlet"
		,type : "post"
		,data : $("#fm").serialize()
		,dataType : "json"
		,success : function(data){
			console.log(data);
			if(1 == data.resultCnt){
				// 페이지 이동
				changePage("/JqueryPro/html/member/registerForm.html");
				alert("성공?");
			}
		}
		,error : function(xhr){
			alert("실패! 관리자한테 문의하세요~")
			console.log(xhr);
		}
	});
}

function changePage(strUrl){
	
	// 방법1
//	window.location.href = "/JqueryPro/html/member/registerForm.html";
	
	// 방법2
	var fm = document.getElementById("fm");
	fm.action = strUrl;// 서블릿을 호출하기도 함
	fm.method = "post";
	fm.submit();
}

function validata(){
	// .... 메시지는 여기서 줌
	return false;
	
	// 체크가 끝나면..
	return true;
}

function openZipPopup(){
//	window.open()
}

