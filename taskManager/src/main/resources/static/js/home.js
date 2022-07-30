$(function(){
	setInterval(showTime, 1000);
	hideSideMenuLink();
	sideMenuClickEvent();
	
	
	//side menu click event
	function sideMenuClickEvent(){
		$('.secList').on("click", function() {
    		$('.'+$(this).attr('id')).slideToggle(400);
		})
	}
	
	//hideSideMenuLink
	function hideSideMenuLink(){
		$('.section').hide();
	}
	
	//show time
	function showTime() {
	  var today = new Date();
	  $('#time').html(today.toDateString() + " " + today.getHours() + ":" + ('0' + today.getMinutes()).slice(-2) + ":" + ('0' + today.getSeconds()).slice(-2));
	}
	
	
})