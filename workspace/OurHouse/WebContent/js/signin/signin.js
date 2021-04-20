/**
 * 로그인 페이지
 */

$(document).ready(function(){
	
});

function validateCheck(){
	//아이디,패스워드 빈칸 처리
	var memId = $("#memId").val();
	if(isEmpty(memId)){
		alert("ID를 입력해주세요🤔.");
		$("#memId").focus();
		return false;
	}
	
	var memPass = $("#memPass").val();
	if(isEmpty(memPass)) {
		alert("패스워드를 입력해주세요🤔");
		$("#memPass").focus();
		return false;
	}
	return true;
}

/**
 * 로그인 버튼 눌렀을때 
 */
function signin(){
	if(!validateCheck()) return;
	
	$.ajax({
		url: "/member/signin.do"
		,type: "post"
		,data: $("#signinForm").serialize()
		,dataType: "json"
		,success: function(data) {
			// data.msg == "ok" { }
			// data.msg == "fail" { } 
//			if(data.msg == "AllOk"){
//				alert("로그인 성공😎");
//				$(location).attr('href','/main.do');
//				//로그인아이디 들고 메인페이지로 이동 그 세션을 안가져오고 
//			}else if(data.msg == "noMember"){
//			 	alert("탈퇴한 회원입니다.");
//			}else{
//				alert("💢아이디나 비밀번호가 틀렸습니다💢 ");
//			}
			if(data.cnt ==1){ 
				alert("🏡우리의 집🏡 회원이 아닙니다.");
			}else if(data.memId == $("#memId").val() && data.memPass == $("#memPass").val() && data.memDelete == "N"){
				if(!(data.memId == "admin")){
					alert( data.memId + " 회원님  환영합니다 🏡우리의집🏡");
					$(location).attr('href','/main.do');
				}else{
					if(data.memId == "admin"){
						$(location).attr('href','/admin/reportList.do');
					}else{
						$(location).attr('href','/member/signin.do');
					}
				}
			}else if(data.memId == $("#memId").val() && data.memPass == $("#memPass").val() && data.memDelete == "Y"){
				alert("📌등록된 회원이 아니거나 탈퇴한 회원입니다");
				$(location).attr('href','/member/signup.do');
			}else if(data.memId == $("#memId").val() && !(data.memPass == $("#memPass").val())){
				alert("💢비밀번호 오류💢 다시 입력해주세요");
			}
		}
		,error: function(xhr) {
			alert("로그인에 실패했습니다. 관리자에게 문의해주세요.\n오류코드 : " + xhr.status);
		}
	});
}


