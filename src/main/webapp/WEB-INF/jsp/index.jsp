<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="common/head.jsp" />
<jsp:include page="common/script.jsp" />

<body class="theme-dark">

<div id="preloader">
	<div class="spinner-border color-highlight" role="status"></div>
</div>

<div id="page">
	
	<!--Header-->
	<div class="page-title page-title-fixed" style="opacity: 1;z-index:99">
		<h1 style="font-size:20px">
			<img src="images/logo_white.png" style="width:60px;position:absolute;top:-10px" />
		</h1>
		<a href="#" class="page-title-icon shadow-xl bg-theme color-theme show-on-theme-light" data-toggle-theme=""><i class="fa fa-moon"></i></a>
		<a href="#" class="page-title-icon shadow-xl bg-theme color-theme" data-menu="menu-main" style=""><i class="fa fa-bars"></i></a>
	</div>
	<!--//Header-->

	<!--스크롤내리면 보이는 Header-->
	<div class="header header-auto-show header-fixed header-logo-center ">
		<a href="index.html" class="header-title">클럽디 청담</a>
		<a href="#" data-menu="menu-main" class="header-icon header-icon-4"><i class="fas fa-bars"></i></a>
		<a href="#" data-toggle-theme="" class="header-icon header-icon-1 show-on-theme-dark"><i class="fas fa-sun"></i></a>
		<a href="#" data-toggle-theme="" class="header-icon header-icon-1 show-on-theme-light"><i class="fas fa-moon"></i></a>
	</div>
	<!--스크롤내리면 보이는 Header-->
	
	<!-- 좌측GNB -->
	<jsp:include page="common/menu.jsp" />
	<!-- //좌측GNB-->

	<div class="">  
		<!--메인슬라이드-->
		<div class="single-slider  slider-has-arrows  owl-carousel   owl-dots-over">
			<div class="card m-0 bg-18" data-card-height="300" onClick="location.href='bay_detail.html'">
				<div class="card-bottom">
					<span class="badge bg-blue-dark px-2 py-1 ml-2 mt-2 text-uppercase">골프</span> 
					<h3 class="color-white px-3 mb-0">B1 카브주간</h3>
					<p class="px-3 color-white font-15 opacity-80 mb-4">
					풀스윙에서 제공하는 골프게임을 즐겨보세요
					</p> 
				</div>
				<div class="card-overlay bg-gradient"></div>
			</div>
			
			<div class="card m-0 bg-1" data-card-height="300" onClick="location.href='bay_detail.html'">
				<div class="card-bottom">
					<span class="badge bg-highlight px-2 py-1 ml-2 mt-2 text-uppercase">게임</span> 
					<h3 class="color-white px-3 mb-0">2F 오픈타석</h3>
					<p class="px-3 color-white font-15 opacity-80 mb-4">
					풀스윙에서 제공하는 골프게임을 즐겨보세요
					</p>
				</div>
				<div class="card-overlay bg-gradient"></div>
			</div>
		
			<div class="card m-0 bg-28" data-card-height="300" onClick="location.href='bay_detail.html'"> 
				<div class="card-bottom">
					<span class="badge bg-highlight px-2 py-1 ml-2 mt-2 text-uppercase">레슨</span> 
					<h3 class="color-white px-3 mb-0">1F 카브주간</h3>
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
		<div class="divider divider-margins"></div>
	
	
		<!-- 이벤트-->
		<jsp:include page="common/event.jsp" />
	
		<!--프로-->
		<jsp:include page="common/pro.jsp" />
		<div class="divider divider-margins"></div>
	
		<!--베이소개-->
		<jsp:include page="common/bay.jsp" />
	
	<!--//end컨텐츠-->
	</div>	


<jsp:include page="common/footer.jsp" />

<div class="divider divider-margins"></div>

<!-- Global Footer-->
<jsp:include page="common/footerBar.jsp" />

</div>
<!--   Content Ends Here-->     

</body>
</html>