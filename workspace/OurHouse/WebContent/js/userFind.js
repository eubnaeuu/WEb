/**
 * 회원 프로필 조회
 */

$('.user .user_image').click(function(){
	var userId = $(this).siblings()[0].value;
	$.ajax({
		url : '/member/user.do'
		, type : 'get'
		, data : {'userId' : userId}
		, success : function(data){
			goUserMyPage(data);
		}
		, error : function(xhr){
			alert("회원 프로필 검색이 실패했습니다. 관리자에게 문의하세요.\n 오류발생 : " + xhr.status);
		}
	})
})
