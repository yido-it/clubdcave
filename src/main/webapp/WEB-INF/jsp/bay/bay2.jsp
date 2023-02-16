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
        
        <a href="#" class="page-title-icon shadow-xl bg-theme color-theme" data-menu="menu-main"><i class="fa fa-bars font-18"></i></a>
    </div>

        
         
        <div class="card card-fixed" data-card-height="300">
          
                        <div class="single-slider  slider-has-arrows  owl-carousel owl-dots-over">
                            <div class="card m-0 " data-card-height="300"   style="background: url(/images/gallery/rest_main2.jpg) no-repeat 0 0;background-size:cover">
                              
                                <div class="card-overlay bg-gradient"></div>
                            </div>
                            
                              <div class="card m-0 " data-card-height="300"   style="background: url(/images/gallery/rest_main3.jpg) no-repeat 0 0;background-size:cover">
                              
                                <div class="card-overlay bg-gradient"></div>
                            </div>
                
                            <div class="card m-0 " data-card-height="300"  style="background: url(/images/gallery/rest_main1.jpg) no-repeat 0 0;background-size:cover">
                               
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
                <p class="mb-n1 color-highlight font-600 mb-n1">카브 레스토랑</p>
                    <h1 class="font-26">
                    <span class="badge bg-blue-dark px-2 py-1 mr-1 mt-2 text-uppercase">B1</span>
                    CAVE Restaurant </h1>
                  
                    <p class="mb-2">
                        <span class="badge bg-green-dark px-2 py-1 mt-2 text-uppercase">CAVE</span>
                        <span class="badge bg-green-dark px-2 py-1 ml-2 mt-2 text-uppercase">해시태그</span>
                    </p>
                        <div class="divider mt-1 mb-1"></div>
                     <p>
           PREMIUM GOLF & BISTRO '클럽디카브'
           <br/>   <br/>

프리미엄 골프 시뮬레이터 ‘FULL SWING'의 플래그십 센터이자
프라이빗 골프 아카데미를 운영하는 '클럽디청담'내 위치한 '클럽디카브'는
세계 명문 골프장이 펼쳐진 스크린에서 생생한 라운드를 즐기며  
전문 셰프의 섬세한 요리를 맛볼 수 있는 와인 비스트로입니다.  

               
                    </p>
                    

					
                    <div class="divider mt-2 mb-3"></div>


<!-- 메뉴 -->
                    <p class="font-600 mb-n1 color-highlight">MENU</p>
                    <h2 class="">대표메뉴</h1>
                        <p>
                           PREMIUM GOLF & BISTRO '클럽디카브'
                        </p>
                        <div class="double-slider owl-carousel owl-no-dots mb-0">
                            <div class="item" style="">
                                <div class="card card-style mx-0 bg-pro1" data-card-height="180" style="background: url(../images/gallery/food1.jpg) no-repeat 0 0;background-size:cover">
                                    <div class="card-bottom mb-2">
                                        <h5 class="color-white font-15 px-2">Salad & Sandwich</h5> 
                                    </div>
                                    <div class="card-overlay bg-gradient opacity-30"></div>
                                    <div class="card-overlay bg-gradient"></div>
                                </div>
                            </div>
                            <div class="item" style="">
                                <div class="card card-style mx-0 bg-pro1" data-card-height="180" style="background: url(../images/gallery/food2.jpg) no-repeat 0 0;background-size:cover">
                                    <div class="card-bottom mb-2">
                                        <h5 class="color-white font-15 px-2">Fried finger food </h5>
                                    </div>
                                    <div class="card-overlay bg-gradient opacity-30"></div>
                                    <div class="card-overlay bg-gradient"></div>
                                </div>
                            </div>
                            <div class="item" style="">
                                <div class="card card-style mx-0 bg-pro1" data-card-height="180" style="background: url(../images/gallery/food3.jpg) no-repeat 0 0;background-size:cover">
                                    <div class="card-bottom mb-2">
                                        <h5 class="color-white font-15 px-2">Pasta & Rice </h5>
                                       
                                    </div>
                                    <div class="card-overlay bg-gradient opacity-30"></div>
                                    <div class="card-overlay bg-gradient"></div>
                                </div>
                            </div>
                                 <div class="item" style="">
                                <div class="card card-style mx-0 bg-pro1" data-card-height="180" style="background: url(../images/gallery/food4.jpg) no-repeat 0 0;background-size:cover">
                                    <div class="card-bottom mb-2">
                                        <h5 class="color-white font-15 px-2">Tapas </h5>
                                       
                                    </div>
                                    <div class="card-overlay bg-gradient opacity-30"></div>
                                    <div class="card-overlay bg-gradient"></div>
                                </div>
                            </div>
                                 <div class="item" style="">
                                <div class="card card-style mx-0 bg-pro1" data-card-height="180" style="background: url(../images/gallery/food5.jpg) no-repeat 0 0;background-size:cover">
                                    <div class="card-bottom mb-2">
                                        <h5 class="color-white font-15 px-2">Platter  </h5>
                                       
                                    </div>
                                    <div class="card-overlay bg-gradient opacity-30"></div>
                                    <div class="card-overlay bg-gradient"></div>
                                </div>
                            </div>
                                 <div class="item" style="">
                                <div class="card card-style mx-0 bg-pro1" data-card-height="180" style="background: url(../images/gallery/food6.jpg) no-repeat 0 0;background-size:cover">
                                    <div class="card-bottom mb-2">
                                        <h5 class="color-white font-15 px-2">Dessert  </h5>
                                       
                                    </div>
                                    <div class="card-overlay bg-gradient opacity-30"></div>
                                    <div class="card-overlay bg-gradient"></div>
                                </div>
                            </div>
                                 <div class="item" style="">
                                <div class="card card-style mx-0 bg-pro1" data-card-height="180" style="background: url(../images/gallery/food7.jpg) no-repeat 0 0;background-size:cover">
                                    <div class="card-bottom mb-2">
                                        <h5 class="color-white font-15 px-2">Wine</h5>
                                       
                                    </div>
                                    <div class="card-overlay bg-gradient opacity-30"></div>
                                    <div class="card-overlay bg-gradient"></div>
                                </div>
                            </div>
                                 <div class="item" style="">
                                <div class="card card-style mx-0 bg-pro1" data-card-height="180" style="background: url(../images/gallery/food8.jpg) no-repeat 0 0;background-size:cover">
                                    <div class="card-bottom mb-2">
                                        <h5 class="color-white font-15 px-2">Alcoholic Drink & beverage </h5>
                                       
                                    </div>
                                    <div class="card-overlay bg-gradient opacity-30"></div>
                                    <div class="card-overlay bg-gradient"></div>
                                </div>
                            </div>
                        </div>
               
               
            <div class="divider mt-2 mb-3"></div>
                    
                    <p class="mb-n1 color-highlight font-600 mb-n1">Time</p>
                    <h2>영업시간</h2>
                    <p>
                        연중무휴 10:00 ~ 22:00 <br/>
                        *캐치테이블에서 예약가능합니다. 
                    </p> 
                    
                    <a href="tel:+1 234 567 890" class="btn btn-border btn-md btn-full mb-3 rounded-sm text-uppercase font-900 border-green-dark color-green-dark bg-theme">
					대관문의
					</a>
					
                    <div class="divider mt-2 mb-5"></div>

  <!--                   <p class="font-600 mb-n1 color-highlight">Chef</p>
                    <h2 class="">셰프소개</h1>
					   <p>
					            다양한 연령을 사로잡는 탁월한 감각과 고객 맞춤 서비스에 지역 특성의 토속적인 맛을 더 하여 최고의 맛과 서비스를 제공하겠습니다.
					   </p>
					   <a href="#" class="card fl" data-card-height="240"  style="background: url(../images/gallery/chef_kang.jpg) no-repeat 0 0;background-size:cover;width: 48%;">
				            <div class="card-bottom text-center bg18 px-3" >
				                <h1 class="font-700 color-white mb-0 font-24">강민구 셰프</h1>
				                <p class="boxed-text-xl color-white opacity-70">
				                  미쉐린2스타 밍글스 
				                </p>
				            </div>
				            <div class="card-overlay bg-gradient"></div>
				        </a>
				           <a href="#" class="card fl" data-card-height="240" style="background: url(../images/gallery/chef_song.jpg) no-repeat 0 0;background-size:cover;width: 48%;margin-left: 4%;">
				            <div class="card-bottom text-center px-3"  >
				                <h1 class="font-700 color-white mb-0 font-24">송하슬람 셰프</h1>
				                <p class="boxed-text-xl color-white opacity-70">
				                 코리안델리 마마리마켓 
				                </p>
				            </div>
				            <div class="card-overlay bg-gradient"></div>
				        </a> -->
					    
                </div>
           

       
            </div>
        </div>
         
</div>   
</div>   

<!-- 하단바 -->
<jsp:include page="../common/footerBar.jsp" />

</body>

</html>