<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />
<!-- <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> -->
    
<body class="theme-dark">
    
<div id="preloader"><div class="spinner-border color-highlight" role="status"></div></div>

<div id="page">
    
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
                
                <div class="input-style input-style-2  input-required">
                    <span class="color-highlight input-style-1-active">거주지(시,구,동)</span>
                    <em><em><i class= "fas fa-search font-17 wrap1"></i></em></em>
                    <input class="form-control" type="hidden" id="msHomezip" name="msHomezip" value="${sessionScope.msMember.msHomezip}" >
                    <input class="form-control" type="name" placeholder="" id="msHomeaddr1" name="msHomeaddr1" value="${sessionScope.msMember.msHomeaddr1}"/>
                </div>
                <div class="col-12 mb-3">
	                    <div id="wrap1" style="display:none;border:1px solid;height:300px;margin:5px 0;position:relative">
	                    <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap1" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
	                    </div>
	                </div>
                 <div class="input-style input-style-2  input-required">
                    <span class="color-highlight input-style-1-active">상세주소</span>
                    <em></em>
                    <input class="form-control" type="name" placeholder="" id="msHomeaddr2" name="msHomeaddr2" value="${sessionScope.msMember.msHomeaddr2}"/>
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
                            <em><i class= "fas fa-search font-17 wrap2"></i></em>
                            <input class="form-control" type="hidden" id="msCompzip" name="msCompzip" value="${sessionScope.msMember.msCompzip}" >
                            <input class="form-control" type="name" placeholder="직장주소(시,구,동)" id="msCompaddr1" name="msCompaddr1" value="${sessionScope.msMember.msCompaddr1}" readonly/>
                        </div> 
                    </div>
                    <div class="col-12 mb-3">
	                    <div id="wrap2" style="display:none;border:1px solid;height:300px;margin:5px 0;position:relative">
	                    	<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap2" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
	                    </div>
	                </div>
                    <div class="col-12 mb-3">
                        <div class="input-style input-style-2 input-required">
                            <span class="color-highlight input-style-1-active">상세주소</span>
                            <input class="form-control" type="name" placeholder="" id="msCompaddr2" name="msCompaddr2" value="${sessionScope.msMember.msCompaddr2}">
                        </div> 
                    </div>
                    <div class="col-12 mb-0">
                        <div class="input-style input-style-2 input-required" style="display:flex;">
                            <span class="color-highlight input-style-1-active">차량번호(최대 3대)</span>                            
                            <input class="form-control col-4" type="name" placeholder="" id="msCarNo1" name="msCarNo1" value="${carList[0].msCarNo}">
                            <input class="form-control col-4" type="name" placeholder="" id="msCarNo2" name="msCarNo2" value="${carList[1].msCarNo}">
                            <input class="form-control col-4" type="name" placeholder="" id="msCarNo3" name="msCarNo3" value="${carList[2].msCarNo}">
                        </div> 
                    </div>
              
                </div>
                <div class="input-style input-style-2 input-required">
                    <span class="color-highlight">선호 업장</span>
                    <input class="form-control" type="hidden" id="coDivVal" value="${msFirstPick}">
                    <em><i class="fa fa-angle-down"></i></em>
                    <select id="coDiv" name="coDiv" >
                        <option value="" disabled>선호하는 업장</option>
                        <c:forEach items="${placeList}" var="item" varStatus="status">
                        <option value="${item.coDiv}">${item.coName}</option>
                        </c:forEach>
                    </select>
                </div>            
            </div>
        
        </form>
        <button type="button" class="col-12 mt-4 mb-4 btn btn-md bg-blue-dark btn-full shadow-xl text-uppercase font-800 rounded-s" id="btnSave">
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

		var element_wrap1 = document.getElementById('wrap1');
		var element_wrap2 = document.getElementById('wrap2');
		
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
			setSelectValue('coDiv');
		})
		
		$('#msHomeaddr1, .wrap1').on('click', function() {
	        element_wrap1.style.display = 'none';
	        element_wrap2.style.display = 'none';
			execDaumPostcode('msHome');
		})	
		$('#msCompaddr1, .wrap2').on('click', function() {
			/* var sParams = String.format("searchAddr={0}", $('#msCompaddr1').val().replace(' ',''));
			var sUrl = "<c:url value='/member/memberAddrPop'/>" + "?" + encodeURI(sParams);
			
			$('#memberAddrPop').load(sUrl);
			$('#memberAddrPop').addClass('menu-active'); */
	        element_wrap1.style.display = 'none';
	        element_wrap2.style.display = 'none';
			execDaumPostcode('msComp');
		})	
			
    function foldDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_wrap1.style.display = 'none';
        element_wrap2.style.display = 'none';
    }
	
    var themeObj = {
    		   bgColor: "#162525", //바탕 배경색
    		   searchBgColor: "#162525", //검색창 배경색
    		   contentBgColor: "#162525", //본문 배경색(검색결과,결과없음,첫화면,검색서제스트)
    		   pageBgColor: "#162525", //페이지 배경색
    		   textColor: "#FFFFFF", //기본 글자색
    		   queryTextColor: "#FFFFFF", //검색창 글자색
    		   //postcodeTextColor: "", //우편번호 글자색
    		   //emphTextColor: "", //강조 글자색
    		   outlineColor: "#444444" //테두리
    		};
    
    function execDaumPostcode(str) {
        // 현재 scroll 위치를 저장해놓는다.
        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        
        if(str == 'msHome') {
        	element_wrap = element_wrap1;
        }else {
        	element_wrap = element_wrap2;        	
        }
        
        new daum.Postcode ({
        	theme: themeObj
            , oncomplete: function(data) {
                var addr = ''; // 주소 변수
                addr = data.jibunAddress;                

                document.getElementById(str + "zip").value = data.zonecode;
                document.getElementById(str + "addr1").value = addr;
                document.getElementById(str + "addr2").focus();

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_wrap.style.display = 'none';

                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                document.body.scrollTop = currentScroll;
            }
            // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            , onresize : function(size) {
                element_wrap.style.height = size.height+'px';
            }
            , width : '100%'
            , height : '468px'
        }).embed(element_wrap);

        // iframe을 넣은 element를 보이게 한다.
        element_wrap.style.display = 'block';
        
    }
			
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
		          url: "/member/saveMemberModify"
		        , type: "post"
		        , dataType: 'json'
		        , data: $('#frmMember').serialize()
		        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
		        , success: function(data) {		    	
		            if(data.result){
		            	alertModal.success('수정이 완료되었습니다.');
		            	goAfterModal();
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