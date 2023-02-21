<!-- 
기능 : 예약 메인 페이지
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
        <a href="#" class="header-title header-subtitle">예약</a>
		<jsp:include page="../common/top.jsp" />
    </div>
    
    <!-- 좌측GNB-->
	<jsp:include page="../common/menu.jsp" />
	<!-- //좌측GNB-->

    <div class="page-content header-clear-medium">
         
		<div class="content">
			<form id="frm">
				<input type="hidden" name="bayCondi" id="bayCondi" value="" />
				<input type="hidden" name="coDiv" id="coDiv" value="${coDiv}" />
				<input type="hidden" name="bkDay" id="bkDay" value="" /> 
			</form>
		
	        <h3 class="mb-3">클럽디청담 예약 </h3> 
	        <div class="input-style input-style-2 input-required" data-menu="select_bay">
	            <span class="color-highlight input-style-1-inactive bayInfo">베이선택</span>
	            <em><i class="fa fa-chevron-down color-green-dark"></i></em> 
	            <input class="form-control" type="text" onfocus="this.blur()">
	        </div>
	         
			<!-- 예약 캘린더 -->
	        <div class="calendar">
	            <div class="cal-header"> 
	            	<!-- 년  / 월 -->
	                <h4 class="cal-title text-center text-uppercase font-400  color-white" style="background-color: #767676  !important;" id="calHeader"></h4>
	                <!-- 이전달 -->
	                <h6 class="cal-title-left color-white">
	                	<i class="fa fa-chevron-left"></i>
	                </h6>
	                <!-- 다음달 -->
	                <h6 class="cal-title-right color-white">
	                	<i class="fa fa-chevron-right"></i>
	                </h6>
	            </div>
	            <div class="clearfix"></div>
	            <div class="cal-days  opacity-80 bottom-0" style="background-color: #b5b5b5  !important;">
	                <a href="#">일</a>
	                <a href="#">월</a>
	                <a href="#">화</a>
	                <a href="#">수</a>
	                <a href="#">목</a>
	                <a href="#">금</a>
	                <a href="#">토</a>
	                <div class="clearfix"></div>
	            </div>
	            <div class="cal-dates cal-dates-border calendar-body" id="calendar-body">
	                <a href="#">1</a>
	                <a href="#">2</a>
	                <a href="#">3</a>
	                <a href="#">4</a>
	                <a href="#">5</a>
	                <a href="#">6</a>
	                <a href="#">7</a>
	                <div class="clearfix"></div>
	            </div>
	        </div>
	        
	        <!-- 시간 선택 -->
	        <div class="calendar bg-theme mb-4 mt-3 timeTable" style="display:none">     
	            <div class="col-sm-12 selectedDate" style="background-color: #333333;padding: 0px 10px 10px 10px;">
	        	</div>
 			</div>
 			
 			<!-- 예약하기 버튼 -->
			<a href="#" onClick="doBook()" class="mt-3 mb-10 btn btn-md bg-blue-dark btn-full shadow-xl text-uppercase font-800 rounded-s">
			예약하기
			</a> 
        </div>
	</div>
	<!--  content ends -->   
</div>   

<!--   Global Footer-->
<jsp:include page="../common/footerBar.jsp" />

<!--베이선택 팝업-->
<div id="select_bay" class="menu menu-box-bottom rounded-0 modal_bay" data-menu-height="356" data-menu-effect="menu-parallax" style="display: block; height: 335px;">
    <div class="menu-title">
        <h1 class="my-0 py-0">Bay Selection</h1>
        <a href="#" class="close-menu"><i class="fa fa-times-circle"></i></a>
    </div>
    <div class="mr-3 ml-3 mt-3">
        <div class="list-group list-custom-small">
        
        <!-- 베이목록 -->
       	<c:forEach items="${bayList}" var="bay">
       	     <a href="#" onClick="selectedBay('${bay.bayCd}', '${bay.bayName}')" class="close-menu">
                <img class="mr-3 mt-2" src="/images/gallery/${bay.bayCd}.jpg">
                <span class="txt_bay_name">${bay.bayName}</span><i class="fa fa-angle-right"></i>
            </a>
       	</c:forEach>
       
        </div>
        <div class="clear"></div>
    </div>
</div>
<!--//베이선택 팝업-->


<!-- 예약개수 초과 모달 -->
<div id="modal_overBkCnt" class="menu menu-box-modal rounded-0" data-menu-height="" data-menu-width="330"
	data-menu-effect="menu-parallax">

	<h1 class="text-center mt-2"><i class="fa-solid fa-triangle-exclamation fa-2x color-red-dark"></i></h1>
	<p class="boxed-text-xl opacity-70" id="overBkTxt"></p>
	<div class="row mb-0 mr-3 ml-3 mb-3">

	   <div class="col-12">
	  	 <a href="#" class="btn close-menu btn-full btn-md bg-red-dark font-800 text-uppercase rounded-s">확인</a>
	   </div>
	</div>

</div> 
<!--//예약개수 초과 모달 -->

<!-- 예약취소 팝업 -->
<div id="voucher_cancle" class="menu menu-box-modal rounded-0" data-menu-height="270" data-menu-width="330"
	data-menu-effect="menu-parallax">
	<h1 class="text-center mt-4"><i class="fa-solid fa-triangle-exclamation fa-2x color-red-dark"></i></h1>
	<h3 class="text-center mt-3 font-700">예약 취소</h3>
	<p class="boxed-text-xl opacity-70">
		예약을 정말 취소하시겠습니까?
	</p>
	<div class="row mb-0 mr-3 ml-3">
		<div class="col-6">
			<a href="#" class="btn close-menu btn-full btn-md bg-red-dark font-800 text-uppercase rounded-s">아니요</a>
		</div>
		<div class="col-6">
			<a href="#" class="btn close-menu btn-full btn-md bg-green-dark font-800 text-uppercase rounded-s">예</a>
		</div>
	</div>
</div> 


</body>

<!--페이지 로드되자마자 베이선택팝업 활성화-->
<div class="menu-hider menu-active"><div></div></div>
<!--//페이지 로드되자마자 베이선택팝업 활성화-->

<jsp:include page="../common/alertModal.jsp" />  
<script type="text/javascript">

var dayClick			= "";	// 직접 클릭한 날짜 넣기 
var preSelectedTime 	= "";
var preSelectedType 	= "";
var sYear, sMonth, sDate;
var msId 				= "<c:out value='${sessionScope.msMember.msId}'/>";
var msLevel 			= "<c:out value='${sessionScope.msMember.msLevel}'/>";
var msNum				= "<c:out value='${sessionScope.msMember.msNum}'/>";
var bkCnt				= "<c:out value='${bkCnt}'/>";			// 현재까지 예약한 갯수
var maxBkCnt			= "<c:out value='${maxBkCnt}'/>";		// 최대 예약가능 갯수
var grantYn 			= "<c:out value='${grantYn}'/>";		// 위약 정보 ( grantYn > N : 예약불가 )

var reservationInfo 	= {};
reservationInfo.coDiv 	= $('#coDiv').val();

$(document).ready(function() {
	// 페이지 로드되자마자 베이선택팝업 활성화
    $(".modal_bay").addClass('menu-active');
    
	if (msId == null || msId == "") {
		// alertModal.fail('로그인 후 이용 가능합니다.');
		location.href = "/login";	
		return;
	}
	
	init();
});


function init() {
	var date = new Date();
	sYear = date.yyyy();
	sMonth = date.mm();
	sDay = date.dd();
	
	// 달력 초기화
	initCalendar(sYear, sMonth);
}

// 선택한 베이 + 날짜로 예약 데이터 조회 
function onClickDay(date, num) {
	dayClick = num;
	
	if ($('#bayCondi').val() == "") {
		alertModal.fail('베이를 선택해주세요.');
		return;
	}
	
	// ┌────────── 기존에 선택된거 class 제거 후 HTML 재구성 ──────────┐
	var matches = document.getElementsByClassName('cal-selected');

	while (matches.length > 0) {
		var afterStr = matches[0].id.split('_');
		
		// HTML 재구성 (원래 선택되어있는 날짜 표출)
		document.getElementById(matches[0].id).innerHTML = afterStr[1];
		// class 제거
		matches[0].classList.remove('cal-selected');
	}
	// └────────── 기존에 선택된거 class 제거 후 HTML 재구성 ──────────┘
	
	// ┌──────────── 선택한 날짜 class 추가 후 HTML 재구성 ───────────┐
	$('#day_' + num).addClass('cal-selected');
	document.getElementById('day_' + num).innerHTML = "<i class='fa fa-square color-highlight'></i><span>" + num + "</span></a>";
	// └──────────── 선택한 날짜 class 추가 후 HTML 재구성 ───────────┘
	
	$("#bkDay").val(date);
	sDate = date;
	doSearch();

}

// 예약 가능한 데이터 조회
function doSearch(){
	
	// 선택한 날짜 표출  
	var divTop = "<div class='reservation_time'><h5>" + getStringDt2(sDate, 'B') + " - 시간선택</h5>";
	var divBottom = "</div>";
	var divCnt = "";
	
	$.ajax({
        url: "/book/bookAvailableTime"
        , type: "post"
       	, dataType: 'json'
      	, data: { 
           	"coDiv" 		: $('#coDiv').val() 
           	, "msLevel" 	: msLevel
           	, "bayCondi" 	: $('#bayCondi').val()
           	, "bkDay" 		: sDate
    	}
        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
        , success: function(data) {	
        	// console.log('예약 가능한 시간 : ', data);
			
			var divCnt = "";
			if (data != null && data.length > 0) {
				
				divCnt += "<div class='btn-group' data-toggle='buttons' style='margin-top: -1px;'>";
				
				// ┌────────── 빈칸 채우려고 하는 작업 ──────────┐
				// 예약 데이터가 9개라면 12로 반복문 돌리기 
				var realLength = 0;
				if (data.length % 4 > 0) {
					
					var tmpCnt = parseInt(data.length / 4) ;
					realLength = (tmpCnt + 1) * 4;	
				} else {
					realLength = data.length;
				}
				console.log('[doSearch] 실제 반복문 크기 : ', realLength);
				// └────────── 빈칸 채우려고 하는 작업 ──────────┘
				
				for (let i=0; i<realLength; i++) {
		        	
					if (data[i] != undefined) {
			        	var bkTime1 = data[i].bkTime.substr(0, 2);
			        	var bkTime2 = data[i].bkTime.substr(2, 2);
						
			        	if (grantYn == 'N') {
			        		 // 위약걸려있으면 예약불가
			        		divCnt += "<label class='bkTime btn btn-secondary btn-lock'>";
							divCnt += "<input type='checkbox' autocomplete='off' disabled='disabled'>" + bkTime1 + ":" + bkTime2 + "<br/>";
							divCnt += "<span>예약불가</span>";
							divCnt += "</label>";
							
			        	} else {
			        		if (data[i].bkRemCount > 0) {
				        		// 예약가능
				        		divCnt += "<label class='bkTime btn btn-secondary' id='bkTime_"+bkTime1+"'>";
								divCnt += "<input type='checkbox' autocomplete='off'>" + bkTime1 + ":" + bkTime2 + "<br/>";
								divCnt += "<span id='status_"+bkTime1+"'>"+data[i].bkRemCount+"개 가능</span>";
								divCnt += "</label>";
				        	} else {
				        		// 마감 
				        		divCnt += "<label class='bkTime btn btn-secondary btn-lock'>";
								divCnt += "<input type='checkbox' autocomplete='off' disabled='disabled'>" + bkTime1 + ":" + bkTime2 + "<br/>";
								divCnt += "<span id='status_"+bkTime1+"'>마감</span>";
								divCnt += "</label>";
				        	}
			        	}
						
					} else {
						divCnt += "<label class='bkTime btn'>";
						divCnt += "<input type='checkbox' autocomplete='off'>";
						divCnt += "</label>";
					}

					if ((i+1) % 4 == 0) {
						// 줄바꿈
						divCnt += "</div><div class='btn-group' data-toggle='buttons' style='margin-top: -1px;'>";
					}
					
					if (i == realLength) {
						divCnt += "</div>";
					}
				}
		
				document.querySelector(".selectedDate").innerHTML = divTop + divCnt + divBottom;
				$('.timeTable').css('display', 'inline');
				
			} else {
				divCnt += "<span style='color:#ff8282'>예약 가능한 시간이 없습니다.</span>";
				document.querySelector(".selectedDate").innerHTML = divTop + divCnt + divBottom;
				$('.timeTable').css('display', 'inline');
			}
		}
	});		
}

// 달력 표출
function initCalendar(year, month) {

	$.ajax({
		url: "/book/getCalendar"
		, type: "post"
		, dataType: 'json'
		, data: {
			"coDiv" : reservationInfo.coDiv
			, "bayCondi" : $('#bayCondi').val()
			, "selYM" : year + month
			, "msLevel" : msLevel
		}
		, contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
		, success: function(data) {

			var currentDay = new Date().yyyymmdd();
			$("#calHeader").html(year + "년 " + month + "월");

			var currentDay = new Date().yyyymmdd();
			var tBody = $("#calendar-body");
			tBody.empty();

			if(data.length > 0) {
				var divCnt = "";
				
				// 시작일자 처리 로직(매달 시작일자 위치가 다르기때문에 아래와 같이 처리)
				var fWeek = data[0].CL_DAYDIV - 1;

				for(i=0; i<fWeek; i++) {
					divCnt += "<a href='#'></a>";
				}
				document.querySelector(".calendar-body").innerHTML = divCnt;
				// end.
				
				for(i=0; i<data.length; i++) {
					if ($('#bayCondi').val() == "") {
						// 기본 달력 표출 
						divCnt += "<a href='#' class='cal-disabled'>" + data[i].DAYNUM + "</a>";
					} else {
						//  지점, 베이, 회원등급에 따른 달력 표출 
						if(data[i].CL_SOLAR == currentDay) {
							// 당일
							if (dayClick == "") {
								divCnt += "<a href='#' id='day_"+data[i].DAYNUM+"' class='cal-selected' onClick='onClickDay(\""+data[i].CL_SOLAR+"\", "+data[i].DAYNUM+")'>";
								divCnt += "<i class='fa fa-square color-highlight'></i>";
								divCnt += "<span>" + data[i].DAYNUM + "</span></a>";
							} else {
								divCnt += "<a href='#' id='day_"+data[i].DAYNUM+"' onClick='onClickDay(\""+data[i].CL_SOLAR+"\", "+data[i].DAYNUM+")'>" + data[i].DAYNUM + "</a>";
							}
						} else {
							// 당일 아닌거 
							if ( data[i].CL_SOLAR > currentDay) {
								// 미래 날짜 
								if(data[i].BK_REM_COUNT <= 0) {
									// 예약 불가한날 
									divCnt += "<a href='#' class='cal-disabled'>" + data[i].DAYNUM + "</a>";
								} else {
									// 예약 가능한날
									if (dayClick != "" && dayClick == data[i].DAYNUM) {
										divCnt += "<a href='#' id='day_"+data[i].DAYNUM+"' class='cal-selected' onClick='onClickDay(\""+data[i].CL_SOLAR+"\", "+data[i].DAYNUM+")'>";
										divCnt += "<i class='fa fa-square color-highlight'></i>";
										divCnt += "<span>" + data[i].DAYNUM + "</span></a>";
									} else {
										divCnt += "<a href='#' id='day_"+data[i].DAYNUM+"' onClick='onClickDay(\""+data[i].CL_SOLAR+"\", "+data[i].DAYNUM+")'>" + data[i].DAYNUM + "</a>";
									}
								}
							} else {
								// 과거 날짜 예약 불가 
								divCnt += "<a href='#' class='cal-disabled'>" + data[i].DAYNUM + "</a>";
							}		
						} 
					}
					document.querySelector(".calendar-body").innerHTML = divCnt;
				}
			} else {
				var divCnt = "";
				for(i=0; i<42; i++) {
					divCnt += "<a href='#' class='cal-disabled'>-</a>";
				}
				document.querySelector(".calendar-body").innerHTML = divCnt;	
				
				$('.timeTable').css('display', 'none');
			}
		
		}
	});
}

// 이전달 클릭
$('.cal-title-left').on('click', function() {
	if(sMonth == "01") {
		sYear -= 1;
		sMonth = "12";
	} else {
		sMonth = Number(sMonth) - 1;
		sMonth = (sMonth > 9 ? '' : '0') + sMonth;
	}

	dayClick = "";
	$('.timeTable').css('display', 'none');
	initCalendar(sYear, sMonth);
});

// 다음달 클릭
$('.cal-title-right').on('click', function() {
	if(sMonth == "12") {
		sYear += 1;
		sMonth = "01";
	} else {
		sMonth = Number(sMonth) + 1;
		sMonth = (sMonth > 9 ? '' : '0') + sMonth;
	}

	dayClick = "";
	$('.timeTable').css('display', 'none');
	initCalendar(sYear, sMonth);
});


// 베이 선택
function selectedBay(bayCd, bayName) {
	document.querySelector(".bayInfo").innerHTML = bayName;
	$('#bayCondi').val(bayCd);
	
	initCalendar(sYear, sMonth);
	if (dayClick == "") {
		// 최초 베이 선택시, 오늘 날짜로 [예약 데이터] 조회하기 
		sDate = sYear + sMonth + sDay;
	} else {
		// 날짜 클릭 후 베이 변경시, 해당 날짜로 [예약 데이터] 조회하기 
		var tmpDay = String(dayClick).padStart(2, "0");		
		sDate = sYear + sMonth + tmpDay;	
	}
	
	console.log('베이 선택 > 날짜 : ' , sDate);
	
	doSearch();
}

// 예약하기 버튼 클릭
function doBook() {
	var matches = document.getElementsByClassName('active');
	
	if (matches.length <= 0) {
		alertModal.fail('예약시간을 선택해주세요.');
		return;
	}

	// 예약 최대 개수 체크 
	var totBkCnt = Number(bkCnt) + matches.length;
	// maxBkCnt 가 99 이면 무제한 
	if (maxBkCnt != 99 && maxBkCnt < totBkCnt) {
		var overBkTxt = "";
		if (bkCnt == 0) {
			// 기존에 예약한 내역이 없다면 
			overBkTxt = "최대 예약 가능 개수는 " + maxBkCnt + "건 입니다.";
		} else {
			// 기존에 예약한 내역이 있다면 
			overBkTxt = "최대 예약 가능 개수는 " + maxBkCnt + "건 입니다.<BR>";
			overBkTxt += "이미 " + bkCnt + "건을 예약 하셨습니다.";
		}
		
		document.getElementById('overBkTxt').innerHTML = overBkTxt;
		$('#modal_overBkCnt').addClass('menu-active');
		return;
	}
	// end.

	var bkList = new Array();		// 선택된 시간 
	
	if ($('#bayCondi').val() == "") {
		alertModal.fail('베이를 선택해주세요.');
		return;
	}
	
	for(i=0; i<matches.length; i++) {
		// 선택된 시간 반복문
		var bookData = new Object();	
		var afterStr = matches[i].id.split('_');
		
		bookData.bkTime = afterStr[1] + ":00";
		bkList.push(bookData);
	}
	reservationInfo.bkList 		= bkList;
	reservationInfo.bayCondi 	= $('#bayCondi').val();
	reservationInfo.bkDay 		= $('#bkDay').val();
	
	console.log("reservationInfo: ", reservationInfo);

	// '예약가능여부' 체크 후 다음페이지로 이동 	
	$.ajax({
		url: "/book/book2/" + $('#coDiv').val()
		, type: "post"
		, dataType: 'json'
		, data: reservationInfo
		, contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
		, success: function(data) {
			if (data.code == '0000') {
				// 다음 페이지로 이동 
				location.href="/book/book2/"+$('#coDiv').val()+"/"+data.data;
				
			} else if (data.code == '9999') {
				alertModal.fail(data.message);
				// 마감된 시간은 마감처리 + 선택해제 + bkList 다시 구성 
				var tList = data.data;

				for(i=0; i<tList.length; i++) {					
					// 선택해제 
					$('#bkTime_'+tList[i].substr(0, 2)).removeClass('active');
					// 마감으로 변경 
					$('#bkTime_'+tList[i].substr(0, 2)).addClass('btn-lock');
					$('#status_'+tList[i].substr(0, 2)).text('마감');

					bkList = [];
					reservationInfo.bkList = "";
				}
				console.log("reservationInfo: ", reservationInfo);
			}
		}
	});	

}
 
$('.selectedDate').on('click', function() {

	window.scrollTo({top:200, behavior:'smooth'});
});
</script>

</html>