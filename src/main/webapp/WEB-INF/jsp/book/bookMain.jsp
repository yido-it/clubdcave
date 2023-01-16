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
        <a href="index.html" class="header-title header-subtitle">예약</a>
		<jsp:include page="../common/top.jsp" />
    </div>
    
    <!-------- 좌측GNB--------->

        
<jsp:include page="../common/menu.jsp" />
<!-- //좌측GNB-->

   
    <div class="page-content header-clear-medium">
         

      <div class="content">
        <h3 class="mb-3">클럽디청담 예약 </h3> 
        <div class="input-style input-style-2 input-required" data-menu="select_bay">
            <span class="color-highlight input-style-1-inactive">베이선택</span>
            <em><i class="fa fa-chevron-down color-green-dark"></i></em> 
            <input class="form-control" type="text" placeholder="">
        </div>
         


        <div class="calendar">
            <div class="cal-header">
                <h4 class="cal-title text-center text-uppercase font-800 bg-highlight color-white">2023년 1월</h4>
                <h6 class="cal-title-left color-white"><i class="fa fa-chevron-left"></i></h6>
                <h6 class="cal-title-right color-white"><i class="fa fa-chevron-right"></i></h6>
            </div>
            <div class="clearfix"></div>
            <div class="cal-days bg-highlight opacity-80 bottom-0">
                <a href="#">일</a>
                <a href="#">월</a>
                <a href="#">화</a>
                <a href="#">수</a>
                <a href="#">목</a>
                <a href="#">금</a>
                <a href="#">토</a>
                <div class="clearfix"></div>
            </div>
            <div class="cal-dates cal-dates-border">
                <a href="#" class="cal-disabled">25</a>
                <a href="#" class="cal-disabled">26</a>
                <a href="#" class="cal-disabled">27</a>
                <a href="#" class="cal-disabled">28</a>
                <a href="#" class="cal-disabled">29</a>
                <a href="#" class="cal-disabled">30</a>
                <a href="#">1</a>
                <a href="#">2</a>
                <a href="#">3</a>
                <a href="#">4</a>
                <a href="#">5</a>
                <a href="#">6</a>
                <a href="#">7</a>
                <a href="#">8</a>
                <a href="#">9</a>
                <a href="#">10</a>
                <a href="#">11</a>
                <a href="#">12</a>
                <a href="#" class="cal-selected"><i class="fa fa-square color-highlight"></i><span>13</span></a>
                <a href="#">14</a>
                <a href="#">15</a>
                <a href="#">16</a>
                <a href="#">17</a>
                <a href="#">18</a>
                <a href="#">19</a>
                <a href="#">20</a>
                <a href="#">21</a>
                <a href="#">22</a>
                <a href="#">23</a>
                <a href="#">24</a>
                <a href="#">25</a>
                <a href="#">26</a>
                <a href="#">27</a>
                <a href="#">28</a>
                <a href="#">29</a>
                <a href="#">30</a>
                <a href="#">31</a>
                <a href="#" class="cal-disabled">1</a>
                <a href="#" class="cal-disabled">2</a>
                <a href="#" class="cal-disabled">3</a>
                <a href="#" class="cal-disabled">4</a>
                <a href="#" class="cal-disabled">5</a>
                <div class="clearfix"></div>
            </div>
        </div>


        <div class="calendar bg-theme mb-4 mt-3">
          
            <div class="col-sm-12" style="background-color: #333333;padding: 0px 10px 10px 10px;">
                <div class="reservation_time"> 
                    <h5>2023년 01월 13일 - 시간선택</h5>
                    <div class="btn-group" data-toggle="buttons" style="margin-top: -1px;">
                        <label class="bkTime btn btn-secondary" onclick="">
                            <input type="checkbox" autocomplete="off">09:00<br/><span>1개 가능</span>
                        </label>
                        <label class="bkTime btn btn-secondary" onclick="">
                            <input type="checkbox" autocomplete="off">10:00<br/><span>1개 가능</span>
                        </label>
                        <label class="bkTime btn btn-secondary btn-lock" onclick="" >
                            <input type="checkbox" autocomplete="off" disabled="disabled">11:00<br/><span>마감</span>
                        </label>
                        <label class="bkTime btn btn-secondary btn-lock" onclick="">
                            <input type="checkbox" autocomplete="off" disabled="disabled">12:00<br/><span>마감</span>
                        </label>
                    </div>
                    <div class="btn-group" data-toggle="buttons" style="margin-top: -1px;">
                        <label class="bkTime btn btn-secondary" onclick="">
                            <input type="checkbox" autocomplete="off">13:00<br/><span>1개 가능</span>
                        </label>
                        <label class="bkTime btn btn-secondary btn-lock" onclick="">
                            <input type="checkbox" autocomplete="off" disabled="disabled">14:00<br/><span>마감</span>
                        </label>
                        <label class="bkTime btn btn-secondary" onclick="">
                            <input type="checkbox" autocomplete="off">15:00<br/><span>1개 가능</span>
                        </label>
                        <label class="bkTime btn btn-secondary btn-lock" onclick="">
                            <input type="checkbox" autocomplete="off" disabled="disabled">16:00<br/><span>마감</span>
                        </label> 
                    </div>
                    <div class="btn-group" data-toggle="buttons" style="margin-top: -1px;">
                        <label class="bkTime btn btn-secondary" onclick="">
                            <input type="checkbox" autocomplete="off">17:00<br/><span>1개 가능</span>
                        </label>
                        <label class="bkTime btn btn-secondary btn-lock" onclick="">
                            <input type="checkbox" autocomplete="off" disabled="disabled">18:00<br/><span>마감</span>
                        </label>
                        <label class="bkTime btn btn-secondary" onclick="">
                            <input type="checkbox" autocomplete="off">19:00<br/><span>1개 가능</span>
                        </label>
                        <label class="bkTime btn btn-secondary" onclick="">
                            <input type="checkbox" autocomplete="off">20:00<br/><span>1개 가능</span>
                        </label>
                    
                    </div>
                </div>
                
            <div> 
        </div>
 
 </div>
        </div>

   
        <a href="javascript:doPay()"  data-menu=""  class="mt-1 mb-4 btn btn-md bg-blue-dark btn-full shadow-xl text-uppercase font-800 rounded-s">
            예약하기</a> 
       </div>
    <!--  content ends -->   
</div>   

        <!--   Global Footer-->
		<jsp:include page="../common/footerBar.jsp" />
		
</div>


<!--베이선택 팝업-->
<div id="select_bay" class="menu menu-box-bottom rounded-0 modal_bay" data-menu-height="435" data-menu-effect="menu-parallax" style="display: block; height: 335px;">
    <div class="menu-title">
        <h1 class="my-0 py-0">베이선택</h1>
        <a href="#" class="close-menu"><i class="fa fa-times-circle"></i></a>
    </div>
    <div class="mr-3 ml-3 mt-3">
        <div class="list-group list-custom-small">
        
       	<c:forEach items="${bayList}" var="bay">
       	     <a href="#" class="close-menu">
                <img class="mr-3 mt-2" src="">
                <span>${bay.bayName}</span><i class="fa fa-angle-right"></i>
            </a>
       	</c:forEach>
        

         
        </div>
        <div class="clear"></div>
    </div>
</div>
<!--//베이선택 팝업-->

<!--예약확인팝업-->
<div id="modal_reservation" class="menu menu-box-bottom rounded-0" style="height:101%;border-radius: 18px !important;" data-menu-effect="menu-parallax" style="display: block; height: 335px;">
    <div class="menu-title">
        <h1 class="my-0 py-0">예약정보확인</h1>
        <a href="#" class="close-menu"><i class="fa fa-times-circle"></i></a>
    </div>
    <div class="divider"></div>
    <div class="page-content">
         
        <div class="content">
            <div class="row mb-3 mt-4">
                <h5 class="col-4 text-left font-15">예약명</h5>
                <h5 class="col-8 text-right font-14 opacity-60">1F 오픈타석</h5>
                <h5 class="col-4 text-left font-15">이용시간</h5>
                <h5 class="col-8 text-right font-14 opacity-60">10:00(1H)), 13:00(2H)</h5>
                <h5 class="col-4 text-left font-15">방문날짜</h5>
                <h5 class="col-8 text-right font-14 opacity-60">2023년 01월 04일</h5>
                <h5 class="col-4 text-left font-15">예약장소</h5>
                <h5 class="col-8 text-right font-14 opacity-60 ">서울시 청담동 클럽디청담</h5>

          
            </div>
            <div class="divider"></div>

            <div class="">
                <h4 class="font-700">이용권 사용</h4>
                <div class="input-style input-style-2 input-required mt-3">
                    <span class="input-style-1-active input-style-1-inactive">이용권선택</span>
                    <em><i class="fa fa-check color-green-dark"></i></em>
                    <select class="form-control">         
                        <option value="e">선택해주세요</option>       
                        <option value="a">이용권1</option>
                        <option value="b">이용권2</option>
                    </select>
                </div> 
               </div>

               <div class="">
                <div class="d-flex">
                    <div>
                        <h1 class="font-14 mb-0 line-height-m font-500">사용된 이용권</h1>
                    </div>
                    <div class="ml-auto pl-3 text-right">
                        <span class="badge bg-blue-dark color-white font-12 font-400">이용권1(50,000원)</span>
                        <br/>
                        <span class="badge bg-blue-dark color-white font-12 font-400">이용권1(50,000원)</span>
                   
                    </div>
                </div> 
            </div>
 
            <div class="divider mt-4"></div>
            <div class="row">
                <div class="col-6"><h6 class="font-16">결제금액</h6></div>
                <div class="col-6"><h6 class="font-16">150,000원</h6></div>
                <div class="w-100 pt-1"></div>
                <div class="col-6"><h6 class="font-16 color-blue-dark">이용권사용 </h6></div>
                <div class="col-6"><h6 class="font-16 color-blue-dark">-100,000원</h6></div>
                <div class="w-100 pt-2"></div>
                <div class="col-6"><h6 class="font-700 font-20">최종결제금액</h6></div>
                <div class="col-6"><h6 class="font-700 font-26">50,000원</h6></div>
            </div>
            <div class="divider"></div>
            <a data-menu="modal_pay" href=""  class="mt-1 mb-4 btn btn-md bg-blue-dark btn-full shadow-xl text-uppercase font-800 rounded-s">
                결제진행</a>
        </div> 
       
    </div> 
</div>

 
<!--결제동의 모달-->
<div id="modal_pay" class="menu menu-box-bottom rounded-0 " data-menu-height="530" data-menu-effect="menu-parallax" style="display: block; height: 510px;">
    <h1 class="text-center mt-4">
        <i class="fa fa-3x fa-check-circle scale-box color-blue-dark shadow-xl rounded-circle"></i> 
    </h1>
    <h3 class="text-center mt-3 font-700">환불규정 약관</h3>
    <p class="boxed-text-xl opacity-70">
        클럽디청담 결제에 대한 동의가 필요합니다.<br>
    </p>
    <div class="accordion" id="accordion-1">
       <div class="mb-0">
         
           <div>
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
           <input class="form-check-input" type="checkbox" value="" id="check1">
           <label class="form-check-label color-white" for="check1">약관에 동의합니다</label>
           <i class="icon-check-1 fa fa-square color-gray-dark font-16 ml-n1"></i>
           <i class="icon-check-2 fa fa-check-square font-16 color-highlight ml-n1"></i>
       </div>
      
   </div>
    <div class="row mb-0 mr-3 ml-3 mt-5 mb-5">
      
       <div class="col-12">
           <a href="payment_end" class="btn btn-full btn-md color-green-dark border-green-dark font-800 text-uppercase rounded-s">
            결제요청</a>
       </div>
    </div>
    </div>

</body>


<!--페이지 로드되자마자 베이선택팝업 활성화-->
<div class="menu-hider menu-active"><div></div></div>
<!--//페이지 로드되자마자 베이선택팝업 활성화-->


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
	// 페이지 로드되자마자 베이선택팝업 활성화
    $(".modal_bay").addClass('menu-active');
    
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

<!--각종알림-->
<div class="list-group list-custom-small list-icon-0">
    <a href="#" data-menu="timed-1" class="menu-active">
        <i class="fa font-17 fa-check-circle color-green-dark"></i>
        <span>성공이나 Ok</span>
        <i class="fa fa-angle-right"></i>
    </a>
    <a href="#" data-menu="timed-2" class="menu-active">
        <i class="fa font-17 fa-times-circle color-red-dark"></i>
        <span>실패나 에러</span>
        <i class="fa fa-angle-right"></i>
    </a>        
 
</div>


<!-- Page content ends here-->
       

<div id="timed-1" 
class="menu menu-box-modal rounded-m" 
data-menu-hide="1000"
data-menu-width="220"
data-menu-height="160">
<h1 class="text-center fa-5x mt-2 pt-3 pb-2"><i class="fa fa-check-circle color-green-dark"></i></h1>
<h3 class="text-center">저장성공</h3>
</div>

<div id="timed-2" 
class="menu menu-box-modal rounded-m" 
data-menu-hide="1000"
data-menu-width="220"
data-menu-height="160">
<h1 class="text-center fa-5x mt-2 pt-3 pb-2"><i class="fa fa-times-circle color-red-dark"></i></h1>
<h4 class="text-center">방문날짜를 선택하세요</h4>
</div>

</html>