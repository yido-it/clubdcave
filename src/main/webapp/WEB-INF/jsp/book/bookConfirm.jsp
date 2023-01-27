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
			<a href="/book/bookList/${bk.CO_DIV}" class="mt-1 mb-3 btn btn-md bg-blue-dark btn-full shadow-xl text-uppercase font-800 rounded-s">
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


<!--결제동의 모달-->
<div id="modal_pay" class="menu menu-box-bottom rounded-0 " data-menu-height="530" data-menu-effect="menu-parallax" style="display: block; height: 510px;">
	<h1 class="text-center mt-4">
		<i class="fa fa-2x fa-check-circle scale-box color-red-dark shadow-xl rounded-circle"></i> 
	</h1>
	<h3 class="text-center mt-3 font-700">환불규정 약관</h3>
	<p class="boxed-text-xl opacity-70">
		클럽디청담 결제에 대한 동의가 필요합니다.<br>
	</p>
	
	<div class="accordion" id="accordion-1">
		<div class="mb-0"><div>
		<div class="py-2 pl-3 pr-3 box_agree" >
			- 제1장 총칙 -
			
			제1조 (목적)
			본 약관은 클럽디(이하 “CLUB D”라 함)및 그 계열사(이하 “회사”라 함)가 제공/운영하는 웹사이트 (이하 “통합회원 홈페이지”라 함)와 스마트폰 등 이동통신기기를 통해 제공되는 CLUB D 모바일 애플리케이션(이하 “CLUB D 앱”이라 함)을 통해서 제공되는 고객 관련 서비스(이하 “서비스”라 함)를 하나의 ID(이하 “아이디”라 함) 와 PASSWORD(이하 “비밀번호”라 함)로 이용함에 있어 회사 및 회원의 제반 권리, 의무, 관련 절차 등을 규정하는데 그 목적이 있습니다.
			
			제2조 (용어의 정의)
			본 약관에서 사용하는 주요 용어의 정의는 다음과 같습니다.
			1. “통합회원 홈페이지”란 회원이 온라인을 통해 서비스를 이용할 수 있는 “회사”의 인터넷 사이트들을 말하며, 추후 변동될 수 있습니다. 각 통합회원 홈페이지에서 서비스를 원활히 이용하고자 하는 회원은 “회사”에서 정하는 회원 가입 절차를 거쳐야 합니다.
		</div>
	</div>
	
	</div>
		<div class="form-check icon-check mt-3 opacity-60" style="margin:0 auto">
		
			<input class="form-check-input" type="checkbox" value="Y" id="chkAgree" name="chkAgree">
			<label class="form-check-label color-white" for="chkAgree">약관에 동의합니다</label>
			
			<i class="icon-check-1 fa fa-square color-gray-dark font-16 ml-n1"></i>
			<i class="icon-check-2 fa fa-check-square font-16 color-highlight ml-n1"></i>
		</div>
	</div>
	
	<div class="row mb-0 mr-3 ml-3 mt-5 mb-5">
		<div class="col-12">
			<a href="#" onClick="doPay()" class="btn btn-full btn-md color-green-dark border-green-dark font-800 text-uppercase rounded-s">
			결제요청
			</a>
		</div>
	</div>
</div>
<!--//결제동의 모달-->

</body>

<jsp:include page="../common/alertModal.jsp" />  

<script type="text/javascript">

var msId 		= "<c:out value='${sessionScope.msMember.msId}'/>";

$(document).ready(function() {
	if (msId == null || msId == "") {
		// alertModal.fail('로그인 후 이용 가능합니다.');
		location.href = "/login";	
		return;
	}
});

</script>
</html>