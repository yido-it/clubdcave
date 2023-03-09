<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="../common/script.jsp" />
<head>
<!--폰트아이콘-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
 
<style>
	body{background-color:#1b1d21;}
	
	h1{color: white;font-size:40px;
	    vertical-align: middle;
	    position: absolute;
	    top: 50%;
	    text-align: center;
	    width: 100%;}
	i{color:white;font-size:60px}
</style>
</head>
<body>
	<h1>
		<i class="fa fa-spinner fa-pulse fa-fw"></i>
		<br/><br/>
		로딩중입니다.
	</h1> 

	<script type="text/javascript">
		var dest = '${dest}';
		console.log(dest);
		if(dest != null) {
			location.href = dest;
		} else {	            			
    		location.href = '/main';
		}
	</script>
</body>
</html>