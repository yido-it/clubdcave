<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="common/head.jsp" />
<jsp:include page="common/script.jsp" />

<style>
.owl-dots{transform: translateY(-30px) !important;}/*메인슬라이드 닷 위치조정 owl-dots-over*/
</style>
<body class="theme-dark">

<div id="preloader"><div class="spinner-border color-highlight" role="status"></div></div>

<div id="page">
	
	<!--Header-->
	<div class="page-title page-title-fixed" style="opacity: 1;z-index:99;position:absolute">
		<h1 style="font-size:20px">
			<img src="images/logo_white.png" style="width:60px;position:absolute;top:-10px" />
		</h1>		
		<a href="#" class="page-title-icon shadow-xl bg-theme color-theme" data-menu="menu-main"><i class="fa fa-bars font-20"></i></a>
	</div>
	<!--//Header-->

	<!--스크롤내리면 보이는 Header-->
	<div class="header header-auto-show header-fixed header-logo-center ">
		<a href="javascript:void(0);" class="header-title">클럽디 청담</a>
		<a href="#" data-menu="menu-main" class="header-icon header-icon-4 mt-1"><i class="fas fa-bars font-18"></i></a>
	</div>
	<!--스크롤내리면 보이는 Header-->
	
	<!-- 좌측GNB -->
	<jsp:include page="common/menu.jsp" />
	<!-- //좌측GNB-->

	<div class="">  
		<!--메인슬라이드-->
		<div class="single-slider  slider-has-arrows    owl-carousel">
			<div class="card m-0 bg-main_slide01" data-card-height="300" onClick="goBayDetail(1);">
				<div class="card-bottom">
					<h1 class="color-white px-3 mb-0">CLUBD CAVE<span class="badge bg-blue-dark px-2 py-1 ml-2 mt-2 text-uppercase">B1</span></h1>
					<p class="px-3 color-white font-15 opacity-80 mb-4">
					풀스윙에서 제공하는 골프게임을 즐겨보세요
					</p> 
				</div>
				<div class="card-overlay bg-gradient"></div>
			</div>
			
			<div class="card m-0 bg-main_slide02" data-card-height="300" onClick="goBayDetail(2);">
				<div class="card-bottom">
					<!-- <span class="badge bg-highlight px-2 py-1 ml-2 mt-2 text-uppercase">게임</span>  -->
					<h1 class="color-white  px-3 mb-0">CAVE Restaurant <span class="badge bg-blue-dark px-2 py-1 ml-1 mt-2 text-uppercase">B1</span></h1>
					<p class="px-3 color-white font-15 opacity-80 mb-4">
					풀스윙에서 제공하는 골프게임을 즐겨보세요
					</p>
				</div>
				<div class="card-overlay bg-gradient"></div>
			</div>
		
			<div class="card m-0 bg-main_slide03" data-card-height="300" onClick="goBayDetail(3);"> 
				<div class="card-bottom">
					<h1 class="color-white px-3 mb-0">Pop-up Store <span class="badge bg-blue-dark px-2 py-1 ml-1 mt-2 text-uppercase">1F</span></h1>
					<p class="px-3 color-white font-15 opacity-80 mb-4">
					풀스윙에서 제공하는 골프게임을 즐겨보세요
					</p>
				</div>
				<div class="card-overlay bg-gradient"></div>
			</div>
				<div class="card m-0 bg-main_slide04" data-card-height="300" onClick="goBayDetail(4);"> 
				<div class="card-bottom">
					<h1 class="color-white  px-3 mb-0">오픈타석 <span class="badge bg-blue-dark px-2 py-1 ml-1 mt-2 text-uppercase">2F</span></h1>
					<p class="px-3 color-white font-15 opacity-80 mb-4">
					풀스윙에서 제공하는 골프게임을 즐겨보세요
					</p>
				</div>
				<div class="card-overlay bg-gradient"></div>
			</div>
				<div class="card m-0 bg-main_slide05" data-card-height="300" onClick="goBayDetail(5);"> 
				<div class="card-bottom">
				<h1 class="color-white  px-3 mb-0">Lesson & TINO5<span class="badge bg-blue-dark px-2 py-1 ml-1 mt-2 text-uppercase">3F</span></h1>
					<p class="px-3 color-white font-15 opacity-80 mb-4">
					풀스윙에서 제공하는 골프게임을 즐겨보세요
					</p>
				</div>
				<div class="card-overlay bg-gradient"></div>
			</div>
		</div>
		<!--//메인슬라이드-->
	
		<!--프로모션-->
		<jsp:include page="common/promotion.jsp" />
	
		<!-- 이벤트-->
		<jsp:include page="common/event.jsp" />
	
		<!--프로-->
		<jsp:include page="common/pro.jsp" />
			
		<!--베이소개-->
		<%-- <div class="divider divider-margins"></div>
		
		<jsp:include page="common/bay.jsp" /> --%>
	
	<!--//end컨텐츠-->
	</div>	

	<div class="divider divider-margins"></div>

</div> 

<jsp:include page="common/footer.jsp" />
<!-- 하단바 -->
<jsp:include page="common/footerBar.jsp" />

</body>

<script type="text/javascript">
function goBayDetail(idx) {
	location.href='/bay'+idx;
}
</script>
</html>