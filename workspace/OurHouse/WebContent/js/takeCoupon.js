$('.btn-take').click(function(){
	
	// 회원정보
	var cpMgNo = $(this).siblings()[0].value;
	var cpStock = $(this).siblings()[1].value;
	var cpPrice = $(this).siblings()[2].value;
	var memId = $(this).siblings()[3].value;
	
	console.log(cpMgNo);
	console.log(cpStock);
	console.log(cpPrice);
	console.log(memId);
	console.log($(this).siblings()[0].value);
	console.log($(this).siblings()[1].value);
	console.log($(this).siblings()[2].value);
	console.log($(this).siblings()[3].value);
	
    // 로그인을 하지 않았을 시
    if(memId == undefined || memId == "null" || memId == null || memId == " "){
    	alert("로그인 후 이용해 주세요");
    	return;
    }
  
    
    var memPoint =$(this).siblings()[4].value;
    //console.log(memPoint);
    //var memPoint = $(this).siblings()[1].value;
    // 쿠폰정보 
   // var couponPrice = $(this).siblings()[2].value;
    console.log(memPoint);
    // 포인트가 부족할 시
    if(memPoint < cpPrice-1){
    	alert("포인트가 부족합니다. "+"(현재포인트 : "+memPoint+"포인트)");
    	return;
    }else if(memPoint >= cpPrice-1 ){
    	
    }
    
   // var cpMgNo = $(this).siblings()[3].value;
  //  var stock = $(this).siblings()[4].value;
    
    var pdata = {"memId" : memId
    			, "memPoint" : memPoint
    			, "couponPrice" : cpPrice
				, "cpMgNo" : cpMgNo
				, "stock"  : cpStock} ;
    
    // 확인 메세지
    var con_test = confirm("쿠폰을 발급받으면 환불이 불가능 합니다. 진행하시겠습니까?");
	    // 확인 시
	    if(con_test == true){
	    	
	    	$.ajax({
	    		url : '/takeCoupon.do'
    			, type : 'post'
				, dataType : 'json'
				, data : pdata
				, success : function(data){
					console.log(data.msg);
					alert("쿠폰 받기 성공! 마이페이지에서 확인해 주세요");
				}
	    	, error : function(xhr){
	    		alert("에러 발생 : " + xhr.status);
	    	}
	    	});
	    	
    	}
	    // 취소시  돌아가기
		else if(con_test == false){
		  return;
		}
    
    
});

