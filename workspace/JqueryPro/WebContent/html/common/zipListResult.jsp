<%@page import="kr.or.ddit.common.vo.ZipVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
[
<% 
List <ZipVO> list = (List<ZipVO>)request.getAttribute("list");
int cnt = 0;
for(int i=0; i < list.size() ; i++){
	cnt++;
	if(i > 0){
		%>,<%
	}
	%>
	{
	"value" : "<%=cnt%>"
	,"name" : "<%=list.get(i).getSido()%>"
	}
	<%
	}
	%>
]