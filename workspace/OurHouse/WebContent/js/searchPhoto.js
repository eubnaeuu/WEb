/**
 * 해시태그 클릭시 해당 태그를 포함하는 사진 글 검색하는 메서드
 * @author 조예슬
 * @param data 해당 hashtag
 * @returns
 */
function searchTag(data){
	console.log(data);
	
  $.ajax({ 
    	url : '/search.do'
		, type : 'get'
		, data : { "hashtag" : data }
    	, success: function(data){ 
    		console.log(data);
    		
		}
    	,error : function(xhr,request,status,error){
    		console.log("오류발생" + xhr.status);
    		console.log("오류발생" + request);
    		console.log("오류발생" + request.responseText);
    		console.log("오류발생" + error);
    	}
	});
		
}




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
    $('.search--init').show();

    // 선택 요소 대분류가 정렬인 경우
    if(selectFilterType == "filter-sort"){
        filterSort(Filterset, FilterCode);
    }
    
    // 선택 요소 대분류가 주거형태인 경우
    else if(selectFilterType == "filter-houseType"){
        filterHouseType(Filterset, FilterCode);
    }
    
    // 선택 요소 대분류가 스타일인 경우
    else if(selectFilterType == "filter-style"){
        filterStyle(Filterset, FilterCode);
    }
    
    // 선택 요소 대분류가 컬러인 경우
    else if(selectFilterType == "filter-color"){
        filterColor(Filterset, FilterCode);
    }
    
    // 선택 요소 대분류가 공간인 경우
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
        $('.search--init').hide();
    }
    activeFilter();
})

// 필터 초기화 버튼
$('.search--init').click(function(){
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
}
