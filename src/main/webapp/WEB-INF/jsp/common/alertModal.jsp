<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 
<script type="text/javascript" src="/scripts/jquery.js"></script>
<script type="text/javascript" src="/scripts/bootstrap.min.js"></script>
 -->
 
<div id="alertPopup" class="menu menu-box-modal rounded-m" data-menu-hide="1000" data-menu-width="220" data-menu-height="160">
	<h1 class="text-center fa-5x mt-2 pt-3 pb-2"><i class=""></i></h1>
	<h4 class="text-center"></h4>
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
	            console.alertOpen("출력할 메시지가 없습니다.");
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
	        setTimeout(this.close, 1000, popup);
	    },
		close : function(popup) {
			popup.find("i").removeClass();
			popup.removeClass('menu-active');
		}
	    
	}
</script>