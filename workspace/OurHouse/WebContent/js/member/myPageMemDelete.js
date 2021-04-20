/**
 * 회원탈퇴
 */

$(document).ready(function(){
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
				$("#mempassSpan").html("&nbsp;비밀번호가 일치합니다.")
								 .css("color", "green");
			}else {
				$("#mempassSpan").html("&nbsp;비밀번호가 일치않습니다.")
								 .css("color", "red");
			}
		}
	});		
});

/**
 * 유효성 체크
 */
function validateCheck(){
	//패스워드 빈값, 정규식
	var memPass = $("#pass").val();
	if(isEmpty(memPass)){
		alert("비밀번호를 입력해주세요😊");
		$("#pass").focus();
		return false;
	}
	
	//패스워드 체크
	var memPassChk = $("#memPass").val();
	if(isEmpty(memPassChk)) {
		alert("비밀번호확인란를 입력해주세요😊");
		$("#memPass").focus();
		return false;
	}else if( !(memPass == memPassChk)){
		alert("비밀번호가 일치하지 않습니다😥");
		$("#memPass").focus();
		return false;
	}
	return true;		
}


/**
 * 비밀번호 일치여부
 * 디비에 있는 비밀번호와 일치하면 탈퇴기능으로 고고
 */
function memPassCheck(){
	
	if(!validateCheck()) return;
	
	//사용자가 입력한 비밀번호
	var memPass = $("#memPass").val();
	//세션으로 가져온 hidden으로 가져온 로그인한 회원이메일
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
				//디비에 있는 비밀번호와 일치..탈퇴기능으로
				memDelete();
			}else{
				alert("회원님의 비밀번호가 일치하지 않습니다.");
				$("#pass").focus();
			}
		}
		,error: function(xhr) {
			alert("비밀번호 중복검사에 실패했습니다.\n관리자에게 문의해주세요.\n오류코드 : " + xhr.status)
		}
	});	
}

/**
 * 회원탈퇴 기능 
 */
function memDelete(){

	if(!confirm("회원탈퇴를 하시겠습니까?")) return;
	
	var memId = $("#memId").val();
	var memDelete = $("#memDelete").val();
	
	$.ajax({
		url: "/member/memDelete.do"
		,type: "post"
		,data: { 
				//회원아이디는 세션에서 받아와야됨.. 로그인한 상태에서 회원탈퇴 페이지에 가니까!
				 "memId"     : memId
				,"memDelete" : "Y"
				}
		,dataType: "json"
		,success: function(data) {
			if(data.cnt == 0){
				alert("회원탈퇴 실패되었습니다.");
			}else{
				alert("회원탈퇴 완료되었습니다.\n그동안 🏡우리의집🏡을 이용해주셔셔 감사합니다.");
				//로그아웃 페이지, 로그인하면 탈퇴한 회원이라고 뜸!
				location.href = "/views/signin/logOut.jsp";
			}
		}
		,error: function(xhr) {
			alert("회원탈퇴에 실패했습니다. 관리자에게 문의해주세요.\n오류코드 : " + xhr.status);
		}
	});
	
}
