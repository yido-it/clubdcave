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
        <a href="javascript:history.back(-1)" class="header-title header-subtitle">예약</a>
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
	                <h4 class="cal-title text-center text-uppercase font-800 bg-highlight color-white" id="calHeader"></h4>
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
			<a href="#" onClick="doBook()" class="mt-1 mb-4 btn btn-md bg-blue-dark btn-full shadow-xl text-uppercase font-800 rounded-s">
			예약하기
			</a> 
        </div>
	</div>
	<!--  content ends -->   
</div>   

<!--   Global Footer-->
<jsp:include page="../common/footerBar.jsp" />

<!--베이선택 팝업-->
<div id="select_bay" class="menu menu-box-bottom rounded-0 modal_bay" data-menu-height="435" data-menu-effect="menu-parallax" style="display: block; height: 335px;">
    <div class="menu-title">
        <h1 class="my-0 py-0">베이선택</h1>
        <a href="#" class="close-menu"><i class="fa fa-times-circle"></i></a>
    </div>
    <div class="mr-3 ml-3 mt-3">
        <div class="list-group list-custom-small">
        
        <!-- 베이목록 -->
       	<c:forEach items="${bayList}" var="bay">
       	     <a href="#" onClick="javscript:selectedBay('${bay.bayCd}', '${bay.bayName}')" class="close-menu">
                <img class="mr-3 mt-2" src="/images/gallery/1.jpg">
                <span>${bay.bayName}</span><i class="fa fa-angle-right"></i>
            </a>
       	</c:forEach>
       
        </div>
        <div class="clear"></div>
    </div>
</div>
<!--//베이선택 팝업-->

</body>

<!--페이지 로드되자마자 베이선택팝업 활성화-->
<div class="menu-hider menu-active"><div></div></div>
<!--//페이지 로드되자마자 베이선택팝업 활성화-->

<jsp:include page="../common/alertModal.jsp" />  
<script type="text/javascript">

var sYear, sMonth, sDate;
var msId 				= "<c:out value='${sessionScope.msMember.msId}'/>";
var msLevel 			= "<c:out value='${sessionScope.msMember.msLevel}'/>";
var msNum				= "<c:out value='${sessionScope.msMember.msNum}'/>";

var reservationInfo 	= {};
reservationInfo.coDiv 	= $('#coDiv').val();

var bkList 				= new Array();		// 시간 선택한 거 bkList 에 넣어주기

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
	sDate = date.dd();
	
	// 달력 초기화
	initCalendar(sYear, sMonth);
}

// 선택한 베이 + 날짜로 예약 데이터 조회 
function onClickDay(date, num) {
	
	if ($('#bayCondi').val() == "") {
		alertModal.fail('베이를 선택해주세요.');
		return;
	}
	console.log('click day > date:' , date, ', num:', num);
	
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
	console.log("[doSearch] 선택한 날짜 : " , sDate);
	var divTop = "<div class='reservation_time'><h5>" + getStringDt2(sDate) + " - 시간선택</h5>";
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
        	console.log('예약 가능한 시간 : ', data);
			
			var divCnt = "";
			if (data != null && data.length > 0) {
				
				divCnt += "<div class='btn-group' data-toggle='buttons' style='margin-top: -1px;'>";
				
				// ┌────────── 빈칸 채우려고 하는 작업 ──────────┐
				// 예약 데이터가 9개라면 12로 반복문 돌리기 
				var realLength = 0;
				if (data.length % 4 > 0) {
					
					var tmpCnt = parseInt(data.length / 4) ;
					realLength = (tmpCnt + 1) * 4;	
				}
				console.log('[doSearch] 실제 반복문 크기 : ', realLength);
				// └────────── 빈칸 채우려고 하는 작업 ──────────┘
				
				for (let i=0; i<realLength; i++) {
		        	
					if (data[i] != undefined) {
			        	var bkTime1 = data[i].bkTime.substr(0, 2);
			        	var bkTime2 = data[i].bkTime.substr(2, 2);
						
			        	if (data[i].bkRemCount > 0) {
			        		// 예약가능
			        		divCnt += "<label class='bkTime btn btn-secondary' id='bkTime_"+i+"' onClick='doTimeSelect(\""+data[i].bkTime+"\", "+i+")'>";
							divCnt += "<input type='checkbox' autocomplete='off'>" + bkTime1 + ":" + bkTime2 + "<br/>";
							divCnt += "<span>"+data[i].bkRemCount+"개 가능</span>";
							divCnt += "</label>";
			        	} else {
			        		// 마감 
			        		divCnt += "<label class='bkTime btn btn-secondary btn-lock'>";
							divCnt += "<input type='checkbox' autocomplete='off' disabled='disabled'>" + bkTime1 + ":" + bkTime2 + "<br/>";
							divCnt += "<span>마감</span>";
							divCnt += "</label>";
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
				divCnt += "<span style='color:red'>예약 가능한 시간이 없습니다.</span>";
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
					
					if(data[i].CL_SOLAR == currentDay) {
						// 당일
						divCnt += "<a href='#' id='day_"+data[i].DAYNUM+"' class='cal-selected' onClick='onClickDay(\""+data[i].CL_SOLAR+"\", "+data[i].DAYNUM+")'>";
						divCnt += "<i class='fa fa-square color-highlight'></i>";
						divCnt += "<span>" + data[i].DAYNUM + "</span></a>";
					} else {
						// 당일 아닌거 
						if ( data[i].CL_SOLAR > currentDay) {
							// 미래 날짜 
							if(data[i].BK_REM_COUNT <= 0) {
								// 예약 불가한날 
								divCnt += "<a href='#' class='cal-disabled'>" + data[i].DAYNUM + "</a>";
							} else {
								// 예약 가능한날
								divCnt += "<a href='#' id='day_"+data[i].DAYNUM+"' onClick='onClickDay(\""+data[i].CL_SOLAR+"\", "+data[i].DAYNUM+")'>" + data[i].DAYNUM + "</a>";
							}
						} else {
							// 과거 날짜 예약 불가 
							divCnt += "<a href='#' class='cal-disabled'>" + data[i].DAYNUM + "</a>";
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

	initCalendar(sYear, sMonth);
});


// 베이 선택
function selectedBay(bayCd, bayName) {
	document.querySelector(".bayInfo").innerHTML = bayName;
	$('#bayCondi').val(bayCd);
	
	// 선택되어있는 날짜로 예약 데이터 조회하기 
	var matches = document.getElementsByClassName('cal-selected');

	if (matches.length > 0) {
		var afterStr = matches[0].id.split('_');
	}
	
	sDate = sYear + sMonth + afterStr[1];
	console.log("[베이선택] 조회 날짜 : ", sDate);
	doSearch();
}

// 예약하기 버튼 클릭
function doBook() {

	if ($('#bayCondi').val() == "") {
		alertModal.fail('베이를 선택해주세요.');
		return;
	}

	console.log('[doBook] bkList: ', bkList);
	if (bkList == null || bkList.length <= 0) {
		alertModal.fail('예약시간을 선택해주세요.');
		return;
	}

	reservationInfo.bkList = bkList;
	console.log('[doBook] reservationInfo: ', reservationInfo);
	
	// 다음페이지로 이동 		
	$.ajax({
		url: "/book/book2/" + $('#coDiv').val()
		, type: "post"
		, dataType: 'json'
		, data: reservationInfo
		, contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
		, success: function(data) {
			if (data.code == '0000') {
				location.href="/book/book2/"+$('#coDiv').val()+"/"+data.data;
			}
		}
	});		

}

// 시간선택시 호출되는 함수 
// 시간선택 => 예약선점 , 시간선택해제 => 예약선점해제
// 시간선택시, 예약마감 체크해서 마감이면 팝업창 띄워주고 마감으로 변경해주기 
function doTimeSelect(bkTime, idx) {

	var bookData 				= new Object();	
	var className 				=  document.getElementById('bkTime_'+idx).className;
	
	reservationInfo.bayCondi 	= $('#bayCondi').val();
	reservationInfo.bkDay 		= $('#bkDay').val();
	reservationInfo.bkTime 		= bkTime;
	console.log('[시간선택&해제] reservationInfo > ', reservationInfo);
	
	if(className.includes('active')) {
		// 선점해제
		console.log('선점해제');
		
		var json_idx = bkList.findIndex(function(key) {
			
			return key.timeIdx === idx
		});
		bkList.splice(json_idx, 1);
		
		chkBkMark(reservationInfo, "N");
	} else {
		// 선점 전에 예약가능여부 조회 (회원위약)
		$.ajax({
			url: "/book/chkGrant"
			, type: "post"
			, dataType: 'json'
			, data: reservationInfo
			, contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
			, success: function(data) {
				result = data;
			}
		});		
			
		setTimeout(function() {
			if (result.code == '9999') {
				alert(result.message);
				// 위약여부 Y이면 선택된 Class 제거 
				$('#bkTime_'+idx).removeClass('active');
				return;
			}
			
			console.log('선점');
			
			chkBkMark(reservationInfo, "Y");

			// 선택된 시간을 bkList 에 저장 (예약하기 버튼 클릭시 필요한 정보)
			bookData.timeIdx 	= idx;
			bookData.bkTime 	= bkTime;
			bkList.push(bookData);
			
		}, 300);	
	}

	console.log('[시간선택&해제] bkList > ', bkList);
}

// 예약선점 & 선점해제
function chkBkMark(reservationInfo, gubun) {
	
	switch(gubun) {
		case "Y" :
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
			break;
			
		case "N" :	
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
			break;			
	}
}

</script>

</html>