/**
 * 210416
 * 부모창 데이터 세팅
 * 부트스트랩 modal
 * input type="hidden"
 */

var loginId = "";
var flag2 = "";
$(document).ready(function() {

//  $("#memId").on("keyup", checkIdspan());
//  $("#memName").on("keyup", checkNamespan());
//  $("#memPass").on("keyup", checkPassspan());
//  $("#memHp").on("keyup", checkHpspan());
//  $("#memMail").on("keyup", checkMailspan());
    




	 $("#tbZipResult").on("dblclick", "tbody tr", function(){
		 // this ==> tr
//		 console.log($(this));
		 
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
function setZip() {
	var param;
	param = {
		"Dong" : $("#inputDong").val()
	};

	$.ajax({
		url : "/MemberPj/ZipServlet",
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
	$("#divZipResult").show();
	var strHtml = "";
	for (i = 0; i < data.length; i++) {
		
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
	$("#inputDong").empty();
	// 테이블 초기화
	$("#tbZipResult tbody").empty();
	// 주소창(모달창) 여닫기
//	$("#zipModal").modal("hide");
	$("#zipModal").modal();
}

/*
 * ====================================================================
 */

function checkIdspan() {

	var flag2 = "f";
	var memId = $("#memId").val();
	// 빈값 확인
	if (isEmpty(memId)) {
		// console.log(memId);
		$("#idspan").text("ID 값이 입력되지 않았습니다");
		$("#idspan").css("color","red");
		$("#memId").focus();
		return;
	}
	
//	alert("2번쨰");
	// 유효성 검사 - 영어소문자와 숫자로 구성, 4글자 이상 12글지 이하
	var regExp = /^[a-z0-9]{4,12}$/;
	// console.log(regExp);
	if (!regExp.test(memId)) {
		$("#idspan").text("ID값이 유효하지 않습니다");
		$("#idspan").css("color","red");
		$("#memId").focus();
		return;
	}

	$("#idspan").text("유효한 ID");
	$("#idspan").css("color","lime");
	flag2 ="t";
}


function checkNamespan() {
		var memName = $("#memName").val();
	// 빈값 확인
	if (isEmpty(memName)) {
		// console.log(memId);
		$("#namespan").text("이름이 입력되지 않았습니다");
		$("#namespan").css("color","red");
		$("#memName").focus();
		return;
	}
	
	// 유효성 검사 - 한글 2~5글자
	var regExp = /^[가-힣]{2,5}$/;
	// console.log(regExp);
	if (!regExp.test(memName)) {
		$("#namespan").text("이름이 유효하지 않습니다");
		$("#namespan").css("color","red");
		$("#memName").focus();
		return;
	}

	$("#namespan").text("유효한 이름");
	$("#namespan").css("color","lime");

}
function checkBirspan() {
	var memBir = $("#memBir").val();
	// 빈값 확인
	if (isEmpty(memBir)) {
		// console.log(memId);
		$("#birspan").text("생일이 입력되지 않았습니댜");
		$("#birspan").css("color","red");
		$("#memBir").focus();
		return;
	}
	var today = new Date();
//	alert(today);
	var minus = today.getFullYear()-($("#memBir").val()).substr(0,4);

	if(minus<9){
		$("#birspan").text("10살 이상만 가입이 가능합니다");
		$("#birspan").css("color","red");
		return;
	};
	$("#birspan").text("유효한 생일값");
	$("#birspan").css("color","lime");
}
function checkPassspan() {
	var memPass = $("#memPass").val();
	// 빈값 확인
	if (isEmpty(memPass)) {
		// console.log(memId);
		$("#passspan").text("비밀번호가 입력되지 않았습니댜");
		$("#passspan").css("color","red");
		$("#memPass").focus();
		return;
	}
	// 유효성 검사
	var regExp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,12}/;
	// console.log(regExp);
	if (!regExp.test(memPass)) {
		$("#passspan").text("비밀번호가 유효하지 않습니다");
		$("#passspan").css("color","red");
		$("#memPass").focus();
		return;

	$("#passspan").text("유효한 PASS");
	$("#passspan").css("color","lime");
	}
}
function checkMailspan() {
	var memMail = $("#memMail").val();
	// 빈값 확인
	if (isEmpty(memMail)) {
		// console.log(memId);
		$("#mailspan").text("이메일이 입력되지 않았습니댜");
		$("#mailspan").css("color","red");
		$("#memMail").focus();
		return;
	}
	// 유효성 검사
	var regExp = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
	// console.log(regExp);
	if (!regExp.test(memMail)) {
		$("#mailspan").text("이메일이 유효하지 않습니다");
		$("#mailspan").css("color","red");
		$("#memMail").focus();
		return;

	$("#mailspan").text("유효한 email");
	$("#mailspan").css("color","lime");
	}
}

function checkHpspan(){
	var memHp = $("#memHp").val();
	// 빈값 확인
	if (isEmpty(memHp)) {
		// console.log(memId);
		$("#hpspan").text("핸드폰 번호가 입력되지 않았습니댜");
		$("#hpspan").css("color","red");
		$("#memHp").focus();
		return;
	}
	// 유효성 검사
	var regExp = /^\d{3}-\d{3,4}-\d{4}$/;
	// console.log(regExp);
	if (!regExp.test(memHp)) {
		$("#hpspan").text("핸드폰 번호가 유효하지 않습니다");
		$("#hpspan").css("color","red");
		$("#memHp").focus();
		return;
	}
	$("#hpspan").text("유효한 핸드폰 번호");
	$("#hpspan").css("color","lime");	
}

function checkAddspan(){
	var memAdd1 = $("#memAdd1").val();
	// 빈값 확인
	if (isEmpty(memAdd1)) {
		// console.log(memId);
		$("#addspan").text("주소가 입력되지 않았습니댜");
		$("#addspan").css("color","red");
		$("#memAdd1").focus();
		return;
	}

	$("#addspan").text("유효한 주소");
	$("#idspan").css("color","lime");
}


function validate() {

	var flag2 = "f";
	var memId = $("#memId").val();
	// 빈값 확인
	if (isEmpty(memId)) {
		// console.log(memId);
		alert("ID 값이 입력되지 않았습니다");
		$("#memId").focus();
		return false;
	}
	// 유효성 검사 - 영어소문자와 숫자로 구성, 3글자 이상 10글지 이하
	var regExp = /^[a-z0-9]{4,12}$/;
	// console.log(regExp);
	if (!regExp.test(memId)) {
		alert("ID값이 유효하지 않습니다");
		$("#memId").focus();
		return false;		
	}

	flag2 ="t";

	$.ajax({
		url : "/MemberPj/MemberServlet",
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
				$("#idspan").text("사용가능");
				$("#idspan").css("color","lime");
			} else {
				alert("중복된 ID");
				$("#idspan").text("중복된 ID");
				$("#idspan").css("color","red");
				return false;
			}
		},
		error : function(xhr) {
			console.error(xhr);
		}
	});


		var memName = $("#memName").val();
	// 빈값 확인
	if (isEmpty(memName)) {
		// console.log(memId);
		alert("이름이 입력되지 않았습니다");
		$("#memName").focus();
		return false;		
	}
	
	// 유효성 검사 - 한글 2~5글자
	var regExp = /^[가-힣]{2,5}$/;
	// console.log(regExp);
	if (!regExp.test(memName)) {
		alert("이름이 유효하지 않습니다");
		$("#memName").focus();
		return false;		
	}


	var memBir = $("#memBir").val();
	// 빈값 확인
	if (isEmpty(memBir)) {
		// console.log(memId);
		alert("생일이 입력되지 않았습니댜");
		$("#memBir").focus();
		return false;		
	}

	var memPass = $("#memPass").val();
	// 빈값 확인
	if (isEmpty(memPass)) {
		// console.log(memId);
		alert("비밀번호가 입력되지 않았습니댜");
		$("#memPass").focus();
		return false;		
	}
	// 유효성 검사
	var regExp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,12}/;
	// console.log(regExp);
	if (!regExp.test(memPass)) {
		alert("비밀번호가 유효하지 않습니다");
		$("#memPass").focus();
		return false;		
	}

	var memMail = $("#memMail").val();
	// 빈값 확인
	if (isEmpty(memMail)) {
		// console.log(memId);
		alert("이메일이 입력되지 않았습니댜");
		$("#memMail").focus();
		return false;		
	}
	// 유효성 검사
	var regExp = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
	// console.log(regExp);
	if (!regExp.test(memMail)) {
		alert("이메일이 유효하지 않습니다");
		$("#memMail").focus();
		return false;		
	}


	var memHp = $("#memHp").val();
	// 빈값 확인
	if (isEmpty(memHp)) {
		// console.log(memId);
		alert("핸드폰 번호가 입력되지 않았습니댜");
		$("#memHp").focus();
		return false;
	}
	// 유효성 검사
	var regExp = /^\d{3}-\d{3,4}-\d{4}$/;
	// console.log(regExp);
	if (!regExp.test(memHp)) {
		alert("핸드폰 번호가 유효하지 않습니다");
		$("#memHp").focus();
		return false;
	}	

	var memAdd1 = $("#memAdd1").val();
	// 빈값 확인
	if (isEmpty(memAdd1)) {
		// console.log(memId);
		alert("주소가 입력되지 않았습니댜");
		$("#addspan").css("color","red");
		$("#memAdd1").focus();
		return false;
	}

	return true;

}
	// DB에서 중복검사 수행
function checkId(){


	if(flag2 == "f"){
		alert("유효한 ID값을 작성 후 중복검사가 가능합니다");
	} else{


	$.ajax({
		url : "/MemberPj/MemberServlet",
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
				$("#idspan").text("사용가능");
				$("#idspan").css("color","lime");
			} else {
				$("#idspan").text("중복된 ID");
				$("#idspan").css("color","red");
			}
		},
		error : function(xhr) {
			console.error(xhr);
		}
	});
	}
}


// 회원정보 저장하기
function save(){
	
	// 회원정보 유효성 체크
	var result = validate();
	if(!result){
		return;
	}
//	
	
//	// 사용자에게 컨펌.
	if(!confirm("저장하시겠습니까?")){
		return;
	}
	
	alert($("#fm").serialize());
	$("#formFlag").val("C");
	//DB에 저장하는 ajax 호출
	$.ajax({
		url : "/MemberPj/MemberServlet"
		,type : "post"
		,data : $("#fm").serialize()
		,dataType : "json"
		,success : function(data){
			console.log(data);
			if(1 == data.resultCnt){
				// 페이지 이동
				changePage("/MemberPj/html/member/registerForm.html");
				alert("가입이 완료되었습니다");
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
//	window.location.href = "/MemberPj/html/member/registerForm.html";
	
	// 방법2
	var fm = document.getElementById("fm");
	fm.action = strUrl;// 서블릿을 호출하기도 함
	fm.method = "post";
	fm.submit();
}



