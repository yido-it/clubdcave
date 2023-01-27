<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>결제요청</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<jsp:include page="../common/alertModal.jsp" />  
<script>
	$(document).ready(function() {
		var code = "${resultCode}";
		var message = "${resultMessage}";
		var calcSerialNo = "${calcSerialNo}";
		
		if(code == "0000") {
			alertModal.success("결제 완료되었습니다.");
		} else {
			if(message == "") message = "결제에 실패했습니다.";
			
			
			alertModal.fail(message);
		}

        self.close();
		
		opener.parent.mPay.result(code);
		opener.parent.mPay.serialNo(calcSerialNo);
	});
</script>
</head>
</html>