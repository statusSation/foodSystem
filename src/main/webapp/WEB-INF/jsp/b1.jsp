<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/layui/css/layui.css">
<script type="text/javascript" src="/layui/layui.js"></script>
</head>
<body>
<h1>124125</h1>
	<form class="layui-form" autocomplete="off" id="form1">
		<div class="layui-input-inline">
		<c:forEach var="item" items="${table }" varStatus="i">
		<div id="${i.index }" style="width:100px;height:100px;background-color:red;" onclick="sss(this.id)">
		<label>${item.tableNo }</label> <label>${item.status }</label>
		</div><br>
	</c:forEach>
		</div>
	</form>
</body>
<script type="text/javascript">

/*
 * 
 <div style="float: left; margin-left: 60px; text-align: center; margin-top: 10px;background-color:red;">
	<label>${item.tableNo }</label><br> <label id="${i.index }">${item.status }</label><br>
</div>
 
 */
	
layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function(){
	  var $ = layui.jquery;
	  
		for(var i = 1 ; i <= 10 ; i++){
			$("#father").append("<div style='width:100px;height:100px;background-color:red;float:left;margin-left:20px;' id="+i+" onclick='sss(this.id)'><h1>"+i+"</h1></div>");
		}
		
		var storeNo = '${storeNo}';

		sss = function (tableNo){
			location.href = 'foodList?tableNo='+tableNo+'&storeNo='+storeNo;
		}
		
});


</script>


</html>