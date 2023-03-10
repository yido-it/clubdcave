/**
* Template Name: EstateAgency - v2.1.0
* Template URL: https://bootstrapmade.com/real-estate-agency-bootstrap-template/
* Author: BootstrapMade.com
* License: https://bootstrapmade.com/license/
*/
(function($) {
  "use strict";

  // Preloader
  $(window).on('load', function() {
    if ($('#preloader').length) {
      $('#preloader').delay(100).fadeOut('slow', function() {
        $(this).remove();
      });
    }
  });

  // Back to top button
  $(window).scroll(function() {
    if ($(this).scrollTop() > 100) {
      $('.back-to-top, .back-to-top2').fadeIn('slow');
    } else {
      $('.back-to-top, .back-to-top2').fadeOut('slow');
    }
  });
  $('.back-to-top').click(function() {
    $('html, body').animate({
      scrollTop: 0
    }, 500, 'easeInOutExpo');
    return false;
  });

  var nav = $('nav');
  var navHeight = nav.outerHeight();

  /*--/ ScrollReveal /Easy scroll animations for web and mobile browsers /--
  window.sr = ScrollReveal();
  sr.reveal('.foo', {
    duration: 1000,
    delay: 15
  });*/

  /*--/ Carousel owl /--*/
  $('#carousel').owlCarousel({
    loop: true,
    margin: -1,
    items: 1,
    nav: true,  
    navText: ['<i class="fa-solid fa-arrow-left" aria-hidden="true"></i>', '<i class="fa-solid fa-arrow-right" aria-hidden="true"></i>'],
    autoplay: true,
    autoplayTimeout: 6000,
    autoplayHoverPause: true
  });

  /*--/ Animate Carousel /--*/
  $('.intro-carousel').on('translate.owl.carousel', function() {
    $('.intro-content .intro-title').removeClass('animate__zoomIn animate__animated').hide();
    $('.intro-content .intro-price').removeClass('animate__fadeInUp animate__animated').hide();
    $('.intro-content .intro-title-top, .intro-content .spacial').removeClass('animate__fadeIn animate__animated').hide();
  });

  $('.intro-carousel').on('translated.owl.carousel', function() {
    $('.intro-content .intro-title').addClass('animate__zoomIn animate__animated').show();
    $('.intro-content .intro-price').addClass('animate__fadeInUp animate__animated').show();
    $('.intro-content .intro-title-top, .intro-content .spacial').addClass('animate__fadeIn animate__animated').show();
  });

  /*--/ Navbar Collapse /--*/
  $('.navbar-toggle-box-collapse').on('click', function() {
    $('body').removeClass('box-collapse-closed').addClass('box-collapse-open');
  });
  $('.close-box-collapse, .click-closed').on('click', function() {
    $('body').removeClass('box-collapse-open').addClass('box-collapse-closed');
    $('.menu-list ul').slideUp(700);
  });

  /*--/ Navbar Menu Reduce /--*/
  $(window).trigger('scroll');
  $(window).bind('scroll', function() {
    var pixels = 50;
    var top = 1200;
    if ($(window).scrollTop() > pixels) {
      $('.navbar-default').addClass('navbar-reduce');
      $('.navbar-default').removeClass('navbar-trans');
    } else {
      $('.navbar-default').addClass('navbar-trans');
      $('.navbar-default').removeClass('navbar-reduce');
    }
    if ($(window).scrollTop() > top) {
      $('.scrolltop-mf').fadeIn(1000, "easeInOutExpo");
    } else {
      $('.scrolltop-mf').fadeOut(1000, "easeInOutExpo");
    }
  });

  /*--/ Property owl /--*/
  $('#property-carousel').owlCarousel({
    loop: true,
    margin: 30,
    responsive: {
      0: {
        items: 1,
      },
      769: {
        items: 2,
      },
      992: {
        items: 3,
      }
    }
  });

  /*--/ Property owl owl /--*/
  $('#property-single-carousel').owlCarousel({
    loop: true,
    margin: 0,
    nav: true,
    navText: ['<i class="fa-solid fa-arrow-left" aria-hidden="true"></i>', '<i class="fa-solid fa-arrow-right" aria-hidden="true"></i>'],
    responsive: {
      0: {
        items: 1,
      }
    }
  });

  /*--/ News owl /--*/
  $('#new-carousel').owlCarousel({
    loop: true,
    margin: 30,
    responsive: {
      0: {
        items: 1,
      },
      769: {
        items: 2,
      },
      992: {
        items: 3,
      }
    }
  });

  /*--/ Testimonials owl /--*/
  $('#testimonial-carousel').owlCarousel({
    margin: 0,
    autoplay: true,
    nav: true,
    animateOut: 'fadeOut',
    animateIn: 'fadeInUp',
    navText: ['<i class="fa-solid fa-arrow-left" aria-hidden="true"></i>', '<i class="fa-solid fa-arrow-right" aria-hidden="true"></i>'],
    autoplayTimeout: 4000,
    autoplayHoverPause: true,
    responsive: {
      0: {
        items: 1,
      }
    }
  });

})(jQuery);

var pUrl = window.location.pathname;

// ?????? ?????? 
function getDate(sDate){ 
    var week = ['???', '???', '???', '???', '???', '???', '???'];
    var dayOfWeek = week[new Date(sDate).getDay()];
    return dayOfWeek;
}

// ?????? ?????? ?????? 
function getToDay(separator){
	var today = new Date();   

	var year = today.getFullYear(); // ??????
	var month = today.getMonth() + 1;  // ???
	var date = today.getDate();  // ??????
	var day = today.getDay();  // ??????
	
	var realMonth;
	if (month < 10) {
		realMonth = "0" + month;
	} else {
		realMonth = month;
	}
	var realDate;
	if (date < 10) {
		realDate = "0" + date;
	} else {
		realDate = date;
	}
	
	return year + separator + realMonth + separator + realDate;
}

function searchDate(value) {
	var endDt = new Date(getToDay("-"));
	var strtDt = new Date(getToDay("-"));
	
	switch(value) {
		case '1' : 
			// 1?????????
			strtDt.setMonth(strtDt.getMonth() - 1);
			strtDt = dateFormatter(strtDt, endDt);
			break;
		case '3' :
			// 3?????? ??? 
			strtDt.setMonth(strtDt.getMonth() - 3);
			strtDt = dateFormatter(strtDt, endDt);
			break;
		case '12' : 
			// 1??????
			strtDt.setFullYear(strtDt.getFullYear() - 1);
			strtDt = dateFormatter(strtDt, endDt);
			break;
	}
	
	return strtDt;
} 

function dateFormatter(newDay, today) {
	var year = newDay.getFullYear();
	var month = newDay.getMonth() + 1;
	var date = newDay.getDate();
	
	if (today) {
		var todayDate = today.getDate();
		if (date != todayDate) {
			if (month == 0) year -=1;
			month = (month + 11) % 12;
			date = new Date(year, month, 0).getDate();
		}
	}
	month = ("0"+month).slice(-2);
	date = ("0"+date).slice(-2);
	
	return year + "-" + month + "-" + date;
}

//?????? ?????? ?????? 
function getToTomorrow(separator){
	var today = new Date();   

	var year = today.getFullYear(); // ??????
	var month = today.getMonth() + 1;  // ???
	var date = today.getDate() + 1;  // ??????
	var day = today.getDay();  // ??????
	
	var realMonth;
	if (month < 10) {
		realMonth = "0" + month;
	} else {
		realMonth = month;
	}
	var realDate;
	if (date < 10) {
		realDate = "0" + date;
	} else {
		realDate = date;
	}
	
	return year + separator + realMonth + separator + realDate;
}

// ?????? ???????????? ?????? 
function getStringDt(date, separator){
	
	var strtYear = date.getFullYear(); // ??????
	var strtMonth = date.getMonth() + 1;  // ???
	var strtDate = date.getDate();  // ??????
	
	var realMonth;
	if (strtMonth < 10) {
		realMonth = "0" + strtMonth;
	} else {
		realMonth = strtMonth;
	}
	var realDate;
	if (strtDate < 10) {
		realDate = "0" + strtDate;
	} else {
		realDate = strtDate;
	}
	
	return strtYear + separator + realMonth + separator + realDate;
}

// ?????? ???????????? ??????  
// separator ??? B ?????? ???????????? return 
function getStringDt2(date, separator){

	// ???????????? : 20230117
	var year = date.substr(0, 4);
	var month = date.substr(4, 2);
	var day = date.substr(6);
	
	var returnDate = "";
	if (separator == "B") {
		returnDate = year + "??? " + month + "??? " + day + "???";
	} else {
		returnDate =  year + separator + month + separator + day;
	}
	
	return returnDate;
}

function getTimeFormat(data){
	var returnValue = data;
		
	if (data < 10) {
		returnValue = "0" + data;
	}
	return returnValue;
}

function randomNum(){
    const randomNum = Math.floor(Math.random() * 899 + 100);

    return randomNum;
}

// input ?????? ?????? ??????
function chkInputVal (id) {
	var str = $('#' + id).data('name');
	const charCode = str.charCodeAt(str.length - 1);
    const consonantCode = (charCode - 44032) % 28;
    
    if($('#' + id).val() == "" || $('#' + id).val() == null) {
    	if(consonantCode === 0){
	        alertModal.fail(str + '??? ??????????????????');
	    } else {	
	        alertModal.fail(str + '??? ??????????????????');
	    }
		return false;
	}
    return true;
}

// select input ????????????
function setSelectValue(id, value) {
	var id = "#" + id;
	
	if(value == undefined || value == null) {
		var valId = id + "Val";
		if($(valId).val() != null && $(valId).val() != "") {		
			$(id).parent().find('span').removeClass('input-style-1-inactive input-style-1-active');
			$(id).val($(valId).val()).prop("selected", true);
			$(id).parent().find('span').addClass('input-style-1-inactive input-style-1-active');
		}
	} else {
		$(id).parent().find('span').removeClass('input-style-1-inactive input-style-1-active');
		$(id).val(value).prop("selected", true);
		$(id).parent().find('span').addClass('input-style-1-inactive input-style-1-active');
	}
	
}

// date input ????????????
function setDateValue(id, value) {
	var id = "#" + id;
	if(value == undefined || value == null) {
		value = $(id).val();
		if(value != null && value != "") {
			$(id).val(value);		
			$(id).parent().find('span').removeClass('input-style-1-inactive input-style-1-active');
			$(id).parent().find('span').addClass('input-style-1-inactive input-style-1-active');
		} else {
			$(id).val(getToDay("-"));			
			$(id).parent().find('span').removeClass('input-style-1-inactive input-style-1-active');
			$(id).parent().find('span').addClass('input-style-1-inactive input-style-1-active');
		}
	} else {
			$(id).parent().find('span').removeClass('input-style-1-inactive input-style-1-active');
			$(id).parent().find('span').addClass('input-style-1-inactive input-style-1-active');
	}

}

// alertModal ??? ????????? ??????
function goAfterModal(url) {
	var time = 1200;
	var str = "";
	
	if (url != undefined && url != null && url != "") {
		str = "location.href='" + url + "'";
	} else if (url == "back") {
		str = "history.back()"
	} else {
		str = "location.reload()";
	}
	setTimeout(str, time);
}

// url parameter ?????????
function urlParam(name) {
	var queryString = window.location.search;
	var urlParams = new URLSearchParams(queryString);
	if (!urlParams.has(name)) { 
		return false;
	} 
	return urlParams.get(name);
}	

/** @param txt<br/>
 *  @param len : ????????? ????????? 20<br/>
 *  @param lastTxt : ????????? ????????? "..."<br/>
 *  @returns ?????????
 * <br/>
 * <br/>
 * ?????? ???????????? ???????????? ???????????? ????????? ????????? ???????????? ???????????? ??????<br/>
 * ???ex) ????????????????????? -> textLengthOverCut('?????????????????????', '5', '...') : ???????????????...<br/>
 */
function textLengthOverCut(txt, len, lastTxt) {
    if (len == "" || len == null) { // ?????????
        len = 20;
    }
    if (lastTxt == "" || lastTxt == null) { // ?????????
        lastTxt = "...";
    }
    if (txt.length > len) {
        txt = txt.substr(0, len) + lastTxt;
    }
    return txt;
}

// ????????? ?????? ?????? ??????
function mobileYn() {
		return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
}

/* 
* ???????????? ??????
* values : formData
* k : ????????? input Name
* v : ????????? input Value
*/
function changeSerialize(values, k, v) {
    var found = false;
    for (i = 0; i < values.length && !found; i++) {
        if ( values[i].name == k ) { 
            values[i].value = v;
            found = true;
        }
    }
    if (!found) {
        values.push(
            {
                name: k,
                value: v
            }  
        );
    }
    return values;
}
