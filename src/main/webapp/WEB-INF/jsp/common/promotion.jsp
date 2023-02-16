<!-- 프로모션 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content mt-1">
	<div class="">
		<h1 class="font-700  ml-1">PROMOTION</h1>
	
		<!-- 
		프로모션 1개 일때
	 	<div class="cover-slider owl-carousel owl-no-dots owl-no-controls" style="touch-action: auto;">
			<div class="item" onclick="location.href='event'">
				<div class="card card-style mx-0 bg-9" data-card-height="150" style="background-image: url(/images/event/promotion.jpg);">
					<div class="card-bottom m-3">
						<p class="color-white mb-0 opacity-90">02/01~02/03</p>
						<h2 class="color-white">박세리프로 프로모션</h2>
					</div>
					<div class="card-overlay bg-gradient"></div>
				</div>
			</div>
		</div>
		-->
				
		<div class="single-slider owl-no-controls owl-carousel owl-no-dots">
		<c:forEach items="${pList}" var="pro" varStatus="status">
            <div data-card-height="150" class="card card-style bg-5 mb-0 rounded-m shadow-l" style="background-image: url(${pro.fileURL});" onclick="location.href='/boardView/${pro.idx}'">
                <div class="card-bottom ml-2  mb-3">
                	<!-- 시작일~종료일 -->
                   	<p class="color-white mb-0 opacity-90">${pro.startDay}~${pro.endDay}</p>
                   	<!-- 제목 -->
					<h2 class="color-white">${pro.title}</h2>
                </div>
                <div class="card-overlay bg-gradient"></div>
            </div> 
        </c:forEach>  
        </div> 
	</div>	
</div>