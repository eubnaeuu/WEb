
/**
 * 
 */


/**
 * 아이디 중복검사 (버튼에 클릭 이벤트)
 * @returns
 */

function chkId(){
	var memId = $("#memId").val();
	// 빈값 확인
	if(isEmpty(memId)){
		alert("ID 값이 입력되지 않았습니댜");
		$("#memId").focus();
		return;
	}
	
	// 유효성 검사 - 영어소문자와 숫자로 구성, 3글자 이상 10글지 이하
	var regExp = /^[a-Z0-9]{3,10}$/;
	if(!regExp.test(memId)){
		alert("ID값이 유효하지 않습니다");
		$("#memId").focus();
		return; 
		}	

// DB에서 중복검사 수행

$.ajax({
	url : ""
	,type : "post"
	,data : "{memId : memId, 'flag' : 'CHKID'}"
	,dataType : "json"
	,success : function(data){
		console.log(data);
	}
	,error : function(xhr){
		console.error(xhr);
	}
});
		
		
		
		
		
}

