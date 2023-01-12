<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />
<body>

예약&결제 페이지 

<div style="padding:10px">
	<form id="frm">
		<input type="hidden" name="bayCondi" id="bayCondi" value="001" />
		<input type="hidden" name="coDiv" id="coDiv" value="001" />
		<input type="hidden" name="bkDay" id="bkDay" value="20230115" /> 
	</form>
	
	<!-- BAY 목록 -->
	<div>
	<c:forEach items="${bayList}" var="bay">
		<span style="background-color:#daeaf9;padding:5px;border-radius:5px">${bay.bayName}</span>
	</c:forEach>
	</div>
	<!-- // BAY 목록 -->
	
	<div style="margin:10px 0">
		<span onClick="selectTime()" style="background-color:#df3e3e;color:white;">B1 풀스윙프로(Day) / 1월15일 클릭</span>
	</div>
	
	<!-- 해당 회원의 등급과 선택한 Bay의 마지막 오픈일자  -->
	<span>마지막 오픈일자 : ${maxBkDay}</span>
	<!-- // 해당 회원의 등급과 선택한 Bay의 마지막 오픈일자  -->
	
	<div style="width:100%;float:left">
		<span>예약가능한시간</span>
		<div class="availableTime" style="padding:10px"></div>
	</div>
	
	<div class="costInfo" style="width:100%;float:left;margin:10px 0"></div>
		
	<div style="width:100%;float:left;margin-top:10px;border-top:1px solid #dddddd; padding-top:10px">
		<input type="button" value="다중 결제" onClick="doPayTest()"/>
	</div>

</div>

<script type="text/javascript">

var msId = "<c:out value='${sessionScope.msMember.msId}'/>";
var msLevel = "<c:out value='${sessionScope.msMember.msLevel}'/>";
var msEmail = "<c:out value='${sessionScope.msMember.msEmail}'/>";
var msName = "<c:out value='${sessionScope.msMember.msName}'/>";
var msPhone = "<c:out value='${sessionScope.msMember.msPhone}'/>";

$(document).ready(function() {
	
	if (msId == null || msId == "") {
		alert("로그인 후 이용 가능합니다.");
		location.href = "/login";	
		return;
	}
	
});

// 다중결제 테스트 
function doPayTest() {
	var reservationInfo 		= {};
	reservationInfo.coDiv 		= $('#coDiv').val();
	reservationInfo.bayCondi 	= $('#bayCondi').val();
	reservationInfo.bkDay 		= $('#bkDay').val();
	reservationInfo.bkTime 		= "0800,1300";			// 쉼표로 구분하여 연결
	reservationInfo.bkAmount 	= "100000";				// 금액 합산
	reservationInfo.msLevel 	= msLevel;
	reservationInfo.userMail 	= msEmail;
	reservationInfo.msId 		= msId;
	reservationInfo.userName 	= msName;
	reservationInfo.phone 		= msPhone;	
	
	var params = {
		"key"      	  : '${sessionScope.msMember.msNum}'
		, "serviceId" : "<%=Globals.serviceId%>"
		, "amount"    : amount
		, "returnUrl" : "<%=Globals.returnPayUrl%>"
		, "itemCode"  : "0004"
		, "itemName"  : "예약"
		, "userId"    : msId
		, "userName"  : msName
		, "userMail"  : msEmail
		, "reserved1" : $('#coDiv').val()
		, "reserved2" : "RESERVATION"
		, "reserved3" : JSON.stringify(reservationInfo)
		, "protocol"  : "<%=Globals.protocolType%>"
	}

	mPay.action(params, function() {
		// 결제 성공했을때 이동할 페이지 
		
		/*
		var sUrl = "<c:url value='/hills/member/setLogin.do'/>";
		
		mAjax3(sUrl, {"msNum" : "<c:out value='${sessionScope.msMember.msNum}'/>"}, "POST", true, function(data) {
			location.href = "<c:url value='/hills/reservation/reservationCheck.do'/>";	
		});*/
		
		alert("예약 선결제 성공");
	}, function() {
		// 결제 실패했을때 .. 
	});
}

// 결제하기 클릭
function doPay(amount, bkTime) {

	var reservationInfo 		= {};
	reservationInfo.coDiv 		= $('#coDiv').val();
	reservationInfo.bayCondi 	= $('#bayCondi').val();
	reservationInfo.bkDay 		= $('#bkDay').val();
	reservationInfo.bkTime 		= bkTime;
	reservationInfo.bkAmount 	= amount;
	reservationInfo.msLevel		= msLevel;
	reservationInfo.userMail 	= msEmail;
	reservationInfo.msId 		= msId;
	reservationInfo.userName 	= msName;
	reservationInfo.phone 		= '${sessionScope.msMember.msPhone}';

	// 예약가능여부 조회 	
	var result = fnChkBook(reservationInfo);
	
	setTimeout(function() {
		
		if (result.code == '0000') {
	
			// 예약가능한 경우 결제창 띄우기 
			var params = {
				"key"      	  : '${sessionScope.msMember.msNum}'
				, "serviceId" : "<%=Globals.serviceId%>"
				, "amount"    : amount
				, "returnUrl" : "<%=Globals.returnPayUrl%>"
				, "itemCode"  : "0004"
				, "itemName"  : "예약"
				, "userId"    : msId
				, "userName"  : msName
				, "userMail"  : msEmail
				, "reserved1" : $('#coDiv').val()
				, "reserved2" : "RESERVATION"
				, "reserved3" : JSON.stringify(reservationInfo)
				, "protocol"  : "<%=Globals.protocolType%>"
			}
		
			mPay.action(params, function() {
				// 결제 성공했을때 이동할 페이지 
				
				/*
				var sUrl = "<c:url value='/hills/member/setLogin.do'/>";
				
				mAjax3(sUrl, {"msNum" : "<c:out value='${sessionScope.msMember.msNum}'/>"}, "POST", true, function(data) {
					location.href = "<c:url value='/hills/reservation/reservationCheck.do'/>";	
				});*/
				
				alert("예약 선결제 성공");
			}, function() {
				// 결제 실패했을때 예약 선점된거 풀기 
				fnUnBkMark(reservationInfo);			
			});    	 			
		
		} else if (result.code == '9999') {
			alert(result.message);
			location.reload();
		}
	}, 300);
}

// 예약가능여부 조회
// 1. 회원위약체크
// 2. 동시에 결제버튼 누를 수 있기 때문에 예약 가능한지 한 번 더 체크 + 예약 가능하다면  예약 선점 처리	
function fnChkBook(reservationInfo) {
	var result = new Object() ;

	$.ajax({
		url: "/book/chkBook"
		, type: "post"
		, dataType: 'json'
		, data: reservationInfo
		, contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
		, success: function(data) {
			result.code = data.code;
			result.message = data.message;
		}
	});
	
	return result;
}

// 예약 선점된거 풀기 
function fnUnBkMark(reservationInfo) {
	var result = new Object();

	$.ajax({
		url: "/book/unBkMark"
		, type: "post"
		, dataType: 'json'
		, data: reservationInfo
		, contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
		, success: function(result) {
			console.log(result);
			if (result.code == '0000'){
				location.reload();
			} else if (result.code == '9999') {
				//
			}
		}
	});
	
	return result;
}

// 선택한 베이에 대한 예약가능시간 조회 
function selectTime() {

	$.ajax({
        url: "/book/bookAvailableTime"
        , type: "post"
       	, dataType: 'json'
      	, data: { 
           	"coDiv" 		: $('#coDiv').val() 
           	, "msLevel" 	: msLevel
           	, "bayCondi" 	: $('#bayCondi').val()
           	, "bkDay" 		: $('#bkDay').val()
    	}
        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
        , success: function(data) {	

			divCnt = "";
			if (data != null && data.length > 0) {
				for (let i=0; i< data.length; i++) {
					divCnt += "<div class='time' onClick='costInfo(\""+data[i].bkTime+"\")'>" + data[i].bkTime;
					
					if (data[i].bkRemCount > 0){
						divCnt += "<br><span style='color:red;font-size:12px'>" + data[i].bkRemCount + "개 남음</span>";
					} else {
						divCnt += "<br><span style='color:red;font-size:12px'>마감</span>";
					}
					divCnt += "</div>";
				}
				document.querySelector(".availableTime").innerHTML = divCnt;
			}
		}
	});		
}

// 선택한 시간에 대한 요금 조회
function costInfo(bkTime) {
	$.ajax({
        url: "/cost/getCostInfo"
        , type: "post"
        , dataType: 'json'
        , data: { 
        	"coDiv" : $('#coDiv').val() 
        	, "msLevel" : '${sessionScope.msMember.msEmail}'
        	, "bayCondi" : $('#bayCondi').val()
        	, "bkDay" : $('#bkDay').val()
        	, "bkTime" : bkTime
        }
        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
        , success: function(data) {
        	
        	divCnt = "";
    		if (data.DR_AMOUNT != null && data.DR_AMOUNT > 0) {
    			
    			divCnt += "결제금액 : " + data.DR_AMOUNT + "원";
    			divCnt += "&nbsp;<input type='button' value='결제' onClick='doPay("+data.DR_AMOUNT+", \""+bkTime+"\")'/>";
        		document.querySelector(".costInfo").innerHTML = divCnt;
        		 
    		} else {
    			// 세팅된 요금정보 없음
    		}
        }
	});
}
</script>
<style>
.time {
	background-color:#f9e7da;padding:5px;border-radius:5px;margin-right:5px;width:80px;text-align:center;float:left
}
</style>
</body>
</html>