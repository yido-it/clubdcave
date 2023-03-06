<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />

<body class="">
    
<div id="preloader"><div class="spinner-border color-highlight" role="status"></div></div>
    
<div id="page">
    
    <div class="header header-fixed header-logo-app header-transparent">
        <a href="javascript:history.back()" class="header-title header-subtitle">아이디/비밀번호찾기</a>
        <jsp:include page="../common/top.jsp" />
    </div>
    <jsp:include page="../common/menu.jsp" />
 
    <div class="page-content mb-0">
        
       <div data-card-height="cover-card" class="card bg-1   mb-0" style="">

                <div class="card-center">

                    <div class="page-content header-clear"> 
                        <div class="divider divider-margins"></div>
                
                        <div class="tab-wrapper">
                            <div class="content">
                                <div class="tab-controls tabs-round tab-animated tabs-medium tabs-rounded shadow-xl" data-tab-items="2" data-tab-active="bg-green-dark color-white">
                                    <a href="#" data-tab-active="" data-tab="tab-id" class="bg-green-dark color-white no-click" style="width: 50%;">
                                        아이디 찾기</a>
                                    <a href="#" data-tab="tab-pw" style="width: 50%;" class="">
                                        비밀번호 찾기</a>
                                </div>
                                <div class="clearfix mb-3"></div>
 
                                <div class="tab-content" id="tab-id" style="display: block;">
                                   <h2 class="text-center mt-4">아이디찾기</h2>
                                    
                                    <div class="row mb-0 mt-4"> 
                                        <div class="col-12 mb-3">
                                            <div class="input-style input-style-2 input-required">
                                                <span class="color-highlight input-style-1-active">이름</span>
                                                <input class="form-control" type="name" placeholder="" id="findIdName">
                                            </div> 
                                        </div>
                                        <div class="col-12 mb-3">
                                            <div class="input-style input-style-2 input-required">
                                                <span class="color-highlight input-style-1-active">핸드폰번호</span>
                                              
                                                <input class="form-control" type="name" placeholder="" id="findIdPhone">
                                            </div> 
                                        </div> 
                                    </div>
                                    <a href="javascript:doFindId()" class="mb-4 btn btn-md bg-blue-dark btn-full shadow-xl text-uppercase 
                                    font-800 rounded-s">조회하기</a> 
                                </div>
  
                                <div class="tab-content" id="tab-pw" style="display: none;">
                                    <h2 class="text-center mt-4">비밀번호찾기</h2>
                                    <div class="row mb-0 mt-4"> 
                                        <div class="col-12 mb-3">
                                            <div class="input-style input-style-2 input-required">
                                                <span class="color-highlight input-style-1-active">아이디</span>
                                                <input class="form-control" type="name" placeholder="" id="findPwId">
                                            </div> 
                                        </div>                                        
                                        <div class="col-12 mb-3">
                                            <div class="input-style input-style-2 input-required">
                                                <span class="color-highlight input-style-1-active">핸드폰번호</span>
                                               
                                                <input class="form-control" type="name" placeholder="" id="findPwPhone">
                                            </div> 
                                        </div>
                                  
                                    </div>
                                    <a href="javascript:doResetPw()" class="mb-4 btn btn-md bg-blue-dark btn-full shadow-xl text-uppercase 
                                    font-800 rounded-s">비밀번호 초기화</a>

                                </div>
                            </div>
                        </div>
                        
                        <div class="divider divider-margins"></div> 
                    </div>
 
                 </div>
            <div class="card-overlay bg-black opacity-80"></div>
        </div>
        
    </div>  
    <!-- Page content ends here--> 
</div>

<!--아이디찾기-->
<div id="findIdPop" class="menu menu-box-modal rounded-0" data-menu-height="310" data-menu-width="330"data-menu-effect="menu-parallax">
	<h1 class="text-center mt-4"><i class="fa fa-3x fa-check-circle scale-box color-green-dark shadow-xl rounded-circle"></i></h1>
	<h3 class="text-center mt-3 font-700">아이디 찾기 완료</h3>
	<p class="boxed-text-xl opacity-70">
	     고객님의 아이디는<br/>
	    <span id="msId" style="font-size:18px"></span> 입니다.
	</p>
	<div class="row mb-0 mr-3 ml-3">
	 
	    <div class="col-12">
	        <a href="../login" class="btn btn-full btn-md bg-blue-dark font-800 text-uppercase rounded-s">로그인 페이지 이동</a>
	    </div>
	</div>
</div> 

<!--비번초기화-->
<div id="findPwPop" class="menu menu-box-modal rounded-0" data-menu-height="310" data-menu-width="330" data-menu-effect="menu-parallax">
    <h1 class="text-center mt-4"><i class="fa fa-3x fa-check-circle scale-box color-green-dark shadow-xl rounded-circle"></i></h1>
    <h3 class="text-center mt-3 font-700">비밀번호 초기화 완료</h3>
    <p class="boxed-text-xl opacity-70">
         고객님의 임시비밀번호가 전달되었습니다.<br/>
         등록된 휴대폰에서 임시비밀번호를 확인하세요.
    </p>
    <div class="row mb-0 mr-3 ml-3">
     
        <div class="col-12">
            <a href="../login" class="btn btn-full btn-md bg-blue-dark font-800 text-uppercase rounded-s">로그인 페이지 이동</a>
        </div>
    </div>
</div>  

<div class="menu-hider"><div></div></div>
<script type="text/javascript">

	$(document).ready(function() {  
		init();
	});
	
	function init() {
		$("#findIdName").keypress(function (event) {
	        if(event.keyCode == 13) {
	        	doFindId();
	        }
	    });

		$("#findIdPhone").keypress(function (event) {
	        if(event.keyCode == 13) {
	        	doFindId();
	        }
	    });

		$("#findPwName").keypress(function (event) {
	        if(event.keyCode == 13) {
	        	doResetPw();
	        }
	    });

		$("#findPwId").keypress(function (event) {
	        if(event.keyCode == 13) {
	        	doResetPw();
	        }
	    });
	}

	function doFindId() {
		
		var sUrl = "<c:url value='/member/doFindId'/>";
		var sParams = "";
		
		var msName = $("#findIdName").val();
		var msPhone = $("#findIdPhone").val();
		
		if(msName == "") {
			alertModal.fail("이름을 입력하세요.");
			return;
		}
		
		if(msPhone == "" || msPhone.length < 11) {
			alertModal.fail("핸드폰번호를 입력하세요.");
			return;			
		}
		
		sParams += String.format("&msName={0}", msName);
		sParams += String.format("&msPhone={0}", msPhone);
		
		$.ajax({
	          url: sUrl
	        , type: "post"
	        , dataType: 'json'
	        , data: sParams
	        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
	        , success: function(data) {
	        	if(data.result){
	        		$("#findIdName").val('');
					$("#findIdPhone").val('');
					
					$("#msId").html(data.msId);
					$("#findIdPop").removeClass('menu-active').addClass('menu-active');
	        	}else{
	        		 alertModal.fail(data.message);
	        	}
	        }
	        , error: function(data) {
	        	alertModal.fail('[error] 아이디 찾기 중 오류가 발생했습니다.');
	        }
	    });
	}

	function doResetPw() {
		var sUrl = "<c:url value='/member/doResetPw'/>";
		var sParams = "";
		
		var msId = $("#findPwId").val();
		var msPhone = $("#findPwPhone").val();
		
		if(msId == "") {
			alertModal.fail("아이디를 입력하세요.");
			return;			
		}
		if(msPhone == "") {
			alertModal.fail("휴대폰번호를 입력하세요.");
			return;
		}	
		
		sParams += String.format("&msId={0}", msId);
		sParams += String.format("&msPhone={0}", msPhone);
				
		$.ajax({
	          url: sUrl
	        , type: "post"
	        , dataType: 'json'
	        , data: sParams
	        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
	        , success: function(data) {
	        	if(data.result){
	        		$("#findPwId").val('');
					$("#findPwPhone").val('');
					
					$("#findPwPop").removeClass('menu-active').addClass('menu-active');
	        	}else{
	        		 alertModal.fail(data.message);
	        	}
	        }
	        , error: function(data) {
	        	alertModal.fail('[error] 비밀번호 초기화 중 오류가 발생했습니다.');
	        }
	    });
	}
	
	$('#findIdPhone, #findPwPhone').on('keyup', function() {
		var msPhone = $(this).val();
		$(this).val(msPhone.replace(/[^0-9]/g, '').replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`));
	})
	
	
</script>
</body>
</html>