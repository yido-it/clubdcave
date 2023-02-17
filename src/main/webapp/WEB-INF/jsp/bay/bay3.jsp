<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />

<body class="theme-dark">
    
<div id="preloader"><div class="spinner-border color-highlight" role="status"></div></div>
    
<div id="page">

<!--스크롤내리면 보이는 Header-->
<div class="header header-auto-show header-fixed header-logo-app">
<a href="#" class="header-title header-subtitle mt-1">시설안내</a> 
<a href="#" class="header-icon header-icon-1 back-button mt-1" id="topGoBack"><i class="fa fa-arrow-left font-18"></i></a>
<a href="#" data-menu="menu-main" class="header-icon header-icon-2 font-20 mt-1"><i class="fas fa-bars"></i></a> 
</div>

<!-- 좌측GNB-->
<jsp:include page="../common/menu.jsp" />
<!-- //좌측GNB-->

<div class="page-content">
	
	
    <div class="page-title page-title-fixed" style="opacity: 1; z-index: 99;">
        <h1 style="font-size:14px">    
            <a href="#" data-back-button class="page-title-icon shadow-xl bg-theme color-theme show-on-theme-dark" >
                <i class="fas fa fa-arrow-left font-18"></i>
            </a>
        </h1> 
        
        <a href="#" class="page-title-icon shadow-xl bg-theme color-theme" data-menu="menu-main"><i class="fa fa-bars"></i></a>
    </div>

        
         
        <div class="card card-fixed" data-card-height="300">
          
                        <div class="single-slider  slider-has-arrows  owl-carousel owl-dots-over">
                            <div class="card m-0 " data-card-height="300"   style="background: url(/images/gallery/store_main2.jpg) no-repeat 0 0;background-size:cover">
                              
                                <div class="card-overlay bg-gradient"></div>
                            </div>
                            
                
                            <div class="card m-0 bg-1" data-card-height="300"  style="background: url(/images/gallery/store_main1.jpg) no-repeat 0 0;background-size:cover">
                               
                                <div class="card-bottom">
                                 
                                </div>
                                <div class="card-overlay bg-gradient"></div>
                            </div>
                
                      
            </div>    
        </div>
 

        <div class="card card-clear" data-card-height="323"></div>
         
        <div class="page-content pb-3">  
            <div class="card card-full rounded-m" style="margin-bottom:-10px">
             
                <div class="divider mb-0"></div>
    
                
                <div class="content">
                <p class="mb-n1 color-highlight font-600 mb-n1">리셉션</p>
                    <h1 class="font-26">
                    <span class="badge bg-blue-dark px-2 py-1 mr-1 mt-2 text-uppercase">1F</span>
                  RECEIPTION                   
                    </h1>
                    
                  
                    <p class="mb-2">
                        <span class="badge bg-green-dark px-2 py-1 mt-2 text-uppercase">Fullswing</span>
                        <span class="badge bg-green-dark px-2 py-1 ml-2 mt-2 text-uppercase">해시태그</span>
                    </p>
                      <div class="divider mt-2 mb-3"></div>
                      <p>
                클럽디 청담 안내 및 간단한 골프 용품들과 Full Swing Kit를 구매하실 수 있는 공간으로 구성되었습니다. 
                <br/><br/>시즌별로 최신 트렌드를 반영한 골프관련 브랜드들의 팝업 전시가 준비되어 있습니다. 

                    </p>
                    
                    <div class="divider mt-2 mb-3"></div>
                    
                    <p class="mb-n1 color-highlight font-600 mb-n1">Time</p>
                    <h2>영업시간</h2>
                    <p>
                        월 10:00 ~ 22:00 <br/>
                        화 10:00 ~ 22:00 <br/>
                        수 10:00 ~ 22:00 <br/>
                        목 10:00 ~ 22:00 <br/>
                        금 10:00 ~ 22:00 <br/>
                    </p> 
                         <div class="divider mt-2 mb-3"></div>

                </div>
            
            </div>
        </div>
        
        
</div>   
</div>   

<!-- 하단바 -->
<jsp:include page="../common/footerBar.jsp" />

</body>

</html>