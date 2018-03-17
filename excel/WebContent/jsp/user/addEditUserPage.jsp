<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../util/header.jspf"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加或修改用户页面</title>
</head>
<body>
	<div style="margin: 10% 20%; height: 300px;">
		<form class="layui-form" action="#">
			<input type="hidden" name="userId" class="layui-input">
			<div class="layui-form-item">
				<label class="layui-form-label">用户编号</label>
				<div class="layui-input-block">
					<input type="text" name="userNo" disabled autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">用户姓名</label>
				<div class="layui-input-block">
					<input type="text" name="userName" lay-verify="userName"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">密码</label>
				<div class="layui-input-block">
					<input type="password" name="userPassword"
						lay-verify="pass" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">确认密码</label>
				<div class="layui-input-block">
					<input type="password" name="checkPassword"
						lay-verify="checkPass" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">用户性别</label>
				<div class="layui-input-block">
					<input name="userSex" title="男" type="radio" value="男" checked>
					<input name="userSex" title="女" type="radio" value="女">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">出生年月</label>
				<div class="layui-input-block">
					<input class="layui-input" id="userBirth" name="userBirth" type="text" lay-verify="required">
				</div>
			</div>
			<!-- <div class="layui-form-item">
				<label class="layui-form-label">地址</label>
				<div class="layui-input-inline" style="width: 20%;">
					<select name="userProvince"  lay-verify="required" lay-search>
						<option value="">请选择省</option>
						<option value="浙江" selected="">浙江省</option>
						<option value="你的工号">江西省</option>
						<option value="你最喜欢的老师">福建省</option>
					</select>
				</div>
				<div class="layui-input-inline" style="width: 25%;">
					<select name="userCity" lay-verify="required" lay-search>
						<option value="">请选择市</option>
						<option value="杭州">杭州</option>
						<option disabled="" value="宁波">宁波</option>
						<option value="温州">温州</option>
						<option value="温州">台州</option>
						<option value="温州">绍兴</option>
					</select>
				</div>
				<div class="layui-input-inline" style="width: 25%;">
					<select name="userCounty" lay-verify="required" lay-search>
						<option value="">请选择县/区</option>
						<option value="西湖区">西湖区</option>
						<option value="余杭区">余杭区</option>
						<option value="拱墅区">临安市</option>
					</select>
				</div>
			</div> -->
			<div class="layui-form-item">
				<label class="layui-form-label">现住地址</label>
				<div class="layui-input-block">
					<input type="text" name="userAddr" lay-verify="userAddr"
						placeholder="详细地址" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">联系方式</label>
				<div class="layui-input-block">
					<input type="text" name="userTel" lay-verify="userTel"
						placeholder="手机号码" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">证件号码</label>
				<div class="layui-input-block">
					<input type="text" name="userCard" lay-verify="userCard"
						placeholder="身份证号" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">用户说明</label>
				<div class="layui-input-block">
					<input type="text" name="userRemark" lay-verify="userRemark"
						placeholder="" autocomplete="off" class="layui-textarea">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block" hidden>
					<button class="layui-btn" name="submitUser" lay-submit
						lay-filter="submitUser">提交</button>
					<button class="layui-btn" type="reset" name="resetUser">重置</button>
				</div>
			</div>
		</form>

	</div>
</body>

<script type="text/javascript">
	layui.use(['form','laydate'], function() {
		var form = layui.form,
		laydate = layui.laydate;
		//开启公历节日
		laydate.render({
		  elem: '#userBirth'
		  ,trigger: 'click'
		  ,calendar: true
		});

		form.render();
		
		//自定义验证规则
		form.verify({
			userName : [ /^[\u4E00-\u9FA5A-Za-z0-9]{1,64}$/,
					'用户编码必须1到64位的中文、字母与数字' ],
			pass : [ /^[A-Za-z0-9]{1,18}$/, '用户密码必须1到18位的字母与数字' ],
			userAddr: [ /^[\u4E00-\u9FA5A-Za-z0-9]{0,125}$/,'用户编码必须0到125位的中文、字母与数字' ],
			userTel: function(value, item){
				if(isNotNull(value)){
					var re = /^(13[0-9]|14[0-9]|15[0-9]|166|17[0-9]|18[0-9]|19[8|9])\d{8}$/;
					if(!re.test(value)){
						return '非手机号码格式';
					}
				}
			},
			userCard: function(value, item){
				if(isNotNull(value)){
					var re = /^((\d{18})|([0-9x]{18})|([0-9X]{18}))$/;
					if(!re.test(value)){
						return '非身份证号码格式';
					}
				}
			},
			checkPass : function(value, item) {
				if ($("input[name='userPassword']").val() != value) {
					return '再次密码不一致，请核查！';
				}
			}
		});
		//监听提交
		form.on('submit(submitUser)', function(data) {
			//layer.msg(JSON.stringify(data.field));
			$.ajax({
				type : 'post',
				async : false,
				dataType : 'json',
				data : data.field,
				url : "${pageContext.request.contextPath}/user/addOrEditUser",
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
		
	});

	function setValue(obj) {
		if (obj != null) {
			$("input[name='userId']").val(obj.userId);
			$("input[name='userName']").val(obj.userName);
			$("input[name='userNo']").val(obj.userNo);
			$("input[name='userPassword']").val(obj.userPassword);
			$("input[name='checkPassword']").val(obj.userPassword);
			$("input[name='userSex'][value='"+obj.userSex+"']").attr("checked",true);
			$("input[name='userBirth']").val(formartDate('yyyy-MM-dd',obj.userBirth));
			/* $("select[name='userProvince']").val(obj.userProvince);
			$("select[name='userCity']").val(obj.userCity);
			$("select[name='userCounty']").val(obj.userCounty); */
			$("input[name='userAddr']").val(obj.userAddr);
			$("input[name='userTel']").val(obj.userTel);
			$("input[name='userCard']").val(obj.userCard);
			$("input[name='userRemark']").val(obj.userRemark);
		} else {
			resetUser();
		}
	}
	
	function submitUser() {
		$("button[name='submitUser']").click();
	}

	function resetUser() {
		$("button[name='resetUser']").click();
	}
</script>
</html>