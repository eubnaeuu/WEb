<%@page import="kr.or.ddit.common.vo.CodeVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
[
<% 
List <CodeVO> list = (List<CodeVO>)request.getAttribute("initlist");
for(int i=0; i < list.size() ; i++){
	if(i > 0){
		%>,<%
	}
	%>
	{
	"value" : "<%=list.get(i).getGroupCode()%>"
	"name" : "<%=list.get(i).getGroupCodeName()%>"
	}
	<%
	}
	%>
]