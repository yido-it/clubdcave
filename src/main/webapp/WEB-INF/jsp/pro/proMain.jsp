<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />
<body class="theme-dark">
    
<div id="preloader"><div class="spinner-border color-highlight" role="status"></div></div>

<div id="page">
    
	<div class="header header-fixed header-logo-app">
		<a href="#" class="header-title header-subtitle">프로선택</a>
		<jsp:include page="../common/top.jsp" />
	</div>
    <jsp:include page="../common/menu.jsp" />

    <div class="content header-clear">
        
       	<!-- 앰배서더 -->
		<div class="content ">
            <h2 class="font-700">AMBASSADOR</h2>      
            <c:choose>
            <c:when test="${not empty proList}">
	            <c:forEach items="${proList}" var="item" varStatus="status">
	            	<c:if test="${item.msLevel == '02'}">
		            <div class="cover-slider owl-carousel owl-no-dots">
		                <div class="item" onclick="javascript:location.href='proDetail?msNum=${item.msNum}'">
		                    <div class="card card-style mx-0 bg-9" data-card-height="180" style="background: url(/images/gallery/choipro.jpg) no-repeat 0 0; background-size: cover;">
		                        <div class="card-bottom m-3">
		                            <p class="color-white mb-0 opacity-90">앰배서더</p>
		                            <h2 class="color-white">${item.msName} 프로</h2>
		                        </div>
		                        <div class="card-overlay bg-gradient"></div>
		                    </div>
		                </div>
		            </div>
		            </c:if>
		        </c:forEach>
		        <div class="cover-slider owl-carousel owl-no-dots">
	                <div class="item" onclick="location.href='event_detail.html'">
	                    <div class="card card-style mx-0 bg-9" data-card-height="180" style="background: url(/images/gallery/choipro.jpg) no-repeat 0 0; background-size: cover;">
	                        <div class="card-bottom m-3">
	                            <p class="color-white mb-0 opacity-90">앰배서더</p>
	                            <h2 class="color-white">최나연 프로</h2>
	                        </div>
	                        <div class="card-overlay bg-gradient"></div>
	                    </div>
	                </div>
		        </div>  
	        </c:when>
	        <c:otherwise>
	        	등록된 프로 정보가 없습니다.	        	
			</c:otherwise>
			</c:choose>   
            
    	</div>
		
		<!-- 프로 골퍼 -->
        <div class="content mt-2">
              
            <h2 class="font-700">PRO GOLFER</h2>
            <div class="row mb-0 mt-2">
            	<c:choose>
	            	<c:when test="${not empty proList}">
	            		<c:forEach items="${proList}" var="item" varStatus="status">
	            		<c:if test="${item.msLevel != '02'}">
		                <div class="col-6" onclick="javascript:location.href='proDetail?msNum=${item.msNum}'">
	                		<c:choose>
		                	<c:when test="${item.msImgName != null && item.msImgName != ''}">
		                    <div class="card card-style m-0 mb-2 rounded-m" data-card-height="150" style="background: url(${item.fileURL}) no-repeat 0 0">
		                    </c:when>
		                    <c:otherwise>
		                    <div class="card card-style m-0 mb-2 rounded-m bg-empty_pro" data-card-height="150" style="">
		                    </c:otherwise>
	                    	</c:choose>
		                        <c:if test="${item.licName != null && item.licName != ''}">
		                        <div class="card-bottom"><span class="badge bg-highlight p-1 pl-2 rounded-0">${item.licName}</span></div>
		                        </c:if>
			                </div>
		                    <p class="line-height-s color-theme mb-1">${item.msName} 프로 </p>
		                    <p class="mb-0 font-10 pt-0 opacity-60"><i class="fa fa-clock pr-1"></i>레슨: 화/목</p> 
		                </div>
		                </c:if>
		                </c:forEach>         
	            	</c:when>
	            	<c:otherwise>
	            		등록된 프로 정보가 없습니다.
	            	</c:otherwise>
            	</c:choose>
            </div>
        </div>            
        
    </div>
    <!-- Page content ends here-->
    
</div>	

<div class="menu-hider"><div></div></div>

<script type="text/javascript">		

	//getProList();
	
	function getProList(){		
    	$.ajax({
              url : "/pro/getProList"
            , type : "post"
            , dataType : 'json'
    		, contentType : 'application/x-www-form-urlencoded; charset=UTF-8'
    		, success : function(data) {
            	if(data.result) {
            		drawProList(data.proList);
            	} else {
            		alert(data.message);
            	}
            }
    		, error : function(data) {	    			
            		alert("프로 조회에 실패했습니다.");
            }
        })
	}
	
	function drawProList(proList) {
		var html = "";
		var count = 0;
		var arrNum = 3;
		
		// 셀 3개씩 정렬
		for(var i = 0; i < proList.length; i++) {
			if(count == 0) {
				html += "<tr>";
			}
			html += "<td><a href='/pro/proDetail?msNum=" + proList[i].msNum + "'>" + proList[i].msName + "</a></td>";
			if(count == arrNum-1) {
				html += "</tr>";
				count = 0;
			}				
			count++;
		}
		$('#proTBody').append(html);
		} 
</script>
</body>
</html>