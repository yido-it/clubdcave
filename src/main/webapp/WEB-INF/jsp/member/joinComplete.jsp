<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<html>

<jsp:include page="../common/script.jsp" />
<head>
</head>
<body>
	<div id="wrap">
		<div class="mainTitle">
			<img src="" alt=""> 가입 완료 <img src="<c:url value=''/>" alt="">
		</div>
		<div class="contents">
			가입을 축하드립니다! 잠시 후 메인으로 이동합니다.
			<button type="button" onclick="location.href='/main'" class="btn btn-dark" id="btnMain">메인으로</button>
		</div>	
	</div>  
	<script type="text/javascript">	
		setTimeout(() => 
			goMain()
			, 3000
			)
			
		function goMain() {
			var sUrl = "<c:url value='/main'/>"; 
			location.href = sUrl;
		}
	</script>
</body>
</html>