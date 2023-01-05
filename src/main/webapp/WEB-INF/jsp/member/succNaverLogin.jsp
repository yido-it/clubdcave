<!doctype html>
<html lang="ko">
<jsp:include page="../common/script.jsp" />

<body>

<script type="text/javascript">
	var naver_id_login = new naver_id_login(opener.parent.naver_key, opener.parent.naver_callback);

	// 네이버 사용자 프로필 조회
  	naver_id_login.get_naver_userprofile("naverSignInCallback()");
  
	// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
  	function naverSignInCallback() {
		// 성별 : gender, 아이디 : id, 이메일 : email 
		opener.parent.succLoginWithNaver(naver_id_login.getProfileData('name'));
		self.close();
  	}
</script>
</body>
</html>