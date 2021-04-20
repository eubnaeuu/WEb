/**
 * 아이디 찾기 자바스크립트 
 */

$(function(){
	//이메일 정규식
	$("#memEmail").on({
		blur : function(){
			if(mailCheck($(this).val())){
				$("#emailSpan").html("&nbsp; GOOD!")
							   .css("color","green");
			}else{
				alert("📌이메일 형식으로 입력해주세요");
				$("#emailSpan").html("&nbsp; FAIL!")
				  	           .css("color","red");
			}
		}
	});
});
/**
 * 이메일 정규식 체크
 */
function mailCheck(email){
	var mailReg = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
	return mailReg.test(email);
}


//아이디찾기 버튼 눌렀을때 기능
function findId(){
	//이메일 빈값 체크
	var memEmail = $("#memEmail").val();
	if(isEmpty(memEmail)){
		alert("📌이메일을 입력해주세요");
		$("#emailSpan").html("&nbsp; FAIL!")
          			   .css("color","red");		
		$("#memEmail").focus();
		return false;
	}else if(!mailCheck(memEmail)){
		alert("📌이메일 양식에 맞게 입력해주세요");
		$("#memEmail").focus();
		return false;
	}
	
	$.ajax({
		url: "/member/findId.do"
		,type: "post"
		,data: $("#findIdForm").serialize()
		,dataType: "json"
		,success: function(data) {
			if(data.memId == 'null'){
				alert("등록된 회원이 아니거나 탈퇴한 회원입니다..😢");
			}else{
				alert("📌 회원님의 아이디는 '"+ data.memId + "'입니다.");
			}
		}
		,error: function(xhr) {
			alert("아이디 찾기 실패했습니다. 관리자에게 문의해주세요.\n오류코드 : " + xhr.status)
		}
	});
}

