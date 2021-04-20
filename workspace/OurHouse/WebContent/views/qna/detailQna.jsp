<%@page import="ourhouse.common.vo.MemberVO"%>
<%@page import="ourhouse.common.vo.ReasonVO"%>
<%@page import="ourhouse.common.vo.ReplyVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ourhouse.common.vo.AtchFileVO"%>
<%@page import="java.util.List"%>
<%@page import="ourhouse.common.vo.PhotoDetailVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
PhotoDetailVO qnaDetailVO = (PhotoDetailVO) request.getAttribute("qnaDetailVO");
List<AtchFileVO> qnaFileList = (List<AtchFileVO>) request.getAttribute("qnaFileList");

List<ReplyVO> replyList = (List<ReplyVO>) request.getAttribute("replyList");
List<ReasonVO> reasonList = (List<ReasonVO>) request.getAttribute("reasonList");
String sessionPrfImg = (String) request.getAttribute("sessionPrfImg");
String sessionId = (String) request.getAttribute("sessionId");

String[] category = new String[5];
System.out.println(qnaDetailVO);
category[0] = qnaDetailVO.getHouseName();
category[1] = qnaDetailVO.getRoomName();
category[2] = qnaDetailVO.getSpaceName();
category[3] = qnaDetailVO.getStyleName();
category[4] = qnaDetailVO.getColorName();

MemberVO mv = (MemberVO) session.getAttribute("session");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>우리의집 | 질문글 보기</title>

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
    <link rel="stylesheet" href="../../css/qnaView.css">

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
<input type="hidden" value="<%=sessionId %>" id="sessionId">
    <!-- body 태그에 직접적으로 css는 부여하지 않는다. 컨테이너를 따로 지정하자. -->
    <!-- 언더바 두개를 사용하는 이유 -->
<div class="body__container"> <!-- body의 일부분인 컨테이너임을 의미 -->

    <!-- 기존 HEADER -->
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
                        <li><a href="/qna/list.do">질문과답변</a></li>
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

    <section class="section section--qnaView">
        <div class="inner">

            <div class="qna-container">
                <div class="question-container">
                    
                    <div class="question-title">

                        <ul class="view-title">
                            <li>질문과 답변</li>
                            <li class="filter">
                            	<%for(int i = 0 ; i < category.length ; i++){ %>
	                            	<%if(!(category[i].equals(" "))){ %>
	                                <span><%=category[i] %></span>
	                                <%} %>
                                <%} %>
                            </li>
                            <li><%=qnaDetailVO.getPostTitle() %></li>
                        </ul>

                    </div>

                    <div class="photo-contents">
						
						<%if(qnaFileList.size() > 0) {%>
                        	<%for(int i = 0 ; i < qnaFileList.size() ; i++){ %>
                        <img src="/image/uploads/<%=qnaFileList.get(i).getStreFileNm() %>" alt="<%=qnaFileList.get(i).getOrignlFileNm() %>">
                        	<%} %>
                        <%} %>
                        <p><%=qnaDetailVO.getPostContent() %></p>
                        
                    </div>

                    <div class="photo-status">
                        
                        <ul class="status-item">
                            
                            <li>
                                조회
                                <span>
                                    <%=qnaDetailVO.getPostView() %>
                                </span>
                            </li>

                            <div class="vertical-dot"></div>
                            
                            <li>
                                댓글
                                <span id="repCount">
                                    <%=qnaDetailVO.getRepCount() %>
                                </span>
                            </li>

                            <div class="vertical-dot"></div>
                            
                            <%if(qnaDetailVO.getMemId().equals(sessionId)){ %>              
                            <li class="report" onclick="deletePost('<%=qnaDetailVO.getPostNo()%>')">
                                	삭제
                            </li>
                            <%} %>
                            <%if(!qnaDetailVO.getMemId().equals(sessionId)){ %>
                            <li class="report report-board" onclick="reportThis(<%=qnaDetailVO.getPostNo()%>)">
                               		 신고
                            </li>
                            <%} %>

                        </ul>

                    </div>

                    <div class="photo-comment">

                        <div class="comment-status">
                            <span class="title">
                                댓글
                            </span>
                            <span class="comment-number">
                                <%=qnaDetailVO.getRepCount() %>
                            </span>
                        </div>

                        <div class="comment-write">

                            <div class="write-profile">
                                <img src="/image/uploads/<%=sessionPrfImg %>" alt="<%=sessionId%>">
                            </div>

                            <div class="write">
                                <!-- 건이가 준 qna 용 댓글 submit -->
                                <!--
                                <form class="input--comment">
                                    <input onsubmit="return false" type="text" id="input_comment" class="comment--text" placeholder="비방 및 욕설시 당신의 손모가지는 날아갑니다 :) ">
                                    <span class="comment_submit">등록</span>
                                </form>
                                 -->
                                <!-- photo 에서 가져온 양식 -->
                                <form class="input--comment">
                                    <input type="text" id="input_comment" class="comment--text" 
                                    	   onkeydown="if (event.keyCode === 13) { event.preventDefault(); insertReply('<%=qnaDetailVO.getPostNo() %>')};" 
                                    	   placeholder="비방 및 욕설시 당신의 손모가지는 날아갑니다 :) " >
                                    <input type="button" id="comment_submit" value="등록" onclick="insertReply('<%=qnaDetailVO.getPostNo() %>')" disabled>
                                </form>
                            </div>

                        </div>

                        <div class="comment-list">

                            <ul class="usercomment-container">
                            
                                <%for (int i = 0; i < replyList.size() ; i++) { %>
                                <li id="<%=replyList.get(i).getReplyNo() %>" class="other-comment">

                                    <div class="user-profile">
                                        <img src="/image/uploads/<%=replyList.get(i).getRepPrStrImgNm() %>" alt="">
                                    </div>

                                    <div class="user-comment">

                                        <div class="nameandcomment">
                                            
                                            <span class="username">
                                            	<input type="hidden" value="<%=replyList.get(i).getMemId() %>"> <!-- 별명으로 수정했을때 id 필요해서 남겨둠 -->
                                            	<%=replyList.get(i).getMemId() %> <!-- 나중에 별명으로 수정할 것임 -->
                                            </span>

                                            <p class="usercomment">
                                                <%=replyList.get(i).getReplyContent() %>
                                            </p>

                                        </div>

                                        <div class="comment-report">
                                            <ul class="report-list">
                                                <li>
                                                    <span><%=replyList.get(i).getReplyDate() %></span>
                                                </li>

												<%if(replyList.get(i).getMemId().equals(sessionId)){ %>
												<div class="vertical-dot"></div>
                                                <li class="report">
                                                    <span onclick="deleteReply('<%=replyList.get(i).getReplyNo() %>')">삭제</span>
                                                </li>
                                                <%} %>
                                                <%if(!replyList.get(i).getMemId().equals(sessionId)) {%>
                                                <div class="vertical-dot"></div>
                                                <li class="report report-comment" onclick="reportThis(<%=replyList.get(i).getReplyNo()%>)">
                               		 					신고
                            					</li>
                            					<%} %>
                                            </ul>
                                        </div>

                                    </div>

                                </li>
							<%} //for문 닫음 %>
                            </ul>

                        </div>

                    </div>


                    <div class="paging--container">

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


                </div>

                <div class="writerinfo-container">
                    
                    <div class="writerinfo">

                        <div class="writer-profile">
                            <div class="writerimage">
	                        	<a href="/member/user.do?memId=<%=qnaDetailVO.getMemId() %>">
	                            	<img src="/image/uploads/<%=qnaDetailVO.getMemPrStrImgNm() %>" alt="">
	                            </a>
                       		</div>

                            <div class="writername">
                                
                                <span>
                                	<%=qnaDetailVO.getMemId() %>
                                </span>
								
								<div>
								<%if(!qnaDetailVO.getMemId().equals(sessionId)){ //본인이 아닐 때%>
		                            <%if("Y".equals(qnaDetailVO.getFollowYN())){ %>
		                            <span class="following" onclick="unfollow('<%=qnaDetailVO.getMemId()%>')">팔로잉</span>
		                            <%}else{ %>
		                            <span class="follow" onclick="follow('<%=qnaDetailVO.getMemId()%>')">팔로우</span>
		                            <%} %>
								<%} %>
								</div>
                            </div>
                        </div>
						<!-- 건이가 원래 준 팔로우 버튼 -->
						<!-- 
                        <div class="followbtn">
                            <button type="button" class="followbtn">팔로우</button>
                        </div>
                        -->

                    </div>

                </div>

            </div>

        </div>
    </section>

    <section class="section section--clipboard">
        <div class="inner">
            <input id="myInput" value="" readonly>
            <span id="clip--meassage">현재 URL이 복사되었습니다.😁</span>
            <button onclick="clipboard()" id="clipboard">URL</button>
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

    <div class="modal-container">
        <div class="modal-bg">
            <div class="modal">

                <div class="popup-title">
                </div>

                <div class="report--list">

                    <form class="report--form" id="reportForm">

                        <!-- 게시글 ID, 혹은 댓글 작성자 ID -->
                        <input type="hidden">

                        <ul class="report--box">
                            <%for(int i = 0 ; i < reasonList.size() ; i++) {%>
                			<li>
                				<input id="<%=reasonList.get(i).getReasonId() %>" type="radio" name="reasonId" value="<%=reasonList.get(i).getReasonId() %>">
                				<label for="<%=reasonList.get(i).getReasonId() %>"><%=reasonList.get(i).getReasonCon() %></label>
                			</li>
                			<%} %>
                        </ul>

                        <div class="popupbtn-group">
                            <button type="button" class="btn-submit" onclick="submitReport()">신고하기</button>
                            <button type="button" class="btn-cancel">닫기</button>
                        </div>

                    </form>

                </div>

            </div>
        </div>
    </div>

    <!-- 헤더 고정시키는 js -->
    <script src="../../js/headerfixed.js"></script>

    <!-- 덧글 입력시에만 submit 가능하게하는 js -->
    <script src="../../js/comment.js"></script>

    <!-- 사진 zoomIn, zoomOut js -->
    <script src="../../js/zooInOut.js"></script>

    <!-- 현재 페이지 url 복사해주는 js -->
    <script src="../../js/urlclip.js"></script>
    
    <!-- 게시글 삭제 및 수정, 좋아요, 팔로우 js 이경륜 -->
    <script src="../../js/detailQna.js"></script>
    <script src="../../js/writebtn.js"></script>
</body>
</html>