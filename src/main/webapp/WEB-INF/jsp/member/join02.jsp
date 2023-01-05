<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../common/head.jsp" />
<body>

회원가입 기본정보 입력 
 
<form id="frmMember" name="frmMember">
	<div class="row mt-2 ">
		<div class="col-md-12 col-lg-2"> 
			<h5 class=""> 성명 </h5>
		</div> 
		<div class="col-md-12 col-lg-10"> 
			<input type="text" id="ms_name" name="ms_name" />
		</div>
	</div>
	
	<div class="row mt-2 ">
		<div class="col-md-12 col-lg-2"> 
			<h5 class=""> 생일구분 </h5>
		</div> 
		<div class="col-md-12 col-lg-10"> 
			<input type="radio" id="ms_birthyl" name="ms_birthyl" value="1" checked>양력
			<input type="radio" id="ms_birthyl" name="ms_birthyl" value="0">음력
		</div>
	</div>
	
	<div class="row mt-2 ">
		<div class="col-md-12 col-lg-2"> 
			<h5 class=""> 결혼유무 </h5>
		</div> 
		<div class="col-md-12 col-lg-10"> 
			<input type="radio" id="ms_wedding" name="ms_wedding" value="Y">기혼
			<input type="radio" id="ms_wedding" name="ms_wedding" value="N" checked>미혼
		</div>
	</div>
	
	<div class="row mt-2 ">
		<div class="col-md-12 col-lg-2"> 
			<h5 class=""> 이메일 수신여부 </h5>
		</div> 
		<div class="col-md-12 col-lg-10"> 
			<input type="radio" id="ms_email_yn" name="ms_email_yn" value="Y">수신
			<input type="radio" id="ms_email_yn" name="ms_email_yn" value="N">미수신
		</div>
	</div>
		
	
	<div class="row mt-2 ">
		<div class="col-md-12 col-lg-2"> 
			<h5 class=""> 문자발송여부 </h5>
		</div> 
		<div class="col-md-12 col-lg-10"> 
			<input type="radio" id="sms_chk" name="sms_chk" value="Y">발송
			<input type="radio" id="sms_chk" name="sms_chk" value="N">미발송
		</div>
	</div>
	
	<div class="row mt-3">
		<div class="col-md-12">
			<button onclick="insertMember()" class="btn btn-dark btn-lg gg" >
				<i class="fa-solid fa-file-lines"></i> 가입
			</button>
		
		</div>
	</div>
		
</form>

<script type="text/javascript">
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