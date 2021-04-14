<%@page import="kr.or.ddit.common.vo.CodeVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
List <CodeVO> list = request.getAttribute("list");

%>
[
<%
for(int i=0; i < list.size() ; i++){
	%>{<%
	list.get(i).getCode();
	%>,<%
	list.get(i).getCodeName();
	%>,<%
}
	 %>
]

