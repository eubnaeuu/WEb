<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//cnt가 0이야..
	int cnt = (Integer) request.getAttribute("cnt");
%>
{"cnt" : <%=cnt %>}    
    