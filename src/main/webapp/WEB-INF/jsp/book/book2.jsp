<!-- 
기능 : 예약 2단계 페이지 (예약 정보 확인, 이용권 선택) 
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
        <a href="javascript:history.back(-1)" class="header-title header-subtitle">예약확인</a>
		<jsp:include page="../common/top.jsp" />
    </div>
    
    <!-- 좌측GNB-->
	<jsp:include page="../common/menu.jsp" />
	<!-- //좌측GNB-->

    <div class="page-content header-clear-medium">
        <div class="menu-title">
            <h1 class="my-0 py-0">예약정보확인</h1>
        </div>
        <div class="divider"></div>
        <div class="content">
            <div class="d-flex mb-3" style="width:100%">
                <div>
                	<!-- 베이 이미지 -->
                    <img src="/images/gallery/4.jpg" height="80" class="rounded-s shadow-xl">
                </div>
                <div class="pl-3" style="width:80%">
                	<!-- 지점 -->
                    <h1 class="font-20 mb-n3"> ${place.coName} </h1>
                    <!-- 베이 -->
                    <p class="mb-1 mt-2 color-highlight font-15"> ${bay.bayName}</p>
                    <!-- <p class="line-height-s font-12 font-500">서울시 청담동 청담로 988</p>  --> 
                </div>
            </div>

            <div class="row mb-3 mt-4">
            	<!-- 방문날짜 -->
                <h5 class="col-4 text-left font-15">방문날짜</h5>
                <h5 class="col-8 text-right font-14 opacity-60"> ${bkDay} </h5>

				<!-- 이용시간 -->
                <h5 class="col-4 text-left font-15">이용시간</h5>
                <h5 class="col-8 text-right font-14 opacity-60"> ${bkHis.bkTime} </h5>
             
             	<!-- 
                <h5 class="col-4 text-left font-15">예약장소</h5>
                <h5 class="col-8 text-right font-14 opacity-60 ">서울시 청담동 클럽디청담</h5>
 				-->
          
            </div>
            <div class="divider"></div>

			<!-- 보유 이용권 -->
            <div class="">
                <h4 class="font-700">보유 이용권</h4>

				<!-- 이용권 목록 -->	
				<c:forEach items="${vcList}" var="vc">
					<div class="d-flex mb-2"> 
						<input type="hidden" id="remCnt" value="${vc.VC_REM_CNT}"/>					<!-- 잔여수량 -->
						<input type="hidden" id="saleDay${vc.SALE_SEQ}" value="${vc.SALE_DAY}"/> 	<!-- 이용권 구매일자 -->
						<input type="hidden" id="coDiv${vc.SALE_SEQ}" value="${vc.CO_DIV}"/> 		
						<div class="w-100">
							<div class="form-check icon-check">
								<!-- 이용권 선택 체크박스 -->
								<input class="form-check-input" type="checkbox" name="chkVoucher" id="check${vc.SALE_SEQ}" value="${vc.SALE_SEQ}" onchange="chkVoucher(this, ${vc.SALE_SEQ})" >
								<!-- 이용권 (잔여수량) -->
								<label class="form-check-label color-gray-dark" for="check${vc.SALE_SEQ}"> 
									${vc.VC_NAME}(잔여${vc.VC_REM_CNT}장)
								</label>
								
								<!-- 이용권 개당 가격 
								<input type="hidden" name="" id="" value="${vc.SALE_SEQ}" />-->
								
								<i class="icon-check-1 far fa-square color-gray-dark font-16"></i>
								<i class="icon-check-2 far fa-check-square font-16 color-highlight"></i>
							</div>
							
							<!-- 유효기간 -->
							<p class="mb-0 mt-n2 pl-4 color-red-dark font-10 opacity-80">유효기간:${vc.VC_TO_DAY}까지</p>
						</div>
						
						<div class="ml-auto text-right">
							<div class="stepper rounded-s switch-s mr-n2 mt-n2">
								<!-- 수량 선택 -->
								<a href="#" class="stepper-sub font-14" onClick="chkQuantity('minus', ${vc.SALE_SEQ})">
									<i class="fa fa-minus color-theme opacity-40"></i>
								</a>
								<input style="font-size:15px !important" id="quantity${vc.SALE_SEQ}" type="number" min="1" max="${vc.VC_REM_CNT}" value="1">
								<a href="#" class="stepper-add font-14" onClick="chkQuantity('plus',${vc.SALE_SEQ})">
									<i class="fa fa-plus color-theme opacity-40"></i>
								</a>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</c:forEach>
				<!--//이용권-->
            </div>
			
			<div class="divider mt-4"></div>
			
			<div class="row">
				<!-- 결제금액 -->
				<div class="col-6">
					<h6 class="font-16">결제금액</h6>
				</div>
				<div class="col-6">
					<h6 class="font-16">
						<fmt:formatNumber value="${amount}" pattern="#,###" />원 
						<input type="hidden" name="amount" id="amount" value="${amount}"/>
					</h6>
				</div>
				<div class="w-100 pt-1"></div>
			
				<!-- 이용권사용 -->
				<div class="col-6 vcCnt" style="display:none">
					<h6 class="font-16 color-blue-dark">이용권사용 </h6>
				</div>
				<div class="col-6 vcAmt" style="display:none">
					<!-- 이용권 금액 -->
					<h6 class="font-16 color-blue-dark txtVcAmt"></h6>
					<input type="hidden" name="vcAmount" id="vcAmount" value=""/>
				</div>
				<div class="w-100 pt-2"></div>
			
				<!-- 최종결제금액 -->
				<div class="col-6 mt-1">
					<h6 class="font-700 font-23">최종결제금액</h6>
				</div>
				<div class="col-6">
					<h6 class="font-700 font-28 txtTotAmt">
						<fmt:formatNumber value="${amount}" pattern="#,###" />원 
					</h6>
					<input type="hidden" name="totAmount" id="totAmount" value="${amount}"/>
				</div>
			</div>
			
			<div class="divider"></div>
		
			<a data-menu="modal_pay" href=""  class="mt-1 mb-10 btn btn-md bg-blue-dark btn-full shadow-xl text-uppercase font-800 rounded-s">
				결제진행
			</a>
		</div> 
		
	</div>  

	<!--  content ends -->   
</div>   

<!--결제동의 모달-->
<jsp:include page="../common/pop/payAgree.jsp" />

<!-- 하단바 -->
<jsp:include page="../common/footerBar.jsp" />


</body>

<jsp:include page="../common/alertModal.jsp" />  

<script type="text/javascript">

var msId 		= "<c:out value='${sessionScope.msMember.msId}'/>";
var amount 		= "<c:out value='${amount}'/>";		// 결제할 금액
var timeAmt 	= "<c:out value='${timeAmt}'/>";	// 시간당 금액 (이용권1장 = 시간당금액)
var totAmount	= "<c:out value='${amount}'/>";		// 최종 결제할 금액
var vAmount 	= 0;								// 총 사용할 이용권금액
var selectedCnt = 0;								// 선택된 이용권의 총 수량

timeAmt = Number(timeAmt);
amount = Number(amount);

$(document).ready(function() {
	if (msId == null || msId == "") {
		// alertModal.fail('로그인 후 이용 가능합니다.');
		location.href = "/login";	
		return;
	}
});

// 이용권 체크박스 선택
function chkVoucher(checked, seq) {

	// 이용권 체크박스 
    const chkVou = document.getElementsByName("chkVoucher");
    const chkLength = $("input:checkbox[name='chkVoucher']:checked").length;   
	console.log('선택되어있는 체크박스:', chkLength);
    
	var quantity = Number($('#quantity'+seq).val());

	console.log('선택된 이용권 수량:', quantity, ', 이용권 총 금액:', vAmount);
	
	if( checked.checked == true ){
		// 이용권 선택
		
		if (quantity == 0) {
			$('#quantity'+seq).val(1);
			quantity = Number($('#quantity'+seq).val());
		}
		
		vAmount += (timeAmt * quantity);
		totAmount = amount - vAmount;
		selectedCnt += 1;
		
		// 이용권금액
	 	document.querySelector(".txtVcAmt").innerHTML = "-" + (vAmount).toLocaleString("ko-KR") + "원";
		$("#vcAmount").val(vAmount);
		
		// 총결제금액
	 	document.querySelector(".txtTotAmt").innerHTML = (totAmount).toLocaleString("ko-KR") + "원";
		$("#vcAmount").val(totAmount);
		
		$('.vcCnt').css('display', 'inline');
		$('.vcAmt').css('display', 'inline');
				
	} else {
		// 이용권 선택 해제
		
		// 총금액 - 선택된 이용권 금액 
		vAmount -= (timeAmt * quantity);
		console.log('해제 > 총 결제금액: ', totAmount, ',총 이용권 금액:', vAmount);
		totAmount = amount - vAmount;	// 결제금액 - 이용권금액 = 최종 결제할 금액
		selectedCnt -= 1;
		
		// 이용권금액
	 	document.querySelector(".txtVcAmt").innerHTML = "-" + (vAmount).toLocaleString("ko-KR") + "원";
		$("#vcAmount").val(vAmount);
		
		// 총결제금액
	 	document.querySelector(".txtTotAmt").innerHTML = (totAmount).toLocaleString("ko-KR") + "원";
		$("#vcAmount").val(totAmount);

		// 선택되어있는 이용권이  0개인 경우에만 숨김처리 
		if (chkLength == 0) {
    		$('.vcCnt').css('display', 'none');
    		$('.vcAmt').css('display', 'none');
		}
	    
	}
}

// 수량 선택시 호출되는 함수
function chkQuantity(type, seq){

	// 이용권 체크박스 
    const chkVou = document.getElementsByName("chkVoucher");					// 모든 이용권
	const chkThis = document.getElementById('check'+seq);						// 현재 선택한 이용권
    const chkLength = $("input:checkbox[name='chkVoucher']:checked").length;   	// 선택된 이용권 개수
		  
	var quantity = Number($('#quantity'+seq).val());
	var remCnt = Number($('#remCnt').val());

	switch (type) {
		case "minus" :
			
			quantity -= 1;
			console.log('현재 선택된 수량:', quantity);
			
			if (quantity == 0 && chkThis.checked == false) {
				quantity += 1;
				$('#quantity'+seq).val(quantity+1);	
				alertModal.fail('이용권을 선택해주세요.');
				return;
			} else if (quantity < 0) {
				quantity += 1;
				$('#quantity'+seq).val(quantity+1);					
				return;
			}
			
			if (chkLength == 1 && quantity <= 0) {
				// 수량이 0이면 체크박스 자동으로 해제되도록
				$("input:checkbox[id='check"+seq+"']").prop("checked", false);
				$('.vcCnt').css('display', 'none');
				$('.vcAmt').css('display', 'none');	
			}
		    
			// 이용권 체크박스 반복문 
			var totSelectedAmt = 0;		// [선택된 이용권+수량]의 금액 합계 	
		    for (let i = 0; i < chkVou.length; i++) {
		    	if (chkVou[i].checked == true) {
		    		
		    		// 선택된 이용권 수량 
		    		var tmpCnt = 0;
		    		if (seq == chkVou[i].value) {
		    			// 현재 선택된 이용권이라면 + 1 
		    			tmpCnt = Number($('#quantity'+chkVou[i].value).val()) - 1; 	
		    		} else {
		    			tmpCnt = Number($('#quantity'+chkVou[i].value).val()); 	
		    		}
		    		// end.
		    	    
					totSelectedAmt += timeAmt * tmpCnt;
					console.log('[선택된 이용권+수량]의 금액 합계 :', totSelectedAmt, ', 선택된 이용권 수량  : ', tmpCnt, ', 총 금액:' , totSelectedAmt);
		    	    			    	   
					if (tmpCnt == 0) {
						// 수량 0개되면 해당 이용권 체크박스 선택해제	
						$("input:checkbox[id='check"+chkVou[i].value+"']").prop("checked", false);
					}
		    	}
		    	vAmount = totSelectedAmt;
		    }			
			// end.
			
			console.log('1. 선택된 이용권금액:', timeAmt, ', 현재 선택된 수량: ', quantity, ', 이용권 총 금액: ', vAmount, ', 총 결제금액 : ', totAmount);
			// vAmount = selectedAmt * quantity;
			totAmount = amount - vAmount;	// 결제금액 - 이용권금액 = 최종 결제할 금액 
			console.log('2. 선택된 이용권금액:', timeAmt, ', 이용권 총 금액: ', vAmount, ', 총 결제금액 : ', totAmount);
			
			break;

		case "plus" :
			quantity += 1;
			console.log('현재 선택된 수량:', quantity);
			
			// 수량이 1이상이면 체크박스 자동으로 선택되도록
			if (quantity > 0) {
				$("input:checkbox[id='check"+seq+"']").prop("checked", true);
			}	
			
			if (quantity == 2) selectedCnt = quantity;
			else selectedCnt += 1;

			// 선택된 수량이 잔여수량보다 크면 중단 
			if (quantity > remCnt) {
				quantity -= 1;
				alertModal.fail('잔여수량 초과하였습니다.');
				return;
			}

			// 이용권 체크박스 반복문 
			var totSelectedAmt = 0;		// [선택된 이용권+수량]의 금액 합계 	
		    for (let i = 0; i < chkVou.length; i++) {
		    	if (chkVou[i].checked == true) {
		    	
		    		// 선택된 이용권 수량 
		    		var tmpCnt = 0;
		    		if (seq == chkVou[i].value) {
		    			// 현재 선택된 이용권이라면 + 1 
		    			tmpCnt = Number($('#quantity'+chkVou[i].value).val()) + 1; 	
		    		} else {
		    			tmpCnt = Number($('#quantity'+chkVou[i].value).val()); 	
		    		}
		    		// end.
					
					totSelectedAmt += timeAmt * tmpCnt;
		    	}
		    	vAmount = totSelectedAmt;
		    }			
			// end.

			console.log('[선택된 이용권+수량]의 금액 합계 :', totSelectedAmt);
			
			// 선택된 수량의 금액이 결제금액보다 크면 중단
			if (totSelectedAmt > amount) {
				quantity -= 1;
				$('#quantity'+seq).val(quantity-1);	// 현재 선택된 이용권의 수량 -1 
				vAmount -= timeAmt;
				alertModal.fail('이용권 사용금액이 결제금액보다 클 수 없습니다.');
				return;
			}
			// end.
			
			$('.vcCnt').css('display', 'inline');
			$('.vcAmt').css('display', 'inline');

			if (quantity == 2) {
				console.log('1. 선택된 이용권금액:', timeAmt, ', 현재 선택된 수량: ', quantity, ', 이용권 총 금액: ', vAmount, ', 총 결제금액 : ', totAmount);
				//vAmount = vAmount + (selectedAmt * quantity);
			} else {
				console.log('1. 선택된 이용권금액:', timeAmt, ', 현재 선택된 수량: ', quantity, ', 이용권 총 금액: ', vAmount, ', 총 결제금액 : ', totAmount);
				//vAmount += selectedAmt;
			}
			totAmount = amount - vAmount;
			console.log('2. 선택된 이용권금액:', timeAmt, ', 이용권 총 금액: ', vAmount, ', 총 결제금액 : ', totAmount);
			
			break;
	}

	// 이용권금액
 	document.querySelector(".txtVcAmt").innerHTML = "-" + (vAmount).toLocaleString("ko-KR") + "원";
	$("#vcAmount").val(vAmount);
	
	// 총결제금액
 	document.querySelector(".txtTotAmt").innerHTML = (totAmount).toLocaleString("ko-KR") + "원";
	$("#vcAmount").val(totAmount);
	
}

// 결제 약관동의  및 결제진행
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
	reservationInfo.bayCondi 	= "<c:out value='${bay.bayCd}'/>"; 			<!-- 베이코드 -->
	reservationInfo.bkDay 		= "<c:out value='${bkHis.bkDay}'/>"; 		<!-- 예약임시테이블 -->
	reservationInfo.bkTime 		= "<c:out value='${bkHis.bkTime2}'/>"; 		<!-- 예약임시테이블 -->
	reservationInfo.serialNo	= "<c:out value='${bkHis.serialNo}'/>"; 	<!-- 예약임시테이블 -->
	reservationInfo.bkAmount 	= totAmount; 
	reservationInfo.oriBkAmount = amount; 
	reservationInfo.msLevel		= msLevel;
	reservationInfo.userMail 	= msEmail;
	reservationInfo.msNum 		= msNum;
	reservationInfo.msId 		= msId;
	reservationInfo.userName 	= msName;
	reservationInfo.phone 		= msPhone;
	
	var vList 					= new Array();		// 이용권 정보 

	// 이용권 정보
    const chkVou = document.getElementsByName("chkVoucher");	// 모든 이용권

    for (let i = 0; i < chkVou.length; i++) {
    	if (chkVou[i].checked == true) {
    		var vData = new Object();	
    		vData.coDiv 	= $('#coDiv'+chkVou[i].value).val();
    		vData.saleSeq 	= chkVou[i].value;
    		vData.saleDay 	= $('#saleDay'+chkVou[i].value).val();
    		vData.amount 	= $('#amount'+chkVou[i].value).val(); 	
    		vData.quantity	= $('#quantity'+chkVou[i].value).val(); 	
    		vList.push(vData);
    	}
    }
    
    if (vList != null) reservationInfo.vList = vList;
    // end.
	
    console.log(reservationInfo);
    
    if (totAmount == 0) {
        // 결제할 요금이 0원이면 결제창 띄우지 않고, 이용권으로 처리
    	$.ajax({
    		url: "/voucher/vPay/"
    		, type: "post"
    		, dataType: 'json'
    		, data: reservationInfo
    		, contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
    		, success: function(data) {
    			if (data.code == '0000') {
    				// 결제완료 페이지로 이동
    				location.href="/book/bookConfirm";
    			} else if (data.code == '9999') {
    				alertModal.fail(data.message);
    				
    			}
    		}
    	});	        
    	
    } else {
    	// 결제창 띄우기 
    	var params = {
    		"key"      	  : msNum
    		, "serviceId" : "<%=Globals.serviceId%>"
    		, "amount"    : totAmount
    		, "returnUrl" : "<%=Globals.returnPayUrl%>"
    		, "itemCode"  : "0004"
    		, "itemName"  : "예약"
    		, "userId"    : msId
    		, "userName"  : msName
    		, "userMail"  : msEmail
    		, "reserved1" : "<c:out value='${place.coDiv}'/>"
    		, "reserved2" : "RESERVATION"
    		, "reserved3" : JSON.stringify(reservationInfo)
    		, "protocol"  : "<%=Globals.protocolType%>"
    	}

    	mPay.action(params, function() {
			// 결제완료 페이지로 이동
			location.href="/book/bookConfirm";
			
    	}, function() {
    		// 결제 실패했을때 예약 선점된거 풀기 
    		fnUnBkMark(reservationInfo);			
    	});   	   	
    }
}
</script>
</html>