$(function(){
    $('#input_comment').on('input',function(){
        if($('#input_comment').val()==''){
            $('#comment_submit').attr("disabled",true);
            $('#comment_submit').css("color",'#93e6ff');
        }
        else{
            $('#comment_submit').attr("disabled",false);
            $('#comment_submit').css("color",'#35C5F0');
        }
    });
//    console.log("hi");
//    console.log(parseInt($("#repCount").text().trim())+1);
    console.log(parseInt($(".comment-number").text().trim())+1);
})

/**
 * 등록할 댓글을 ajax로 보내는 함수
 * @param postNo
 * @author 이경륜
 */
function insertReply(postNo){
	
	console.log($("#input_comment").val());
	if($("#input_comment").val() == "") {
		alert("댓글 내용을 입력해 주세요.");
		$("#input_comment").focus();
		return;
	}
	
	if(!confirm("댓글을 등록하시겠습니까?")) return;
	
	// 댓글내용
	var replyContent = $("#input_comment").val();
	var memId = $("#sessionId").val(); 
	
	$.ajax({
		url: "/reply/write.do"
		,type: "post"
		,data: { "postNo" : postNo
				,"memId" : memId
				,"replyContent" : replyContent }
		,dataType: "json"
		,success: function(data) {
			console.log(data);
			if(data.msg == "성공"){ // 성공시 기존 댓글에 append로 새 댓글 추가
				$("#input_comment").val('');
				appendReply(data.replyVO);
			}else { // 실패시 경고창
				alert("댓글 등록에 실패하였습니다.");
			}
		}
		,error: function(xhr) {
			alert("댓글 등록에 실패하였습니다. 관리자에게 문의해 주세요.\n오류코드 : " + xhr.status);
			console.log(xhr);
		}
		
	});
}

/**
 * DB에 등록한 댓글을 화면에 넣는 함수
 * @param data db에 등록한 정보를 담은 replyVO
 * @author 이경륜
 */
function appendReply(data) {
	console.log(data)
	var sessionId = $("#sessionId").val(); 
	
	// data로 append 하는 메서드
	var str = "";
	str += '<li value="'+ data.replyNo +'" class="other-comment">'
			+  '<div class="user-profile">'
				+  '<img src="../../image/uploads/'+ data.repPrStrImgNm +'" alt="">'
			+  '</div>'
			
			+  '<div class="user-comment">'
				+  '<div class="nameandcomment">'
					+  '<span class="username">'+ data.memId +'</span>'
					+  '<p class="usercomment">'
						+  data.replyContent
					+  '</p>'
				+  '</div>'
					
					+  '<div class="comment-report">'
			
					+  '<ul class="report-list">'
						+  '<li>'
							+  '<span>'+ data.replyDate +'</span>'
						+  '</li>';
						
					
				if (sessionId == data.memId) { 
				str+=  '<div class="vertical-dot"></div>'
					    +  '<li class="report">'
						+  '<span onclick="deleteReply('+ data.replyNo + ')">삭제</span>'
						+  '</li>';
				}
				
				if (!sessionId == data.memId) {
				str +=  '<div class="vertical-dot"></div>'
 					  +   '<li class="report report-comment" onclick="reportThis(' + data.replyNo + ')">'
 					  		+  '<span>신고</span>'
					  +   '</li>';
				}
				str	+=  '</ul>'
				+  '</div>'
			+  '</div>'
		+ '</li>';
		
	$(".usercomment-container").append(str);
	
	// 댓글숫자1추가
	var repCount = parseInt($("#repCount").text().trim())+1;
	$("#repCount").text(repCount);
	$(".comment-number").text(repCount);
}

/**
 * 댓글 삭제하기위해 ajax로 핸들러한테 보내는 함수
 * @param replyNo
 * @returns
 * @author 이경륜
 */
function deleteReply(replyNo){
	
	if(!confirm("댓글을 삭제하시겠습니까?")) return;
	console.log(replyNo);
	$.ajax({
		url: "/reply/delete.do"
		,type: "post"
		,data: { "replyNo" : replyNo }
		,dataType: "json"
		,success: function(data) {
			console.log(data);
			if(data.msg == "성공"){ // 삭제된 댓글 remove
				removeReply(replyNo); // db 로들고갔던 그 댓글번호
			}else {
				alert("댓글 삭제에 실패하였습니다.");
			}
		}
		,error: function(xhr) {
			alert("댓글 삭제에 실패하였습니다. 관리자에게 문의해 주세요.\n오류코드 : " + xhr.status);
			console.log(xhr);
		}
		
	});
}


/**
 * DB에서 삭제한 댓글을 화면에서 지우는 함수
 * @param replyNo삭제선택한 댓글 번호 
 * @author 이경륜
 */
function removeReply(replyNo) {
	replyNo = "#" + replyNo;
	console.log("지우는 함수에서 찍는거 로그");
	console.log(replyNo);
	$(replyNo).remove();
	
	// 댓글 숫자1감소
	var repCount = parseInt($("#repCount").text().trim())-1;
	$("#repCount").text(repCount);
	$(".comment-number").text(repCount);
}










