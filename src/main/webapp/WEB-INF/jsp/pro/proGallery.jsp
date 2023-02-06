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
		<a href="javascript:history.back()" class="header-title header-subtitle">갤러리수정</a>
		<jsp:include page="../common/top.jsp" />
	</div>
	<jsp:include page="../common/menu.jsp" />
	
	<div class="page-content header-clear">
		<div class="divider divider-margins"></div>
		<div class="menu-title">
			<h1 class="my-0 py-0">갤러리수정</h1>
		</div>
        <div class="divider divider-margins"></div>

        <!--사진수정-->
        <div class="card card-style">
            <div class="content mb-0">
                <h3>사진업로드</h3>
                <p>가로로 긴 사진 업로드를 추천합니다.(사진크기 3MB이하)</p>
                   <div class="file-data pb-5">
                       <input type="file" id="file-upload" class="upload-file bg-highlight shadow-s rounded-s" accept="image/*">
                       <p class="upload-file-text color-white font-15"><i class="fa fa-camera"></i> 사진 찾기</p>
                   </div> 	
                   <div class="row mb-0 mt-2"> 
                       <!--사진-->
                       <div class="col-6 mb-3">
                           <img id="image-data float-left" src="images/pictures/pro4.jpg" class="img-fluid" > 
                           <div class="d-flex mt-1">
                               <div>
                                   <a href="#" class="border-0" >
                                       <i class="fa-regular fa-images color-brown-dark"></i>
                                       <span class="color-white">1.4MB</span>
                                   </a>      
                               </div>
                               <div class="ml-auto pl-3 text-right">
                                   <a class="color-red-dark font-14 ">
                                       <i class="fa-regular fa-rectangle-xmark" ></i> 삭제
                                   </a>    
                               </div>
                           </div> 
                       </div>
                       <div class="col-6 mb-3">
                       	<img id="image-data" src="images/pictures/pro3.jpg" class="img-fluid image-fit" > 
                       	<div class="d-flex mt-1">
                           	<div>
                                <a href="#" class="border-0" >
                                    <i class="fa-regular fa-images color-brown-dark"></i>
                                    <span class="color-white">1.4MB</span>
                                </a>      
                            </div>
                           	<div class="ml-auto pl-3 text-right">
                                <a class="color-red-dark font-14 ">
                                    <i class="fa-regular fa-rectangle-xmark" ></i> 삭제
                                </a>    
                           	</div>
                       	</div> 
                   	</div>
                     	<div class="col-6 mb-3">
                       	<img id="image-data float-left" src="images/pictures/pro4.jpg" class="img-fluid" > 
                       	<div class="d-flex mt-1">
                            <div>
                                <a href="#" class="border-0" >
                                    <i class="fa-regular fa-images color-brown-dark"></i>
                                    <span class="color-white">1.4MB</span>
                                </a>      
                            </div>
                            <div class="ml-auto pl-3 text-right">
                                <a class="color-red-dark font-14 ">
                                    <i class="fa-regular fa-rectangle-xmark" ></i> 삭제
                                </a>    
                            </div>
                       	</div> 
                   	</div>
               	</div>
			</div>
		</div>	            
        <!--//사진수정-->

        <!--영상수정-->
        <div class="card card-style">
        	<div class="content mb-0">
            	<h3>영상업로드</h3>
            	<p>영상크기 5MB이하 권장</p>
				<div class="file-data pb-5">
				    <input type="file" id="file-upload" class="upload-file bg-highlight shadow-s rounded-s " accept="image/*">
				    <p class="upload-file-text color-white font-15"><i class="fa-solid fa-video"></i> 영상 찾기</p>
				</div>

	            <div class="row mb-0 mt-2"> 
	                <!--영상-->
	                <div class="col-6 mb-3">
	                    <img id="image-data float-left" src="images/pictures/pro4.jpg" class="img-fluid" > 
	                    <div class="d-flex mt-1">
	                        <div>
	                            <a href="#" class="border-0" >
	                                <i class="fa fa-video color-brown-dark"></i>
	                                <span class="color-white">1.4MB</span>
	                            </a>      
	                        </div>
	                        <div class="ml-auto pl-3 text-right">
	                            <a class="color-red-dark font-14 ">
	                                <i class="fa-regular fa-rectangle-xmark" ></i> 삭제
	                            </a>    
	                        </div>
	                    </div> 
	                </div>
	                <div class="col-6 mb-3">
		                 <img id="image-data" src="images/pictures/pro3.jpg" class="img-fluid image-fit" > 
		                 <div class="d-flex mt-1">
		                     <div>
		                         <a href="#" class="border-0" >
		                             <i class="fa fa-video color-brown-dark"></i>
		                             <span class="color-white">2.4MB</span>
		                         </a>      
		                     </div>
		                     <div class="ml-auto pl-3 text-right">
		                         <a class="color-red-dark font-14 ">
		                             <i class="fa-regular fa-rectangle-xmark" ></i> 삭제
		                         </a>    
		                     </div>
		                </div> 
	            	</div>
	            	<div class="col-6 mb-3">
		                <img id="image-data float-left" src="images/pictures/pro4.jpg" class="img-fluid" > 
		                <div class="d-flex mt-1">
		                    <div>
		                        <a href="#" class="border-0" >
		                            <i class="fa fa-video color-brown-dark"></i>
		                            <span class="color-white">3.4MB</span>
		                        </a>      
		                    </div>
		                    <div class="ml-auto pl-3 text-right">
		                        <a class="color-red-dark font-14 ">
		                            <i class="fa-regular fa-rectangle-xmark" ></i> 삭제
		                        </a>    
		                    </div>
		                </div> 
	            	</div>
					<!--//영상-->
				</div>
			</div>
		</div>
		<!--//영상수정-->
	</div>
	<!-- Page content ends here--> 
</div>


<div id="timed-3" class="menu menu-box-modal rounded-m" data-menu-hide="1000" data-menu-width="220" data-menu-height="160" style="display: block; height: 160px; width: 220px;">
    <h1 class="text-center fa-5x mt-2 pt-3 pb-2"><i class="fa fa-check-circle color-green-dark"></i></h1>
    <h4 class="text-center">업로드 완료</h4>
</div>

<div id="timed-4" class="menu menu-box-modal rounded-m" data-menu-hide="1500" data-menu-width="240" data-menu-height="190" style="display: block; height: 160px; width: 220px;">
    <h1 class="text-center fa-5x mt-2 pt-3 pb-2"><i class="fa fa-times-circle color-red-dark"></i></h1>
    <h4 class="text-center">용량초과<br/> 5MB 이하의 영상 업로드하세요</h4>
    <br/>
</div>
<div class="menu-hider"><div></div></div>

<jsp:include page="../common/alertModal.jsp" />  
<script type="text/javascript">	
	$('#btnEdit').on('click', function() {
		
	})
	
	function getProList(){			
	   	$.ajax({
	             url : "/pro/getProList"
	           , type : "post"
	           , dataType : 'json'
	   		, contentType : 'application/x-www-form-urlencoded; charset=UTF-8'
	   		, success : function(data) {
	           	if(data.rows.length > 0) {
	           		drawProList(data.rows);
	           	}
	           }
	   		, error : function(data) {
	   			
	           }
	       })
	}
</script>
</body>
</html>