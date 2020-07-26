$(document).ready(function(){
	$(".popup_modal").addClass("hide fade");
	var popup_left = ($(document).width() - $(".popup_modal").outerWidth())/2;
	$(".popup_modal").css({left: popup_left});
})