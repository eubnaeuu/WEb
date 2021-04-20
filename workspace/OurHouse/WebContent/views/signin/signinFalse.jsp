<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//로그인 실패 1이 돌아옴
int cnt = (Integer) request.getAttribute("cnt");
%>

{ "cnt" : <%=cnt %>}