<%@page import="ourhouse.common.vo.MemberVO"%>
<%@page import="ourhouse.common.vo.NoticeVO"%>
<%@page import="ourhouse.common.vo.QnaListVO"%>
<%@page import="ourhouse.common.vo.ColorVO"%>
<%@page import="ourhouse.common.vo.StyleVO"%>
<%@page import="ourhouse.common.vo.SpaceVO"%>
<%@page import="ourhouse.common.vo.RoomVO"%>
<%@page import="ourhouse.common.vo.HouseVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 로드시 카테고리 디비에서 가져옴  
	List<HouseVO> houseList = (List<HouseVO>) request.getAttribute("houseList");
	List<RoomVO> roomList = (List<RoomVO>) request.getAttribute("roomList");
	List<SpaceVO> spaceList = (List<SpaceVO>) request.getAttribute("spaceList");
	List<StyleVO> styleList = (List<StyleVO>) request.getAttribute("styleList");
	List<ColorVO> colorList = (List<ColorVO>) request.getAttribute("colorList");	
	
	List<QnaListVO> qnaList = (List<QnaListVO>)request.getAttribute("QnAList");

	
	NoticeVO nv = (NoticeVO)request.getAttribute("RecentNotice");
	
	MemberVO mv = (MemberVO) session.getAttribute("session");

%>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>우리의집 | 질문과 답변</title>

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
    <link rel="stylesheet" href="../../css/qnahome.css">

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

    <!-- 기존 HEADER -->
    <!-- <header class="section">
        콘텐츠가 들어가는 inner영역
        <div class="inner clearfix">

            <div class="menu-group float--left">
                
                <div class="logo">
                    <a href="/main.do">우리의집</a>
                </div>

            </div>

            <div class="sign-group float--right">

                <form id="search-form" method="GET" action="/searchTotal.do">고유한 개체에 id 명시
                    <span class="search-icon"></span>
                    <input type="text" id="search" name="search" class="input--text" placeholder="우리의집 통합검색">
                    <input type="submit" value="Submit">화면엔 있지만 숨길것임.
                </form>

                <ul class="sub-menu">
                    <li><a href="./signin.html">로그인</a></li>
                    <li><a href="./signup.html">회원가입</a></li>
                </ul>

                <div class="btn-group">
                    <a href="" class="btn">글쓰기
                        <i style="margin-left: 10px;"class="fas fa-angle-down"></i>
                    </a>
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
                        <li style="border-bottom: 3px solid #35C5F0;"><a href="/qna/list.do" style="color:#35C5F0;">질문과답변</a></li>
                        <li><a href="/coupon/list.do">쿠폰</a></li>
                        <li><a href="/notice/noticeHome.do">공지사항</a></li>
                    </ul>
                </div>

                <div class="sign-group float--right">
                     <a href="/member/mypage.do" class="textbold">마이페이지
                    </a>
                </div>
                
            </div>
    </header>

    <!-- 최근 올라온 공지사항.
         가장 최근에 게시된 공지게시글 1개 -->
    <section class="section section--notice">
        <div class="inner">

            <div class="notice-container">

                <span>📢</span>
                <span class="notice-title"><a id="noticeTitle" href="/notice/noticeHome.do">[공지사항]<%=nv.getNoticeTitle() %></a></span>
                 
            </div>

        </div>
    </section>

    <!-- 필터 카테고리 -->
    <section class="section section--category">
        <div class="inner">

            <div class="filter--group">

                <!-- 필터 선택 버튼 폼 -->
 
                <div class="dropdown">
                    <button type="button" class="category--houseType btn btn--filter">형태
                        <i style="margin-left: 10px;"class="fas fa-angle-down"></i>
                    </button>
                    <ul class="dropdown-content filter-houseType">
                      	<%for(int i = 0 ; i < houseList.size() ; i++) { %>
	                    <li value="<%=houseList.get(i).getHouseId() %>"><%=houseList.get(i).getHouseName() %></li>
	                	<%} %>
                    </ul>
                </div>

                <div class="dropdown">
                    <button type="button" class="category--room btn btn--filter">공간
                        <i style="margin-left: 10px;"class="fas fa-angle-down"></i>
                    </button>
                    <ul class="dropdown-content filter-room">
                       <%for(int i = 0 ; i < roomList.size() ; i++) { %>
	                    <li value="<%=roomList.get(i).getRoomId() %>"><%=roomList.get(i).getRoomName() %></li>
	                	<%} %>
                    </ul>
                </div>

                <div class="dropdown">
                    <button type="button" class="category--space btn btn--filter">평수
                        <i style="margin-left: 10px;"class="fas fa-angle-down"></i>
                    </button>
                    <ul class="dropdown-content filter-space">
                        <%for(int i = 0 ; i < spaceList.size() ; i++) { %>
	                    <li value="<%=spaceList.get(i).getSpaceId() %>"><%=spaceList.get(i).getSpaceName() %></li>
	                	<%} %>
                    </ul>
                </div>
                
               
	            <div class="dropdown">
                    <button type="button" class="category--style btn btn--filter">스타일
                        <i style="margin-left: 10px;"class="fas fa-angle-down"></i>
                    </button>
                    <ul class="dropdown-content filter-style">
                         <%for(int i = 0 ; i < styleList.size() ; i++) { %>
	                    <li value="<%=styleList.get(i).getStyleId() %>"><%=styleList.get(i).getStyleName() %></li>
	                	<%} %>
                    </ul>
           		</div>        
           		        
	            <div class="dropdown">
                    <button type="button" class="category--color btn btn--filter">컬러
                        <i style="margin-left: 10px;"class="fas fa-angle-down"></i>
                    </button>
                    <ul class="dropdown-content filter-color">
                         <%for(int i = 0 ; i < colorList.size() ; i++) { %>
	                    <li value="<%=colorList.get(i).getColorId() %>"><%=colorList.get(i).getColorName() %></li>
	                	<%} %>
                    </ul>
           		</div>                
                
                
                
            </div>
            
            
            
            <div class="questionBtn--group">

                <button type="button" class="btn askwrite">답변을 기다리는 질문</button>
                <button type="button" class="btn questionwrite">질문 작성하기</button>

            </div>

        </div>
    </section>

    <!-- 필터링에서 선택한 옵션들이 표시되는 컨테이너 -->
    <section class="section filter-section">
        <div class="inner">

            <form action="" class="filter--setting">

                <!-- 필터 선택 폼 [정렬] -->
                <span class="nonactive search--filter select--sort">
                    
                    <!-- 사용자에게 보여지는 선택 요소 -->
                    <span class="search--value"></span>
                    <i class="fas fa-times-circle"></i>
                </span>

                <!-- R1 등 선택 요소의 코드가 들어갈 곳 -->
                <input type="hidden" class="apply-value value--sort" value="" onchange="">
                <!-- 필터 선택 폼 [정렬] 여기까지가 선택 폼 -->

                <!-- 필터 선택 폼 [형태] -->
                <span class="nonactive search--filter select--houseType">
                    <span class="search--value"></span>
                    <i class="fas fa-times-circle"></i>
                </span>
                <input type="hidden" class="apply-value value--houseType" value="" onchange="">
                
                <!-- 필터 선택 폼 [공간] -->
                <span class="nonactive search--filter select--room">
                    <span class="search--value"></span>
                    <i class="fas fa-times-circle"></i>
                </span>
                <input type="hidden" class="apply-value value--room" value="" onchange="">

                <!-- 필터 선택 폼 [평수] -->
                <span class="nonactive search--filter select--space">
                    <span class="search--value"></span>
                    <i class="fas fa-times-circle"></i>
                </span>
                <input type="hidden" class="apply-value value--space" value="" onchange="">
                
                <!-- 필터 선택 폼 [스타일] -->
                <span class="nonactive search--filter select--style">
                    <span class="search--value"></span>
                    <i class="fas fa-times-circle"></i>
                </span>
                <input type="hidden" class="apply-value value--style" value="" onchange="">

                <!-- 필터 선택 폼 [컬러] -->
                <span class="nonactive search--filter select--color">
                    <span class="search--value"></span>
                    <i class="fas fa-times-circle"></i>
                </span>
                <input type="hidden" class="apply-value value--color" value="" onchange="">

                <button type="text" class="nonactive search--init" id="btnFilterReset">초기화</button>
            </form>
        </div>
    </section>
	
<!-- 	질문게시판 ajax로 재생성해야할 부분 -->
    <section class="section section--board">
        <div class="inner">

            <div class="QnA-container">
			
                <!-- 질문 게시글 폼 -->
                <% if(qnaList!=null){%>
                <%for(int i=0; i<qnaList.size() ;i++ ) {%>
<!--                 반복되야할 부분 -->
                <div class="writecontainer" onclick="location.href='/qna/detail.do?postNo=<%=qnaList.get(i).getPostNo()%>'">

                    <div class="write-box">

                        <div class="write-content">
                            <div class="write_title">
                            <%=qnaList.get(i).getPostTitle() %>
                            </div>

                            <div class="write_comment">
                            <%if(qnaList.get(i).getPostContent()!=null){ %>
                            <%=qnaList.get(i).getPostContent() %>
                            <%} %>
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
                        <img src="../../image/uploads/<%=qnaList.get(i).getFileFileNm() %>" alt="">
                    </div>
					<% } %>
                </div>
                <!-- 여기까지가 게시글 폼 반복해야함 -->
                 <% } // for 문 끝 %>
               <%} // if문 끝 null체크 %> 

            </div>

        </div>
    </section>

    <!-- 페이징 섹션 -->

    <section class="section section-paging">

        <div class="inner">

            <!-- 페이징 폼 -->
            <ul class="page--list">
                <li class="page-number prev--page"><i class="fas fa-chevron-left"></i></li>
                <li class="page-number active">1</li>
                <li class="page-number">2</li>
                <li class="page-number">3</li>
                <li class="page-number">4</li>
                <li class="page-number">5</li>
                <li class="page-number">6</li>
                <li class="page-number">7</li>
                <li class="page-number">8</li>
                <li class="page-number">9</li>
                <li class="page-number">10</li>
                <li class="page-number next--page"><i class="fas fa-chevron-right"></i></li>
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

    <script src="../../js/qnahome.js"></script>
    <script src="../../js/headerfixed.js"></script>
    <script src="../../js/writebtn.js"></script>
    <script>
    
    
    </script>
    
    
</body>
</html>












