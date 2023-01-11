<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />
<head>
</head>
<body>
	회원가입 기본정보 입력  
	<form id="frmMember" name="frmMember">
		<div class="mt-2">
			<input type="text" class="app" id="msId" name="msId" placeholder="아이디" data-name="아이디"/>
			<button type="button" class="btn btn-dark" id="idExist">중복확인</button>
		</div>	
		<div class="mt-2">
			<input type="password" class="app" id="msPassword" name="msPassword" placeholder="비밀번호" data-name="비밀번호"/>
		</div>
		<div id="pwdMsg1"></div>
		<div class="mt-2">
			<input type="password" class="app" id="msPasswordChk" name="msPasswordChk" placeholder="비밀번호 확인" data-name="비밀번호 확인"/>
		</div>
		<div id="pwdMsg2"></div>
		<div class="mt-2">
			<input type="text" class="all" id="msName" name="msName" placeholder="이름" data-name="이름"/>
		</div>
		<div class="mt-2">
			<input type="text" class="all" id="msPhone" name="msPhone" placeholder="핸드폰번호" data-name="핸드폰번호"/>
			<button type="button" class="btn btn-dark" id="sendCode">인증요청</button>
		</div>
		<div class="mt-2">
			<input type="number" class="all" id="verifyCode" name="verifyCode" placeholder="인증번호 입력" data-name="인증번호" max="9999"/>
			<button type="button" class="btn btn-dark" id="verifyChk">확인</button>
		</div>
		<div class="mt-2">
			<input type="hidden" id="hiddenCode" name="hiddenCode"/>
		</div>
		<div class="mt-2">
			<input type="checkbox" id="chkSmsYn"/> SMS 수신에 동의합니다
			<input type="hidden" id="smsChk1" name="smsChk1"/>
		</div>
		<div class="mt-2">
			<input type="hidden" id="msMktAgreeYn" name="msMktAgreeYn" value='${msMktAgreeYn}'/>
		</div>
		<div class="mt-2">
			<input type="hidden" id="msEmail" name="msEmail"/>
		</div>
		<div class="mt-2">
			<input type="text" id="msLoginCd" name="msLoginCd" value="${msLoginCd}"/>
		</div>
		<div class="mt-3">
			<button type="button" class="btn btn-dark" id="signUp">가입하기</button>
		</div>		
	</form>
	
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
		} else {			
			$('#msId').val(msId);
			$('#msName').val(msName);			
			$('#msEmail').val(msEmail);			
			$('.app').hide();
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
				alert('3~16자의 영문/숫자 조합으로 입력해주세요.');
				return;
			}
			$.ajax({
		        url: "/member/checkIdExist",
		        type: "post",
		        dataType: 'json',
		        data: {msId : $('#msId').val()},
		        contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
		        success: function(data) {    	
		            if(data.result == true){
		            	alert('사용 가능한 아이디입니다.');
		            	$('#msId').prop('readonly', true);
		            	checkId = true;
		            } else {
		                alert('이미 가입된 아이디입니다.');
		                checkId = false;
		            }                
		        },
		        error: function(data) {
		           alert('중복확인 중 오류가 발생하였습니다.');
		        }
		    });
		})
		
		
		$('#msPassword').on('keyup', function() {
			if(!$('#msPassword').val().match(pwdReg)) {
				$('#pwdMsg1').html('8자 이상의 특수문자 한개 이상을 포함한 영어 및 숫자 조합으로 입력해주세요.');
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
		          url: "/member/verifybyCode"
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
		        		alert(data.message);
		        	}else{
		        		$('#hiddenCode').val("");
		        		alert(data.message);
		        	}
		        }
		        , error: function(data) {
		        	alert(data.message);
		        }
		    });
		})
		
		$('#verifyChk').on('click', function () {
			if (!chkInputVal ('verifyCode')) return;
			if($('verifyCode').val != $('hiddenCode').val) {
				alert('입력하신 인증번호가 일치하지 않습니다.');
				return;
			} else {
				alert('인증이 완료되었습니다');
				checkVerify = true;
				$('verifyCode').prop('readonly', true);
			}
			
		})
		
		$('#msPhone').on('keyup', function() {
			var msPhone = $('#msPhone').val();
			$('#msPhone').val(msPhone.replace(/[^0-9]/g, '').replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`));
		})
		
		$('#signUp').on('click', function() {
			var result = true;
			if(msLoginCd == 'APP') {				
				$('.app, .all').each(function() {
					if (!chkInputVal(this.id)) {
						result = false;
						return false;
					}				
				})
			} else {
				$('.all').each(function() {
					if (!chkInputVal(this.id)) {
						result = false;
						return false;
					}				
				})
			}
			if (!result) return;
			
			if(msLoginCd == 'APP' && !checkId) {
				alert('아이디 중복확인을 해주세요.');
				return;
			}
			/* if(!checkVerify) {
				alert('본인인증이 완료되지 않았습니다.');
				return;
			} */
			if(msLoginCd == 'APP' && $('#msPassword').val() != $('#msPasswordChk').val()) {
				alert('비밀번호가 일치하지 않습니다.');
				return;
			}
			doSignUp();
			
		})
		
		function doSignUp() {
		    $.ajax({
		        url: "/member/doSignUp",
		        type: "post",
		        dataType: 'json',
		        data: $('#frmMember').serialize(),
		        contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
		        success: function(data) {		    	
		            if(data.result){
		            	alert('가입이 완료되었습니다.');
						location.href="/member/join03";
		            } else {
		                alert(data.message);                    
		            }                
		        },
		        error: function(data) {
		           alert('[error] 가입중 오류가 발생하였습니다.');
		        }
		    });
		
		}
	</script>	
</body>
</html>