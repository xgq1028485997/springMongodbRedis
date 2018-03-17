<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>类型管理</title>
</head>

<body>
	<div class="layui-row">
		<fieldset class="layui-elem-field site-demo-button">
			<legend style="font-size: 12px;">检索条件</legend>
			<form class="layui-form" action="">
				<div class="layui-form-item" style="margin-bottom:4px;">
					<div class="layui-inline">
						<label class="layui-form-label">类型编号</label>
						<div class="layui-input-block">
							<input name="typeNo" class="layui-input" type="text" onkeyup="this.value=this.value.toUpperCase()"
								placeholder="类型编号" autocomplete="off" lay-verify="searchTypeNo">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">类型名称</label>
						<div class="layui-input-block">
							<input name="typeName" class="layui-input" type="text"
								placeholder="类型名称" autocomplete="off" lay-verify="searchTypeName">
						</div>
	
					</div>
					<div class="layui-inline">
						<div class="layui-input-block">
							<button class="layui-btn" name="search" lay-filter="search" lay-submit="">检索</button>
	      					<button class="layui-btn layui-btn-primary" type="reset">重置</button>
						</div>
					</div>
				</div>
			</form>
		</fieldset>
		
		<div class="layui-col-md12">
			<div class="layui-btn-group">
				<button class="layui-btn  layui-btn-sm layui-btn-primary" data-type="addType" data-method="addType">添加</button>
			</div>
		</div>
		<div class="layui-col-md8">
			<table id="typeList" lay-filter="typeList"></table>
		</div>
		<div class="layui-col-md4">
			<fieldset class="layui-elem-field site-demo-button" style="height:490px;padding-right:10px;">
			  <legend>类型信息</legend>
			  <div style="padding-top:25%;">
					<form class="layui-form" action="#">
						<input type="hidden" name="typeId" class="layui-input">
						<div class="layui-form-item">
							<label class="layui-form-label">类型编码</label>
							<div class="layui-input-block">
								<input type="text" name="typeNo" lay-verify="typeNo" 
									autocomplete="off" class="layui-input" onkeyup="this.value=this.value.toUpperCase()">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">类型名称</label>
							<div class="layui-input-block">
								<input type="text" name="typeName"
									lay-verify="typeName" autocomplete="off" 
									class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-input-block" align="center">
								<button class="layui-btn" name="submitType" lay-submit
									lay-filter="submitType">添加</button>
							</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-input-block" align="center" hidden>
								<button class="layui-btn layui-btn-primary" name="resetType" type="reset">重置</button>
							</div>
						</div>
					</form>
				</div>
			</fieldset>
		</div>
		
	</div>
</body>
<script type="text/javascript">
layui.use(['form','table','layer'], function(){
	var table = layui.table
	,layer = layui.layer,
	form = layui.form;
	
	var typeTable = table.render({
	  id : 'typeList'
	  ,elem: '#typeList' //或 elem: document.getElementById('test') 等
	  ,url: 'type/getTypeList'
	  ,cols:  [[ //标题栏
	    {field: 'typeId', title: '序号', width: 80,templet: '#number'},
	    {field: 'typeNo', title: '类型编码', width: 160, sort:true},
	    {field: 'typeName', title: '类型名称'},
	    {field: 'typeAddtime', title: '添加时间', width: 120, templet: '#typeAddtime', sort:true},
	    {fixed: 'right', width: 184, align:'center', toolbar: '#barType'}
	  ]]
	  ,method: 'post'
	  ,height: 480
	  //,skin: 'row' //表格风格
     ,even: true //是否开启偶数行背景
     ,page: true //是否显示分页
     ,limits: [5,10,20]//每页展示多少数据量的选项
     ,limit: 10 //每页默认显示的数量
     ,done: function(res, curr, count){
   	    //如果是异步请求数据方式，res即为你接口返回的信息。
   	    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
   	    //console.log(res);
   	    //得到当前页码
   	    //console.log(curr); 
   	    //得到数据总量
   	    //console.log(count);
   	  }
	});
	
	//触发事件
	var typeActive = {
		addType : function(othis){
			$("button[name='submitType']").html("添加");
			$("button[name='resetType']").click();
			$("input[name='typeId']").val("");
		}
	};
	
	//监听工具条
	table.on('tool(typeList)', function(obj){ //注：tool是工具条事件名，typeList是table原始容器的属性 lay-filter="对应的值"
		  var data = obj.data //获得当前行数据
		  ,layEvent = obj.event; //获得 lay-event 对应的值
		  if(layEvent === 'del'){
		    layer.confirm('确定要删除' + data.typeNo + "吗？", function(index){
		    	$.ajax({
			        type :'post',  
			        async : false,  
			        dataType : 'json',
			        data : data,
			        url: "${pageContext.request.contextPath}/type/delType",    
			        success : function(data) {
			        	layer.alert(data.info);
			        	$("button[name='search']").click();
			        }, 
			        error : function(XMLHttpRequest, textStatus, errorThrown) {
			        	layer.msg(errorThrown);
					}
	 			});
		    });
		  } else if(layEvent === 'edit'){
			$("input[name='typeId']").val(data.typeId);
			$("input[name='typeName']").val(data.typeName);
			$("input[name='typeNo']").val(data.typeNo);
			$("button[name='submitType']").html("修改");
		  }
	});

	$('.layui-btn').on('click', function(){
	    var othis = $(this), method = othis.data('method');
	    typeActive[method] ? typeActive[method].call(this, othis) : '';
	  });
	
	//自定义验证规则
	form.verify({
	   typeNo: [/^[A-Z0-9]{1,32}$/, '用户编码必须1到32位的大写字母与数字']
	   ,typeName: [/^[\u4E00-\u9FA5A-Za-z0-9]{1,64}$/, '用户编码必须1到64位的中文、字母与数字']
	   ,searchTypeName: [/^[\u4E00-\u9FA5A-Za-z0-9]{0,64}$/, '用户编码必须0到64位的中文、字母与数字']
	   ,searchTypeNo: [/^[A-Z0-9]{0,32}$/, '用户编码必须0到32位的大写字母与数字']
	});
	//监听提交
	  form.on('submit(search)', function(data){
		  typeTable.reload({
				where: { //设定异步数据接口的额外参数，任意设
			    	typeNo: data.field.typeNo
			    	,typeName : data.field.typeName
				}
			});
	    return false;
	  });
	//监听提交
	  form.on('submit(submitType)', function(data){
		  $.ajax({
		        type :'post',  
		        async : false,  
		        dataType : 'json',
		        data : data.field,
		        url: "${pageContext.request.contextPath}/type/addOrEditType",    
		        success : function(data) {
	        	 	layer.msg(data.info,
	        	 			{icon: 3
	        	 			,time: 1000 //1秒关闭（如果不配置，默认是3秒）
	        	 			, title:'提示'}, function(){
	        	 				$("button[name='submitType']").html("添加");
	        	 				$("button[name='resetType']").click();
	        	 				$("input[name='typeId']").val("");
	        	 				$("button[name='search']").click();
		        	});
	        	 	return true;
		        }, 
		        error : function(XMLHttpRequest, textStatus, errorThrown) {
		        	layer.msg(errorThrown);
				}
 			});
			return false;
	  });
});
</script>
<script type="text/html" id="typeAddtime">
	{{ formartDate('yyyy-MM-dd',d.typeAddtime) }}
</script>
<script type="text/html" id="barType">
	<a class="layui-btn  layui-btn-sm" lay-event="edit">编辑</a>
  	<a class="layui-btn layui-btn-danger  layui-btn-sm" lay-event="del">删除</a>
</script>
<script type="text/html" id="number">
	{{d.LAY_INDEX}}
</script>
</html>