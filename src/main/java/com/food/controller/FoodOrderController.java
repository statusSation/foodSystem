package com.food.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.food.entiy.OrderItems;
import com.food.entiy.ResponeBody;
import com.food.entiy.Table;
import com.food.service.FoodOrderService;

@RestController
@RequestMapping(value = "/api/foodOrder")
public class FoodOrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(FoodOrderController.class);
	
	@Autowired
	private FoodOrderService foodService;

	//订单新建
	@RequestMapping(value = "createOrder",method = RequestMethod.POST)
	public ResponeBody<JSONObject> createOrder(@RequestBody JSONObject data) {
		logger.info("createOrder接受参数:"+data);
		ResponeBody<JSONObject> responeBody = new ResponeBody<JSONObject>();
		String storeNo = data.getString("storeNo");
		String tableNo = data.getString("tableNo");
		JSONArray detail = data.getJSONArray("detail");
		if("".equals(storeNo)) {
			responeBody.setCode(201);
			responeBody.setMsg("createOrder(201) 入参错误:店号不能为空");
			logger.error(responeBody.getMsg());
			return responeBody;
		}
		if("".equals(tableNo)) {
			responeBody.setCode(202);
			responeBody.setMsg("createOrder(202) 入参错误:餐桌号不能为空");
			logger.error(responeBody.getMsg());
			return responeBody;
		}
		if(detail.isEmpty()) {
			responeBody.setCode(203);
			responeBody.setMsg("createOrder(203) 入参错误:订单内容不能为空");
			logger.error(responeBody.getMsg());
			return responeBody;
		}
		return this.foodService.createOrder(storeNo,tableNo,detail);
	}
	
	//展示餐桌状态
	@RequestMapping(value = "foodList",method = RequestMethod.POST)
	public ResponeBody<JSONObject> foodList(@RequestBody JSONObject data) {
		ResponeBody<JSONObject> responeBody = new ResponeBody<JSONObject>();
		String storeNo = data.getString("storeNo");
		JSONObject json = new JSONObject();
		//List<Food> pic = this.foodService.findPic();
		List<Table> table = this.foodService.showTableStatus(storeNo);
		json.put("data", table);
		responeBody.setBody(json);
		return responeBody;
	}

	
	@RequestMapping(value = "orderDetail",method = RequestMethod.POST)
	public ResponeBody<JSONObject> ordersDetail(@RequestBody JSONObject data) {
		logger.info("ordersDetail接受参数:"+data);
		ResponeBody<JSONObject> responeBody = new ResponeBody<JSONObject>();
		try {
		String storeNo = data.getString("storeNo");
		String beginDate = data.getString("beginDate");
		String endDate = data.getString("endDate");
		int page = data.getInteger("page");
		int limit = data.getInteger("limit");
		if (page == 1) {
			page = 0;
		} else {
			page = (page - 1) * limit;
		}
		if("".equals(storeNo)) {
			responeBody.setCode(204);
			responeBody.setMsg("orderDetail(204) 入参错误:店号不能为空");
			logger.error(responeBody.getMsg());
			return responeBody;
		}
		return this.foodService.getOrderList(storeNo, beginDate, endDate, page, limit);
		
		} catch (Exception e) {
			responeBody.setCode(205);
			responeBody.setMsg("orderDetail(205)异常:"+e.getLocalizedMessage());
			logger.error(responeBody.getMsg());
			return responeBody;
		}
	}
	
	@RequestMapping("orderDetailList")
	@ResponseBody
	public JSONObject orderDetailList(HttpServletRequest request) {
		String storeNo = request.getParameter("storeNo");
		String orderNo = request.getParameter("orderNo");
		List<OrderItems> order = this.foodService.getOrderDetailList(storeNo,orderNo);
		System.out.println(order);
		
		JSONObject jobj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		if(order.size() != 0) {
			for(int i = 0 ; i < order.size() ; i ++) {
				JSONObject jo = new JSONObject();
				jo.put("name", foodService.getItemName(order.get(i).getItemNo()));
				jo.put("storeNo", order.get(i).getStoreNo());
				jo.put("orderNo", order.get(i).getOrderNo());
				jo.put("itemNo", order.get(i).getItemNo());
				jo.put("amount", order.get(i).getAmount());
				jo.put("qty", order.get(i).getQty());
				jsonArray.add(jo);
			}
		}
		jobj.put("code", 0);
		jobj.put("msg", "success");
		jobj.put("count", order.size());
		jobj.put("data", jsonArray);
		return jobj;
	}


}
