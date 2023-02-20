<!-- 시설안내 페이지 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<jsp:include page="common/head.jsp" />
<jsp:include page="common/script.jsp" />

<body class="theme-dark">
    
<div id="preloader"><div class="spinner-border color-highlight" role="status"></div></div>
    
<div id="page">
<!--스크롤header -->
<div class="header header-fixed header-logo-app">
<a href="#" class="header-title header-subtitle mt-1">시설안내</a>
<jsp:include page="common/top.jsp" />
</div>
<!--//스크롤header -->
<!-- 좌측GNB-->
<jsp:include page="common/menu.jsp" />
<!-- //좌측GNB-->

  
    <div class="page-content header-clear">
    
        <a href="bay1" class="card card-style mt-5" data-card-height="180"  style="background: url(/images/pictures/1.jpg) no-repeat 0 0;background-size:cover">
            <div class="card-center pl-3 pr-5">
                <h1 class="color-white font-700">
                <span class="badge bg-blue-dark px-2 py-1 mr-1 mt-2 text-uppercase">B1</span>
              CLUBD CAVE GOLF</h1>
                <p class="color-white opacity-80 mb-0 mt-n2">
            클럽디청담 FULLSWING TOPGOLF SWING SUITE  
                </p>
            </div>
             <div class="card-center">
                <span class="icon icon-s fr bg-theme  me-3 rounded-xl"><i class="fa fa-arrow-right"></i></span>
            </div>
         <!--    <div class="card-bottom">
                <i class="fa fa-arrow-right color-white font-18 float-right mb-3 pr-3 opacity-80"></i>
            </div> -->
            <div class="card-overlay bg-black opacity-50"></div>
        </a>

        <a href="bay2" class="card card-style" data-card-height="180"  style="background: url(/images/gallery/rest_main.jpg) no-repeat 0 0;background-size:cover">
            <div class="card-center pl-3 pr-5">
                <h1 class="color-white font-700">
                <span class="badge bg-blue-dark px-2 py-1 mr-1 mt-2 text-uppercase">B1</span>
                CLUBD CAVE BISTRO </h1>
                <p class="color-white opacity-80 mb-0 mt-n2">
              	클럽디카브 BISTRO FOOD AND WINE 

                </p>
            </div>
           <div class="card-center">
                <span class="icon icon-s fr bg-theme  me-3 rounded-xl"><i class="fa fa-arrow-right"></i></span>
            </div>
            <div class="card-overlay bg-black opacity-50"></div>
        </a>
     
        <a href="bay3" class="card card-style" data-card-height="180" style="background: url(/images/gallery/store_main2.jpg) no-repeat 0 0;background-size:cover">
            <div class="card-center pl-3 pr-5">
                <h1 class="color-white font-700">
                <span class="badge bg-blue-dark px-2 py-1 mr-1 mt-2 text-uppercase">1F</span>
               RECEIPTION</h1>
                <p class="color-white opacity-80 mb-0 mt-n2">
      			 클럽디청담, FULLSWING, TINO5
                </p>
            </div>
            <div class="card-center">
                <span class="icon icon-s fr bg-theme  me-3 rounded-xl"><i class="fa fa-arrow-right"></i></span>
            </div>
        
            <div class="card-overlay bg-black opacity-50"></div>
        </a>
       
        <a href="bay4" class="card card-style" data-card-height="180"  style="background: url(/images/gallery/open_main2.jpg) no-repeat 0 0;background-size:cover">
            <div class="card-center pl-3 pr-5">
                <h2 class="color-white font-700 font-22">
         <span class="badge bg-blue-dark px-2 py-1 mr-1 mt-2 text-uppercase">2F</span>       
             OPEN BAY & SHORT GAME AREA</h2>
                <p class="color-white opacity-80 mb-0 mt-n2">
               	VIRTUALGREEN, PUTTVIEW, BUNKER, OPENBAY
                </p>
            </div>
            <div class="card-center">
                <span class="icon icon-s fr bg-theme  me-3 rounded-xl"><i class="fa fa-arrow-right"></i></span>
            </div>
            <div class="card-overlay bg-black opacity-50"></div>
        </a>
        <a href="bay5" class="card card-style" data-card-height="180"  style="background: url(/images/gallery/lesson_main.jpg) no-repeat 0 0;background-size:cover">
            <div class="card-center pl-3 pr-5">
                <h2 class="color-white font-700">
                <span class="badge bg-blue-dark px-2 py-1 mr-1 mt-2 text-uppercase">3F</span> 
                PRIVATE ROOM & TINO 5 </h2>
                <p class="color-white opacity-80 mb-0 mt-n2">
           	1:1 LESSON, TINO5, CLUB FITTING 
                </p>
            </div>
           <div class="card-center">
                <span class="icon icon-s fr bg-theme  me-3 rounded-xl"><i class="fa fa-arrow-right"></i></span>
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