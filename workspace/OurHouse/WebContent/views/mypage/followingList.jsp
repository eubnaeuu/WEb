<%@page import="ourhouse.common.vo.FollowImgVO"%>
<%@page import="ourhouse.common.vo.ProfileImgVO"%>
<%@page import="ourhouse.common.vo.FollowVO"%>
<%@page import="java.util.List"%>
<%@page import="ourhouse.common.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	MemberVO memVO = (MemberVO) request.getAttribute("memberVO");
	List<FollowVO> followingList = (List<FollowVO>) request.getAttribute("followingList");
	int followCnt = (Integer)request.getAttribute("followingCnt");
	int followerCnt = (Integer)request.getAttribute("followerCnt");
	String img = (String)request.getAttribute("img");
	List<FollowImgVO> followingImgList = (List<FollowImgVO>) request.getAttribute("followingImg");
%>    

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>우리의 집 | 팔로잉 목록</title>

    <meta name="author" content="3team">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">


    <meta property="og:type" content="website">
    <meta property="og:site_name" content="wehouse">
    <meta property="og:title" content="Introduce your home, together">
    <meta property="og:description" content="Deadock MiddleProject">
    <meta property="og:image" content="img/logo__github.png">
    <meta property="og:url" content="https://wehouse.com">

    <!-- 대표 로고 -->
    <link rel="icon" href="../../image/PNG/favicon.png">
    <link rel="apple-touch-icon" href="../../image/PNG/favicon.png">
    
    <link rel="stylesheet" href="../../css/reset.css">
    <link rel="stylesheet" href="../../css/followlist.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />

    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
</head>
<body>
    <!-- body 태그에 직접적으로 css는 부여하지 않는다. 컨테이너를 따로 지정하자. -->
    <!-- 언더바 두개를 사용하는 이유 -->
<div class="body__container"> <!-- body의 일부분인 컨테이너임을 의미 -->

    <!-- HEADER -->
    <!-- HEADER -->
    <header class="section section--header">
        <!-- 콘텐츠가 들어가는 inner영역 -->
        <div class="inner clearfix">

            <div class="menu-group float--left">
                
                <div class="logo">
                    <a href="/main.do">우리의집</a>
                </div>

            </div>

            <div class="sign-group float--right">

                <form id="search-form" method="GET" action="/searchTotal.do"><!-- 고유한 개체에 id 명시 -->
                    <span class="search-icon"></span>
                    <input type="text" id="search" name="search" class="input--text" placeholder="우리의집 통합검색">
                    <input type="submit" value="Submit"><!-- 화면엔 있지만 숨길것임. -->
                </form>

                <ul class="sub-menu">
                    <li><a href="/views/signin/logOut.jsp" id="logOutTag">로그아웃</a></li>
                </ul>

                <div class="btn-group dropdown--writelist">
                    <button type="button" class="btn btn-write">글쓰기
                        <i style="margin-left: 10px;"class="fas fa-angle-down"></i>
                    </button>
                    
                    <ul class="dropdown-write">
                        <li class="write-item" onclick="writePhoto()">
                            <img src="../../image/PNG/picture.png" alt=""> 

                            <a class="write-info">
                                <span class="write--title">사진 올리기</span>
                                <span class="write--description">우리집 사진을 공유해보세요.</span>
                            </a>
                        </li>
                        <li class="write-item" onclick="writeQna()">
                            <img src="../../image/PNG/question.png" alt=""> 

                            <a class="write-info">
                                 <span class="write--title">인테리어 질문하기</span>
                                 <span class="write--description">인테리어 고수들에게 질문해보세요.</span>
                            </a>
                        </li>
                    </ul>

                </div>
            </div>
        </div>
    </header>

    <!-- 서브 메뉴 집들이(사진), 질문과 답변, 이벤트 -->
    <header class="section sub-section">
            <div class="inner clearfix">

                <div class="section-memu float--left">
                    <ul class="section-group active">
                        <li><a href="/main.do">사진</a></li>
                        <li><a href="/qna/list.do">질문과답변</a></li>
                        <li><a href="/coupon/list.do">쿠폰</a></li>
                        <li style="border-bottom: 3px solid #35C5F0;"><a href="/notice/noticeHome.do" style="color:#35C5F0;">공지사항</a></li>
                    </ul>
                </div>

                <div class="sign-group float--right">
                    <a href="" class="textbold">나의 활동기록 NEW!
                    </a>
                </div>
                
            </div>
    </header>

    <!-- 나의 프로필 배너 -->
    <section class="section section--profilebanner">
        <div class="inner">

            <div class="profile--banner">
                <img class="banner-image" src="../../image/TESTIMAGE/oneroom.jpg" alt="">
                <div class="mask"></div>
                
                <div class="profile--info">
                    
                    <div class="profile--image">
                        <img src="../../image/uploads/<%=img %>" alt="">
                    </div>

                    <div class="simpleinfo">

                        <p class="name"><%=memVO.getMemNickname() %></p>

                        <ul class="foll-info">
                            <li class="following">
                                <span>팔로잉</span>
                                <span><%=followCnt %></span>
                            </li>

                                <div class="line-veritical"></div>

                            <li class="follow">
                                <span>팔로워</span>
                                <span><%=followerCnt %></span>
                            </li>
                        </ul>

                        <!-- 아래 settingbtn은 자신일때만 보이게해줘요 -->
                        <div class="settingbtn">
                            <button type="button" class="btn"><i class="fas fa-cog"></i>설정</button>
                        </div>

                    </div>

                </div>

            </div>


        </div>
    </section>

    <!-- 팔로잉 / 팔로워 선택 메뉴 -->
    <nav class="section section--followmenu">
        <div class="inner">
            <ul class="section-menu">
                <li><a href="/mypage/followingList.do">팔로잉</a></li>
                <li><a href="/mypage/followerList.do">팔로워</a></li>
            </ul>
        </div>            
    </nav>

    <section class="section section--follow">
        <div class="inner">
            <div class="summary__title">
            	팔로잉
            </div>

				<%
                   	int followingSize = followingList.size();
                   	if(followingSize > 0){
                   		for(int i = 0; i < followingSize; i++){
				%>
            <div class="follow--conatiner">
                <ul class="user--list">
                    <li onclick="location='/member/user.do?memId=<%=followingImgList.get(i).getTargetId()%>'"><!-- onclick에 회원 프로필로 -->
                        <div class="userImg">
                        	<%if(!(followingImgList.get(i).getProfileImg() == null)) {%>
                            <img src="/image/uploads/<%=followingImgList.get(i).getProfileImg() %>" alt="">
                        	<%} else { %>
                            <img src="/image/uploads/default_profile.png" alt="">
                        	<%} %>
                        </div>

                        <a class="userNick"><%=followingImgList.get(i).getTargetId() %></a>
                    </li>
                </ul>
            </div>
					<%} %>
				<%} %>                    

        </div>
    </section>

    <!-- FOOTER -->
    <footer class="section">
            <div class="inner clearfix">

                <ul class="site-links float--right">
                    <li>@Copyright © 2020 ~ 2020 by Daedockplace, Inc All rights reserved</li>
                </ul>

                <ul class="site-links float--left">
                    <li><a href="#">브랜드 스토리</a></li>
                    <li><a href="#">이용약관</a></li>
                    <li><a href="#">개인정보처리방침</a></li>
                    <li><a href="#">고객센터</a></li>
                    <li><a href="#">공지사항</a></li>
                </ul>
            </div>
    </footer>

    <!-- 헤더 고정시키는 js -->
    <script src="../../js/headerfixed.js"></script>
    <script src="../../js/writebtn.js"></script>
</body>
</html>
