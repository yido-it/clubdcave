<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<html>

<jsp:include page="../common/script.jsp" />
<head>
</head>
<body>
	<div class="container-scroller">
		<div class="container-fluid page-body-wrapper full-page-wrapper">
			<div class="content-wrapper d-flex align-items-center auth px-0">
				<div class="row w-100 mx-0">
					<div class="col-lg-4 mx-auto">
						<div class="auth-form-light text-left py-5 px-4 px-sm-5">					
					
							<form class="pt-3" id="form-login" action="" method="post" novalidate="novalidate">
								<!-- 아이디 -->
								<div class="form-group">
									<input type="text" name="msId" id="msId" class="form-control form-control-lg"  placeholder="아이디" value="">
								</div>
								<!-- 비밀번호 -->
								<div class="form-group">
									<input type="password" name="msPassword" id="msPassword" class="form-control form-control-lg" placeholder="비밀번호" value="">
								</div>
								<div class="my-2 d-flex justify-content-between align-items-center">
									<div class="form-check">
										<label class="form-check-label text-muted">
										<input type="checkbox" class="form-check-input" name="chkSaveLogin" id="chkSaveLogin">
										자동로그인
										</label>
									</div>
								</div>
								<!-- 로그인 -->
								<div class="mt-3">
									<button type="button" class="btn-login" id="btnLogin">로그인</button>
								</div>								
								<!-- 회원가입, 아이디/비번찾기 -->
								<div class="mt-3">
									<a href="/member/join01?msLoginCd=APP" class="btn-join" id="btnJoin">회원가입</a>
									<a href="/member/find" class="btn-find" id="btnFind">아이디/비밀번호 찾기</a>
								</div>								
								<!-- 카카오 로그인 -->
								<div id="kakao_id_login" style="display:inline-block;"><img id='kakao_btn_image'/></div>
								<!-- 네이버 로그인 -->
						 		<div id="naver_id_login" style="display:inline-block;"></div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
	var kakao_key = "${Globals.KakaoKey}";
	var kakao_client_id = "${Globals.kakaoClientId}";
	var kakao_redirect_url = "${Globals.kakaoRedirectUrl}";
	var kakao_button_image = "<c:url value='/img/login_kakao.png'/>";

	var naver_domain = "${Globals.NaverDomain}";
	var naver_key = "${Globals.NaverKey}"; 
	var naver_callback = "${Globals.NaverCallbackUrl}";
	var naver_button_image = "<c:url value='/img/login_naver.png'/>";	
    var naver_id_login = new naver_id_login(naver_key, naver_callback);
    
	$(document).ready(function(){

		if ("${sessionScope.msMember.msNum}" != null && "${sessionScope.msMember.msNum}" != "") {
			location.href = "<c:url value='/main'/>";
		}
		
		kakaoInit();
		naverInit();
	});
	

	// 카카오 로그인 버튼 클릭
	$('#kakao_id_login').on('click', function () {
		console.log(kakao_client_id);
		console.log(kakao_redirect_url);
		// REST api 방법
		location.href = "https://kauth.kakao.com/oauth/authorize?response_type=code"
					  + "&client_id=" + kakao_client_id
					  + "&redirect_uri=" + kakao_redirect_url;
		
		// Javascript api 방법
		/*window.Kakao.Auth.login({
		    success: function(authObj) {
		    	window.Kakao.Auth.setAccessToken(authObj.access_token);
		    	window.Kakao.API.request({
		            url: '/v2/user/me',
		            success: function(res) {
		            	var obj =  {
	            			  msId : res.id
	        				, msName : res.properties.nickname
	        				, msEmail : res.kakao_account.email	
		            	}
		            	succLoginWithKakao(obj);
			        },
			        fail: function(error) {
			          alert(JSON.stringify(error));
			        }
		      });
		    },
		    fail: function(err) {
		      alert('카카오 로그인에 실패했습니다. 관리자에게 문의하세요.' + JSON.stringify(err));
		    }
		  });*/
	})	

	$('#btnLogin').on('click', function () {		
		doLogin();
	})
		
	function kakaoInit() {
		$("#kakao_id_login").css("cursor", "pointer");
		$("#kakao_btn_image").attr("src", kakao_button_image);
	  	<!-- 카카오 로그인 javascript키 초기화 -->
		Kakao.init(kakao_key);
	}
		
	function naverInit() {
	    var state = naver_id_login.getUniqState();
	    
	    naver_id_login.setButton("green", 2, 43);
	    naver_id_login.setDomain(naver_domain);
	    naver_id_login.setState(state);
	    naver_id_login.init_naver_id_login();
	}
	
	function doLogin() {
		var sUrl = "<c:url value='/member/doLogin'/>";
		
		var msId = $("#msId").val();
		var msPassword = $("#msPassword").val();
		var saveLogin = $('input:checkbox[id="chkSaveLogin"]').is(":checked") ? "1" : "0";

		if(msId == "") {
			alert("아이디를 입력하세요.");
			return;
		}
		if(msPassword == "") {
			alert("비밀번호를 입력하세요.");
			return;
		}
		var sParams = {
				  msId : msId
				, msPassword : msPassword
				, chkSaveLogin : saveLogin
		}

        mAjax2(sUrl, sParams, function(data) {
        	if (data.resultCode == "100") {
        		alert('회원정보가 없거나 일치하지 않습니다.');
        		
        	} else if (data.resultCode == "200") {

        		// 휴면계정
        		if(data.userInfo.msDormant == 'Y'){
            		if(confirm('휴면계정입니다. 휴면 상태를 해제하시겠습니까?')){
						location.href = "<c:url value='/clubd/member/sleeper.do?coDiv=" +" '/>";
            		}
            	}
        		
        		/* var bUri = document.referrer;
				var idx = bUri.indexOf("reservation.do");				
				var _bkDay = getCookie("_bkDayNew");
				
				// _bkDay 값이 있으면 예약페이지로 이동 (메인에서 날짜 클릭한거)- 배은화 (2022-12-07)
				if (_bkDay != "") {
					if("${setCoDiv}" == globals.coDiv.boeun) {
						location.href = "<c:url value='/boeun/reservation/reservation.do'/>";
					} else if("${setCoDiv}" == globals.coDiv.songnisan) {
						location.href = "<c:url value='/songnisan/reservation/reservation.do'/>";
					} else if("${setCoDiv}" == globals.coDiv.geumgang) {
						location.href = "<c:url value='/geumgang/reservation/reservation.do'/>";
					} else if("${setCoDiv}" == globals.coDiv.geochang) {
						location.href = "<c:url value='/geochang/reservation/reservation.do'/>";
					}			
				} else {
					if(idx == -1) {
						if("${setCoDiv}" == globals.coDiv.boeun) {
							location.href = "<c:url value='/boeun/index.do'/>";
						} else if("${setCoDiv}" == globals.coDiv.songnisan) {
							location.href = "<c:url value='/songnisan/index.do'/>";
						} else if("${setCoDiv}" == globals.coDiv.geumgang) {
							location.href = "<c:url value='/geumgang/index.do'/>";
						} else if("${setCoDiv}" == globals.coDiv.geochang) {
							location.href = "<c:url value='/geochang/index.do'/>";
						}
					} else {
						if("${setCoDiv}" == globals.coDiv.boeun) {
							location.href = "<c:url value='/boeun/reservation/reservation.do'/>";
						} else if("${setCoDiv}" == globals.coDiv.songnisan) {
							location.href = "<c:url value='/songnisan/reservation/reservation.do'/>";
						} else if("${setCoDiv}" == globals.coDiv.geumgang) {
							location.href = "<c:url value='/geumgang/reservation/reservation.do'/>";
						} else if("${setCoDiv}" == globals.coDiv.geochang) {
							location.href = "<c:url value='/geochang/reservation/reservation.do'/>";
						}
					}					
				} */
				
        		location.href = "<c:url value='/main'/>";

            } else if(data.resultCode == "999") {
        		if($('input:checkbox[id="chkSaveId"]').is(":checked")){
        			setCookie("id", $("#msId").val(), 365);
        		} else {
        			setCookie("id", "", 365);
        		}
        		loginPopupOpen(data.subData);
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
</body>
</html>

