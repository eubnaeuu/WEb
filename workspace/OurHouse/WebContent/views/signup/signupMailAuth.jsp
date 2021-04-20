<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>우리의집 | 회원가입</title>

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
<!--     <script type="text/javascript" src="../../js/signup/signup.js"></script> -->
	<!--회원가입 정규식,중복체크,이메일 인증 자바스크립트  -->
	<script type="text/javascript" src="../../js/signup/signup2.js?v=1"></script>
	<!--약관동의 체크 자바스크립트  -->
	<script type="text/javascript" src="../../js/signup/signupAgree.js"></script>
    <script src="../../js/myUtil.js"></script>
    <script type="text/javascript">
	//회원가입 기능
    function signup(){
    	if(!validateCheck()) return;
    	if(!confirm("🏡우리의 집🏡 회원가입을 하시겠습니까?")) return;
    	
    	$.ajax({
    		url: "/member/signup.do"
    		,type: "post"
    		,data: $("#signupForm").serialize()
    		,dataType: "json"
    		,success: function(data) {
    			console.log(data);
    			alert(data[0]);
    			//회원가입 완료되면 로그인 페이지로 이동
    			$(location).attr('href',"<%=request.getContextPath()%>/member/signin.do");
    		}
    		,error: function(xhr) {
    			alert("회원가입에 실패했습니다. 관리자에게 문의해주세요.\n오류코드 : " + xhr.status);
    		}
    	});
    }
    </script>
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

        <h2 class="summary__title">회원가입</h2>

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
                <h2 class="sign_subtitle">
                           💌이메일 <span id="emailSpan"></span>
                </h2>
                <h2 class="sign_subdescription">
                    이메일 형식으로 입력해주세요😊 
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
                         ✔아이디 <span id="memIdSpan"></span>
                </h2>
                <h2 class="sign_subdescription">
                   영문 대소문자 숫자포함 4~12자리로 입력해주세요😊 
                </h2>
                <div class="sign-form">
                    <input class="input--text password" type="text" id="memId" name="memId" placeholder="아이디 입력" maxlength="12">
                    <input type="button" href="" class="input--check" onclick="idDupCheck()" value="중복확인">
                </div>
                <h2 class="danger">
                   필수 입력 항목입니다.
                </h2>
            </div>

            <div class="sign">
                <h2 class="sign_subtitle">
                         ✔ 비밀번호<span id="passSpan"></span>
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
                          ✔비밀번호 확인<span id="mempassSpan"></span>
                </h2>
                <div class="sign-form">
                    <input class="input--text password" type="password" id="memPass" name="memPass" placeholder="비밀번호 재입력" maxlength="12">
                </div>
                <h2 class="pass-danger">
                    비밀번호가 일치하지 않습니다.
                </h2>
            </div>

            <div class="sign">
                <h2 class="sign_subtitle">
                      ✔별명	<span id="memNicknameSpan"></span>
                </h2>
                <h2 class="sign_subdescription">
                    한글 2~15글자로 입력해주세요😊
                </h2>
                <div class="sign-form">
                    <input class="input--text password" type="text" id="memNickname" name="memNickname" placeholder="별명 (2~15자)" maxlength="15">
                    <input type="button" href="" class="input--check" onclick="nickNameCheck()" id="nickNameChk" value="중복확인">
                </div>
                    
                <h2 class="pass-danger">
                    필수 입력 항목입니다.
                </h2>
            </div>

            <div class="sign">
                <h2 class="sign_subtitle">
                    약관동의
                </h2>
                <div class="agreement">
                    <div class="check_container" id="checker">
                        <div class="allcheck">
                            <input class="all--checker" id="all" type="checkbox" value="">
                            <label for="all"><span></span>전체동의</label>
                        </div>  

                        <div class="agecheck">
                            <input class="all--checker" id="age" type="checkbox" value="">
                            <label for="age"><span></span>만14세 이상입니다.</label><span class="necessary">(필수)</span>
                            <span class="necessary"></span>
                        </div>  
                        <div class="usecheck">
                            <input class="all--checker" id="use" type="checkbox" value="">
                            <label for="use"><span></span><a href="#">이용약관</a></label><span class="necessary">(필수)</span>
                        </div>  
                        <div class="privacycheck">
                            <input class="all--checker" id="privacy" type="checkbox" value="">
                            <label for="privacy"><span></span><a href="#">개인정보처리방침</a></label><span class="necessary">(필수)</span>
                        </div>  
                        <div class="snscheck">
                            <input class="all--checker" id="sns" type="checkbox" value="">
                            <label for="sns"><span></span>이벤트,프로모션 알림 메일 및 SMS 수신</label><span class="select">(선택)</span>
                        </div>
                    </div>
                </div>
            </div>
			<input type="hidden" id="checkedId">
			<input type="hidden" id="checkedNcikName">
			<input type="hidden" id="checkedEmail">
			<input type="hidden" id="checkedUserAuth">
            <input type="button" href="" class="btn" onclick="signup()" value="회원가입 완료">

            </form>

            <div class="takeid">
                이미 아이디가 있으신가요?<a href="<%=request.getContextPath()%>/member/signin.do">로그인</a>
            </div>
        </div>
    </section>

</div>
<!--     <script src="../js/signup.js"></script> -->
</body>
</html>












