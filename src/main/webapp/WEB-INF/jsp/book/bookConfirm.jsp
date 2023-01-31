<!-- 
기능 : 결제완료 페이지 
작성자 :bae
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />

<body class="theme-dark">
    
<div id="preloader"><div class="spinner-border color-highlight" role="status"></div></div>
    
<div id="page">
    
    <div class="header header-fixed header-logo-app">
        <a href="javascript:history.back(-1)" class="header-title header-subtitle">결제완료</a>
		<jsp:include page="../common/top.jsp" />
    </div>
    
    <!-- 좌측GNB-->
	<jsp:include page="../common/menu.jsp" />
	<!-- //좌측GNB-->

    <div class="page-content header-clear-medium">
        <div class="menu-title">
            <h1 class="my-0 py-0 ">결제완료</h1>
            <p>결제가 정상적으로 완료되었습니다. <br/>고객센터: 02-222-2222</p>
        </div>
        <div class="divider mt-4"></div>
        
        <div class="content">
			<fmt:parseDate value="${bk.BK_DAY}" var="dateValue" pattern="yyyyMMdd"/>
			<fmt:formatDate value="${dateValue}" var="bkDay" pattern="yyyy-MM-dd"/>
			<div class="row mb-3 mt-4">
				<div class="d-flex mb-3 pl-2" style="width:100%">
					<div>
						<img src="/images/gallery/4.jpg" height="80" class="rounded-s shadow-xl">
					</div>
					<div class="pl-3" style="width:80%">
						<h1 class="font-20 mb-n3">${bk.CO_NAME} </h1>
						<p class="mb-1 mt-2 color-highlight font-15">${bk.BAY_NAME}</p>
						<!-- <p class="line-height-s font-12 font-500">서울시 청담동 청담로 988</p> -->
					</div>
				</div> 
			
				<!-- 방문날짜 -->
				<h5 class="col-4 text-left font-15">방문날짜</h5>
				<h5 class="col-8 text-right font-14 opacity-60">
					${bkDay}
				</h5>
			
				<!-- 이용시간 -->
				<h5 class="col-4 text-left font-15">이용시간</h5>
				<h5 class="col-8 text-right font-14 opacity-60">
					${bk.BK_TIME}
				</h5>
				<!-- 결제금액 -->
				<h5 class="col-4 text-left font-15">결제금액</h5>
				<h5 class="col-8 text-right font-14 opacity-60 ">
					<fmt:formatNumber value="${bk.MN_AMOUNT}" pattern="#,###" />원
				</h5>
				<!-- 이용권 -->
				<c:if test="${bk.VC_CNT > 0}">
					<h5 class="col-4 text-left font-15">이용권</h5>
					<h5 class="col-8 text-right font-14 opacity-60 ">
						${bk.VC_NAME} (${bk.VC_CNT} 매 사용)
					</h5>
				</c:if>
			</div>
			
			<div class="divider"></div>
			
			<!-- 예약내역확인 -->
			<a href="javascript:onClick=bookList('${bk.CO_DIV}')" class="mt-1 mb-3 btn btn-md bg-blue-dark btn-full shadow-xl text-uppercase font-800 rounded-s">
				예약내역확인
			</a>
			
			<!-- 홈으로 -->
			<a href="/main" class="mt-1 mb-4 btn btn-md bg-green-dark btn-full shadow-xl text-uppercase font-800 rounded-s">
				홈으로
			</a>
        </div> 
	</div>  
	<!--  content ends -->   
</div>   

<!--   Global Footer-->
<jsp:include page="../common/footerBar.jsp" />

</body>

<jsp:include page="../common/alertModal.jsp" />  

<script type="text/javascript">

var msId = "<c:out value='${sessionScope.msMember.msId}'/>";

$(document).ready(function() {
	if (msId == null || msId == "") {
		// alertModal.fail('로그인 후 이용 가능합니다.');
		location.href = "/login";	
		return;
	}
});

function bookList(coDiv) {
	location.href='/book/bookList/'+coDiv;
}
</script>
</html>