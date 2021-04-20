<%@page import="ourhouse.common.vo.MemberVO"%>
<%@page import="ourhouse.common.vo.HomePhotoVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 
 	List<HomePhotoVO> photolist = (List<HomePhotoVO>)request.getAttribute("PhotoList");
	
 	String hashtag = (String)request.getAttribute("hashtag");
 	
 	MemberVO mv = (MemberVO) session.getAttribute("session");
 	
 	String sessionId = " ";
  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>우리의집 | 태그검색</title>

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
    <link rel="stylesheet" href="../../css/searchPhoto.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />

    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
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
           <%sessionId = mv.getMemId();%>
        <%}%>
     }    
    </script>    
</head>
<body>
<input type="hidden" id="sessionId" value="<%=sessionId %>">
    <!-- body 태그에 직접적으로 css는 부여하지 않는다. 컨테이너를 따로 지정하자. -->
    <!-- 언더바 두개를 사용하는 이유 -->
<div class="body__container"> <!-- body의 일부분인 컨테이너임을 의미 -->

    <!-- HEADER -->
    <!-- <header class="section section--header">
        콘텐츠가 들어가는 inner영역
        <div class="inner clearfix">

            <div class="menu-group float--left">
                
                <div class="logo">
                    <a href="/main.do">우리의집</a>
                </div>

            </div>

            <div class="sign-group float--right">

                <form id="search-form" method="POST" action="">고유한 개체에 id 명시
                    <span class="search-icon"></span>
                    <input type="text" id="search" class="input--text" placeholder="우리의집 통합검색">
                    <input type="submit" value="Submit">화면엔 있지만 숨길것임.
                </form>

                <ul class="sub-menu">
                    <li><a href="/member/signin.do">로그인</a></li>
                    <li><a href="/member/signup.do">회원가입</a></li>
                </ul>

                <div class="btn-group dropdown--writelist">
                    <button type="button" class="btn btn-write">글쓰기
                        <i style="margin-left: 10px;"class="fas fa-angle-down"></i>
                    </button>
                    
                    <ul class="dropdown-write">
                        <li class="write-item" onclick="">
                            <img src="../../image/PNG/picture.png" alt=""> 

                            <div class="write-info">
                                <span class="write--title">사진 올리기</span>
                                <span class="write--description">우리집 사진을 공유해보세요.</span>
                            </div>
                        </li>
                        <li class="write-item" onclick="clickgo()">
                            <img src="../../image/PNG/question.png" alt=""> 

                            <div class="write-info">
                                <span class="write--title">인테리어 질문하기</span>
                                <span class="write--description">인테리어 고수들에게 질문해보세요.</span>
                            </div>
                        </li>
                    </ul>

                </div>
            </div>
        </div>
    </header> -->
    
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
                        <li><a href="/coupon/list.do">쿠폰</a></li>
                        <li><a href="/notice/noticeHome.do">공지사항</a></li>
                    </ul>
                </div>

                <div class="sign-group float--right">
                    <a href="/member/mypage.do">마이페이지
                    </a>
                </div>
                
            </div>
    </header>

    <!-- 통합검색시 검색어 필터표시되는곳 -->
    <section class="section section--searchfilter">
        <div class="inner clearfix">
            <div class="search--number float--left">
                
                <!-- <span>'+ (검색 내용) +' </span> <span>에 대한 사진 검색 결과</span>-->
                <span class="search">'<%=hashtag %>'</span>
                <span>에 대한 사진 검색 결과</span>
                <span class="number"><%=photolist.size() %></span>

            </div>

            <!-- 질문게시판 홈으로 가는 링크 -->
            <div class="link--qna float--right">
                <a href=""> </a>
            </div>
        </div>
    </section>
    


    <section class="section section--board">
        <div class="board">
            <div class="inner">

                <!-- photh-card div 가 게시글 하나-->
                <!-- 수정사항 : 댓글이 표시되자 않음 -->
              <%if(photolist.size()>0){ %>
                <%for(int i=0 ; i < photolist.size(); i++){ %>
                <div class="photo-card">

                    <div class="profile">
                        <ul class="user">
                            <li class="user_image">
                                <a href="/member/user.do?memId=<%=photolist.get(i).getMemId() %>" >
                                <img src="/image/uploads/<%=photolist.get(i).getMemPrStrImgNm() %>" alt="">
                                </a>                                                            
                            </li>
                            <li class="user_info">
                                <div class="follow">
                                    <a href="/member/user.do?memId=<%=photolist.get(i).getMemId() %>"><span class="user-id"><%=photolist.get(i).getMemId() %></span></a>
                                    <span class="dot">·</span>
                                     <%if("Y".equals(photolist.get(i).getFollowYN())){ %>
	                                 <a>
                                       <span class="user-following <%=photolist.get(i).getMemId() %>" onclick="unfollow('<%=photolist.get(i).getMemId()%>')">팔로잉</span>
                                     </a>
                                    <%} else{ %>
	                                 <a>
                                       <span class="user-follow <%=photolist.get(i).getMemId() %>" onclick="follow('<%=photolist.get(i).getMemId()%>')">팔로우</span>
                                     </a>
                                    <%} %>
                                </div>
                                <div class="user-introduce">
                                    <%=photolist.get(i).getMemIntro() %>
                                </div>
                            </li>
                        </ul>
                    </div>

                    <div class="thumnail">
                        	<img src="/image/uploads/<%=photolist.get(i).getFileFileNm() %>" alt="">
                        <div class="mask" onmouseenter="zoomIn()" onmouseleave="zoomOut()" onclick="location='/photo/detail.do?postNo=<%= photolist.get(i).getPostNo()%>'">
                        
                            <div class="thumnail-view">
                                <ul class="view_chart">
                                    <li>조회수</li>
                                    <li><%=photolist.get(i).getPostView() %></li>
                                </ul>
                            </div>

                        </div>
                    </div>

                    <div class="likeandcomment">
                        <div class="like"><i class="far fa-heart"></i> <span class="like_num"><%=photolist.get(i).getLikeCount() %></span></div>
                        <div class="comment"><i class="far fa-comment-alt"></i> <span class="comment_num"><%=photolist.get(i).getRepCount() %></span></div>
                    </div>

                    <div class="user-description">
                        <%=photolist.get(i).getFileCn() %>
                    </div>

                </div>
                	<%} %>
                <%}else{ %>
                <div class="photo-card">
                <span>해당 게시글이 없습니다.</span>
                </div>
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

    <!-- 사진 줌인아웃 -->
    <script src="../../js/zooInOut.js"></script>

    <!-- 헤더 고정 -->
    <script src="../../js/headerfixed.js"></script>

    <!-- 사진 검색 페이지 필터 -->
    <script src="../../js/searchPhoto.js"></script>

    <!-- 헤더에 글쓰기 클릭시 나오는 글쓰기 종류 -->
    <script src="../../js/writebtn.js"></script>
</body>
</html>


