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
			<a href="#" class="color-white header-title header-subtitle">약관 확인하기</a>
	    	<jsp:include page="../common/top.jsp" />
	    </div>
	    <jsp:include page="../common/menu.jsp" />
        
	    <div data-card-height="cover-card" class="card bg-1">
	        <div class="card-center">
	            <div style="width:100%;text-align:center">
	                <img src="../images/logo_white.png" class="text-center" style="width:200px;">
	         </div> 
	            <p class="mt-1 text-center color-white font-11">클럽디청담 이용약관 동의</p>
	            <div style="max-width:300px;" class="mx-auto mb-n5">
	             
	                <div class="col-12" style="margin:0 auto">
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
	                </a>
			       	<input type="hidden" id="msLoginCd" name="msLoginCd" value="${msLoginCd}"/>
	            </div>
	        </div>
	        <div class="card-overlay bg-black opacity-80"></div>
	    </div>
    
	</div>
<!-- </div> -->
<!-- Page content ends-->

<!--모달-->
<div id="modalAgree" class="menu menu-box-modal rounded-0" style="background-color: #303030;z-index:99999;padding: 15px 0;" data-backdrop="static" data-keyboard="false"
	data-menu-height="auto" data-menu-width="330" data-menu-effect="menu-parallax">
<h3 class="text-center mt-3 font-600">이용약관</h3>
<div class="accordion" id="accordion-1">
    <div class="mb-0">
        <button class="btn accordion-btn border-0 color-theme" data-toggle="collapse" data-target="#collapse1">
            <i class="fa-solid fa-play color-red-dark mr-2"></i>
            CLUB D 통합회원 이용 약관
            <i class="fa fa-chevron-down font-10 accordion-icon"></i>
        </button>
        <div id="collapse1" class="collapse" data-parent="#accordion-1">
            <div class="py-2 pl-3 pr-3"style="overflow-y: scroll;height: 150px;">
                - 제1장 총칙 -

                    제1조 (목적)
                    본 약관은 클럽디(이하 “CLUB D”라 함)및 그 계열사(이하 “회사”라 함)가 제공/운영하는 웹사이트 (이하 “통합회원 홈페이지”라 함)와 스마트폰 등 이동통신기기를 통해 제공되는 CLUB D 모바일 애플리케이션(이하 “CLUB D 앱”이라 함)을 통해서 제공되는 고객 관련 서비스(이하 “서비스”라 함)를 하나의 ID(이하 “아이디”라 함) 와 PASSWORD(이하 “비밀번호”라 함)로 이용함에 있어 회사 및 회원의 제반 권리, 의무, 관련 절차 등을 규정하는데 그 목적이 있습니다.

                    제2조 (용어의 정의)
                    본 약관에서 사용하는 주요 용어의 정의는 다음과 같습니다.
                    1. “통합회원 홈페이지”란 회원이 온라인을 통해 서비스를 이용할 수 있는 “회사”의 인터넷 사이트들을 말하며, 추후 변동될 수 있습니다. 각 통합회원 홈페이지에서 서비스를 원활히 이용하고자 하는 회원은 “회사”에서 정하는 회원 가입 절차를 거쳐야 합니다.
            </div>
        </div>
    </div>
    <div class="mb-0">
        <button class="btn accordion-btn border-0 color-theme"  data-toggle="collapse" data-target="#collapse2">
            <i class="fa-solid fa-play color-yellow-dark mr-2"></i>
            개인정보 제공/취급위탁에 대한 동의
            <i class="fa fa-chevron-down font-10 accordion-icon"></i>
        </button>
        <div id="collapse2" class="collapse"  data-parent="#accordion-1">
            <div class="py-2 pl-3 pr-3" style="overflow-y: scroll;height: 150px;">
                - 제1장 총칙 -

                    제1조 (목적)
                    본 약관은 클럽디(이하 “CLUB D”라 함)및 그 계열사(이하 “회사”라 함)가 제공/운영하는 웹사이트 (이하 “통합회원 홈페이지”라 함)와 스마트폰 등 이동통신기기를 통해 제공되는 CLUB D 모바일 애플리케이션(이하 “CLUB D 앱”이라 함)을 통해서 제공되는 고객 관련 서비스(이하 “서비스”라 함)를 하나의 ID(이하 “아이디”라 함) 와 PASSWORD(이하 “비밀번호”라 함)로 이용함에 있어 회사 및 회원의 제반 권리, 의무, 관련 절차 등을 규정하는데 그 목적이 있습니다.

                    제2조 (용어의 정의)
                    본 약관에서 사용하는 주요 용어의 정의는 다음과 같습니다.
                    1. “통합회원 홈페이지”란 회원이 온라인을 통해 서비스를 이용할 수 있는 “회사”의 인터넷 사이트들을 말하며, 추후 변동될 수 있습니다. 각 통합회원 홈페이지에서 서비스를 원활히 이용하고자 하는 회원은 “회사”에서 정하는 회원 가입 절차를 거쳐야 합니다.
            </div>
        </div>
    </div>
    <div class="mb-2">
        <button class="btn accordion-btn border-0 color-theme" data-toggle="collapse" data-target="#collapse3">
             <i class="fa-solid fa-play color-blue-dark mr-2"></i>
            마케팅 활용 동의서
            <i class="fa fa-chevron-down font-10 accordion-icon"></i>
        </button>
        <div id="collapse3" class="collapse"  data-parent="#accordion-1">
            <div class="py-2 pl-3 pr-3"style="overflow-y: scroll;height: 150px;">
                - 제1장 총칙 -

                    제1조 (목적)
                    본 약관은 클럽디(이하 “CLUB D”라 함)및 그 계열사(이하 “회사”라 함)가 제공/운영하는 웹사이트 (이하 “통합회원 홈페이지”라 함)와 스마트폰 등 이동통신기기를 통해 제공되는 CLUB D 모바일 애플리케이션(이하 “CLUB D 앱”이라 함)을 통해서 제공되는 고객 관련 서비스(이하 “서비스”라 함)를 하나의 ID(이하 “아이디”라 함) 와 PASSWORD(이하 “비밀번호”라 함)로 이용함에 있어 회사 및 회원의 제반 권리, 의무, 관련 절차 등을 규정하는데 그 목적이 있습니다.

                    제2조 (용어의 정의)
                    본 약관에서 사용하는 주요 용어의 정의는 다음과 같습니다.
                    1. “통합회원 홈페이지”란 회원이 온라인을 통해 서비스를 이용할 수 있는 “회사”의 인터넷 사이트들을 말하며, 추후 변동될 수 있습니다. 각 통합회원 홈페이지에서 서비스를 원활히 이용하고자 하는 회원은 “회사”에서 정하는 회원 가입 절차를 거쳐야 합니다.
            </div>
        </div>
    </div>
</div>
<div class="row mb-0 mr-3 ml-3">
   <div class="col-12">
       <a href="#" class="btn close-menu btn-full btn-m bg-blue-dark font-800 text-uppercase rounded-s">확인</a>
   </div>
</div>
</div>  
<div class="menu-hider"><div></div></div>
<jsp:include page="../common/alertModal.jsp" />  
	<script type="text/javascript">
	
		$('#btnNext').on('click', function () {
			var sParams = "";			
			var agree1 = $("input[id=check1]:checked").prop('checked');
	
			if(agree1 != true){
				alertModal.fail("약관에 동의가 필요합니다.");			
				return;
			} else {
				sParams += String.format("msMktAgreeYn=Y");
			}
			
			sParams += String.format("&msLoginCd={0}", $('#msLoginCd').val());			
			location.href = "<c:url value='/member/memberForm'/>" + "?" + encodeURI(sParams);
		})
				
		/* $('.agree-check').on('change', function() {
			var checkVal = $(this).prop('checked');
			var result = true;
			$('.agree-check').each(function() {
				if($(this).prop('checked') != checkVal) {
					result = false;
				} 
			})
			$('#chkAgreeAll').prop('checked', result);
		})
		
		$('#chkAgreeAll').on('click', function() {
			var checkVal;
			if($('#chkAgreeAll').prop('checked') != true) {
				checkVal = false;
			} else {
				checkVal = true;
			}
			$('.agree-check').each(function() {
				$(this).prop('checked', checkVal);
			})			
			$('#chkAgreeAll').prop('checked', checkVal);
		}) */
			
	</script>
</body>
</html>