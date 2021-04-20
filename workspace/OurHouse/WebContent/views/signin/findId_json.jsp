<%@page import="ourhouse.common.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
MemberVO resultVO = new MemberVO();
String memId = null;
if(request.getAttribute("resultVO") != null) {
	resultVO = (MemberVO) request.getAttribute("resultVO");
	memId = resultVO.getMemId();
}
//int cnt = (Integer) request.getAttribute("cnt");

// String memId = (String) resultVO.getMemId();
%>

{
	"memId" : "<%=memId %>"
}