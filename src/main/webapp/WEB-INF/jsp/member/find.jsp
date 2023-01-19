<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/script.jsp" />

<div id="wrap">
	<div class="mainTitle">
		회원가입
	</div>
	<div class="contents">
		<div class="grayBg">
			<div class="loginCont bgBottom">
				<p class="loginTitle">아이디를 잊으셨나요?</p>
				<div class="logInputBox">
					<div class="loginInput">
					  <input type="text" placeholder="이름" id="findIdName">
					  <input type="text" placeholder="휴대폰번호" id="findIdPhone">
					</div>
					<div class="loginBtn"><a href="javascript:doFindId()">확인</a></div>
				</div>
			</div>
			<div class="loginCont">
				<p class="loginTitle">비밀번호를 잊으셨나요?</p>
				<div class="logInputBox">
					<div class="loginInput">
							  <input type="text" placeholder="아이디" id="findPwId">
							  <input type="text" placeholder="이름" id="findPwName">
							  <input type="text" placeholder="휴대폰번호" id="findPwPhone">
					</div>
					<div class="loginBtn"><a href="javascript:doFindPw()">확인</a></div>
				</div>
			</div>
			<div class="loginTxt">
				<p class="q_B">회원이 아니세요?</p>
				<p>회원가입을 하시면 더 많은 혜택을 누리실 수 있습니다.</p>
				<a href='/member/join?msLoginCd=APP' class="joinBtn">회원가입</a>
			</div>

		</div>
	</div><!-- contents End -->
</div>   
<script type="text/javascript">

	$(document).ready(function() {  
		init();
	});
	
	function init() {
		$("#findIdName").keypress(function (event) {
	        if(event.keyCode == 13) {
	        	doFindId();
	        }
	    });

		$("#findIdPhone").keypress(function (event) {
	        if(event.keyCode == 13) {
	        	doFindId();
	        }
	    });

		$("#findPwName").keypress(function (event) {
	        if(event.keyCode == 13) {
	        	doFindPw();
	        }
	    });

		$("#findPwId").keypress(function (event) {
	        if(event.keyCode == 13) {
	        	doFindPw();
	        }
	    });
	}

	function doFindId() {
		var sUrl = "<c:url value='/member/doFindId'/>";
		var sParams = "";
		
		var msName = $("#findIdName").val();
		var msPhone = $("#findIdPhone").val();
		
		if(msName == "") {
			alert("이름을 입력하세요.");
			return;
		}
		
		if(msPhone == "" || msPhone.length < 11) {
			alert("핸드폰번호를 입력하세요.");
			return;			
		}
		
		//sParams += String.format("&coDiv={0}", "01");
		sParams += String.format("&msName={0}", msName);
		sParams += String.format("&msPhone={0}", msPhone);
		
		mAjax(sUrl, sParams, function(data) {
			if(data.resultCode == "0000") {
				$("#findIdName").val('');
				$("#findIdPhone").val('');
				alert("회원님의 아이디가 등록 된 휴대폰으로 전송되었습니다.");
			} else {
				alert(data.resultMessage);
			}
		});
	}

	function doFindPw() {
		var sUrl = "<c:url value='/member/doFindPw'/>";
		var sParams = "";
		
		var msId = $("#findPwId").val();
		var msName = $("#findPwName").val();
		var msPhone = $("#findPwPhone").val();
		
		if(msId == "") {
			alert("아이디를 입력하세요.");
			return;			
		}
		if(msName == "") {
			alert("이름을 입력하세요.");
			return;
		}
		if(msPhone == "") {
			alert("휴대폰번호를 입력하세요.");
			return;
		}			
		
		//sParams += String.format("&coDiv={0}", "01");
		sParams += String.format("&id={0}", msId);
		sParams += String.format("&msName={0}", msName);
		sParams += String.format("&msPhone={0}", msPhone);
		
		mAjax(sUrl, sParams, function(data) {
			if(data.resultCode == "0000") {
				$("#findPwId").val('');
				$("#findPwName").val('');
				$("#findPwPhone").val('');
				alert("회원님의 초기화 된 비밀번호가 등록 된 휴대폰으로 전송되었습니다.");
			} else {
				alert(data.resultMessage);
			}
		});
	}
	
	$('#findIdPhone, #findPwPhone').on('keyup', function() {
		var msPhone = $(this).val();
		$(this).val(msPhone.replace(/[^0-9]/g, '').replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`));
	})
	
	
</script>
</body>
</html>