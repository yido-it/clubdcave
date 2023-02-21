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
      
	<img src="../images/gallery/intro.jpg" style="width:100%"/>
	                            
                            
        <div class="page-content pb-3">  
            <div class="mb-1" style="">
             
                <div class="divider mb-0"></div> 
                <div class="content">
                 
            <div class="content">
                <p class="mb-n1 color-highlight font-600">클럽디청담 소개</p>
	                <h1>CLUBD CHEONGDAM </h1>
	                <p>
	               클럽디는 밸류업플랫폼 기업인 <span class="color-highlight">주식회사 이도</span>가 운영하는 골프클럽 브랜드입니다.<br/><br/>
	               2018년 런칭 이후 차별화된 서비스를 통해 지속적으로 성장하고 있으며 현재 클럽디 보은, 
	               속리산, 금강, 거창에서 골프코스를 운영하고 있습니다.
	               <br/><br/>
	                2023년 4월, 강남구 도산대로에 새로운 실내 골프 문화공간 <span class="color-highlight"> '클럽디 청담'</span>을 오픈하여 그 영역을 확장하고자 합니다.
	                클럽디 청담은 타이거 우즈의 프리미엄 골프 시뮬레이터<span class="color-highlight"> FULL SWING, 골프 편집샵 TINO 5, </span>
	                전문 셰프의 섬세한 요리와 와인을  
	                함께 즐길 수 있는 <span class="color-highlight"> '클럽디 카브 골프 & 비스트로'</span>로 구성되어 도시에서 즐기는 새로운 골프 문화 공간을 선사합니다.
	                <br/><br/>
	                 또한, 최고의 프로들의 1:1 골프레슨, 주니어 그룹 골프 수업, 여러가지 주제로 진행되는 오픈 클래스, 
	                그리고 다양한 이벤트와 Skills Challenge도 참여할 수 있습니다.
	                </p>
	  
            </div>
        </div>
        
        
        
        <!-- //카드종료 -->
        
    <!--     
        <div class="content">
            <h3 class="font-700">이용안내/요금</h3>
          
            <table class="table table-borderless text-center" style="overflow: hidden;">
                <thead>
                    <tr class="bg-blue-dark">
                        <th scope="col" class="color-white">비고</th>
                        <th scope="col" class="color-white">요금</th>
                        <th scope="col" class="color-white">??</th>
                        <th scope="col" class="color-white">야간</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row" class="bg-highlight">Apple</th>
                        <td class="color-green-dark">$500</td>
                        <td><i class="fa fa-arrow-up rotate-45 color-green-dark"></i></td>
                        <td class="color-yellow-dark">25%</td>
                    </tr>
                    <tr>
                        <th scope="row" class="bg-highlight">Android</th>
                        <td class="color-yellow-dark">$400</td>
                        <td><i class="fa fa-arrow-right rotate-45 color-yellow-dark"></i></td>
                        <td class="color-green-dark">65%</td>
                    </tr>
                    <tr>
                        <th scope="row" class="bg-highlight">Nope</th>
                        <td class="color-red-dark">$300</td>
                        <td><i class="fa fa-arrow-right rotate-90 color-red-dark"></i></td>
                        <td class="color-red-dark">10%</td>
                    </tr>
                </tbody>
            </table>
        </div>
         -->
        
      
        <!-- //카드종료 -->
        

    </div>  
</div>   
<div class="mb-10"></div>

<!-- 하단바 -->
<jsp:include page="common/footerBar.jsp" />

</body>

</html>