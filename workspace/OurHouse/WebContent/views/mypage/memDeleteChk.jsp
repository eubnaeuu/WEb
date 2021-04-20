<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//cnt가 1이야 .. 회원탈퇴야..비밀번호 수정이야..회원정보수정이야..
	int cnt = (Integer)request.getAttribute("cnt");	
%>

{ "cnt" : <%=cnt %>}