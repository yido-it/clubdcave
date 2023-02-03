<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />
<body class="theme-dark">
    
<div id="preloader"><div class="spinner-border color-highlight" role="status"></div></div>
    
<div id="page">
    
	<div class="header header-fixed header-logo-app">
		<a href="javascript:history.back()" class="header-title header-subtitle">프로선택</a>
		<a href="#" data-back-button class="header-icon header-icon-1"><i class="fa fa-arrow-left"></i></a>
		<a href="#" data-menu="menu-main" class="header-icon header-icon-2"><i class="fas fa-bars"></i></a>
	</div>
    <jsp:include page="../common/menu.jsp" />

    <div class="page-content header-clear">
        <div class="divider divider-margins"></div>
        <div class="menu-title">
            <h1 class="my-0 py-0">프로필수정</h1>
            <!-- <a href="" class="font-12 mr-2 btn btn-sm color-red-dark border-red-dark rounded-s" style="width:auto;height:auto" >
                회원탈퇴</a> -->
        </div>
        <div class="divider divider-margins"></div>

        <!--사진수정-->
        <div class="card card-style">
            <div class="content mb-0">
                <h3>사진수정</h3>
                <p>
                    세로로 긴 사진 업로드를 추천합니다.(사진크기 3MB이하)
                </p>
                <div class="file-data pb-5">
                    <input type="file" id="profileUpload" class="upload-file bg-highlight shadow-s rounded-s " accept="image/*">
                    <p class="upload-file-text color-white font-15"><i class="fa fa-camera"></i> 사진 찾기</p>
                </div>
             
                <div class="row mb-0 mt-2"> 
                    <!--사진1-->
                    <div class="col-6 mb-3">
                        <img id="image-data float-left" src="images/pictures/pro4.jpg" class="img-fluid"> 
                        <div class="d-flex mt-1">
                            <div>
                                <a href="#" class="border-0">
                                    <i class="fa-regular fa-images color-brown-dark"></i>
                                    <span class="color-white">1.4MB</span>
                                </a>      
                            </div>
                            <div class="ml-auto pl-3 text-right">
                                <a class="color-red-dark font-14 ">
                                    <i class="fa-regular fa-rectangle-xmark"></i> 삭제
                                </a>    
                            </div>
                        </div> 
                    </div>
                  <!--//사진1-->
            </div>

            </div>
        </div>
        <!--.//사진수정-->

        <div class="content" id="formArea">
        <form id="frmPro" name="frmPro">  
            <!--개인정보 출신대학빼고 다 필수-->
            <h3 class="font-700 mt-5 mb-3">개인정보</h3> 
 
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
            <div class="mb-3">
                <div class="input-style input-style-2  input-required">
                    <span class="color-highlight input-style-1-active">거주지(시,구,동)</span>
                    <em><em><i class= "fas fa-search font-17"></i></em></em>
                    <input class="form-control" type="hidden" id="msHomezip" name="msHomezip" value="${sessionScope.msMember.msHomezip}" >
                    <input class="form-control" type="name" placeholder="" id="msHomeaddr1" name="msHomeaddr1" value="${sessionScope.msMember.msHomeaddr1}"/>
                </div>
                 <div class="input-style input-style-2  input-required">
                    <span class="color-highlight input-style-1-active">상세주소</span>
                    <em></em>
                    <input class="form-control" type="name" placeholder="" id="msHomeaddr2" name="msHomeaddr2" value="${sessionScope.msMember.msHomeaddr2}"/>
                </div>  
            </div>     

            <div class="input-style input-style-2 input-required">
                <span class="input-style-1-active input-style-1-inactive">경력사항 입력</span>
                <em></em>
               	<textarea class="form-control" placeholder="ex)대학교/전공, 투어경력, 방송활동, 레슨경력" id="n_001" name="n_001">
               		<c:forEach items="${noticeList}" var="item" varStatus="status">
               		<c:if test="${item.noticeDiv == '001'}">
	        			${item.proRemark}
	        		</c:if>
					</c:forEach>
               	</textarea>
            </div>     

            <div class="mb-3">
                <div class="input-style input-style-2 input-required" style="display:flex;">
                    <span class="color-highlight input-style-1-active">차량번호(최대 3대)</span>
                    <em></em>
                     <input class="form-control col-4" type="name" placeholder="" id="msCarNo1" name="msCarNo1" value="${carList[0].msCarNo}">
                     <input class="form-control col-4" type="name" placeholder="" id="msCarNo2" name="msCarNo2" value="${carList[1].msCarNo}">
                     <input class="form-control col-4" type="name" placeholder="" id="msCarNo3" name="msCarNo3" value="${carList[2].msCarNo}">

                </div> 
            </div>

            <!--레슨관련-->
            <h3 class="font-700 mt-5 mb-3">레슨가능날짜,시간</h3> 
    
            <div class="row mb-0 mt-4">
                <div class="col-12 mb-1">
                    <div class="input-style input-style-2 input-required">
                        <span class="input-style-1-active input-style-1-inactive">레슨계획 입력</span>
                        <em></em>
                        <textarea class="form-control" id="n_002" name="n_002" placeholder="자유롭게 기술"></textarea>
                    </div>
                </div>
                <div class="col-6 mb-1">
                    <div class="input-style input-style-2  input-required">
                        <span class="color-highlight input-style-1-active">레슨가능횟수</span>
                        <em><i class="fa fa-angle-down"></i></em>
                        <select>
                            <option value="" disabled>빈도 선택</option>
                            <option value="week1">주1회</option>
                            <option value="week2">주2회</option> 
                            <option value="week3">주3회</option>
                            <option value="week4">주4회</option> 
                            <option value="week5">주5회</option>
                            <option value="week6">주6회</option> 
                            <option value="week7">주7회</option> 
                        </select>
                    </div> 
                </div>
                <div class="col-6 mb-1">
                    <div class="input-style input-style-2  input-required">
                        <span class="color-highlight input-style-1-active">선호 요일</span>
                        <input class="form-control" type="name" placeholder="ex) 월,수">
                    </div> 
                </div>
            
                <div class="col-6 mb-1">
                    <div class="input-style input-style-2  input-required">
                        <span class="color-highlight input-style-1-active">레슨 선호시간</span>
                        <input class="form-control" type="name" placeholder=" ex) 19시~21시">
                    </div> 
                </div>
                <div class="col-6 mb-1">
                    <div class="input-style input-style-2  input-required">
                        <span class="color-highlight input-style-1-active">필드 레슨일</span>
                        <input class="form-control" type="name" placeholder=" ex) 매주 화요일">
                    </div> 
                </div>
                <div class="col-12 mb-1">
                    <div class="input-style input-style-2 input-required">
                        <span class="color-highlight">선호 업장</span>
                        <em><i class="fa fa-angle-down"></i></em>
						<select id="coDiv" name="coDiv">
							<option value="" disabled>선호하는 업장</option>
			                <c:forEach items="${placeList}" var="item" varStatus="status">
			                <option value="${item.coDiv}">${item.coName}</option>
			                </c:forEach>
			             </select>
                    </div> 
                </div>
            
            </div>
              
            <!--자격증-->
            <h3 class="font-700 mt-4 mb-3">자격증</h3> 
            <div class="row mb-0">
                <c:forEach items="${licenseList}" var="item" varStatus="status">
                <div class="col-6">
                    <div class="form-check icon-check">
                        <input class="form-check-input" type="checkbox" id="${'l_'}${item.cdCode}" name="${'l_'}${item.cdCode}" value="Y">
                        <label class="form-check-label" for="${'l_'}${item.cdCode}">${item.cdTitle1}</label>
                        <i class="icon-check-1 fa fa-square color-gray-dark font-16"></i>
                        <i class="icon-check-2 fa fa-check-square font-16 color-highlight"></i>
                	</div>
                </div>
                </c:forEach>
            </div>

            <!--선택-->
            <h3 class="font-700 mt-5 mb-3">SNS정보입력</h3> 
            <div class="row mb-0 mt-4">
                <div class="col-12 mb-3">
                    <div class="input-style input-style-2 input-required">
                        <span class="color-highlight input-style-1-active">유튜브채널 주소</span>
                        <input class="form-control" type="name" placeholder="https://www.youtube.com/@채널명" id="n_004" name="n_003">
                    </div> 
                </div> 
                <div class="col-12 mb-3">
                    <div class="input-style input-style-2 input-required">
                        <span class="color-highlight input-style-1-active">인스타그램 주소</span>
                       <input class="form-control" type="name" placeholder="https://www.instagram.com/계정명" id="n_006" name="n_006">
                    </div> 
                </div> 
                <div class="col-12 mb-3">
                    <div class="input-style input-style-2 input-required">
                        <span class="color-highlight input-style-1-active">카카오 오픈채팅 주소</span>
                        <input class="form-control" type="name" placeholder="" id="msCarNo5" name="n_005">
                    </div> 
                </div>             
            </div>
            <input type="hidden" id="msNum" name="msNum" value="${sessionScope.msMember.msNum}"/>
            <div class="mt-4 mb3" style="display:flex;">
	            <button type="button" class="col-6 btn bg-green-dark btn-full shadow-xl text-uppercase font-800 rounded-s" id="btnMidSave">
	                중간저장
	            </button>  
	            <button type="button" class="col-6 ml-1 btn bg-blue-dark btn-full shadow-xl text-uppercase font-800 rounded-s" id="btnSubmit">
	            	제출하기
	            </button>  
            </div>     
        </form>
        <c:forEach items="${noticeList}" var="item" varStatus="status">
        	<input type="hidden" class='hidden-notice' id="${item.noticeDiv}" value="${item.proRemark}"/>
		</c:forEach>
        </div>
        
    </div>
    <!-- Page content ends here--> 
</div>
 
<div id="menu-success-1" class="menu menu-box-bottom bg-blue-dark rounded-0" data-menu-height="335" data-menu-effect="menu-parallax" >
    <h1 class="text-center mt-4"><i class="fa fa-3x fa-check-circle scale-box color-white shadow-xl rounded-circle"></i></h1>
    <h1 class="text-center mt-3 font-700 color-white">관리자 승인필요</h1>
    <p class="boxed-text-l color-white opacity-70 font-16">
        관리자 검토/승인후에 프로필이 반영됩니다. <br/>
        제출하시겠습니까?
    </p>
    <a  data-menu="timed-5" class="font-18  btn btn-m btn-center-m button-s shadow-l rounded-s text-uppercase font-600 bg-white color-black">
        확인</a>  
</div>
<div class="menu-hider"><div></div></div>

<jsp:include page="../common/alertModal.jsp" />  
<script type="text/javascript">
	<c:forEach items="${proLicList}" var="item" varStatus="status">
		$('${"#l_"}${item.licKind}').attr('checked', true);
	</c:forEach>
	if(localStorage.getItem('frmPro') != undefined) {
		var obj = JSON.parse(localStorage.getItem('frmPro'));
		console.log(obj);
		if(obj.msNum == $('#msNum').val()) {
			for(var key in obj) {
				var type = $('#' + key).prop('type');
				if(type.indexOf('text') > -1) {
					$('#' + key).val(obj[key]);
				}
				if(type.indexOf('select') > -1) {
					setSelectValue(key, obj[key]);
				}
				if(type == 'date') {
					setDateValue(key, obj[key]);
				}
				if(type.indexOf('check') > -1) {
					if(obj[key] == 'Y') {						
					$('#' + key).prop('checked', true);
					}
				}
			}	
		}		
	}
	 $('.hidden-notice').each(function () {
		var id = this.id;
		var val = $(this).val().replaceAll("<br/>", "\r\n");
		$('#n_' + id).val(val);
	}) 
	
	$('#btnMidSave').on('click', function() {
		var midSave = new Object();
		var formArr = $('#frmPro').serializeArray();
		var obj = {};
		$.each(formArr, function() {
			obj[this.name] = this.value;
		})
		localStorage.setItem('frmPro', JSON.stringify(obj));
		alertModal.success('중간저장이 완료되었습니다.');
	})
	
	$('#btnSubmit').on('click', function() {
		console.log($('#frmPro').serialize());
		alertModal.success('제출이 완료되었습니다.');
		localStorage.removeItem('frmPro');
		//saveProForm();
	})
			
	function saveProForm() {			
	    $.ajax({
	        url: "/pro/saveProForm"
	        , type: "post"
	        , dataType: 'json'
	        , data: $('#frmPro').serialize()
	        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
	        , success: function(data) {		    	
	            if(data.result){
	            	alertModal.success('제출이 완료되었습니다.');
	            	localStorage.removeItem('frmPro');
	            	goAfterModal();
	            } else {
	            	alertModal.fail(data.message);                    
	            }                
	        }
	        , error: function(data) {
	        	alertModal.fail('[error] 제출 중 오류가 발생했습니다.');
	        }
	    });
		
	}

</script>
<jsp:include page="../common/script.jsp" />
</body>
</html>