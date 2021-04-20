
/**
 * 매개변수가 값이 있는지 확인하는 함수
 * @param param
 * @returns true: undefined/공백/null일때, false: 값이 있을 때
 */
function isEmpty(param) {
	if(param == undefined) return true;
	param += "";
	if(param.trim().length == 0 || param == null || param == "") return true;
}