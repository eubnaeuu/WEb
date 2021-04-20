<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>우리의집 | 아이디찾기</title>

    <meta name="author" content="3team">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <!-- 대표 로고 -->
    <link rel="icon" href="../../image/PNG/favicon.png">
    <link rel="apple-touch-icon" href="../image/PNG/favicon.png">
    
    <link rel="stylesheet" href="../../css/reset.css">
    <link rel="stylesheet" href="../../css/signin.css">

    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    
    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <script src="../../js/myUtil.js"></script>
	<script src="../../js/signin/findId.js" ></script>	
</head>
<body>
    <!-- body 태그에 직접적으로 css는 부여하지 않는다. 컨테이너를 따로 지정하자. -->
    <!-- 언더바 두개를 사용하는 이유 -->
<div class="body__container"> <!-- body의 일부분인 컨테이너임을 의미 -->

    <!-- HEADER -->
    <section class="section section-login">
        <!-- 콘텐츠가 들어가는 inner영역 -->
        <div class="inner">
            <a href="<%=request.getContextPath()%>/main.do" class="login-logo"></a>

            <form action="" class="signin" id="findIdForm">
	            <div class="signform">
					<h2 class="sign_subtitle">
	                          💌이메일  <span id="emailSpan"></span><!--이메일 정규식 성공했을때 뜨는 메시지-->
	                </h2>
	                <input class="input--text text--pw" type="text" name="memEmail" id="memEmail" placeholder="이메일 입력">
	            </div>
            
            	<input type="button" href="<%=request.getContextPath()%>/member/findId.do" class="btn" onclick="findId()" value="아이디 찾기"></a>
            </form>

            <div class="reset">
                <a href="<%=request.getContextPath()%>/member/signin.do">로그인💬</a>
                <a href="<%=request.getContextPath()%>/member/changeMemPass.do">비밀번호 재설정💬</a>
            </div>

            <div class="sign--api">
            <p class="summary__description">SNS 계정으로 간편 로그인</p>
            <div class="api--service">
                <div class="icon facebook-signup"><i class="xi-3x xi-facebook"></i></div>
                <div class="icon kakaotalk-signup"><i class="xi-3x xi-kakaotalk text-white"></i></div>
                <div class="icon naver-signup"><i class="xi-3x xi-naver"></i></div>
            </div>
            </div>
            <hr class="line">
        </div>
    </section>
</div>


<footer class="section section-footer">
    @Deadokplace Inc. All Right Reserved
</footer>

    
</body>
</html>