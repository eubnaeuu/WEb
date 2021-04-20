<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="ourhouse.common.vo.MypagePhotoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="ourhouse.common.vo.MemberVO"%>

<%
	
	String jsonString= (String) request.getAttribute("jsonString");
	out.write(jsonString);

// 	List<MypagePhotoVO> mpList = new ArrayList();
	
// 	mpList = (List<MypagePhotoVO>)request.getAttribute("MpPostPhoto");
	
// 	System.out.println(mpList);
%>
    
    
