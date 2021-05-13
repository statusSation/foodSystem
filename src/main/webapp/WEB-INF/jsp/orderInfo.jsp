<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/layui/css/layui.css" />
<link rel="stylesheet" href="/layui/css/styles.css" />
<script type="text/javascript" src="/layui/layui.js"></script>
<style type="text/css">
	.water {
      width: 100vw;
      height: 2000px;
      position: absolute;
      top: 0;
      left: 0;
      background-repeat: no-repeat;
    }
    .content {
      width: 800px;
      height: 2000px;
      margin-left: auto;
      margin-right: auto;
      background: cadetblue;
      overflow: hidden;
    }
</style>
</head>
<body>
	<div class="pager-form">
		<form class="layui-form form_across" action="" id="myForm">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">查询时间：</label>
					<div class="layui-input-inline with-date-icon">
						<input type="text" name="beginDate"
							id="beginDate" placeholder="yyyy-MM-dd"
							autocomplete="off" readonly class="layui-input"> <i
							class="ui-icon icon-date" style="font-size: 30px;"></i>
					</div>
					<div class="layui-input-inline with-date-icon">
						<input type="text" name="endDate" id="endDate"
							placeholder="yyyy-MM-dd" autocomplete="off" readonly
							class="layui-input"> <i class="ui-icon icon-date"
							style="font-size: 30px;"></i>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">订单号：</label>
					<div class="layui-input-inline">
						<input type="text" name="orderNo" id="orderNo"
							autocomplete="off" placeholder="" class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="text-align: right;">
				<div class="layui-inline">
					<button class="layui-btn" type="button" onclick="resetButton()">重置</button>
				</div>
				<div class="layui-inline ml_xl">
					<button class="layui-btn" type="button" onclick="search()">查询</button>
				</div>
			</div>
		</form>
		<div class="layui-inline ml_xl">
			<button class="layui-btn" type=button id="recive" onclick="collection()">收款</button>
		</div>
		<div class="layui-inline ml_xl">
			<button class="layui-btn" type=button id="download" onclick="download()">下载</button>
		</div>
	</div>
	<div class="pager-table">
		<table class="layui-hide layui-table-radius" id="table1" lay-filter="table1"></table>
	</div>
	<div id="demoDetailDiv1" style="display: none; padding: 10px;">
		<div id="detailDiv1">
		</div>
	</div>
</body>

<script id="detail" type="text/html">
	<button lay-event="orderDetail">详情</button>
    
</script>

<script id="orderDetail" type="text/html">
<table class="layui-table" id="table2" lay-filter="tabl2">
            <thead>
                <tr>
                    <th>菜名</th>
                    <th>货号</th>
                    <th>价格</th>
                    <th>数量</th>
                </tr>
            </thead>
            <tbody>
                {{# layui.each(d.data,function(index,r){ }}
                <tr>
                    <td>{{r.name}}</td>
                    <td>{{r.itemNo}}</td>
                    <td>{{r.amount}}</td>
                    <td>{{r.qty}}</td>
                </tr>
                {{# }); }}
            </tbody>
        </table>
		<button class="layui-btn" type="button"
					onclick="downloadData()">打印</button>
		<button class="layui-btn" type="button"
					onclick="closeButton()" style="margin-left: 120px;">返回</button>
</script>

<script type="text/javascript">
layui.config({
    base:'/layui/js/'
});
layui.use(['form', 'table', 'laydate','layer','laytpl','getTime'], () => {
	
	var form = layui.form, $ = layui.jquery, layer = layui.layer, table = layui.table, 
	laydate = layui.laydate, laytpl = layui.laytpl, getTime = layui.getTime;
	
	//var now = getNow();
	//shuiyin.watermark({"watermark_txt":"A B C X Y Z "+now});
	
	table.render({
		elem: '#table1',
		url: '',
		data: [],
		cols: [
			[
				{field: 'storeNo',title: '店别',align: 'center'},
				{field: 'tableNo',title: '桌号',align: 'center'},
				{field: 'orderNo',title: '订单号',align: 'center'},
				{field: 'status',title: '状态',align: 'center'},
				{field: 'createTime',title: '生成时间',align: 'center'},
				{field: 'flag',title: '操作',align: 'center',templet: '#detail'}
			]
		],
		page: true,
		limits: [20,50,100],
		limit: 20
	});
	
	/*
	var can = document.createElement('canvas');
	var body = document.body;
	body.appendChild(can);
	can.width=400;
	can.height=200;
	can.style.display='none';
	var cans = can.getContext('2d');
	//ctx.translate(-60, 0);//移动坐标原点
	cans.rotate(-20*Math.PI/180);
	cans.font = "16px Microsoft JhengHei";
	cans.fillText("阿斯顿发送到!",can.width/3,can.height/1);
	body.style.backgroundImage="url("+can.toDataURL("image/png")+")";
	*/
	
	var startDate1 = laydate.render({
		elem: '#beginDate',
		type: 'date',
		value: getTime.getRecentDay(-30),
		done: function(value, date, endDate) {
			
		}
	});
	var endDate1 = laydate.render({
		elem: '#endDate',
		type: 'date',
		value: getTime.getRecentDay(0),
		done: function(value, date, endDate) {
			
		}
	});
	
	table.on('tool(table1)', function (obj) {
		var data = obj.data;
		console.log(data);
		if(obj.event === 'orderDetail'){
        	index = layer.open({
                title: '详情',
                type: 1,
                content: $('#demoDetailDiv1'),
                area: ['600px', '200px'],
                success : function(){
                	 
                }
            });
        	//obj.data是可以更换的
            var demoDetailTpl = orderDetail.innerHTML, //获取模板，
              detailDiv = document.getElementById('detailDiv1');  //视图
              $.ajax({
            	  type: 'post',
					url: '/food/orderDetailList',
					data: {
						"storeNo" : data.storeNo,
						"orderNo" : data.orderNo
					},
					success : function(result) {
						console.log(result);
						laytpl(demoDetailTpl).render(result, function (html) { //渲染视图
							//console.log(tableData2);
			                  detailDiv.innerHTML = html;
			            });
					}
              })
        }
		
	})
	
	var beginDate;
	var endDate;
	search = function(){
		beginDate = $("#beginDate").val();
		endDate = $("#endDate").val();
		table.reload('table1',{
			url:'/food/orderDetail',
			method:'post',
			where : {
				'storeNo' : 1,
				'beginDate' : beginDate,
				'endDate' : endDate
			},
			page: {
			    curr: 1 //重新从第 1 页开始
			},
			parseData: function(res){ //res 即为原始返回的数据
				console.log(res);
			/*
			return {
			      "code": res.rsCode,
			      "msg": res.message, 
			      "count": res.paging.totalCnt,
			      "data": res.body
			    };
			*/
			    
			  }
		});
	}
	
	closeButton = function(){
		layer.closeAll();
	}
	
})
</script>
</html>