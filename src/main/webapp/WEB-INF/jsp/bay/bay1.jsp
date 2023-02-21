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
                             <div class="card m-0 bg-1" data-card-height="300"  >
                              
                                <div class="card-overlay bg-gradient"></div>
                            
           				     </div>
                            <div class="card m-0 bg-18" data-card-height="300"  >
                              
                                <div class="card-overlay bg-gradient"></div>
                            </div>
                            
                
                            <div class="card m-0 bg-28" data-card-height="300" style="background: url(/images/gallery/cave_main2.jpg) no-repeat 0 0;background-size:cover">
                               
                                <div class="card-bottom">
                                 
                                </div>
                                <div class="card-overlay bg-gradient"></div>
                            </div>
                              <div class="card m-0 bg-28" data-card-height="300" style="background: url(/images/gallery/cave_main3.jpg) no-repeat 0 0;background-size:cover">
                               
                                <div class="card-bottom">
                                 
                                </div>
                                <div class="card-overlay bg-gradient"></div>
                            </div>
                
                       
            </div>    
        </div>
 

        <div class="card card-clear" data-card-height="323"></div>
         
        <div class="page-content pb-3">  
            <div class="card card-full rounded-m" style="margin-bottom:-10px;">
             
                <div class="divider mb-0"></div>
    
                
                <div class="content">
                <p class="mb-n1 color-highlight font-600 mb-n1">클럽디 카브 골프</p>
                    <h1 class="font-26">
                    <span class="badge bg-blue-dark px-2 py-1 mr-1 mt-2 text-uppercase">B1</span>
                   CLUBD CAVE GOLF
                    </h1>
                                      
                  
                    <p class="mb-2">
                        <span class="badge bg-green-dark px-2 py-1 mt-2 text-uppercase">클럽디청담</span>
                        <span class="badge bg-green-dark px-2 py-1 ml-2 mt-2 text-uppercase">FULLSWING</span>
                        <span class="badge bg-green-dark px-2 py-1 ml-2 mt-2 text-uppercase">TOPGOLF SWINGSUITE</span>
                    </p>
                      <div class="divider mt-2 mb-3"></div>
                      <p>
					                  골프를 사랑하는 사람들의 아지트 <br/><br/>
					타이거 우즈가 공동개발한 골프 시뮬레이터 ‘FULL SWING PRO’와 함께 세계 명문 골프장에서 VIRTUAL 라운드를 즐기며, 
					미국 ‘TOP GOLF SWING SUITE’의 다양한 게임을 실내에서 즐길 수 있습니다. 
					<br/><br/>클럽디 카브의 시그니처 요리와 와인을 곁들여 골프&미식 라이프를 함께 즐기세요! 
                    </p>
                    
                    <div class="divider mt-2 mb-3"></div>
                    
                      <h2>영업시간</h2>
                    <p>
                    	연중무휴<br/>
                        8:00 ~ 17:00(주간) <br/>
                       17:00 ~ 24:00 (야간)
					<br/> 
                    </p> 
                
                </div> 
            </div>
        </div>
        <div class="mb-5"></div>
        
        
</div>   
</div>   

<!-- 하단바 -->
<jsp:include page="../common/footerBar.jsp" />

</body>

</html>