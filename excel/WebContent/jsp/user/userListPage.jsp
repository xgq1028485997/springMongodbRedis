<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<style type="text/css">

</style>
<script>
	layui.use(['form','table','layer'], function(){
		var table = layui.table
		,layer = layui.layer,
		form = layui.form;
		
		var userTable = table.render({
		  id : 'userList'
		  ,elem: '#userList' //或 elem: document.getElementById('test') 等
		  ,url: 'user/getUserList'
		  ,cols:  [[ //标题栏
		    {checkbox: true, LAY_CHECKED: false, fixed:true},
		    {field: 'userId', title: '序号', width: 80 ,templet: '#number'},
		    {field: 'userNo', title: '用户编号', width: 200, sort:true},
		    {field: 'userName', title: '用户姓名', width: 100},
		    {field: 'userSex', title: '性别', width: 60},
		    {field: 'userBirth', title: '出生年月', width: 120, templet: '#userBirth', sort:true},
		    {field: 'userProvince', title: '所在省', width: 100},
		    {field: 'userCity', title: '所在市', width: 100},
		    {field: 'userCounty', title: '所在县', width: 100},
		    {field: 'userAddr', title: '详细地址', width: 200},
		    {field: 'userAddtime', title: '添加时间', width: 120, templet: '#userAddtime', sort:true},
		    {field: 'userTel', title: '联系方式', width: 150},
		    {field: 'userRemark', title: '用户说明', width: 200}
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
		var userActive = {
			oUser : function(othis){
				var type = othis.data('type')
			      ,text = othis.text();
				var checkStatus = table.checkStatus('userList');
				var title = '修改用户';
				if(type == 'addUser'){
					title = '添加用户';
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
			        ,id: 'layerUser_'+type //防止重复弹出
			        ,content: '${pageContext.request.contextPath}/jsp/user/addEditUserPage.jsp'
			        ,anim: 5 //渐显
			        ,resize: false //不能允许拉伸
			        //,shade: 0 //不显示遮罩
			        ,success: function(layero, index){
			        	//层弹出后的成功回调方法
			        	var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法
			        	if(type == 'addUser'){
			        		iframeWin.setValue(null);
						}else{
							iframeWin.setValue(checkStatus.data[0]);
						}
			        	
			        }
			        ,btn: ['提交', '重置']
					,yes: function(index, layero){
			        	var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法
			        	iframeWin.submitUser();
			        	
			        	userActive.searchUser();
			        	
				  	},btn2: function(index, layero){
					  //按钮【按钮二】的回调
					  var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法
			        	iframeWin.resetUser();
					  return false; //开启该代码可禁止点击该按钮关闭
					},cancel: function(index, layero){ 
					  if(confirm('确定要关闭么')){ //只有当点击confirm框的确定时，该层才会关闭
					    layer.close(index);
					  }
					  return false; 
					}
			    });
			},delUser : function(){
				var checkUser = table.checkStatus('userList');
				var params = {};
				if(checkUser.data.length>0){
					var userId = "";
					for(var i = 0 ; i < checkUser.data.length ; i++){
						userId +=  checkUser.data[i].userId + ",";
					}
					if(userId != ""){
						params["userId"] = userId.substring(0,userId.length-1);
					}
					$.ajax({
				        type :'post',
				        async : false,
				        dataType : 'json',
				        data : params,
				        url: "${pageContext.request.contextPath}/user/delUser",
				        success : function(data) {
				        	layer.alert(data.info);
				        	userActive.searchUser();
				        },
				        error : function(data) {
				        	layer.alert(data.info);
						}
		    		}); 
				}else{
					layer.alert("请勾选需要删除的数据");
				}
			},searchUser : function(){
				$("button[name='search']").click();
			}
			
		};
		
		$('.layui-btn').on('click', function(){
		    var othis = $(this), method = othis.data('method');
		    userActive[method] ? userActive[method].call(this, othis) : '';
		  });
		
		//监听提交
		  form.on('submit(search)', function(data){
			  userTable.reload({
					where: { //设定异步数据接口的额外参数，任意设
				    	userNo: data.field.userNo
				    	,userName : data.field.userName
					}
				});
		    return false;
		  });
		
		//自定义验证规则
		form.verify({
		   searchUserName: [/^[\u4E00-\u9FA5A-Za-z0-9]{0,64}$/, '用户编码必须0到64位的中文、字母与数字']
		   ,searchUserNo: [/^[A-Z0-9.-_]{0,32}$/, '用户编码必须0到32位的大写字母、数字、_、.和-']
		});
	});
	
</script>
<script type="text/html" id="userAddtime">
	{{ formartDate('yyyy-MM-dd',d.userAddtime) }}
</script>
<script type="text/html" id="userBirth">
	{{ formartDate('yyyy-MM-dd',d.userBirth) }}
</script>
<script type="text/html" id="number">
	{{d.LAY_INDEX}}
</script>
</head>
<body>
	<div>
		<fieldset class="layui-elem-field site-demo-button">
			<legend style="font-size: 12px;">检索条件</legend>
			<form class="layui-form" action="">
			<div class="layui-form-item"  style="margin-bottom:4px;">
				<div class="layui-inline">
					<label class="layui-form-label">用户编号</label>
					<div class="layui-input-block">
						<input name="userNo" class="layui-input" type="text"
							placeholder="用户编号" autocomplete="off" lay-verify="searchUserNo">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">用户姓名</label>
					<div class="layui-input-block">
						<input name="userName" class="layui-input" type="text"
							placeholder="用户姓名" autocomplete="off" lay-verify="searchUserName">
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
		<button class="layui-btn  layui-btn-sm layui-btn-primary" data-type="addUser" data-method="oUser">添加</button>
		<button class="layui-btn  layui-btn-sm layui-btn-primary" data-type="editUser" data-method="oUser">修改</button>
		<button class="layui-btn  layui-btn-sm layui-btn-primary" data-method="delUser">删除</button>
	</div>

	<table id="userList"></table>
</body>
</html>