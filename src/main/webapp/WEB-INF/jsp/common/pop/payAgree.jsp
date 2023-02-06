<!-- 결제 약관 동의 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="modal_pay" class="menu menu-box-bottom rounded-0 " data-menu-height="530" data-menu-effect="menu-parallax" style="display: block; height: 510px;">
	<h1 class="text-center mt-4">
		<i class="fa fa-2x fa-check-circle scale-box color-red-dark shadow-xl rounded-circle"></i> 
	</h1>
	<h3 class="text-center mt-3 font-700">환불규정 약관</h3>
	<p class="boxed-text-xl opacity-70">
		클럽디청담 결제에 대한 동의가 필요합니다.<br>
	</p>
	
	<div class="accordion" id="accordion-1">
		<div class="mb-0"><div>
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
		
			<input class="form-check-input" type="checkbox" value="Y" id="chkAgree" name="chkAgree">
			<label class="form-check-label color-white" for="chkAgree">약관에 동의합니다</label>
			
			<i class="icon-check-1 fa fa-square color-gray-dark font-16 ml-n1"></i>
			<i class="icon-check-2 fa fa-check-square font-16 color-highlight ml-n1"></i>
		</div>
	</div>
	
	<div class="row mb-0 mr-3 ml-3 mt-5 mb-5">
		<div class="col-12">
			<a href="#" onClick="doPay()" class="btn btn-full btn-md color-green-dark border-green-dark font-800 text-uppercase rounded-s">
			결제요청
			</a>
		</div>
	</div>
</div>