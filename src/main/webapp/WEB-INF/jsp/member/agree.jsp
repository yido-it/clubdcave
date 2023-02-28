<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />
<body class="theme-dark">
<div id="preloader"><div class="spinner-border color-highlight" role="status"></div></div>
    
<!-- <div class="page-content mb-0"> -->
    <div id="page">
		<div class="header header-fixed header-logo-app header-transparent">
			<a href="javascript:history.back()" class="color-white header-title header-subtitle">약관 확인하기</a>
			<a href="#" data-back-button class="color-white header-icon header-icon-1" id="topGoBack"><i class="fa fa-arrow-left"></i></a>
        	<a href="#" data-menu="menu-main" class="color-white header-icon header-icon-2"><i class="fas fa-bars"></i></a>
	    </div>
	    <jsp:include page="../common/menu.jsp" />
        
	    <div data-card-height="cover-card" class="card bg-1 mb-0">
	        <div class="card-center">
	            <div style="width:100%;text-align:center">
	                <img src="../images/logo_white.png" class="text-center" style="width:200px;">
	         </div> 
	            <p class="mt-1 text-center color-white font-11">클럽디청담 이용약관 동의</p>
	            <div style="max-width:300px;" class="mx-auto mb-n5">
	            	<div style="max-width:320px;" class="mx-auto mb-n5 mt-5 list-group list-custom-small"> 
						<!--전체동의-->
		                <div class="form-check icon-check " style="position:relative">
		                    <input class="form-check-input" type="checkbox" value="" id="chkAgreeAll">
		                    <label class="form-check-label font-16 color-white" style="margin-top: 1px;"  for="chkAgreeAll">약관 전체동의</label>
		                    <i class="icon-check-1 far fa-circle color-gray-dark font-20"></i>
		                    <i class="icon-check-2 far fa-check-circle font-20 color-highlight"></i> 
		                </div>			                	
		                <div class="divider divider-margins bg-gray-dark mb-3 opacity-50" style="margin:0;"></div>
		                
						<!--이용약관 동의 (필수)-->
	                    <div>
	                        <div class="form-check icon-check  mt-0 mb-0 " style="position:relative;display:inline-block">
	                            <input class="form-check-input agree-item required-item" type="checkbox" value="" id="check1">
	                            <label class="form-check-label font-16 color-white" style="margin-top: 1px;" for="check1">이용약관 동의 <span class="color-red-light">(필수)</span></label>
	                            <i class="icon-check-1 far fa-circle color-gray-dark font-18"></i>
	                            <i class="icon-check-2 far fa-check-circle font-18 color-highlight"></i>   
	                        </div> 
	                        <i data-menu="modal_agree1" class="font-20 fa fa-chevron-right accordion-icon" style="position: relative;top:-5px"></i>
	                           
	                    </div>		              
		                <div class="clear"></div>
						<!--개인정보 수집 및 이용동의 (필수)-->              
						<div>
		                    <div class="form-check icon-check  mt-0 mb-0 " style="position:relative;display:inline-block">
		                        <input class="form-check-input agree-item required-item" type="checkbox" value="" id="check2">
		                        <label class="form-check-label font-16 color-white" style="margin-top: 1px;"  for="check2">개인정보 수집 및 이용동의 <span class="color-red-light">(필수)</span></label>
		                        <i class="icon-check-1 far fa-circle color-gray-dark font-18"></i>
		                        <i class="icon-check-2 far fa-check-circle font-18 color-highlight"></i>   
		                    </div> 
		                    <i data-menu="modal_agree2" class="font-20 fa fa-chevron-right accordion-icon" style="position: relative;top:-5px"></i>
		                       
		                </div> 
		                <div class="clear"></div>
		              
		                <a href="#" class="mt-5 mb-4 btn btn-md bg-blue-dark btn-full shadow-xl text-uppercase font-800 font-18 rounded-s" id="btnNext">
		                    다음 단계</a>
		            </div>
	             
	                <!-- <div class="col-12" style="margin:0 auto">
	                    <a href="#" data-menu="modalAgree" class="btn btn-border btn-md btn-full mb-3 rounded-xl text-uppercase 
	                    font-900 border-blue-dark color-blue-dark bg-theme">약관 확인하기</a>
	                </div>
	                <div class="form-check icon-check mt-3 opacity-60"  style="margin:0 auto">
	                    <input class="form-check-input" type="checkbox" value="" id="check1">
	                    <label class="form-check-label color-white" for="check1">약관에 동의합니다</label>
	                    <i class="icon-check-1 fa fa-square color-gray-dark font-16 ml-n1"></i>
	                    <i class="icon-check-2 fa fa-check-square font-16 color-highlight ml-n1"></i>
	                </div>
	                <a href="#" class="mt-4 mb-4 btn btn-md bg-blue-dark btn-full shadow-xl text-uppercase font-800 rounded-s" id="btnNext">
	                    다음단계
	                </a> -->
	                
			       	<input type="hidden" id="msLoginCd" name="msLoginCd" value="${msLoginCd}"/>
	            </div>
	        </div>
	        <div class="card-overlay bg-black opacity-80"></div>
	    </div>
    
	</div>
<!-- </div> -->
<!-- Page content ends-->

<!-- 모달-->
<jsp:include page="../common/pop/modal.jsp" />
 
<div class="menu-hider"><div></div></div>
	<script type="text/javascript">
	
		$('#btnNext').on('click', function () {
			var sParams = "";			
			var result = true;
			
			$('.required-item').each(function () {
				var checkVal = $(this).prop('checked');
				if(!checkVal) {
					alertModal.fail("필수 약관에 동의가 필요합니다.");
					result = false;
					return;
				}
			})
			
			if(!result){
				return;
			} else {
				sParams += String.format("msMktAgreeYn=Y");
			}
			sParams += String.format("&msLoginCd={0}", $('#msLoginCd').val());			
			location.href = "<c:url value='/member/memberForm'/>" + "?" + encodeURI(sParams);
		})
				
		
		// 약관 전체동의 클릭 이벤트
		$('#chkAgreeAll').on('click', function() {
			var checkVal;
			if($('#chkAgreeAll').prop('checked') != true) {
				checkVal = false;
			} else {
				checkVal = true;
			}
			$('.agree-item').each(function() {
				$(this).prop('checked', checkVal);
			})			
		})
		
		// 약관 항목 클릭 시 전체동의 자동 체크 로직
		$('.agree-item').on('click', function() {
			var checkVal = $(this).prop('checked'); // 체크인지 체크해제인지
			var result = true;
			
			$('.agree-item').each(function() {
				if(checkVal != $(this).prop('checked')) { // 다른게 하나라도 있으면 false를 return
					result = false;
					return;
				} 
			})
			
			// 모두 체크/체크해제인 경우 전체동의에도 체크/체크해제
			if(result) {
				$('#chkAgreeAll').prop('checked', checkVal);
			} else {
				$('#chkAgreeAll').prop('checked', false);
			}

		})
			
	</script>
</body>
</html>