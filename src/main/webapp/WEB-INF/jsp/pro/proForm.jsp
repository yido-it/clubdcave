<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />
<script type="text/javascript" src="/scripts/load-image-all.min.js"></script>
<body class="theme-dark">
    
<div id="preloader"><div class="spinner-border color-highlight" role="status"></div></div>
    
<div id="page">
    
	<div class="header header-fixed header-logo-app">
		<a href="#" class="header-title header-subtitle">프로선택</a>
		<a href="#" data-back-button class="header-icon header-icon-1" style="margin-top: 8px !important;    font-size: 20px;">
			<i class="fa fa-arrow-left"></i></a>
		<a href="#" data-menu="menu-main" class="header-icon header-icon-2 font-20 mt-1"><i class="fas fa-bars"></i></a>
	</div>
    <jsp:include page="../common/menu.jsp" />

    <div class="page-content header-clear">
        <div class="divider divider-margins"></div>
        <div class="menu-title">
            <h1 class="my-0 py-0">프로필수정</h1>
       
        </div>
        <div class="divider divider-margins"></div>

        <!--사진수정-->
        <div class="card card-style">
            <div class="content mb-0">
                <h3>사진수정</h3>
                <p>
                    세로로 긴 사진 업로드를 추천합니다.
                </p>
                <div class="dropzone" id="">
                    <button type="button" id="profileUpload" class="btn bg-highlight color-white font-15 dz-message mb-5" style="width:100%;height:100%">
                    <i class="fa fa-camera"></i> 사진 찾기</button>
                    
                    <c:if test="${proProfile != null}">
                    <div class="img-area px-0 col-6 mb-3">
						<div class="">
							<div class="dz-image my-2" style="text-align: center">
								<img alt="${proProfile.msImgName}" src="${proProfile.thumbURL}" data-dz-thumbnail>
							</div>
							<div class="d-flex mt-1">
								<div class="ml-auto pl-3 text-right">
									<a class="dz-remove color-red-dark font-14 btn" href="javascript:undefined;" 
										id="${sessionScope.msMember.msNum}" data-filepath="${proProfile.msImgPath}" data-filename="${proProfile.msImgName}" data-dz-remove>
									<i class="fa-regular fa-rectangle-xmark"></i> 삭제
									</a>
								</div>								
							</div>
						</div>
					</div>
                    </c:if>
                </div>        

            </div>
        </div>
        <!--.//사진수정-->

        <div class="content" id="formArea">
        <form id="frmPro" name="frmPro">  
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
                <input type="text" id="msBirth" name="msBirth" value="${sessionScope.msMember.msBirth}">
            </div>
            <div class="row mb-1 mt-3">
	            <div class="col-6 mb-1">
	                <div class="input-style input-style-2  input-required">
	                    <span class="color-highlight">지역(시/도)</span>
	                    <em><i class="fa fa-angle-down"></i></em>
	                    <input class="form-control" type="hidden" id="msArea1Val" value="${sessionScope.msMember.msArea1}" >
	                      <select id="msArea1" name="msArea1">
	                        <option value="">선택하세요</option>
	                        <c:forEach items="${msArea1List}" var="item" varStatus="status">
	                        <option value="${item.cdCode}">${item.cdTitle1}</option>
			                </c:forEach>
	                    </select>
	                </div> 
	            </div>
                <div class="col-6 mb-1">
                    <div class="input-style input-style-2  input-required">
                        <span class="color-highlight">시/군/구</span>
                        <em><i class="fa fa-angle-down"></i></em>
                        <input class="form-control" type="hidden" id="msArea2Val" value="${sessionScope.msMember.msArea2}" >
                        <select id="msArea2" name="msArea2">
                            <option value="">선택하세요</option>
                        </select>
                    </div> 
                </div>
            </div>    

            <div class="input-style input-style-2 input-required">
                <span class="input-style-1-active input-style-1-inactive">경력사항 입력</span>
                <em></em>
               	<textarea class="form-control" placeholder="ex)학력, 투어경력, 방송활동, 레슨경력" style="height: 110px;padding: 10px;line-height: unset;" id="n_001" name="n_001"></textarea>
            </div>     

            <div class="mb-3">
                 <div class="row mb-0 mt-4" id="carArea1">
		            <div class="col-12 mb-1">
		                <div class="input-style input-style-2 input-required">
		                    <span class="color-highlight input-style-1-active">차량번호(최대3대)</span>
		                    <p class="font-13 color-green-dark mb-0" style="position: absolute; right: 10px;top:12px;height:30px;z-index:1;" id="btnAddCar">
		                     <i class="fa-solid fa-plus font-12"></i> 차량추가</p>
		                    <input class="form-control" type="name" id="msCarNo1" name="msCarNo1" value="${carList[0].msCarNo}">
		                </div> 
		            </div>
		         </div>
				<!--차량추가2-->
		        <div class="row mb-0 mt-0" id="carArea2" style="display:none;">
		            <div class="col-12 mb-1">
		                <div class="input-style input-style-2 input-required">
		                    <p class="font-13 color-red-dark mb-0" style="position: absolute; right: 10px;top:12px;height:30px;z-index:1;" id="btnDelCar2"> 
		                    <i class="fa-solid fa-x  font-12"></i> 차량삭제</p>
		                    <input class="form-control" type="name" id="msCarNo2" name="msCarNo2" value="${carList[1].msCarNo}">
		                </div> 
		            </div>
		        </div>
				<!--차량추가3-->
				<div class="row mb-0 mt-0" id="carArea3" style="display:none;">
				    <div class="col-12 mb-1">
				        <div class="input-style input-style-2 input-required">
				            <p class="font-13 color-red-dark mb-0" style="position: absolute; right: 10px;top:12px;height:30px;z-index:1;" id="btnDelCar3"> 
				            <i class="fa-solid fa-x  font-12"></i> 차량삭제</p>
				            <input class="form-control" type="name" id="msCarNo3" name="msCarNo3" value="${carList[2].msCarNo}">
				        </div> 
				    </div>
				</div>
			</div>

            <!--레슨관련-->
            <h3 class="font-700 mt-5 mb-3">레슨</h3> 
    
            <div class="row mb-0 mt-4">
                <div class="col-12 mb-1">
                    <div class="input-style input-style-2 input-required">
                        <em></em>
                        <span class="input-style-1-active input-style-1-inactive">레슨계획</span>
                        <textarea class="form-control" placeholder="자유롭게 기술" style="height: 110px;padding: 10px;line-height: unset;" id="n_002" name="n_002"></textarea>
                    </div>
                </div>
                <div class="col-12 mb-1">
                    <div class="input-style input-style-2 input-required">
                        <em></em>
                        <span class="input-style-1-active input-style-1-inactive">레슨일정</span>
                        <textarea class="form-control" placeholder="레슨가능날짜, 시간, 횟수 등" style="height: 110px;padding: 10px;line-height: unset;" id="n_007" name="n_007"></textarea>
                    </div>
                </div>
                <div class="col-12 mb-1">
                    <div class="input-style input-style-2 input-required">
                        <span class="color-highlight">선호 업장</span>
                        <em><i class="fa fa-angle-down"></i></em>
                        <input class="form-control" type="hidden" id="coDivVal" value="${msFirstPick}" >
						<select id="coDiv" name="coDiv">
							<option value="" selected>선호하는 업장</option>
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
                        <input class="form-control" type="name" placeholder="https://www.youtube.com/@채널명" id="n_003" name="n_003">
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
                        <input class="form-control" type="name" placeholder="" id="n_005" name="n_005">
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
<div class="menu-hider"><div></div></div>
<script type="text/javascript">
	var params;

	$().ready(function() {
		
	    $('#msBirth').datepicker({
	        changeYear: true,
	        changeMonth: true,
	        dateFormat: 'yy-mm-dd',
	        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
	        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	        showMonthAfterYear: true,
	        yearSuffix: '년',
	        yearRange: '-100:+00',
        	
	    }); 
	    
	    setDateValue('msBirth');
		
	    setSelectValue('msSex');
	    setSelectValue('coDiv');
	    
		setSelectValue('msArea1');
		if($('#msArea1').val() != '') {
			getArea2List();
		}
	    
		showCarInput();
	})
	
	Dropzone.autoDiscover = false;
		var imgDropzone = new Dropzone('div.dropzone', {
			  autoProcessQueue : true
			, url : '/pro/uploadProfileImg'
			, method : 'post'
			, maxFiles : 1
			, maxFilesize : 10
			, resizeQueality : 1
			, resizeWidth : 960
			, dictFileTooBig : '{{maxFilesize}}MB 이하로 업로드 해주세요.'
			, paramName : 'file'
			, addRemoveLinks : true
			, acceptedFiles : "image/*"
			, uploadMultiple : false
			, previewTemplate : '<div class="dz-preview px-0 col-6 mb-3 dz-processing dz-image-preview" style="display:none;"></div>'
			, init : function() {
				// 파일 개수 초과
				this.on("maxfilesexceeded", function (file) {
					this.removeAllFiles();
					this.addFile(file);
				});
				// 에러 발생 (ex 파일 용량 초과)
		   		this.on("error", function(file, message) { 
		   			alertModal.fail(message);
	                this.removeFile(file); 
	    		});
				// 파일 dropzone area에 올라간 후 (총 업로드 개수 제한)
		   		this.on("addedfile", function (file) {
		   			if($(".img-area").length >= 1) {
		   				this.removeAllFiles();
		   				alertModal.fail('등록된 사진을 먼저 삭제해주세요!');
			   			 /* var params = {
			   				  msImgName : $('.img-area').find('a').data('filename')	
			   				, msImgPath : $('.img-area').find('a').data('filepath')
			   				, msNum : $('#msNum').val()
			   			}
		   				deleteProfileImg(params);  */
		   			}
		   			
		   		});
				// 파일 업로드 중
				this.on('sending', function(file, xhr, formData){										
		   			formData.append('msNum', $("#msNum").val());
		   		});	
				// 파일 업로드 성공 후
				this.on("success", function(file, res){
					this.removeAllFiles();
	            	goAfterModal();  	                   
                });
			}
		});
		
	var objData = []; // 제출 시 비교할 기존 데이터
	objData["msNum"] = "${sessionScope.msMember.msNum}";
	objData["msSex"] = "${sessionScope.msMember.msSex}";
	objData["msBirth"] = "${sessionScope.msMember.msBirth}";
	objData["msArea1"] = "${sessionScope.msMember.msArea1}";
	objData["msArea2"] = "${sessionScope.msMember.msArea2}";
	objData["coDiv"] = "${msFirstPick}";
	<c:forEach items="${carList}" var="item" varStatus="status">
		objData['${"msCarNo"}${status.count}'] = '${item.msCarNo}';
	</c:forEach>
	<c:forEach items="${noticeList}" var="item" varStatus="status">
		objData['${"n_"}${item.noticeDiv}'] = '${item.proRemark}'.replaceAll("<br/>", "\r\n");
	</c:forEach>
	<c:forEach items="${proLicList}" var="item" varStatus="status">
		$('${"#l_"}${item.licKind}').attr('checked', true);
		objData['${"l_"}${item.licKind}'] = 'Y';
	</c:forEach>
	
	if(localStorage.getItem('frmPro') != undefined) {
		var obj = JSON.parse(localStorage.getItem('frmPro'));
		console.log(obj);
		if(obj.msNum == $('#msNum').val()) {
			for(var key in obj) {
				var type = $('#' + key).prop('type');
				if(type != undefined) {
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
	}
	
	 $('.hidden-notice').each(function () {
		var id = this.id;
		var val = $(this).val().replaceAll("<br/>", "\r\n");
		$('#n_' + id).val(val);
	}) 
	
	function showCarInput() {
			if($('#msCarNo2').val() != '' && $('#msCarNo2').val() != null) {
				$('#carArea2').show();
			}
			if($('#msCarNo3').val() != '' && $('#msCarNo3').val() != null) {
				$('#carArea3').show();
			}
	}
	 
	$('.dz-remove').on('click', function() {	
		
		 params = {
			  msImgId : $(this).attr('id')
			, msImgName : $(this).data('filename')	
			, msImgPath : $(this).data('filepath')
			, msNum : $('#msNum').val()
		};		
		alertModal.confirm2('사진을 삭제하시겠습니까?', 'deleteProfileImg(params);');
	})	 
	
	function deleteProfileImg(params) {
		$.ajax({
	        url: "<c:out value='/pro/deleteProfileImg'/>"
	        , type: "post"
	        , dataType: 'json'
	        , data: params
	        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
	        , success: function(data) {
	            if(data.result){
	            	document.getElementById(params.msImgId).closest('.img-area').remove();
	            	
	            	$("#confirm1Popup").removeClass('menu-active');
	            	params = null;
	            	
	            } else {
	                alertModal.fail(data.message);                    
	            }                
	        }
	        , error: function(data) {
	        	 alertModal.fail('[error] 코드 호출 중 오류 발생했습니다.');
	        }
	    });
	}
	
	$('#msArea1').on('change', function() {
		getArea2List();
	})
	
	function getArea2List() {
		var params = {
			  coDiv : '001'
			, cdDivision : '221'	
			, cdCode : $('#msArea1').val()
		}		
		
		$.ajax({
	        url: "<c:out value='/common/getCommonCodeDetailList'/>"
	        , type: "post"
	        , dataType: 'json'
	        , data: params
	        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
	        , success: function(data) {
	            if(data.result){		          
	            	var list = data.detailList;
	            	$('.cd_detail_list').remove();
					for(var i = 0; i < list.length; i++) {
						var option = $("<option value='" + list[i].cdCode + "' class='cd_detail_list'>" + list[i].cdTitle1 + "</option>");
						$('#msArea2').append(option);
					}
					setSelectValue('msArea2');
	            } else {
	                alertModal.fail(data.message);                    
	            }                
	        }
	        , error: function(data) {
	        	 alertModal.fail('[error] 코드 호출 중 오류 발생했습니다.');
	        }
	    });
	}

	$('#btnAddCar').on('click', function() {
		if(!$('#carArea2').is(':visible')) {
			$('#carArea2').show();				
		} else {				
			$('#carArea3').show();
		}
	})
	
	$('#btnDelCar2').on('click', function() {			
		$('#carArea2').hide();
		$('#msCarNo2').val('');
	})
	
	$('#btnDelCar3').on('click', function() {
		$('#carArea3').hide();
		$('#msCarNo3').val('');		
	})
	
	$('#btnMidSave').on('click', function() {
		var midSave = new Object();
		var frmArr = $('#frmPro').serializeArray();
		var obj = [];
		$.each(frmArr, function() {
			obj[this.name] = this.value;
		})
		localStorage.setItem('frmPro', JSON.stringify(obj));
		alertModal.success('중간저장이 완료되었습니다.');
	})
	
	$('#btnSubmit').on('click', function() {
		var frmArr = $('#frmPro').serializeArray()
		var result = false;
		$.each(frmArr, function() {
			if(objData[this.name] != this.value && !(this.value == '' && objData[this.name] == undefined)) {
				result = true;
				return result;
			}
			//console.log(objData[this.name] + ' == ' + this.value + ', result : ' + result);
		})
		
		if(!result) {
			alertModal.fail('변경된 내용이 없습니다.');
			return;
		}
		alertModal.confirm1('관리자 검토/승인후에 프로필이 반영됩니다.<br/>제출하시겠습니까?', 'saveProForm()', '관리자 승인필요');
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
	            	
               		$("#confirm1Popup").removeClass('menu-active');
	            	
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