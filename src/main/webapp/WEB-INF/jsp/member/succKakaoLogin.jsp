<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<jsp:include page="../common/script.jsp" />
<head>
</head>
<body>
	<script type="text/javascript">
		
		$(document).ready(function(){			
			if('${message}' != ''){
		        alert('${message}');
		        history.back();
			}
		});
	
		var msId = "${joinInfo.msId}";
		var msName ="${joinInfo.msName}";
		var msEmail = "${joinInfo.msEmail}";
		
		doLoginForSocial("KAKAO", msId, msName, msEmail);
		
		// SNS 로그인 처리 
		function doLoginForSocial(type, msId, msName, msEmail) {
			var sUrl = "<c:url value='/member/doLoginForSocial'/>";
			
			if (msId == "" || msId == null) {
				alert("알 수 없는 오류입니다. 다시 시도해주세요.");
				return;
			}
			if (msName == "" || msName == null) {
				alert("알 수 없는 오류입니다. 다시 시도해주세요.");
				return;
			}
			
			var sParams = "&msId=" + msId;
				sParams += "&msName=" + msName;
				sParams += "&msEmail=" + msEmail;
				sParams += "&msLoginCd=" + type;
	
			// tool.js 프로그래스 모달 
			// progressStart();
			
			mAjax(sUrl, sParams, function(data) {
				if(data.resultCode == "1000") {
					// 가입된 정보 없다면, 간편회원가입으로 이동 
					location.href = "<c:url value='/member/agree?msLoginCd=" + type + "'/>";
				} else if(data.resultCode == "0000") {
					// 로그인 성공시 메인으로 이동
					location.href = "<c:url value='/main'/>";
				} else {
					alert(data.message);
				}
			});	
		}
	</script>
</body>
</html>