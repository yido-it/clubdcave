<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="menu-main"  data-menu-active="nav-welcome"   class="menu menu-box-left"   data-menu-width="250" data-menu-effect="menu-parallax">
	
	<!-- 상단 로그인 버튼 -->
	<div class="text-center pt-4 notch-clear">
		<a href="javascript:location.href='/main'"><h1 class="text-uppercase font-800"><span class="color-highlight font-800">클럽디 </span>청담</h1></a>
	
		<c:if test="${empty sessionScope.msMember.msId}">
			<!-- 비로그인 상태 -->
			<div class="row mb-0" style="margin:0 auto">
				<div class="col-6 pr-1">
					<a href="javascript:location.href='/login'" class="btn btn-border btn-m btn-full mb-3 rounded-sm text-uppercase font-900 border-blue-dark color-blue-dark bg-theme">
					로그인
					</a>
				</div>
				<div class="col-6  pr-2">
					<a href="javascript:location.href='/member/agree?msLoginCd=APP'" 
						class="btn btn-border btn-m btn-full mb-3 rounded-sm text-uppercase font-900 border-green-dark color-green-dark bg-theme">
					회원가입
					</a>
				</div>
			</div>
		</c:if>
		
		<c:if test="${!empty sessionScope.msMember.msId}">
			<!-- 로그인 상태 -->
			<p class="mt-n3 font-13 opacity-50 mb-3">${sessionScope.msMember.msName} 회원님의 방문을 환영합니다.</p>		
		</c:if>	
	</div> 
	
	<div class="divider mt-3 mb-3"></div>
	
	<div class="menu-list">
		<!-- 이용관련 메뉴 -->
		<h6 class="ml-1 font-14 font-400">이용관련</h6>
		<a href="guide.html" id="nav-welcome">
			<i class="fa fa-star color-yellow-dark"></i>이용안내<i class="fa fa-angle-right"></i>
		</a>
		
		<a href="facility.html" id="nav-homepages">
			<i class="fa fa-file color-green-dark"></i>시설안내<i class="fa fa-angle-right"></i>
		</a>
		
		<a href="map.html" data-menu="menu-maps" id="nav-components">
			<i class="fa fa-map-location-dot color-blue-dark"></i>오시는길<i class="fa fa-angle-right"></i>
		</a>
		
		<a href="javascript:location.href='/pro/proMain'" id="nav-navigation">
			<i class="fa fa-user color-magenta-dark"></i>프로정보<i class="fa fa-angle-right"></i>
		</a>
		
	<!-- 	<a href="event.html" id="nav-pages">
			<i class="fa fa-gift color-green-light"></i>이벤트<i class="fa fa-angle-right"></i>
		</a> -->
		
		<a href="board.html" id="nav-media">
			<i class="fa fa-list color-teal-dark"></i>공지사항<i class="fa fa-angle-right"></i>
		</a>
		<div class="divider mt-3 mb-3"></div>
		<!-- 개인설정 메뉴 -->
		<h6 class="ml-1 font-14 font-400">개인설정</h6>
		<a href="javascript:location.href='/voucher/voucherMain/001';"" id="nav-starters">
			<i class="fa-solid fa-money-check color-red-dark"></i>이용권<i class="fa fa-angle-right"></i>
		</a>
		
		<a href="javascript:location.href='member/memberModify'" id="nav-templates">
			<i class="fa fa-pen color-brown-dark"></i>정보수정<i class="fa fa-angle-right"></i>
		</a>
		
		<a href="javascript:location.href='/book/bookList/001';" id="nav-contact">
			<i class="fa fa-calendar-check color-blue-dark"></i>예약내역<i class="fa fa-angle-right"></i>
		</a>
	
		<c:if test="${!empty sessionScope.msMember.msId}">
			<!-- 로그인 상태 일때-->
			<a href="#" class="" id="btnLogout">
				<i class="fa fa-right-to-bracket color-red-dark"></i>로그아웃<i class="fa fa-angle-right"></i>
			</a>
		</c:if>	
	
	</div>
	
	<br/><br/><br/><br/>
	<!-- 
	<div class="menu-list">
	<a href="index.html" id="nav-welcome"><i class="fa fa-star color-yellow-dark"></i>Welcome<i class="fa fa-angle-right"></i></a>
	<a href="homepages.html" id="nav-homepages"><i class="fa fa-home color-green-dark"></i>Homepages<i class="fa fa-angle-right"></i></a>
	<a href="components.html" id="nav-components"><i class="fa fa-cog color-blue-dark"></i>Components<i class="fa fa-angle-right"></i></a>
	<a href="navigations.html" id="nav-navigation"><i class="fa fa-bars color-magenta-dark"></i>Navigations<i class="fa fa-angle-right"></i></a>
	<a href="pages.html" id="nav-pages"><i class="fa fa-file color-green-light"></i>Site Pages<i class="fa fa-angle-right"></i></a>
	<a href="media.html" id="nav-media"><i class="fa fa-camera color-teal-dark"></i>Media<i class="fa fa-angle-right"></i></a>
	<a href="walkthroughs.html" id="nav-starters"><i class="fa fa-heart color-red-dark"></i>Starters<i class="fa fa-angle-right"></i></a>
	<a href="templates.html" id="nav-templates"><i class="fa fa-shopping-bag color-brown-dark"></i>Templates<i class="fa fa-angle-right"></i></a>
	<a href="contact.html" id="nav-contact"><i class="fa fa-envelope color-blue-dark"></i>Contact<i class="fa fa-angle-right"></i></a>
	<a href="#" class="close-menu"><i class="fa fa-times color-red-dark"></i>Close<i class="fa fa-angle-right"></i></a>
	</div>
	
	<div class="divider mt-3 mb-3"></div>
	<p class="text-center font-10">Copyright <span class="copyright-year"></span> - Enabled. All rights reserved.</p> 
	-->
</div>   

<!-- 
<div id="menu-colors" data-menu-load="menu-colors.html" data-menu-effect="menu-parallax" class="menu menu-box-right"  data-menu-width="75"></div>   
<div id="menu-share"  data-menu-load="menu-share.html"  data-menu-effect="menu-parallax" class="menu menu-box-bottom" data-menu-height="323"></div>   
-->

<script type="text/javascript">
	$('#btnLogout').on('click', function() {
		if(confirm('로그아웃 하시겠습니까?')) {
			location.href = '/succ-logout';
		}
	})
</script>

