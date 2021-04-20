/**
 * 마이페이지=> 설정=> 메일인증받고 => 비밀번호 재설정
 */
$(function(){
	//패스워드 정규식
	$("#pass").on({
		blur : function() {
			if(passCheck($(this).val())){
				$("#passSpan").html("&nbsp;GOOD!")
							   .css("color", "green");
			}else {
				$("#passSpan").html("&nbsp;FAIL!")
							   .css("color", "red");
			}
		}
	});
	//패스워드 일치 여부
	$("#memPass").on({
		blur : function() {
			var pass= $("#pass").val();
			if(isEmpty(pass)){
				alert("📌새비밀번호를 먼저 입력해주세요");
				$("#pass").focus();
				return;
			}
			
			if($(this).val() == $("#pass").val()){
				$("#mempassSpan").html("&nbsp;GOOD!")
								 .css("color", "green");
			}else {
				$("#mempassSpan").html("&nbsp;FAIL!")
								 .css("color", "red");
			}
		}
	});	
});

/**
 * 정규식 패스워드
 */
function passCheck(pass){
	console.log(pass);
	var passReg = /^[a-zA-Z0-9]{8,12}$/;
	return passReg.test(pass);
	
}

/**
 * 유효성 체크
 */
function validateCheck(){
	//패스워드 빈값, 정규식
	var memPass = $("#pass").val();
	if(isEmpty(memPass)){
		alert("새비밀번호를 입력해주세요😊");
		$("#pass").focus();
		return false;
	}else if(!passCheck(memPass)){
		alert("양식에 맞지 않는 패스워드입니다😥");
		$("#pass").focus();
		return false;
	}
	
	//패스워드 체크
	var memPassChk = $("#memPass").val();
	if(isEmpty(memPassChk)) {
		alert("새비밀번호를 확인해 주세요😊");
		$("#memPass").focus();
		return false;
	}else if( !(memPass == memPassChk)){
		alert("패스워드가 일치하지 않습니다😥");
		$("#memPass").focus();
		return false;
	}
	return true;	
}

/**
 * 비밀번호 중복검사 (현재비밀번호와 동일하게 할수도 있으니까)
 */
function memPassCheck(){
	if(!validateCheck()) return;
	
	var memPass = $("#memPass").val();
	var memEmail = $("#memEmail").val();
	
	$.ajax({
		//비밀번호 중복체크
		url: "/member/memPassChk.do"
		,type: "post"
		,data: { 
			//사용자가 입력한 새비밀번호, 이메일주소
			 "memEmail" : memEmail
			,"memPass" : memPass
		}
		,dataType: "json"
		,success: function(data) {
			if(data.cnt == 1){
				alert("📌회원님의 현재 비밀번호와 동일합니다" +
					  "\n📌다시 입력해주세요.");
				$("#pass").focus();
			}else{
				
				changeMemPass();
			}
		}
		,error: function(xhr) {
			alert("비밀번호 중복검사에 실패했습니다.\n관리자에게 문의해주세요.\n오류코드 : " + xhr.status)
		}
	});	
}

/**
 * 비밀번호 수정 기능
 */
function changeMemPass(){
	
	if(!confirm("📌새비밀번호로 수정하시겠습니까?")) return;
	
	//사용자가 입력한 이메일과, 비밀번호
	var memEmail = $("#memEmail").val();
	var memPass = $("#memPass").val();
	$.ajax({
		//비밀번호 수정
		url: "/member/mypageMemPassChange.do"
		,type: "post"
		,data: { 
			 "memEmail" : memEmail
			,"memPass" : memPass
		}
		,dataType: "json"
		,success: function(data) {
			if(data.cnt==0){
				alert("📌비밀번호가 수정 실패했습니다.");
			}else{
				alert("비밀번호 수정완료되었습니다😊");
				//로그인페이지로
				$(location).attr('href','/member/signin.do');
				
			}
		}
		,error: function(xhr) {
			alert("비밀번호수정에 실패했습니다.\n관리자에게 문의해주세요.\n오류코드 : " + xhr.status)
		}
	});
}



















