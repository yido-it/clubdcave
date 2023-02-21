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
                <i class="fas fa fa-arrow-left font-18 mt-2"></i>
            </a>
        </h1> 
        <a href="#" class="page-title-icon shadow-xl bg-theme color-theme" data-menu="menu-main"><i class="fa fa-bars font-20 mt-2"></i></a>
    </div>

        
         
        <div class="card card-fixed" data-card-height="300">
          
                        <div class="single-slider  slider-has-arrows  owl-carousel owl-dots-over">
                            <div class="card m-0" data-card-height="300"  style="background: url(/images/gallery/lesson_main.jpg) no-repeat 0 0;background-size:cover">
                              
                                <div class="card-overlay bg-gradient"></div>
                            </div>
                            
                
                            <div class="card m-0" data-card-height="300"  style="background: url(/images/gallery/lesson_main5.jpg) no-repeat 0 0;background-size:cover">
                               
                                <div class="card-bottom">
                                 
                                </div>
                                <div class="card-overlay bg-gradient"></div>
                            </div>
                
                            <div class="card m-0" data-card-height="300"   style="background: url(/images/gallery/lesson_main4.jpg) no-repeat 0 0;background-size:cover">
                              
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
                    <h1 class="font-24">
                    <span class="badge bg-blue-dark px-2 py-1 mr-1 mt-2 text-uppercase">3F</span>
                    PRIVATE ROOM & TINO 5              
                    </h1>
                    
                  
                    <p class="mb-2">
                        <span class="badge bg-green-dark px-2 py-1 mt-2 text-uppercase">1:1LESSON</span>
                        <span class="badge bg-green-dark px-2 py-1 ml-2 mt-2 text-uppercase">TINO5</span>
                        <span class="badge bg-green-dark px-2 py-1 ml-2 mt-2 text-uppercase">CLUBFITTING</span>
                    </p>
                      <div class="divider mt-2 mb-3"></div>
                      <p>
    타이거 우즈, 존람, 조던 스피스 등 세계 정상급 선수들이 사용하는
     ‘Full Swing PRO’가 설치된 프라이빗 룸에서 최고의 프로들에게 체계적인 레슨을 받으실 수 있습니다.<br/><br/>
TINO 5의 최신 트렌드를 반영한 골프 용품 쇼핑과 클럽 피팅을 받으실 수 있습니다. 





                    </p>
                    
                    <div class="divider mt-2 mb-3"></div>
                    
                    <!-- <p class="mb-n1 color-highlight font-600 mb-n1">Time</p> -->
                    <h2>영업시간</h2>
                    <p>
                   8:00~22:00(연중무휴)
                    </p> 
                    
                    <a href="javascript:alert('준비중입니다.');"  class="btn btn-border btn-md btn-full mb-3 rounded-sm text-uppercase font-900 border-blue-dark color-blue-dark bg-theme">
					레슨문의
					</a>
					
					<a href="javascript:alert('준비중입니다.');" class="btn btn-border btn-md btn-full mb-3 rounded-sm text-uppercase font-900 border-green-dark color-green-dark bg-theme">
					클럽피팅문의
					</a>
					
					<!-- <a href="tel:+1 234 567 890" class="btn btn-border btn-md btn-full mb-3 rounded-sm text-uppercase font-900 border-green-dark color-green-dark bg-theme">
					클럽피팅문의
					</a> -->
                         <div class="divider mt-2 mb-5"></div>

                </div>
           

       
            </div>
        </div>
        
        
</div>   
</div>  

<!-- 하단바 -->
<jsp:include page="../common/footerBar.jsp" />

</body>

</html>