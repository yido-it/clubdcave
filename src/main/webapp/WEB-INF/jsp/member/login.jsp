<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />

<div id="preloader"><div class="spinner-border color-highlight" role="status"></div></div>
<body class="is-not-ios theme-dark">
    
<div id="page">
    <div class="header header-fixed header-logo-app header-transparent">
        <a href="#" class="color-white header-title header-subtitle">로그인</a>
        <a href="#" data-back-button class="color-white header-icon header-icon-1" id="topGoBack"><i class="fa fa-arrow-left"></i></a>
        <a href="#" data-menu="menu-main" class="color-white header-icon header-icon-2 mt-1"><i class="fas fa-bars"></i></a>
    </div>
   	<jsp:include page="../common/menu.jsp" />

    <div class="page-content mb-0 pb-0">            

        <div data-card-height="cover-card" class="card bg-1 mb-0">
            <div class="card-center">
                <div style="width:100%;text-align:center">
                	<img src="../images/logo_white.png" class="text-center" style="width:180px;">
                </div> 
               <!--  <a href="/main"><h2 class="font-800 text-center color-white font-40 text-uppercase">Login</h2></a> -->
                <p class="mt-1 text-center color-white color-highlight font-12">클럽디청담 회원로그인</p>
                <div style="max-width:300px;" class="mx-auto">
                    <div class="input-style input-light input-style-1 has-icon">
                        <i class="input-icon fa fa-user"></i>
                        <em>(필수)</em>
                        <input class="form-control" type="" id="msId" name="msId" placeholder="아이디">
                    </div> 
                    <div class="input-style input-light input-style-1 has-icon">
                        <i class="input-icon fa fa-lock"></i>
                        <em>(필수)</em>
                        <input class="form-control" type="password" id="msPassword" name="msPassword" placeholder="비밀번호">
                    </div> 
                    <div class="form-check icon-check mt-3 opacity-60">
                        <input class="form-check-input" type="checkbox" value="" id="chkSaveLogin" name="chkSaveLogin">
                        <label class="form-check-label color-white" for="chkSaveLogin" style="padding: 5px 10px 2px 29px;">자동로그인</label>
                        <i style="top:5px" class="icon-check-1 fa fa-square color-gray-dark font-16 ml-n1"></i>
                        <i class="icon-check-2 fa fa-check-square font-16 color-highlight ml-n1"></i>
                    </div>
                
                    <a href="#" class="btn btn-md bg-blue-dark btn-full shadow-xl text-uppercase font-800 rounded-s" id="btnLogin">로그인</a>
                    
                    <div class="row pt-3 mb-3 font-15">
                        <div class="col-6 text-start">
                        <a href="<c:url value='/member/agree?msLoginCd=APP'/>" class="color-highlight">회원가입</a>
                        </div>
                        <div class="col-6 text-right">
                         <a href="<c:url value='/member/memberFind'/>" class="color-highlight">아이디/비밀번호찾기</a>
                        </div>
                    </div>

                    <p class="mb-1 opacity-80" style="border-top: 1px solid #3a3a3a;padding-top: 10px;">SNS간편로그인</p>
                    <div class="row mb-0"> 
                        <div class="col-6" id="naver_id_login"></div>
                        <div class="col-6">
                            <a href="#" class="letter1 font-13 btn btn-icon btn-m btn-full font-800 text-uppercase rounded-sm= bg-yellow-dark" id="kakao_id_login" style="background-color:#ebd300 !important">
                                <i class="fa-solid fa-k text-center"></i>카카오톡</a>
                        </div>
                    </div> 
                </div>
            </div>
            <div class="card-overlay bg-black opacity-80"></div>
        </div>

    </div><!-- Page-content ends-->
</div> <!-- Page ends-->  
<div class="menu-hider"><div></div></div>
<script type="text/javascript">

	if('${sessionScope.msMember.msNum}' != '' && '${sessionScope.msMember.msNum}' != undefined) {
		location.href='/main';
	}

	var kakao_key = "${Globals.KakaoKey}";
	var kakao_client_id = "${Globals.KakaoClientId}";
	var kakao_redirect_url = "${Globals.KakaoRedirectUrl}";
	
	var naver_domain = "${Globals.NaverDomain}";
	var naver_key = "${Globals.NaverKey}"; 
	var naver_callback = "${Globals.NaverCallbackUrl}";
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
	
	$('#msPassword').on('keyup', function(e){
		if(e.keyCode == 13) $('#btnLogin').trigger('click');
	});
	
	function kakaoInit() {
	  	// 카카오 로그인 javascript키 초기화 
		Kakao.init(kakao_key);
	}
		
	function naverInit() {
	    var state = naver_id_login.getUniqState();
	    naver_id_login.setDomain(naver_domain);
	    naver_id_login.setState(state);
	    naver_id_login.init_naver_id_login();
	    
	    // 네이버 로그인 버튼 자동 css 막기
	    $('#naver_id_login_anchor').addClass('letter1 font-13 btn btn-icon btn-m btn-full font-800 text-uppercase rounded-sm= bg-naver-green');
	    $('#naver_id_login_anchor').empty();
	    $('#naver_id_login_anchor').css('background-color', '#00c63b !important');
	    $('#naver_id_login_anchor').append('<i class="fa-solid fa-n text-center"></i> 네이버</a>');
	}
	
	function doLogin() {
		
		var msId = $("#msId").val();
		var msPassword = $("#msPassword").val();
		var saveLogin = $('input:checkbox[id="chkSaveLogin"]').is(":checked") ? "Y" : "N";

		if(msId == "") {
			alertModal.fail('아이디를 입력해주세요.');
			return;
		}
		if(msPassword == "") {
			alertModal.fail('비밀번호를 입력해주세요.');
			return;
		}
		var sParams = {
				  msId : msId
				, msPassword : msPassword
				, loginAuto : saveLogin
				, ua : getUserAgent()
		};
		var preUrl = document.referrer;
		
		$.ajax({
			  url: "<c:url value='/member/doLogin'/>"
			, type: "post"
			, dataType: 'json'
			, data: sParams
			, contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
			, success: function(data) {
				if(data.result) {
					if(data.userInfo.msDormant == 'Y'){
	            		if(confirm('휴면계정입니다. 휴면 상태를 해제하시겠습니까?')){
							changeDormant(data.userInfo);
	            		}
	            	} else {
	            		if(data.dest != null) {
	            			location.href = data.dest;
	            		} else {	            			
		            		location.href = '/api/appInfo';
	            		}
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
	
	function goAppInfo(dest) {	
		$.ajax({
			  url: "<c:url value='/api/appInfo'/>"
			, type: "post"
			, dataType: 'text'
			, contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
			, success: function(data) {
        		location.href = dest;
			}
			, error: function(data) {
				alertModal.fail('[error] 오류가 발생했습니다.');
			}
		});
	}
	
	function getUserAgent(){
		var varUA = navigator.userAgent.toLowerCase();
		var ua;
		if (varUA.indexOf("android") > -1) {
			ua = "Android";
		} else if (varUA.indexOf("iphone") > -1 || varUA.indexOf("ipad") > -1 || varUA.indexOf("ipod") > -1 || varUA.indexOf("apple") > -1) {
			ua = "iPhone";
		} else {
			ua = "etc";
		}
		return ua;
	}	
	
</script>

</body>	
</html>