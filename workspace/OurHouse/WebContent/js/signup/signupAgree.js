/**
 * 
 */
$(function(){

	//약관전체 감싼 div id = 'checker'
	$("#checker label").click(function(){
		//input에 대한 모든 요소
		var checkedType = $(this).siblings('input');
		
		//label의 형제요소중에 input의 id에 해당하는 모든 요소 선택
		var checkId = $(this).siblings('input').attr('id');
		//$(checkType).prop('checked',true);
		
		console.log(checkId);
		
		if(checkId == "all"){ //id가 all일때 전체동의일때
			//input에 클래스(all--checker).다음 label의 자식 span
			var check = $('.all--checker').next('label').children('span');
			
			if(!$(check).hasClass('active')){
				//span에 이미지 추가
				$(check).css("background","url(../../image/SVG/check_checked.svg) top no-repeat");				
				$(check).addClass('active');
				
				//span의 부모 label의 형제요소중에 input에 해당하는 모든 요소의 checked가 true
				$(check).parent().siblings('input').prop('checked',true);
				console.log('전체동의 ' + $('.allcheck').children('input').prop('checked'));
				console.log('만 14세 이상 ' + $('.agecheck').children('input').prop('checked'));
				console.log('이용약관 ' + $('.usecheck').children('input').prop('checked'));
				console.log('개인정보처리방침 ' + $('.privacycheck').children('input').prop('checked'));
				console.log('이벤트,프로모션 알림 메일 및 SMS 수신 ' + $('.snscheck').children('input').prop('checked'));
			}else{//전체동의 체크가 아닐때
				
				$(check).css("background","url(../../image/SVG/check_default.svg) top no-repeat");				
				$(check).removeClass('active');

				$(check).parent().siblings('input').prop('checked',false);
				console.log('전체동의 ' + $('.allcheck').children('input').prop('checked'));
				console.log('만 14세 이상 ' + $('.agecheck').children('input').prop('checked'));
				console.log('이용약관 ' + $('.usecheck').children('input').prop('checked'));
				console.log('개인정보처리방침 ' + $('.privacycheck').children('input').prop('checked'));
				console.log('이벤트,프로모션 알림 메일 및 SMS 수신 ' + $('.snscheck').children('input').prop('checked'));
			}
			
		}
		
		else if(checkId == "privacy"){//input 아이디가 개인정보처리방침일때 
			//개인정보처리방침 다음 라벨의 자식 span
			var check = $('#privacy').next('label').children('span');
			
			if(!$(check).hasClass('active')){
				$(check).css("background","url(../../image/SVG/check_checked.svg) top no-repeat");				
				$(check).addClass('active');
				
				$(check).parent().siblings('input').prop('checked',true);
				console.log('개인정보처리방침 ' + $('.privacycheck').children('input').prop('checked'));
				console.log('전체동의 ' + $('.allcheck').children('input').prop('checked'));
			}
			
			else{
				
				$(check).css("background","url(../../image/SVG/check_default.svg) top no-repeat");				
				$(check).removeClass('active');

				$(check).parent().siblings('input').prop('checked',false);
				
				console.log('개인정보처리방침 ' + $('.privacycheck').children('input').prop('checked'));
				console.log('전체동의 ' + $('.allcheck').children('input').prop('checked'));
			}
			
			var all = $('.all--checker');
			
			for(var i = 1; i < all.length ; i++){
				console.log($(all)[i].checked);
				
				if(!$(all)[i].checked){
					$('#all').siblings('label').children('span').css("background","url(../../image/SVG/check_default.svg) top no-repeat");			
					$('#all').prop('checked',false);

					console.log('개인정보처리방침 ' + $('.privacycheck').children('input').prop('checked'));
					console.log('전체동의 ' + $('.allcheck').children('input').prop('checked'));
					
					return;
				}
			}
			$('#all').siblings('label').children('span').css("background","url(../../image/SVG/check_checked.svg) top no-repeat");			
			$('#all').prop('checked',true);

			console.log('개인정보처리방침 ' + $('.privacycheck').children('input').prop('checked'));
			console.log('전체동의 ' + $('.allcheck').children('input').prop('checked'));
		}//else if privacy 끝
		
		else if(checkId == "use"){//id가 이용약관일때
			var check = $('#use').next('label').children('span');
			
			if(!$(check).hasClass('active')){//이용약관만 체크안했을때
				$(check).css("background","url(../../image/SVG/check_checked.svg) top no-repeat");				
				$(check).addClass('active');
				
				$(check).parent().siblings('input').prop('checked',true);//다른 체크박스는 체크
				
				console.log('이용약관 ' + $('.usecheck').children('input').prop('checked'));
				console.log('전체동의 ' + $('.allcheck').children('input').prop('checked'));
			}
			
			else{
				//이용약관만 체크했을때
				$(check).css("background","url(../../image/SVG/check_default.svg) top no-repeat");				
				$(check).removeClass('active');

				$(check).parent().siblings('input').prop('checked',false); //다른 체크박스는 노체크
				
				console.log('이용약관 ' + $('.usecheck').children('input').prop('checked'));
				console.log('전체동의 ' + $('.allcheck').children('input').prop('checked'));
			}
			
			var all = $('.all--checker'); //모든 체크박스 input요소에 있는 class
			
			for(var i = 1; i < all.length ; i++){
				console.log($(all)[i].checked);
				
				if(!$(all)[i].checked){
					$('#all').siblings('label').children('span').css("background","url(../../image/SVG/check_default.svg) top no-repeat");			
					$('#all').prop('checked',false);

					console.log('이용약관 ' + $('.usecheck').children('input').prop('checked'));
					console.log('전체동의 ' + $('.allcheck').children('input').prop('checked'));
					
					return;
				}
			}
			$('#all').siblings('label').children('span').css("background","url(../../image/SVG/check_checked.svg) top no-repeat");			
			$('#all').prop('checked',true);

			console.log('이용약관 ' + $('.usecheck').children('input').prop('checked'));
			console.log('전체동의 ' + $('.allcheck').children('input').prop('checked'));			
		}//else if use 끝
		else if(checkId == "age"){//14세이상만 체크했을때
			var check = $('#age').next('label').children('span');
			
			if(!$(check).hasClass('active')){//14세이상만 체크했을때
				$(check).css("background","url(../../image/SVG/check_checked.svg) top no-repeat");				
				$(check).addClass('active');
				
				$(check).parent().siblings('input').prop('checked',true);//다른 체크박스는 체크
				
				console.log('만14세 이상 ' + $('.agecheck').children('input').prop('checked'));
				console.log('전체동의 ' + $('.allcheck').children('input').prop('checked'));
			}
			
			else{
				//14세이상만 체크했을때
				$(check).css("background","url(../../image/SVG/check_default.svg) top no-repeat");				
				$(check).removeClass('active');

				$(check).parent().siblings('input').prop('checked',false); //다른 체크박스는 노체크
				
				console.log('만14세 이상 ' + $('.agecheck').children('input').prop('checked'));
				console.log('전체동의 ' + $('.allcheck').children('input').prop('checked'));
			}
			
			var all = $('.all--checker'); //모든 체크박스 input요소에 있는 class
			
			for(var i = 1; i < all.length ; i++){
				console.log($(all)[i].checked);
				
				if(!$(all)[i].checked){
					$('#all').siblings('label').children('span').css("background","url(../../image/SVG/check_default.svg) top no-repeat");			
					$('#all').prop('checked',false);

					console.log('만14세 이상 ' + $('.agecheck').children('input').prop('checked'));
					console.log('전체동의 ' + $('.allcheck').children('input').prop('checked'));
					
					return;
				}
			}
			$('#all').siblings('label').children('span').css("background","url(../../image/SVG/check_checked.svg) top no-repeat");			
			$('#all').prop('checked',true);

			console.log('만14세 이상 ' + $('.agecheck').children('input').prop('checked'));
			console.log('전체동의 ' + $('.allcheck').children('input').prop('checked'));			
			
		}//else if age 끝
		else if(checkId == "sns"){
			var check = $('#sns').next('label').children('span');
			
			if(!$(check).hasClass('active')){//14세이상만 체크했을때
				$(check).css("background","url(../../image/SVG/check_checked.svg) top no-repeat");				
				$(check).addClass('active');
				
				$(check).parent().siblings('input').prop('checked',true);//다른 체크박스는 체크
				
				console.log('알림메일수신 ' + $('.snscheck').children('input').prop('checked'));
				console.log('전체동의 ' + $('.allcheck').children('input').prop('checked'));
			}
			
			else{
				//14세이상만 체크했을때
				$(check).css("background","url(../../image/SVG/check_default.svg) top no-repeat");				
				$(check).removeClass('active');

				$(check).parent().siblings('input').prop('checked',false); //다른 체크박스는 노체크
				
				console.log('알림메일수신 ' + $('.snscheck').children('input').prop('checked'));
				console.log('전체동의 ' + $('.allcheck').children('input').prop('checked'));
			}
			
			var all = $('.all--checker'); //모든 체크박스 input요소에 있는 class
			
			for(var i = 1; i < all.length ; i++){
				console.log($(all)[i].checked);
				
				if(!$(all)[i].checked){
					$('#all').siblings('label').children('span').css("background","url(../../image/SVG/check_default.svg) top no-repeat");			
					$('#all').prop('checked',false);

					console.log('알림메일수신 ' + $('.snscheck').children('input').prop('checked'));
					console.log('전체동의 ' + $('.allcheck').children('input').prop('checked'));
					
					return;
				}
			}
			$('#all').siblings('label').children('span').css("background","url(../../image/SVG/check_checked.svg) top no-repeat");			
			$('#all').prop('checked',true);

			console.log('알림메일수신 ' + $('.snscheck').children('input').prop('checked'));
			console.log('전체동의 ' + $('.allcheck').children('input').prop('checked'));				
		}//else if sns끝
	});	
});