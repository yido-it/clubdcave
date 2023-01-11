<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<html>

<jsp:include page="../common/script.jsp" />
<head>
</head>
<body>
	<div id="wrap">
		<div class="mainTitle">
			<img src="" alt=""> 회원가입 <img src="<c:url value='/images/m_clubd/titleDot.png'/>" alt="">
		</div>
		<div class="contents">
			<div class="grayBg">
				<input type="hidden" id="msLoginCd" name="msLoginCd" value="${msLoginCd}"/>
				<div class="agreeBgBox">
					<div class="joinAgreeList">
						<div class="agreeTitle">CLUB D 통합회원 이용 약관 (필수)</div>
						<div class="btn"><div class="agreeBtn" onclick="popupOpen(1)">약관보기</div></div>
						<div><input type="checkbox" class="agree-check" id="chkAgree1"> 동의</div>				
					</div>
	
					<div class="joinAgreeList">
						<div class="agreeTitle">CLUB D 개인정보 제공/취급위탁에 대한 동의 (필수)</div>
						<div class="btn"><div class="agreeBtn" onclick="popupOpen(2)">약관보기</div></div>
						<div><input type="checkbox" class="agree-check" id="chkAgree2"> 동의</div>				
					</div>
	
					<div class="joinAgreeList">
						<div class="agreeTitle">CLUB D 개인정보 수집 및 이용에 대한 동의 (필수)</div>
						<div class="btn"><div class="agreeBtn" onclick="popupOpen(3)">약관보기</div></div>
						<div><input type="checkbox" class="agree-check" id="chkAgree3"> 동의</div>				
					</div>
	
					<div class="joinAgreeList">
						<div class="agreeTitle">CLUB D 마케팅 활용 동의서 (선택)</div>
						<div class="btn"><div class="agreeBtn" onclick="popupOpen(4)">약관보기</div></div>
						<div><input type="checkbox" class="agree-check" id="chkAgree4"> 동의</div>			
					</div>
				</div>
			</div>
			<div>
				<input type="checkbox" id="chkAgreeAll"> 약관에 모두 동의합니다.
			</div>
			<div>
				<a href="javascript:doNextPage()" class="btnBox" >다음단계</a>	
			</div>
		</div>
	</div>  
	<script type="text/javascript">
	
		function doNextPage() {
			var sUrl = "<c:url value='/member/join02'/>"; 
			var sParams = "";
			
			var agree1 = $("input[id=chkAgree1]:checked").prop('checked');
			var agree2 = $("input[id=chkAgree2]:checked").prop('checked');
			var agree3 = $("input[id=chkAgree3]:checked").prop('checked');
			var agree4 = $("input[id=chkAgree4]:checked").prop('checked');
	
			if(agree1 != true || agree2 != true || agree3 != true) {
				alert("회원 약관을 확인해주세요.");			
				return;
			}
			
			if(agree4 != true){
				sParams += String.format("msMktAgreeYn={0}", 'N');
			} else {
				sParams += String.format("msMktAgreeYn={0}", 'Y');
			}
			sParams += String.format("&msLoginCd={0}", $('#msLoginCd').val());			
			location.href = sUrl+ "?" + encodeURI(sParams);
		}
				
		$('.agree-check').on('change', function() {
			var checkVal = $(this).prop('checked');
			var result = true;
			$('.agree-check').each(function() {
				if($(this).prop('checked') != checkVal) {
					result = false;
				} 
			})
			$('#chkAgreeAll').prop('checked', result);
		})
		
		$('#chkAgreeAll').on('click', function() {
			var checkVal;
			if($('#chkAgreeAll').prop('checked') != true) {
				checkVal = false;
			} else {
				checkVal = true;
			}
			$('.agree-check').each(function() {
				$(this).prop('checked', checkVal);
			})			
			$('#chkAgreeAll').prop('checked', checkVal);
		})
			
	</script>
</body>
</html>