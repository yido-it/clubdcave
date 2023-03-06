<!-- 하단 메뉴 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="footer-bar" class="footer-bar-6">
	<!-- 홈 -->
	<a href="javascript:location.href='/main';" id="footerMain" class="">
		<i class="fa fa-home"></i><span>홈</span><em></em>
	</a>
	
	<!-- 프로소개 -->
	<a href="javascript:location.href='/pro/proMain';" id="footerPro" class="">
		<i class="fa fa-golf-ball-tee"></i><span>프로소개</span><em></em>
	</a>
	
	<!-- 예약 -->
	<a href="javascript:location.href='/book/bookMain/001';" class="circle-nav" id="footerBook">
		<i class="fa fa-calendar-check"></i><span>예약</span><strong><u></u></strong>
	</a>
	
	<!-- 이용권 -->
	<a href="javascript:location.href='/voucher/voucherMain/001';" id="footerVoucher" class="">
		<i class="fa-solid fa-money-check"></i><span>이용권</span><em></em>
	</a>
	
	<!-- 오시는길 -->
	<a href="/map" data-menu="menu-maps">
		<i class="fa fa-map-location-dot"></i><span>오시는길</span>
	</a>
</div> 

<jsp:include page="../common/alertModal.jsp" />
<script type="text/javascript">

// 하단바 active 관련 

if (pUrl.indexOf('/main') >= 0) $('#footerMain').addClass('active-nav');	// 메인
else if (pUrl.indexOf('/pro/proMain') >= 0) $('#footerPro').addClass('active-nav');	// 프로소개
else if (pUrl.indexOf('/voucher/voucherMain') >= 0) $('#footerVoucher').addClass('active-nav'); // 이용권


function copyClipboard() {
	  const text = document.getElementById('text').textContent;
	  const textarea = document.createElement('textarea');
	  textarea.textContent = text;
	  document.body.append(textarea);
	  textarea.select();
	  document.execCommand('copy');
	  alertModal.success('주소가 복사되었습니다.');
	  textarea.remove();
	}

	const button = document.getElementById('button');
	button.addEventListener('click', copyClipboard);
	
</script>
