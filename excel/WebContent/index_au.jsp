<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<html>
<head>
 <%@include file="/jsp/util/header.jspf" %>
 <%@include file="/jsp/util/tools.jsp" %>
</head>
<body>

	<%@include file="/jsp/util/header.jsp"%>

	<div class="am-cf admin-main">
		<!-- sidebar start -->
		<%@include file="/jsp/util/menu.jsp"%>
		<!-- sidebar end -->

		<!-- content start -->
		<div id="indexContents"></div>
		<!-- footer -->
		<div id="index_bottom">
			<section class="am-panel am-panel-default">
				<footer class="am-panel-footer"></footer>
			</section>
		</div>
	</div>

</body>
<script type="text/javascript">
$(document).ready(function(){
	$(".admin-main").css({"height":getWindowSub()});
	$("#indexContents").css({"height":getWindowSub()});
	$(".am-panel-footer").html(new Date());
	
	if('${empty sessionScope}'=='true'){
		window.location.href = "${pageContext.request.contextPath}/lc/index";
	}
	if(!isNotNull($("#indexContents").html())){
		$("#indexContents").load('${pageContext.request.contextPath}/main.jsp',null,null);
	}
});

//容器标签加载页面
function loadPage(url,params,fun){
	//alert(url);
	//load(url,data,function(response,status,xhr))
	//typeof 返回类型'undefined' “string” 'number' 'boolean'  'function'  'object'   
	$("#indexContents").load('${pageContext.request.contextPath}/'+url,params,typeof fun == 'function' ? fun : null);
}

</script>
</html>

