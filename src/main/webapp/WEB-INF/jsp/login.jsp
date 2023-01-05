<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<html>

<jsp:include page="common/script.jsp" />
<script type="text/javascript">

	var kakao_key = "${Globals.KakaoKey}";
	var kakao_client_id = "${Globals.kakaoClientId}";
	var kakao_redirect_url = "${Globals.kakaoRedirectUrl}";
	var kakao_button_image = "<c:url value='/img/login_kakao.png'/>";

	var naver_domain = "${Globals.NaverDomain}";
	var naver_key = "${Globals.NaverKey}"; 
	var naver_callback = "${Globals.NaverCallbackUrl}";
	var naver_button_image = "<c:url value='/img/login_naver.png'/>";
	
</script>
<body>
<div class="container-scroller">
	<div class="container-fluid page-body-wrapper full-page-wrapper">
		<div class="content-wrapper d-flex align-items-center auth px-0">
			<div class="row w-100 mx-0">
				<div class="col-lg-4 mx-auto">
					<div class="auth-form-light text-left py-5 px-4 px-sm-5">
					
						<!--  
						<h4>Hello! let's get started</h4>
						<h6 class="font-weight-light">Sign in to continue.</h6> 
						-->
						<form class="pt-3" id="form-login" action="/checkLogin" method="post" novalidate="novalidate">
							<!-- 아이디 -->
							<div class="form-group">
								<input type="text" name="userId" id="userId" class="form-control form-control-lg"  placeholder="아이디" value="">
							</div>
							<!-- 비밀번호 -->
							<div class="form-group">
								<input type="password" name="password" id="password" class="form-control form-control-lg" placeholder="비밀번호" value="">
							</div>
							<!-- 로그인 -->
							<div class="mt-3">
								<a class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn">로그인</a>
							</div>
							<div class="my-2 d-flex justify-content-between align-items-center">
								<div class="form-check">
									<label class="form-check-label text-muted">
									<input type="checkbox" class="form-check-input" name="remember_me" id="remember_me">
									자동로그인
									</label>
								</div>
							</div>
							
							<!-- 카카오 로그인 -->
							<a id="custom-login-btn" href="javascript:loginWithKakao()">
								<img id="kakao_login_button"/>
							</a>
							<!-- 네이버 로그인 -->
					 		<div id="naver_id_login" style="display:inline-block;"></div>
   							
						    <script type="text/javascript">
						        var naver_id_login = new naver_id_login(naver_key, naver_callback);
						        var state = naver_id_login.getUniqState();
						        naver_id_login.setButton("green", 2, 43);
						     	//  naver_id_login.setButton("", 10, "", naver_button_image);//네이버 로그인 이미지 
						        naver_id_login.setDomain(naver_domain);
						        naver_id_login.setState(state);
						        naver_id_login.setPopup();
						        naver_id_login.init_naver_id_login();
						    </script>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>

<script type="text/javascript">
$(document).ready(function(){
	/*
	if('${errorMessage}' != ''){
        alert('${errorMessage}');
	}
	
	$('#form-login').validate();
	*/
	kakaoInit();
});

function kakaoInit() {
	$("#kakao_login_button").attr("src", kakao_button_image);	

  	<!-- 카카오 로그인 javascript키 초기화 -->
	Kakao.init(kakao_key);		
}

// 카카오 로그인 버튼 클릭
function loginWithKakao() {
	var sUrl = String.format("https://kauth.kakao.com/oauth/authorize?client_id={0}&redirect_uri={1}&response_type=code", kakao_client_id, kakao_redirect_url);
	location.href = sUrl;
}

function succLoginWithNaver(name) {
	// snsLogin.js 함수 
	actionLoginForSocial("NAVER", name);
}

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

//로그인 버튼 클릭
$(".auth-form-btn").on("click", function(){
	var userId = $('#userId').val().trim();
	var password = $('#password').val().trim();
	
	if (userId == "") {
		alert('아이디를 입력해주세요.');
		return;
	}
	if (password == "") {
		alert('비밀번호를 입력해주세요.');
		return;
	}
	
	$("#form-login").submit();
})

$('#password').on('keyup', function(e){
	if(e.keyCode == 13) $('.auth-form-btn').trigger('click');
});


</script>
