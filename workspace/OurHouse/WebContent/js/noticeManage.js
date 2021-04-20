$('.delete-btn').click(function(){
	var noticeNo = $(this).siblings()[0].value;
	
    $('.modal-container').fadeIn(100);
    $('.popup-title').text("🤔 해당 게시글을 삭제하시겠습니까?");
    $('.btn-agree').text('삭제하기');

    $('.btn-agree').off().click(noticeNo, function(){
        console.log(noticeNo);
    	$('.modal-container').fadeOut(100);
    	deleteNotice(noticeNo);
    })
})

$('.btn-cancel').click(function(){
    $('.modal-container').fadeOut(100);
})

// Modal창에서 삭제하기 버튼 클릭 시 호출되는 메서드
function deleteNotice(data){
	var pdata = {"noticeNo" : data};
	$.ajax({
		url : "/admin/deleteNotice.do"
		, type : "post"
		, data : pdata
		, success : function(data){
		    goNoticeList();
		}
		, error : function(xhr){
			alert("에러 발생 : " + xhr.status);
		}
	})
}

function goNoticeList(){
	window.location = "http://localhost:9090/admin/noticeList.do";
}

