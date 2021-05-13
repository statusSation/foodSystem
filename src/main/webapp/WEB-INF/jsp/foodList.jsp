<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/layui/css/layui.css">
<script type="text/javascript" src="/layui/layui.js"></script>
</head>
<body>
	<div style="text-align: center; font-size: 15px;">
		<label>桌号:</label><input type="text" name="tableNo" id="tableNo" value="${tableNo }" readonly>
		<input type="text" name="storeNo" id="storeNo" style="display:none;" value="${storeNo }">
	</div>
	<form class="layui-form" autocomplete="off" id="form1">
		<div class="layui-input-inline">
			<c:forEach var="item" items="${img }" varStatus="i">
				<div
					style="float: left; margin-left: 60px; text-align: center; margin-top: 10px;">
					<img alt="" src="${item.pic}" width="150px" height="100px"><br>
					<label>${item.name }</label><br> <label id="price${i.index }">${item.sellPrice }</label><label>元${item.itemNo }</label><br>
					<button type="button"
						class="layui-btn layui-btn-sm layui-btn-primary"
						id="add${item.itemNo }" name="add${item.itemNo }"
						onclick="addCount(this.id)">+</button>
					<input type="text" readonly="readonly" id="Count${item.itemNo }"
						value="0" name="Count${item.itemNo }" style="width: 30px;">
					<button type="button"
						class="layui-btn layui-btn-sm layui-btn-primary"
						name="minus${item.itemNo }" id="minus${item.itemNo }"
						onclick="minusCount(this.id)">-</button>
				</div>
			</c:forEach>
		</div>
		<br> <br>
	</form>
	<div style="text-align: center;">
		<button class="layui-btn" name="insert" onclick="insertOrdFood()">提交</button>
		<button class="layui-btn" name="insert" onclick="insertOrdFoods()">提交</button>
	</div>
</body>
<script type="text/javascript">
	layui
			.use(
					[ 'element', 'form', 'layer' ],
					function() {
						var element = layui.element, $ = layui.jquery, layer = layui.layer, form = layui.form;

						addCount = function(id) {
							//alert($("#Count").val());
							//alert(id.substr(3));
							console.log(id);
							$("#Count" + id.substr(3)).val(parseInt($("#Count" + id.substr(3)).val()) + 1);
						}

						minusCount = function(id) {
							if (parseInt($("#Count" + id.substr(5)).val()) != 0) {
								//console.log(id);
								$("#Count" + id.substr(5)).val(parseInt($("#Count" + id.substr(5)).val()) - 1);
							}
						}
						
						insertOrdFoods = function() {
							layer.open({
								type : 2,
								content : "fPayExpendTest?hhh="+3,
								area: ['350px', '430px']
							});
							
						}
						insertOrdFood = function() {
							
							var arry = $("#form1").serializeArray();
							var list = {};
							var list_arry = [];
							console.log(arry);
							list["tableNo"] = $("#tableNo").val();
							list["storeNo"] = "${storeNo}";
							for (var i = 0; i < arry.length; i++) {
								var obj = {};
								if (arry[i].value != 0) {
									//console.log(arry[i].name.substr(5));
									obj["itemNo"] = arry[i].name.substr(5);
									obj["count"] = arry[i].value;
									obj["sellPrice"] = $("#price"+i).html();
									list_arry.push(obj);
								}
							}
							//var index = layer.load(1); 
							var index = layer.load(2,{
								shade : [ 0.4, 'gray' ],
								content: '<span style="margin-left: -60px;color: black;">生成中订单中</span>',
								success: function (layerContentStyle) {
							        layerContentStyle.find('.layui-layer-content').css({
							            'padding-top': '35px',
							            'margin-top': '-30px',
							            'text-align': 'left',
							            'width': '120px',
							            'font-size': '30px'
							        });
							    }
							},{time: 15 * 1000});
							list["data"] = list_arry;
							if (list_arry.length != 0) {
								console.log(list_arry.length);
								//layer.close(index);
								$.ajax({
									type: 'post',
									url: '/food/createOrder',
									contentType: 'application/json',
									data:JSON.stringify(list),
									success : function(result){
										//console.log(result);
										if(result.code == 0){
											setTimeout(function(){
												layer.closeAll();
											}, 2000);
										}
									}
								});
							}
						}
					});
</script>
</html>