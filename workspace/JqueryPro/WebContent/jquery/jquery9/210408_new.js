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