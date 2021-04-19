<%@page import="kr.or.ddit.common.vo.ZipVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
[
<% 
List <ZipVO> list = (List<ZipVO>)request.getAttribute("list");
int cnt = 0;
for(int i=0; i < list.size() ; i++){
	
// 	ZipVO vo = list.get(i);
// 	String sido = vo.getSido();
// 	String gugun = vo.getGugun();
// 	String dong = vo.getDong();
// 	String ri = vo.getRi();
// 	String bldg = vo.getBldg();
// 	String bunji = vo.getBunji();
// 	String zipcode = vo.getZipcode();
// 	long seq = vo.getSeq();

// cnt++;
	if(i > 0){
		%>,<%
	}
	%>
	{
	"sido" : "<%=list.get(i).getSido()%>"
	,"gugun" : "<%=list.get(i).getGugun()%>"
	,"dong" : "<%=list.get(i).getDong()%>"
	,"bunji" : "<%=list.get(i).getBunji()%>"
	,"ri" : "<%=list.get(i).getRi()%>"
	,"bldg" : "<%=list.get(i).getBldg()%>"
	,"zipcode" : "<%=list.get(i).getZipcode()%>"
	,"seq" : "<%=list.get(i).getSeq()%>"
<%-- 	,"value" : "<%=cnt%>" --%>
	}
	<%
	}
	%>
]