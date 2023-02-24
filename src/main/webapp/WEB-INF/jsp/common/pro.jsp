<!-- 프로 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!--앰베서더-->
    <div class="content mt-3">
         <h1 class="font-700  ml-1">
         AMBASSADOR</h1>

		 <div class="cover-slider owl-carousel owl-no-dots owl-no-controls" style="touch-action: auto;">
			<div class="item" onclick="location.href=''">
				<div class="card card-style mx-0 bg-9" data-card-height="150" style="background: url(images/gallery/choipro.jpg) no-repeat 0 0; background-size: cover;">
					<div class="card-bottom m-3">
						<p class="color-white mb-0 opacity-90">앰베서더</p>
						<h2 class="color-white">최나연 프로</h2>
					</div>
					<div class="card-overlay bg-gradient"></div>
				</div>
			</div>
		</div>    
         
      <!-- 슬라이드 2개 이상일경우   
      
      	<div class="single-slider owl-no-controls owl-carousel owl-no-dots">
            <div data-card-height="170" class="card card-style bg-5 mb-0 rounded-m shadow-l"  style="background: url(images/gallery/choipro.jpg) no-repeat 0 0; background-size: cover;">
                <div class="card-bottom ml-2  mb-3">
                       <p class="color-white mb-0 opacity-90">앰베서더</p>
                         <h2 class="color-white">최나연 프로</h2>
                </div>
                <div class="card-overlay bg-gradient"></div>
            </div>
       
        </div>    -->
    </div>
    
<div class="content mt-3">
	<div class="">
		<h1 class="font-700  ml-1">PRO GOLFER</h1>
		
		<c:choose>
		    <c:when test="${not empty proList}">
			<div class="double-slider owl-carousel owl-no-dots mb-4" style="">			
				<c:forEach var="item" items="${proList}" varStatus="status">
				<div class="item" style="">
					<c:choose>
					<c:when test="${item.msImgName != null && item.msImgName != ''}">
						<div class="card card-style mx-0 bg-pro1" data-card-height="230" style="background: url(${item.fileURL}) no-repeat 0 0; background-size: cover;">
					</c:when>
					<c:otherwise>					
						<div class="card card-style mx-0 bg-pro1 bg-empty_profile" data-card-height="230">
					</c:otherwise>
					</c:choose>
						<div class="card-bottom">
							<h2 class="text-center color-white pb-4 font-700">${item.msName} 프로</h2>
						</div>
						<div class="card-overlay bg-gradient"></div>
					</div>
					<a href="javascript:location.href='pro/proDetail?msNum=${item.msNum}'" class="btn btn-sm btn-center-xs bg-highlight under-slider-btn text-uppercase font-11 font-900 rounded-sm">프로필</a>
				</div>
				</c:forEach>			
			</div>
			</c:when>
			<c:otherwise>
	         	등록된 프로 정보가 없습니다.
	         </c:otherwise>
        </c:choose>
	</div>
</div>