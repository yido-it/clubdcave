<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />
    
<body class="theme-light">
    
<div id="preloader"><div class="spinner-border color-highlight" role="status"></div></div>

<div id="">
    
    <div class="header header-fixed header-logo-app">
        <a href="javascript:history.back(-1)" class="header-title header-subtitle">정보수정</a>
        <jsp:include page="../common/top.jsp" />
    </div>    
	<jsp:include page="../common/menu.jsp" />
    
    <div class="page-content header-clear">
        <div class="divider divider-margins"></div>
        <div class="menu-title">
            <h1 class="my-0 py-0">정보수정</h1>
            <a href="" class="font-12 mr-2 btn btn-sm color-red-dark border-red-dark rounded-s" style="width:auto;height:auto" id="btnDelete">
                회원탈퇴</a>
        </div>
             
        <div class="content">
        <form id="frmMember" name="frmMember">            
            <div style="">    
                <div class="input-style input-light input-style-1 has-icon input-required app">
                    <i class="input-icon fa fa-address-card"></i>
                    <span class="color-highlight">아이디</span>
                    <input class="form-control" type="" value="${sessionScope.msMember.msId}" disabled>
                    <input class="form-control" type="hidden" id="msId" name="msId" value="${sessionScope.msMember.msId}">
                </div> 
                <div class="input-style input-light input-style-1 has-icon input-required">
                    <i class="input-icon fa fa-user"></i>
                    <span class="color-highlight">이름</span>                   
                    <input class="form-control" type="name" value="${sessionScope.msMember.msName}" disabled>
                    <input class="form-control" type="hidden" id="msName" name="msName" value="${sessionScope.msMember.msName}">
                </div> 
                <div class="input-style input-light input-style-1 has-icon input-required app">
                    <i class="input-icon fa fa-lock"></i>
                    <span class="color-highlight">현재비밀번호</span>
                    <input class="form-control" type="password" id="msPassword" name="msPassword" data-name="비밀번호" placeholder="현재비밀번호" >
                </div> 
                <div class="input-style input-light input-style-1 has-icon input-required app">
                    <i class="input-icon fa fa-lock"></i>
                    <span class="color-highlight">변경할 비밀번호</span>
                    <input class="form-control" type="password" id="newMsPassword" name="newMsPassword" data-name="변경할 비밀번호" placeholder="변경할 비밀번호">
                    <p class="text-right font-12 color-red-light mb-1" id="pwdMsg1"></p>
                </div> 
                <div class="input-style input-light input-style-1 has-icon input-required app">
                    <i class="input-icon fa fa-lock"></i>
                    <span class="color-highlight">비밀번호 확인</span>
                    <input class="form-control" type="password" id="msPasswordChk" name="msPasswordChk" data-name="비밀번호 확인" placeholder="비밀번호 확인">
                    <p class="text-right font-12 color-red-light mb-1" id="pwdMsg2"></p>
                </div> 
                <div class="input-style input-light input-style-1 has-icon input-required">
                    <i class="input-icon fa-solid fa-mobile-screen-button"></i>     
                        <span class="color-highlight">휴대폰번호</span>
                    <em style="top:4px"><button type="button" class="btn btn-xs bg-green-dark border-green-dark btn-primary" id="sendCode">인증요청</button></em>
                    <input class="form-control" type="" id="msPhone" name="msPhone" data-name="휴대폰번호" placeholder="휴대폰번호">
                </div> 
                <div class="input-style input-light input-style-1 has-icon input-required">
                    <i class="input-icon fa fa-key"></i>
                    <span class="color-highlight">인증번호입력</span>
                    <em style="top:4px"><button type="button" class="btn btn-xs bg-green-dark border-green-dark btn-primary" id="verifyChk">확인</button></em>
                    <input class="form-control" type="" id="verifyCode" name="verifyCode" data-name="인증번호입력" placeholder="인증번호입력">
                </div>
                <input type="hidden" id="hiddenCode" name="hiddenCode"/>
                <input type="checkbox" id="chkSmsYn"/> SMS 수신에 동의합니다
                <input type="hidden" id="smsChk1" name="smsChk1"/>
                <input type="hidden" id="msNum" name="msNum" value="${sessionScope.msMember.msNum}"/>  
                <input type="hidden" id="msLoginCd" name="msLoginCd" value="${sessionScope.msMember.msLoginCd}"/>
            </div>

            <h3 class="font-700 mt-5">개인정보</h3> 
            
            <div class="">                
                <!-- <h3 class="font-700">개인정보</h3> --> 
                <div class="input-style input-style-2 input-required">
                    <span class="color-highlight">성별 선택</span>
                    <em><i class="fa fa-angle-down"></i></em>
                    <input class="form-control" type="hidden" id="msSexVal" value="${sessionScope.msMember.msSex}" >
                    <select id="msSex" name="msSex" >
                        <option value="" disabled>성별 선택</option>
                        <option value="M">남성</option>
                        <option value="F">여성</option> 
                    </select>
                </div>
        
                <div class="input-style input-style-2 input-required">
                    <span style="color:#3b80d6">생년월일</span>
                    <em><i class="fa-solid fa-calendar-days font-17"></i></em>
                    <input type="date" id="msBirth" name="msBirth" value="${sessionScope.msMember.msBirth}">
                </div>
        
                <div class="input-style input-style-2 input-required">
                    <span class="color-highlight">레슨경험</span>
                    <em><i class="fa fa-angle-down"></i></em>
                    <input class="form-control" type="hidden" id="msLessonExpYnVal" value="${basicInfo.msLessonExpYn}" >
                    <select id="msLessonExpYn" name="msLessonExpYn" >
                        <option value="" disabled>레슨경험</option>
                        <option value="Y" >있음</option>
                        <option value="N">없음</option> 
                    </select>
                </div>
            
                <div class="row mb-0 mt-4">
                    <div class="col-12 mb-3">
                        <div class="input-style input-style-2  input-required">
                            <span class="color-highlight input-style-1-active">레슨빈도</span>
                            <input class="form-control" type="name" placeholder="ex) 주2회, 월1회.." id="msLessonTrem" name="msLessonTrem" value="${basicInfo.msLessonTrem}">
                        </div> 
                    </div>
                    <div class="col-12 mb-3">
                        <div class="input-style input-style-2  input-required">
                            <span class="color-highlight input-style-1-active">레슨선호시간</span>
                            <input class="form-control" type="name" placeholder=" ex) 30분, 50분.." id="msLessonMinute" name="msLessonMinute" value="${basicInfo.msLessonMinute}">
                        </div> 
                    </div>
        
                    <div class="col-6 mb-3">
                        <div class="input-style input-style-2  input-required">
                            <span class="color-highlight input-style-1-active">라운드빈도</span>
                            <input class="form-control" type="name" placeholder="ex) 주2회, 월1회.." id="msRoundCnt" name="msRoundCnt" value="${basicInfo.msRoundCnt}">
                        </div> 
                    </div>
                    <div class="col-6 mb-3">
                        <div class="input-style input-style-2  input-required">
                            <span class="color-highlight input-style-1-active">핸디캡</span>
                            <input class="form-control" type="name" placeholder="ex) 0개, 2개.." id="msHandcap" name="msHandcap" value="${basicInfo.msHandcap}">
                        </div> 
                    </div>
                </div>
        
                <div class="row mb-0"> 
                    <div class="col-6 mb-3">
                        <div class="input-style input-style-2  input-required">
                            <span class="color-highlight input-style-1-active">직업</span>
                            <input class="form-control" type="hidden" id="msJobCdVal" value="${sessionScope.msMember.msJobCd}">
                            <select id="msJobCd" name="msJobCd" >                            
                                <option value="" disabled>선택</option>
                                <c:forEach items="${jobList}" var="item" varStatus="status">
                                <option value="${item.cdCode}">${item.cdTitle1}</option>
                                </c:forEach>
                            </select>
                        </div> 
                    </div>
                    <div class="col-6 mb-3">
                        <div class="input-style input-style-2  input-required">
                            <span class="color-highlight input-style-1-active">직장명</span>
                            <input class="form-control" type="name" placeholder="" id="msCompnm" name="msCompnm" value="${sessionScope.msMember.msCompnm}">
                        </div> 
                    </div>
                    <div class="col-12 mb-3">
                        <div class="input-style input-style-2"">
                            <span class="color-highlight input-style-1-active">직장주소</span>
                            <em><i class= "fas fa-search font-17"></i></em>
                            <input class="form-control" type="name" placeholder="테헤란로5 또는 서린동154과 같이 입력 후 검색" id="msCompaddr1" name="msCompaddr1" value="${sessionScope.msMember.msCompaddr1}"/>
                        </div> 
                    </div>
                    <div class="col-12 mb-3">
                        <div class="input-style input-style-2 input-required">
                            <span class="color-highlight input-style-1-active">주소상세</span>
                            <input class="form-control" type="name" placeholder="" id="msCompaddr2" name="msCompaddr2" value="${sessionScope.msMember.msCompaddr2}">
                        </div> 
                    </div>
                    <div class="col-12 mb-0">
                        <div class="input-style input-style-2 input-required" style="display:flex;">
                            <span class="color-highlight input-style-1-active">차량번호</span>                            
                            <input class="form-control col-4" type="name" placeholder="최대3대 등록가능" id="msCarNo1" name="msCarNo1" value="${carList[0].msCarNo}">
                            <input class="form-control col-4" type="name" placeholder="" id="msCarNo2" name="msCarNo2" value="${carList[1].msCarNo}">
                            <input class="form-control col-4" type="name" placeholder="" id="msCarNo3" name="msCarNo3" value="${carList[2].msCarNo}">
                        </div> 
                    </div>
              
                </div>
                <div class="input-style input-style-2 input-required">
                    <span class="color-highlight">선호 업장</span>
                    <em><i class="fa fa-angle-down"></i></em>
                    <select>
                        <option value="" disabled>선호하는 업장</option>
                        <option value="chungdam">클럽디청담</option>
                    </select>
                </div>            
            </div>
        
        </form>
        <button type="button" class="mt-4 mb-4 btn btn-md bg-blue-dark btn-full shadow-xl text-uppercase font-800 rounded-s" id="btnSave">
            수정완료
        </button>
        </div>
        
    </div>
    <!-- Page content ends here--> 
</div>
<div class="menu-hider"><div></div></div>
<jsp:include page="../common/alertModal.jsp" /> 	
	<script>
		var checkVerify = false; // 인증 완료 여부
		var pwdReg = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
		var frqReg = /^[월주]{1,1}[0-9]{1,3}([회]{1,1})?$/g;
		
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
		
		$().ready(function() {
			if($('#smsChk1').val() == 'Y') {
				$('#chkSmsYn').prop("checked", true);
			} else {
				$('#chkSmsYn').prop("checked", false);			
			}
			
			setDateValue('msBirth');
			setSelectValue('msSex');		
			setSelectValue('msLessonExpYn');		
			setSelectValue('msJobCd');
		})
		
			
		$('#newMsPassword').on('keyup', function() {
			if(!$('#newMsPassword').val().match(pwdReg)) {
				$('#pwdMsg1').html('특수문자를 포함한 영어/숫자로 8자 이상 입력해주세요.');
			} else {
				$('#pwdMsg1').html('');
			}
		})
				
		$('#msPasswordChk').on('keyup', function() {
			if($('#newMsPassword').val() != $('#msPasswordChk').val()) {
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
			        	 alertModal.send(data.message);
		        	}else{
		        		$('#hiddenCode').val("");
		        		 alertModal.fail(data.message);
		        	}
		        }
		        , error: function(data) {
		        	 alertModal.fail("[error] 오류가 발생했습니다.");
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
		
		$('#chkSmsYn').on('change', function () {
			if($('#chkSmsYn').is(':checked')) {
				$('#smsChk1').val('Y');
			} else {
				$('#smsChk1').val('N');
			}
		})
		
		$('#btnSave').on('click', function() {
			
			var result = true;
			
			if(msLoginCd == 'APP') {
				if($('#newMsPassword').val() != "") {
					if(!chkInputVal('msPassword')) {
						result = false;
						return;
					}
					if(!$('#newMsPassword').val().match(pwdReg)) {
						alertModal.fail('올바른 비밀번호가 아닙니다.');
						result = false;
						return;
					}
					if($('#newMsPassword').val() != $('#msPasswordChk').val()) {
						alertModal.fail('비밀번호 확인이 일치하지 않습니다.');
						result = false;
						return;
					}
				}
			}			
			
			if (!result) return;
			/* 
			if($('#msPhone').val() != msPhone) {
				if(!checkVerify) {
					alertModal.fail('본인인증이 완료되지 않았습니다.');
					result = false;
					return;
				}
			}		 */				
			if (!result) return;
			
			if ($('#msLessonTrem').val() != "") {
				if(!$('#msLessonTrem').val().match(frqReg)) {
					alertModal.fail('레슨빈도를 다시 입력해주세요. (주2회, 월1회..)');
					result = false;
					return;
				}				
			}		
			if ($('#msLessonMinute').val() != "") {
				var minReg = /^[0-9]{1,3}([분]{1,1})?$/g;
				if(!$('#msLessonMinute').val().match(minReg)) {
					alertModal.fail('선호레슨시간을 다시 입력해주세요. (30분, 50분..)');
					result = false;
					return;
				}	
			}
			if ($('#msRoundCnt').val() != "") {
				if(!$('#msRoundCnt').val().match(frqReg)) {
					alertModal.fail('라운드빈도를 다시 입력해주세요. (주2회, 월1회..)');
					result = false;
					return;
				}
			}
			if ($('#msHandcap').val() != "") {
				var timeReg = /^[0-9]{1,2}([개]{1,1})?$/g;
				if(!$('#msHandcap').val().match(timeReg)) {
					alertModal.fail('핸디캡를 다시 입력해주세요. (0개, 2개..)');
					result = false;
					return;
				}	
			}
							
			console.log($('#frmMember').serialize());
			saveMemberModify();
		})
		
		function saveMemberModify() {			
		    $.ajax({
		        url: "/member/saveMemberModify",
		        type: "post",
		        dataType: 'json',
		        data: $('#frmMember').serialize(),
		        contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
		        success: function(data) {		    	
		            if(data.result){
		            	alertModal.success('수정이 완료되었습니다.');
						location.reload();
		            } else {
		            	alertModal.fail(data.message);                    
		            }                
		        },
		        error: function(data) {
		        	alertModal.fail('[error] 오류가 발생했습니다.');
		        }
		    });
		
		}
	</script>	
</body>
</html>