<%@page import="ourhouse.common.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

//로그인 성공
MemberVO resultVO = (MemberVO) request.getAttribute("resultVO");

String memId = resultVO.getMemId();
String memPass = resultVO.getMemPass();
String memDelete = resultVO.getMemDelete();

%>    

{ 

	"memId" : "<%=memId %>"
	,"memPass" : "<%=memPass %>"
	,"memDelete" : "<%=memDelete %>"

}