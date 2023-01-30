<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />
<body class="theme-light">
	<div id="preloader"><div class="spinner-border color-highlight" role="status"></div></div>

	<!-- 선택정보 입력 -->

    <div class="menu-title">
        <h1 class="my-0 py-0">선택정보입력</h1>
        <a data-menu="" href="javascript:location.href='/main'" class="font-14 mr-2 mb-3 bold btn btn-sm color-red-dark border-red-dark rounded-s" style="width:auto;height:auto;top:30%" >
            건너뛰기</a>
    </div>
	<form id="frmMemberAdd" name="frmMemberAdd">
    <div class="content mt-5">
        <!-- <h3 class="font-700">개인정보</h3> --> 
        <input type="hidden" id="msNum" name="msNum" value="">
        <div class="input-style input-style-2 input-required">
            <span class="color-highlight">성별 선택</span>
            <em><i class="fa fa-angle-down"></i></em>
            <select id="msSex" name="msSex">
                <option value="default" disabled selected>성별 선택</option>
                <option value="M">남성</option>
                <option value="F">여성</option> 
            </select>
        </div>

        <div class="input-style input-style-2 input-required">
            <span style="color:#3b80d6">생년월일</span>
            <em><i class="fa-solid fa-calendar-days font-17"></i></em>
            <input type="date" id="msBirth" name="msBirth" value="">
        </div>

        <div class="input-style input-style-2 input-required">
            <span class="color-highlight">레슨경험</span>
            <em><i class="fa fa-angle-down"></i></em>
            <select id="msLessonExpYn" name="msLessonExpYn">
                <option value="default" disabled selected>레슨경험</option>
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
		                <option value="default" disabled selected>선택</option>
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
                    <input class="form-control" type="name" placeholder="테헤란로5 또는 서린동154과 같이 입력 후 검색" id="msCompaddr1" name="msCompaddr1"/>
                </div> 
            </div>
            <div class="col-12 mb-3">
                <div class="input-style input-style-2 input-required">
                    <span class="color-highlight input-style-1-active">주소상세</span>
                    <input class="form-control" type="name" placeholder="" id="msCompaddr2" name="msCompaddr2">
                </div> 
            </div>
            <div class="col-12 mb-0">
                <div class="input-style input-style-2 input-required" style="display:flex;">
                    <span class="color-highlight input-style-1-active">차량번호</span>
                    <input class="form-control col-4" type="name" placeholder="최대3대 등록가능" id="msCarNo1" name="msCarNo1">
                    <input class="form-control col-4" type="name" placeholder="" id="msCarNo2" name="msCarNo2">
                    <input class="form-control col-4" type="name" placeholder="" id="msCarNo3" name="msCarNo3">
                </div> 
            </div>
      
        </div>
        <div class="input-style input-style-2 input-required">
            <span class="color-highlight">선호 업장</span>
            <em><i class="fa fa-angle-down"></i></em>
            <select>
                <option value="default" disabled selected>선호하는 업장</option>
                <option value="chungdam">클럽디청담</option>
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
	
	<!-- 주소 검색 팝업 -->
	<!-- menu menu-box-bottom rounded-0 modal_bay -->
	<div class="menu menu-box-modal rounded-0" id="memberAddrPop" data-menu-height="310" data-menu-width="100%" data-keyboard="false">
	               
	</div>
	<!-- 주소 검색 팝업 -->

	<!-- 가입완료 팝업 -->
	<div id="memberComplete" class="menu menu-box-modal rounded-0 " data-menu-height="310" data-menu-width="330" data-menu-effect="menu-parallax" >
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
	<div class="menu-hider"><div></div></div>
	<jsp:include page="../common/alertModal.jsp" /> 
	<script type="text/javascript">
	
		$('.fa-search').on('click', function() {
			var sParams = String.format("searchAddr={0}", $('#msCompaddr1').val().replace(' ',''));
			var sUrl = "<c:url value='/member/memberAddrPop'/>" + "?" + encodeURI(sParams);
			
			$('#memberAddrPop').load(sUrl);
			$('#memberAddrPop').addClass('menu-active');
		})
		
		$('#btnSave').on('click', function() {
			console.log($('#frmMemberAdd').serialize());	
			
			//saveMemberAdd();
		})
		
		function saveMemberAdd() {
		    $.ajax({
		        url: '/saveMemberAdd',
		        type: "post",
		        dataType: 'json',
		        data: $('#frmMemberAdd').serialize(),
		        contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
		        success: function(data) {
		            if(data.result){
						$('#memberAddPop').removeClass();
						$('#memberComplete').addClass('menu-active');
		            } else {
		                alertModal.fail(data.message);                    
		            }                
		        },
		        error: function(data) {
		        	 alertModal.fail('[error] 추가정보 등록 중 오류가 발생했습니다.');
		        }
		    });
		
		}
	</script>
</body>
</html>