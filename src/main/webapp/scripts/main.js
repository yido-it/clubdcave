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

// 요일 반환 
function getDate(sDate){ 
    var week = ['일', '월', '화', '수', '목', '금', '토'];
    var dayOfWeek = week[new Date(sDate).getDay()];
    return dayOfWeek;
}

// 오늘 날짜 반환 
function getToDay(separator){
	var today = new Date();   

	var year = today.getFullYear(); // 년도
	var month = today.getMonth() + 1;  // 월
	var date = today.getDate();  // 날짜
	var day = today.getDay();  // 요일
	
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

// 오늘 날짜 구하려면 -> year = 0, month = 1
// 1달뒤 날짜 구하려면 -> year = 0, month = 2
// 1년뒤 날짜 구하려면 -> year = 1, month = 1
function getDaybyMonth(separator, year, month){
	var today = new Date();   

	var year;
	if (year == 0) {
		year = today.getFullYear(); // 년도
	} else {
		year = today.getFullYear() + year; // 년도
	}
	var month = today.getMonth() + month;  // 월
	var date = today.getDate();  // 날짜
	var day = today.getDay();  // 요일
	
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


//내일 날짜 반환 
function getToTomorrow(separator){
	var today = new Date();   

	var year = today.getFullYear(); // 년도
	var month = today.getMonth() + 1;  // 월
	var date = today.getDate() + 1;  // 날짜
	var day = today.getDay();  // 요일
	
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

// 날짜 문자열로 반환 
function getStringDt(date, separator){
	
	var strtYear = date.getFullYear(); // 년도
	var strtMonth = date.getMonth() + 1;  // 월
	var strtDate = date.getDate();  // 날짜
	
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

// 날짜 문자열로 반환  
function getStringDt2(date, separator){

	// 파라미터 : 20230117
	var year = date.substr(0, 4);
	var month = date.substr(4, 2);
	var day = date.substr(6);
	
	return year + separator + month + separator + day;
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

function chkInputVal (id) {
	var str = $('#' + id).data('name');
	const charCode = str.charCodeAt(str.length - 1);
    const consonantCode = (charCode - 44032) % 28;
    
    if($('#' + id).val() == "" || $('#' + id).val() == null) {
    	if(consonantCode === 0){
	        alertModal.fail(str + '를 입력해주세요');
	    } else {	
	        alertModal.fail(str + '을 입력해주세요');
	    }
		return false;
	}
    return true;
}

function setSelectValue(id) {
	var id = "#" + id;
	var valId = id + "Val";
	if($(valId).val() != null && $(valId).val() != "") {		
		$(id).parent().find('span').removeClass('input-style-1-inactive input-style-1-active');
		$(id).val($(valId).val()).prop("selected", true);
		$(id).parent().find('span').addClass('input-style-1-inactive input-style-1-active');
	}
}

function setDateValue(id) {
	var id = "#" + id;
	if($(id).val() != null && $(id).val() != "") {		
		$(id).parent().find('span').removeClass('input-style-1-inactive input-style-1-active');
		$(id).parent().find('span').addClass('input-style-1-inactive input-style-1-active');
	}
}