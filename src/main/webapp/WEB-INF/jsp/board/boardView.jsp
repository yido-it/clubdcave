<!-- 
기능 : 공지사항 상세
작성자 :bae
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<fmt:parseDate value="${bbs.regDate}" pattern="yyyy-MM-dd" var="dRegdate" type="both"/>
<fmt:formatDate var="regdate" pattern="yyyy.MM.dd" value="${dRegdate}"/>

<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />

<body class="theme-dark">
    
<div id="preloader"><div class="spinner-border color-highlight" role="status"></div></div>
    
<div id="page">
    
    <div class="header header-fixed header-logo-app">
        <a href="#" class="header-title header-subtitle">공지사항</a>
		<jsp:include page="../common/top.jsp" />
    </div>
    
    <!-- 좌측GNB-->
	<jsp:include page="../common/menu.jsp" />
	<!-- //좌측GNB-->

    <div class="page-content header-clear-medium">

		<div class="mt-3 mb-0">
			<div class="d-flex mb-3">
				<div>
					<!-- 제목 -->
					<p class="pl-3 line-height-s color-theme mb-1 font-15">${bbs.title}</p>
					<!-- 작성일 -->
					<p class="mb-0 pl-3 font-12 pt-1 opacity-60"><i class="fa fa-clock pr-1"></i>${regdate}</p> 
				</div>
			</div>
			<div class="divider mb-3"></div>
		</div>

		<div class="content">
			<!-- 이미지 -->
			<div>
				<img src="${bbs.fileURL}" style="width:100%">
			</div>
		
			<!-- 내용 -->
			<p class="mt-2">${bbs.content}</p>
		</div>
		
		<div class="divider mb-3"></div>
		<div class="row">
			<button onClick="location.href='/boardList/001/${bbs.bbsType}'" type="button" class="col-6 mt-2 mb-10 btn btn-md bg-blue-dark shadow-xl text-uppercase font-800 rounded-s" style="margin:0 auto">            	
				목록
			</button>
		</div>
	</div>  
</div>   

<!-- 하단바 -->
<jsp:include page="../common/footerBar.jsp" />
</body>

</html>