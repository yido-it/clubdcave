<!-- 이벤트 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div class="content"> 
	<div class="mb-0">
	
		<h1 class="font-700 ml-1">EVENT</h1>
	
		<div class="single-slider-boxed owl-carousel owl-no-dots">
			<!-- 이벤트 -->
			<c:forEach items="${eList}" var="event" varStatus="status">
			<div class="item" onclick="location.href='/boardView/${event.idx}'">
				<div class="card card-style mx-0 bg-9" data-card-height="250" style="background-image: url(${event.fileURL});">
					<div class="card-bottom m-3">
                		<!-- 시작일~종료일 -->
						<p class="color-white mb-0 opacity-80">${event.startDay}~${event.endDay}</p>
						<!-- 제목 -->
						<h2 class="color-white">${event.title}</h2>
					</div>
					<div class="card-overlay bg-gradient"></div>
				</div>
			</div>
			</c:forEach>
			
		</div>    
	</div>	 
	<div class="divider divider-margins mb-0"></div>
</div>