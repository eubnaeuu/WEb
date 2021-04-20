<%@page import="ourhouse.common.vo.AdminMemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<AdminMemberVO> adminMemList = (List<AdminMemberVO>)request.getAttribute("adminMemList");

%>    
    
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>우리의집 | 회원관리</title>

    <meta name="author" content="3team">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <!-- 대표 로고 -->
    <link rel="icon" href="../../image/PNG/favicon.png">
    <link rel="apple-touch-icon" href="../../image/PNG/favicon.png">
    
    <link rel="stylesheet" href="../../css/reset.css">
    <link rel="stylesheet" href="../../css/memberManage.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />

    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <script src="../../js/memberManage.js"></script>
</head>
<body>
    <!-- body 태그에 직접적으로 css는 부여하지 않는다. 컨테이너를 따로 지정하자. -->
    <!-- 언더바 두개를 사용하는 이유 -->
<div class="body__container"> <!-- body의 일부분인 컨테이너임을 의미 -->

    <!-- HEADER -->
    <header class="section">
        <!-- 콘텐츠가 들어가는 inner영역 -->

        <div class="header--background"></div>

            <div class="inner clearfix">

                <div class="menu-group float--left">
                    
                    <div class="logo">
                        <a href="/admin/reportList.do">우리의집</a>
                    </div>

                </div>

                <div class="item-nav float--right">

                    <a class="item-box" href="/main.do"> 

                        <div class="item-picture">
                            <img src="../../image/PNG/picture.png" alt="">
                        </div>
                        
                        <div class="item-title">
                            <h3>사진게시판으로</h3>
                            <p>회원들이 올린 사진을 보러갑시다</p>
                        </div>
                        
                    </a>

                    <a class="item-box" href="/qna/list.do"> 

                        <div class="item-picture">
                            <img src="../../image/PNG/question.png" alt="">
                        </div>
                        
                        <div class="item-title">
                            <h3>질문게시판으로</h3>
                            <p>회원들의 질문글을 보러갑시다</p>
                        </div>
                        
                    </a>
                    
                    <a class="item-box" href="/admin/insertNotice.do"> 

                        <div class="item-picture">
                            <img src="../../image/PNG/write.png" alt="">
                        </div>
                        
                        <div class="item-title">
                            <h3>공지게시글 작성</h3>
                            <p>자 이제 한번 공지해봅시다</p>
                        </div>
                        
                    </a>

                </div>
            </div>
    </header>

    <section class="section section-dashboard">

        <div class="inner">

            <nav class="nav-container">
                <ul class="nav-list">
                    <li class="nav-item"><a href="/admin/reportList.do">신고내역</a></li>
                    <li class="nav-item" style="color: #35C5F0;"><a href="/admin/memberList.do">회원관리</a></li>
                    <li class="nav-item"><a href="/admin/noticeList.do">공지사항</a></li>
                    <li class="nav-item"><a href="/admin/coupon.do">쿠폰관리</a></li>
                </ul>
            </nav>

            <div class="dashboard-contents">


                <h2 class="summary__title">회원 관리
                <img src="../../image/SVG/SVG/poyo.svg" alt="" class="chracter">
                </h2>

                <div class="report-list">
                    
                    <div class="summary__desciption"> 회원 조회</div>
                
                    <ul class="tr">
                        <li>회원 이메일</li>
                        <li>회원 ID</li>
                        <li>회원 포인트</li>
                        <li>글 신고 횟수</li>
                        <li>댓글 신고 횟수</li>
                        <li>별명</li>
                        <li>계정 활성화 여부</li>
                    </ul>

                    <div class="list--wrap">
        			<% for(int i=0 ; i < adminMemList.size() ; i++){ %>
        			<%
	        			String memEmail = adminMemList.get(i).getMemEmail();
	        			String memId = adminMemList.get(i).getMemId();
	        			int memPoint = adminMemList.get(i).getMemPoint();
	        			int pReportCount = adminMemList.get(i).getpReportCount();
	        			int rReportCount = adminMemList.get(i).getrReportCount();
	        			String memNickname = adminMemList.get(i).getMemNickname();
	        			String memDelete = adminMemList.get(i).getMemDelete();
	        			String btnClass = "status--active";
	        			if(memDelete.equals("N")){
	        				memDelete="활성화";
	        			}else{
	        				memDelete="비활성화";
	        				btnClass = "status--nonactive";
	        			}
        			%>
                        <ul class="tr--notice" onclick=""> 
                            <li><a href="/member/user.do?memId=<%=memId%>"><%=memEmail %></a></li>
                            <li><%=memId %></li>
                            <li><%=memPoint %></li>
                            <li><%=pReportCount %></li>
                            <li><%=rReportCount %></li>
                            <li><%=memNickname %></li>
                            <li>
                                <form id="fm5" action="">
                                	<input type="button" onclick="changeStatus(this, '<%=adminMemList.get(i).getMemId() %>')" id="deleChk"
                                	 class="status--activation <%=btnClass %>" value="<%=memDelete%>"><!-- submit 혹은 button -->
                                </form>
                            </li>
                        </ul>
        
					<%} %>
        
                    </div>

                </div>

            </div>

        </div>

    </section>

    </div>

</body>
</html>












