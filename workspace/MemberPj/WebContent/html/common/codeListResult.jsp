<%@page import="kr.or.ddit.common.vo.CodeVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
[
<% 
List <CodeVO> list = (List<CodeVO>)request.getAttribute("list");
int cnt=0;
for(int i=0; i < list.size() ; i++){
	cnt++;
	if(i > 0){
		%>,<%
	}
	%>
	{
	"groupCode" : "<%=list.get(i).getGroupCode()%>"
	,"groupCodeName" : "<%=list.get(i).getGroupCodeName()%>"
	,"code" : "<%=list.get(i).getCode()%>"
	,"codeName" : "<%=list.get(i).getCodeName()%>"
	,"description" : "<%=list.get(i).getDescription()%>"
	,"cnt" : "<%=cnt%>"
	}
	<%
	}
	%>
]