$(document).ready(function() {
	sessionId = $("#sessionId").val()
});
function zoomIn() {
	var hover = event.srcElement.previousElementSibling;
	hover.style.transform = "scale(1.08)";
	hover.style.zIndex = 1;
	hover.style.transition = "all 0.5s";
}

function zoomOut() {
	var hover = event.srcElement.previousElementSibling;
	hover.style.transform = "scale(1)";
	hover.style.zIndex = 0;
	hover.style.transition = "all 0.5s";
}

//$(".dropdown-content a").on('click',function(){
//console.log($(this).attr('title'));
//});

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

	var thisSpan = document.getElementsByClassName(targetId);
	var orgName = "user-follow " + targetId;
	var chgName = "user-following " + targetId;
	console.log(thisSpan[0].innerHTML);
	for(var i = 0 ; i < thisSpan.length ; i++) {
		thisSpan[i].innerHTML = "팔로잉";
		$(orgName).attr("onclick", "unfollow('"+targetId+"')");
		$(chgName).attr("class", orgName);
		$(chgName).css("color", "#a4b5bd");
	}
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
	var thisSpan = document.getElementsByClassName(targetId);
	var orgName = "user-following " + targetId;
	var chgName = "user-follow " + targetId;
	console.log(thisSpan[0].innerHTML);
	for(var i = 0 ; i < thisSpan.length ; i++) {
		thisSpan[i].innerHTML = "팔로우";
		$(orgName).attr("onclick", "follow('"+targetId+"')");
		$(chgName).attr("class", orgName);
		$(chgName).css("color", "#35C5F0");
	}
}


/**
 * 좋아요 하는 메서드
 * @param postNo
 * @author 이경륜
 */
function like(thisPost, postNo) {
		
	var sessionId = $("#sessionId").val();
	
	$.ajax({
		url: "/like/insert.do"
		,type: "post"
		,data: { "postNo" : postNo 
				,"sessionId" : sessionId }
		,dataType: "json"
		,success: function(data){
			console.log(data);
			if(data.msg == "성공") {
				makeLiked(thisPost, postNo);
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
function makeLiked(thisPost, postNo) {
	
	// 좋아요 하트 변화
	$(thisPost).attr("onclick","dislike(this,"+postNo+")");
	$(thisPost).children().first().remove();
	$(thisPost).prepend("<i class='fas fa-heart'></i>");

	// 좋아요 수 증가
	var likeCount = parseInt($(thisPost).children().last().text().trim())+1;
	$(thisPost).children().last().text(likeCount);
	
}


/**
 * 좋아요 취소하는 메서드
 * @param postNo
 */
function dislike(thisPost, postNo) {

	var sessionId = $("#sessionId").val();
	
	$.ajax({
		url: "/like/delete.do"
		,type: "post"
		,data: { "postNo" : postNo
				,"sessionId" : sessionId}
		,dataType: "json"
		,success: function(data){
			console.log(data);
			if(data.msg == "성공") {
				makeDisliked(thisPost, postNo);
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
function makeDisliked(thisPost, postNo) {
	
	// 좋아요 하트 변화
	$(thisPost).attr("onclick","like(this,"+postNo+")");
	$(thisPost).children().first().remove();
	$(thisPost).prepend("<i class='far fa-heart'></i>");

	// 좋아요 수 감소
	var likeCount = parseInt($(thisPost).children().last().text().trim())-1;
	$(thisPost).children().last().text(likeCount);
	
}

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

//필터 제거 버튼
$('.search--filter').click(function(){
	$(this).children('span').text(null);
	$(this).hide();
	$(this).next().val(null);

	if(!$('.search--value').text()){
		$('#btnFilterReset').hide();
	}
	activeFilter();
})

//필터 초기화 버튼
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


//모든 단계에서 이벤트 발생
//ajax 처리 ...
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
		url : '/main.do'
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
	console.log(data);
	$('.section--board').children().hide();

	var str = "";
	str +='<div class="board">';
	str +='  <div class="inner">';

	if(data.length > 0) {
		for(var i =0 ;  i < data.length ; i++){
			str +=
				  '<div class="grid_test">' 
				+'  <div class="profile">'
				+'   <ul class="user">'
				+'     <li class="user_image">'
				+'     <a href="/member/user.do?memId='+data[i].memId +'">'
				+'    <img src="/image/uploads/'+data[i].memPrStrImgNm +'" alt="">'
				+'     </a>'
				+'     </li>'
				+'     <li class="user_info">'
				+'      <div class="follow">'
				+'        <a href="#">'
				+'          <span class="user-id">'+data[i].memId+'</span>'              
				+'   	  </a>'
				+'    <span class="dot">·</span>'
				+'    <a href="#">'    ;
			if(" "===data[i].memId){
				str +='<span class="user-follow">'+'팔로우'+'</span>'  ;
			}else{
				if("Y"===data[i].followYN){
				 str +='<span class="user-following">'+'팔로잉'+'</span>'	;
				}else{
				 str +='<span class="user-follow">'+'팔로우'+'</span>'	  ;
				}
			}	
			var location = "location='/photo/detail.do?postNo="+ data[i].postNo +"'";
			str += '    </a>'
			+'  </div>'
			+'  <div class="user-introduce">'
			+ 	  data[i].memIntro
			+'   </div>'
			+' </li>'
			+' </ul>'
			+' </div>'
			+' <div class="thumnail">'
			+'  <img src="/image/uploads/'+data[i].fileFileNm +'" alt="">'
			+'  <div class="mask" onmouseenter="zoomIn()" onmouseleave="zoomOut()" onclick="'+location+'">'
			+'    <div class="thumnail-view">'
			+'     <ul class="view_chart">'
			+'      <li>'+'조회수'+'</li>'
			+'      <li>'+data[i].postView+'</li>'
			+'     </ul>'
			+'	 </div>'
			+' 	</div>'
			+' </div>'
			+'<div class="likeandcomment">';
			
				
				if("Y" === data[i].likeYN) {
					str += '<div class="like" onclick="dislike(this, '+ data[i].postNo + ')">'
                    	+		'<i class="fas fa-heart"></i>'
                    	+		'<span class="like_num">'+data[i].likeCount+'</span>'
                    	+   '</div>';
				}else{
					str += '<div class="like" onclick="like(this, '+ data[i].postNo + ')">'
                	+			'<i class="far fa-heart"></i>'
                	+			'<span class="like_num">'+data[i].likeCount+'</span>'
                	+		'</div>';
				}
				
		str	+=   '<div class="comment">'
				+'   <i class="far fa-comment-alt"></i>'
				+'   <span class="comment_num">'+data[i].repCount+'</span>'
				+'</div>'
			+'</div>'
			+'<div class="user-description">'
				+ data[i].fileCn
			+'</div>'         ;
					
			if(!(" " == data[i].repMemId)){ 
		str +=' <div class="user-card">'
				+' <div class="user_image">'
					+'<a href="/member/user.do?memId='+data[i].repMemId+'">'
						+'<img src="../../image/uploads/'+data[i].repPrImgNm+'" alt="">'
					+'</a>'
				+'</div>'
				+'<div class="user_comment">'
				+'	<p class="user-comment">'
				+'  	<span class="user_id">'+data[i].repMemId+'</span>'
				+		 " "+data[i].repContent
				+'	</p>'
				+'</div>'
				+'</div>'    ;
			}
			str += '</div>'     ;
		}
//		str+='</div>';
//		str+='</div>';
	}else{
		str+='게시글이 없습니다.'  ; 
		str+='</div>' ;
		str+='</div>';
	}
	
	$('.section--board').append(str);
	
}	