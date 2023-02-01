<!-- 
기능 : 이용권 (이용권구매 / 이용권보유내역)

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
        <a href="javascript:history.back(-1)" class="header-title header-subtitle">이용권</a>
		<jsp:include page="../common/top.jsp" />
    </div>
    
    <!-- 좌측GNB-->
	<jsp:include page="../common/menu.jsp" />
	<!-- //좌측GNB-->

    <div class="page-content header-clear-medium">
         

      <div class="content">
        <!--탭선택-->
        <div class="tab-controls tabs-round tab-animated tabs-medium tabs-rounded shadow-xl" data-tab-items="2" data-tab-active="bg-green-dark color-white">
            <a href="#" data-tab-active="" data-tab="tab-1" class="bg-green-dark color-white no-click font-15" style="width: 50%;">
                이용권구매</a>
            <a href="#" data-tab="tab-2" style="width: 50%;" class="font-15">
                이용권보유내역</a>
        </div>
        <div class="clearfix mb-3"></div>
        <!--//탭선택-->

          <!--이용권구매-->
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
            <button class="btn btn-primary btn-xs bg-blue-dark ml-1" onClick="location.href='payment2.html'">구매</button>
        </div>
        <div class="divider mt-3 mb-3"></div>
		</c:forEach>

        </div>




        <!--이용권보유 Tab-2-->
        <div class="tab-content" id="tab-2">
            <div class="menu-title">
                <h3 class="my-0 py-0">보유내역
                </h3>
                <div class="float-right  mt-n4">
                 
                    <a href="" data-menu="modal_day" class="color-white"><i class="fa-solid fa-calendar-days"></i></a>
                </div>
            </div>
            <div class="divider mt-3 mb-3"></div>
            <div class="accordion" id="accordion-1">

                <div class="mb-0">
                    <button class="btn accordion-btn border-0 color-theme font-14" data-toggle="collapse" data-target="#collapse3">
                        오픈타석 1매 
                        <i class="fa fa-chevron-down font-10 accordion-icon"></i>
                        <p class="opacity-50 font-11"><i class="fa-regular fa-clock"></i>유효기간: 2024.03.27</p>    
                    </button>
                    <div id="collapse3" class="collapse" data-parent="#accordion-1">
                        <div class="d-flex mt-2 mb-2 content">
                            <div>
                                <h2 class="font-13 mb-0 line-height-m font-500">오픈타석 <span class="badge bg-blue-dark px-2 py-1 ml-2 mt-2 text-uppercase">사용가능</span></h2>       
                               </div>
                            <div class="ml-auto mt-1 pl-3 text-right">
                                <p class="mb-0 opacity-50 font-11">구매일자: 23.08.12</p>        
                            </div>
                        </div>
                    </div>
                </div>
               
                <div class="mb-0">
                    <button class="btn accordion-btn border-0 color-theme collapsed font-14" data-toggle="collapse" data-target="#collapse1" aria-expanded="false"> 
                        오픈타석 10+2회 <span class="color-blue-dark ml-1"> 잔여 4매</span>
                        <i class="fa fa-chevron-down font-10 accordion-icon"></i>
                        <p class="opacity-50 font-11"><i class="fa-regular fa-clock"></i>유효기간: 2024.03.27</p>    
                    </button>
                    <div id="collapse1" class="collapse" data-parent="#accordion-1" >
                        <div class="d-flex mt-2 mb-2 content">
                            <div>
                                <h2 class="font-13 mb-0 line-height-m font-500">오픈타석 <span class="badge bg-blue-dark px-2 py-1 ml-2 mt-2 text-uppercase">사용가능</span></h2>       
                               </div>
                            <div class="ml-auto mt-1 pl-3 text-right">
                                <p class="mb-0 opacity-50 font-11">구매일자: 23.08.12</p>        
                            </div>
                        </div>
                        <div class="d-flex mt-2 mb-2 content">
                            <div>
                                <h2 class="font-13 mb-0 line-height-m font-500">오픈타석  <span class="badge bg-blue-dark px-2 py-1 ml-2 mt-2 text-uppercase">사용가능</span></h2>       
                               </div>
                            <div class="ml-auto mt-1 pl-3 text-right">
                                <p class="mb-0 opacity-50 font-11">구매일자: 23.08.12</p>        
                            </div>
                        </div>
                        <div class="d-flex mt-2 mb-2 content">
                            <div>
                                <h2 class="font-13 mb-0 line-height-m font-500">오픈타석  <span class="badge bg-blue-dark px-2 py-1 ml-2 mt-2 text-uppercase">사용가능</span></h2>       
                               </div>
                            <div class="ml-auto mt-1 pl-3 text-right">
                                <p class="mb-0 opacity-50 font-11">구매일자: 23.08.12</p>        
                            </div>
                        </div>
                        <div class="d-flex mt-2 mb-2 content">
                            <div>
                                <h2 class="font-13 mb-0 line-height-m font-500">오픈타석  <span class="badge bg-blue-dark px-2 py-1 ml-2 mt-2 text-uppercase">사용가능</span></h2>       
                               </div>
                            <div class="ml-auto mt-1 pl-3 text-right">
                                <p class="mb-0 opacity-50 font-11">구매일자: 23.08.12</p>        
                            </div>
                        </div>
                        <div class="d-flex mt-2 mb-2 content">
                            <div>
                                <h2 class="font-13 mb-0 line-height-m font-500">오픈타석  <span class="badge bg-gray-dark px-2 py-1 ml-2 mt-2 text-uppercase">사용완료</span></h2>       
                               </div>
                            <div class="ml-auto mt-1 pl-3 text-right">
                                <p class="mb-0 opacity-50 font-11">구매일자: 23.08.12</p>        
                            </div>
                        </div>
                        <div class="d-flex mt-2 mb-2 content">
                            <div>
                                <h2 class="font-13 mb-0 line-height-m font-500">오픈타석  <span class="badge bg-gray-dark px-2 py-1 ml-2 mt-2 text-uppercase">사용완료</span></h2>       
                               </div>
                            <div class="ml-auto mt-1 pl-3 text-right">
                                <p class="mb-0 opacity-50 font-11">구매일자: 23.08.12</p>        
                            </div>
                        </div>
                      
                    </div> 
                </div>

                <div class="mb-0">
                    <button class="btn accordion-btn border-0 color-theme font-14" data-toggle="collapse" data-target="#collapse2">
                        축구게임 1매
                        <i class="fa fa-chevron-down font-10 accordion-icon"></i>
                        <p class="opacity-50 font-11"><i class="fa-regular fa-clock"></i>유효기간: 2024.03.27</p>    
                    </button>
                    <div id="collapse2" class="collapse" data-parent="#accordion-1">
                        <div class="d-flex mt-2 mb-2 content">
                            <div>
                                <h2 class="font-13 mb-0 line-height-m font-500">축구게임 <span class="badge bg-blue-dark px-2 py-1 ml-2 mt-2 text-uppercase">사용가능</span></h2>       
                               </div>
                            <div class="ml-auto mt-1 pl-3 text-right">
                                <p class="mb-0 opacity-50 font-11">구매일자: 23.08.12</p>        
                            </div>
                        </div>
                    </div>
                </div>
            
            </div>

        </div>
        <!--//이용권보유-->

       </div>
    <!-- Page content ends here-->  

	<!--  content ends -->   
</div>   

<!--내역조회 모달-->
<div id="modal_day" class="menu menu-box-modal rounded-0"  style="background-color: #303030;z-index:99999;padding: 15px 0;"
data-menu-height="auto" 
data-menu-width="330"
data-menu-effect="menu-parallax" style="padding:20px">
<h3 class="text-center mt-3 font-600">지난 사용내역 조회</h3>
<div class="divider mb-3 mt-3"></div>
<div class="row mb-0 mr-3 ml-3 mt-2">
             
   <div class="col-6"> 
        <div class="form-check icon-check">
            <input class="form-check-input" type="radio" name="inlineRadioOptions" value="" id="radio2">
            <label class="form-check-label" for="radio2">최근 1개월</label>
            <i class="icon-check-1 fa fa-circle color-gray-dark font-16"></i>
            <i class="icon-check-2 fa fa-check-circle font-16 color-highlight"></i>
        </div>
        <div class="form-check icon-check">
            <input class="form-check-input" type="radio" name="inlineRadioOptions" value="" id="123">
            <label class="form-check-label" for="123">최근 1년</label>
            <i class="icon-check-1 fa fa-circle color-gray-dark font-16"></i>
            <i class="icon-check-2 fa fa-check-circle font-16 color-highlight"></i>
        </div> 
</div>
    <div class="col-6"> 
        <div class="form-check icon-check">
            <input class="form-check-input" type="radio" name="inlineRadioOptions" value="" id="124">
            <label class="form-check-label" for="124">최근 3개월</label>
            <i class="icon-check-1 fa fa-circle color-gray-dark font-16"></i>
            <i class="icon-check-2 fa fa-check-circle font-16 color-highlight"></i>
        </div>
        <div class="form-check icon-check">
            <input class="form-check-input" type="radio" name="inlineRadioOptions" value="" id="125">
            <label class="form-check-label" for="125">전체보기</label>
            <i class="icon-check-1 fa fa-circle color-gray-dark font-16"></i>
            <i class="icon-check-2 fa fa-check-circle font-16 color-highlight"></i>
        </div> 
</div>
<div class="col-12"> 
    <div class="form-check icon-check">
        <input class="form-check-input" type="radio" name="inlineRadioOptions" value="" id="126">
        <label class="form-check-label" for="126">보유중인 이용권만 보기</label>
        <i class="icon-check-1 fa fa-circle color-gray-dark font-16"></i>
        <i class="icon-check-2 fa fa-check-circle font-16 color-highlight"></i>
    </div>
 
</div>

   <div class="col-12 mt-3">
       <a href="#" class="btn close-menu btn-full btn-md bg-blue-dark font-800 text-uppercase rounded-s">조회</a>
   </div>
</div>
</div>  
<!--//내역조회 모달-->
 
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