<!-- 공지사항 상세  -->
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
	<div class="mt-3 mb-0">
            <div class="d-flex mb-3">
                <div>
                    
                    <p class="pl-3 line-height-s color-theme mb-1 font-15">"G"골프의 "Plus" 재미를 더하다! G+Plus OPEN</p>
                    <p class="mb-0 pl-3 font-12 pt-1 opacity-60"><i class="fa fa-clock pr-1"></i>2023.01.22 </p> 
                </div>
            </div>
            <div class="divider mb-3"></div>
        
        </div>
        <div class="content">
        	<div>
        		<img src="/images/event/notice.jpg" style="width:100%">
        	</div>
        	
	        <p class="mt-1">
		       	이도와 카카오VX는 20일 서울 중구 이도 본사 씨티스퀘어 빌딩에서 온·오프라인 플랫폼 활용을 통한 골프 사업 활성화 협약을 했다고 밝혔다.
				
				이도는 퍼블릭 골프 클럽 브랜드 클럽디(Club D)를 운영하는 골프장 통합관리운영 전문회사다.<Br/><Br/>
				
				클럽디는 현재 충북 보은에 있는 클럽디 보은과 속리산, 전북의 클럽디 금강 등 3개의 골프장(72홀)을 위탁 운영하고 있으며, 전국으로 사업장을 꾸준히 확대하고 있다.
				
				카카오 VX는 골프장과 골퍼를 연결해 예약부터 결제, 실시간 교통 안내까지 카카오톡 채팅창에서 한 번에 실행하는 원스톱 골프 서비스인 '카카오 골프 예약'과 스크린골프 브랜드인 '티업비전 2', '티업비전', '지스윙' 등 다양한 골프 서비스와 사업으로 골프 시장을 선도하고 있다
				
				이번 업무협약으로 이도는 카카오 VX에 클럽디(Club D)가 운영하는 골프장 티타임 제공, 스크린골프 코스 제작과 프로모션 등을 지원한다.
				
				카카오 VX는 클럽디 골프장 예약과 홍보, 스크린골프 코스 제공과 프로모션을 제공한다.
		        	
	        </p>
        </div>
          <div class="divider mb-3"></div>
          <div class="row">
          	
          	<button onClick="location.href='noticeList'" type="button" class="col-6 mt-2 mb-10 btn btn-md bg-blue-dark shadow-xl text-uppercase font-800 rounded-s" style="margin:0 auto">            	
	          		목록
	        </button>
	        
        </div>
</div>   
</div>   

<!-- 하단바 -->
<jsp:include page="../common/footerBar.jsp" />

</body>

</html>