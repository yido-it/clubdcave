<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<html>
<head>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<c:url value='/js/globals.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/tools.js'/>"></script>
</head>
<body>
<script type="text/javascript">
	
	$(document).ready(function() { 
		actionLoginForSocial("${type}", "${name}");
	});
	
	// SNS 로그인 처리 
	function actionLoginForSocial(type, name) {

		var sUrl = "<c:url value='/member/actionLoginForSocial'/>";
		var sParams = "";

		if (type != "") {
			sParams += String.format("&msLoginCd={0}", type);
		} else {
			alert("알 수 없는 오류입니다. 다시 시도해주세요.");
		}

		if (name != "") {
			sParams += String.format("&msName={0}", name);
		} else {
			alert("알 수 없는 오류입니다. 다시 시도해주세요.");
		}

		// tool.js 프로그래스 모달 
		// progressStart();

		mAjax(sUrl, sParams, function(data) {
			// progressStop();

			if(data.result == "0000") {
				// 로그인 성공시 메인으로 이동 
				location.href = "<c:url value='/main'/>";
				
			} else if(data.result == "1000") {
				// 가입된 정보 없다면, 간편회원가입으로 이동 
				location.href = "<c:url value='/member/join05?" + sParams + "'/>";
				
			} else {
				alert(data.message);
			}
		});	
	}
</script>
</body>
</html>