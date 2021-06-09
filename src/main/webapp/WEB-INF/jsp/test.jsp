<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>layout 后台大布局 - Layui</title>
<link rel="stylesheet" href="/layui/css/layui.css">
<script type="text/javascript" src="/layui/layui.js"></script>
<style type="text/css">
.layui-layout-admin .layui-body{
	bottom:0px;
}
</style>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">layui 后台布局</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a href="">控制台</a></li>
				<li class="layui-nav-item"><a href="">商品管理</a></li>
				<li class="layui-nav-item"><a href="">用户</a></li>
				<li class="layui-nav-item"><a href="javascript:;">其它系统</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">邮件管理</a>
						</dd>
						<dd>
							<a href="">消息管理</a>
						</dd>
						<dd>
							<a href="">授权管理</a>
						</dd>
					</dl></li>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="http://t.cn/RCzsdCq" class="layui-nav-img"> 贤心
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">基本资料</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="javascript:;" onclick="loginOut()">退出</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed"><a class=""
						href="javascript:;">所有商品</a>
						<dl class="layui-nav-child">
							<dd>
								<a id="b1" href="javascript:;" onclick="changeFrame(this.id)">点餐桌</a>
							</dd>
							<dd>
								<a id="addFood" href="javascript:;" onclick="changeFrame(this.id)">新增菜品</a>
							</dd>
							<dd>
								<a id="orderInfo" href="javascript:;" onclick="changeFrame(this.id)">订单查询</a>
							</dd>
							<dd>
								<a id="foodList" href="javascript:;" onclick="changeFrame(this.id)">超链接</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">解决方案</a>
						<dl class="layui-nav-child">
							<dd>
								<a id="main" href="javascript:;" onclick="changeFrame(this.id)">列表一</a>
							</dd>
							<dd>
								<a id="sss1" href="javascript:;" onclick="changeFrame(this.id)">列表二</a>
							</dd>
							<dd>
								<a href="">超链接</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="">云市场</a></li>
					<li class="layui-nav-item"><a href="">发布商品</a></li>
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<iframe id="iframeMain" src="" height="100%"
				width="100%"></iframe>
		</div>

	</div>
	<script>
		//JavaScript代码区域
		layui.use('element', function() {
			var element = layui.element,
			$ = layui.jquery;
			
			var user = '${user}';
			var storeNo = '${storeNo}';
			
			changeFrame = function(a){
				$("#iframeMain").attr("src",a);
			}
			
			loginOut = function(){
				
			}
			
		});
	</script>
</body>
</html>