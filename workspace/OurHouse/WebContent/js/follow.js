/**
 * 팔로우 하는 메서드
 * @param targetId
 * @returns
 */
function follow(targetId) {
	

	
	var sessionId = $("#sessionId").val();
	
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
				alert("팔로우에 실패하였습니다.");
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
 */
function unfollow(targetId) {
	
	var sessionId = $("#sessionId").val();
	
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