<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<html>

<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />
<head>
</head>
<body>
	<div class="mainTitle">
		<img src="" alt="">프로 상세페이지<img src="<c:url value=''/>" alt="">
	</div>
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
		<div id="kakao">
		</div>
		<div id="instagram">
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