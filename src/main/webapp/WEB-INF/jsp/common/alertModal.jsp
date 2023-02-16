<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 
<script type="text/javascript" src="/scripts/jquery.js"></script>
<script type="text/javascript" src="/scripts/bootstrap.min.js"></script>
 -->
 
<div id="alertPopup" class="menu menu-box-modal rounded-m" data-menu-hide="2000" data-menu-width="220" data-menu-height="160" style="">
	<h2 class="text-center fa-4x mt-2 pt-3 pb-2"><i class=""></i></h2>
	<h4 class="text-center font-15 fontnormal"></h4>
</div>
<div id="confirm1Popup" class="menu menu-box-bottom bg-blue-dark rounded-0" data-menu-height="335" data-menu-effect="menu-parallax" data-backdrop="true" >
    <h1 class="text-center mt-4"><i class="fa fa-3x fa-check-circle scale-box color-white shadow-xl rounded-circle"></i></h1>
    <h1 class="text-center mt-3 font-700 color-white confirm1-title"></h1>
    <p class="boxed-text-l color-white opacity-70 font-16"></p>
    <a href="" class="font-18  btn btn-m btn-center-m button-s shadow-l rounded-s text-uppercase font-600 bg-white color-black">확인</a>  
</div>

<script type="text/javascript">
	var alertModal = {	
		success : function(txt) {
			if(txt == null || txt.trim() == ""){
	            console.warn("출력할 메시지가 없습니다.");
	            return;
	        }else{
	            this.alertOpen("fa fa-check-circle color-green-dark", txt);
	        }
		},
		fail : function(txt) {
			if(txt == null || txt.trim() == ""){
	            console.warn("출력할 메시지가 없습니다.");
	            return;
	        }else{
	            this.alertOpen("fa fa-times-circle color-red-dark", txt);
	        }
		},
		send : function(txt) {
			if(txt == null || txt.trim() == ""){
				console.warn("출력할 메시지가 없습니다.");
	            return;
	        }else{
	            this.alertOpen("fa-regular fa-envelope color-blue-dark", txt);
	        }
		},
		alertOpen : function(icon, txt){
	        var popup = $("#alertPopup");
	        popup.find("h4").text(txt);
	        popup.find("i").addClass(icon);
	        popup.addClass('menu-active');
	        setTimeout(this.close, 1500, popup);
	    },
		close : function(popup) {
			popup.find("i").removeClass();
			popup.removeClass('menu-active');
		},
		confirm1 : function(txt, callback, title) {
			if(txt == null || txt.trim() == ""){
				console.warn("출력할 메시지가 없습니다.");
	            return;
	        }else{
	            this.confirm1Open(txt, callback, title);
	        }
		},
		confirm1Open : function(txt, callback, title){
	        var popup = $("#confirm1Popup");
	        $(".confirm1-title").text(title);
	        popup.find("p").html(txt);
	        popup.find("a").attr('href', 'javascript:' + callback);
	        popup.addClass('menu-active');
	    },	
	}
	$(document).click(function(e) {
		var target = e.target;
		if((!$(target).hasClass('btn')) && (!$('#confirm1Popup').is(target))) {
			$('#confirm1Popup').removeClass('menu-active');
		}
	})
</script>