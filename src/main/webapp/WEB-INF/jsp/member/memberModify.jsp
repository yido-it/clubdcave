<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />
<head>
</head>
<body>회원정보 수정
	<button type="button" onclick="javascript:location.href='memberAddForm'">추가정보 확인</button>
	<button type="button" onclick="javascript:location.href='deleteMember'">회원탈퇴</button>
	<form id="frmMember" name="frmMember">
		<div class="mt-2">
			<input type="text" class="app" id="msId" name="msId" placeholder="아이디" data-name="아이디" value="${sessionScope.msMember.msId}" readonly/>
		</div>	
		<div class="mt-2">
			<input type="password" class="app" id="msPassword" name="msPassword" placeholder="현재 비밀번호" data-name="현재 비밀번호"/>
		</div>
		<div class="mt-2">
			<input type="password" class="app" id="newMsPassword" name="newMsPassword" placeholder="변경할 비밀번호" data-name="변경할 비밀번호"/>
		</div>
		<div id="pwdMsg1"></div>
		<div class="mt-2">
			<input type="password" class="app" id="msPasswordChk" name="msPasswordChk" placeholder="비밀번호 확인" data-name="비밀번호 확인"/>
		</div>
		<div id="pwdMsg2"></div>
		<div class="mt-2">
			<input type="text" class="all" id="msName" name="msName" placeholder="이름" data-name="이름" value="${sessionScope.msMember.msName}" />
		</div>
		<div class="mt-2">
			<input type="text" class="all" id="msPhone" name="msPhone" placeholder="핸드폰번호" data-name="핸드폰번호" value="" />
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
			<input type="hidden" id="msNum" name="msNum" value="${sessionScope.msMember.msNum}"/>
		</div>
		<div class="mt-2">
			<input type="checkbox" id="chkSmsYn"/> SMS 수신에 동의합니다
			<input type="hidden" id="smsChk1" name="smsChk1" value="${sessionScope.msMember.smsChk1}"/>
		</div>
		<div class="mt-2">
			<input type="hidden" id="msLoginCd" name="msLoginCd" value="${sessionScope.msMember.msLoginCd}"/>
		</div>
		<div class="mt-3">
			<button type="button" class="btn btn-dark" id="saveMember">저장</button>
		</div>		
	</form>
	
	<script>
		var checkVerify = false; // 인증 완료 여부
		var idReg = /^(?=.*[a-z0-9])[a-z0-9]{3,16}$/;
		var pwdReg = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
		
		var msLoginCd = $('#msLoginCd').val();
		var msName = "${sessionScope.msMember.msName}";
		var msPhone = "${sessionScope.msMember.msFirstPhone1}" + "-" + "${sessionScope.msMember.msMidPhone1}" + "-" + "${sessionScope.msMember.msLastPhone1}";
		var smsChk1 = "${sessionScope.msMember.smsChk1}";
		$('#msPhone').val(msPhone);
		
		
		if(msLoginCd == 'APP') {	
			$('.app').show();
		} else {				
			$('.app').hide();
		}
		
		if($('#smsChk1').val() == 'Y') {
			$('#chkSmsYn').prop("checked", true);
		} else {
			$('#chkSmsYn').prop("checked", false);			
		}
			
		$('#newMsPassword').on('keyup', function() {
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
			if($('verifyCode').val() != $('hiddenCode').val()) {
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
		
		$('#chkSmsYn').on('change', function () {
			if($('#chkSmsYn').is(':checked')) {
				$('#smsChk1').val('Y');
			} else {
				$('#smsChk1').val('N');
			}
		})
		
		$('#saveMember').on('click', function() {
			
			var result = true;
			var params = {
					  msId : $('#msId').val()
					, msNum : $('#msNum').val()
			};
			
			if($('#newMsPassword').val().replace(' ', '') != "") {
				alert($('#newMsPassword').val());
				if(!chkInputVal('msPassword')) {
					result = false;
					return;
				}
				if($('#newMsPassword').val() != $('#msPasswordChk').val()) {
					alert('비밀번호 확인이 일치하지 않습니다.');
					result = false;
					return;
				}
				params.msPassword = $('#msPassword').val();
				params.newMsPassword = $('#newMsPassword').val();
			}
			if (!result) return;
			
			if($('#msName').val() != msName) {
				params.msName = $('#msName').val(); 
			}
			if($('#msPhone').val() != msPhone) {
				alert($('#msPhone').val());
				alert(msPhone);
				if(!checkVerify) {
					alert('본인인증이 완료되지 않았습니다.');
					result = false;
					return;
				} 					
				params.msPhone = $('#msPhone').val(); 
			}						
			if (!result) return;
			
			if($('#smsChk1').val() != smsChk1) {
				params.smsChk1 = $('#smsChk1').val(); 
			}
			
			if(Object.keys(params).length == 2) {
				alert('수정된 내용이 없습니다.');
				return;
			}
			
			console.log(params);
			saveMemberModify(params);
		})
		
		function saveMemberModify(params) {
		    $.ajax({
		        url: "/member/saveMemberModify",
		        type: "post",
		        dataType: 'json',
		        data: params,
		        contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
		        success: function(data) {		    	
		            if(data.result){
		            	alert('수정이 완료되었습니다.');
						location.reload();
		            } else {
		                alert(data.message);                    
		            }                
		        },
		        error: function(data) {
		        	alert('[error] 오류가 발생했습니다.');
		        }
		    });
		
		}
	</script>	
</body>
</html>