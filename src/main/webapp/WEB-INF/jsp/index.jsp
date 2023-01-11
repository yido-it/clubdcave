<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="common/head.jsp" />
<jsp:include page="common/script.jsp" />
<body>
	<div>
		<button type="button" id="btnLogout" style="display:none">로그아웃</button>
	</div>
<script type="text/javascript"> 
	console.log("${sessionScope.msMember}");
	if ("${sessionScope.msMember.msNum}" != null && "${sessionScope.msMember.msNum}" != "") {
		$('#btnLogout').show();
	}
	
	$('#btnLogout').on('click', function() {
		var sUrl = "<c:url value='/member/doLogout'/>";
		 $.ajax({
		        url: sUrl,
		        type: "post",
		        dataType: 'json',
		        contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
		        success: function(data) {		    	
		        	if(data.result) {
						alert('로그아웃 되었습니다.');
						location.href = "<c:url value='/login'/>";
					}              
		        },
		        error: function(data) {
		        	alert('로그아웃에 실패했습니다.');
		        }
		    });
	});
</script>
</body>
</html>