/**
 * 좋아요 하는 메서드
 * @param postNo
 * @returns
 */
function likePost(postNo) {
	
	var sessionId = $("#sessionId").val();
	
	$.ajax({
		url: "/like/insert.do"
		,type: "post"
		,data: { "postNo" : postNo 
				,"sessionId" : sessionId }
		,dataType: "json"
		,success: function(data){
			console.log(data);
			if(data.msg == "성공") {
				makeLiked(postNo);
			}else {
				alert("좋아요에 실패하였습니다.");
			}
		}
		,error: function(xhr){
			alert("좋아요에 실패하였습니다. 관리자에게 문의해 주세요.\n오류코드 : " + xhr.status)
			console.log(xhr);
		}
	});
}

/**
 * 화면에 좋아요로 변경되는 메서드
 * @author 이경륜
 */
function makeLiked(postNo) {
	
	$(".post-like").attr("onclick", "dislikePost("+postNo+")");
	$(".post-like").children().first().remove();
	$(".post-like").prepend("<i class='fas fa-heart'></i>");
	
	var likeCount = parseInt($("#likeCount").text().trim())+1;
	$("#likeCount").text(likeCount);
	
}


/**
 * 좋아요 취소하는 메서드
 * @param postNo
 */
function dislikePost(postNo) {
	
	var sessionId = $("#sessionId").val();
	
	$.ajax({
		url: "/like/delete.do"
		,type: "post"
		,data: { "postNo" : postNo
				,"sessionId" : sessionId}
		,dataType: "json"
		,success: function(data){
			console.log(data);
			if(data.msg == "성공") {
				makeDisliked();
			}else {
				alert("좋아요 취소에 실패하였습니다.");
			}
		}
		,error: function(xhr){
			alert("좋아요 취소에 실패하였습니다. 관리자에게 문의해 주세요.\n오류코드 : " + xhr.status)
			console.log(xhr);
		}
	});
}

/**
 * 화면에 좋아요가 취소되는 메서드
 * @author 이경륜
 */
function makeDisliked(postNo) {
	$(".post-like").attr("onclick", "likePost("+postNo+")");
	$(".post-like").children().first().remove();
	$(".post-like").prepend("<i class='far fa-heart'></i>");
	
	var likeCount = parseInt($("#likeCount").text().trim())-1;
	$("#likeCount").text(likeCount);
	
}