<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../common/head.jsp" />
<body>

예약&결제 페이지 -

<!-- BAY 목록 -->
<div style="padding:5px">
<c:forEach items="${bayList}" var="bay">
	<span style="background-color:#daeaf9;padding:5px;border-radius:5px">${bay.bayName}</span>
</c:forEach>
</div>
<!-- // BAY 목록 -->

<!-- 해당 회원의 등급과 선택한 Bay의 마지막 오픈일자  -->
	<span>${maxBkDay}</span>
<!-- // 해당 회원의 등급과 선택한 Bay의 마지막 오픈일자  -->

<div>
	<input type="button" value="결제하기" onClick="doPay()"/>
</div>

<script type="text/javascript">
function doPay() {

	var params = {
		// key, userid, amount, itemName 등 수정해야함
		"key"       : "1234",
		"serviceId" : "<%=Globals.serviceId%>",
		"amount"    : 100,
		"returnUrl" : "<%=Globals.returnPayUrl%>",
		"itemCode"  : "0004",
		"itemName"  : "이용권",
		"userId"    : "1234",
		"userName"  : "배은화",
		"userMail"  : "baeean@naver.com",
		"reserved1" : "클럽디청담",
		"reserved2" : "RESERVATION",
		//"reserved3" : JSON.stringify(royalPayReservationInfo),
		"protocol"  : "<%=Globals.protocolType%>"
	}

	mPay.action(params, function() {
		// 결제 성공했을때 이동할 페이지 
		
		/*
		var sUrl = "<c:url value='/hills/member/setLogin.do'/>";
		
		mAjax3(sUrl, {"msNum" : "<c:out value='${sessionScope.msMember.msNum}'/>"}, "POST", true, function(data) {
			location.href = "<c:url value='/hills/reservation/reservationCheck.do'/>";	
		});*/
		
		alert("!!!!");
	}, function() {
		// 결제 실패했을때 .. 
	});
}
</script>
<jsp:include page="../common/script.jsp" />
</body>
</html>