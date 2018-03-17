<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/jsp/util/header.jspf"%>
<title>Excel</title>
<style type="text/css">
	body {
		text-align: center;
		margin-top: 10%;
	}
    .header h1 {
      font-size: 200%;
      color: #333;
      margin-top: 30px;
    }
    .header p {
      font-size: 14px;
    }
</style>

<script type="text/javascript">
	$(document).ready(function(){
		$(".layui-form-label").css({"width":"110px","display":"ruby-base"});
		$(".layui-form-item .layui-input-inline").css({"float":"none"});
		
		$("input[name='userNo']").focus();
		
	});
	layui.use(['form', 'layer'], function(){
	  	var form = layui.form,//2.x版本，以下版本latui.form()
	  	layer = layui.layer;
		//监听提交
		form.on('submit(login)', function(data){
			var params = {};
			params['userNo'] = data.field.userNo;
			params['userPassword'] = data.field.userPassword;
			$.ajax({    
		        type :'POST',  
		        async : false,  
		        dataType : 'json',
		        data : params,
		        url: "${pageContext.request.contextPath}/lc/login",    
		        success : function(data) {
		        	if(data.flag){
		        		window.location.href = "${pageContext.request.contextPath}"+data.url;
		        		return true;
		        	}else{
		        		layer.alert(data.info); 
		        	}
		        }, 
		        error:function(XMLHttpRequest, textStatus, errorThrown){
    	            ajaxError(XMLHttpRequest);
   	          	}
    		});
			return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
		});
		
		/* document.onkeydown = function (e) {
		    var theEvent = window.event || e;
		    var code = theEvent.keyCode || theEvent.which;
		    if (code == 13) {
		    	var params = {};
				params['userNo'] = $("input[name='userNo']").val().trim();
				params['userPassword'] = $("input[name='userPassword']").val().trim();
				login(params);
		    }
		}
		
		function login(params){
			$.ajax({    
		        type :'POST',  
		        async : false,  
		        dataType : 'json',
		        data : params,
		        url: "${pageContext.request.contextPath}/lc/login",    
		        success : function(data) {
		        	if(data.flag){
		        		window.location.href = "${pageContext.request.contextPath}"+data.url;
		        	}else{
		        		layer.msg(data.info); 
		        	}
		        }, 
		        error:function(XMLHttpRequest, textStatus, errorThrown){
       	            ajaxError(XMLHttpRequest);
      	          	}
    		});
			
		} */
		
		
	});
	
	
</script>
</head>
<body>
	<div class="header">
		<div>
			<h1>Excel系统</h1>
			<p>
				welcome access system<br />欢迎访问系统
			</p>
		</div>
		<hr />
	</div>

	<div>
		<form class="layui-form" method="post" action="">
			<div class="layui-form-item">
				<label class="layui-form-label">用户编号：</label>
				<div class="layui-input-inline">
					<input name="userNo" class="layui-input" type="text" autocomplete="off" required lay-verify="required">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">登陆密码：</label>
				<div class="layui-input-inline">
					<input name="userPassword" class="layui-input" type="password" autocomplete="off" required lay-verify="required">
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="login">登陆</button>
				</div>
			</div>
		</form>

		
	</div>
	<hr />
	<div class="botton"><p>© 2017 All Compute, Inc. Licensed under MIT license.</p></div>

</body>
</html>