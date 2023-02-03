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
        <!-- <h3 class="font-700">개인정보</h3> --> 
        <input type="hidden" id="msNum" name="msNum" value="">
        <div class="input-style input-style-2 input-required">
            <span class="color-highlight">성별 선택</span>
            <em><i class="fa fa-angle-down"></i></em>
            <select id="msSex" name="msSex">
                <option value="" disabled>성별 선택</option>
                <option value="M">남성</option>
                <option value="F">여성</option> 
            </select>
        </div>

        <div class="input-style input-style-2 input-required">
            <span style="color:#3b80d6">생년월일</span>
            <em><i class="fa-solid fa-calendar-days font-17"></i></em>
            <input type="date" id="msBirth" name="msBirth" value="">
        </div>
		
		<div class="input-style input-style-2  input-required">
                   <span class="color-highlight input-style-1-active">거주지(시,구,동)</span>
                   <em><em><i class= "fas fa-search font-17"></i></em></em>
                   <input class="form-control" type="hidden" id="msHomezip" name="msHomezip" >
                   <input class="form-control" type="name" placeholder="" id="msHomeaddr1" name="msHomeaddr1" />
               </div>
                <div class="input-style input-style-2  input-required">
                   <span class="color-highlight input-style-1-active">상세주소</span>
                   <em></em>
                   <input class="form-control" type="name" placeholder="" id="msHomeaddr2" name="msHomeaddr2"/>
               </div>  
               
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

        <div class="row mb-0"> 
            <div class="col-6 mb-3">
                <div class="input-style input-style-2  input-required">
                    <span class="color-highlight input-style-1-active">직업</span>
                    <select id="msJobCd" name="msJobCd">
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
                    <input class="form-control" type="name" placeholder="" id="msCompnm" name="msCompnm">
                </div> 
            </div>
            <div class="col-12 mb-3">
                <div class="input-style input-style-2"">
                    <span class="color-highlight input-style-1-active">직장주소</span>
                    <em><i class= "fas fa-search font-17"></i></em>
                    <input class="form-control" type="hidden" id="msCompzip" name="msCompzip" >
                    <input class="form-control" type="name" placeholder="직장주소(시,구,동)" id="msCompaddr1" name="msCompaddr1"/>
                </div> 
            </div>
            <div class="col-12 mb-3">
                <div class="input-style input-style-2 input-required">
                    <span class="color-highlight input-style-1-active">상세주소</span>
                    <input class="form-control" type="name" placeholder="" id="msCompaddr2" name="msCompaddr2">
                </div> 
            </div>
            <div class="col-12 mb-0">
                <div class="input-style input-style-2 input-required" style="display:flex;">
                    <span class="color-highlight input-style-1-active">차량번호(최대 3대)</span>
                    <input class="form-control col-4" type="name" placeholder="" id="msCarNo1" name="msCarNo1">
                    <input class="form-control col-4" type="name" placeholder="" id="msCarNo2" name="msCarNo2">
                    <input class="form-control col-4" type="name" placeholder="" id="msCarNo3" name="msCarNo3">
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
		
		$('#btnSkip').on('click', function() {
			$('#memberComplete').addClass('menu-active');
		})
		
		$('#btnSave').on('click', function() {
			console.log($('#frmMemberAdd').serialize());	
			
			//saveMemberAdd();
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
						$('#memberAddPop').removeClass();
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