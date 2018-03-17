<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="layui-header">
		<div class="layui-logo">Excel后台管理系统</div>
		<!-- 头部区域（可配合layui已有的水平导航） -->
		<div class="layui-bg-molv">
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;">
				 ${sessionScope.loginInfo.getUserNo()}/${sessionScope.loginInfo.getUserName()}
				</a>
					<!-- <dl class="layui-nav-child">
						<dd>
							<a href="">基本资料</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl> --></li>
				<li class="layui-nav-item"><a href="javascript:loginOut()">退出</a></li>
			</ul>
		</div>
	</div>
  <script type="text/javascript">
  $(document).ready(function(){
		$(".layui-layout-admin .layui-header").css({"background-color":"#f2f2f2","color":"#333"});
		$(".layui-nav .layui-nav-item a").css({"color":"#333"});
		$(".layui-layout-admin .layui-logo").css({"color":"#333"});
  });
  
  function loginOut(){
	  if(confirm('确定要退出么')){ //只有当点击confirm框的确定时，该层才会关闭
		  $.ajax({    
		        type :'post',  
		        async : false,  
		        dataType : 'json',
		        url: "${pageContext.request.contextPath}/lc/loginOut",    
		        success : function(data) {
		        	window.location.href = "${pageContext.request.contextPath}"+data.url;
	        		return true;
		        }, 
		        error:function(XMLHttpRequest, textStatus, errorThrown){
	            	ajaxError(XMLHttpRequest);
	          	}
			});
	  }
	  
  }
  	/* $(function(){
  		$.ajax({    
		        type :'post',  
		        async : false,  
		        dataType : 'json',
		        url: "${pageContext.request.contextPath}/system/notice/noReadNoticeCount",    
		        success : function(data) { 
		        	if(data.noticeCount == null || data.noticeCount == 0){
		        		$('#noticeCount').css("display","none");
		        		//$('#tz').attr("href", "#");
		        	}else{
			        	$('#noticeCount').html(data.noticeCount);
		        	}
		        }, 
		        error : function(data) {
		        		
					}
    		});
    		
    		 
  	});  */
  </script>
