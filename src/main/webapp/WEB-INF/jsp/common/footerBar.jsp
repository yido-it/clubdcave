<!-- 하단 메뉴 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- localhost용 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b61eed986791fe4d57df0aa6a7026339&libraries=services"></script> 

<!-- 실서버용	
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8a48549102e1a7ae0002b90f05f92c98&libraries=services"></script>-->

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

<!-- 오시는길팝업 -->
<div id="menu-maps" class="menu menu-box-bottom rounded-0" data-menu-height="600" data-menu-effect="menu-parallax">
	<div class="menu-title">
		<h3 class="ml-3">오시는길</h3>
		<a href="#" class="close-menu"><i class="fa fa-times-circle"></i></a>
	</div>
		
	<div id="map"style="width: 100%; height: 232px;"></div>
	
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
<input type="hidden" id="lati" value="37.52279535409807" />
<input type="hidden" id="longi" value="127.05617738572018" />

<script type="text/javascript">

// 하단바 active 관련 
if (pUrl.indexOf('/main') >= 0) $('#footerMain').addClass('active-nav');	// 메인
else if (pUrl.indexOf('/pro/proMain') >= 0) $('#footerPro').addClass('active-nav');	// 프로소개
else if (pUrl.indexOf('/voucher/voucherMain') >= 0) $('#footerVoucher').addClass('active-nav'); // 이용권
// end.

//lati 또는 longi값이 없으면 주소로 검색
var mapContainer = document.getElementById('map'); // 지도를 표시할 div 
var mapOption = { 
        center: new kakao.maps.LatLng($('#lati').val(), $('#longi').val()), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
};
var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		
if($('#lati').val() == ''  || $('#longi').val() == ''){
	var geocoder = new kakao.maps.services.Geocoder();
	geocoder.addressSearch('${loc.addr}', function(result, status) {
	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {
	    	var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	    	setMarker(map, coords);	    	
	    } 
	});    
} else {
	var coords = new kakao.maps.LatLng($('#lati').val(), $('#longi').val());
	setMarker(map, coords);
}
    
//지점 마커표시   
function setMarker(map, coords){
	var marker = new kakao.maps.Marker({
        map: map,
        position: coords
    });

    // 인포윈도우로 장소에 대한 설명을 표시합니다
    var infowindow = new kakao.maps.InfoWindow({
        content: '<div style="width:200px;text-align:center;padding:6px 0;">서울 강남구 청담동 131-17</div>'
    });
    infowindow.open(map, marker);

    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    map.setCenter(coords);
    
    //map 생성전 해당 div영역이 hidden시키면 지도가 깨짐. 지도 생성후 map영역 hidden
    $('#pills-map').hide();
}
</script>
