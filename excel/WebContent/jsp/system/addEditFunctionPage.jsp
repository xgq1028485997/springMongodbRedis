<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../util/header.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加或修改用户页面</title>
</head>
<body>
	<div class="layui-row">
		<div class="layui-col-md4" style="background-color: red;">
			<div id="functionPcode_tree"></div>
		</div>
		<div class="layui-col-md8" style="height:490px;padding-right:10px;">
			<form class="layui-form" action="#">
				<input type="hidden" name="functionId" class="layui-input">
				<div class="layui-form-item">
					<label class="layui-form-label">父级功能</label>
					<div class="layui-input-block">
						<input type="text" name="functionPcode" disabled autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">功能编号</label>
					<div class="layui-input-block">
						<input type="text" name="functionCode" disabled autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">功能名称</label>
					<div class="layui-input-block">
						<input type="text" name="functionName" lay-verify="functionName"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">是否有效</label>
					<div class="layui-input-block">
						 <input name="functionDelete" type="checkbox" lay-skin="switch" lay-text="停用|启动">
	
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">功能URL</label>
					<div class="layui-input-block">
						<input type="text" name="functionUrl" lay-verify="functionUrl"
							placeholder="" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">功能排序</label>
					<div class="layui-input-block">
						<input type="text" name="userTel" lay-verify="userTel"
							placeholder="" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">功能类型</label>
					<div class="layui-input-block">
						<input type="text" name="userCard" lay-verify="userCard"
							placeholder="Excel_1" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block" hidden>
						<button class="layui-btn" name="submitFunction" lay-submit
							lay-filter="submitUser">提交</button>
						<button class="layui-btn" type="reset" name="resetFunction">重置</button>
					</div>
				</div>
			</form>
	
		</div>
	</div>
</body>

<script type="text/javascript">
	layui.use(['form','laydate','tree'], function() {
		var form = layui.form;
		
		form.render();
		
		//自定义验证规则
		form.verify({
			functionName : [ /^[\u4E00-\u9FA5A-Za-z0-9]{1,64}$/,
					'用户编码必须1到64位的中文、字母与数字' ],
			functionUrl: [ /^[\u4E00-\u9FA5A-Za-z0-9]{0,125}$/,'用户编码必须0到125位的中文、字母与数字' ]
		});
		//监听提交
		form.on('submit(submitFunction)', function(data) {
			//layer.msg(JSON.stringify(data.field));
			$.ajax({
				type : 'post',
				async : false,
				dataType : 'json',
				data : data.field,
				url : "${pageContext.request.contextPath}/function/addOrEditFunction",
				success : function(data) {
					layer.msg(data.info, {
						icon : 3,
						time : 1000 //1秒关闭（如果不配置，默认是3秒）
						,
						title : '提示'
					}, function() {
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭
					});
					return true;
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					layer.msg(errorThrown);
				}
			});
			return false;
		});
		
		
		layui.tree({
		  elem: '#functionPcode_tree'
		  ,nodes: [{ //节点数据
		    name: '节点A'
		    ,children: [{
		      name: '节点A1'
		    }]
		  }, {
		    name: '节点B'
		    ,children: [{
		      name: '节点B1'
		      ,alias: 'bb' //可选
		      ,id: '123' //可选
		    }, {
		      name: '节点B2'
		    }]
		  }] 
		  ,click: function(node){
		    console.log(node) //node即为当前点击的节点数据
		  }  
		});
		
	});

	function setValue(obj) {
		if (obj != null) {
			$("input[name='functionId']").val(obj.functionId);
			$("input[name='functionName']").val(obj.functionName);
			$("input[name='functionCode']").val(obj.functionCode);
			$("input[name='functionDelete']").val(obj.functionDelete);
			$("select[name='functionUrl']").val(obj.functionUrl);
			$("select[name='functionType']").val(obj.functionType);
			$("select[name='functionSort']").val(obj.functionSort);
		} else {
			resetFunction();
		}
	}
	
	function submitFunction() {
		$("button[name='submitFunction']").click();
	}

	function resetFunction() {
		$("button[name='resetFunction']").click();
	}
	
</script>

</html>