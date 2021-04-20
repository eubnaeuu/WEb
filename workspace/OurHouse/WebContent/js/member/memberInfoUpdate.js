/**
 * 회원정보수정
 */

$(function(){
	//닉네임 정규식
	$("#memNickname").on({
		blur : function() {
			if(nickNameReg($(this).val())){
				//정규식 패스하면
				$("#memNicknameSpan").html("&nbsp;중복체크 필수✔")
							   		 .css("color", "green");
			}else {
				$("#memNicknameSpan").html("&nbsp;📌한글 2~15글자로 입력해주세요")
							   		 .css("color", "red");
			}
		}
	});	
	
	//한줄소개 정규식
	$("#memIntro").on({
		blur : function() {
			if(memIntroReg($(this).val())){
				//정규식 패스하면
				$("#memIntroSpan").html("&nbsp;GOOD!")
							   		 .css("color", "green");
			}else {
				$("#memIntroSpan").html("&nbsp;📌33글자 이내로 입력해주세요")
							   		 .css("color", "red");
			}
		}
	});		
});
/**
 * 정규식 닉네임
 */
function nickNameReg(nickname){
	console.log(nickname);
	var nicknameReg = /^[가-힣]{2,15}$/;
	return nicknameReg.test(nickname);
}
/**
 * 정규식 한줄소개
 */
function memIntroReg(memIntro){
	console.log(memIntro);
	var memIntroReg = /^[.가-힣a-zA-Z0-9\s]{1,33}$/;     //   /^[.\s]{1,20}$/
	return memIntroReg.test(memIntro);
}

/**
 * 닉네임 중복체크
 */
function nickNameCheck(){
	var memNickname = $("#memNickname").val();
	if(isEmpty(memNickname)){
		alert("📌별명을 입력해주세요.");
		$("#memNickname").focus();
		return false;
	}else if(!nickNameReg(memNickname)){
		alert("📌한글 2~15글자로 입력해주세요")
		$("#memId").focus();
		return;
	}
	
	$.ajax({
		//중복체크 핸들러
		url: "/member/nickNameChk.do"
		,type: "post"
		,data: $("#signupForm").serialize()
		,dataType: "json"
		,success: function(data) {
			console.log(data);
			nickNameCheckResult(data);
		}
		,error: function(xhr) {
			alert("별명 중복검사에 실패했습니다.\n관리자에게 문의해주세요.\n오류코드 : " + xhr.status)
		}
	});
}
/**
 * 닉네임 중복체크 후처리
 */
function nickNameCheckResult(data) {
	var result = data.cnt;
	if (result == 0) { // 중복아님
		alert("중복되지 않는 별명입니다. 사용가능👍");
		$("#memNicknameSpan").html("&nbsp;GOOD!")
		                     .css("color", "green");
		
		$("#checkedNickName").val($("#memNickname").val()); // 회원가입 버튼 클릭시 중복체크 했는지 비교하기 위해
	} else { // 중복임
		alert("중복되는 별명입니다. 다른 별명를 입력해 주세요😥");
		$("#memNickname").focus();
		$("#memNicknameSpan").html("&nbsp;FAIL!")
		   				     .css("color", "red");
	}
}
/**
 * 회원정보수정 
 */
function memberInfoUpdate(){
	
	if(!validateCheck()) return;
//	if(!confirm("회원정보 수정을 하시겠습니까?")) return;

	// file 존재 여부 검사 -> 있으면 input hidden에 1, 없으면 0
	var fileCheck = document.getElementsByClassName("file-upload-input")[0].value; // 파일첨부여부
	if(fileCheck){ // 파일첨부 됨
		$("#fileCount").val(1);
	}else{ // 프로필사진은 변경하지않음 (파일첨부안됨)
		$("#fileCount").val(0);
	}

	// 폼 양식 가져오기
	var form = $("#signupForm")[0];
	
	// form양식을 데이터화시키기
	var data = new FormData(form);
	
	$.ajax({
		//회원정보수정
		url: "/member/memberInfoUpdate.do"
		,type: "post"
		,enctype: 'multipart/form-data'
		,data: data
        ,processData: false //필수
        ,contentType: false // 필수
		,dataType: "json"
        ,cache: false
        ,timeout: 600000
		,success: function(data) {
			console.log(data);
			if(data.cnt==0){
				alert("📌회원정보 수정 실패했습니다.");
			}else{
				alert("회원정보 수정완료되었습니다😊");
				//마이페이지로 보내기 -- 수정예정
				//$(location).attr('href','/member/signin.do');
			}
		}
		,error: function(xhr) {
			console.log(xhr);
			alert("회원정보수정 실패했습니다.\n관리자에게 문의해주세요.\n오류코드 : " + xhr.status);
		}
	});
}

/**
 * 회원정보수정 버튼 클릭시 입력별명이 중복체크 되었는지 확인하는 함수
 */
function validateNickNameCheck(memNickname){
	var checkedNickName = $("#checkedNickName").val();
	if(memNickname == checkedNickName) return true;
	return false;
}
/**
 * 회원정보 수정시 유효성 체크 
 */
function validateCheck(){
	//별명 빈값,중복체크 유무 
	var memNickname = $("#memNickname").val();
	if(isEmpty(memNickname)){
		alert("별명을 입력해주세요😊");
		$("#memNickname").focus();
		return false;
	}else if(!nickNameReg(memNickname)){
		alert("📌양식에 맞지 않는 별명입니다." +
			  "\n한글2~15글자 이내로 입력해주세요");
		$("#memNickname").focus();
		return false;
	}else if(!validateNickNameCheck(memNickname)){// 중복체크 했는지도 hidden으로 검사해야함
		alert("별명 중복체크를 해주세요😊");
		$("#nickNameChk").focus();
		return false;
	}	
	return true;
}




