<!-- 
기능 : 이용권 결제 

작성자 : bae
-->
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
<a href="#" class="header-title header-subtitle">구매정보확인</a>
<jsp:include page="../common/top.jsp" />
</div>

<!-- 좌측GNB-->
<jsp:include page="../common/menu.jsp" />
<!-- //좌측GNB-->

<div class="page-content header-clear-medium">
	<div class="menu-title">
		<h1 class="my-0 py-0">구매정보확인</h1>
	</div>
	<div class="divider"></div>
	<div class="content">
		<div class="d-flex mb-0" style="width:100%">
			<div>
				<!-- <i class="fa-solid fa-money-check fa-4x color-red-dark"></i>  -->
				<img src="/images/icons/ticket2.png" style="width:70px">
			</div>
			<div class="pl-3" style="width:80%">
				<h1 class="font-20 mb-n3">${place.coName}</h1>
				<p class="mb-1 mt-2 color-highlight font-15">${voc.vcName}</p>				
			</div>
		</div>
		<p class="opacity-50 mt-1 font-13"><i class="fa-regular fa-clock"></i> 유효기간: 구매일로부터 6개월</p>
	
		<div class="divider mt-4"></div>
		<div class="row">
			<div class="col-6 mt-1"><h6 class="font-700 font-20">최종결제금액</h6></div>
			<div class="col-6">
				<h6 class="font-700 font-28">
					<fmt:formatNumber value="${voc.vcAmount}" pattern="#,###" />원
				</h6>
			</div>
		</div>
		<div class="divider"></div>
		<a data-menu="modal_pay" href="" class="mt-1 mb-4 btn btn-md bg-blue-dark btn-full shadow-xl text-uppercase font-800 rounded-s">
		결제진행
		</a>
	</div> 
</div>  

<!--결제동의 모달-->
<jsp:include page="../common/pop/payAgree.jsp" />

<!-- 하단바 -->
<jsp:include page="../common/footerBar.jsp" />

</body>

<jsp:include page="../common/alertModal.jsp" />  

<script type="text/javascript">

var msId 		= "<c:out value='${sessionScope.msMember.msId}'/>";
var coDiv 		= "<c:out value='${coDiv}'/>";
var vcCd 		= "<c:out value='${voc.vcCd}'/>";		// 이용권코드
var vcName 		= "<c:out value='${voc.vcName}'/>";		// 이용권명
var vcAmount 	= "<c:out value='${voc.vcAmount}'/>";	// 구매금액

$(document).ready(function() {
	if (msId == null || msId == "") {
		// alertModal.fail('로그인 후 이용 가능합니다.');
		location.href = "/login";	
		return;
	}
});

//결제 약관동의  및 결제진행
function doPay() {
	
	const checkbox = document.getElementById('chkAgree');
	const isChk = checkbox.checked;
	
	if (isChk == false) {
		alertModal.fail('환불규정 약관에 동의해주세요.');
		return;
	}
	
	var msNum			= "<c:out value='${sessionScope.msMember.msNum}'/>";
	var msLevel 		= "<c:out value='${sessionScope.msMember.msLevel}'/>";
	var msEmail 		= "<c:out value='${sessionScope.msMember.msEmail}'/>";
	var msName 			= "<c:out value='${sessionScope.msMember.msName}'/>";
	var msFirstPhone1	= "<c:out value='${sessionScope.msMember.msFirstPhone1}'/>";
	var msMidPhone1 	= "<c:out value='${sessionScope.msMember.msMidPhone1}'/>";
	var msLastPhone1 	= "<c:out value='${sessionScope.msMember.msLastPhone1}'/>";
	var msPhone 		= msFirstPhone1 + "-" + msMidPhone1 + "-" + msLastPhone1;

	var reservationInfo 		= {};
	reservationInfo.coDiv 		= "<c:out value='${place.coDiv}'/>";		<!-- 지점코드 -->
	reservationInfo.vcCd 		= vcCd; 									<!-- 이용권코드 -->
	reservationInfo.vcAmount 	= vcAmount; 
	reservationInfo.msLevel		= msLevel;
	reservationInfo.userMail 	= msEmail;
	reservationInfo.msNum 		= msNum;
	reservationInfo.msId 		= msId;
	reservationInfo.userName 	= msName;
	reservationInfo.phone 		= msPhone;
	
    console.log(reservationInfo);

   	// 결제창 띄우기 
   	var params = {
   		"key"      	  : msNum
   		, "serviceId" : "<%=Globals.serviceId%>"
   		, "amount"    : vcAmount
   		, "returnUrl" : "<%=Globals.returnPayUrl%>"
   		, "itemCode"  : "0005"
   		, "itemName"  : vcName
   		, "userId"    : msId
   		, "userName"  : msName
   		, "userMail"  : msEmail
   		, "reserved1" : "<c:out value='${place.coDiv}'/>"
   		, "reserved2" : "VOUCHER"
   		, "reserved3" : JSON.stringify(reservationInfo)
   		, "protocol"  : "<%=Globals.protocolType%>"
   	}

   	mPay.action(params, function() {
		// 페이지 이동
		location.href="/voucher/voucherConfirm";
   	}, function() {
   		// 결제 실패했을때		
   	});   	   	
}


</script>
</html>