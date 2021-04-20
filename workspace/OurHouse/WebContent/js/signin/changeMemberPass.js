/**
 * 비밀번호 재설정
 */

$(function(){
	//이메일 정규식
	$("#memEmail").on({
		blur : function(){
			if(mailCheck($(this).val())){
				$("#emailSpan").html("&nbsp;GOOD!")
							   .css("color","green");
			}else{
				$("#emailSpan").html("&nbsp;FAIL!")
				  	           .css("color","red");
			}
		}
	});
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
				alert("📌새비밀번호를 입력해주세요");
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
 * 이메일 정규식 체크
 */
function mailCheck(email){
	var mailReg = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
	return mailReg.test(email);
}
/**
 * 정규식 패스워드
 */
function passCheck(pass){
	console.log(pass);
	var passReg = /^[a-zA-Z0-9]{8,12}$/;
	return passReg.test(pass);
	
}

/**
 * 사용자가 입력한 이메일에 인증번호 전송 기능 
 */
function mailAuth(){
	//사용자가 입력한 메일주소
	console.log("사용자가 입력한 메일주소 : "+ $('#memEmail').val());
	
	$.ajax({
		url : "/member/mailAuth.do" //핸들러 서블릿주소로 바꾸고
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
			}else{
				alert("인증번호가 맞지 않습니다😥");
				$("#emailChkSpan").html("&nbsp; FAIL!")
			   	  			      .css("color","red");
				return;
			}
		}	
		,error : function(xhr){
			console.log(xhr);
			alert("오류");
		}
	});
}

/**
 * 이메일 인증 버튼 눌렀을때 이메일중복체크 먼저
 */
function memEmailDbCheck(){
	var memEmail = $("#memEmail").val();
	if(isEmpty(memEmail)){
		alert("📌이메일을 입력해주세요");
		$("#memEmail").focus();
		return false;
	}else if(!mailCheck(memEmail)){
		alert("📌양식에 맞지 않는 이메일입니다");
		$("#memEmail").focus();
		return false;
	}
	
	var memEmail = $("#memEmail").val();
	$.ajax({
		//중복체크 핸들러
		url: "/member/memEmailChk.do"
		,type: "post"
		,data: { "memEmail" : memEmail}
		,dataType: "json"
		,success: function(data) {
			console.log(data);
			memEmailDbCheckResult(data);
		}
		,error: function(xhr) {
			alert("이메일 중복검사에 실패했습니다.\n관리자에게 문의해주세요.\n오류코드 : " + xhr.status)
		}
	});
}

/**
 * 이메일 중복체크 후처리
 */
function memEmailDbCheckResult(data) {
	var result = data.cnt;
	if (result == 0) { // 이메일이 없으니까 회원가입 안한 회원
		alert("🏡우리의집🏡  회원이 아닙니다..");
		$("#emailSpan").focus();
		$("#emailSpan").html("&nbsp;FAIL!")
		   			   .css("color", "red");
	} else { // 중복O 회원이메일이 있음
		alert("🏡우리의집🏡 회원입니다. \n입력하신 이메일로 인증번호를 보내드렸습니다😉");
		$("#emailSpan").html("&nbsp;GOOD!")
				       .css("color", "green");
		$("#checkedEmail").val($("#memEmail").val()); // 회원가입 버튼 클릭시 중복체크 했는지 비교하기 위해
		mailAuth(); //메일인증으로
	}
}

/**
 * 회원가입 버튼 클릭시 입력인증번호가 인증되었는지 확인하는 함수
 */
function validateUserAuthCheck(userAuth){
	var checkedUserAuth = $("#checkedUserAuth").val();
	if(userAuth == checkedUserAuth) return true;
	return false;
}

/**
 * 빈값 체크
 */
function validateCheck(){
	//이메일 빈값, 중복체크
	var memEmail = $("#memEmail").val();
	
	if(isEmpty(memEmail))	{
		alert("회원가입때 사용하신 이메일을 입력해주세요😊");
		$("#memEmail").focus();
		return false;
	} else if(!mailCheck(memEmail)){
		alert("양식에 맞지 않는 이메일입니다😥");
		$("#memEmail").focus();
		return false;
	}
	
	//인증번호 빈값, 인증번호 확인 유무
	var userAuth = $("#userAuth").val();
	
	if(isEmpty(userAuth)){
		alert("이메일 인증은 필수입니다😊");
		$("#userAuth").focus();
		return false;
	}else if(!validateUserAuthCheck(userAuth)){
		alert("📌인증번호 확인해주세요");
		$("#userAuth").focus();
		return false;
	}
	
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
		//비밀번호 수정
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
		url: "/member/changeMemPass.do"
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










