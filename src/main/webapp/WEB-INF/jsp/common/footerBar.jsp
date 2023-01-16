<!-- 하단 메뉴 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="footer-bar" class="footer-bar-6">
	<!-- 홈 -->
	<a href="javascript:location.href='/main';" class="active-nav">
		<i class="fa fa-home"></i><span>홈</span><em></em>
	</a>
	
	<!-- 프로소개 -->
	<a href="javascript:location.href='/pro';">
		<i class="fa fa-golf-ball-tee"></i><span>프로소개</span>
	</a>
	
	<!-- 예약 -->
	<a href="javascript:location.href='/book/bookMain';" class="circle-nav">
		<i class="fa fa-calendar-check"></i><span>예약</span><strong><u></u></strong>
	</a>
	
	<!-- 이용권 -->
	<a href="javascript:location.href='/voucher/voucherMain';">
		<i class="fa fa-coins"></i><span>이용권</span>
	</a>
	
	<!-- 오시는길 -->
	<a href="/map" data-menu="menu-maps">
		<i class="fa fa-map-location-dot"></i><span>오시는길</span>
	</a>
</div> 

<!-- 오시는길팝업 -->
<div id="menu-maps" class="menu menu-box-bottom rounded-0" data-menu-height="550" data-menu-effect="menu-parallax">
	<div class="menu-title">
		<h3 class="ml-3">오시는길</h3>
		<a href="#" class="close-menu"><i class="fa fa-times-circle"></i></a>
	</div>
	
	<div class='responsive-iframe max-iframe mb-n3'>
		<iframe src='https://maps.google.com/?ie=UTF8&ll=47.595131,-122.330414&spn=0.006186,0.016512&t=h&z=17&output=embed' frameborder='0' allowfullscreen></iframe>
	</div>
	
	<div class="content">
		<h3 class="font-700">Address</h3>
		<p class="pb-0 mb-0">서울시 청담로 클럽디청담</p>
		<p class="pb-0 mb-0">Seoul, CheongdamRo 133</p>
	
		<div class="divider mt-2 mb-3"></div>
		
		<h3 class="font-700">Contact</h3>
		<div class="list-group list-custom-small">
			<a href="tel:+1 234 567 890">
				<i class="fa font-14 fa-phone color-phone"></i>
				<span>+82 02-1313-1313</span>
				<span class="badge bg-highlight">전화걸기</span>
				<i class="fa fa-angle-right"></i>
			</a>        
			<a href="mailto:mail@domain.com">
				<i class="fa font-14 fa-envelope color-mail"></i>
				<span>cheongdam@yido.com</span>
				<span class="badge bg-highlight">메일보내기</span>
				<i class="fa fa-angle-right"></i>
			</a>         
		</div>
	</div>   
</div>
<!--//오시는길팝업-->
