/**
 * 마이페이지 비밀번호 재설정 전에 이메일 인증받기
 */

$(function(){
	
});

/**
 * 사용자가 입력한 이메일에 인증번호 전송 기능 
 */
function mailAuth(){
	//사용자가 입력한 메일주소
	console.log("사용자가 입력한 메일주소 : "+ $('#memEmail').val());
	
	$.ajax({
		url : "/member/mypageMailAuth.do"
		,type : "post"
		,data : {"userMail" : $("#memEmail").val()} // { "memEmail" : $("#memEmail").val()}
		//사용자가 입력한 메일주소를 키값 "userMail"로 set해서 보냄					
		,dataType : "json"
		,success  : function(data){
			console.log("석세스데이터"+data);
			alert("인증번호가 전송되었습니다😊");
		}
		, error : function(xhr){
			console.log(xhr);
			alert("인증번호 전송 실패 ");
		}
	});
}

/**
 *  받은 인증번호를 맞게 썼는지 체크
 *  authChk2.jsp
 */
function authCheck(){
	var userAuth = $("#userAuth").val();
	if(isEmpty(userAuth)){
		alert("📌인증번호를 입력해주세요");
		$("#userAuth").focus();
		return false;
	}
	
	$.ajax({
		url : "/member/mailAuthChk.do"
		,type : "post"
		,data : {
			
			"userAuth" : $("#userAuth").val()
		
			} // 사용자가 입력한 인증번호
		,dataType : "json"
		,success : function(data){
			if(data.cnt==1){
				//1이 돌아오면 보낸인증키와 사용자가 입력한 인증키가 같다는것..
				alert("인증번호가 맞습니다😊");
				$("#emailChkSpan").html("&nbsp; GOOD!")
				  			   	  .css("color","green");
				$("#checkedUserAuth").val($("#userAuth").val()); // 회원가입 버튼 클릭시 인증했는지 비교하기 위해
//				location.href = "/member/mypageMemPassChange.do";  //비밀번호 수정페이지로 
			}else{
				alert("인증번호가 맞지 않습니다😥");
				$("#emailChkSpan").html("&nbsp; FAIL!")
			   	  			      .css("color","red");
				return;
			}
		}	
		,error : function(xhr){
			console.log(xhr);
			alert("인증번호 확인 실패"+xhr.status);
		}
	});
}