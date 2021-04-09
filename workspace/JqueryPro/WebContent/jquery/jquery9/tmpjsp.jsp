<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>jsp 사용법</title>
	</head>
<%
String str = "홍길동";
System.out.println("오잉~");

//한글 깨짐 현상 해결 방법 (request부분 인코딩)
request.setCharacterEncoding("UTF-8");
//data가 request에 담겨옴
String userName =request.getParameter("userName");
String userAge = request.getParameter("userAge");

%>		
	<body>
<p>안녕하세요. <span id="spUserName"><%=userName %></span>씨</p>
<p>올해로  <span id="spUserAge"><%=userAge %></span>살 이시군요.</p>
	</body>
</html>