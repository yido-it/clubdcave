
var mPay = function() {
	var fnSuccess, fnFail;

	var form = "<form name='payment' id='frm_payment' method='post'> \
				  <input type='hidden' name='SERVICE_ID'   value='{0}'> \
				  <input type='hidden' name='SERVICE_CODE' value='0900'> \
				  <input type='hidden' name='SERVICE_TYPE' value='0000'> \
				  <input type='hidden' name='ORDER_ID'     value='{1}_{2}'> \
				  <input type='hidden' name='ORDER_DATE'   value='{2}'> \
				  <input type='hidden' name='AMOUNT'       value='{3}'> \
				  <input type='hidden' name='RETURN_URL'   value='{4}'> \
				  <input type='hidden' name='ITEM_CODE'    value='{5}'> \
				  <input type='hidden' name='ITEM_NAME'    value='{6}'> \
				  <input type='hidden' name='USER_ID'      value='{7}'> \
				  <input type='hidden' name='USER_NAME'    value='{8}'> \
				  <input type='hidden' name='USER_EMAIL'   value='{9}'> \
				  <input type='hidden' name='RESERVED1'    value='{10}'> \
				  <input type='hidden' name='RESERVED2'    value='{11}'> \
				  <input type='hidden' name='RESERVED3'    value='{12}'> \
				  <input type='hidden' name='CANCEL_FLAG'  value='Y'> \
				  <input type='hidden' name='WEBAPI_FLAG'  value='Y'> \
				  <input type='hidden' name='LOGO'         value=''> \
				  <input type='hidden' name='APPNAME'      value='clubdcave'> \
				</form>";

    var action = function(params, success, fail) {
		fnSuccess = success;
		fnFail = fail;
		
		var current = new Date().yyyymmddhhmmss();
		
		var format_form = String.format(form
			, params.serviceId
			, params.key
			, current
			, params.amount
			, params.returnUrl
			, params.itemCode
			, params.itemName
			, params.userId
			, params.userName
			, params.userMail
			, encodeURIComponent(params.reserved1)
			, encodeURIComponent(params.reserved2)
			, encodeURIComponent(params.reserved3)
		);
		
		$("#frm_payment").remove();
		$("body").append(format_form);

		GX_pay("payment", "popup", params.protocol);
    }

	var result = function(code) {
		if(code == "0000") {
			alert('성공');
			fnSuccess();
		} else {
			fnFail();
		}
	}
   
    return {
        action : action,
		result : result
    }
   
}();