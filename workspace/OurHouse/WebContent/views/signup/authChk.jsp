<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
 <%
 //MailAuthServlet에서 생성한 인증키를 req.set("authKey", authKey) get으로 받아옴
 String authKey = (String)request.getAttribute("authKey");
 
 %>
 
	{
		"authKey" : "<%=authKey %>"
	}