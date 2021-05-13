<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
<link rel="stylesheet" href="/layui/css/layui.css">
<script type="text/javascript" src="/layui/layui.js"></script>
<script type="text/javascript" src="/layui/jquery.autocomplete.js"></script>
</head>
<body>
	<div style="margin: 200px auto; text-align: center;">
		<div class="layui-input-inline">
			<label>店别：</label><input type="text" name="storeNo" id="storeNo"
				autocomplete="off">
		</div>
		<br> <br>
		<div class="layui-input-inline">
			<label>用户名：</label><input type="text" name="usrId" id="usrId"
				autocomplete="off">
		</div>
		<br> <br>
		<div class="layui-input-inline">
			<label>密码：</label><input type="password" name="password"
				id="password" autocomplete="off">
		</div>
		<br> <br>
		<div>
			<button class="layui-btn" name="login" onclick="login()">登录</button>
		</div>
	</div>

</body>
<script type="text/javascript">
	layui.use([ 'element', 'form', 'layer' ],function() {
						var element = layui.element, $ = layui.jquery, layer = layui.layer, form = layui.form;

						login = function() {
							var data = {"storeNo":$("#storeNo").val(),"usrId":$("#usrId").val(),"password":$("#password").val()};
							$.ajax({
								type : 'post',
								url : '/login/loginUsr',
								contentType : 'application/json',
								dataType : 'json',
								data: JSON.stringify(data),
								success : function(result) {
									//console.log(result);
									if (result.code == 0) {
										layer.msg("登录成功");
										window.location.href = "main";
									} else {
										layer.msg("用户名或密码错误！");
									}
								}
							})
						}
					});
</script>
</html>