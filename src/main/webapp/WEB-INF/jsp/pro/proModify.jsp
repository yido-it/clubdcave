<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />
<body class="is-not-ios theme-light">
    
<div id="preloader"><div class="spinner-border color-highlight" role="status"></div></div>
<div class="header header-fixed header-logo-app header-transparent">
    <a href="#" class="color-white header-title header-subtitle">프로소개</a>
    <a href="#" data-back-button class="color-white header-icon header-icon-1"><i class="fa fa-arrow-left"></i></a>
    <a href="#" data-menu="menu-main" class="color-white header-icon header-icon-2"><i class="fas fa-bars"></i></a>
</div>
<jsp:include page="../common/menu.jsp" />

	<div>
	<%-- <c:if test="${sessionScope.msMember.msNum eq proInfo.msNum}"> --%>
	<button type="button" onclick="javascript:location.href='/pro/proForm'" >프로필 수정</button>
	<%-- </c:if> --%>
		${proInfo.msName}
		<c:forEach items="${proNoticeList}" var="item" varStatus="status">
		<div id="youTube">
			<c:if test="${item.noticeDiv == '003'}">
			${item.proRemark}
			</c:if>
		</div>
		<div id="kakaoChat">
			<c:if test="${item.noticeDiv == '005'}">
			${item.proRemark}
			</c:if>
		</div>
		<div id="instagram">
			<c:if test="${item.noticeDiv == '006'}">
			${item.proRemark}
			</c:if>
		</div>
		<!-- 경력, 레슨 계획 -->
		<div id="profileText">
			<c:if test="${item.noticeDiv == '001'}">
			${item.proRemark}
			</c:if>
			<c:if test="${item.noticeDiv == '002'}">
			${item.proRemark}
			</c:if>
		</div>
		<div id="imgArea">
		</div>
		</c:forEach>
	</div>
<div class="menu-hider"><div></div></div>
<jsp:include page="../common/alertModal.jsp" />  
<script type="text/javascript">	
	$('#btnEdit').on('click', function() {
		
	})
	
	function getProList(){			
	   	$.ajax({
	             url : "/pro/getProList"
	           , type : "post"
	           , dataType : 'json'
	   		, contentType : 'application/x-www-form-urlencoded; charset=UTF-8'
	   		, success : function(data) {
	           	if(data.rows.length > 0) {
	           		drawProList(data.rows);
	           	}
	           }
	   		, error : function(data) {
	   			
	           }
	       })
	}
</script>
</body>
</html>