/**
 * 
 */		

/** 핸드폰 번호 포멧 */
function formatHp(val){
			/* 
			val형태
			000-0000-0000
			000-00000000
			0000000-0000
			00000000000
			000 0000 0000
			000 00000000
			00-000-000000
			등등
			*/
			
			val = val.replaceAll("-","").replaceAll(" ","");
			val = val.replace(/(\d{3})(\d{3,4})(\d{4})/,"$1-$2-$3");
			return val;
		}

/** 
 * 	url에서 해당 속성의 value값 추출
 * 	url 
 * 	key 보고자하는 속성값
 *  */
function getValue(url, key){
	var idx = url.indexOf("?");
	if(idx > -1){
		url = url.substr(idx + 1);
		var arr = url.split("&");
		
		for(var i=0; i<arr.length; i++){
			
			var tmp = arr[i].split("=");
			if(tmp[0] == key){
				if(tmp.length>0){
					return tmp[1];
				} else {
					return "없음요..";
				}
			}
		}
	}
}


/**
 * 빈값체크
 * @param val
 * @returns
 */
function isEmpty(val){
	//val이 빈 값이거나 null이거나 undefined이거나 " ", "    " 등 인 경우
	if(val == null){
		return true;
	}
	if(val == undefined){
		return true;	
	}
	if(val == ""){
		return true;	
	}
	if(val == " "){
		return true;	
	}
	if(val.length == val.match(" ")){
		return true;
	}
	return false;
}