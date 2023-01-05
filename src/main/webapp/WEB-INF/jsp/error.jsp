<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
</html>
<script>
	var msg = "오류가 발생하였습니다.";
	var error = "${error}";
	
	if(error.indexOf("Found") > 0){
		msg = "잘못된 주소에 접근하셨습니다.";
	}
	
	if(error == "Forbidden"){
		msg = "접근 권한이 없습니다.";
	}

    alert(msg);
    back(true);
    function back(data){
    	if(data){
    		window.history.back();
    	}
    }
    
</script>
