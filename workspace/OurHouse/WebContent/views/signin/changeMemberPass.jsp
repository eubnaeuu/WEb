<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>우리의집 | 비밀번호 재설정</title>

    <meta name="author" content="3team">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <!-- 대표 로고 -->
    <link rel="icon" href="../../image/PNG/favicon.png">
    <link rel="apple-touch-icon" href="../../image/PNG/favicon.png">
    
    <link rel="stylesheet" href="../../css/reset.css">
    <link rel="stylesheet" href="../../css/signup.css">

    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    
    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
	<!--비밀번호 수정 자바스크립트  -->
	<script type="text/javascript" src="../../js/signin/changeMemberPass.js?v=1"></script>
    <script src="../../js/myUtil.js"></script>
</head>
<body>
    <!-- body 태그에 직접적으로 css는 부여하지 않는다. 컨테이너를 따로 지정하자. -->
    <!-- 언더바 두개를 사용하는 이유 -->
<div class="body__container"> <!-- body의 일부분인 컨테이너임을 의미 -->

    <!-- HEADER -->
    <header class="section">
        <!-- 콘텐츠가 들어가는 inner영역 -->
        <div class="inner clearfix">

            <div class="menu-group float--left">
                
                <div class="logo">
                    <img src="../../image/PNG/favicon.png" alt="">
                    <a href="<%=request.getContextPath()%>/main.do">우리의집</a>
                </div>

            </div>

        </div>
    </header>

    <section class="section section-signup">

        <h2 class="summary__title">비밀번호 재설정</h2>

        <div class="sign--api">
            <p class="summary__description">SNS 계정으로 간편 회원가입</p>
            <div class="api--service">
                <div class="icon facebook-signup"><i class="xi-3x xi-facebook"></i></div>
                <div class="icon kakaotalk-signup"><i class="xi-3x xi-kakaotalk text-white"></i></div>
                <div class="icon naver-signup"><i class="xi-3x xi-naver"></i></div>
            </div>
        </div>

        <div class="inner sign-group">

            <form id="signupForm">

            <div class="sign">
                <h2 class="sign_subtitle" id="emailInput">
                           💌이메일 <span id="emailSpan"></span>
                </h2>
                <h2 class="sign_subdescription">
                    비밀번호 변경을 위해 이메일인증이 필요합니다😊
                </h2>                
                <div class="sign-form">
                    <input class="input--text email1" type="text" id="memEmail" name="memEmail" placeholder="이메일 입력" >
                    <input type="button" href="" class="input--check" onclick="memEmailDbCheck()" value="인증받기">
                </div>
                <h2 class="danger"> 
                    필수 입력 항목입니다.
                </h2>
            </div>
            
            <div class="sign">
                <h2 class="sign_subtitle">
                           💌이메일 인증 <span id="emailChkSpan"></span>
                </h2>
                <div class="sign-form">
                    <input class="input--text email2" type="text" id="userAuth" name="userAuth" placeholder="인증번호 입력" >  
                    <input type="button" href="" class="input--check" onclick="authCheck()" value="인증번호 확인">
                </div>
                <h2 class="danger"> 
                    필수 입력 항목입니다.
                </h2>
            </div>

            <div class="sign">
                <h2 class="sign_subtitle">
                   새비밀번호 ✔<span id="passSpan"></span>
                </h2>
                <h2 class="sign_subdescription">
                   영문 대소문자 숫자포함 8-12자리 입력해주세요😊
                </h2>                
                <div class="sign-form">
                    <input class="input--text password" type="password" id="pass" name="pass" placeholder="비밀번호 입력" maxlength="12">
                </div>
                <h2 class="danger">
                    필수 입력 항목입니다.
                </h2>
            </div>

            <div class="sign">
                <h2 class="sign_subtitle">
                    새비밀번호 확인✔<span id="mempassSpan"></span>
                </h2>
                <div class="sign-form">
                    <input class="input--text password" type="password" id="memPass" name="memPass" placeholder="비밀번호 재입력" maxlength="12">
                    <input type="button" href="" class="input--check" onclick="memPassCheck()" value="비밀번호 수정">
                </div>
                <h2 class="pass-danger">
                    비밀번호가 일치하지 않습니다.
                </h2>
            </div>
            
			<!--이메일 가입된 회원인지 아닌지 아이디인지 메일인증번호 확인을 위한 id값들  -->
			<input type="hidden" id="checkedEmail">
			<input type="hidden" id="checkedUserAuth">
			<input type="hidden" id="checkedMemPass">

            </form>

<!--             <div class="takeid"> -->
<%--                 로그인 해보실래요?💨<a href="<%=request.getContextPath()%>/member/signin.do">로그인</a> --%>
<!--             </div> -->
        </div>
    </section>

</div>
</body>
</html>












