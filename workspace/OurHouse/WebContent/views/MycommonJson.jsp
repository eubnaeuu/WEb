<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String jsonString= (String) request.getAttribute("jsonString");
	out.write(jsonString);
	
%>
    