<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />
<html>
<head>
</head>
<body>

회원가입 선택사항 입력 
 
<form id="frmMember" name="frmMember">
	<div class="mt-2">
		<input type="hidden" id="msNum" name="msNum" value="${sessionScope.msMember.msNum}"/>
	</div>
	<%-- <div class="row mt-2 col-md-10">
		<div class="col-md-2"> 
			<h6 class=""> 이메일 </h6>
		</div> 
		<div class="col-md-6"> 
			<input type="text" id="msEmail" name="msEmail" value="${sessionScope.msMember.msEmail}">
			<div class="mt-2">
				<input type="checkbox" id="chkSmsYn"/> 이메일 수신에 동의합니다
				<input type="hidden" id="msEmailYn" name="msEmailYn"/>
			</div>
		</div>
	</div> --%>
	
	<div class="row mt-2 col-md-10">
		<div class="col-md-2"> 
			<h6 class=""> 성별 </h6 >
		</div> 
		<div class="col-md-6"> 
			<input type="radio" id="msSex" name="msSex" value="1" checked>남
			<input type="radio" id="msSex" name="msSex" value="0">여
		</div>
	</div>
	
	<div class="row mt-2 col-md-10">
		<div class="col-md-2"> 
			<h6 class=""> 생년월일 </h6 >
		</div>
		<div class="col-md-2"> 
			캘린더자리
		</div>
		<div class="col-md-2"> 
			<input type="radio" id="msBirthyl" name="msBirthyl" value="1" checked>양력
			<input type="radio" id="msBirthyl" name="msBirthyl" value="0">음력
		</div>
	</div>
	
	<div class="row mt-2 col-md-10">
		<div class="col-md-2"> 
			<h6 class=""> 거주지 </h6 >
		</div> 
		<div class="col-md-6"> 
			<input type="text" id="msHomeaddr1" name="msHomeaddr1">
			<button type="button">찾기</button>
			<input type="text" id="msHomeaddr2" name="msHomeaddr2">
		</div>
	</div>
	
	<div class="row mt-2 col-md-10">
		<div class="col-md-2"> 
			<h6 class=""> 핸디캡 </h6 >
		</div> 
		<div class="col-md-6"> 
			<input type="text" id="msHandcap" name="msHandcap">
		</div>
	</div>
	
	<div class="row mt-2 col-md-10">
		<div class="col-md-2"> 
			<h6 class=""> 레슨경험 </h6 >
		</div> 
		<div class="col-md-6"> 
			<input type="radio" id="msLessonExpYn" name="msLessonExpYn" value="1" checked>유
			<input type="radio" id="msLessonExpYn" name="msLessonExpYn" value="0">무
		</div>
	</div>
	
	<div class="row mt-2 col-md-10">
		<div class="col-md-2"> 
			<h6 class=""> 레슨빈도 </h6 >
		</div> 
		<div class="col-md-6"> 
			<select id="msLessonTrem1" name="msLessonTrem1">
				<option value="월">월</option>
				<option value="주">주</option>
			</select>
			<input type="text" id="msLessonTrem2" name="msLessonTrem2">회
		</div>
	</div>
	
	<div class="row mt-2 col-md-10">
		<div class="col-md-2"> 
			<h6 class=""> 1회 레슨시 선호하는 시간 </h6 >
		</div> 
		<div class="col-md-6"> 
			<input type="text" id="msLessonMinute" name="msLessonMinute">분
		</div>
	</div>
	
	<div class="row mt-2 col-md-10">
		<div class="col-md-2"> 
			<h6 class=""> 라운드 빈도 </h6 >
		</div> 
		<div class="col-md-6">
			<select id="msRoundTrem1" name="msRoundTrem1">
				<option value="월">월</option>
				<option value="주">주</option>
			</select>
			<input type="text" id="msRoundTrem2" name="msRoundTrem2">회
		</div>
	</div>
	
	<div class="row mt-2 col-md-10">
		<div class="col-md-2"> 
			<h6 class=""> 선호하는 업장 </h6 >
		</div> 
		<div class="col-md-6"> 
			<input type="checkbox" value="cheongdam" checked> 클럽디 청담
		</div>
	</div>
	
	<div class="row mt-2 col-md-10">
		<div class="col-md-2"> 
			<h6 class=""> 직업 </h6 >
		</div> 
		<div class="col-md-6"> 
			<input type="text" id="msLessonTrem" name="msLessonTrem">
		</div>
	</div>
	
	<div class="row mt-2 col-md-10">
		<div class="col-md-2"> 
			<h6 class=""> 직장명 </h6 >
		</div> 
		<div class="col-md-6"> 
			<input type="text" id="msCompnm" name="msCompnm">
		</div>
	</div>
	
	<div class="row mt-2 col-md-10">
		<div class="col-md-2"> 
			<h6 class=""> 직장주소 </h6 >
		</div> 
		<div class="col-md-6"> 
			<input type="text" id="msCompaddr1" name="msCompaddr1">
			<button type="button">찾기</button>
			<input type="text" id="msCompaddr1" name="msCompaddr2">
		</div>
	</div>	
	
	<!-- <div class="row mt-2 col-md-10">
		<div class="col-md-2"> 
			<h6 class=""> 결혼유무 </h6 >
		</div> 
		<div class="col-md-6"> 
			<input type="radio" id="msWedding" name="msWedding" value="Y" checked>기혼
			<input type="radio" id="msWedding" name="msWedding" value="N">미혼
		</div>
	</div> -->
	
	<div class="row mt-2 col-md-10">
		<div class="col-md-2"> 
			<h6 class=""> 차량번호 (최대 3개까지 등록 가능) </h6>
		</div> 
		<div class="col-md-6"> 
			<input type="text" id="msCarNo1" name="msCarNo1" placeholder="차량번호">
			<input type="text" id="msCarKind1" name="msCarKind1" placeholder="차량종류">
		</div>
	</div>
	
	<div class="row mt-2 col-md-10">
		<div class="col-md-2"> 
		</div>
		<div class="col-md-6"> 
			<input type="text" id="msCarNo1" name="msCarNo1" placeholder="차량번호">
			<input type="text" id="msCarKind1" name="msCarKind1" placeholder="차량종류">
		</div>
	</div>
	
	<div class="row mt-2 col-md-10">
		<div class="col-md-2"> 
		</div>
		<div class="col-md-6"> 
			<input type="text" id="msCarNo1" name="msCarNo1" placeholder="차량번호">
			<input type="text" id="msCarKind1" name="msCarKind1" placeholder="차량종류">
		</div>
	</div>
	
	<div class="row mt-3">
		<div class="col-md-6">
			<button onclick="insertMember()" class="btn btn-dark" id="btnSave">저장</button>
			<button type="button" onclick="location.href='/member/joinComplete'" class="btn btn-dark" id="btnLater">나중에 하기</button>		
		</div>
	</div>
	
	<div class="row mt-2 col-md-10">
		<div class="col-md-6"> 
			<input type="checkbox" value="agree"> 회원약관을 확인하였으며 정보제공에 동의합니다.
		</div>
	</div>
		
</form>

<script type="text/javascript">

var msLoginCd = "<c:out value='${sessionScope.msLoginCd}'/>";
var msEmail = "<c:out value='${sessionScope.joinInfo.msEmail}'/>";

if(msLoginCd == 'APP') {			
	$('#msEmail').val('');	
} else {			
	$('#msEmail').val(msEmail);	
}

function insertMember() {
	if ($('#ms_name').val().trim() == "") {
		alert('성명을 입력해 주세요.');
		return;
	}
	
    $.ajax({
        url: "/insertMember",
        type: "post",
        dataType: 'json',
        data: $('#frmMember').serialize(),
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
        success: function(data) {
    	
            if(data.result && data.msNum != ""){
				// 성공시, 다음 페이지로 이동 
				// location.href="/resultBook/"+data.bookCd+"/"+data.locSeq;
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