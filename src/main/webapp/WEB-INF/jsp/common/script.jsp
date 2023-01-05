<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<script src="/js/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script src="/js/bootstrap.bundle.min.js"></script><!--부트스트랩-->
<script src="/js/jquery.easing.min.js"></script><!--움직임-->
<!--<script src="/js/validate.js"></script><!--폼알럿-->
<script src="/js/owl.carousel.js"></script><!--슬라이드-->
<script src="/js/scrollreveal.min.js"></script><!--메인페이지-->
<script src="/js/aos.js"></script><!--스크롤-->
<script src="/js/main.js"></script><!--메인-->


<!-- date range picker -->
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script src="https://npmcdn.com/flatpickr/dist/flatpickr.min.js"></script>
<script src="https://npmcdn.com/flatpickr/dist/l10n/ko.js"></script>
<!-- // date range picker -->
<script> 
	AOS.init(); // 자바스크립트로 init()을 해야 동작한다.
</script>

<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=Globals.serviceJs%>" ></script>
<script type="text/javascript" src="/js/tools.js?now=<%=new Date()%>"></script>
<script type="text/javascript" src="/js/pay.js?now=<%=new Date()%>"></script>