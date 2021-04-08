/**
 * 
 */
// strUrl = http://localhost:9000/JqueryPro/jquery/jquery1/210325_hw_jq_1.html?userName=%ED%99%8D%EA%B8%B8%EB%8F%99&userAge=32
// strKey = "userId"
/**
 * Url에서 userId값과 관련된 부분을 반환
 * 
 * @param strUrl 추출하고자하는 Url
 * @param strKey 이용할 userId
 * @returns
 */
function getValue(strUrl, strKey){
	var params = url.substr(url.indexOf("?")+1);
	var arr = params.split("&"); 
	arr[0]; // 
	
}

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
	if(val.lenth == val.match(" ")){
		return true;
	}
	
}
function chkRegExp(){
	
}
function format(val, type){
	if(type == "hpno"){
		val = val.replaceAll("-", "").replaceAll(" ", "");
		// only숫자만을 이용해서 000-0000-0000 or 000-000-0000형식으로 보여주기
		val = val.replace(/(\d{3})(\d{3,4})(\d{4})/, "$1-$2-$3"); // $1의 의미는 1번째 파라미터를 말하며 1번째 파라미터는  첫번째()임
//		val.replace(/(\d{4})(\d{2})(\d{2})/, "$1년$2월$3일");
		
	}
}