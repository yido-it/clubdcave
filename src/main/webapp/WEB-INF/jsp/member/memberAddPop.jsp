<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<jsp:include page="../common/script.jsp" />

    <div class="menu-title">
        <h1 class="my-0 py-0">선택정보입력</h1>
        <a id="btnSkip" class="font-14 mr-2 mb-3 bold btn btn-sm color-red-dark border-red-dark rounded-s" style="width:auto;height:auto;top:30%" >
            건너뛰기
        </a>
    </div>
	<form id="frmMemberAdd" name="frmMemberAdd">
    <div class="content mt-5">
       <h4 class="font-700 mt-5 mb-3">개인정보</h4>
        <input type="hidden" id="msNum" name="msNum" value="${sessionScope.msMember.msNum}">
        <div class="input-style input-style-2 input-required">
            <span class="color-highlight">성별 선택</span>
            <em><i class="fa fa-angle-down"></i></em>
            <select id="msSex" name="msSex">
                <option value="">성별 선택</option>
                <option value="M">남성</option>
                <option value="F">여성</option> 
            </select>
        </div>

        <div class="input-style input-style-2 input-required">
            <span style="color:#3b80d6">생년월일</span>
            <em><i class="fa-solid fa-calendar-days font-17"></i></em>
            <input type="text" class="form-control" id="msBirth" name="msBirth" value="" readonly style="    height: 50px;">
        </div>
        
		
		<div class="row mt-3 mb-0">
            <div class="col-6 mb-1">
                <div class="input-style input-style-2  input-required">
                    <span class="color-highlight">지역(시/도)</span>
                    <em><i class="fa fa-angle-down"></i></em>
                      <select id="msHomeaddr1" name="msHomeaddr1">
                        <option value="" disabled>선택하세요</option>
                        <option value="서울시">서울시</option>
                        <%-- <c:forEach items="${msHomeaddr1List}" var="item" varStatus="status">
                        <option value="${item.cdCode}">${item.cdTitle1}</option>
		                </c:forEach> --%>
                    </select>
                </div> 
            </div>
            <div class="col-6 mb-1">
                <div class="input-style input-style-2  input-required">
                    <span class="color-highlight">시/군/구</span>
                    <em><i class="fa fa-angle-down"></i></em>
                    <select id="msHomeaddr2" name="msHomeaddr2">
                        <option value="" disabled>선택하세요</option>
                        <option value="강남구">강남구</option>
                        <%-- <c:forEach items="${msHomeaddr2List}" var="item" varStatus="status">
                     	<option value="${item.cdCode}">${item.cdTitle1}</option>
              			 </c:forEach> --%>
                    </select>
                </div> 
            </div> 
        </div>
          
               <div class="row mb-0 mt-2" id="carArea1">
		            <div class="col-12 mb-1">
		                <div class="input-style input-style-2 input-required">
		                    <span class="color-highlight input-style-1-active">차량번호(최대3대)</span>
		                    <p class="font-13 color-green-dark mb-0" style="position: absolute; right: 10px;top:12px;height:30px;z-index:1;" id="btnAddCar">
		                     <i class="fa-solid fa-plus font-12"></i> 차량추가</p>
		                    <input class="form-control" type="name" id="msCarNo1" name="msCarNo1">
		                </div> 
		            </div>
		         </div>
				<!--차량추가2-->
		        <div class="row mb-0 mt-0" id="carArea2" style="display:none;">
		            <div class="col-12 mb-1">
		                <div class="input-style input-style-2 input-required">
		                    <p class="font-13 color-red-dark mb-0" style="position: absolute; right: 10px;top:12px;height:30px;z-index:1;" id="btnDelCar2"> 
		                    <i class="fa-solid fa-x  font-12"></i> 차량삭제</p>
		                    <input class="form-control" type="name" id="msCarNo2" name="msCarNo2">
		                </div> 
		            </div>
		        </div>
				<!--차량추가3-->
				<div class="row mb-0 mt-0" id="carArea3" style="display:none;">
				    <div class="col-12 mb-1">
				        <div class="input-style input-style-2 input-required">
				            <p class="font-13 color-red-dark mb-0" style="position: absolute; right: 10px;top:12px;height:30px;z-index:1;" id="btnDelCar3"> 
				            <i class="fa-solid fa-x  font-12"></i> 차량삭제</p>
				            <input class="form-control" type="name" id="msCarNo3" name="msCarNo3">
				        </div> 
				    </div>
				</div>
            
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
             
         
         <h4 class="font-700 mt-5 mb-3">레슨정보</h4>  
        <div class="input-style input-style-2 input-required">
            <span class="color-highlight">레슨경험</span>
            <em><i class="fa fa-angle-down"></i></em>
            <select id="msLessonExpYn" name="msLessonExpYn">
                <option value="" disabled>레슨경험</option>
                <option value="Y">있음</option>
                <option value="N">없음</option> 
            </select>
        </div>
    
        <div class="row mb-0 mt-4">
            <div class="col-12 mb-3">
                <div class="input-style input-style-2  input-required">
                    <span class="color-highlight input-style-1-active">레슨빈도</span>
                    <input class="form-control" type="name" placeholder="ex) 주2회, 월1회.." id="msLessonTrem" name="msLessonTrem">
                </div> 
            </div>
            <div class="col-12 mb-3">
                <div class="input-style input-style-2  input-required">
                    <span class="color-highlight input-style-1-active">레슨선호시간</span>
                    <input class="form-control" type="name" placeholder=" ex) 30분, 50분.." id="msLessonMinute" name="msLessonMinute">
                </div> 
            </div>

            <div class="col-6 mb-3">
                <div class="input-style input-style-2  input-required">
                    <span class="color-highlight input-style-1-active">라운드빈도</span>
                    <input class="form-control" type="name" placeholder="ex) 주2회, 월1회.." id="msRoundCnt" name="msRoundCnt">
                </div> 
            </div>
            <div class="col-6 mb-3">
                <div class="input-style input-style-2  input-required">
                    <span class="color-highlight input-style-1-active">핸디캡</span>
                    <input class="form-control" type="name" placeholder="ex) 0개, 2개.." id="msHandcap" name="msHandcap">
                </div> 
            </div>
        </div>

		
		<h4 class="font-700 mt-1 mb-3">직장정보</h4>
		
        <div class="row mb-0"> 
            <div class="col-6 mb-3">
                <div class="input-style input-style-2 input-required">
                    <span class="color-highlight">직업</span>
                    <em><i class="fa fa-angle-down"></i></em>
                    <select id="msJobCd" name="msJobCd">
		                <option value="" disabled>직업 선택</option>
		                <c:forEach items="${jobList}" var="item" varStatus="status">
		                <option value="${item.cdCode}">${item.cdTitle1}</option>
		                </c:forEach>
           			</select>
                </div> 
            </div>
            <div class="col-6 mb-3">
                <div class="input-style input-style-2  input-required">
                    <span class="color-highlight input-style-1-active">직장명</span>
                    <input class="form-control" type="name" placeholder="" id="msCompnm" name="msCompnm">
                </div> 
            </div>
            <div class="col-12 mb-3">
                       <div class="input-style input-style-2  input-required">
                           <span class="color-highlight input-style-1-active">직장주소</span>
                           <input class="form-control" type="name" placeholder="" id="msCompnm" name="msCompAddr1" >
                       </div> 
                   </div> 
       	 </div>
       
    </div>
	</form>
    <div class="content mb-0 ">
          <button type="button" class="col-12 mb-4 btn btn-md  bg-blue-dark btn-full shadow-xl text-uppercase font-800 rounded-s" id="btnSave">
			입력완료
          </button>   
    </div>  
	<!-- // 선택정보 입력 -->
		
	<div class="menu-hider"><div></div></div>
	
	<!-- 가입완료 팝업 -->
	<div id="memberComplete" class="menu menu-box-modal rounded-0 " data-menu-height="310" data-menu-width="330" data-menu-effect="menu-parallax" 
	data-backdrop="static" data-keyboard="false" style="display: block;height: 310px;width: 330px;z-index:99;" >
	    <h1 class="text-center mt-4"><i class="fa fa-3x fa-crown scale-box color-yellow-light shadow-xl rounded-circle"></i></h1>
	    <h3 class="text-center mt-3 font-700">회원가입 완료!</h3>
	    <p class="boxed-text-xl opacity-70">
	        김이도 회원님 반갑습니다!<br/>
	        클럽디청담 서비스 이용이 가능합니다 <br>
	    </p>
	    <div class="row mb-0 mr-3 ml-3">
	     
	        <div class="col-12">
	            <a href="javascript:location.href='/main'" class="btn btn-full btn-md bg-blue-dark font-800 text-uppercase rounded-s">시작하기</a>
	        </div>
	    </div>
	</div>
	<!-- // 가입완료 팝업 -->
	
	<script type="text/javascript">
		var frqReg = /^[월주]{1,1}[0-9]{1,3}([회]{1,1})?$/g;
		
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
		})
		
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
		
		$('#btnSkip').on('click', function() {
			$('#memberComplete').addClass('menu-active');
		})
		
		$('#btnSave').on('click', function() {
			var result = true;

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
			
			// 나중에 삭제
			console.log($('#frmMemberAdd').serialize());
			window.scrollTo(0, 0);
			saveMemberAdd();
		})		
		function saveMemberAdd() {
		    $.ajax({
		        url: "<c:out value='/member/saveMemberAdd'/>"
		        , type: "post"
		        , dataType: 'json'
		        , data: $('#frmMemberAdd').serialize()
		        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
		        , success: function(data) {
		            if(data.result){		            	
						$('#memberComplete').addClass('menu-active');
		            } else {
		                alertModal.fail(data.message);                    
		            }                
		        }
		        , error: function(data) {
		        	 alertModal.fail('[error] 추가정보 등록 중 오류가 발생했습니다.');
		        }
		    });
		
		}
	</script>