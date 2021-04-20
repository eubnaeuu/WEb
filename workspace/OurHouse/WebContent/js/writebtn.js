$('.btn-write').click(function(e){
    $('.dropdown-write').show();

    $('body').click(function(e){
    if(!$(e.target).hasClass("btn-write")){
        $('.dropdown-write').hide();
    }
})
})

$(document).ready(function() {
	sessionId = $("#sessionId").val();
});
/**
 * 사진 글쓰기 버튼
 * 
 */
function writePhoto() {
	if(sessionId == " ") {
		alert("로그인 후 이용해 주세요.");
		return;
	}
	location.href = "/photo/write.do";
}

/**
 * 질문글 글쓰기 버튼
 * 
 */
function writeQna() {
	if(sessionId == " ") {
		alert("로그인 후 이용해 주세요.");
		return;
	}
	location.href = "/qna/write.do";
}