<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="nowDt"><fmt:formatDate value="${now}" pattern="yyyyMMdd" /></c:set> 

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
               클럽디 카브는 세계 명문 골프장이 펼쳐진 스크린에서 라운드를 즐기며 미슐랭 셰프의 섬세한 요리들을 맛볼 수 있는 어반골프클럽 비스트로 입니다. 
               <br/><br/>
               동반 플레이어들과 골프게임 후 식사도 가능하여 도심에서 골프모임을 즐기기에 최적화된 공간으로 구성되었습니다. 
               
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

                    <p class="font-600 mb-n1 color-highlight">MENU</p>
                    <h2 class="">대표메뉴</h1>
                        <p>
                          다양한 음식을 제공하고 있습니다.
                        </p>
                        <div class="double-slider owl-carousel owl-no-dots mb-0">
                            <div class="item" style="">
                                <div class="card card-style mx-0 bg-pro1" data-card-height="180" style="background: url(../images/gallery/food2.jpg) no-repeat 0 0;background-size:cover">
                                    <div class="card-bottom mb-2">
                                        <h5 class="color-white font-15 px-2">Salad</h5> 
                                    </div>
                                    <div class="card-overlay bg-gradient opacity-30"></div>
                                    <div class="card-overlay bg-gradient"></div>
                                </div>
                            </div>
                            <div class="item" style="">
                                <div class="card card-style mx-0 bg-pro1" data-card-height="180" style="background: url(../images/gallery/food1.jpg) no-repeat 0 0;background-size:cover">
                                    <div class="card-bottom mb-2">
                                        <h5 class="color-white font-15 px-2">Sandwich</h5>
                                    </div>
                                    <div class="card-overlay bg-gradient opacity-30"></div>
                                    <div class="card-overlay bg-gradient"></div>
                                </div>
                            </div>
                            <div class="item" style="">
                                <div class="card card-style mx-0 bg-pro1" data-card-height="180" style="background: url(../images/gallery/food3.jpg) no-repeat 0 0;background-size:cover">
                                    <div class="card-bottom mb-2">
                                        <h5 class="color-white font-15 px-2">Wine</h5>
                                       
                                    </div>
                                    <div class="card-overlay bg-gradient opacity-30"></div>
                                    <div class="card-overlay bg-gradient"></div>
                                </div>
                            </div>
                        </div>
               
                    <div class="divider mt-2 mb-3"></div>

                    <p class="font-600 mb-n1 color-highlight">Chef</p>
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
				        </a>
					   
					   
                    <div class=" mb-5" style="clear:left"></div>

                </div>
           

       
            </div>
        </div>
         
</div>   
</div>   

<!-- 하단바 -->
<jsp:include page="../common/footerBar.jsp" />

</body>

</html>