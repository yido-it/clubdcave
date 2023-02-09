<!-- 공지사항 목록  -->
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

<div class="header header-fixed header-logo-app">
<a href="javascript:history.back(-1)" class="header-title header-subtitle">공지사항</a>
<jsp:include page="../common/top.jsp" />
</div>

<!-- 좌측GNB-->
<jsp:include page="../common/menu.jsp" />
<!-- //좌측GNB-->

<div class="page-content header-clear-medium">

	<div class="mt-3 mb-0 div_notice">
            <div class="d-flex pb-3">
                <div>
                    <a href="noticeView"><p class="pl-3 line-height-s color-theme mb-1 pt-3 font-13">"G"골프의 "Plus" 재미를 더하다! G+Plus OPEN</p></a>
                    <p class="mb-0 pl-3 font-10 pt-1 opacity-60"><i class="fa fa-clock pr-1"></i>2023.01.22 </p> 
                </div>
            </div>
            <div class="divider mb-0"></div>
            <div class="d-flex pb-3">
                <div>
                    <a href="noticeView"><p class="pl-3 line-height-s color-theme mb-1 pt-3 font-13">식품의약품안전처 지정 음식점 위생등급 매우우수 '안심식당'</p></a>
                    <p class="mb-0 pl-3 font-10 pt-1 opacity-60"><i class="fa fa-clock pr-1"></i>2023.01.12 </p> 
                </div>
            </div>
            <div class="divider mb-0"></div>
            <div class="d-flex pb-3">
                <div>
                    <a href="noticeView"><p class="pl-3 line-height-s color-theme mb-1 pt-3 font-13">CLUB D 통합 예약 사이트 출시</p></a>
                    <p class="mb-0 pl-3 font-10 pt-1 opacity-60"><i class="fa fa-clock pr-1"></i>2023.01.09 </p> 
                </div>
            </div>
              <div class="divider mb-0"></div>
              <div class="d-flex pb-3">
                <div>
                    <a href="noticeView"><p class="pl-3 line-height-s color-theme mb-1 pt-3 font-13">CLUB D 통합 예약 사이트 출시</p></a>
                    <p class="mb-0 pl-3 font-10 pt-1 opacity-60"><i class="fa fa-clock pr-1"></i>2023.01.09 </p> 
                </div>
            </div>
              <div class="divider mb-0"></div>
              <div class="d-flex pb-3">
                <div>
                    <a href="noticeView"><p class="pl-3 line-height-s color-theme mb-1 pt-3 font-13">CLUB D 통합 예약 사이트 출시</p></a>
                    <p class="mb-0 pl-3 font-10 pt-1 opacity-60"><i class="fa fa-clock pr-1"></i>2023.01.09 </p> 
                </div>
            </div>
              <div class="divider mb-0"></div>
               <div class="d-flex pb-3">
                <div>
                    <a href="noticeView"><p class="pl-3 line-height-s color-theme mb-1 pt-3 font-13">CLUB D 통합 예약 사이트 출시</p></a>
                    <p class="mb-0 pl-3 font-10 pt-1 opacity-60"><i class="fa fa-clock pr-1"></i>2023.01.09 </p> 
                </div>
            </div>
              <div class="divider mb-0"></div>
              
     </div>
     
     <a href="#" onclick="doSearchList('more')" class="btn btn-border btn-m btn-full mt-4 mb-10 rounded-xl text-uppercase font-900 border-blue-dark color-blue-dark ml-4 mr-4" id="btnMore">
        	더보기 <i class="fa-solid fa-chevron-down"></i>
        </a>
</div>   
</div>   

<!-- 하단바 -->
<jsp:include page="../common/footerBar.jsp" />

</body>

</html>