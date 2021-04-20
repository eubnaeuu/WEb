<%@page import="ourhouse.common.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//세션에서 회원정보 들고오기
MemberVO mv = (MemberVO) session.getAttribute("session");

//로그인한 회원의 프로필 사진 받아오기
String img = (String) request.getAttribute("img");

//로그인한 아이디를 세션에 받아서 디비에 가서 회원의 현재 정보 가져오기
MemberVO memVO = (MemberVO) request.getAttribute("getVO");


%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>우리의집 | 회원정보 수정</title>

    <meta name="author" content="3team">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- 대표 로고 -->
    <link rel="icon" href="../../image/PNG/favicon.png">
    <link rel="apple-touch-icon" href="../../image/PNG/favicon.png">
    
    <link rel="stylesheet" href="../../css/reset.css">
    <link rel="stylesheet" href="../../css/userSetting.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
	
    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <script src="../../js/member/memberInfoUpdate.js"></script>
    <script src="../../js/myUtil.js"></script>
    <script type="text/javascript"> //상단화면 로그인여부에 따라 변경
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
                    <li><a href="<%=request.getContextPath()%>/member/signin.do" id="loginTag">로그인</a></li>
                    <li><a href="<%=request.getContextPath()%>/member/signup.do" id="signUpTag">회원가입</a></li>
                    <li><a href="/views/signin/logOut.jsp" id="logOutTag">로그아웃</a></li>
                </ul>

                <div class="btn-group">
                    <a href="" class="btn">글쓰기
                        <i style="margin-left: 10px;"class="fas fa-angle-down"></i>
                    </a>
                </div>
            </div>
        </div>
    </header> --%>
    
    <!-- 글쓰기 버튼 추가한 헤더 -->
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

                
                <li><a href="<%=request.getContextPath()%>/member/memberInfoUpdate.do">회원정보수정</a></li>
                <li><a href="<%=request.getContextPath()%>/member/mypageMailAuth.do">비밀번호 변경</a></li>
                 
            </ul>
    </header>

    <!-- 회원정보 수정 컨테이너 -->
    <section class="section section-profileSet_form">

        <form id="signupForm" enctype="multipart/form-data"><!--폼아이디-->

        <div class="inner set-container">

            <div class="set-header clearfix">
                
                <div class="title float--left">
                회원정보수정
                </div>

                <a href="<%=request.getContextPath() %>/member/memDelete.do" class="serviceOut-btn float--right">
                    탈퇴하기
                </a>

            </div>

            <div class="form--group">

                <div class="form-title">
                    <p>이메일</p>
                    <p>*필수항목</p>
                </div>

                <div class="set-form">
                    <div class="input-form email">
                    	<%if(memVO != null){ %>
                        <input class="input--text" type="text" placeholder="이메일주소 입력" id="memEmail" name="memEmail" value="<%=memVO.getMemEmail()%>" readonly="readonly">
                        <%} %>
                    </div>
                </div>
                
            </div>

            <div class="form--group">

                <div class="form-title">
                    <p>별명</p>
                    <p>*필수항목</p>
                </div>
				
                <div class="set-form">
                	<span id="memNicknameSpan"></span>
                    <div class="input-form nickname">
                    	<%if(memVO != null){ %>
                        <input class="input--text userNick" type="text" placeholder="별명" id="memNickname" name="memNickname" value="<%=memVO.getMemNickname()%>">
                        <%} %>
                        <button type="button" class="btn" onclick="nickNameCheck()">중복확인</button>
                    </div>
					

                </div>
                
            </div>

            <div class="form--group">

                <div class="form-title profileimage">
                    <p>프로필 이미지</p>
                </div>

                <div class="set-form">

                    <div class="img-container">
                        <div class="image-upload-wrap">
                            <input class="file-upload-input" name="atchFile" type='file' onchange="readURL(this);" accept="image/*" />
                            <img src="/image/uploads/<%=img %>" alt=""><!--나오는거-->
                            <div class="drag-text">
                                <h3>이미지 업로드</h3>   
                            </div>
                        </div>
                
                        <div class="file-upload-content">
                
                            <div class="imgBox">
                            	
                                <img class="file-upload-image" src="" alt="your image" /><!--원래이미지소스-->
                
                                <button type="button" onclick="removeUpload(this)" class="remove-image"><i class="fas fa-times"></i></button>
                            </div>    
                
                        </div>
                    </div>
                </div>
                
            </div>

            <div class="form--group">

                <div class="form-title simpleintroduce">
                    <p>한줄소개</p>
                </div>
		
                <div class="set-form">
                	<span id="memIntroSpan"></span>
                    <div class="input-form">
                    	<%if(memVO.getMemIntro() != null){ %>
                        <input class="input--text introduce" type="text" id="memIntro" name="memIntro" value="<%=memVO.getMemIntro()%>">
                        <%}else{ %>
                        <input class="input--text introduce" type="text" id="memIntro" name="memIntro" placeholder="한줄소개 입력" value="">
                        <%} %>
                    </div>
                </div>
                
            </div>

        <input type="button" class="btn-setting" onclick="memberInfoUpdate()"  value="회원정보수정">

        </div>

		<input type="hidden" id="checkedNickName" value="">
		<input type="hidden" id="fileCount" name="fileCount" value="">
        </form>

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


    <script src="../../js/imageupload.js"></script>
    <script src="../js/writebtn.js"></script>
</body>
</html>