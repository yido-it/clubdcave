<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="../common/alertModal.jsp" />
<jsp:include page="../common/script.jsp" />
<head>
</head>
<body>
	<script type="text/javascript">
		var naver_key = "${Globals.NaverKey}"; 
		var naver_callback = "${Globals.NaverCallbackUrl}";
		var naver_id_login = new naver_id_login(naver_key, naver_callback);
		
		// 접근 토큰 값 출력
		console.log(naver_id_login.oauthParams.access_token);
	
		// 네이버 사용자 프로필 조회
	  	naver_id_login.get_naver_userprofile("naverSignInCallback()");
	  
		// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
	  	function naverSignInCallback() {
			var msId = naver_id_login.getProfileData('id');
			var msName = naver_id_login.getProfileData('name');
			var msEmail = naver_id_login.getProfileData('email');
			
			doLoginForSocial("NAVER", msId, msEmail, msName);
	  	}
		
	 // SNS 로그인 처리 
		function doLoginForSocial(type, msId, msEmail, msName) {
			var sUrl = "<c:url value='/member/doLoginForSocial'/>";
	
			if (msId == "" || msId == null) {
				alertModal.fail("알 수 없는 오류입니다. 다시 시도해주세요.");
				return;
			}
			if (msName == "" || msName == null) {
				alertModal.fail("알 수 없는 오류입니다. 다시 시도해주세요.");
				return;
			}
			
			var sParams = "&msId=" + msId;
				sParams += "&msName=" + msName;
				sParams += "&msEmail=" + msEmail;
				sParams += "&msLoginCd=" + type;
	
			// tool.js 프로그래스 모달 
			// progressStart();
	
			/*mAjax(sUrl, sParams, function(data) {
				if(data.resultCode == "1000") {
					// 가입된 정보 없다면, 간편회원가입으로 이동 
					location.href = "<c:url value='/member/agree?msLoginCd=" + type + "'/>";
				} else if(data.resultCode == "0000") {
					// 로그인 성공시 메인으로 이동 
					location.href = "<c:url value='/main'/>";
				} else {
					alert(data.message);
				}
			});*/
			
				$.ajax({
					  url: "<c:url value='/member/doLoginForSocial'/>"
					, type: "post"
					, dataType: 'json'
					, data: sParams
					, contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
					, success: function(data) {
						if(data.result) {							
		            		// 페이지 이동
		            		if(data.dest != null && data.dest != "") {	  
	            				location.href = data.dest;
	            			} else {	            			
								location.href = "<c:url value='/main'/>";
		            		}        		
						} else {
							alertModal.fail(data.message);
						}
					}
					, error: function(data) {
						alertModal.fail('[error] 오류가 발생했습니다.');
					}
				});
			
			
		}
	</script>
</body>
</html>