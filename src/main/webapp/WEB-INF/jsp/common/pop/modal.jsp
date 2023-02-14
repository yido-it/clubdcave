<!-- 모달 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 예약 결제 페이지 > 뒤로가기 모달 -->
<div id="modal_back" class="menu menu-box-modal rounded-0" data-menu-height="" data-menu-width="330"
	data-menu-effect="menu-parallax">

	<h1 class="text-center mt-2"><i class="fa-solid fa-triangle-exclamation fa-2x color-red-dark"></i></h1>
	<p class="boxed-text-xl opacity-70">
		이전페이지로 이동하시겠습니까?<br/>
		선택하신 예약정보는 모두 초기화 됩니다.
	</p>
	<div class="row mb-0 mr-3 ml-3 mb-3">
	   <div class="col-6">
	       <a href="#" class="btn close-menu btn-full btn-md bg-red-dark font-800 text-uppercase rounded-s">아니요</a>
	   </div>
	   <div class="col-6">
	       <a href="#" onClick="goBack()" class="btn close-menu btn-full btn-md bg-green-dark font-800 text-uppercase rounded-s">예</a>
	   </div>
	</div>

</div> 
<!--// 예약 결제 페이지 > 뒤로가기 모달 -->

<!-- 예약 결제 페이지 > 하단바 [예약] 클릭시 모달 -->
<div id="modal_btnBook" class="menu menu-box-modal rounded-0" data-menu-height="" data-menu-width="330"
	data-menu-effect="menu-parallax">

	<h1 class="text-center mt-2"><i class="fa-solid fa-triangle-exclamation fa-2x color-red-dark"></i></h1>
	<p class="boxed-text-xl opacity-70">
		이전페이지로 이동하시겠습니까?<br/>
		선택하신 예약정보는 모두 초기화 됩니다.<br/>
		결제하기 버튼은 화면 하단에 있습니다.
	</p>
	<div class="row mb-0 mr-3 ml-3 mb-3">
	   <div class="col-6">
	       <a href="#" class="btn close-menu btn-full btn-md bg-red-dark font-800 text-uppercase rounded-s">아니요</a>
	   </div>
	   <div class="col-6">
	       <a href="#" onClick="goBack()" class="btn close-menu btn-full btn-md bg-green-dark font-800 text-uppercase rounded-s">예</a>
	   </div>
	</div>

</div> 
<!--// 예약 결제 페이지 > 하단바 [예약] 클릭시 모달 -->

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
			<a href="#" onClick="doCancel()" class="btn btn-full btn-md color-red-dark border-red-dark font-800 text-uppercase rounded-s">
				예약취소
			</a>
		</div>
	</div>
</div>
<!--//예약취소 모달 -->

<!-- 이용권 취소 모달 -->
<div id="voucher_cancle" class="menu menu-box-modal rounded-0" data-menu-height="" data-menu-width="330"
	data-menu-effect="menu-parallax">

	<h1 class="text-center mt-2"><i class="fa-solid fa-triangle-exclamation fa-2x color-red-dark"></i></h1>
	<h3 class="text-center mt-3 font-700">이용권 취소</h3>
	<p class="boxed-text-xl opacity-70">
		 이용권을 정말 취소하시겠습니까?<br/>
		*사용중인 이용권취소는 고객센터에 연락부탁드립니다.  
	</p>
	<div class="row mb-0 mr-3 ml-3 mb-3">
	   <div class="col-6">
	       <a href="#" class="btn close-menu btn-full btn-md bg-red-dark font-800 text-uppercase rounded-s">아니요</a>
	   </div>
	   <div class="col-6">
	       <a href="#" onClick="doCancel()" class="btn close-menu btn-full btn-md bg-green-dark font-800 text-uppercase rounded-s">예</a>
	   </div>
	</div>

</div> 
<!--// 이용권 취소 모달 -->

<!-- 결제 동의 모달 (약관) -->
<div id="modal_pay" class="menu menu-box-bottom rounded-0 " data-menu-height="530" data-menu-effect="menu-parallax" style="display: block; height: 510px;">
	<h1 class="text-center mt-4">
		<i class="fa fa-2x fa-check-circle scale-box color-red-dark shadow-xl rounded-circle"></i> 
	</h1>
	<h3 class="text-center mt-3 font-700">환불규정에 대한 동의</h3>
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
			<label class="form-check-label color-white" for="chkAgree" style="padding: 0px 15px 0px 32px;">위 내용을 확인하였고 이에 동의합니다</label>
			
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
<!-- // 결제 동의 모달 (약관) -->