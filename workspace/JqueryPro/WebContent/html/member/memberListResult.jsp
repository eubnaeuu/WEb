<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<!-- java에서 데이터를 읽어서 json형식으로 내보내기 -->

[
<%
// 서블릿에서 조회한 결과를 "list"라는 key를 request에 담아줬음


List<MemberVO> list = (List<MemberVO>)request.getAttribute("list"); // 조회결과를 list로 담아줬음

for(int i=0; i<list.size(); i++){
	MemberVO vo = list.get(i);
	String memId = vo.getMemId();
	String memName = vo.getMemName();
	if(i>0){
		%>,<%
	}
	
// json타입으로 만들어야 하는 부분 ==> 
//						// {name : "", id : ""},{name : "", id : ""},...
%>
{name : <%=memName%>, id : "<%=memId%>"}


<%
} // 자바의 대괄호
%>
]
