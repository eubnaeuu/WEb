<%@page import="ourhouse.common.vo.MemberVO"%>
<%@page import="ourhouse.common.vo.SearchMemberVO"%>
<%@page import="ourhouse.common.vo.QnaListVO"%>
<%@page import="ourhouse.common.vo.HomePhotoVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    List<HomePhotoVO> photoList = (List<HomePhotoVO>)request.getAttribute("searchPhotos");
    List<QnaListVO> qnaList = (List<QnaListVO>)request.getAttribute("searchQnaList");
    List<SearchMemberVO> memList = (List<SearchMemberVO>)request.getAttribute("searchTotalUser");
    String search = (String)request.getAttribute("search");
	System.out.println(search);
	
	MemberVO mv = (MemberVO) session.getAttribute("session");	
%>    
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>우리의집 | 통합검색</title>

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
    <link rel="stylesheet" href="../../css/searchTotal.css">

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
        <%}%>
     }    
    </script>
</head>
<body>
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
                        <li class="write-item" onclick="">
                            <img src="../../image/PNG/picture.png" alt=""> 

                            <div class="write-info">
                                <span class="write--title">사진 올리기</span>
                                <span class="write--description">우리집 사진을 공유해보세요.</span>
                            </div>
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
                <span class="search">'<%=search %>'</span>
                <span>에 대한 통합 검색 결과</span>
                <span class="number"><%=photoList.size()+qnaList.size()+memList.size() %></span>

            </div>
        </div>
    </section>

    <!-- 사진 컨테이너 보드 -->
    <section class="section section--Photoboard">
        <div class="board">
            <div class="inner">

                <div class="search-like clearfix">

                    <div class="search-title float--left">
                        <!-- 사진 검색 -->
                        <span>사진</span>

                        <!-- 검색으로 나온 사진 갯수 -->
                        <span><%=photoList.size() %></span>
                    </div>

                    <div class="search-moreview float--right">
                        <a href="">더보기</a>
                    </div>


                </div>

                <div class="photo--container">
				<%if(photoList.size()>0) {%>
                    <!-- 요눔이 검색으로 나온 사진글 한세트 총8개만 조회하고 나머지는 더보기 이후 searchPhoto로 ! -->
                  <%for(int i=0 ; i<photoList.size() ; i++){ %>  
                    <div class="photo-card">

                        <div class="thumnail">
							
                           		 <img src="/image/uploads/<%=photoList.get(i).getFileFileNm() %>" alt="">
                            <div class="mask" onmouseenter="zoomIn()" onmouseleave="zoomOut()" onclick="location='/photo/detail.do?postNo=<%= photoList.get(i).getPostNo()%>'"> </div>

                            <div class="user-info">
                                <div class="profile-image">
                                    <img src="/image/uploads/<%=photoList.get(i).getMemPrStrImgNm() %>" alt="">
                                </div>
                                <div class="user-nickname"><%=photoList.get(i).getMemId() %></div>
                            </div>

                        </div>

                    </div>
                  	<%} %>
                 <%}else{ %>
                 	<p>검색 결과가 없습니다.</p>
				  <%} %>
                </div>
        </div>
    </section>

    <!-- 질문과 답변 컨테이너 보드 -->
    <section class="section section--QnAboard">
        <div class="inner">
                <div class="search-like clearfix">

                    <div class="search-title float--left">
                        <!-- 사진 검색 -->
                        <span>질문과 답변</span>

                        <!-- 검색으로 나온 사진 갯수 -->
                        <span><%=qnaList.size() %></span>
                    </div>

                    <div class="search-moreview float--right">
                        <a href="">더보기</a>
                    </div>

                </div>

			<%if(qnaList.size()>0) {%>
                <div class="QnA-container">
                <!-- 질문 게시글 폼 이게 한세트 입니다 총 3개만 조회해주세여 나머지는 더보기 -> 질문게시판으로 -->
                <%for(int i = 0 ; i < qnaList.size() ; i++) { %>
                <div class="writecontainer" onclick="location.href='/qna/detail.do?postNo=<%=qnaList.get(i).getPostNo()%>'">

                    <div class="write-box">

                        <div class="write-content">
                            <div class="write_title">
                               <%=qnaList.get(i).getPostTitle() %>
                            </div>

                            <div class="write_comment">
                               <%=qnaList.get(i).getPostContent() %>
                            </div>
                        </div>

                        <div class="write_info">
                            <div class="writer">
                                <span>by</span>

                                <!-- 작성자명 들어가는곳 -->
                                <span>
                                   	<%=qnaList.get(i).getMemId() %>
                                </span>
                            </div>

                            <!-- 작성날짜 -->
                            <div class="write_date">
                               <%=qnaList.get(i).getPostDate() %>
                            </div>

                            <div class="dashed_vertical"></div>

                            <div class="write_comment">
                                <span>댓글</span>
                                
                                <!-- 댓글 수 -->
                                <span>
                                    <%=qnaList.get(i).getRepCount() %>
                                </span>
                            </div>

                            <div class="dashed_vertical"></div>

                            <div class="write_lookup">
                                <span>조회</span>

                                <!-- 조회수 -->
                                <span>
                                    <%=qnaList.get(i).getPostView() %>
                                </span>
                            </div>

                        </div>
                    </div>

                    <!-- 사진 없으면 아래 div 태그는 없어도 됩니다. -->
                    <%if(qnaList.get(i).getFileFileNm()!=null){ %>
                    <div class="writeImg-box">
                        <img src="/image/uploads/<%=qnaList.get(i).getFileFileNm() %>" alt="">
                    </div>
					<%} %>
                </div>
                <%} %>
                <!-- 여기까지가 게시글 폼 -->
            </div>
			<%}else{ %>
         		<p>검색 결과가 없습니다.</p>
		 	 <%} %>

        </div>
    </section>

    

    <!-- 질문과 답변 컨테이너 보드 -->
    <section class="section section--userboard">
        <div class="inner">

                <div class="search-like">

                    <div class="search-title">
                        <!-- 사진 검색 -->
                        <span>고풍스런 유저님들</span>

                    </div>

                </div>

                <!-- 유저 리스트 -->
            <%if(memList.size()>0){ %>
             	<%for(int i=0 ; i<memList.size();i++){ %> 
                <ul class="user-list">
                    <!-- li태그 한개가 유저 한명 ! -->
                    <li>

                        <div class="user-image">
                        	<a href="/member/user.do?memId=<%=memList.get(i).getMemId() %>">
                            <img src="/image/uploads/<%=memList.get(i).getProfileImg() %>" alt="">
                        	</a>
                        </div>

                        <span class="user-name">
                           	<%=memList.get(i).getMemNickname() %>
                        </span>
                        <span class="user-Id" style="font-size: 0.75em;">
                           	[<%=memList.get(i).getMemId()%>]
                        </span>

                    </li>
                </ul>
                <%} %>
	     	<%}else{ %>
	     	<br><br>
         	<p>검색 결과가 없습니다.</p>
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

    <!-- 헤더에 글쓰기 클릭시 나오는 글쓰기 종류 -->
    <script src="../../js/writebtn.js"></script>
</body>
</html>
