$(function(){
	showTime();
	setInterval(showTime, 60000);
	hideSideMenuLink();
	menuHoverEvent();
	sideMenuClickEvent();
	
	
	//menu hover event
	function menuHoverEvent(){
		$('.secMenuList').hover(
			function(){
				$('.'+$(this).attr('id')).slideDown(400);
			},
		    function () {
		      $('.'+$(this).attr('id')).slideUp(250);
		    }
		) 
	}
	
	//side menu click event
	function sideMenuClickEvent(){
		$('.secList').on("click", function() {
    		$('.'+$(this).attr('id')).slideToggle(300);
		})
	}
	
	//hideSideMenuLink
	function hideSideMenuLink(){
		$('.section').hide();
	}
	
	//show time
	function showTime() {
	  var today = new Date();
	  $('#time').html(today.toDateString() + " " + today.getHours() + ":" + ('0' + today.getMinutes()).slice(-2));
	}
	
	
})