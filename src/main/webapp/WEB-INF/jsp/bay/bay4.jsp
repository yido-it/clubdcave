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
<a href="#" class="header-title header-subtitle  mt-1">시설안내</a> 
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
                        
                            <div class="card m-0" data-card-height="300"  style="background: url(/images/gallery/open_main3.jpg) no-repeat 0 0;background-size:cover">
                               
                                <div class="card-bottom">
                                 
                                </div>
                                <div class="card-overlay bg-gradient"></div>
                            </div>
                            
                            
                            <div class="card m-0" data-card-height="300"  style="background: url(/images/gallery/open_main2.jpg) no-repeat 0 0;background-size:cover">
                              
                                <div class="card-overlay bg-gradient"></div>
                            
              				  </div>
              				  
                            <div class="card m-0 " data-card-height="300"  style="background: url(/images/gallery/open_main1.jpg) no-repeat 0 0;background-size:cover">
                              
                                <div class="card-overlay bg-gradient"></div>
                            </div>
                            
                
                        
                
            </div>    
        </div>
 

        <div class="card card-clear" data-card-height="323"></div>
         
        <div class="page-content pb-3">  
            <div class="card card-full rounded-m" style="margin-bottom:-10px">
             
                <div class="divider mb-0"></div>
    
                
                <div class="content">
                <p class="mb-n1 color-highlight font-600 mb-n1"></p>
                    <h1 class="font-22">
           <span class="badge bg-blue-dark px-2 py-1 mr-1 mt-2 text-uppercase">2F</span>         
                    OPEN BAY & SHORT GAME AREA  </h1>
                    
                  
                    <p class="mb-2">
                        <span class="badge bg-green-dark px-2 py-1 mt-2 text-uppercase">VIRTUALGREEN</span>
                        <span class="badge bg-green-dark px-2 py-1 ml-2 mt-2 text-uppercase">PUTTVIEW</span>
                        <span class="badge bg-green-dark px-2 py-1 ml-2 mt-2 text-uppercase">BUNKER</span>
                        <span class="badge bg-green-dark px-2 py-1 ml-2 mt-2 text-uppercase">OPENBAY</span>
                    </p>
                      <div class="divider mt-2 mb-3"></div>
                      <p>
              명문 골프장의 라운드 전 연습공간을 실내에 구현하였습니다. 
              <br/><br/>실외 잔디 연습장과 같은 형식의 오픈타석에서 개방형 스크린 화면을 보고 샷을 할 수 있습니다. 
              이때 정확한 샷 데이터 분석을 위해 타이거 우즈가 신뢰하는 Full Swing Kit 런치모니터를 사용하실 수 있습니다. 
              <br/><br/>
              타석 곳곳에 티노5가 제공하는 최신 골프 클럽들이 비치되어 있어 신제품 시타가 가능합니다. 
최고의 기술력으로 개발된 버추얼 그린을 프라이빗하게 사용할 수 있으며, 테라스 벙커에서 벙커샷 연습도 가능합니다.<br/><br/> 
벙커장에는 티노 5에서 제공하는 여러 브랜드의 웨지가 다양한 스펙별로 비치되어 있어 본인에게 맞는 웨지 스타일을 탐색할 수 있습니다. 
주말에는 오픈 타석과 클래스존에서 주니어 그룹 골프 수업이 진행됩니다.  



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