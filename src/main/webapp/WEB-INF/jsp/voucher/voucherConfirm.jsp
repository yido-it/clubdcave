<!-- 
기능 : 이용권 결제완료 페이지 
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
    
 <%--    
	<div class="header header-fixed header-logo-app">
        <a href="#" class="header-title header-subtitle">결제완료</a>
		<jsp:include page="../common/top.jsp" />
    </div> 
    
    <!-- 좌측GNB-->
	<jsp:include page="../common/menu.jsp" />
	<!-- //좌측GNB-->
--%>

    <div class="page-content mt-5">
		<div class="menu-title">
			<h1 class="my-0 py-0 text-center">
				<i class="fa fa-3x fa-check-circle scale-box color-green-dark shadow-xl rounded-circle mb-2"></i>
				<br/>
				결제완료
			</h1>
			<p class="text-center mt-2 font-14">결제가 정상적으로 완료되었습니다. <br/>고객센터: 02-6403-7717</p>
		</div>
		        
        <div class="divider mt-3"></div>
        
        <div class="content">
			<div class="d-flex mb-0" style="width:100%">
				<div>
					<!-- <i class="fa-solid fa-money-check fa-4x color-red-dark"></i>  -->
					<img src="/images/icons/ticket2.png" style="width:70px">
				</div>
				<div class="pl-3" style="width:80%">
					<h1 class="font-20 mb-n3">${sale.CO_NAME} </h1>
					<p class="mb-1 mt-2 color-highlight font-15">${sale.VC_NAME}</p>
					<!-- <p class="line-height-s font-12 font-500">서울시 청담동 청담로 988</p> -->
				</div>
			</div> 
			<p class="opacity-50 mt-1 font-13"><i class="fa-regular fa-clock"></i> 유효기간: 구매일로부터 ${sale.VC_MONTH}개월</p>


			<div class="divider mt-4"></div>
			<div class="row">
				<div class="col-6 mt-1"><h6 class="font-700 font-20">결제금액</h6></div>
				<div class="col-6">
					<h6 class="font-700 font-28">
						<fmt:formatNumber value="${sale.VC_AMOUNT}" pattern="#,###" />원
					</h6>
				</div>
			</div>	 
		
			
			<div class="divider"></div>
			
			<!-- 이용권 내역확인 -->
			<a href="javascript:onClick=goList('${sale.CO_DIV}')" class="mt-1 mb-3 btn btn-md bg-blue-dark btn-full shadow-xl text-uppercase font-800 rounded-s">
				이용권 내역확인
			</a>
			
			<!-- 홈으로 -->
			<a href="javascript:location.href='/main';" class="mt-1 mb-4 btn btn-md bg-green-dark btn-full shadow-xl text-uppercase font-800 rounded-s">
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

// 이용권 구매내역 확인 페이지로 이동 
function goList(coDiv) {
	location.href='/voucher/voucherMain/'+coDiv+'?tab=2';
}
</script>
</html>