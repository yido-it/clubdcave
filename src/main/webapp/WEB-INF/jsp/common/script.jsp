<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.yido.clubd.common.utils.Globals" %>

<!-- 예약페이지 시간 선택시 label onclick 이 두번 실행되어서 원인 찾다가 아래 3개 넣었더니 정상 작동 함.. -->
<script type="text/javascript" src="/scripts/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript" src="/scripts/bootstrap.bundle.min.js"></script>
<!-- // -->

<script type="text/javascript" src="/scripts/jquery.js"></script>
<script type="text/javascript" src="/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="/scripts/main.js"></script>
<script type="text/javascript" src="/scripts/custom.js"></script>
<script type="text/javascript" src="/scripts/custom2.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=Globals.serviceJs%>" ></script>
<script type="text/javascript" src="/scripts/tools.js?now=<%=new Date()%>"></script>
<script type="text/javascript" src="/scripts/pay.js?now=<%=new Date()%>"></script>
<script type="text/javascript" src="https://unpkg.com/@swup/preload-plugin@2"></script>
