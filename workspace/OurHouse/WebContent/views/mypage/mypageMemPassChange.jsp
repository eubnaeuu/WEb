<%@page import="ourhouse.common.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String memEmail = (String) request.getAttribute("memEmail");
MemberVO mv = (MemberVO) session.getAttribute("session");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>우리의집 | 비밀번호변경</title>

    <meta name="author" content="3team">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- 대표 로고 -->
    <link rel="icon" href="../../image/PNG/favicon.png">
    <link rel="apple-touch-icon" href="../../image/PNG/favicon.png">
    
    <link rel="stylesheet" href="../../css/reset.css">
    <link rel="stylesheet" href="../../css/userSettingPW.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />

    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
	<script type="text/javascript" src="../js/member/mypageMemPassChange.js"></script>
	<script type="text/javascript" src="../js/myUtil.js"></script>
	
    <title>Document</title>
    <script type="text/javascript">
    $(function(){
        loginTrue();//로그인 되었을때
     });
     function loginTrue(){
        <%if(mv == null){%> //로그인 상태가 아닐때
          $("#loginTag").show();//로그인태그나타남
           $("#logOutTag").hide();//로그아웃상태숨김
           $("#signUpTag").show();//회원가입나타남
        <%}else{%>
           $("#loginTag").hide();//로그인태그 숨김
           $("#logOutTag").show();//로그아웃상태나타남
           $("#signUpTag").hide();//회원가입숨김
        <%}%>
     }
    </script>
</head>

<body>

<div class="body__container">

    <!-- 기존 HEADER -->
    <%-- <header class="section">
        <div class="inner clearfix">

            <div class="menu-group float--left">
                
                <div class="logo">
                    <a href="<%=request.getContextPath()%>/main.do">우리의집</a>
                </div>

            </div>

            <div class="sign-group float--right">

                <form id="search-form" method="POST" action="">
                    <span class="search-icon"></span>
                    <input type="text" id="search" class="input--text" placeholder="우리의집 통합검색">
                    <input type="submit" value="Submit">
                </form>

                <ul class="sub-menu">
                    <li><a href="/member/signin.do" id="loginTag">로그인</a></li>
                    <li><a href="/views/signin/logOut.jsp" id="logOutTag">로그아웃</a></li>
                    <li><a href="/member/signup.do" id="signUpTag">회원가입</a></li>
                </ul>

                <div class="btn-group">
                    <a href="" class="btn">글쓰기
                        <i style="margin-left: 10px;"class="fas fa-angle-down"></i>
                    </a>
                </div>
            </div>
        </div>
    </header> --%>
    
    <!-- 새로운 헤더 -->
    <header class="section">
        <div class="inner clearfix">

            <div class="menu-group float--left">
                
                <div class="logo">
                    <a href="/main.do">우리의집</a>
                </div>

            </div>

            <div class="sign-group float--right">

                <form id="search-form" method="GET" action="/searchTotal.do">
                    <span class="search-icon"></span>
                    <input type="text" id="search" name="search" class="input--text" placeholder="우리의집 통합검색">
                    <input type="submit" value="Submit">
                </form>

                <ul class="sub-menu">
                    <li><a href="/member/signin.do" id="loginTag">로그인</a></li>
                    <li><a href="/views/signin/logOut.jsp" id="logOutTag">로그아웃</a></li>
                    <li><a href="/member/signup.do" id="signUpTag">회원가입</a></li>
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

    <!-- 프로필, 설정 선택하는 서브 헤더 -->
    <header class="section section-profile_nav">
        <div class="inner">
            <ul>
                <li><a href="#">프로필</a></li>
                <li><a href="<%=request.getContextPath()%>/member/memberInfoUpdate.do">설정</a></li>
            </ul>
        </div>
    </header>

    <!-- 모두보기, 사진, 질문과답변, 좋아요 선택하는 서브 헤더 -->
    <header class="section section-profile-subnav">
        <div class="inner">
            <ul>
                <!-- <li><a href="#">모두보기</a></li>
                <li><a href="#">사진</a></li>
                <li><a href="#">질문과답변</a></li>
                <li><a href="#">좋아요</a></li> -->

                
                <li><a href="<%=request.getContextPath()%>/member/memberInfoUpdate.do">회원정보수정</a></li>
                <li><a href="<%=request.getContextPath()%>/member/mypageMailAuth.do">비밀번호 변경</a></li>
                 
            </ul>
    </header>

    <!-- 회원정보 수정 컨테이너 -->
    <section class="section section-settingPW">

        <div class="inner">
            <form action="">
            <h2 class="summary__title">비밀번호 변경</h2>

                <div class="changePW">
                    <h2 class="subtitle__changePW">
                        새 비밀번호	<span id="passSpan"></span>
                    </h2>
                    <p class="description__changePW">
                        영문 대소문자 숫자포함 8-12자리 입력해주세요😊
                    </p>
                    <div class="change-form">
                        <input class="input--text password" type="password" id="pass" name="pass" placeholder="비밀번호 입력" maxlength="12">
                    </div>
                    <h2 class="checkPW">
                        <i class="fas fa-exclamation-circle"></i>필수 입력 항목입니다.
                    </h2>
                </div>

                <div class="changePW">
                    <h2 class="subtitle__changePW">
                        새 비밀번호 확인<span id="mempassSpan"></span>
                    </h2>
                    <div class="description__changePW">
                    	
                        <input class="input--text password" type="password" id="memPass" name="memPass" placeholder="비밀번호 재입력" maxlength="12">
                    </div>
                    <h2 class="checkPW">
                        <i class="fas fa-exclamation-circle"></i>비밀번호가 일치하지 않습니다.
                    </h2>
                </div>

                <input type="button" class="btn-setting" onclick="memPassCheck()" value="비밀번호 변경">
				<input type="hidden" id="memEmail" name="memEmail" value="<%=memEmail%>"> <!--로그인상태로 오면 세션에서 가져온 이메일로 세팅  --> 
                </form>
            </div>
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
    
    
    <script src="../js/writebtn.js"></script>

</body>
</html>