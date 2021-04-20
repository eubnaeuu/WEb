<%@page import="ourhouse.common.vo.ColorVO"%>
<%@page import="ourhouse.common.vo.StyleVO"%>
<%@page import="ourhouse.common.vo.SpaceVO"%>
<%@page import="ourhouse.common.vo.RoomVO"%>
<%@page import="ourhouse.common.vo.HouseVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<HouseVO> houseList = (List<HouseVO>) request.getAttribute("houseList");
	List<RoomVO> roomList = (List<RoomVO>) request.getAttribute("roomList");
	List<SpaceVO> spaceList = (List<SpaceVO>) request.getAttribute("spaceList");
	List<StyleVO> styleList = (List<StyleVO>) request.getAttribute("styleList");
	List<ColorVO> colorList = (List<ColorVO>) request.getAttribute("colorList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>우리의집 | 질문글 작성</title>

    <meta name="author" content="3team">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <!-- 대표 로고 -->
    <link rel="icon" href="../../image/PNG/favicon.png">
    <link rel="apple-touch-icon" href="../../image/PNG/favicon.png">
    
    <link rel="stylesheet" href="../../css/reset.css">
    <link rel="stylesheet" href="../../css/writeQna.css">
    
    
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
    <div class="inner clearfix">

        <div class="menu-group float--left">
            
            <div class="logo">
                <img src="../../image/PNG/favicon.png" alt="">
                <a href="/main.do">우리의집</a>
            </div>

        </div>

    </div>
</header>

<section class="section section-write">

    <div class="inner">

        <h2 class="summary__title">💛 질문 작성 💛</h2>

        <form class="writeform" id="writeform" enctype="multipart/form-data">

            <div class="title--input">
                <input type="text" class="input--text" id="title" placeholder="제목을 입력해주세요.">
            </div>

            <div class="select-container">
                
                <div class="selectbox select1"> 
                    <label for="houseList">형태</label> 
                        <select id="houseId" onchange="" name="houseId">
                            <option disabled selected>선택하세요</option> 
		                    <%for(int i = 0 ; i < houseList.size() ; i++) { %>
		                    <option value="<%=houseList.get(i).getHouseId() %>"><%=houseList.get(i).getHouseName() %></option>
		                	<%} %> 
                        </select> 
                </div>
                
                <div class="selectbox select2"> 
                    <label for="roomList">공간</label> 
                        <select id="roomId" onchange="" name="roomId">
                            <option disabled selected>선택하세요</option> 
		                    <%for(int i = 0 ; i < roomList.size() ; i++) { %>
		                    <option value="<%=roomList.get(i).getRoomId() %>"><%=roomList.get(i).getRoomName() %></option>
		                	<%} %> 
                        </select> 
                </div>
                
                <div class="selectbox select3"> 
                    <label for="spaceList">평수</label> 
                        <select id="spaceId" onchange="" name="spaceId">
                        	<option disabled selected>선택하세요</option> 
		                    <%for(int i = 0 ; i < spaceList.size() ; i++) { %>
		                    <option value="<%=spaceList.get(i).getSpaceId() %>"><%=spaceList.get(i).getSpaceName() %></option>
		                	<%} %> 
                        </select> 
                </div>

                <div class="selectbox select4"> 
                    <label for="styleList">스타일</label> 
                        <select id="styleId" onchange="" name="styleId">
                        	<option disabled selected>선택하세요</option> 
		                    <%for(int i = 0 ; i < styleList.size() ; i++) { %>
		                    <option value="<%=styleList.get(i).getStyleId() %>"><%=styleList.get(i).getStyleName() %></option>
		                	<%} %> 
                        </select> 
                </div>

                <div class="selectbox select5"> 
                    <label for="colorList">컬러</label> 
                        <select id="colorId" onchange="" name="colorId">
                        	<option disabled selected>선택하세요</option> 
		                    <%for(int i = 0 ; i < colorList.size() ; i++) { %>
		                    <option value="<%=colorList.get(i).getColorId() %>"><%=colorList.get(i).getColorName() %></option>
		                	<%} %> 
                        </select> 
                </div>
            </div>

            <!-- 반복할 폼 -->
            <div class="selectform1" title="write-form" style="position: relative;">

                <div class="write-contents">

                    <div class="img-container">
                        <div class="image-upload-wrap">
                            <input class="file-upload-input" type='file' onchange="readURL(this);" accept="image/*" name="atchFile1"/>
                            
                            <div class="drag-text">
                                <h3>이미지 업로드</h3>   
                            </div>
                        </div>
                
                        <div class="file-upload-content">
                
                            <div class="imgBox">
                                <img class="file-upload-image" src="#" alt="your image" />
                
                                <button type="button" onclick="removeUpload(this)" class="remove-image"><i class="fas fa-times"></i></button>
                            </div>    
                
                        </div>
                    </div>

                    <textarea class="input--text comment" name="fileCn1" id="" cols="30" rows="10"></textarea>

                </div>
            
                <p class="number" onclick="">1</p>

            </div>
                
            <button type="button" class="btn addform" onclick="writeAdd(this)">글 추가하기</button>

            <div class="formbtn-group">
                <input type="hidden" name="fileCount" value="">
                <input type="submit" class="btn complete" value="작성완료" id="btnSubmit">
                <input type="button" class="btn previous" value="이전으로">

            </div>

        </form>

    </div>

</section>

</div>

<div class="modal-container">
    <div class="modal-bg">
        <div class="modal">

            <div class="popup-title">
            </div>

            <div class="popupbtn-group">
                <button class="btn-cancel">확인</button>
            </div>
        </div>
    </div>
</div>

    <script src="../../js/writeQna.js"></script>
    <script src="../../js/myUtil.js"></script>
</body>
</html>












