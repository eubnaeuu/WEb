/**
 * 공지사항 등록
 */

function addNotice(){
	$.ajax({
		url : "/admin/insertNotice.do"
		, type : "post"
		, data : $("#insertNotice").serialize()
		, success : function(data){
			goNoticeList();
		}
		, error : function(xhr){
			alert("에러 발생 : " + xhr.status);
		}
	});
}

function goNoticeList(){
	window.location = "http://localhost:9090/admin/noticeList.do";
}

function chkTitle(){
	var title = $("#noticeTitle").val();
	
	if(title == ""){
		$("#checkTitle").text("필수입력사항입니다.").css("color", "red");
	}
}

function chkContent(){
	var content = $("#noticeContent").val();
	
	if(content == ""){
		$("#checkContent").text("필수입력사항입니다.").css("color", "red");
	}
}


