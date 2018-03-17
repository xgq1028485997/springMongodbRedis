<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>功能菜单</title>
<script>
	layui.use(['form','table','layer'], function(){
		var table = layui.table
		,layer = layui.layer,
		form = layui.form;
		
		var functionTable = table.render({
		  id : 'functionList'
		  ,elem: '#functionList' //或 elem: document.getElementById('test') 等
		  ,url: 'function/getFunctionList'
		  ,cols:  [[ //标题栏
		    {checkbox: true, LAY_CHECKED: false, fixed:true},
		    {field: 'functionId', title: '序号', width: 80 ,templet: '#number'},
		    {field: 'functionPcode', title: '父级编码', width: 160, sort:true},
		    {field: 'functionCode', title: '功能编码', width: 160},
		    {field: 'functionName', title: '功能名称', width: 160},
		    {field: 'functionUrl', title: '功能URL', width: 300},
		    {field: 'functionSort', title: '功能排序', width: 80},
		    {field: 'functionIcon', title: '功能图标', width: 100},
		    {field: 'functionType', title: '功能类型', width: 100},
		    {field: 'functionAddtime', title: '添加时间', width: 120, templet: '#functionAddtime', sort:true},
		    {field: 'functionRemark', title: '功能说明', width: 200},
		  ]]
		  ,method: 'post'
		  ,height: 490
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
		var functionActive = {
			oFunction : function(othis){
				var type = othis.data('type')
			      ,text = othis.text();
				var checkStatus = table.checkStatus('functionList');
				var title = '修改功能菜菜单';
				if(type == 'addFunction'){
					title = '添加功能菜单';
				}else{
					if(checkStatus.data.length != 1){
						layer.alert('请勾选一条需修改的数据。');
						return false;
					}
				}
				layer.open({
			        type: 2 //0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）。 
			        ,title: title
			        //,offset: "" //默认垂直水平居中
			        ,area: ['800px', '500px']
			        ,id: 'layerFunction_'+type //防止重复弹出
			        ,content: '${pageContext.request.contextPath}/jsp/system/addEditFunctionPage.jsp'
			        ,anim: 5 //渐显
			        ,resize: false //不能允许拉伸
			        //,shade: 0 //不显示遮罩
			        ,success: function(layero, index){
			        	//层弹出后的成功回调方法
			        	var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法
			        	if(type == 'addFunction'){
			        		iframeWin.setValue(null);
						}else{
							iframeWin.setValue(checkStatus.data[0]);
						}
			        	
			        }
			        ,btn: ['提交', '重置']
					,yes: function(index, layero){
			        	var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法
			        	iframeWin.submitFunction();
			        	
			        	functionActive.searchFunction();
			        	
				  	},btn2: function(index, layero){
					  //按钮【按钮二】的回调
					  var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法
			        	iframeWin.resetFunction();
					  return false; //开启该代码可禁止点击该按钮关闭
					},cancel: function(index, layero){ 
					  if(confirm('确定要关闭么')){ //只有当点击confirm框的确定时，该层才会关闭
					    layer.close(index);
					  }
					  return false; 
					}
			    });
			},delFunction : function(){
				var checkFunction = table.checkStatus('functionList');
				var params = {};
				if(checkFunction.data.length>0){
					var functionId = "";
					for(var i = 0 ; i < checkFunction.data.length ; i++){
						functionId +=  checkFunction.data[i].functionId + ",";
					}
					if(userId != ""){
						params["functionId"] = functionId.substring(0,functionId.length-1);
					}
					$.ajax({
				        type :'post',
				        async : false,
				        dataType : 'json',
				        data : params,
				        url: "${pageContext.request.contextPath}/function/delFunction",
				        success : function(data) {
				        	layer.alert(data.info);
				        	functionActive.searchFunction();
				        },
				        error : function(data) {
				        	layer.alert(data.info);
						}
		    		}); 
				}else{
					layer.alert("请勾选需要删除的数据");
				}
			},searchFunction : function(){
				$("button[name='search']").click();
			}
			
		};
		
		$('.layui-btn').on('click', function(){
		    var othis = $(this), method = othis.data('method');
		    functionActive[method] ? functionActive[method].call(this, othis) : '';
		  });
		
		//监听提交
		  form.on('submit(search)', function(data){
			  functionTable.reload({
					where: { //设定异步数据接口的额外参数，任意设
				    	functionCode : data.field.functionCode
				    	,functioName : data.field.functionName
					}
				});
		    return false;
		  });
		
		//自定义验证规则
		form.verify({
		   searchFunctionName: [/^[\u4E00-\u9FA5A-Za-z0-9]{0,64}$/, '用户编码必须0到64位的中文、字母与数字']
		   ,searchFunctionCode: [/^[A-Z0-9.-_]{0,32}$/, '用户编码必须0到32位的大写字母、数字、_、.和-']
		});
	});
	
</script>
<script type="text/html" id="functionAddtime">
	{{ formartDate('yyyy-MM-dd',d.functionAddtime) }}
</script>
</head>
<body>
	<div>
		<fieldset class="layui-elem-field site-demo-button">
			<legend style="font-size: 12px;">检索条件</legend>
			<form class="layui-form" action="">
			<div class="layui-form-item"  style="margin-bottom:4px;">
				<div class="layui-inline">
					<label class="layui-form-label">功能编号</label>
					<div class="layui-input-block">
						<input name="functionCode" class="layui-input" type="text"
							placeholder="功能编号" autocomplete="off" lay-verify="searchFunctionCode">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">功能名称</label>
					<div class="layui-input-block">
						<input name="functionName" class="layui-input" type="text"
							placeholder="功能名称" autocomplete="off" lay-verify="searchFunctionName">
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
	</div>
	<div class="layui-btn-group" style="width:490px;">
		<button class="layui-btn  layui-btn-sm layui-btn-primary" data-type="addFunction" data-method="oFunction">添加</button>
		<button class="layui-btn  layui-btn-sm layui-btn-primary" data-type="editFunction" data-method="oFunction">修改</button>
		<button class="layui-btn  layui-btn-sm layui-btn-primary" data-method="delFunction">删除</button>
	</div>

	<table id="functionList"></table>
</body>
</html>