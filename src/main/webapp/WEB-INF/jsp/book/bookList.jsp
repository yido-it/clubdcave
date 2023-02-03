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
            <div class="float-right  mt-n4">
                <!-- <div class="form-check icon-check">
                    <input class="form-check-input" type="checkbox" value="" id="check1" >
                    <label class="form-check-label font-15 ml-n1" for="check1">지난예약 보기</label>
                    <i class="icon-check-1 fa fa-square color-gray-dark font-16"></i>
                    <i class="icon-check-2 fa fa-check-square font-16 color-highlight"></i>
                </div> -->
                <a href="" data-menu="modal_day" class="page-title-icon shadow-xl bg-theme color-theme"><i class="fa-solid fa-calendar-days"></i></a>
            </div>
        </div>

		<!-- ┌──────────────────────── 예약카드 ────────────────────────┐-->
		<div id="bookList"></div>
		<!-- └──────────────────────── 예약카드 ────────────────────────┘-->

		 <a href="javascript:onClick=doSearch()" class="btn btn-border btn-m btn-full mb-10 rounded-xl text-uppercase font-900 border-blue-dark color-blue-dark ml-4 mr-4" id="btnMore">
        	더보기 <i class="fa-solid fa-chevron-down"></i>
        </a>
        
	</div>  

	<!--  content ends -->   
</div>   
    
<!-- 내역조회 모달 -->
<div id="modal_day" class="menu menu-box-modal rounded-0"  style="background-color: #303030;z-index:99999;padding: 15px 0;"
	data-menu-height="auto" 
	data-menu-width="330"
	data-menu-effect="menu-parallax" style="padding:20px">
	<h3 class="text-center mt-3 font-600">예약내역 기간조회</h3>
	<div class="divider mb-3 mt-3"></div>
	<div class="row mb-0 mr-1 ml-1 mt-2">
	
		<div class="col-6"> 
			<div class="form-check icon-check">
				<input class="form-check-input" type="radio" name="searchType" value="30" id="type1" onclick='search(this.value)' checked>
				<label class="form-check-label" for="type1">최근 1개월</label>
				<i class="icon-check-1 fa fa-circle color-gray-dark font-16"></i>
				<i class="icon-check-2 fa fa-check-circle font-16 color-highlight"></i>
			</div>
			<div class="form-check icon-check">
				<input class="form-check-input" type="radio" name="searchType" value="365" id="type2" onclick='search(this.value)'>
				<label class="form-check-label" for="type2">최근 1년</label>
				<i class="icon-check-1 fa fa-circle color-gray-dark font-16"></i>
				<i class="icon-check-2 fa fa-check-circle font-16 color-highlight"></i>
			</div> 
		</div>
		<div class="col-6"> 
			<div class="form-check icon-check">
				<input class="form-check-input" type="radio" name="searchType" value="90" id="type3" onclick='search(this.value)'>
				<label class="form-check-label" for="type3">최근 3개월</label>
				<i class="icon-check-1 fa fa-circle color-gray-dark font-16"></i>
				<i class="icon-check-2 fa fa-check-circle font-16 color-highlight"></i>
			</div>
			<div class="form-check icon-check">
				<input class="form-check-input" type="radio" name="searchType" value="0" id="type4" onclick='search(this.value)'>
				<label class="form-check-label" for="type4">직접설정</label>
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
	<div class="col-12 mt-3">
		<a href="#" class="btn close-menu btn-full btn-md bg-blue-dark font-800 text-uppercase rounded-s">조회</a>
	</div>
</div>

<!--//내역조회 모달-->
 
<!-- 예약취소 모달 -->
<div id="modal_cancle" class="menu menu-box-bottom rounded-0 " data-menu-height="500" data-menu-effect="menu-parallax" style="display: block; height: 510px;">
	<h1 class="text-center mt-4">
		<i class="fa fa-2x fa-check-circle scale-box color-red-dark shadow-xl rounded-circle"></i> 
	</h1>
	<h3 class="text-center mt-2 font-700">예약취소</h3>
	<p class="boxed-text-xl opacity-70">
		정말 취소하시겠습니까?
	</p>
	<div class="divider mt-2 mb-2"></div>
	<div class="content" >
		<div class="mb-0">
			<div class="row mb-3 mt-4">
				<h5 class="col-4 text-left font-15">예약명</h5>
				<h5 class="col-8 text-right font-14 opacity-60" id="popBayName"></h5>
				
				<h5 class="col-4 text-left font-15">방문날짜</h5>
				<h5 class="col-8 text-right font-14 opacity-60" id="popBkDay"></h5>
				
				<h5 class="col-4 text-left font-15">이용시간</h5>
				<h5 class="col-8 text-right font-14 opacity-60" id="popBkTime"></h5>
				
				<h5 class="col-4 text-left font-15">결제금액</h5>
				<h5 class="col-8 text-right font-14 opacity-60 " id="popMnAmt"></h5> 
				
				<h5 class="col-4 text-left font-15" id="popVoucherTxt">이용권</h5>
				<h5 class="col-8 text-right font-14 opacity-60" id="popVoucher"></h5> 
			</div>  
		</div>
	
	</div>
	<div class="row mb-0 mr-3 ml-3 mt-5 mb-5">
		<div class="col-12">
			<a href="javascript:onClick=doCancel()" class="btn btn-full btn-md color-red-dark border-red-dark font-800 text-uppercase rounded-s">
				예약취소
			</a>
		</div>
	</div>
</div>
<!--//예약취소 모달 -->

<!-- Global Footer -->
<jsp:include page="../common/footerBar.jsp" />

</body>

<jsp:include page="../common/alertModal.jsp" />  

<script type="text/javascript">

var listSize		= 0;	
var msId 			= "<c:out value='${sessionScope.msMember.msId}'/>";
var coDiv 			= "<c:out value='${coDiv}'/>";
var cancelSerialNo 	= "";		// 취소할때 필요한 serialno
var cancelAmt 		= "";		// 취소할때 필요한 금액

$(document).ready(function() {
	if (msId == null || msId == "") {
		// alertModal.fail('로그인 후 이용 가능합니다.');
		location.href = "/login";	
		return;
	}
	
	init();
	doSearch();

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
    
	$('#strtDt').val(getDaybyMonth("-", 0, 1));		// 오늘 날짜
	$('#endDt').val(getDaybyMonth("-", 0, 2));		// 1달뒤 날짜
}

// 예약취소 확인 팝업창
function popCancel(idx){
	
	$('#popBayName').text($('#bayName_'+idx).val()); 	// 예약명
	$('#popBkDay').text($('#bkDay_'+idx).val());		// 방문날짜 
	$('#popBkTime').text($('#bkTime_'+idx).val());		// 이용시간 
	$('#popMnAmt').text($('#mnAmt_'+idx).val());		// 결제금액 
	
	if ( $('#vcCnt_'+idx).val() == 0 ) {
		$('#popVoucherTxt').css('display', 'none');
		$('#popVoucher').css('display', 'none');
	} else {

		$('#popVoucher').text($('#vcName_'+idx).val() + '(' + $('#vcCnt_'+idx).val() + '매 사용)');		// 이용권 
	}
	
	cancelSerialNo = $('#serialNo_'+idx).val();			// 취소할때 필요한 serialno
	cancelAmt = $('#mnAmt_'+idx).val();					// 취소할때 필요한 금액
    $("#modal_cancle").addClass('menu-active');			// 모달창 open 
}

// 예약취소 
function doCancel(idx){
	
	var reservationInfo 			= {};
	reservationInfo.calcSerialNo 	= cancelSerialNo;
	reservationInfo.coDiv 			= coDiv;
	
	var url = "";
	
    if (cancelAmt == 0) {
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

// 예약내역 > 날짜로 검색
function search(value){

	switch(value) {
		case '30' :
			$('#strtDt').val(getDaybyMonth("-", 0, 1));	// 오늘 날짜
			$('#endDt').val(getDaybyMonth("-", 0, 2));	// 1달뒤 날짜
			break;
		case '90' : 
			$('#strtDt').val(getDaybyMonth("-", 0, 1));	// 오늘 날짜
			$('#endDt').val(getDaybyMonth("-", 0, 4));	// 3달뒤 날짜
			break;
		case '365': 
			$('#strtDt').val(getDaybyMonth("-", 0, 1));	// 오늘 날짜
			$('#endDt').val(getDaybyMonth("-", 1, 1));	// 12달뒤 날짜
			break;

		case '0' :
			$('#strtDt').val(getDaybyMonth("-", 0, 1));	// 오늘 날짜
			$('#endDt').val(getDaybyMonth("-", 0, 1));	// 오늘 날짜
			break;
	}
}

// 더보기 
function doSearch() {
	
	console.log('strtDt:', $('#strtDt').val(), ', endDt:', $('#endDt').val());
	
	$.ajax({
        url: "/book/moreBookList/" + coDiv
        , type: "post"
       	, dataType: 'json'
      	, data: { 
           	"listSize" : listSize
    	}
        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
        , success: function(data) {	
        	console.log(data);
        	
			if (data != null && data.length > 0) {
				var divCnt = '';
				for (let i=0; i<data.length; i++) {
					var bkDay = getStringDt2(data[i].BK_DAY, '-');
					var mnAmt = Number(data[i].MN_AMOUNT).toLocaleString("ko-KR")+'원';
					
					divCnt += '<input type="hidden" id="serialNo_'+i+'" value="'+data[i].CALC_SERIAL_NO+'">';
					divCnt += '<input type="hidden" id="bayName_'+i+'" value="'+data[i].BAY_NAME+'">';
					divCnt += '<input type="hidden" id="bkDay_'+i+'" value="'+bkDay+'">';
					divCnt += '<input type="hidden" id="bkTime_'+i+'" value="'+data[i].BK_TIME+'">';
					divCnt += '<input type="hidden" id="vcName_'+i+'" value="'+data[i].VC_NAME+'">';
					divCnt += '<input type="hidden" id="vcCnt_'+i+'" value="'+data[i].VC_CNT+'">';
					divCnt += '<input type="hidden" id="mnAmt_'+i+'" value="'+mnAmt+'">';
					
					divCnt += '<div class="card card-style" id=bookList_'+(listSize+i)+'>';
					divCnt += '<div class="content mb-3">';
					divCnt += '<div class="d-flex mb-3" style="width:100%">';
					divCnt += '<div>';
					divCnt += '<img src="/images/gallery/4.jpg" height="80" class="rounded-s shadow-xl">';
					divCnt += '</div>';
					divCnt += '<div class="pl-3" style="width:80%">';
					divCnt += '<h1 class="font-20 mb-n3">'+data[i].CO_NAME+'</h1>';
					divCnt += '<p class="mb-1 mt-2 color-highlight font-15">'+data[i].BAY_NAME+'</p>';
					divCnt += '</div>';
					divCnt += '</div>';
					divCnt += '<div class="row mb-3 mt-4">';
					divCnt += '<h5 class="col-4 text-left font-15">방문날짜</h5>';
					divCnt += '<h5 class="col-8 text-right font-14 opacity-60">'+bkDay+'</h5>';
					divCnt += '<h5 class="col-4 text-left font-15">이용시간</h5>';
					divCnt += '<h5 class="col-8 text-right font-14 opacity-60">'+data[i].BK_TIME+'</h5>';
					divCnt += '<h5 class="col-4 text-left font-15">결제금액</h5>';
					divCnt += '<h5 class="col-8 text-right font-14 opacity-60 ">'+mnAmt+'</h5>';
					
					if (data[i].VC_CNT > 0) {
						divCnt += '<h5 class="col-4 text-left font-15">이용권</h5>';
						divCnt += '<h5 class="col-8 text-right font-14 opacity-60 ">';
						divCnt += data[i].VC_NAME+'('+data[i].VC_CNT+'매 사용)';
						divCnt += '</h5>';
					}
				
					divCnt += '</div>';
					divCnt += '<div class="divider mb-3"></div>';
			
					switch (data[i].BK_STATE) {
						case '1' : 
							if ( ${nowDt} == data[i].BK_DAY ) {
								// 당일취소불가
								divCnt += '<a href="#" class="fr btn btn-border btn-sm rounded-0 text-uppercase font-900 border-yellow-dark color-yellow-dark bg-theme">';
								divCnt += '당일취소불가';
								divCnt += '</a>';
							} else if ( ${nowDt} > data[i].BK_DAY ) {
								// 취소불가
								divCnt += '<a href="#" class="fr btn btn-border btn-sm rounded-0 text-uppercase font-900 border-yellow-dark color-yellow-dark bg-theme">';
								divCnt += '취소불가';
								divCnt += '</a>';
							} else {
								// 예약취소
								divCnt += '<a href="#" data-menu="modal_cancle" data-idx="'+i+'" data-page="bookList" class="fr btn btn-border btn-sm rounded-0 text-uppercase font-900 border-red-dark color-red-dark bg-theme">';								
								divCnt += '예약취소';
								divCnt += '</a>';
							}
							break;
						case '3' :	
							divCnt += '<a href="#" class="fr btn btn-border btn-sm rounded-0 text-uppercase font-900 border-yellow-dark color-yellow-dark bg-theme">';
							divCnt += data[i].BK_STATE_NAME+' 된 예약';
							divCnt += '</a>';
							break;
					}
					divCnt += '</div>';
					divCnt += '</div>';
				}
				
				if (listSize == 0) {
				 	document.querySelector("#bookList").innerHTML = divCnt;
				} else {
					var tmp = listSize - 1;
					var sTmp = String(tmp);
					$("#bookList_"+ sTmp).after(divCnt);
				}
				listSize += data.length;
			} else {
				$('#btnMore').text("더이상 조회할 내역이 없습니다.");
				document.getElementById("btnMore").setAttribute("href", "#");
			}

        }
	});
}

</script>
</html>