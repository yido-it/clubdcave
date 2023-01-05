<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>결제요청</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		var code = "${resultCode}";
		var message = "${resultMessage}";
		
		if(code == "0000") {
			alert("결제 완료되었습니다.");
		} else {
			if(message == "") message = "결제에 실패했습니다.";
			
			alert(message);
		}

        self.close();
		
		opener.parent.mPay.result(code);
	});
</script>
</head>
</html>