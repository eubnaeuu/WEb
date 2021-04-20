
$(document).ready(function() {
	sessionId = $("#sessionId").val();
	if(sessionId == " ") {
		$("#input_comment").attr("disabled","disabled");
	}
});
/**
 * 글삭제하는 메서드
 * @param 삭제할 글 번호
 * @author 이경륜
 */
function deletePost(postNo) {
	
	if(!confirm("게시글을 삭제하시겠습니까?")) return;
	
	$.ajax({
		url: "/post/delete.do"
		,type:"post"
		,data: { "postNo" : postNo }
		,dataType: "json"
		,success: function(data){
			console.log(data);
			if(data.msg == "성공") {
				alert("게시글이 삭제되었습니다.");
				location.href = "/main.do"; // 리다이렉트
			}else {
				alert("게시글 삭제에 실패하였습니다.");
			}
		}
		,error: function(xhr){
			alert("게시글 삭제에 실패하였습니다. 관리자에게 문의해 주세요.\n오류코드 : " + xhr.status)
			console.log(xhr);
		}
	});
}

/**
 * 좋아요 하는 메서드
 * @param postNo
 * @author 이경륜
 */
function like(postNo) {
	
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
				alert("로그인 후 이용해 주세요.");
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
	
	$(".post-like").attr("onclick", "dislike("+postNo+")");
	$(".post-like").children().first().remove();
	$(".post-like").prepend("<i class='fas fa-heart'></i>");
	
	var likeCount = parseInt($("#likeCount").text().trim())+1;
	$("#likeCount").text(likeCount);
	
}


/**
 * 좋아요 취소하는 메서드
 * @param postNo
 */
function dislike(postNo) {
	
	$.ajax({
		url: "/like/delete.do"
		,type: "post"
		,data: { "postNo" : postNo
				,"sessionId" : sessionId}
		,dataType: "json"
		,success: function(data){
			console.log(data);
			if(data.msg == "성공") {
				makeDisliked(postNo);
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
	$(".post-like").attr("onclick", "like("+postNo+")");
	$(".post-like").children().first().remove();
	$(".post-like").prepend("<i class='far fa-heart'></i>");
	
	var likeCount = parseInt($("#likeCount").text().trim())-1;
	$("#likeCount").text(likeCount);
	
}

/**
 * 팔로우 하는 메서드
 * @param targetId
 * @author 이경륜
 */
function follow(targetId) {
	
	$.ajax({
		url: "/follow/insert.do"
		,type: "post"
		,data: { "targetId" : targetId 
				,"sessionId" : sessionId }
		,dataType: "json"
		,success: function(data){
			console.log(data);
			if(data.msg == "성공") {
				makeFollowing(targetId);
			}else {
				alert("로그인 후 이용해 주세요.");
			}
		}
		,error: function(xhr){
			alert("팔로우에 실패하였습니다. 관리자에게 문의해 주세요.\n오류코드 : " + xhr.status)
			console.log(xhr);
		}
	});
}

/**
 * 화면에 팔로잉 상태로 변경되는 메서드
 * @author 이경륜
 */
function makeFollowing(targetId) {
	$(".follow").first().first().text("팔로잉");
	$(".follow").attr("onclick", "unfollow('"+targetId+"')");
	$(".follow").attr("class", "following");
}

/**
 * 팔로잉 취소하는 메서드
 * @param targetId
 * @author 이경륜
 */
function unfollow(targetId) {
	
	$.ajax({
		url: "/follow/delete.do"
		,type: "post"
		,data: { "targetId" : targetId 
				,"sessionId" : sessionId }
		,dataType: "json"
		,success: function(data){
			console.log(data);
			if(data.msg == "성공") {
				makeFollow(targetId);
			}else {
				alert("팔로우 취소에 실패하였습니다.");
			}
		}
		,error: function(xhr){
			alert("팔로우 취소에 실패하였습니다. 관리자에게 문의해 주세요.\n오류코드 : " + xhr.status)
			console.log(xhr);
		}
	});
}

/**
 * 팔로우를 가능 상태로 화면에 표시하는 메서드
 * @param targetId
 * @author 이경륜
 */
function makeFollow(targetId) {
	$(".following").first().first().text("팔로우");
	$(".following").attr("onclick", "follow('"+targetId+"')");
	$(".following").attr("class", "follow");
	
}



/**
 * 글화면에서 신고버튼 클릭시 모달창 input 에 글번호 입력
 * @param postNo
 * @returns
 */
function reportThis(no) {
	console.log(sessionId);
	if(sessionId == " "){
		
		alert("로그인 후 이용해 주세요.");
		return;
	}else{
		$("#reportThis").val(no);

		/**
		 * 게시글/댓글 신고 버튼 클릭시 모달창 띄어주는 아이들
		 * @author 임건
		 * @returns
		 */
		$('.report-board').click(function(){
		    $('.modal-container').fadeIn(100);
		    $('.popup-title').text("게시글 신고 사유를 선택해주세요");
		    
		    $('.btn-cancel').click(function(){
		            $('.modal-container').fadeOut(100);
		    })
		    
		    $("#reportThis").attr('name','preport'); // 플래그
		})

		$('.report-comment').click(function(){
		    $('.modal-container').fadeIn(100);
		    $('.popup-title').text("댓글 신고 사유를 선택해주세요");

		    $('.btn-cancel').click(function(){
		            $('.modal-container').fadeOut(100);
		    })
		    
		    $("#reportThis").attr('name','rreport'); // 플래그
		})
	}
}

/**
 * 모달창에서 신고버튼 클릭시 db 로 전송
 * @returns
 */
function submitReport() {
	
	if(!confirm("해당 회원을 신고하시겠습니까?")) return;
	
	$.ajax({
		url: "/report/insert.do"
		,type: "post"
		,data: $("#reportForm").serialize()
		,dataType: "json"
		,success: function(data) {
			console.log(data);
			if(data.msg == "성공"){
				alert("신고 접수에 성공했습니다.\n관리자 확인 후 삭제될 예정입니다. 감사합니다.")
				
			}else {
				alert("로그인 후 이용해 주세요.")
			}
			$('.modal-container').fadeOut(100);
		}
		,error: function(xhr){
			console.log(xhr);
			alert("신고 접수에 실패했습니다. 관리자에게 문의해 주세요.\n오류코드 : " + xhr.status);
		}
	});
}

