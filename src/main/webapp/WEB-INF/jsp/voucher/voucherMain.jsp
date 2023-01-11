<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../common/head.jsp" />
<body>

이용권 구매 페이지 

<!-- 이용권 목록 -->
<div style="padding:5px">
<c:forEach items="${vocList}" var="voc">
	<div>
	${voc.vcName} / ${voc.vcAmount} / <input type="button" value="구매" onClick="doPay('${voc.vcName}', ${voc.vcAmount})"/>
	<br/> 유효기간 : ${voc.nowDt} ~ ${voc.endDt}
	<hr>
	</div>
</c:forEach>
</div>
<!-- // 이용권 목록 -->


<script type="text/javascript">
function doPay(vcName, vcAmount) {
	var reservationInfo = {};
	reservationInfo.vcName = vcName;
	reservationInfo.vcAmount = vcAmount;
	
	var params = {
		// key, userid, amount, itemName 등 수정해야함
		"key"       : "1234",
		"serviceId" : "<%=Globals.serviceId%>",
		"amount"    : vcAmount,
		"returnUrl" : "<%=Globals.returnPayUrl%>",
		"itemCode"  : "0004",
		"itemName"  : vcName,
		"userId"    : "1234",
		"userName"  : "배은화",
		"userMail"  : "baeean@naver.com",
		"reserved1" : "클럽디청담",
		"reserved2" : "VOUCHER",
		"reserved3" : JSON.stringify(reservationInfo),
		"protocol"  : "<%=Globals.protocolType%>"
	}

	mPay.action(params, function() {
		// 결제 성공했을때 이동할 페이지 
		
		/*
		var sUrl = "<c:url value='/hills/member/setLogin.do'/>";
		
		mAjax3(sUrl, {"msNum" : "<c:out value='${sessionScope.msMember.msNum}'/>"}, "POST", true, function(data) {
			location.href = "<c:url value='/hills/reservation/reservationCheck.do'/>";	
		});*/
		
		alert("이용권 구매 성공");
	}, function() {
		// 결제 실패했을때 .. 
	});
}
</script>
<jsp:include page="../common/script.jsp" />
</body>
</html>