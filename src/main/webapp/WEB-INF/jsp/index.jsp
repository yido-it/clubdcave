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
					<h1 class="color-white px-3 mb-0">CLUBD CAVE GOLF<span class="badge bg-blue-dark px-2 py-1 ml-2 mt-2 text-uppercase">B1</span></h1>
					<p class="px-3 color-white font-13 opacity-80 mb-4">
					 클럽디청담 FULLSWING TOPGOLF SWING SUITE  
					</p> 
				</div>
				<div class="card-overlay bg-gradient"></div>
			</div>
			
			<div class="card m-0 bg-main_slide02" data-card-height="300" onClick="goBayDetail(2);"  style="background: url(/images/gallery/rest_main.jpg) no-repeat 0 0;background-size:cover">
				<div class="card-bottom">
				 	<h1 class="color-white  px-3 mb-0">CLUBD CAVE BISTRO <span class="badge bg-blue-dark px-2 py-1 ml-1 mt-2 text-uppercase">B1</span></h1>
					<p class="px-3 color-white font-13 opacity-80 mb-4">
					클럽디카브 BISTRO FOOD AND WINE 

					</p>
				</div>
				<div class="card-overlay bg-gradient"></div>
			</div>
		
			<div class="card m-0 bg-main_slide03" data-card-height="300" onClick="goBayDetail(3);"> 
				<div class="card-bottom">
					<h1 class="color-white px-3 mb-0">Reception <span class="badge bg-blue-dark px-2 py-1 ml-1 mt-2 text-uppercase">1F</span></h1>
					<p class="px-3 color-white font-13 opacity-80 mb-4">
					클럽디청담, FULLSWING, TINO5
					</p>
				</div>
				<div class="card-overlay bg-gradient"></div>
			</div>
				<div class="card m-0 " data-card-height="300" onClick="goBayDetail(4);" style="background: url(/images/gallery/open_main2.jpg) no-repeat 0 0;background-size:cover"> 
				<div class="card-bottom">
					<h1 class="color-white  px-3 mb-0">OPEN BAY & SHORT GAME AREA <span class="badge bg-blue-dark px-2 py-1 ml-1 mt-2 text-uppercase">2F</span></h1>
					<p class="px-3 color-white font-13 opacity-80 mb-4">
					VIRTUALGREEN, PUTTVIEW, BUNKER, OPENBAY
					</p>
				</div>
				<div class="card-overlay bg-gradient"></div>
			</div>
				<div class="card m-0 bg-main_slide05" data-card-height="300" onClick="goBayDetail(5);"  style="background: url(/images/gallery/lesson_main.jpg) no-repeat 0 0;background-size:cover"> 
				<div class="card-bottom">
				<h1 class="color-white  px-3 mb-0">PRIVATE ROOM & TINO 5<span class="badge bg-blue-dark px-2 py-1 ml-1 mt-2 text-uppercase">3F</span></h1>
					<p class="px-3 color-white font-13 opacity-80 mb-4">
					1:1LESSON, TINO5, CLUBFITTING 
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
	 <div class="divider divider-margins"></div>
		
		<jsp:include page="common/bay.jsp" />  
	
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