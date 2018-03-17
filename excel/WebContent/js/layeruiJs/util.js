/**
 * 
 */

/**
 * AJAX error时处理方法
 * @param XMLHttpRequest
 */
$(document).ajaxComplete(function(event, xhr, settings) {
	if (xhr.getResponseHeader("sessionstatus") == "timeout") {
		if (xhr.getResponseHeader("loginPath")) {
			alert("登录过期，请重新登录...");
			window.location.replace(xhr.getResponseHeader("loginPath"));
		} else {
			alert("请求超时请重新登陆!");   
		}
	}
});