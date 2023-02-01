<!-- 
기능 : 예약내역
작성자 :bae
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
        <a href="javascript:history.back(-1)" class="header-title header-subtitle">예약내역</a>
		<jsp:include page="../common/top.jsp" />
    </div>
    
    <!-- 좌측GNB-->
	<jsp:include page="../common/menu.jsp" />
	<!-- //좌측GNB-->

    <div class="page-content header-clear-medium">
        <div class="menu-title">
            <h1 class="my-0 py-0">예약내역</h1>
        </div>

		<!--예약카드-->
		<c:forEach items="${list}" var="bk">
			<fmt:parseDate value="${bk.BK_DAY}" var="dateValue" pattern="yyyyMMdd"/>
			<fmt:formatDate value="${dateValue}" var="bkDay" pattern="yyyy-MM-dd"/>
			<div class="card card-style">
				<div class="content mb-3">
				
					<div class="d-flex mb-3" style="width:100%">
						<div>
							<img src="/images/gallery/4.jpg" height="80" class="rounded-s shadow-xl">
						</div>
						
						<div class="pl-3" style="width:80%">
							<!-- 지점 -->
							<h1 class="font-20 mb-n3">${bk.CO_NAME}</h1>
							<!-- 베이 -->
							<p class="mb-1 mt-2 color-highlight font-15">${bk.BAY_NAME}</p>
							<!-- <p class="line-height-s font-12 font-500">서울시 청담동 청담로 988</p>  -->
						</div>
					</div>
					
					<div class="row mb-3 mt-4">
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
					
					<div class="divider mb-3"></div>
					
					<!-- ┌────────────── 예약취소 / 당일취소불가 ──────────────┐-->
					<c:choose> 
						<c:when test="${bk.BK_STATE == 1}">
							
							<c:choose> 
								<c:when test="${nowDt == bk.BK_DAY}">
									<!-- 당일취소불가 -->
									<a href="#" class="fr btn btn-border btn-sm rounded-0 text-uppercase font-900 border-yellow-dark color-yellow-dark bg-theme">
									당일취소불가
									</a>
								</c:when> 
								<c:when test="${nowDt >= bk.BK_DAY}">
									<!-- 취소불가 -->
									<a href="#" class="fr btn btn-border btn-sm rounded-0 text-uppercase font-900 border-yellow-dark color-yellow-dark bg-theme">
									취소불가
									</a>
								</c:when> 
								<c:otherwise>
									<!-- 예약취소 -->
									<a href="javascript:onClick=doCancel('${bk.CALC_SERIAL_NO}', ${bk.MN_AMOUNT})" class="fr btn btn-border btn-sm rounded-0 text-uppercase font-900 border-red-dark color-red-dark bg-theme">
									예약취소
									</a>
								</c:otherwise> 
							</c:choose> 
									
						</c:when> 
						<c:otherwise>
							<a href="#" class="fr btn btn-border btn-sm rounded-0 text-uppercase font-900 border-yellow-dark color-yellow-dark bg-theme">
							${bk.BK_STATE_NAME} 된 예약
							</a>
						</c:otherwise> 
					</c:choose> 
					<!-- └────────────── 예약취소 / 당일취소불가 ──────────────┘-->
				</div> 
			</div>
		</c:forEach>	
		<!--//예약카드-->	
	</div>  

	<!--  content ends -->   
</div>   

<!--   Global Footer-->
<jsp:include page="../common/footerBar.jsp" />

</body>

<jsp:include page="../common/alertModal.jsp" />  

<script type="text/javascript">

var msId 		= "<c:out value='${sessionScope.msMember.msId}'/>";
var coDiv 		= "<c:out value='${coDiv}'/>";

$(document).ready(function() {
	if (msId == null || msId == "") {
		// alertModal.fail('로그인 후 이용 가능합니다.');
		location.href = "/login";	
		return;
	}
});

// 예약취소 
function doCancel(serialNo, amount){
	if(confirm('예약을 취소 하시겠습니까?')) {
		
		var reservationInfo 			= {};
		reservationInfo.calcSerialNo 	= serialNo;
		reservationInfo.coDiv 			= coDiv;
		
		var url = "";
		
	    if (amount == 0) {
	    	url = "/voucher/vCancel/";
	    } else {	    
	    	url = "/book/bookCancel/";
	    }
	    
    	$.ajax({
    		url: url
    		, type: "post"
    		, dataType: 'json'
    		, data: reservationInfo
    		, contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
    		, success: function(data) {    			
    			if (data.code == '0000') {
    				location.reload();
    			} else if (data.code == '9999') {
    				alertModal.fail(data.message);
    			}
    		}
    	});	      
	}
}

</script>
</html>