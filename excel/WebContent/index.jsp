<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/jsp/util/header.jspf"%>
<!doctype html>
<html>
<head>
<title>Index主页面</title>
<style type="text/css">

</style>
<script type="text/javascript">
	
	$(document).ready(
		function() {
			$(".layui-layout-admin .layui-footer").css({
				"text-align" : "center",
				"color" : "#333"
			});
			$(".layui-footer").html("<span style='float:left;'>© excel.com</span>"+ new Date());
			//$(".layui-body").css({"left":"252px","text-align":"center"});
			//6分钟
			if ('${empty sessionScope}' == 'true') {
				window.location.href = "${pageContext.request.contextPath}/lc/index";
			}
	
			if (!isNotNull($("#index_content").html())) {
				$("#index_content").load('${pageContext.request.contextPath}/main.jsp',null, null);
			}
	});

	layui.use('element', function() {
		var element = layui.element;

	});

	//容器标签加载页面
	function loadPage(url, params, fun) {
		//alert(url);
		//load(url,data,function(response,status,xhr))
		//typeof 返回类型'undefined' “string” 'number' 'boolean'  'function'  'object' 
		if (isNotNull(url)) {
			$("#index_content").load(
					'${pageContext.request.contextPath}/' + url, params,
					typeof fun == 'function' ? fun : null);
		}

	}
</script>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<%@include file="/jsp/index/header.jsp"%>

		<%@include file="/jsp/index/menu.jsp"%>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div id="index_content" style="padding: 15px;"></div>
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			
		</div>
	</div>
</body>
</html>

