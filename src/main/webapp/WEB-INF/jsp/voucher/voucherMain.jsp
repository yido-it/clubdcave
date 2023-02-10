<!-- 
기능 : 이용권 (이용권구매 / 이용권보유내역)

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
	<a href="javascript:history.back(-1)" class="header-title header-subtitle">이용권</a>
	<jsp:include page="../common/top.jsp" />
	</div>

	<!-- 좌측GNB-->
	<jsp:include page="../common/menu.jsp" />
	<!-- //좌측GNB-->
	
	<div class="page-content header-clear-medium">
	
	
	<div class="content">
		<!-- 탭선택 -->
		<div class="tab-controls tabs-round tab-animated tabs-medium tabs-rounded shadow-xl" data-tab-items="2" data-tab-active="bg-green-dark color-white">
			<a href="#" data-tab-active="" data-tab="tab-1" class="bg-green-dark color-white no-click font-15" style="width: 50%;">
				이용권구매
			</a>
			
			<a href="#" data-tab="tab-2" style="width: 50%;" class="font-15">
				이용권보유내역
			</a>
		</div>
		<div class="clearfix mb-3"></div>
		<!--//탭선택 -->
	
		<!-- 이용권구매 -->
		<div class="tab-content" id="tab-1" style="display: block;">
			<c:forEach items="${vocList}" var="voc" varStatus="status">
				<div class="d-flex <c:if test="${status.index == 0}"> mt-5 </c:if>" >
					<div>
						<h2 class="font-14 mb-0 line-height-m font-500">${voc.vcName} </h2>       
						<p class="opacity-50 font-11"><i class="fa-regular fa-clock"></i>유효기간: 구매일로부터 ${voc.vcMonth}개월</p>
					</div>
					<div class="ml-auto pl-3 text-right mt-2">
						<h5><fmt:formatNumber value="${voc.vcAmount}" pattern="#,###" />원</h5>                
					</div>
					
					<button class="btn btn-primary btn-xs bg-blue-dark ml-1" onClick="doPay('${voc.vcCd}')">
						구매
					</button>
				</div>
				<div class="divider mt-3 mb-3"></div>
			</c:forEach>
		</div>
		<!-- // 이용권구매 -->
	
		<!-- 이용권보유내역 -->
		<div class="tab-content" id="tab-2">
			<div class="menu-title">
				<h3 class="my-0 py-0">보유내역</h3>
				<div class="float-right  mt-n4">
					<!-- 검색 버튼 -->
					<a href="" data-menu="modal_day" class="color-white"><i class="fa-solid fa-calendar-days"></i></a>
				</div>
			</div>
			<div class="divider mt-3 mb-3"></div>
			
			<!-- ┌──────────────────────── 구매내역 ────────────────────────┐-->
			<div class="accordion" id="accordion-1"></div>
			<!-- └──────────────────────── 구매내역 ────────────────────────┘-->
			
			<a href="#" onClick="doSearchList('more')" class="btn btn-border btn-m btn-full mt-4 mb-10 rounded-xl text-uppercase font-900 border-blue-dark color-blue-dark ml-4 mr-4" id="btnMore">
	        	더보기 <i class="fa-solid fa-chevron-down"></i>
	        </a>
	        
		</div>
		<!-- // 이용권보유내역 -->
	</div>
	<!-- Page content ends here-->  

<!--  content ends -->   
</div>   
</div>

<!-- 내역조회 모달 -->
<div id="modal_day" class="menu menu-box-modal rounded-0"  style="background-color: #303030;z-index:99999;padding: 15px 0;"
	data-menu-height="auto" 
	data-menu-width="330"
	data-menu-effect="menu-parallax" style="padding:20px">
	<!-- <h3 class="text-center mt-3 font-600">예약내역 기간조회</h3> -->
	<h3 class="ml-3 mt-3">이용권 내역조회</h3>
		<div class="row mb-0 mr-1 ml-1 mt-2">
	
	<!-- ======================================= -->
		<div class="col-6"> 
			<div class="form-check icon-check">
				<input class="form-check-input" type="radio" name="srchUseYn" value="A" id="srchUseYn1">
				<label class="form-check-label" for="srchUseYn1">전체보기</label>
				<i class="icon-check-1 fa fa-circle color-gray-dark font-16"></i>
				<i class="icon-check-2 fa fa-check-circle font-16 color-highlight"></i>
			</div>
			<div class="form-check icon-check">
				<input class="form-check-input" type="radio" name="srchUseYn" value="N" id="srchUseYn2" checked>
				<label class="form-check-label" for="srchUseYn2">사용중</label>
				<i class="icon-check-1 fa fa-circle color-gray-dark font-16"></i>
				<i class="icon-check-2 fa fa-check-circle font-16 color-highlight"></i>
			</div> 
		</div>
		<div class="col-6"> 
			<div class="form-check icon-check">
				<input class="form-check-input" type="radio" name="srchUseYn" value="Y" id="srchUseYn3">
				<label class="form-check-label" for="srchUseYn3">사용완료</label>
				<i class="icon-check-1 fa fa-circle color-gray-dark font-16"></i>
				<i class="icon-check-2 fa fa-check-circle font-16 color-highlight"></i>
			</div>
		</div>
		
	</div>
		<div class="divider mb-3 mt-3"></div>
	 <h3 class="ml-3 mt-4">구매일자 조회</h3>
	<div class="row mb-0 mr-1 ml-1 mt-2">
		<div class="col-6"> 
			<div class="form-check icon-check">
				<input class="form-check-input" type="radio" name="srchPeriod" value="1" id="srchPeriod1" onclick='search(this.value)'>
				<label class="form-check-label" for="srchPeriod1">최근 1개월</label>
				<i class="icon-check-1 fa fa-circle color-gray-dark font-16"></i>
				<i class="icon-check-2 fa fa-check-circle font-16 color-highlight"></i>
			</div>
			<div class="form-check icon-check">
				<input class="form-check-input" type="radio" name="srchPeriod" value="12" id="srchPeriod2" onclick='search(this.value)'>
				<label class="form-check-label" for="srchPeriod2">최근 1년</label>
				<i class="icon-check-1 fa fa-circle color-gray-dark font-16"></i>
				<i class="icon-check-2 fa fa-check-circle font-16 color-highlight"></i>
			</div> 
		</div>
		<div class="col-6"> 
			<div class="form-check icon-check">
				<input class="form-check-input" type="radio" name="srchPeriod" value="3" id="srchPeriod3" onclick='search(this.value)'>
				<label class="form-check-label" for="srchPeriod3">최근 3개월</label>
				<i class="icon-check-1 fa fa-circle color-gray-dark font-16"></i>
				<i class="icon-check-2 fa fa-check-circle font-16 color-highlight"></i>
			</div>
			<div class="form-check icon-check">
				<input class="form-check-input" type="radio" name="srchPeriod" value="0" id="srchPeriod4" onclick='search(this.value)'>
				<label class="form-check-label" for="srchPeriod4">직접설정</label>
				<i class="icon-check-1 fa fa-circle color-gray-dark font-16"></i>
				<i class="icon-check-2 fa fa-check-circle font-16 color-highlight"></i>
			</div> 
		</div>
	</div>

	<!--기간달력선택 -->
	<div class="row mt-3 mb-0" style="padding:10px;position:relative;" id="calendar">
		<div class="col-6" class=""> 
			<div class="input-style input-style-2">
				<span style="border-radius: 5px;" class="input-style-1-active input-style-1-inactive">시작일</span>
				<input type="text" name="strtDt" id="strtDt">
			</div>
		</div>
		<span style="position: absolute;left: 50%;top: 20px;">~</span>
		<div class="col-6"> 
			<div class="input-style input-style-2">
				<span style="border-radius: 5px;" class="input-style-1-active input-style-1-inactive">종료일</span>
				<input type="text" name="endDt" id="endDt">
			</div>
		
		</div>
	</div>
	<!--//기간달력선택-->
	
	<!-- 조회 버튼 -->
	<div class="col-12 mt-3">
		<a href="#" onclick="doSearchList('search')" class="btn btn-full btn-md bg-blue-dark font-800 text-uppercase rounded-s">
			조회
		</a>
	</div>
</div>
<!--//내역조회 모달-->

<!-- 모달-->
<jsp:include page="../common/pop/modal.jsp" />

<!-- 하단바 -->
<jsp:include page="../common/footerBar.jsp" />

</body>

<jsp:include page="../common/alertModal.jsp" />  

<script type="text/javascript">

var listSize	= 0;	
var msId 		= "<c:out value='${sessionScope.msMember.msId}'/>";
var coDiv 		= "<c:out value='${coDiv}'/>";
var saleSeq		= ""; // 취소할때 필요한 매출순번
var drSerialNo	= ""; // 취소할때 필요한 이용권 매출 고유번호

$(document).ready(function() {
	if (msId == null || msId == "") {
		// alertModal.fail('로그인 후 이용 가능합니다.');
		location.href = "/login";	
		return;
	}
	
	init();
	doSearchList('search');
	

});

function init() {

    $('#strtDt, #endDt').datepicker({
        dateFormat: 'yy-mm-dd',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        yearSuffix: '년'     	
    });
    
	$('#strtDt').val(searchDate('1'));		// 1달전 날짜
	$('#endDt').val(getToDay("-"));			// 오늘 날짜
}

// 날짜 검색 > 조회 개월 클릭했을때 실행되는 함수 
function search(value) {

	switch(value) {
		case '1' :
			// 1개월전
			$('#strtDt').val(searchDate('1'));		// 1달전 날짜
			$('#endDt').val(getToDay("-"));			// 오늘 날짜
			break;
		case '3' : 
			// 3개월전
			$('#strtDt').val(searchDate('3'));		// 3달전 날짜
			$('#endDt').val(getToDay("-"));			// 오늘 날짜
			break;
		case '12': 
			// 1년전
			$('#strtDt').val(searchDate('12'));		// 3달전 날짜
			$('#endDt').val(getToDay("-"));			// 오늘 날짜
			break;
		case '0' :
			// 직접설정 
			$('#strtDt').val(getToDay("-"));		// 오늘 날짜
			$('#endDt').val(getToDay("-"));			// 오늘 날짜
			break;
	}
}

// 이용권 사용내역 조회 
function doSearch(saleDay, saleSeq, vcCd, idx) {
	
	$.ajax({
        url: "/voucher/useList"
        , type: "post"
       	, dataType: 'json'
      	, data: { 
           	"saleDay" 	: saleDay 
           	, "saleSeq" : saleSeq
           	, "vcCd" 	: vcCd
    	}
        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
        , success: function(data) {	
        	
			if (data != null && data.length > 0) {
				var divCnt = '';
				
				for (let i=0; i<data.length; i++) {
					divCnt += '<div class="d-flex mt-2 mb-2 content">';
					divCnt += '<div>';
					divCnt += '<h2 class="font-13 mb-0 line-height-m font-500">' + data[i].BAY_NAME + '</h2>'; 
					divCnt += '</div>';
					divCnt += '<div class="ml-auto mt-1 pl-3 text-right">';
					divCnt += '<p class="mb-0 opacity-50 font-11">예약: '+data[i].BK_DAY + ' / ' + data[i].BK_TIME +'</p>';    
					divCnt += '</div>';
					divCnt += '</div>';
				}
				document.querySelector(".useList_"+idx).innerHTML = divCnt;
			} else {
				divCnt = '<p class="mb-0 opacity-50 font-11" style="text-align:center">사용전입니다.</p>'; 
				document.querySelector(".useList_"+idx).innerHTML = divCnt;
			}
        }
	});
}

// 이용권 결제 페이지 이동 
function doPay(vcCd) {
	location.href='/voucher/voucherPay/' + vcCd;
}

// 이용권 결제 취소 
function doCancel() {

   	$.ajax({
   		url: "/voucher/cancel/"
   		, type: "post"
   		, dataType: 'json'
   		, data: { 
   			"coDiv"			: coDiv
   			, "saleSeq" 	: saleSeq 
   			, "drSerialNo" 	: drSerialNo
   		}
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

//검색 
//type > 더보기 : more , 조회 : search
function doSearchList(type) {
	
	if (type == "search") {
		listSize = 0;
	} 
	
	var srchPeriod = "";
	if (document.querySelector('input[name="srchPeriod"]:checked') != null) {
		srchPeriod = document.querySelector('input[name="srchPeriod"]:checked').value;
	}
	
	var srchUseYn = document.querySelector('input[name="srchUseYn"]:checked').value;

	$.ajax({
     url: "/voucher/vouSaleList/" + coDiv
     , type: "post"
    	, dataType: 'json'
   	, data: { 
        	"listSize" : listSize
        	, "strtDt" : $('#strtDt').val()
        	, "endDt" : $('#endDt').val()
        	, "srchUseYn" : srchUseYn
        	, "srchPeriod" : srchPeriod
 	}
     , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
     , success: function(data) {	
			if (data != null && data.length > 0) {
				var divCnt = '';
				for (let i=0; i<data.length; i++) {
	
					var lastIdx = Number(listSize);
					var toDay = getStringDt2(data[i].VC_TO_DAY, '-');
					var saleDay = getStringDt2(data[i].SALE_DAY, '-');
				
					var isCancel = false;
					if (data[i].VC_LIMIT_CNT == data[i].VC_REM_CNT) {
						isCancel = true;	// 사용전이므로 [구매취소] 가능  
					} 
					
					divCnt += '<input type="hidden" id="saleSeq_'+(lastIdx+i)+'" value="'+data[i].SALE_SEQ+'"/>';
					divCnt += '<input type="hidden" id="drSerialNo'+(lastIdx+i)+'" value="'+data[i].DR_SERIAL_NO+'"/>';
					divCnt += '<div class="mb-0" id=saleList_'+(lastIdx+i)+'>';
				
					if (isCancel) {
						// 사용전이므로 [구매취소] 가능  
						divCnt += '<button class="btn accordion-btn border-0 color-theme font-14 accodion-padding">';
					} else {
						// [구매취소] 불가 + 상세내역 조회 
						divCnt += '<button class="btn accordion-btn border-0 color-theme font-14 accodion-padding"';
						divCnt += '			onClick="doSearch(\''+data[i].SALE_DAY+'\', '+data[i].SALE_SEQ+', \''+data[i].VC_CD+'\', '+(lastIdx+i)+')"';
						divCnt += '			data-toggle="collapse" data-target="#collapse'+i+'">';						
					}

					divCnt += data[i].VC_NAME ;
					divCnt += '<span class="color-blue-dark ml-1"> 잔여 '+data[i].VC_REM_CNT+'매</span>';
					
					if (isCancel) {
						// 구매취소 
						divCnt += '<a href="javascript:void(0);" onClick="showModal('+(lastIdx+i)+')" class="fr btn btn-xs btn_accodion_voucher rounded-0 font-900 border-red-dark color-red-dark"';
						divCnt += '		style="border-bottom : 1px solid #DA4453 !important">';
						divCnt += '취소';
						divCnt += '</a>';
					} else {
						divCnt += '<i class="fa fa-chevron-down font-10 accordion-icon"></i>';
					}
					

					divCnt += '<p class="opacity-50 font-11 mt-1 mb-1"><i class="fa-regular fa-clock"></i>유효기간: '+toDay+'</p>'; 
					divCnt += '<p class="opacity-50 font-11"><i class="fa-regular fa-calendar"></i></i>결제일자: '+saleDay+'</p>';	 					        

					divCnt += '</button>';
					
					// 사용내역 
					divCnt += '<div id="collapse'+(lastIdx+i)+'" class="collapse useList_'+(lastIdx+i)+'" data-parent="#accordion-1"></div>';
					divCnt += '</div>';
				}
				
				if (listSize == 0) {
				 	document.querySelector("#accordion-1").innerHTML = divCnt;
				} else {
					var tmp = listSize - 1;
					var sTmp = String(tmp);
					$("#saleList_"+ sTmp).after(divCnt);
				}
				listSize += data.length;
				
				if (data.length < 10) {
					$('#btnMore').text("더이상 조회할 내역이 없습니다.");
					document.getElementById("btnMore").setAttribute("href", "#");
				}
			} else {
				if (type == "search") {
					// 조회 결과 없을때 
					$('#btnMore').css('display','none');
					$('#accordion-1').css('text-align','center');
					document.querySelector("#accordion-1").innerHTML = "검색 결과가 없습니다.";
				} else {
					// 더보기 결과 없을때
					$('#btnMore').text("더이상 조회할 내역이 없습니다.");
					document.getElementById("btnMore").setAttribute("href", "#");
				}
			}
		}
	});
	
	if (type == "search") {
		// 조회 팝업 닫기 
		$('#modal_day').removeClass('menu-active');
		$('.menu-hider').removeClass('menu-active menu-active-clear');
		$('.header').css('transform','translate(0,0)');
		$('.page-content').css('transform','translate(0,0)');
		$('.menu-hider').css('transform','translate(0,0)');
	}
}

// 이용권 취소 모달창 표출
function showModal(idx){
	
	saleSeq 	= $('#saleSeq_'+idx).val();		// 취소할때 필요한 매출순번	
	drSerialNo	= $('#drSerialNo'+idx).val();	// 취소할때 필요한 이용권 매출 고유번호
	
	$('#voucher_cancle').addClass('menu-active');
	$('.menu-hider').addClass('menu-active');
	
}

</script>
</html>