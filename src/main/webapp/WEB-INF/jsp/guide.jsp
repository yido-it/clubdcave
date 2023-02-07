<!-- 이용안내 페이지 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<jsp:include page="common/head.jsp" />
<jsp:include page="common/script.jsp" />


<body class="theme-dark">
    
<div id="preloader"><div class="spinner-border color-highlight" role="status"></div></div>
    
<div id="page">

<div class="header header-fixed header-logo-app">
<a href="#" class="header-title header-subtitle">이용안내</a>
<jsp:include page="common/top.jsp" />
</div>

<!-- 좌측GNB-->
<jsp:include page="common/menu.jsp" />
<!-- //좌측GNB-->

  
    <div class="page-content header-clear">
    

    </div>  
</div>   
<div class="mb-10"></div>

<!-- 하단바 -->
<jsp:include page="common/footerBar.jsp" />

</body>

</html>