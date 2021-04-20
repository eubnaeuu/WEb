/**
 * 마이페이지 사진 상세페이지 가져옴 
 * @author 조예슬
 * @param data
 * @returns
 */
function showMemPhoto(data){
    $.ajax({ 
    	url : '/member/photo.do'
		, type : 'post'
		, dataType : 'json'
		, data : { "memId" : data }
    	, success: function(data){ 
    		
    		$('.section-profile-subnav').children().children().children('li').css('border-bottom','');
    		$('.section-profile-subnav').children().children().children('li').children('a').css('color','');
    		$('.section-profile-subnav').children().children().children('li:nth-child(2)').css('border-bottom','4px solid #35C5F0');
    		$('.section-profile-subnav').children().children().children('li:nth-child(2)').children('a').css('color','#35C5f0');
    		makePhotoTable(data);
		}
    	,error : function(xhr,request,status,error){
    		console.log("오류발생" + xhr.status);
    	}
	});
	
}
/**
 * 상세페이지 사진 리로드 
 * @param data
 * @returns
 */
function makePhotoTable(data){
	console.log(data);
	$('.contents-container').children().hide();
	
	var str = "";
	str +='  <div class="picturecontainer">';
	str +='  <div class="picturetitle">';
	str +='  <div class="title">';
	str +=  '사진';
	str +='   <span class="pictures_sum">';
	str += data.length ;
	str += ' </span>' ;
	str += '  </div>' ;
	str += '  </div>' ;
	str += '  <div class="pictures-container">' ;
	if(data.length>0){
	for(var i =0 ;  i < data.length ; i++){
		str +=
			' <div class="box-container">'
			+' 		<div class="picture-box">'
			+'			<a href="/photo/detail.do?postNo='+data[i].postNo+'">'
			+' 				<img src="../image/uploads/'+data[i].fileFileNm +'" alt=""/>'
			+'			</a>'
			+'		</div>'
			+'	<div class="picture-title">'  ;
	if(data[i].hashtag!=undefined){
		str += data[i].hashtag ;
	}	
    	str += '	</div>'
		    +'</div>' ;
	}
	str+='</div>';
	str+='</div>';
	}else{
		str+='<br><br>'+'게시글이 없습니다.';
		str+='</div>';
		str+='</div>';
		
	}
	$(".contents-container").append(str);
	$(".contents-container").addClass("float--right");
}



/**
 * 마이페이지 질문과답변 상세페이지 가져옴 
 * @author 조예슬
 * @param data
 * @returns
 */
function showMemQnA(data){
    $.ajax({ 
    	url : '/member/mypage.do'
		, type : 'post'
		, dataType : 'json'
		, data : { "memId" : data }
    	, success: function(data){ 
    		

    		
    		$('.section-profile-subnav').children().children().children('li').css('border-bottom','');
    		$('.section-profile-subnav').children().children().children('li').children('a').css('color','');
    		$('.section-profile-subnav').children().children().children('li:nth-child(3)').css('border-bottom','4px solid #35C5F0');
    		$('.section-profile-subnav').children().children().children('li:nth-child(3)').children('a').css('color','#35C5f0');
    		console.log(data);
    		makeQnaTable(data);
    			
		}
    	,error : function(xhr,request,status,error){
    		console.log("오류발생" + xhr.status);
    		console.log("오류발생" + request);
    		console.log("오류발생" + request.responseText);
    		console.log("오류발생" + error);
    	}
	});
	
}

/**
 * 질문과 답변 상세페이지 리로드
 * @author 조예슬
 * @param data
 * @returns
 */
function makeQnaTable(data){
	console.log(data);
	$('.contents-container').children().hide();
	
	var str = "";
	str +='   <div class="QnAcontainer">';
	str +='  <div class="QnAtitle">';
	str +='  <div class="title">';
	str +=  '질문과 답변';
	str +='    <span class="QnA_sum">';
	str += data.length ;
	str += ' </span>' ;
	str += '  </div>' ;
	str += '  </div>' ;
	str += '   <div class="QnA-container">' ;
	if(data.length > 0){
	for(var i =0 ;  i < data.length ; i++){
		str +=
			'   <div class="writecontainer" onclick="location.href="/qna/detail.do?postNo='+data[i].postNo+'">'
			+'    <div class="write-box">'
			+'  <div class="write-content">'
			+'   <div class="write_title">'
			+  data[i].postTitle 
			+'</div>' 
			+' <div class="write_comment">'
			+ data[i].postContent
			+'</div>'
			+'</div>'
			+'  <div class="write_info">'
			+'   <div class="writer">'
			+'   <span>'+'by'+'</span>'
			+' <span>'
			+data[i].memId
			+' <span>'
			+' </div>'
			+'   <div class="write_date">'
			+data[i].postDate
			+'</div>'
			+' <div class="dashed_vertical"></div>'
			+'   <div class="write_comment">'
			+'     <span>'+'댓글'+'</span>'
			+'<span>'
			+data[i].repCount	
			+'</span>'
			+'</div>'
			+' <div class="dashed_vertical"></div>'
			+'    <div class="write_lookup">'
			+'   <span>'+'조회'+'</span>'
			+'<span>'
			+data[i].postView
			+'</span>'
			+'</div>'
			+' <div class="write_keyword-box">'
			+'   <a class="write_keyword">'
			+'  <span></span>'
			+'<span>'
			+ data[i].houseId
			+'</a>'
			+'</div>'
			+'</div>' ;
			if(data[i].fileFileNm!=undefined){
			str +=' <div class="writeImg-box">'
			    +' <img src="../image/uploads/'+data[i].fileFileNm +'" alt="">'
			    +'</div>';
		}else{
			str += '';
		}	
			+'</div>'
	}
	str+='</div>';
	str+='</div>';
	str+='</div>';
	str+='</div>';
	str+='</div>';
	}else{
		str+='<br><br>'+'게시글이 없습니다.';
		str+='</div>';
		str+='</div>';
		str+='</div>';
		str+='</div>';
		str+='</div>';
	}
	$(".contents-container").append(str);
	$(".contents-container").addClass("float--right");
}



/**
 * 내가 좋아요 누른 사진들 보기
 * @author 조예슬
 * @param data
 * @returns
 */
function showMemLike(data){
    $.ajax({ 
    	url : '/member/likes.do'
		, type : 'post'
		, dataType : 'json'
		, data : { "memId" : data }
    	, success: function(data){ 
    		console.log(data);

    		$('.section-profile-subnav').children().children().children('li').css('border-bottom','');
    		$('.section-profile-subnav').children().children().children('li').children('a').css('color','');
    		$('.section-profile-subnav').children().children().children('li:nth-child(4)').css('border-bottom','4px solid #35C5F0');
    		$('.section-profile-subnav').children().children().children('li:nth-child(4)').children('a').css('color','#35C5f0');
    		makeLikeTable(data);
    			
		}
    	,error : function(xhr,request,status,error){
    		console.log("오류발생" + xhr.status);
    		console.log("오류발생" + request);
    		console.log("오류발생" + request.responseText);
    		console.log("오류발생" + error);
    	}
	});
	
}

function makeLikeTable(data){
	
$('.contents-container').children().hide();
	
	var str = "";
	str +='  <div class="picturecontainer">';
	str +='  <div class="picturetitle">';
	str +='  <div class="title">';
	str +=  '좋아요';
	str +='   <span class="pictures_sum">';
	str += data.length ;
	str += ' </span>' ;
	str += '  </div>' ;
	str += '  </div>' ;
	str += '  <div class="pictures-container">' ;
	if(data.length>0){
	for(var i =0 ;  i < data.length ; i++){
		str +=
			' <div class="box-container">'
			+' <div class="picture-box">'
			+'			<a href="/photo/detail.do?postNo='+data[i].postNo+'">'
			+' <img src="../image/uploads/'+data[i].fileFileNm +'" alt="">'
			+'			</a>'
			+'</div>'
			+'<div class="picture-title">';
		if(data[i].hashtag==undefined){
			
		}else{
			str += data[i].hashtag;
		}	
		 str+='</div>'
			+'</div>' ;
	}
	str+='</div>';
	str+='</div>';
	}else{
		str+='<br><br>'+'게시글이 없습니다.';
		str+='</div>';
		str+='</div>';
		
	}
	$(".contents-container").append(str);
	$(".contents-container").addClass("float--right");
	
}


