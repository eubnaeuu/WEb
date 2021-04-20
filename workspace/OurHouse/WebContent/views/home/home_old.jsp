<%@page import="ourhouse.common.vo.MemberVO"%>
<%@page import="ourhouse.common.vo.ColorVO"%>
<%@page import="ourhouse.common.vo.StyleVO"%>
<%@page import="ourhouse.common.vo.SpaceVO"%>
<%@page import="ourhouse.common.vo.RoomVO"%>
<%@page import="ourhouse.common.vo.HouseVO"%>
<%@page import="java.io.File"%>
<%@page import="ourhouse.common.vo.HomePhotoVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<HomePhotoVO> homePhotoList = (List<HomePhotoVO>) request.getAttribute("homePhotoList");
	List<HomePhotoVO> topPhotoList = (List<HomePhotoVO>) request.getAttribute("topPhotoList");
	HomePhotoVO topOneVO = topPhotoList.get(0);
	HomePhotoVO topTwoVO = topPhotoList.get(1);
	
	List<HouseVO> houseList = (List<HouseVO>) request.getAttribute("houseList");
	List<RoomVO> roomList = (List<RoomVO>) request.getAttribute("roomList");
	List<SpaceVO> spaceList = (List<SpaceVO>) request.getAttribute("spaceList");
	List<StyleVO> styleList = (List<StyleVO>) request.getAttribute("styleList");
	List<ColorVO> colorList = (List<ColorVO>) request.getAttribute("colorList");
	
	String memId = (String) request.getAttribute("memId");
	//세션받아오기
// 	HttpSession session2 = request.getSession(false);
	
// 	if(session2 == null) {
// 		System.out.println("로그인안함");
// 	}else {
// 		MemberVO mv = (MemberVO) session2.getAttribute("session");
		
// 		System.out.println(mv.getMemId() + "님 로그인중, ");

		//지현작업---
		MemberVO mv = (MemberVO) request.getSession().getAttribute("session");
		
		for(int i = 0 ; i < homePhotoList.size() ; i++){
			System.out.print("팔로우여부:"+homePhotoList.get(i).getFollowYN());
			System.out.print("좋아요여부:"+homePhotoList.get(i).getLikeYN());
		}
// 	}
	
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>우리의집 | 메인</title>

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
    <link rel="stylesheet" href="../../css/home.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />

    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
	
	<!--지현작업-->    
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
    <!-- body 태그에 직접적으로 css는 부여하지 않는다. 컨테이너를 따로 지정하자. -->
    <!-- 언더바 두개를 사용하는 이유 -->
<div class="body__container"> <!-- body의 일부분인 컨테이너임을 의미 -->

    <!-- HEADER -->
    <header class="section">
        <!-- 콘텐츠가 들어가는 inner영역 -->
        <div class="inner clearfix">

            <div class="menu-group float--left">
                
                <div class="logo">
                    <a href="/main.do">로고</a>
                </div>

            </div>

            <div class="sign-group float--right">
 				<!-- <a href="/member/mypage.do">마이페이지</a> -->
                <form id="search-form" method="POST" action=""><!-- 고유한 개체에 id 명시 -->
                    <span class="search-icon"></span>
                    <input type="text" id="search" class="input--text" placeholder="우리의집 통합검색">
                    <input type="submit" value="Submit"><!-- 화면엔 있지만 숨길것임. -->
                </form>

                <ul class="sub-menu">
                	<!--지현작업-->
                	<%-- <span><%=mv.getMemId()%>님 접속중</span> --%>
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
    </header>

    <!-- 서브 메뉴 집들이(사진), 질문과 답변, 이벤트 -->
    <header class="section sub-section">
            <div class="inner clearfix">

                <div class="section-memu float--left">
                    <ul class="section-group active">
                        <li><a href="#">사진</a></li>
                        <li style="border-bottom: 3px solid #35C5F0;"><a href="#" style="color:#35C5F0;">질문과 답변</a></li>
                        <li><a href="#">쿠폰</a></li>
                        <li><a href="#">공지사항</a></li>
                    </ul>
                </div>

                <div class="sign-group float--right">
                    <a href="/member/mypage.do" class="textbold">마이페이지
                    </a>
                </div>
                
            </div>
    </header>

    <!-- 메인 배너 -->
    <section class="section section--banner">

        <div class="summary">
            <h2 class="summary__title">오늘의 스토리</h2>
        </div>

        <div class="inner">
            <ul class="banner__inner">
                <li class="banner-main">
                    <div class="main">
                        <img class="over" src="../../image/uploads/<%=topOneVO.getFileFileNm() %>" alt="">
                        <div class="mask" onmouseenter="zoomIn()" onmouseleave="zoomOut()">
                        <div class="banner__description">
                            <div class="title_banner">
                                <p>인기 게시글 #1</p>
                                <div class="profile">
                                    <ul class="userinfo">
                                        <li><span><img src="../../image/uploads/<%=topOneVO.getMemPrStrImgNm() %>" alt=""></span></li>
                                        <li><%=topOneVO.getMemId() %></li>
                                    </ul>
                                </div>
                            </div>

                            <div class="btn_banner">
                                <a href="" class="btn">보러가기</a>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="banner-sub">
                    <div class="sub">
                        <img class="over" src="../../image/uploads/<%=topTwoVO.getFileFileNm() %>" alt="">
                        <div class="mask" onmouseenter="zoomIn()" onmouseleave="zoomOut()">
                            
                        <div class="banner__description">
                            <div class="title_banner">
                                <p>인기 사진글 #2</p>
                                <div class="profile">
                                    <ul class="userinfo">
                                        <li><span><img src="../../image/uploads/<%=topTwoVO.getMemPrStrImgNm()%>" alt=""></span></li>
                                        <li><%=topTwoVO.getMemId() %></li>
                                    </ul>
                                </div>
                            </div>

                            <div class="btn_banner">
                                <a href="" class="btn">보러가기</a>
                            </div>
                        </div>
                        </div>
                    </div>
                </li>   
            </ul>
        </div>

    </section>

    <!-- 카테고리 -->
    <section class="section section--category">
        <div class="inner">

            <div class="dropdown">
                <button type="button" class="category--type btn">형태
                    <i style="margin-left: 10px;"class="fas fa-angle-down"></i>
                </button>
                <div class="dropdown-content">
                	<%for(int i = 0 ; i < houseList.size() ; i++) { %>
                    <a href="#" title="<%=houseList.get(i).getHouseId() %>"><%=houseList.get(i).getHouseName() %></a>
                	<%} %>
                </div>
            </div>

            <div class="dropdown">
                <button type="button" class="category--space btn">공간
                    <i style="margin-left: 10px;"class="fas fa-angle-down"></i>
                </button>
                <div class="dropdown-content">
                	<%for(int i = 0 ; i < roomList.size() ; i++) { %>
                    <a href="#" title="<%=roomList.get(i).getRoomId() %>"><%=roomList.get(i).getRoomName() %></a>
                	<%} %>
                </div>
            </div>

            <div class="dropdown">
                <button type="button" class="category--acreage btn">평수
                    <i style="margin-left: 10px;"class="fas fa-angle-down"></i>
                </button>
                <div class="dropdown-content">
               	<%for(int i = 0 ; i < spaceList.size() ; i++) { %>
                    <a href="#" title="<%=spaceList.get(i).getSpaceId() %>"><%=spaceList.get(i).getSpaceName() %></a>
                	<%} %>
                </div>
            </div>

            <div class="dropdown">
                <button type="button" class="category--style btn">스타일
                    <i style="margin-left: 10px;"class="fas fa-angle-down"></i>
                </button>
                <div class="dropdown-content">
               		<%for(int i = 0 ; i < styleList.size() ; i++) { %>
                    <a href="#" title="<%=styleList.get(i).getStyleId() %>"><%=styleList.get(i).getStyleName() %></a>
                	<%} %>
                </div>
            </div>

            <div class="dropdown">
                <button type="button" class="category--color btn">컬러
                    <i style="margin-left: 10px;"class="fas fa-angle-down"></i>
                </button>
                <div class="dropdown-content">
               		<%for(int i = 0 ; i < colorList.size() ; i++) { %>
                    <a href="#" title="<%=colorList.get(i).getColorId() %>"><%=colorList.get(i).getColorName() %></a>
                	<%} %>
                </div>
            </div>
            
        </div>
    </section>
	
	<!-- 사진 게시글 출력 -->
    <section class="section section--board">
        <div class="board">
            <div class="inner">

	<%for(int i = 0 ; i < homePhotoList.size() ; i++){ %>
	<%HomePhotoVO hpVO = homePhotoList.get(i); %>
                <div class="grid_test">

                    <div class="profile">
                        <ul class="user">
                            <li class="user_image">
                                <a href="/member/user.do?userId=<%=hpVO.getMemId() %>">
                                <img src="../../image/uploads/<%=hpVO.getMemPrStrImgNm() %>" alt="">
                                </a>
                            </li>
                            <li class="user_info">
                                <div class="follow">
                                    <a href="#">
                                    	<span class="user-id"><%=hpVO.getMemId() %></span>
                                    </a>
                                    <span class="dot">·</span>
                                    <a href="#">
                                    <%if(" ".equals(memId)){ %>
                                    	<span class="user-follow">팔로우</span>
	                        		<%}else{ %>
                                    	<%if("Y".equals(hpVO.getFollowYN())){ %>
                                    	<span class="user-following">팔로잉</span>
                                    	<%} else{ %>
                                    	<span class="user-follow">팔로우</span>
                                    	<%} %>
	                        		<%} %>
                                    </a>
                                </div>
                                <div class="user-introduce">
                                    <%=hpVO.getMemIntro() %>
                                </div>
                            </li>
                        </ul>
                    </div>

                    <div class="thumnail">
                        <img src="../../image/uploads/<%=hpVO.getFileFileNm() %>" alt="">
                        <div class="mask" onmouseenter="zoomIn()" onmouseleave="zoomOut()" onclick="location='/photo/detail.do?postNo=<%= hpVO.getPostNo()%>'">
                        
                            <div class="thumnail-view">
                                <ul class="view_chart">
                                    <li>조회수</li>
                                    <li><%=hpVO.getPostView() %></li>
                                </ul>
                            </div>

                        </div>
                    </div>
                    <div class="likeandcomment">
                        <div class="like">
                        	<%if(" ".equals(memId)){ %>
	                        	<i class="far fa-heart"></i>
	                        <%}else{ %>
	                            <%if("Y".equals(hpVO.getLikeYN())){ %>
	                        	<i class="fas fa-heart"></i>
                                <%} else{ %>
	                        	<i class="far fa-heart"></i>
                                <%} %>
	                        <%} %>
	                        <span class="like_num"><%=hpVO.getLikeCount() %></span>
                        </div>
                        <div class="comment">
                        	<i class="far fa-comment-alt"></i>
                        	<span class="comment_num"><%=hpVO.getRepCount() %></span>
                        </div>
                    </div>

                    <div class="user-description">
                        <%=hpVO.getFileCn() %>
                    </div>

				<%if(!" ".equals(hpVO.getRepMemId())){ %>
                    <div class="user-card">
                        <div class="user_image">
                                <img src="../../image/uploads/<%=hpVO.getRepPrImgNm() %>" alt=""></div>
                        <div class="user_comment">
                            <p class="user-comment">
                            <span class="user_id"><%=hpVO.getRepMemId() %></span>
                            	<%=hpVO.getRepContent() %>
                            </p>
                        </div>
                    </div>
                <%} %>

                </div>
    <%} %>
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

    <script src="../js/main.js"></script>
</body>
</html>












