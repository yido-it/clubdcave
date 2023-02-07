<!-- 시설안내 페이지 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="nowDt"><fmt:formatDate value="${now}" pattern="yyyyMMdd" /></c:set> 

<jsp:include page="common/head.jsp" />
<jsp:include page="common/script.jsp" />

<body class="theme-dark">
    
<div id="preloader"><div class="spinner-border color-highlight" role="status"></div></div>
    
<div id="page">

<div class="header header-fixed header-logo-app">
<a href="javascript:history.back(-1)" class="header-title header-subtitle">시설안내</a>
<jsp:include page="common/top.jsp" />
</div>

<!-- 좌측GNB-->
<jsp:include page="common/menu.jsp" />
<!-- //좌측GNB-->

  
    <div class="page-content header-clear">
    
        <div class="content  mb-2">
        <!--        <div style="width:100%;text-align:center">
	                <img src="../images/logo_white.png" class="text-center" style="width:130px;">
	         </div> -->
	   <!--       <p>
	         클럽디 청담은 총 4개 층으로 운영되고 있습니다. 
	     FULL SWING, 골프편집샵 TINO 5, FULL SWING Pro 스크린 골프와
	     클럽디 카브 골프 & 비스트로를 경험하실 수 있습니다. 
		또한 KPGA, KLPGA 프로들의 골프레슨과 이벤트에 참여할 수 있습니다.
	         
	         </p> -->
        </div>
        
        <a href="bay1" class="card card-style mt-5" data-card-height="180"  style="background: url(../images/gallery/cave_main1.jpg) no-repeat 0 0;background-size:cover">
            <div class="card-center pl-3 pr-5">
                <h1 class="color-white font-700">
                <span class="badge bg-blue-dark px-2 py-1 mr-1 mt-2 text-uppercase">B1</span>
                CLUBD CAVE</h1>
                <p class="color-white opacity-80 mb-0 mt-n2">
            FULL SWING Pro가 설치되어 있어 PGA TOUR 메이저 대회가 열리는 골프코스 라운드 뿐만 아니라 
                  다양한 스포츠 게임도 즐길 수 있습니다. 

                </p>
            </div>
            <div class="card-bottom">
                <i class="fa fa-arrow-right color-white font-18 float-right mb-3 pr-3 opacity-80"></i>
            </div>
            <div class="card-overlay bg-black opacity-50"></div>
        </a>

        <a href="bay2" class="card card-style" data-card-height="180"  style="background: url(../images/gallery/rest_main2.jpg) no-repeat 0 0;background-size:cover">
            <div class="card-center pl-3 pr-5">
                <h1 class="color-white font-700">
                <span class="badge bg-blue-dark px-2 py-1 mr-1 mt-2 text-uppercase">B1</span>
                CAVE Restaurant </h1>
                <p class="color-white opacity-80 mb-0 mt-n2">
                미슐랭 셰프의 섬세한 요리들을 맛볼 수 있는 어반골프클럽 비스트로 입니다. 

                </p>
            </div>
            <div class="card-bottom">
                <i class="fa fa-arrow-right color-white font-18 float-right mb-3 pr-3 opacity-80"></i>
            </div>
            <div class="card-overlay bg-black opacity-50"></div>
        </a>
     
        <a href="bay3" class="card card-style" data-card-height="180" style="background: url(../images/gallery/store_main2.jpg) no-repeat 0 0;background-size:cover">
            <div class="card-center pl-3 pr-5">
                <h1 class="color-white font-700">
                <span class="badge bg-blue-dark px-2 py-1 mr-1 mt-2 text-uppercase">1F</span>
                Pop-up store </h1>
                <p class="color-white opacity-80 mb-0 mt-n2">
          	시즌별로 최신 트렌드를 반영한 골프관련 브랜드들의 팝업 전시가 준비되어 있습니다.

                </p>
            </div>
            <div class="card-bottom">
                <i class="fa fa-arrow-right color-white font-18 float-right mb-3 pr-3 opacity-80"></i>
            </div>
            <div class="card-overlay bg-black opacity-50"></div>
        </a>
       
        <a href="bay4" class="card card-style" data-card-height="180"  style="background: url(../images/gallery/1.jpg) no-repeat 0 0;background-size:cover">
            <div class="card-center pl-3 pr-5">
                <h1 class="color-white font-700 font-22">
         <span class="badge bg-blue-dark px-2 py-1 mr-1 mt-2 text-uppercase">2F</span>       
                오픈타석</h1>
                <p class="color-white opacity-80 mb-0 mt-n2">
                 명문 골프장의 라운드 전 연습공간을 실내에 구현하였습니다.
                 실외 잔디 연습장과 같은 형식의 오픈타석에서 개방형 스크린 화면을 보고 샷을 할 수 있습니다.
                </p>
            </div>
            <div class="card-bottom">
                <i class="fa fa-arrow-right color-white font-18 float-right mb-3 pr-3 opacity-80"></i>
            </div>
            <div class="card-overlay bg-black opacity-50"></div>
        </a>
        <a href="bay5" class="card card-style" data-card-height="180"  style="background: url(../images/gallery/lesson_main1.jpg) no-repeat 0 0;background-size:cover">
            <div class="card-center pl-3 pr-5">
                <h1 class="color-white font-700">
                <span class="badge bg-blue-dark px-2 py-1 mr-1 mt-2 text-uppercase">3F</span> Lesson & TINO 5 </h1>
                <p class="color-white opacity-80 mb-0 mt-n2">
                 국내 최고의 프로골퍼들에게 1:1 개인레슨을 받을 수 있는  프라이빗 레슨룸이 설치되어 있습니다. 
                </p>
            </div>
            <div class="card-bottom">
                <i class="fa fa-arrow-right color-white font-18 float-right mb-3 pr-3 opacity-80"></i>
            </div>
            <div class="card-overlay bg-black opacity-60"></div>
        </a>
     
    </div>  
</div>   
<div class="mb-10"></div>

<!-- 하단바 -->
<jsp:include page="common/footerBar.jsp" />

</body>

</html>