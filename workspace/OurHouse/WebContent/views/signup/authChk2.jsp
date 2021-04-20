<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//1이 돌아오면 보낸인증키와 사용자가 입력한 인증키가 같다는것..
int cnt = (Integer)request.getAttribute("cnt"); 
%>

{"cnt" : <%=cnt %> }