<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

//메시지 (fail, ok) 얻어오기
String msg = (String) request.getAttribute("msg");

%>

{ "msg" : "<%=msg %>"}