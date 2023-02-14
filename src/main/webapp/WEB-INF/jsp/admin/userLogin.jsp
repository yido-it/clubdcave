<!-- 
기능 : 사용자 로그인 
작성자 :bae
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />

<body class="theme-dark">
    
<div id="preloader"><div class="spinner-border color-highlight" role="status"></div></div>
    
<div id="page">
    
    <div class="header header-fixed header-logo-app">
        <a href="#" class="header-title header-subtitle">사용자 로그인</a>
		<jsp:include page="../common/top.jsp" />
    </div>
    
    <!-- 좌측GNB-->
	<jsp:include page="../common/menu.jsp" />
	<!-- //좌측GNB-->

    <div class="page-content header-clear-medium">    	
    	<div class="content row">
 
			<div class="input-style input-light input-style-1 has-icon input-required col-6">
				<select id="searchType" name="searchType">
					<option value="name" selected>이름</option>
					<option value="id">아이디</option> 
				</select>
			</div> 

			<div class="input-style input-light input-style-1 has-icon input-required col-6">
				<em style="top:4px">
					<button type="button" class="btn btn-xs bg-green-dark border-green-dark btn-primary" onClick="fnSearch()">
					검색
					</button>
				</em>
				
				<input class="form-control" type="text" id="searchTxt" name="searchTxt">
			</div>		
			
			<div class="mt-3 mb-0 row divUserList" style="width:100%;text-align:center"></div>
		</div>
		
	</div>  
	<!--  content ends -->   
</div>   

<!-- 모달-->
<jsp:include page="../common/pop/modal.jsp" />

<!-- 하단바 -->
<jsp:include page="../common/footerBar.jsp" />

</body>

<jsp:include page="../common/alertModal.jsp" />  

<script type="text/javascript">

var msId = "<c:out value='${sessionScope.msMember.msId}'/>";

$(document).ready(function() {
	if (msId == null || msId == "") {
		location.href = "/login";	
		return;
	}
	
	if (msId != "admin") {
		alertModal.fail('접근권한이 없습니다.');
		location.href = "/main";	
		return;
	}
});

function fnSearch() {
	$.ajax({
        url: "/member/searchMemberList"
        , type: "post"
       	, dataType: 'json'
      	, data: { 
           	"searchType" 	: $('#searchType').val()
           	, "searchTxt" 	: $('#searchTxt').val()
    	}
        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
        , success: function(data) {	
        
			if (data != null && data.length > 0) {
			
				var divCnt = '';
				divCnt += '<div class="col-4"><p class="pl-3 line-height-s color-theme mb-1 pt-3 font-13">아이디</p></div>';
				divCnt += '<div class="col-4"><p class="pl-3 line-height-s color-theme mb-1 pt-3 font-13">비밀번호</p></div>';
				divCnt += '<div class="col-4"></div>';
				divCnt += '<div class="col-12 divider mb-0"></div>';
				
				for (let i=0; i<data.length; i++) {
				
					divCnt += '<div class="col-4"><p class="pl-3 line-height-s color-theme mb-1 pt-3 font-13">'+data[i].msId+'</p></div>';
					divCnt += '<div class="col-4"><p class="pl-3 line-height-s color-theme mb-1 pt-3 font-13">'+data[i].msName+'</p></div>';
					divCnt += '<div class="col-4">';
					divCnt += '<button type="button" class="btn btn-xs bg-orange-dark border-orange-dark btn-primary" onClick="doLogin('+data[i].msNum+')">';
					divCnt += '로그인';
					divCnt += '</button></div>';

					divCnt += '<div class="col-12 divider mb-0"></div>';
					
				}
				document.querySelector(".divUserList").innerHTML = divCnt;
			} else {
				
			}
        }
	});
}

function doLogin(msNum) {
	
	$.ajax({
        url: "/admin/userLogin/"+msNum
        , type: "post"
       	, dataType: 'json'
        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
        , success: function(data) {	
        	
        	if (data.code == '0000') {
        		location.href='/main'
        	}
			
        }
	});
}
</script>
</html>