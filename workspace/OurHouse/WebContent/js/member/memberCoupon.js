function addCoupon(data){
	console.log(data);
	$.ajax({
		url : '/member/myCoupon.do'
		, type : 'post'
		, data : {"memId" : data}
		, dataType : 'json'
		, success : function(data){
			console.log(data);
			
			$('.section-profile-subnav').children().children().children('li').css('border-bottom','');
    		$('.section-profile-subnav').children().children().children('li').children('a').css('color','');
    		$('.section-profile-subnav').children().children().children('li:nth-child(5)').css('border-bottom','4px solid #35C5F0');
    		$('.section-profile-subnav').children().children().children('li:nth-child(5)').children('a').css('color','#35C5f0');
			
			viewCoupon(data);
		}
		, error : function(xhr){
			alert('에러 발생 : ' + xhr.status);
		}
	});
}

/**
 * 마이페이지에서 '내 쿠폰' 버튼을 클릭했을 때 상세페이지를 가져옴
 * @author 서대철
 * @param data
 * @returns
 */
function viewCoupon(data){
	$('.contents-container').children().hide();
	var str = '';
		str +='		<div class="coupon--inner"> '
			+ '			<div class="coupon--title"> '
			+ '  			<h2 class="summary__title">'
			+ 		      		'나의 쿠폰 관리'
			+ '		    	</h2>'
			+ '				<h2 class="summary__desciption"> '
			+ '   				마이 포인트 '
			+ '   			</h2> '
			+ '				<h2 class="summary__point">'
			+					data[1].memPoint
			+ '				</h2>'
			+ '			</div>'
			+ '			<div class="mycoupon__status"> '
			+ '   			<h2 class="summary__title">'
			+ '					쿠폰 리스트'
			+ '				</h2>'
			+ '			</div>'
			+ '    		<div class="report-commend">'
			+ '				<ul class="tr">'
			+ '					<li>상품 순번</li>'
			+ '					<li>상품 이름</li>'
			+ '					<li>사용처</li>'
			+ '					<li>쿠폰 번호</li>'
			+ '				</ul>'
		 	+ '				<div class="list--wrap">';
	if(data.length > 0){
		for(var i = 0; i < data[0].length; i++) {
			str +='			<ul class="tr--notice">	'
				+ '				<li>' + (i+1) +'</li>'
				+ '				<li>' + data[0][i].prodName     + '원</li>'
				+ '				<li>' + data[0][i].couponSite   + '</li>'
				+ '				<li>' + data[0][i].myCouponCode + '</li>'
				+ '			</ul>';
		}
		str +='			</div>'
			+ '		</div>';
	}else {
		str +='					<ul class="tr" style="border:none"> '
			+ '						<li>'+'결과가 존재하지 않습니다.'+'</li> '
			+ '					</ul>'
			+ '				</div>'
			+ '			</div>';
	}
	str += '</div>';
	$('.contents-container').append(str);
}