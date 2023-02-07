<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />

<body class="theme-dark">
    
<div id="preloader"><div class="spinner-border color-highlight" role="status"></div></div>
    
<div id="page">

<!-- 좌측GNB-->
<jsp:include page="../common/menu.jsp" />
<!-- //좌측GNB-->

<div class="page-content">
	
	
    <div class="page-title page-title-fixed" style="opacity: 1; z-index: 99;">
        <h1 style="font-size:14px">    
            <a href="#" data-back-button class="page-title-icon shadow-xl bg-theme color-theme show-on-theme-dark" >
                <i class="fas fa fa-arrow-left"></i>
            </a>
        </h1> 
        
        <a href="#" class="page-title-icon shadow-xl bg-theme color-theme" data-menu="menu-main"><i class="fa fa-bars"></i></a>
    </div>

        
         
        <div class="card card-fixed" data-card-height="300">
          
                        <div class="single-slider  slider-has-arrows  owl-carousel owl-dots-over">
                            <div class="card m-0" data-card-height="300"  style="background: url(/images/gallery/lesson_main1.jpg) no-repeat 0 0;background-size:cover">
                              
                                <div class="card-overlay bg-gradient"></div>
                            </div>
                            
                
                            <div class="card m-0" data-card-height="300"  style="background: url(/images/gallery/lesson_main2.jpg) no-repeat 0 0;background-size:cover">
                               
                                <div class="card-bottom">
                                 
                                </div>
                                <div class="card-overlay bg-gradient"></div>
                            </div>
                
                            <div class="card m-0" data-card-height="300"   style="background: url(/images/gallery/lesson_main3.jpg) no-repeat 0 0;background-size:cover">
                              
                                <div class="card-overlay bg-gradient"></div>
                            
                </div>
            </div>    
        </div>
 

        <div class="card card-clear" data-card-height="323"></div>
         
        <div class="page-content pb-3">  
            <div class="card card-full rounded-m" style="margin-bottom:-10px">
             
                <div class="divider mb-0"></div>
    
                
                <div class="content">
                <p class="mb-n1 color-highlight font-600 mb-n1">프라이빗 레슨룸 & TINO5</p>
                    <h1 class="font-26">
                    <span class="badge bg-blue-dark px-2 py-1 mr-1 mt-2 text-uppercase">3F</span>
                    Lesson & TINO5                    
                    </h1>
                    
                  
                    <p class="mb-2">
                        <span class="badge bg-green-dark px-2 py-1 mt-2 text-uppercase">개인레슨</span>
                        <span class="badge bg-green-dark px-2 py-1 ml-2 mt-2 text-uppercase">TINO5</span>
                    </p>
                      <div class="divider mt-2 mb-3"></div>
                      <p>
     클럽디 청담 소속 국내 최고의 프로골퍼들에게 1:1 개인레슨을 받을 수 있는 4개의 프라이빗 레슨룸이 설치되어 있습니다. 
     <br/><br/>레슨룸에는 타이거 우즈와 존람, 조던 스피스의 집에 설치되어 있는 Full Swing Pro를 사용하여 섬세하고 정밀한 스윙 분석과 레슨을 받으실 수 있습니다.<br/><br/>
Tino 5가 입점되어 있어 최신 트렌드의 골프용품 쇼핑이 가능합니다. Tino 5 소속 클럽피터에게 예약제로 클럽 피팅이 가능하여 본인에게 가장 잘 맞는 클럽을 선택하고 구매하실 수 있습니다.  




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