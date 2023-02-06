<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />
<body class="theme-dark">
	<div id="preloader"><div class="spinner-border color-highlight" role="status"></div></div>
    <div id="page">        
        <div class="header header-fixed header-logo-app header-transparent">
            <a href="javascript:history.back(-1)" class="color-white header-title header-subtitle">회원가입</a>
            <jsp:include page="../common/top.jsp" />
        </div>
        <jsp:include page="../common/menu.jsp" />
    
        <div class="page-content mb-0">
            
            <div data-card-height="cover-card" class="card bg-1">
                <div class="card-center">
                    <div style="width:100%;text-align:center">
                        <img src="../images/logo_wing.png" class="text-center" style="width:50px;margin-bottom:10px">
                	</div> 
                    <h2 class="font-800 text-center color-white font-40 text-uppercase">REGISTER</h2>
                    <p class="mt-1 text-center color-white font-11">  가입정보 입력</p>
                    <form id="frmMemberForm" name="frmMemberForm">
                        <div style="max-width:300px;" class="mx-auto mb-n5">
                        
                            
                            <div class="input-style input-light input-style-1 has-icon input-required app">
                                <i class="input-icon fa fa-address-card"></i>
                                <span class="color-highlight">아이디</span>
                                <em style="top:4px"><button type="button" class="btn btn-xs bg-green-dark border-green-dark btn-primary" id="idExist">중복확인</button></em>
                                <input class="form-control" type="" id="msId" name="msId" placeholder="아이디" data-name="아이디">
                            </div> 
                            <div class="input-style input-light input-style-1 has-icon input-required app">
                                <i class="input-icon fa fa-lock"></i>
                                <span class="color-highlight">비밀번호</span>
                                <em>(필수)</em>
                                <input class="form-control" type="password" id="msPassword" name="msPassword" placeholder="비밀번호" data-name="비밀번호">
                            </div> 
                            <p class="text-right font-12 color-red-light mb-1" id="pwdMsg1"></p>
                            <div class="input-style input-light input-style-1 has-icon input-required app" >
                                <i class="input-icon fa fa-lock"></i>
                                <span class="color-highlight">비밀번호 확인</span>
                                <em>(필수)</em>
                                <input class="form-control" type="password" id="msPasswordChk" name="msPasswordChk" placeholder="비밀번호 확인" data-name="비밀번호 확인">
                            </div>
                            <p class="text-right font-12 color-red-light mb-1" id="pwdMsg2"></p>
                        
                            <div class="input-style input-light input-style-1 has-icon input-required all">
                                <i class="input-icon fa fa-user"></i>
                                <span class="color-highlight">이름</span>
                                <em>(필수)</em>
                                <input class="form-control" type="name" id="msName" name="msName" placeholder="이름" data-name="이름">
                            </div> 
                        
                                <div class="input-style input-light input-style-1 has-icon input-required all">
                                    <i class="input-icon fa-solid fa-mobile-screen-button"></i>     
                                    <span class="color-highlight">휴대폰번호</span>
                                    <em style="top:4px"><button type="button" class="btn btn-xs bg-green-dark border-green-dark btn-primary" id="sendCode">인증요청</button></em>
                                    <input class="form-control" type="" id="msPhone" name="msPhone" placeholder="휴대폰번호" data-name="휴대폰번호">
                                </div> 
                                <div class="input-style input-light input-style-1 has-icon input-required all">
                                    <i class="input-icon fa fa-key"></i>
                                    <span class="color-highlight">인증번호입력</span>
                                    <em style="top:4px"><button type="button" class="btn btn-xs bg-green-dark border-green-dark btn-primary" id="verifyChk">확인</button></em>
                                    <input class="form-control" type="" id="verifyCode" name="verifyCode" placeholder="인증번호 입력" data-name="인증번호" max="9999">
                                </div> 
                                <input type="hidden" id="hiddenCode" name="hiddenCode"/>
                                <input type="checkbox" id="chkSmsYn"/> SMS 수신에 동의합니다
                                <input type="hidden" id="smsChk1" name="smsChk1"/>
                                <input type="hidden" id="msEmail" name="msEmail"/>
                                <input type="hidden" id="msMktAgreeYn" name="msMktAgreeYn" value="${msMktAgreeYn}"/>
                                <input type="hidden" id="msLoginCd" name="msLoginCd" value="${msLoginCd}"/>
                            <button type="button" class="col-12 mt-4 mb-4 btn btn-md bg-blue-dark btn-full shadow-xl text-uppercase font-800 rounded-s" id="btnSignUp">
                                가입하기
                            </button>
                        </div>
                    </form>
                </div>
                <div class="card-overlay bg-black opacity-80"></div>
            </div>        
        </div> <!-- page-content end-->
        
    </div> <!-- page end-->
    
    <!-- 추가 정보 입력 팝업 -->
	<div id="memberAddPop" class="menu menu-box-bottom rounded-0 modal_bay" data-menu-height="102%" data-menu-effect="menu-parallax" style="display: block;">
	</div>
	<!-- // 추가 정보 입력 팝업 -->
    
	<div class="menu-hider"><div></div></div>
	<jsp:include page="../common/alertModal.jsp" /> 
	<script>
		var checkId = false; // 아이디 중복 여부
		var checkVerify = false; // 인증 완료 여부
		var idReg = /^(?=.*[a-z0-9])[a-z0-9]{3,16}$/;
		var pwdReg = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
		
		var msLoginCd = $('#msLoginCd').val();
		var msId = "<c:out value='${sessionScope.joinInfo.msId}'/>";
		var msName = "<c:out value='${sessionScope.joinInfo.msName}'/>";
		var msEmail = "<c:out value='${sessionScope.joinInfo.msEmail}'/>";
		
		if(msLoginCd == 'APP') {			
			$('#msId').val('');
			$('#msName').val('');			
			$('#msEmail').val('');	
			$('.app').show();
			$('#idExist').show();
		} else {			
			$('#msId').val(msId);
			$('#msName').val(msName);			
			$('#msEmail').val(msEmail);			
			$('.app').hide();
			$('#idExist').hide();
		}
		
		$('#chkSmsYn').on('change', function () {
			if($('#chkSmsYn').is(':checked')) {
				$('#smsChk1').val('Y');
			} else {
				$('#smsChk1').val('N');
			}
		})
		
		$('#idExist').on('click', function() {
			if (!chkInputVal ('msId')) return;
			if(!$('#msId').val().match(idReg)) {
				alertModal.fail('3~16자의 영문/숫자 조합으로 입력해주세요.');
				return;
			}
			$.ajax({
		          url: "<c:out value='/member/checkIdExist'/>"
		        , type: "post"
		        , dataType: 'json'
		        , data: {msId : $('#msId').val()}
		        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
		        , success: function(data) {    	
		            if(data.result == true){
		            	alertModal.success('사용 가능한 아이디입니다.');
		            	$('#msId').prop('readonly', true);
		            	checkId = true;
		            } else {
		            	alertModal.fail('이미 가입된 아이디입니다.');
		                checkId = false;
		            }                
		        }
		        , error: function(data) {
		        	alertModal.fail('[error] 오류가 발생했습니다.');
		        }
		    });
		})
		
		
		$('#msPassword').on('keyup', function() {
			if(!$('#msPassword').val().match(pwdReg)) {
				$('#pwdMsg1').html('특수문자를 포함한 영어/숫자로 8자 이상 입력해주세요.');
			} else {
				$('#pwdMsg1').html('');
			}
		})
		
		$('#msPasswordChk').on('keyup', function() {
			if($('#msPassword').val() != $('#msPasswordChk').val()) {
				$('#pwdMsg2').html('비밀번호 확인이 일치하지 않습니다.');				
			} else {
				$('#pwdMsg2').html('');								
			}
		})
				
		$('#sendCode').on('click', function() {
			if (!chkInputVal ('msPhone')) return;
			
			var ran = Math.random();
			var verifyCode = (ran.toString()).substring(2,6);
			var verifyMsg = "[ClubD 청담] 본인확인 인증번호 ["+verifyCode+"]입니다."

			$.ajax({
		          url: "<c:out value='/member/verifybyCode'/>"
		        , type: "post"
		        , dataType: 'json'
		        , data: {
		        	  msPhone : $('#msPhone').val()
		        	, sendMsg : verifyMsg
		        	}
		        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
		        , success: function(data) {
		        	if(data.result){
			        	$('#hiddenCode').val(verifyCode);
		        		alertModal.send(data.message);
		        	}else{
		        		$('#hiddenCode').val("");
		        		alertModal.fail(data.message);
		        	}
		        }
		        , error: function(data) {
		        	alertModal.fail('[error] 오류가 발생했습니다.');
		        }
		    });
		})
		
		$('#verifyChk').on('click', function () {
			if (!chkInputVal ('verifyCode')) return;
			if($('verifyCode').val() != $('hiddenCode').val()) {
				alertModal.fail('입력하신 인증번호가 일치하지 않습니다.');
				return;
			} else {
				alertModal.success('인증이 완료되었습니다');
				checkVerify = true;
				$('verifyCode').prop('readonly', true);
			}
			
		})
		
		$('#msPhone').on('keyup', function() {
			var msPhone = $('#msPhone').val();
			$('#msPhone').val(msPhone.replace(/[^0-9]/g, '').replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`));
		})
		
		$('#btnSignUp').on('click', function() {
			$('#memberAddPop').load('/member/memberAddPop');
			$('#memberAddPop').addClass('menu-active');
			/*if(msLoginCd == 'APP') {
				if(!chkInputVal('msId')) return;
				if(!checkId) {
					alertModal.fail('아이디 중복확인을 해주세요.');
					return;
				}
				if(!$('#msPassword').val().match(pwdReg)) {
					alertModal.fail('올바른 비밀번호가 아닙니다.');
					return;
				}
				if(!chkInputVal('msPassword')) return;
				if($('#msPassword').val() != $('#msPasswordChk').val()) {
					alertModal.fail('비밀번호가 일치하지 않습니다.');
					return;
				}
			} 
			if(!chkInputVal('msName')) return;
			if(!checkVerify) {
				alertModal.fail('본인인증이 완료되지 않았습니다.');
				return;
			}
			
			doSignUp();*/			
		})
		
		function doSignUp() {
		    $.ajax({
		          url: "<c:out value='/member/doSignUp'/>"
		        , type: "post"
		        , dataType: 'json'
		        , data: $('#frmMemberForm').serialize()
		        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
		        , success: function(data) {		    	
		            if(data.result){
		    			$('#memberAddPop').load('/member/memberAddPop');
		    			$('#memberAddPop').addClass('menu-active');
		            	//goAfterModal("/member/memberAddPop");		            	
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