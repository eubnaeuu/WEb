

// 쿠폰 삭제 버튼 누를 시 
$('.coupon-delete').click(function(){
   var couponNo = $(this).siblings()[1].value;
    $('.modal-container').fadeIn(100);
    $('.popup-title').text("🤔 해당 게시글을 삭제하시겠습니까?");
    $('.btn-agree').text('삭제하기');
    $('.btn-agree').off().click(couponNo, function(){
        console.log(couponNo);
       $('.modal-container').fadeOut(100);
       deleteCoupon(couponNo);
    })

    
})

$('.btn-cancel').click(function(){
    $('.modal-container').fadeOut(100);
})

// Modal창에서 삭제하기 버튼 클릭 시 호출되는 메서드
function deleteCoupon(data){
   var pdata = {"couponNo" : data};
   $.ajax({
      url : "/admin/deletecoupon.do"
      , type : "post"
      , data : pdata
      , success : function(){
          goCouponList();
      }
      , error : function(xhr){
         alert("에러 발생 : " + xhr.status);
      }
   });
}

function goCouponList(){
   window.location = "/admin/coupon.do";
}



function writecoupon(){
	if(!validataCheck()) {
		return;
	}
	
	if(!confirm("쿠폰을 추가하시겠습니까?")) return;
	
	$.ajax({
		url: "/admin/newcoupon.do"
		,type: "post"
		,data: $("#writeCoupon").serialize()
		,dataType: "json"
		,success: function(data) {
			console.log(data);
			alert(data);
			//회원가입 완료되면 로그인 페이지로 이동
			$(location).attr('href','http://localhost:9090/admin/coupon.do');
		}
		,error: function(xhr) {
			alert("쿠폰등록에 실패했습니다.\n오류코드 : " + xhr.status)
		}
	});
	
	
}


function validataCheck(){
	 var shopId = $("#shopId").val();
	 console.log(shopId);
	 var price = $("#price").val();
	 var stock = $("#stock").val();
	 
	
	 if(isNaN(price) || price=="") {
		 alert("숫자를 입력해주세요.")
		 return;
	 }
	 if(isNaN(stock) || stock=="") {
		 alert("숫자를 입력해주세요.")
		 return;
	 }
	 
	 if(price > 1000000){
		 alert("쿠폰금액은 1,000,000 이하로 입력해주세요.")
		 return;
	 }
	 
	 if(stock > 10000){
		 alert("쿠폰은 10,000장 이하로 입력해주세요.")
		 return;
	 }
	 
	 return true;
}




