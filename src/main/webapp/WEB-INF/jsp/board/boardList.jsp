<!-- 
기능 : 공지사항 목록 
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
        <a href="#" class="header-title header-subtitle">
        <c:if test="${bbsType == 1}">
       	 공지사항
        </c:if>
        <c:if test="${bbsType == 2}">
       	 이벤트
        </c:if>
        <c:if test="${bbsType == 3}">
       	 프로모션
        </c:if>
        </a>
		<jsp:include page="../common/top.jsp" />
    </div>
    
    <!-- 좌측GNB-->
	<jsp:include page="../common/menu.jsp" />
	<!-- //좌측GNB-->

    <div class="page-content header-clear-medium">
    
		<!-- ┌──────────────────────── 공지사항 목록 ────────────────────────┐-->
		<div id="noticeList"></div>
		<!-- └──────────────────────── 공지사항 목록 ────────────────────────┘-->

		 <a href="#" onClick="doSearch('more')" class="btn btn-border btn-m btn-full mt-4 mb-10 rounded-xl text-uppercase font-900 border-blue-dark color-blue-dark ml-4 mr-4" id="btnMore">
        	더보기 <i class="fa-solid fa-chevron-down"></i>
        </a>
        
	</div>  

	<!--  content ends -->   
</div>   
    

<!-- 하단바 -->
<jsp:include page="../common/footerBar.jsp" />
</body>

<script type="text/javascript">

var listSize		= 0;	
var coDiv 			= "<c:out value='${coDiv}'/>";
$(document).ready(function() {
	doSearch('search');
});

// 검색 
// type > 더보기 : more , 조회 : search
function doSearch(type) {
	
	if (type == "search") {
		listSize = 0;
	}
	
	$.ajax({
        url: "/searchList/" + coDiv
        , type: "post"
       	, dataType: 'json'
      	, data: { 
      		"listSize" : listSize
           	, "bbsType" : "<c:out value='${bbsType}'/>"
    	}
        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
        , success: function(data) {	
        	
        	console.log(data);
			if (data != null && data.length > 0) {
				var divCnt = '';
				for (let i=0; i<data.length; i++) {
					var lastIdx = Number(listSize);
					var regdate = String(data[i].regDate).substr(0, 10).replaceAll('-', '.');
					
					divCnt += '<div id=noticeList_'+(lastIdx+i)+' onclick="goView('+data[i].idx+')">';
					divCnt += '<div class="d-flex pb-3">';
					divCnt += '<div>';
					divCnt += '<p class="pl-3 line-height-s color-theme mb-1 pt-3 font-13">'+textLengthOverCut(data[i].title, 30)+'</p>';
					divCnt += '<p class="mb-0 pl-3 font-10 pt-1 opacity-60"><i class="fa fa-clock pr-1"></i>'+regdate+'</p>'; 
					divCnt += '</div>';
					divCnt += '</div>';
					divCnt += '<div class="divider mb-0"></div>';
					divCnt += '</div>';
				}
				
				if (listSize == 0) {
				 	document.querySelector("#noticeList").innerHTML = divCnt;
				} else {
					var tmp = listSize - 1;
					var sTmp = String(tmp);
					$("#noticeList_"+ sTmp).after(divCnt);
				}
				listSize += data.length;
				
				if (data.length < 5) {
					$('#btnMore').text("더이상 조회할 내역이 없습니다.");
					document.getElementById("btnMore").setAttribute("href", "#");
				}
			} else {
				if (type == "search") {
					// 조회 결과 없을때 
					$('#btnMore').css('display','none');
					$('#noticeList').css('text-align','center');
					document.querySelector("#noticeList").innerHTML = "검색 결과가 없습니다.";
				} else {
					// 더보기 결과 없을때
					$('#btnMore').text("더이상 조회할 내역이 없습니다.");
					document.getElementById("btnMore").setAttribute("href", "#");
				}
			}
		}
	});
}

function goView(idx) {
	location.href="/boardView/" + idx;
}
</script>
</html>