<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.yido.clubd.common.utils.Globals" %>
<html>

<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />
<head>
</head>
<body>
	<div class="mainTitle">
		<img src="" alt="">프로 리스트<img src="<c:url value=''/>" alt="">
	</div>
	<div>
		<table id="proTable">
			<tbody id="proTBody">
			</tbody>	
		</table>
	</div>
	<script type="text/javascript">
		
		getProList();
		
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
			
			// 셀 3개씩 정렬
			for(var i = 0; i < proList.length; i++) {
				if(count == 0) {
					html += "<tr>";
				}
				html += "<td><a href='/pro/proDetail?msNum=" + proList[i].msNum + "'>" + proList[i].msName + "</a></td>";
				if(count == 2) {
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