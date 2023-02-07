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
                            <div class="card m-0 bg-18" data-card-height="300"  >
                              
                                <div class="card-overlay bg-gradient"></div>
                            </div>
                            
                
                            <div class="card m-0 bg-1" data-card-height="300"  >
                               
                                <div class="card-bottom">
                                 
                                </div>
                                <div class="card-overlay bg-gradient"></div>
                            </div>
                
                            <div class="card m-0 bg-28" data-card-height="300"  > 
                              
                                <div class="card-overlay bg-gradient"></div>
                            
                </div>
            </div>    
        </div>
 

        <div class="card card-clear" data-card-height="323"></div>
         
        <div class="page-content pb-3">  
            <div class="card card-full rounded-m" style="margin-bottom:-10px">
             
                <div class="divider mb-0"></div>
    
                
                <div class="content">
                <p class="mb-n1 color-highlight font-600 mb-n1">오픈골프존</p>
                    <h1 class="font-26">
                    <span class="badge bg-blue-dark px-2 py-1 mr-1 mt-2 text-uppercase">B1</span>
                     OPEN GOLF ZONE
                    </h1>
                                      
                  
                    <p class="mb-2">
                        <span class="badge bg-green-dark px-2 py-1 mt-2 text-uppercase">Fullswing</span>
                        <span class="badge bg-green-dark px-2 py-1 ml-2 mt-2 text-uppercase">해시태그</span>
                    </p>
                      <div class="divider mt-2 mb-3"></div>
                      <p>
                   6개 타석 모두 국내 최초로 FULL SWING Pro가 설치되어 있어 PGA TOUR 메이저 대회가 열리는 골프코스 라운드 뿐만 아니라 다양한 스포츠 게임도 즐길 수 있습니다. 
                   <br/><br/>
                   미니 프로암, 다양한 오픈 클래스와 스킬 챌린지, 그리고 동반 플레이어들과 골프게임 후 식사도 가능하여 도심에서 골프모임을 즐기기에 최적화된 공간으로 구성되었습니다. 

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