/**
 * 회원정보수정페이지 / 이미지업로드 js파일
 */

/* 
임건이 생각한 데이터 주기

var noname = new Array();

noname[0] = {
    "fileURL" : ""
   ,"fileName" : ""
   ,"contents" : ""
} 

noname[1] = {
    ~
}

~

noname[5] = {
    ~
}

위와 같이 5개의 배열공간에 각각 게시물의 정보를 넣을수 있도록 배열안에 객체로 선언

i번째 인덱스에 저장
naname[i].fileURL = "파일정보"

이제 이 배열 변수를
ajax 혹은 submit, get?
으로 전달한다.
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
      console.log(reader);
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
  }

  /* 추가 버튼 이전에 위에 복제하고 재정의한 작성폼을 추가해줌 */
  $(input).before(addform);

  /* 추가시 원래 있던 사진의 이미지를 그대로 가져오는 문제가 있음 */
  /* 이 이미지를 삭제하고 이미지 업로드를 띄우기 위해 정의함. */
  $(addform).children().children().children().last().children().children().first().attr('src',null);
  $(addform).children().children().children().first().children().first().val('');
  $(input).prev().children().children().children().last().hide();
  $(input).prev().children().children().children().first().show();

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