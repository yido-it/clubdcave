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
	
	<div style="width:100%;float:left;margin-top:10px;border-top:1px solid #dddddd; padding-top:10px">
	<!-- 예약선점 -->
		<input type="button" value="1/15 8시 예약선점" onClick="doTimeSelect()"/>
	<!-- 예약선점해제 -->
		<input type="button" value="1/15 8시 예약선점해제" onClick="doTimeUnSelect()"/>
	</div>

</div>

<script type="text/javascript">

var msId 			= "<c:out value='${sessionScope.msMember.msId}'/>";
var msNum			= "<c:out value='${sessionScope.msMember.msNum}'/>";
var msLevel 		= "<c:out value='${sessionScope.msMember.msLevel}'/>";
var msEmail 		= "<c:out value='${sessionScope.msMember.msEmail}'/>";
var msName 			= "<c:out value='${sessionScope.msMember.msName}'/>";
var msFirstPhone1	= "<c:out value='${sessionScope.msMember.msFirstPhone1}'/>";
var msMidPhone1 	= "<c:out value='${sessionScope.msMember.msMidPhone1}'/>";
var msLastPhone1 	= "<c:out value='${sessionScope.msMember.msLastPhone1}'/>";
var msPhone 		= msFirstPhone1 + "-" + msMidPhone1 + "-" + msLastPhone1;

var result = new Object() ;

var reservationInfo 		= {};
reservationInfo.coDiv 		= $('#coDiv').val();
reservationInfo.bayCondi 	= $('#bayCondi').val();
reservationInfo.bkDay 		= $('#bkDay').val();
reservationInfo.msLevel		= msLevel;
reservationInfo.userMail 	= msEmail;
reservationInfo.msId 		= msId;
reservationInfo.userName 	= msName;
reservationInfo.phone 		= msPhone;

$(document).ready(function() {
	
	if (msId == null || msId == "") {
		alert("로그인 후 이용 가능합니다.");
		location.href = "/login";	
		return;
	}
	
});

// 시간선택 + 예약선점
// 예약마감 체크해서 마감이면 팝업창 띄워주고 마감으로 변경해주기 
function doTimeSelect() {

	reservationInfo.bkTime 		= "0800";
	reservationInfo.bkAmount 	= 50000;
	
	$.ajax({
		url: "/book/chkBook"
		, type: "post"
		, dataType: 'json'
		, data: reservationInfo
		, contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
		, success: function(result) {
			
			if (result.code == '9999') {
				alert(result.message);

				// ★ 해당시간 '마감'으로 바꿔주고, 선택못하도록 변경 
			}
		}
	});
	
	return result;
}

// 시간선택해제 + 예약선점해제 
function doTimeUnSelect() {

	reservationInfo.bkTime 		= "0800";
	reservationInfo.bkAmount 	= 50000;
	
	$.ajax({
		url: "/book/unBkMark"
		, type: "post"
		, dataType: 'json'
		, data: reservationInfo
		, contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
		, success: function(data) {
			
			// ★ 해제 완료되면 화면에 '수량' 복원 해주어야함 (마감인 경우도 체크??)
		}
	});
	
	return result;
}

// 다중결제 테스트 
function doPayTest() {
	
	var amount = 100000;

	// 시간 클릭할때 bookData 에 금액, 시간 넣어주기 
	var bookData = new Object();		
	var bookData2 = new Object();		
	var bkList = new Array();			
	
	bookData.bkTime = "08:00".replace(":", "");
	bookData.amount = 50000;
	bkList.push(bookData);
	
	bookData2.bkTime = "13:00".replace(":", "");
	bookData2.amount = 50000;
	bkList.push(bookData2);
	// end.
	
	reservationInfo.bkAmount 	= amount;				// 금액 합산
	reservationInfo.bkList		= bkList;	// 시간, 금액 리스트 
	
	console.log('doPayTest > reservationInfo: ', reservationInfo);
	// 예약가능여부 조회 	()
	var result = fnChkBook(reservationInfo);
	
	setTimeout(function() {
		
		if (result.code == '0000') {
	
			// 예약가능한 경우 결제창 띄우기 
					
			var params = {
				"key"      	  : msNum
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
	});
}

// 결제하기 클릭
function doPay(amount, bkTime) {

	// 예약가능여부 조회 	(회원위약)
	var result = fnChkBook(reservationInfo);
	
	setTimeout(function() {
		
		if (result.code == '0000') {
	
			// 예약가능한 경우 결제창 띄우기 
			var params = {
				"key"      	  : msNum
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

// 예약가능여부 조회 (회원위약체크)
function fnChkGrant(reservationInfo) {
	var result = new Object() ;

	$.ajax({
		url: "/book/chkGrant"
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

// 선택한 시간에 대한 요금 조회 + 예약선점 
function costInfo(bkTime) {
	$.ajax({
        url: "/cost/getCostInfo"
        , type: "post"
        , dataType: 'json'
        , data: { 
        	"coDiv" : $('#coDiv').val() 
        	, "msLevel" : msLevel
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