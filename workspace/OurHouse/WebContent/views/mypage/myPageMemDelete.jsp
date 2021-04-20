<%@page import="ourhouse.common.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

//건이가 준걸로 변경 
MemberVO mv = (MemberVO) session.getAttribute("session");

%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>우리의집 | 회원탈퇴</title>

    <meta name="author" content="3team">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- 대표 로고 -->
    <link rel="icon" href="../../image/PNG/favicon.png">
    <link rel="apple-touch-icon" href="../../image/PNG/favicon.png">
    
    <link rel="stylesheet" href="../../css/reset.css">
    <link rel="stylesheet" href="../../css/userSessionout.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />

    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <script src="../js/member/myPageMemDelete.js"></script>
    <script src="../js/myUtil.js"></script>
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

    <title>Document</title>
</head>

<body>

<div class="body__container">

    <!-- 기존 HEADER -->
    <!-- <header class="section">
        <div class="inner clearfix">

            <div class="menu-group float--left">
                
                <div class="logo">
                    <a href="/main.do">우리의집</a>
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
    </header> -->
    
    <!-- 글쓰기 버튼 추가한 HEADER -->
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
                <li><a href="/member/mypage.do">프로필</a></li>
                <li><a href="/member/memberInfoUpdate.do">설정</a></li>
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

                
                <li><a href="/member/memberInfoUpdate.do">회원정보수정</a></li>
                <li><a href="/member/mypageMailAuth.do">비밀번호 변경</a></li>
                 
            </ul>
    </header>

    <!-- 회원정보 수정 컨테이너 -->
    <section class="section section-settingPW">

        <div class="inner">
            <h2 class="summary__title">회원탈퇴</h2>
            <h3 class="summary__description">회원 탈퇴에 앞서 아래 내용을 반드시 확인해주세요.</h3>

                <div class="term-contents">
                    <h3 class="summary__description">회원탈퇴 시 처리내용</h3>
                    
                    <ul class="term__description">
                        <li>우리의집 포인트·쿠폰은 소멸되며 환불되지 않습니다.</li>
                    </ul>
                    <h3 class="summary__description">회원탈퇴 시 게시물관리</h3>
                    
                    <ul class="term__description">
                        <li>회원탈퇴 후 우리의집 서비스에 입력한 게시물 및 댓글은 삭제되지 않으며, 회원정보 삭제로 인해 작성자 본인을 확인할 수 없으므로 게시물 편집 및 삭제 처리가 원칙적으로 불가능 합니다. 게시물 삭제를 원하시는 경우에는 먼저 해당 게시물을 삭제 하신 후, 탈퇴하시길 바랍니다.</li>
                    </ul>
                    <h3 class="summary__description">회원탈퇴 후 재가입 규정</h3>
                    
                    <ul class="term__description">
                        <li>탈퇴 회원이 재가입하더라도 오늘의집 포인트는 이미 소멸되었기 때문에 양도되지 않습니다.</li>
                    </ul>
                </div>

            <div class="vertical--line"></div>  

            <h2 class="summary__title pwconfirm">비밀번호 확인</h2>
            <h3 class="summary__description pwterm">회원 탈퇴를 위해 비밀번호를 입력해주세요.</h3>
			
				<form>
                <div class="changePW">
                    <h2 class="subtitle__changePW">
                        비밀번호
                    </h2>
                    <div class="change-form">
                        <input class="input--text password" id="pass" name="pass" type="password" placeholder="비밀번호 입력" maxlength="12">
                    </div>
                    <h2 class="checkPW">
                        <i class="fas fa-exclamation-circle"></i>필수 입력 항목입니다.
                    </h2>
                </div>

                <div class="changePW">
                    <h2 class="subtitle__changePW">
                        비밀번호 확인 <span id="mempassSpan"></span>
                    </h2>
                    <div class="description__changePW">
                        <input class="input--text password" id="memPass" name="memPass" type="password" placeholder="비밀번호 재입력" maxlength="12">
                    </div>
                    <h2 class="checkPW">
                        <i class="fas fa-exclamation-circle"></i>비밀번호가 일치하지 않습니다.
                    </h2>
                </div>

                <input type="button" href="" class="btn-setting" onclick="memPassCheck()" value="회원탈퇴">
				<input type="hidden" id="memEmail" name="memEmail" value="<%=mv.getMemEmail()%>">
				<input type="hidden" id="memId" name="memId" value="<%=mv.getMemId()%>">
				<input type="hidden" id="memDelete" name="memDelete" value="<%=mv.getMemDelete()%>">
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