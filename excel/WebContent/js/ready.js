

$(document).ready(function(){
	//分页
	$('.page_s').on('click',function(){
		var params = {};
		params["userNo"] = $("#userNo").val();
		params["currentPage"] = $(this).attr('lang');
		loadPage("user/getUserList",params,null);
	});		
});
