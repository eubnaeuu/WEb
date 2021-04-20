/**
 * 댓글 삭제
 */

function deleteReply(target){
	var replyNo = $(target).siblings()[0].value;
	console.log(replyNo);
	
	var pdata = {'replyNo' : replyNo};
	
	if(!confirm("댓글을 삭제하시겠습니까?")) return;
	
	
	$.ajax({
		url : '/admin/deleteReply.do'
	    ,type : 'post'
		,data : pdata
		,dataType : "json"
		,success : function(data){
			console.log(data); // 실패 뜸
			if(data.msg == "성공"){
				goReportList();
			} else {
				alert("신고 댓글 삭제에 실패했습니다.");
				return;
			}
		}
		, error : function(xhr){
			alert("신고 댓글 삭제에 실패했습니다.\n오류코드 : " + xhr.status);
		}
	});
}

/**
 * 게시물 삭제
 */
function deletePost(target){
	var postNo = $(target).siblings()[0].value;
	console.log(postNo);
	if(!confirm("게시글을 삭제하시겠습니까?")) return;
	
	$.ajax({
		url : "/admin/deletePost.do"
		, type : "post"
		, data : {"postNo" : postNo}
		, dataType : "json"
		, success : function(data){
			console.log(data);
			if(data.msg == "성공"){
				goReportList();
			} else {
				alert("신고 게시글 삭제에 실패했습니다.");
			}
		}
		, error : function(xhr){
			alert("신고 게시글 삭제에 실패했습니다.\n 오류코드  : " + xhr.status);
		}
	});
	
}

function goReportList(){
	window.location = "http://localhost:9090/admin/reportList.do";
}