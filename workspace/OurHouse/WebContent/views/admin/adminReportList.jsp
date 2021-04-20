<%@page import="ourhouse.common.vo.RReportVO"%>
<%@page import="ourhouse.common.vo.PReportVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<PReportVO> preportList = (List<PReportVO>) request.getAttribute("preportList");
	List<RReportVO> rreportList = (List<RReportVO>) request.getAttribute("rreportList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>우리의집 | 신고내역관리</title>

    <meta name="author" content="3team">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <!-- 대표 로고 -->
    <link rel="icon" href="../../image/PNG/favicon.png">
    <link rel="apple-touch-icon" href="../../image/PNG/favicon.png">
    
    <link rel="stylesheet" href="../../css/reset.css">
    <link rel="stylesheet" href="../../css/adminhome.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />

    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
	

</head>
<body>
    <!-- body 태그에 직접적으로 css는 부여하지 않는다. 컨테이너를 따로 지정하자. -->
    <!-- 언더바 두개를 사용하는 이유 -->
<div class="body__container"> <!-- body의 일부분인 컨테이너임을 의미 -->

    <!-- HEADER -->
    <header class="section">
        <!-- 콘텐츠가 들어가는 inner영역 -->

        <div class="header--background"></div>

            <div class="inner clearfix">

                <div class="menu-group float--left">
                    
                    <div class="logo">
                        <a href="/admin/reportList.do">우리의집</a>
                    </div>

                </div>

                <div class="item-nav float--right">

                    <a class="item-box" href="/main.do"> 

                        <div class="item-picture">
                            <img src="../../image/PNG/picture.png" alt="">
                        </div>
                        
                        <div class="item-title">
                            <h3>사진게시판으로</h3>
                            <p>회원들이 올린 사진을 보러갑시다</p>
                        </div>
                        
                    </a>

                    <a class="item-box" href="/qna/list.do"> 

                        <div class="item-picture">
                            <img src="../../image/PNG/question.png" alt="">
                        </div>
                        
                        <div class="item-title">
                            <h3>질문게시판으로</h3>
                            <p>회원들의 질문글을 보러갑시다</p>
                        </div>
                        
                    </a>
                    
                    <a class="item-box" href="/admin/insertNotice.do"> 

                        <div class="item-picture">
                            <img src="../../image/PNG/write.png" alt="">
                        </div>
                        
                        <div class="item-title">
                            <h3>공지게시글 작성</h3>
                            <p>자 이제 한번 공지해봅시다</p>
                        </div>
                        
                    </a>

                </div>
            </div>
    </header>

    <section class="section section-dashboard">

        <div class="inner">

            <nav class="nav-container">
                <ul class="nav-list">
                    <li class="nav-item" style="color: #35C5F0;"><a href="/admin/reportList.do">신고내역</a></li>
                    <li class="nav-item"><a href="/admin/memberList.do">회원관리</a></li>
                    <li class="nav-item" style="color: #35C5F0;"><a href="/admin/noticeList.do">공지사항</a></li>
                    <li class="nav-item"><a href="/admin/coupon.do">쿠폰관리</a></li>
                </ul>
            </nav>

            <div class="dashboard-contents">


                <h2 class="summary__title">신고 내역 관리
<!--                 <img src="../../image/SVG/poyo.svg" alt="" class="chracter"> -->
                </h2>

                <div class="report-list">
                    
                    <div class="summary__desciption"> 신고 내역

                        <!-- <form id="search-form" method="POST" action="">
                            <span class="search-icon"></span>
                            <input type="text" id="search" class="input--text" placeholder="우리의집 통합검색">
                            <input type="submit" value="Submit">
                        </form> -->

                    </div>
                
                    <ul class="tr">
                        <li>신고된 게시글 번호</li>
                        <li>게시글 내용</li>
                        <li>게시글 회원 ID</li>
                        <li>신고 사유</li>
                        <li>날짜</li>
                        <li>게시글 삭제 여부</li>
                    </ul>

                    <div class="list--wrap">
                        <%
                        	int preportSize = preportList.size();
                        	if(preportSize > 0){
                        		for(int i = 0; i < preportSize; i++){
                        %>
        				
                        <ul class="tr--notice"> 
                            <li><a href="/photo/detail.do?postNo=<%=preportList.get(i).getPostNo()%>"><%=preportList.get(i).getPostNo() %></a></li>
                            <li><%=preportList.get(i).getFileCn() %></li>
                            <li><%=preportList.get(i).getMemId() %></li>
                            <li><%=preportList.get(i).getReasonCon() %></li>
                            <li><%=preportList.get(i).getReportDate().substring(0, 10) %></li>
                            <li>
                                <form action="">
                                <input type="hidden" name="postNo" value="<%=preportList.get(i).getPostNo()%>"> <!-- 회원 ID 넣을 곳 -->
                                <input class="remove--post" type="button" onclick="deletePost(this)" value="게시글 삭제"><!-- submit 혹은 button -->
                                </form>
                            </li>
                        </ul>
        					<%} %>
        				<%} %>
                    </div>

                </div>

                <div class="report-comment">
                    
                    <div class="summary__desciption"> 
                        신고 댓글
<!--                         <img src="../../image/SVG/poyp2.svg" alt="" class="chracter img2"> -->
                    </div>
                
                    <ul class="tr">
                        <li>게시글 번호</li>
                        <li>댓글 내용</li>
                        <li>댓글 회원 ID</li>
                        <li>신고 사유</li>
                        <li>작성 날짜</li>
                        <li>댓글 삭제 여부</li>
                    </ul>

                    <div class="list--wrap">
        				<%
        					int rreportListSize = rreportList.size();
        					if(rreportListSize > 0){
        						for(int i = 0; i < rreportListSize; i++){
        							
        				%>
                        <ul class="tr--notice"> 
                            <li><a href="/photo/detail.do?postNo=<%=rreportList.get(i).getPostNo()%>"><%=rreportList.get(i).getPostNo() %></a></li>
                            <li><%=rreportList.get(i).getReplyContent() %></li>
                            <li><%=rreportList.get(i).getMemId() %></li>
                            <li><%=rreportList.get(i).getReasonCon() %></li>
                            <li><%=rreportList.get(i).getReportDate().substring(0, 10) %></li>
                            <li>
                                <form action="">
                                	<input type="hidden" name="replyNo" value="<%=rreportList.get(i).getReplyNo()%>"> <!-- 회원 ID 넣을 곳 -->
                                	<input class="remove--post" type="button" onclick="deleteReply(this)" value="댓글 삭제"><!-- submit 혹은 button -->
                                </form>
                            </li>
                        </ul>
                            <%} %>
						<%} %>
        
                    </div>

                </div>

            </div>

        </div>

    </section>
	
    </div>
    
    
    
    <script src="../../js/main.js"></script>
    <script src="../../js/report/deleteReport.js"></script>
</body>
</html>












