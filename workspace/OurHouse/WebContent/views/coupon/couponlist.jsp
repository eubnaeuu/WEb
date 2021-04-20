<%@page import="ourhouse.common.vo.MemberVO"%>
<%@page import="ourhouse.common.vo.CouponManageVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<CouponManageVO> cpMgList = (List<CouponManageVO>)request.getAttribute("cpMgList");
	System.out.println(cpMgList.get(0).getPrice());
	System.out.println(cpMgList.get(2).getPrice());
	System.out.println(cpMgList.get(3).getPrice());
	MemberVO memVO = new MemberVO();
		memVO = (MemberVO)request.getAttribute("memberVO");

	if((MemberVO)request.getAttribute("memberVO")!=null){

	}

%>    
    
<!DOCTYPE html>
<html lang="en">
<head>  
    <meta charset="UTF-8">
    <title>우리의집 | 쿠폰 </title>

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
    <link rel="stylesheet" href="../../css/couponlist.css">
      <link rel="stylesheet" href="../../css/home.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />

    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>

    <script type="text/javascript">
 	//로그인이 안된 상태에도 쿠폰페이지가 보여줘야된다.
    $(function(){
        loginTrue();//로그인 되었을때
     });
     function loginTrue(){
        <%if(memVO == null || " ".equals(memVO.getMemId())){%> //로그인 상태가 아닐때
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
<%if(memVO != null){ %>
<input type="hidden" value="<%=memVO.getMemId() %>" id="sessionId">
<%} else{%>
<input type="hidden" value=" " id="sessionId">
<%} %>
    <!-- body 태그에 직접적으로 css는 부여하지 않는다. 컨테이너를 따로 지정하자. -->
    <!-- 언더바 두개를 사용하는 이유 -->
<div class="body__container"> <!-- body의 일부분인 컨테이너임을 의미 -->

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

    <!-- 서브 메뉴 집들이(사진), 질문과 답변, 이벤트 -->
    <header class="section sub-section">
            <div class="inner clearfix">

                <div class="section-memu float--left">
                    <ul class="section-group active">
                        <li><a href="/main.do">사진</a></li>
                        <li><a href="/qna/list.do">질문과답변</a></li>
                        <li style="border-bottom: 3px solid #35C5F0;"><a href="#" style="color:#35C5F0;" >쿠폰</a></li>
                        <li><a href="/notice/noticeHome.do">공지사항</a></li>
                    </ul>
                </div>
                <div class="sign-group float--right">
                     <a href="/member/mypage.do" class="textbold">마이페이지
                    </a>
                </div>
                
            </div>
    </header>

    <section class="section section--coupon">
        <div class="inner">

            <div class="summary__title">쿠폰</div>

            <!-- 쿠폰 리스트 컨테이너 -->
            <ul class="list-container">
			 <%if(cpMgList!=null) { %>			
			   <%for(int i = 0 ; i < cpMgList.size() ; i++) { %>
			     <%if(!cpMgList.get(i).getCpDelete().equals("Y")){ %>
                <!-- 쿠폰 한개 -->
                <li class="coupon-item"> 
                    
                    <div class="detail--use">
                        <i class="far fa-question-circle">
                            <!-- 쿠폰 상세이용 정보 -->
                            <div class="hidden-info">- 원목가구 행사상품에 적용가능
                                - 쿠폰끼리는 중복적용이 불가합니다. 포인트 및 간편결제 청구할인과는 중복사용 가능합니다.
                            </div>
                        </i>
                    </div>

                    <!-- 쿠폰 내용 -->
                    <div class="item-info">

                        <!-- 쿠폰 사용상세정보 -->

                        <!-- 남은 재고 -->
                        <div class="stock--coupon">
                            남은재고 : 
                            <span><%=cpMgList.get(i).getStock() %></span>
                        </div>

                        <!-- 상품 제목 -->
                        <div class="title--coupon">
                            <%=cpMgList.get(i).getShopName() %><%=cpMgList.get(i).getPrice() %>원 쿠폰
	                   
                        </div>
                        
                        <!-- 사용처 -->
                        <div class="where-coupon">
                            사용처
                            <span><a href="http://<%=cpMgList.get(i).getShopUrl() %>" target="_blank"><%=cpMgList.get(i).getShopUrl() %></a></span>
                        </div>

                        <div class="buy-coupon">
                            <!-- 쿠폰을 가지고 있지 않은 경우 btn-taked를 없애주세용 -->
                            <!-- 쿠폰을 이미 가지고 있는 경우 btn-take를 없애주세용-->
                            <button type="button" class="btn btn-take">받기</button>
                    		<input type="hidden" id="cpMgNo" value="<%=cpMgList.get(i).getCpMgNo() %>">
                    		<input type="hidden" id="cpStock"  value="<%=cpMgList.get(i).getStock() %>">
                     		<input type="hidden" id="cpPrice" value="<%=cpMgList.get(i).getPrice() %>">
		                     <%if(memVO!=null){ %>       
		                   	<input type="hidden" id="memId" value="<%=memVO.getMemId() %>">
		                    <input type="hidden" id="memPoint" value="<%=memVO.getMemPoint() %>">
								<%}else{ %>
							<input type="hidden" id="memId" value="<%="null" %>">
		                    <input type="hidden" id="memPoint" value="<%=" "%>">	
								<%} %>
                            <!-- <button class="btn btn-taked">
                                <i class="fas fa-check"></i>
                                받았음
                            </button> -->

                        </div>

                    </div>
                    
                </li>
                	<% } // 쿠폰 삭제 여부 확인 id 문 종료 %>
				<%} //for문 종료%>
              
			<%}  // if문 종료 %>
            </ul>

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
    <script src="../../js/takeCoupon.js"></script>   
    <script src="../js/main.js"></script>
    <script src="../js/writebtn.js"></script>

</body>
</html>
