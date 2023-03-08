<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="../common/script.jsp" />
<head>
	TEST
	TEST
	TEST
</head>
<body>
	<script type="text/javascript">
		var dest = '${dest}';
		location.href = dest;
		$(document).ready(function(){	
			//location.href = dest;		
		})
	</script>
</body>
</html>