<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="../common/script.jsp" />
<head>
	TEST
</head>
<body>
	<button type="button" onclick="location='/main'">메인가기(삭제할거임)</button>
	<script type="text/javascript">
		var dest = '${dest}';
		//location.href = dest;
		$(document).ready(function(){	
			//location.href = dest;		
		})
	</script>
</body>
</html>