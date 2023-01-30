<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../common/head.jsp" />
<jsp:include page="../common/script.jsp" />
<!--주소 검색 팝업-->
    <table class="content col-11">
		<c:forEach items="${addrList}" var="item" varStatus="status">
		<tr class="col-12">
			<td>
				${item.d_address}
			</td>
			<td rowspan="2">
				<button type="button" class="btn btn-md bg-blue-dark btn-full shadow-xl text-uppercase font-800 rounded-s btn-select" value="${item.num}">
					선택
				</button>
			</td>
		</tr>
		<tr class="col-12">
			<td>
				${item.j_address}
			</td>		
		</tr>
    	</c:forEach>        
    </table>  
    <div class="content mb-0 ">
	    <button type="button" class="col-12 mb-4 btn btn-md  bg-blue-dark btn-full shadow-xl text-uppercase font-800 rounded-s" id="btnClose">
	    	닫기
	    </button>   
    </div>  
    
<!--// 주소 검색 팝업-->		

<script type="text/javascript">

$('#btnClose').on('click', function() {
	$('#memberAddrPop').empty();
	$('#memberAddrPop').removeClass('menu-active');
})

$('.btn-select').on('click', function() {
	alert($(this).val());
})

function saveMemberAdd() {
    $.ajax({
        url: '/saveMemberAdd',
        type: "post",
        dataType: 'json',
        data: $('#frmMemberAdd').serialize(),
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
        success: function(data) {
            if(data.result){
				$('#memberAddPop').removeClass();
				$('#memberComplete').addClass('menu-active');
            } else {
                alertModal.fail(data.message);                    
            }                
        },
        error: function(data) {
        	 alertModal.fail('[error] 추가정보 등록 오류가 발생했습니다.');
        }
    });

}
</script>
</html>