<%@page import="java.util.ArrayList"%>
<%@page import="ourhouse.common.vo.AtchFileVO"%>
<%@page import="ourhouse.common.vo.MypagePhotoVO"%>
<%@page import="ourhouse.common.vo.PostVO"%>
<%@page import="ourhouse.common.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	MemberVO memVO = (MemberVO) request.getAttribute("memberVO");
	int followCnt = (Integer) request.getAttribute("followingCnt");
	int followerCnt = (Integer) request.getAttribute("followerCnt");
	String img = (String) request.getAttribute("img");
	int QnaReply = (Integer) request.getAttribute("QnAReplyCnt");

	List<PostVO> QnAList = (List<PostVO>) request.getAttribute("QnAList");
	List<MypagePhotoVO> mpList = (List<MypagePhotoVO>) request.getAttribute("MpPostPhoto");
	List<PostVO> postList = (List<PostVO>) request.getAttribute("postList");
	List<AtchFileVO> QatchList = (List<AtchFileVO>) request.getAttribute("QatchFile");

	List<String> hashtag = new ArrayList();

	if (QnAList != null) {
		if (QnAList.get(0).getHouseId() != null || QnAList.get(0).getHouseId() != "") {
			hashtag.add(QnAList.get(0).getHouseId());
		}
		if (QnAList.get(0).getSpaceId() != null || QnAList.get(0).getSpaceId() != "") {
			hashtag.add(QnAList.get(0).getSpaceId());
		}
		if (QnAList.get(0).getRoomId() != null || QnAList.get(0).getRoomId() != "") {
			hashtag.add(QnAList.get(0).getRoomId());
		}
		if (QnAList.get(0).getStyleId() != null || QnAList.get(0).getStyleId() != "") {
			hashtag.add(QnAList.get(0).getStyleId());
		}
		if (QnAList.get(0).getColorId() != null || QnAList.get(0).getColorId() != "") {
			hashtag.add(QnAList.get(0).getColorId());
		}
	}

	MemberVO mv = (MemberVO) session.getAttribute("session");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>우리의 집 | 회원 상세페이지</title>

<meta name="author" content="3team">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<!-- 대표 로고 -->
<link rel="icon" href="../../image/PNG/favicon.png">
<link rel="apple-touch-icon" href="../../image/PNG/favicon.png">

<link rel="stylesheet" href="../../css/reset.css">
<link rel="stylesheet" href="../../css/userpage.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
	integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA=="
	crossorigin="anonymous" />

<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script src="../../js/userFind.js"></script>
<script src="../js/mypage.js"></script>

<title>Document</title>

<script type="text/javascript">
	$(function() {
		loginTrue();//로그인 되었을때
	});
	function loginTrue() {
<%if (mv == null) {%>
	//로그인 상태가 아닐때
		$("#loginTag").show();//로그인태그나타남
		$("#logOutTag").hide();//로그아웃상태숨김
		$("#signUpTag").show();//회원가입나타남
<%} else {%>
	$("#loginTag").hide();//로그인태그 숨김
		$("#logOutTag").show();//로그아웃상태나타남
		$("#signUpTag").hide();//회원가입숨김
<%}%>
	}
</script>
</head>

<body>

	<div class="body__container">

		<!-- 기존 HEADER -->
		<header class="section">
			<div class="inner clearfix">

				<div class="menu-group float--left">

					<div class="logo">
						<a href="/main.do">우리의집</a>
					</div>

				</div>

				<div class="sign-group float--right">

					<form id="search-form" method="GET" action="/searchTotal.do">
						<span class="search-icon"></span> <input type="text" id="search"
							name="search" class="input--text" placeholder="우리의집 통합검색">
						<input type="submit" value="Submit">
					</form>

					<ul class="sub-menu">
						<li><a href="/member/signin.do" id="loginTag">로그인</a></li>
						<li><a href="/views/signin/logOut.jsp" id="logOutTag">로그아웃</a></li>
						<li><a href="/member/signup.do" id="signUpTag">회원가입</a></li>
					</ul>

					<div class="btn-group dropdown--writelist">
						<button type="button" class="btn btn-write">
							글쓰기 <i style="margin-left: 10px;" class="fas fa-angle-down"></i>
						</button>

						<ul class="dropdown-write">
							<li class="write-item" onclick="writePhoto()"><img
								src="../../image/PNG/picture.png" alt=""> <a
								class="write-info"> <span class="write--title">사진 올리기</span>
									<span class="write--description">우리집 사진을 공유해보세요.</span>
							</a></li>
							<li class="write-item" onclick="writeQna()"><img
								src="../../image/PNG/question.png" alt=""> <a
								class="write-info"> <span class="write--title">인테리어
										질문하기</span> <span class="write--description">인테리어 고수들에게
										질문해보세요.</span>
							</a></li>
						</ul>

					</div>
				</div>
			</div>
		</header>

		<!-- 모두보기, 사진, 질문과답변, 좋아요 선택하는 서브 헤더 -->
		<header class="section section-profile-subnav">
			<div class="inner">
				<ul>
					<li><a href="/member/user.do?memId=<%=memVO.getMemId()%>">모두보기</a></li>
					<li><a href="#" onclick="showMemPhoto('<%=memVO.getMemId()%>')">사진</a></li>
					<li><a href="#" onclick="showMemQnA('<%=memVO.getMemId()%>')">질문과답변</a></li>
				</ul>
		</header>

		<!-- 마이프로필 -->
		<section class="section section--profile">
			<div class="inner clearfix">

				<!-- 회원 정보 카드 -->
				<div class="procontainer float--left">

					<div class="myprofile_procontainer">
						<%
							if (!(img == null)) {
						%>
						<img src="../../image/uploads/<%=img%>" alt="">
						<%
							} else {
						%>
						<img src="../../image/uploads/default_profile.png" alt="">
						<%
							}
						%>
						<div class="profile-info">

							<div class="user-name">
								<%
									if (memVO.getMemNickname() != null) {
								%>
								<span><%=memVO.getMemNickname()%></span>
								<%
									} else {
								%>
								<span></span>
								<%
									}
								%>
							</div>

							<div class="user-follow-state">
								<div class="follow-state-info">
									<a href="/mypage/followerList.do"> <span>팔로워</span> <span
										class="state__follow"><%=followerCnt%></span>
									</a>
								</div>

								<div class="vertical-bar"></div>

								<div class="follower-state-info">
									<a href="/mypage/followingList.do"> <span>팔로잉</span> <span
										class="state__follower"><%=followCnt%></span>
									</a>
								</div>
							</div>

							<div class="user-info__btn writername">
								<span class="profile_btn"> 팔로우 </span>
							</div>

						</div>
					</div>
				</div>

				<!--  -->

				<div class="contents-container float--right">

					<div class="picturecontainer">

						<div class="picturetitle">

							<div class="title">
								사진 <span class="pictures_sum"> <%
 	if (mpList != null) {
 %> <%=mpList.size()%>
									<%
										}
									%>
								</span>
							</div>

							<div class="allView">전체보기</div>

						</div>

						<div class="pictures-container">

							<div class="box-container">
								<div class="picture-box">
									<%
										if (mpList != null) {
									%>
									<a
										href="/photo/detail.do?postNo=<%=mpList.get(0).getPostNo()%>">
										<img
										src="../image/uploads/<%=mpList.get(0).getFileFileNm()%>"
										alt="">
									</a>
									<%
										}
									%>
								</div>

								<div class="picture-title">
									<%
										if (postList != null && postList.size() > 0 && mpList.get(0).getHashtag() != null) {
									%>
									<%=mpList.get(0).getHashtag()%>
									<%
										} else {
									%>

									<%
										}
									%>
								</div>
							</div>

							<div class="box-container">
								<%
									if (postList != null && postList.size() > 2 && mpList != null && mpList.size() > 2) {
								%>
								<div class="picture-box">
									<%
										if (postList != null && postList.size() > 2 && mpList != null && mpList.size() > 2) {
									%>
									<a
										href="/photo/detail.do?postNo=<%=mpList.get(1).getPostNo()%>">
										<img alt=""
										src="../image/uploads/<%=mpList.get(1).getFileFileNm()%>">
									</a>
									<%
										}
									%>
								</div>

								<div class="picture-title">
									<%
										if (postList != null && postList.size() > 2 && mpList.get(1).getHashtag() != null
													&& mpList.size() > 2) {
									%>
									<%=mpList.get(1).getHashtag()%>
									<%
										} else {
									%>

									<%
										}
									%>
								</div>
								<%
									}
								%>
							</div>

							<div class="box-container">
								<%
									if (postList != null && postList.size() > 3 && mpList.get(2).getHashtag() != null && mpList.size() > 3) {
								%>
								<div class="picture-box">
									<%
										if (mpList != null && mpList.size() > 3) {
									%>
									<a
										href="/photo/detail.do?postNo=<%=mpList.get(2).getPostNo()%>">
										<img alt=""
										src="../image/uploads/<%=mpList.get(2).getFileFileNm()%>">
									</a>
									<%
										}
									%>
								</div>

								<div class="picture-title">
									<%
										if (postList != null && postList.size() > 3 && mpList.get(2).getHashtag() != null
													&& mpList.size() > 3) {
									%>
									<%=mpList.get(2).getHashtag()%>
									<%
										} else {
									%>

									<%
										}
									%>
								</div>
								<%
									}
								%>
							</div>

							<div class="box-container">
								<%
									if (postList != null && postList.size() > 4 && mpList.get(3).getHashtag() != null && mpList.size() > 4) {
								%>
								<div class="picture-box">
									<%
										if (mpList != null && postList.size() > 4 && mpList.size() > 4) {
									%>
									<a
										href="/photo/detail.do?postNo=<%=mpList.get(3).getPostNo()%>">
										<img alt=""
										src="../image/uploads/<%=mpList.get(3).getFileFileNm()%>">
									</a>
									<%
										}
									%>
								</div>

								<div class="picture-title">
									<%
										if (postList != null && postList.size() > 4 && mpList.get(3).getHashtag() != null
													&& mpList.size() > 4) {
									%>
									<%=mpList.get(3).getHashtag()%>
									<%
										} else {
									%>

									<%
										}
									%>
								</div>
								<%
									}
								%>
							</div>

						</div>

					</div>

					<div class="QnAcontainer">

						<%
							if (QnAList != null) {
						%>
						<div class="QnAtitle">
							<div class="title">
								질문과 답변 <span class="QnA_sum"> <%
 	if (QnAList != null) {
 %> <%=QnAList.size()%>
									<%
										}
									%>
								</span>
							</div>

							<div class="allView">전체보기</div>

						</div>

						<div class="QnA-container">
							<%
								if (QnAList != null) {
							%>
							<div class="writecontainer">

								<div class="write-box">

									<div class="write-content">
										<div class="write_title">
											<%
												if (QnAList != null) {
											%>
											<%=QnAList.get(0).getPostTitle()%>
											<%
												}
											%>
										</div>

										<div class="write_comment">
											<%
												if (QnAList != null) {
											%>
											<%=QnAList.get(0).getPostContent()%>
											<%
												}
											%>
										</div>
									</div>

									<div class="write_info">
										<div class="writer">
											<span>by</span>

											<!-- 작성자명 들어가는곳 -->
											<span> <%
 	if (QnAList != null) {
 %> <%=memVO.getMemNickname()%>
												<%
													}
												%>
											</span>
										</div>

										<!-- 작성날짜 -->
										<div class="write_date">
											<%
												if (QnAList != null) {
											%>
											<%=QnAList.get(0).getPostDate().substring(0, 10)%>
											<%
												}
											%>
										</div>

										<div class="dashed_vertical"></div>

										<div class="write_comment">
											<span>댓글</span>

											<!-- 댓글 수 -->
											<span> <%=QnaReply%>
											</span>
										</div>

										<div class="dashed_vertical"></div>

										<div class="write_lookup">
											<span>조회</span>

											<!-- 조회수 -->
											<span> <%
 	if (QnAList != null) {
 %> <%=QnAList.get(0).getPostView()%>
												<%
													}
												%>
											</span>
										</div>

										<div class="write_keyword-box">

											<%
												if (hashtag != null) {
											%>
											<%
												for (int i = 0; i < hashtag.size(); i++) {
											%>
											<%
												if (!(hashtag.get(i).equals(" "))) {
											%>
											<a class="write_keyword"
												onclick="searchTag('<%=hashtag.get(i)%>')"> <span>#</span>
												<%-- 		                                <span><%=hashtag %></span> --%>
												<span
												onclick="location='/search.do?hashtag=<%=hashtag.get(i)%>'"><%=hashtag.get(i)%></span>
											</a>
											<%
												}
											%>
											<%
												}
											%>
											<%
												}
											%>

										</div>

									</div>
								</div>

								<!-- 사진 없으면 .nonactive 클래스 추가 -->
								<%
									if (QatchList != null) {
								%>
								<div class="writeImg-box">
									<img
										src="../image/uploads/<%=QatchList.get(0).getStreFileNm()%>"
										alt="">
								</div>
								<%
									}
								%>

							</div>
							<%
								}
							%>
						</div>
						<%
							}
						%>
					</div>

				</div>

			</div>
		</section>

		<!-- FOOTER -->
		<footer class="section">
			<div class="inner clearfix">

				<ul class="site-links float--right">
					<li>@Copyright © 2020 ~ 2020 by Daedockplace, Inc All rights
						reserved</li>
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


		<script src="../js/writebtn.js"></script>
</body>
</html>