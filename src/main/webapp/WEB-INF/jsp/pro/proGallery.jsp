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
	
	<input type="hidden" id="msNum" name="msNum" value="${sessionScope.msMember.msNum}"/>
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
				<div class="img-dropzone dropzone" id="">
                    <button type="button" id="profileUpload" class="btn bg-highlight color-white font-15 dz-message mb-5" style="width:100%;height:100%">
                    <i class="fa fa-camera"></i> 사진 찾기</button>
                    
                    <c:if test="${proProfile != null}">
                    <div class="img-area">
						<div class="col-6 px-0 dz-image-preview float-left">
							<div class="dz-image my-2" style="text-align: center">
								<img class="image-data float-left" data-dz-thumbnail="" alt="${proProfile.msImgName}" src="${proProfile.thumbURL}">
							</div>
							<div style="display:flex;">
								<div class="">
									<div class="">
										<i class="fa-regular fa-images color-brown-dark"></i>
										<span class="color-white data-dz-size">0.1MB</span>
									</div>
								</div>
								<a class="dz-remove color-red-dark font-14 btn-img-delete" data-filepath="${proProfile.msImgData}" data-filename="${proProfile.msImgName}" data-dz-remove=""><i class="fa-regular fa-rectangle-xmark"></i> 삭제</a>
							</div>
						</div>
					</div>
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
                    
                    <c:if test="${proProfile != null}">
                    <div class="img-area">
						<div class="col-6 px-0 dz-image-preview">
							<div class="dz-image my-2" style="text-align: center">
								<img data-dz-thumbnail="" alt="${proProfile.msImgName}" src="${proProfile.thumbURL}">
							</div>
							<div style="display:flex;">
								<div class="">
									<div class="">
										<i class="fa-regular fa-images color-brown-dark"></i>
										<span class="color-white data-dz-size">0.1MB</span>
									</div>
								</div>
								<a class="dz-remove color-red-dark font-14 btn-img-delete" data-filepath="${proProfile.msImgData}" data-filename="${proProfile.msImgName}" data-dz-remove=""><i class="fa-regular fa-rectangle-xmark"></i> 삭제</a>
							</div>
						</div>
					</div>
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

<jsp:include page="../common/alertModal.jsp" />  
<script type="text/javascript">	

	Dropzone.autoDiscover = false;
	var imgDropzone = new Dropzone('div.img-dropzone', {
		  autoProcessQueue : true
		, url : '/pro/uploadGalleryImg'
		, method : 'post'
		, maxFiles : 10
		, maxFilesize : 3
		, resizeQueality : 1
		, resizeWidth : 960
		, dictFileTooBig : '{{maxFilesize}}MB 이하로 업로드 해주세요.'
		, paramName : 'file'
		, addRemoveLinks : true
		, acceptedFiles : "image/*"
		, uploadMultiple : true
		, init : function() {
			// 파일 개수 초과
			this.on("maxfilesexceeded", function (file) {
				alertModal.fail('10개까지 업로드 가능합니다.');
				this.removeFile(file);
			});
			// 에러 발생 (ex 파일 용량 초과)
	   		this.on("error", function(file, message) { 
	   			alertModal.fail(message);
                this.removeFile(file); 
    		});
			// 파일 dropzone area에 올라간 후 (총 업로드 개수 제한)
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
		, maxFiles : 10
		, maxFilesize : 5
		, resizeQueality : 1
		, resizeWidth : 960
		, dictFileTooBig : '{{maxFilesize}}MB 이하로 업로드 해주세요.'
		, paramName : 'file'
		, addRemoveLinks : true
		, acceptedFiles : "video/*"
		, uploadMultiple : false
		, init : function() {
			// 파일 개수 초과
			this.on("maxfilesexceeded", function (file) {
				alertModal.fail('10개까지 업로드 가능합니다.');
				this.removeFile(file);
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