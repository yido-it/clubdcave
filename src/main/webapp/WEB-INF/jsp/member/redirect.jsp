<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="../common/script.jsp" />
<head>
	TEST
</head>
<body>
	<script type="text/javascript">
		var dest = '${dest}';
		// 화면 작업 위해 잠시 막아둠
		/* if(dest != null) {
			location.href = dest;
		} else {	            			
    		location.href = '/main';
		} */
	</script>
</body>
</html>