<%@page import="java.util.List"%>
<%@page import="ourhouse.common.vo.CouponManageVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<CouponManageVO> cpMgList = (List<CouponManageVO>)request.getAttribute("cpMgList");
	System.out.println(cpMgList);

%>    
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>우리의집 | 쿠폰관리 </title>

    <meta name="author" content="3team">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <!-- 대표 로고 -->
    <link rel="icon" href="../../image/PNG/favicon.png">
    <link rel="apple-touch-icon" href="../../image/PNG/favicon.png">
    
    <link rel="stylesheet" href="../../css/reset.css">
    <link rel="stylesheet" href="../../css/addcoupon.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />

    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
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
                    
                    <a class="item-box" href="/admin/insertNotice.do" data-toggle="modal" data-target="#exampleModal"> 

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
                    <li class="nav-item"><a href="/admin/memberList.do">회원관리</a></li>
                    <li class="nav-item" style="color: #35C5F0;"><a href="/admin/noticeList.do">공지사항</a></li>
                    <li class="nav-item"><a href="/admin/coupon.do">쿠폰관리</a></li>
                </ul>
            </nav>

            <div class="dashboard-contents">


                <h2 class="summary__title">쿠폰 관리
                <img src="../../image/PNG/poyo.png" alt="" class="chracter">
                </h2>

                <div class="report-list">
                    
                    <div class="summary__desciption"> 쿠폰 상자</div>

                    <div class="list--wrap">
                        
                        <div class="coupon-container">


                            <!-- 쿠폰 아이템 ! 여기서 부터 반복해주세요! -->
                      <%if(cpMgList!=null){ %> 
                         	<%for(int i=0 ;  i<cpMgList.size(); i++){ %> 
                         		<%if(!cpMgList.get(i).getCpDelete().equals("Y")){ %>   
                            <div class="coupon-item">

                                <div class="coupon-stocknumber">
                                    <h2>남은재고 :</h2>

                                    <span><%=cpMgList.get(i).getStock() %></span>
                                </div>

                                <div class="coupon-title">
                                	 <%=cpMgList.get(i).getShopName() %>
                                    <span><%=cpMgList.get(i).getPrice() %>원 쿠폰</span>
                                </div>

                                <div class="coupon-whereuse">
                                    <h2>사용처</h2>

                                    <span><a href="http://<%=cpMgList.get(i).getShopUrl() %>" target="_blank"><%=cpMgList.get(i).getShopUrl() %></a></span>
                                </div>
							
                                <div class="couponbtn-group">
                                    <form action="/admin/upcoupon.do" method="get">
	                                   <input type="hidden" name="couponNo" value="<%=cpMgList.get(i).getCpMgNo() %>"> 
	                                   <button type="submit" class="btn coupon-revise" >쿠폰 수정</button>
                                    </form>
                                    
									
									<input type="hidden" name="couponNo" value="<%=cpMgList.get(i).getCpMgNo()%>">
                                    <button type="button" class="btn coupon-delete">쿠폰 삭제</button>
									                                   
                                </div>
							

                            </div>
                                <%} // 삭제 쿠폰여부 if문 %> 
                            <!-- 여기까지가 쿠폰 한세트! -->
                            <% } // for문 종료 %>
                        <%}//if문종료 %>  


                            
                            <!-- 야는 건들지 마시게! -->
                            <div class="coupon-item">


                                <div class="couponbtn-group">
                                     <form action="/admin/newcoupon.do" method="get">
                                    <button type="submit" class="btn coupon-add">쿠폰 추가</button>
                                    </form>
                                </div>
                                

                            </div>
                            <!-- 까지가 추가버튼이여라! -->

                        </div>
                        
                    </div>

                </div>

            </div>

        </div>

    </section>

    </div>

    <!-- 팝업창 -->
    <div class="modal-container">
        <div class="modal-bg">
            <div class="modal">

                <div class="popup-title">
                </div>

                <div class="popupbtn-group">
                    <button class="btn-agree"></button>
                    <button class="btn-cancel">취소</button>
                </div>
            </div>
        </div>
    </div>

    <script src="../../js/addcoupon.js"></script>
</body>
</html>












