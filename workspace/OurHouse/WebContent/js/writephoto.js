$(document).ready(function () {

	/**
	 * 파일 업로드 위한 js 코드
	 * @author 이경륜
	 */
    $("#btnSubmit").click(function (event) {
    	// submit 버튼으로 submit되는 것 막아줌 (validation 체크후 ajax로 보내기 위함)
    	event.preventDefault();

    	// 업로드된 파일 갯수: java에서 for문 돌려서 파일객체 꺼내기 위해 파악
    	var fileCount = document.getElementsByClassName('write-contents').length;
    	$(".formbtn-group").children().first().val(fileCount); // hidden에 파일갯수추가
    	
    	// 1. 빈칸 유효성 검사
    	if (!validateSelect()) return;
    	// 2. file 존재 여부 검사 (최소1개)
    	if (!validateFiles(fileCount)) return;
    	// 3. 해시태그 합치기 (ex: #해시#태그#디비#저장#방식)
    	makeHashtag();
    	
    	if (!confirm("사진을 등록하시겠습니까?")) return;
    	
//		var fm = document.getElementById("writeform"); // 메인form말고 임시form
//		fm.method = "post";
//		fm.action = "/photo/write.do";
//		fm.enctype = "multipart/form-data";
//		fm.submit();
    	
    	
    	/* ajax로 보내는 방식*/
        // form양식 가져오기
        var form = $('#writeform')[0];

        // form양식을 데이터화시키기
        var data = new FormData(form);

        // form데이터에 필드 추가하고싶으면 아래 append문 이용
        // data.append("CustomField", "This is some extra data, testing");

        // submit버튼 disabled 시키기
        $("#btnSubmit").prop("disabled", true);

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/photo/write.do",
            data: data,
            dataType: "json",
            processData: false, //필수
            contentType: false, // 필수
            cache: false,
            timeout: 600000,
            success: function (data) {
            	$("#btnSubmit").prop("disabled", false);
            	console.log(data);
            	console.log(data.msg);
                if(data.msg == "성공"){
                	location.href = "/photo/detail.do?postNo=" + data.postNo;
                }else{
                	alert("새글 등록에 실패했습니다. 잠시 후 다시 시도해 주세요.");
                }
            },
            error: function (xhr) {
                alert("새글 등록에 실패했습니다. 관리자에게 문의해주세요.\n오류코드 : " + xhr.status);
                $("#btnSubmit").prop("disabled", false);
            }
        });

    });

});



/**
 * 셀렉트박스 빈칸 유효성 검사
 * @author 이경륜
 * @return false 빈칸있음 false 빈칸없음
 */
function validateSelect() {

	if(isEmpty($("#houseId").val())) { 
		alert("형태를 선택하세요.");
		$("#houseId").focus();
		return false;
	}

	if(isEmpty($("#roomId").val())) { 
		alert("공간을 선택하세요.");
		$("#roomId").focus();
		return false;
	}
	
	if(isEmpty($("#spaceId").val())) { 
		alert("평수를 선택하세요.");
		$("#spaceId").focus();
		return false;
	}
	
	if(isEmpty($("#styleId").val())) { 
		alert("스타일을 선택하세요.");
		$("#styleId").focus();
		return false;
	}
	if(isEmpty($("#colorId").val())) { 
		alert("컬러를 선택하세요.");
		$("#houseId").focus();
		return false;
	}
	
	return true;
}

/**
 * 파일 빈칸유효성 검사
 * @author 이경륜
 * @return false 빈칸있음 false 빈칸없음
 */
function validateFiles(fileCount) {
	
	var fileCheck;
	for(var i = 0; i < fileCount ; i++) {
		fileCheck = document.getElementsByName('atchFile'+(i+1))[0].value; // 파일첨부여부 true:첨부 false:미첨부
		if(!fileCheck){
			alert("파일을 첨부해 주세요.");
			return false;
		}
	}
	return true;
}



/**
 * 해시태그 있으면 #내용물 (#으로 구분자추가) 
 * @author 이경륜
 */
function makeHashtag(){
	var hashtags = document.getElementsByClassName("input--hashtag"); // 화면의 hashtag input
	var hashtagsDB = ""; // db로 넘어갈 hashtag
	for(var i = 0 ; i < hashtags.length ; i++){
		if(!isEmpty(hashtags[i].value)){
			hashtagsDB += "#" + hashtags[i].value;
		}
	}
	$(".hashtag-container").children().eq(5).val(hashtagsDB); // input hidden넣어주기
}














/**
 * 기존 js 코드
 * @author 임건
 */
$(document).ready(function() {

    var selectTarget = $('.selectbox select');

    /* 셀렉트 옵션이 변할때마다 실행 */ 
    selectTarget.change(function(){ 
    
        /* 셀렉트된 option의 text, 즉 선택된 값 */
        var select_name = $(this).children('option:selected').text(); 
        
        /* 형제 요소인 label에 표시 */
        $(this).siblings('label').text(select_name); 
        
    }); 
});

/* 이미지 미리보기 및  */
function readURL(input) {
  if (input.files && input.files[0]) {

    /* 파일 리더 객체 생성 */
    var reader = new FileReader();

    /* 파일 리더 객체에 파일 URL data를 저장 -> 프리뷰로 보여주기 위해 사용 */
    reader.readAsDataURL(input.files[0]);

    /* 업로드되어 파일을 읽어오면 실행 */
    reader.onload = function(e) {
      /* image-upload-wrap 클래스가 붙은 div 태그*/
      /* 이 태그는 업로드, '이미지 업로드' 텍스트가 자식으로 있는 div태그임. */
      $(input).parent().hide();

      /* file-upload-image 클래스가 붙은 img 태그 */
      /* 업로드된 사진의 data정보를 읽어 프리뷰로 보여준다. */
      $(input).parent().next().children().children().first().attr('src', e.target.result);

      /* file-upload-content 클래스가 붙은 div 태그*/
      /* 이 태그는 프리뷰로 보는 이미지, 제거버튼이 자식으로 있는 div태그임. */
      $(input).parent().next().show();

      /* 파일 정보 콘솔 */
      //console.log(input.files[0]);
    };

  }
}

/* 삭제시 실행되는 메소드 */
function removeUpload(input) {
  /* 이미지 프리뷰 삭제 */
  $(input).prev().attr('src',null);
  /* 인풋의 URL삭제 */
  $(input).parent().parent().prev().children().first().val(null);
  

  /* file-upload-content 숨김 */
  $(input).parent().parent().hide();
  /* image-upload-wrap 보여줌 */
  $(input).parent().parent().prev().show();

  /* 업로드 이미지를 삭제해서 alt 텍스트가 나오게 되는데 */
  /* file-upload-content를 숨기고 다시 업로드 버튼이 나오도록 */
  /* 새로고침과 같은 기능을 하기위해 정의함. */
}

/* 사진 게시글 작성폼 추가 버튼 실행시 실행되는 메소드 */
function writeAdd(input){

  /* 만약 타이틀 write-form을 가진 작성폼이 5개가 넘는경우 */
  /* 더는 추가되지 않도록 if문 정의 */
  if($(input).parent().children("div[title='write-form']").length < 5){

  /* 삭제버튼의 형제요소인 게시글 작성폼을 복제 */
  var addform = $(input).prev().clone();

  /* 복제한 작성폼에 순번을 정의할 클래스명을 재정의한다. */
  /* 첫 작성폼의 클래스명은 selectform1 마지막폼은 selectform5 */
  var classnumber = parseInt($(addform).attr('class').charAt($(addform).attr('class').length-1)) + 1;
  
  /* 클래스명을 정의 */
  /* 처음 있는 작성폼의 클래스명을 바뀌지 않게 if문 정의 */
  if($(input).parent().children('.selectform1').length > 0){
  $(addform).attr('class',$(addform).attr('class').slice(0,-1) + classnumber);
  
  /* 왼쪽에 파란 박스에 해당 폼이 몇번째 폼인지 알려주기 위해 정의 */
  $(addform).children('p').text(classnumber);
  
  /* file name과 글내용 name에 카운트 붙여주기*/ // 이경륜
  // $(addform).children().children().children().children().first().attr('name')) - 건이가 만들어준거
  
  var preAtchFile = $(addform).children().children().children().children().first();
  preAtchFile.attr('name',preAtchFile.attr('name').slice(0,-1) + classnumber);
  
  var preFileCn = $(addform).children().children().eq(1);
  preFileCn.attr('name',preFileCn.attr('name').slice(0,-1) + classnumber);
  }

  /* 추가 버튼 이전에 위에 복제하고 재정의한 작성폼을 추가해줌 */
  $(input).before(addform);

  /* 추가시 원래 있던 사진의 이미지를 그대로 가져오는 문제가 있음 */
  /* 이 이미지를 삭제하고 이미지 업로드를 띄우기 위해 정의함. */
  $(addform).children().children().children().last().children().children().first().attr('src',null);
  $(addform).children().children().children().first().children().first().val('');
  $(input).prev().children().children().children().last().hide();
  $(input).prev().children().children().children().first().show();

  /* 추가시 원래 있던 글을 그대로 가져오는 문제 해결하기 위한 코드*/
  $(addform).children().children().last().val('');
  }
  
  /* 글 갯수가 5개가 넘을 경우 실행 */
  else{
    $('.modal-container').fadeIn(100);
    $('.popup-title').text("😅 사진글은 최대 5개입니다!");

    $('.btn-cancel').click(function(){
        $('.modal-container').fadeOut(100);
    })
  }
}