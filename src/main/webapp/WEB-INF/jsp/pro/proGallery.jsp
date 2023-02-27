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
		<a href="#" class="header-title header-subtitle">갤러리수정</a>
		<a href="javascript:location.href='/pro/proDetail?msNum=${sessionScope.msMember.msNum}'" data-back-button class="header-icon header-icon-1" style="margin-top: 8px !important;    font-size: 20px;">
			<i class="fa fa-arrow-left"></i></a>
		<a href="#" data-menu="menu-main" class="header-icon header-icon-2 font-20 mt-1"><i class="fas fa-bars"></i></a>
	</div>
	<jsp:include page="../common/menu.jsp" />
	
	<input type="hidden" id="msNum" name="msNum" value="${sessionScope.msMember.msNum}"/>
	<div class="page-content header-clear">
		<div class="divider divider-margins"></div>
	
       
        <!--사진수정-->
        <div class="card card-style">
            <div class="content mb-0">
                <h3>사진업로드</h3>
				<p>가로로 긴 사진 업로드를 추천합니다.</p>
				<div class="img-dropzone dropzone" id="">
                    <button type="button" id="profileUpload" class="btn bg-highlight color-white font-15 dz-message mb-5" style="width:100%;height:100%">
                    <i class="fa fa-camera"></i> 사진 찾기</button>
                    
                    <c:if test="${not empty galleryList}">
	                    <c:forEach items="${galleryList}" var="item" varStatus="status">
		                    <c:if test="${item.imgDiv == 1}">
		                    <div class="img-area px-0 col-6 mb-3 fl">
									<div class="dz-image my-2" style="text-align: center">
										<img alt="${item.imgFilename}" src="${item.thumbURL}" data-dz-thumbnail>
									</div>
									<div class="d-flex mt-1">
										<div class="ml-auto pl-3 text-right">
											<a class="dz-remove color-red-dark font-14 btn del-img" href="javascript:undefined;" 
												id="${item.imgSeq}" data-filepath="${item.imgPath}" data-filename="${item.imgFilename}" data-dz-remove>
											<i class="fa-regular fa-rectangle-xmark del-img"></i> 삭제
											</a>
										</div>								
									</div>
							</div>
							</c:if>
	                    </c:forEach>
                    </c:if>
                </div>
			</div>
		</div>	            
        <!--//사진수정-->

        <!--영상수정-->
        <div class="card card-style">
        	<div class="content mb-0">
            	<h3>영상업로드</h3>
            	<p>영상크기 5MB이하 권장</p>
				<div class="video-dropzone dropzone" id="">
                    <button type="button" id="profileUpload" class="btn bg-highlight color-white font-15 dz-message mb-5" style="width:100%;height:100%">
                    <i class="fa-solid fa-video"></i> 영상 찾기</button>
                    
                    <c:if test="${not empty galleryList}">
	                    <c:forEach items="${galleryList}" var="item" varStatus="status">
		                    <c:if test="${item.imgDiv == 2}">
		                    <div class="img-area px-0 col-6 mb-3 fl">
								<div class="">
									<div class="dz-image my-2" style="text-align: center">
										<img alt="${item.imgFilename}" src="${item.videoThumbURL}" onerror="this.src='/images/thumb_loading.jpg'" style="width: 143px" data-dz-thumbnail>
									</div>
									<div class="d-flex mt-1">
										<div class="ml-auto pl-3 text-right">
											<a class="dz-remove color-red-dark font-14 btn del-video" href="javascript:undefined;" 
												id="${item.imgSeq}" data-filepath="${item.imgPath}" data-filename="${item.imgFilename}" data-dz-remove>
											<i class="fa-regular fa-rectangle-xmark del-video"></i> 삭제
											</a>
										</div>								
									</div>
								</div>
							</div>
							</c:if>
	                    </c:forEach>
                    </c:if>
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

<script type="text/javascript">	

	Dropzone.autoDiscover = false;
	var imgDropzone = new Dropzone('div.img-dropzone', {
		  autoProcessQueue : true
		, url : '/pro/uploadGalleryImg'
		, method : 'post'
		, maxFiles : 1
		, maxFilesize : 10
		, resizeQueality : 0.9
		, resizeWidth : 960
		, dictFileTooBig : '{{maxFilesize}}MB 이하로 업로드 해주세요.'
		, paramName : 'file'
		, addRemoveLinks : true
		, acceptedFiles : "image/*"
		, uploadMultiple : true
		, previewTemplate : '<div class="dz-preview px-0 col-6 mb-3 dz-processing dz-image-preview" style="display:none;"></div>'
		, init : function() {
			// 파일 개수 초과
			this.on("maxfilesexceeded", function (file) {
				this.removeAllFiles();
				this.addFile(file);
			});
			// 에러 발생 (ex 파일 용량 초과)
	   		this.on("error", function(file, message) { 
	   			alertModal.fail(message);
                this.removeFile(file); 
    		});
			// 파일 dropzone area에 올라간 후 (총 업로드 개수 제한)
	   		this.on("addedfile", function (file) {
	   			if($(".img-area").length >= 10) {
	   				alertModal.fail('10개까지 등록 가능합니다.');
	   				this.removeFile(file); 
	   			}
	   		})
	   		// 파일 업로드 중
			this.on('sending', function(file, xhr, formData){
	   			formData.append('msNum', $("#msNum").val());
	   		});
			// 사진 업로드 완료 후
			this.on("success", function(file, res){
                this.removeAllFiles();
				alertModal.success('업로드 완료');
            	goAfterModal();
                   
            });
	   		/* this.on("successmultiple", function(file){
	   			this.removeAllFiles()
				alertModal.success('업로드 완료');
            	goAfterModal();
	   		}); */	   		
		}
	});
	
	var videoDropzone = new Dropzone('div.video-dropzone', {
		  autoProcessQueue : true
		, url : '/pro/uploadGalleryVideo'
		, method : 'post'
		, maxFiles : 1
		, maxFilesize : 10
		, dictFileTooBig : '{{maxFilesize}}MB 이하로 업로드 해주세요.'
		, paramName : 'file'
		, addRemoveLinks : true
		, acceptedFiles : "video/*"
		, uploadMultiple : false
		, previewTemplate : '<div class="dz-preview px-0 col-6 mb-3 dz-processing dz-image-preview" style="display:none;"></div>'
		, init : function() {
			// 파일 개수 초과
			this.on("maxfilesexceeded", function (file) {
				this.removeAllFiles();
				this.addFile(file);
			});
			// 에러 발생 (ex 파일 용량 초과)
	   		this.on("error", function(file, message) { 
	   			alertModal.fail(message);
                this.removeFile(file); 
    		});
	   		// 파일 dropzone area에 올라간 후 (총 업로드 개수 제한, 영상 썸네일)
	   		this.on("addedfile", function (file) {
	   			if($(".img-area").length >= 10) {
	   				alertModal.fail('10개까지 업로드 가능합니다.');
	   				this.removeFile(file); 
	   			}	   			
	   		})
	   		// 파일 업로드 중
			this.on('sending', function(file, xhr, formData){
	   			formData.append('msNum', $("#msNum").val());
	   		});	
			// 사진 업로드 완료 후
			this.on("success", function(file, res){
				this.removeAllFiles();
				alertModal.success('업로드 완료');
	          	goAfterModal();                 
             });
		}
	});
	
	$('.dz-remove').on('click', function() {	
		
		 params = {
			  imgSeq : $(this).attr('id')
			, imgFilename : $(this).data('filename')	
			, imgPath : $(this).data('filepath')
			, msNum : $('#msNum').val()
		};
		// console.log(params);
		if($(this).hasClass('del-video')) {
			alertModal.confirm2('영상을 삭제하시겠습니까?', 'deleteGalleryVideo(params);');			
		} else {			
			alertModal.confirm2('사진을 삭제하시겠습니까?', 'deleteGalleryImg(params);');
		}
	})
	
	function deleteGalleryVideo(params) {
		$.ajax({
	        url: "<c:out value='/pro/deleteGalleryVideo'/>"
	        , type: "post"
	        , dataType: 'json'
	        , data: params
	        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
	        , success: function(data) {
	            if(data.result){
	            	document.getElementById(params.imgSeq).closest('.img-area').remove();	          
	            	$("#confirm1Popup").removeClass('menu-active');
	            	params = null;
	            	
	            } else {
	                alertModal.fail(data.message);                    
	            }                
	        }
	        , error: function(data) {
	        	 alertModal.fail('[error] 코드 호출 중 오류 발생했습니다.');
	        }
	    });
	}
	
	function deleteGalleryImg(params) {
		$.ajax({
	        url: "<c:out value='/pro/deleteGalleryImg'/>"
	        , type: "post"
	        , dataType: 'json'
	        , data: params
	        , contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
	        , success: function(data) {
	            if(data.result){
	            	document.getElementById(params.imgSeq).closest('.img-area').remove();	          
	            	$("#confirm1Popup").removeClass('menu-active');
	            	params = null;
	            	
	            } else {
	                alertModal.fail(data.message);                    
	            }                
	        }
	        , error: function(data) {
	        	 alertModal.fail('[error] 코드 호출 중 오류 발생했습니다.');
	        }
	    });
	}
	
</script>
</body>
</html>