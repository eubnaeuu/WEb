<%@page import="ourhouse.common.vo.NoticeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	NoticeVO noticeVO = (NoticeVO) request.getAttribute("noticeVO");
	//int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
	//String noticeCont = request.getParameter("noticeContent");
	System.out.println(noticeVO.getNoticeTitle());
	System.out.println(noticeVO.getNoticeContent());
	
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>우리의 집 | 공지사항 수정</title>

    <meta name="author" content="3team">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <!-- 대표 로고 -->
    <link rel="icon" href="../../image/PNG/favicon.png">
    <link rel="apple-touch-icon" href="../../image/PNG/favicon.png">
    
    <link rel="stylesheet" href="../../css/reset.css">
    <link rel="stylesheet" href="../../css/writenotice.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />

    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
	<script src="../../js/noticeManage.js"></script>
	<script src="../../js/notice/noticeNew.js"></script>
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
            
                <div class="write-container">
	 				<form action="/admin/updateNotice.do" method="post" >
		                
		                <input class="input--text" type="text" name="noticeTitle" value="<%=noticeVO.getNoticeTitle()%>" onchange="chkTitle()">
						<span id="checkTitle"></span>
		                <input type="hidden" id="checkTitle">
		             	
	 					<input type="hidden" name="noticeNo" value="<%=noticeVO.getNoticeNo() %>">
		                    
		                <textarea class="input--text" name="noticeContent" onchange="chkContent()"><%=noticeVO.getNoticeContent() %></textarea>
						<span name="checkContent"></span>
						<input type="hidden" id="checkContent">
		                
		                <input type="hidden" name="updateDate" value="<%=noticeVO.getUpdateDate()%>">
		                   
		                <input class="btn" type="submit" value="게시글 수정">
	 				
	 				</form>           
 				</div>

            </div>

        </div>

    </section>

    </div>
    
</body>
</html>