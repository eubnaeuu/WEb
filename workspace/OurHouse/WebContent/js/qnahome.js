/* 
    경우의 수
    
    인풋에 R1등 값 넣기


*/

// 필터요소 드롭다운 이벤트
$('.dropdown').mouseover(function(){
    $(this).children().last().show();
})

$('.dropdown').mouseout(function(){
    $(this).children().last().hide();
})

/**
 * 각 필터요소에 클릭 이벤트 지정
 */
$('.dropdown-content > li').click(function(){

    /* 필터 대분류
       정렬 filter-sort 
       주거형태 filter-houseType
       스타일 filter-style
       컬러 filter-color
       공간 filter-space
    */

    // 필터의 대분류를 저장
    var selectFilterType = $(this).parent().attr('class').split(' ')[1];

    // 필터의 소분류 저장 사용자에게 선택된 필터를 보여주기 위해 클릭한 요소의 이름을 불러옴
    // ex) 그레이, 주방 등 선택된 필터 요소
    var Filterset = $(this).text();

    // DB에 저장된 R1과 같은 필터 분류 코드
    var FilterCode = $(this).attr('value');

    // 요소가 선택되면 초기화 버튼 보이기
    $('#btnFilterReset').show();

    // 선택 요소 대분류가 정렬인 경우
    if(selectFilterType == "filter-sort"){
        filterSort(Filterset, FilterCode);
    }
    
    // 선택 요소 대분류가 주거형태인 경우
    else if(selectFilterType == "filter-houseType"){
        filterHouseType(Filterset, FilterCode);
    }
    
    // 선택 요소 대분류가 공간인 경우
    else if(selectFilterType == "filter-room"){
    	filterRoom(Filterset, FilterCode);
    }
    // 선택 요소 대분류가 스타일인 경우
    else if(selectFilterType == "filter-style"){
        filterStyle(Filterset, FilterCode);
    }
    
    // 선택 요소 대분류가 컬러인 경우
    else if(selectFilterType == "filter-color"){
        filterColor(Filterset, FilterCode);
    }
    
    // 선택 요소 대분류가 평수인 경우
    else if(selectFilterType == "filter-space"){
        filterSpace(Filterset, FilterCode);
    }
    else{
        console.log('필터에러 => ' + selectFilter);
    }

    // 드롭다운 사라짐
    $(this).parent().hide();
})

// 필터 제거 버튼
$('.search--filter').click(function(){
    $(this).children('span').text(null);
    $(this).hide();
    $(this).next().val(null);
    
    if(!$('.search--value').text()){
        $('#btnFilterReset').hide();
    }
    activeFilter();
})

// 필터 초기화 버튼
$('#btnFilterReset').click(function(){
    /* 
    console.log($('.value--sort').val());
    console.log($('.value--houseType').val());
    console.log($('.value--style').val());
    console.log($('.value--color').val());
    console.log($('.value--space').val()); */

    $('.search--value').text(null);
    $('.search--value').parent().hide();

    $('.apply-value').val(null);
    $(this).hide();

    activeFilter();
})

function filterSort(filter, filtercode){
    // 사용자에게 보여줄 선택 요소의 text값 저장
    // 선택 요소 분류에 맞는 span에 저장
    $('.select--sort').children().first().text(filter);

    // DB 쿼리에 쓰일 선택 요소의 코드값 저장
    // input에서 type이 hidden인 요소에 저장
    $('.value--sort').val(filtercode);

    // 사용자에게 보여질 선택된 요소
    $('.select--sort').show();

    activeFilter();
}

function filterHouseType(filter, filtercode){
    $('.select--houseType').children().first().text(filter);
    $('.value--houseType').val(filtercode);

    $('.select--houseType').show();
    activeFilter();
}

function filterStyle(filter, filtercode){
    $('.select--style').children().first().text(filter);
    $('.value--style').val(filtercode);

    $('.select--style').show();
    activeFilter();
}

function filterColor(filter, filtercode){
    $('.select--color').children().first().text(filter);
    $('.value--color').val(filtercode);

    $('.select--color').show();
    activeFilter();
}

function filterSpace(filter, filtercode){
    $('.select--space').children().first().text(filter);
    $('.value--space').val(filtercode);

    $('.select--space').show();
    activeFilter();
}

function filterRoom(filter, filtercode){
	$('.select--room').children().first().text(filter);
	$('.value--room').val(filtercode);
	
	$('.select--room').show();
	activeFilter();
}

// 모든 단계에서 이벤트 발생
// ajax 처리 ...
function activeFilter(){

    /* form.serialize() */
    var filterform = $('.filter--setting').children('.apply-value');

    var filter = new Array();

    for(var i = 0 ; i < filterform.length ; i++){
        // 폼에 input요소중 값이 있는 요소를 읽어옴
        if(filterform[i].value){
            filter.push(filterform[i].value);
        }
    }

    console.log(filter);
    
    $.ajax({ 
    	url : '/qna/list.do'
//   		url : '/views/qna/commonJson_test.jsp'
		, type : 'post'
		, dataType : 'json'
		, data : { "filterArr" : filter }
    	, success: function(data){ 
    		console.log("성공");	
    		console.log(data);	
    		setCategoryPage(data);
		}
    	,error : function(xhr){
    		console.log("오류발생" + xhr);
    	}
	});
    
}

function setCategoryPage(data){
	$('.section--board').children().hide();
	
	var str = "";
	str +='    <div class="inner">';
	str +=' <div class="QnA-container">';
	
	if(data.length > 0) {
		console.log(data[0].postTitle);
		for(var i =0 ;  i < data.length ; i++){
			var location = "location.href='/qna/detail.do?postNo="+ data[i].postNo +"'";
			str +=
				 ' <div class="writecontainer" onclick="'+location+'">'
				+'  <div class="write-box">'
				+'   <div class="write-content">'
				+'    <div class="write_title">'
				+     data[i].postTitle
				+'    </div>'
				+' <div class="write_comment">'
				+     data[i].postContent
				+' </div>'
				+'</div>'
				+' <div class="write_info">'
				+'  <div                                                                              class="writer">'
				+' <span>'+'by'+'</span>'
				+'   <span>'
				+ 	  data[i].memId
				+'   <span>'
				+'  </div>'
				+'   <div class="write_date">'
				+ 	  data[i].postDate
				+'   </div>'
				+'<div class="dashed_vertical"></div>'
				+' <div class="write_comment">'
				+' <span>'+'댓글'+'</span>'
				+' <span>'
				+	  data[i].repCount
				+' <span>'
				+' </div>'
				+' <div class="dashed_vertical"></div>'
				+'  <div class="write_lookup">'
				+'     <span>'+'조회'+'</span>'
				+'     <span>'
				+	data[i].postView
				+'	   </span>'
				+' 	</div>'
				+' </div>'
				+'</div>'	;
			if(data[i].fileFileNm!=null) {	
				str += ' <div class="writeImg-box">'
					+' <img src="../../image/uploads/'+data[i].fileFileNm  +'" alt="">'
				+' </div>' ;
			 }	
				str += '</div>' ;
		}
	str+='</div>';
	str+='</div>';
	}else{
		str+='게시글이 없습니다.';
		str+='</div>';
		str+='</div>';
	}
	
	$('.section--board').append(str);
	
	
}

