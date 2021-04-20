<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

Object obj = request.getAttribute("result");

String result = "";

if(obj == null){
	result = "🏡우리의 집🏡  회원이 되신걸 환영합니다💙";
}else{
	result = "회원가입실패..😥";
}

%>

["<%=result %>"]