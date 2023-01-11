<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../common/head.jsp" />
<body>

회원가입 선택사항 입력 
 
<form id="frmMember" name="frmMember">
	<div class="mt-2">
		<input type="text" id="msNum" name="msNum"/>
	</div>
	<div class="row mt-2 col-md-10">
		<div class="col-md-2"> 
			<h6 class=""> 이메일 </h6>
		</div> 
		<div class="col-md-6"> 
			<input type="text" id="msEmail" name="msEmail">
			<div class="mt-2">
				<input type="checkbox" id="chkSmsYn"/> 이메일 수신에 동의합니다
				<input type="hidden" id="msEmailYn" name="msEmailYn"/>
			</div>
		</div>
	</div>
	
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
	
	<div class="row mt-2 col-md-10">
		<div class="col-md-2"> 
			<h6 class=""> 핸디캡 </h6 >
		</div> 
		<div class="col-md-6"> 
			<input type="text" id="msHandcap" name="msHandcap">회?
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
			월<input type="text" id="msLessonTrem" name="msLessonTrem">회
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
			<h6 class=""> 결혼유무 </h6 >
		</div> 
		<div class="col-md-6"> 
			<input type="radio" id="msWedding" name="msWedding" value="Y" checked>기혼
			<input type="radio" id="msWedding" name="msWedding" value="N">미혼
		</div>
	</div>
	
	<div class="row mt-3">
		<div class="col-md-6">
			<button onclick="insertMember()" class="btn btn-dark" id="btnSave">저장</button>
			<button type="button" class="btn btn-dark" id="btnLater">나중에 하기</button>		
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
<jsp:include page="../common/script.jsp" />
</body>
</html>