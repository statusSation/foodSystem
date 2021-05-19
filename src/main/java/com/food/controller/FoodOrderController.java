package com.food.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.food.entiy.ResponeBody;
import com.food.entiy.Table;
import com.food.service.FoodOrderService;

@RestController
@RequestMapping(value = "/api/foodOrder")
public class FoodOrderController {

	private static final Logger logger = LoggerFactory.getLogger(FoodOrderController.class);

	@Autowired
	private FoodOrderService foodService;

	// 订单新建
	@RequestMapping(value = "createOrder", method = RequestMethod.POST)
	public ResponeBody<JSONObject> createOrder(@RequestParam("storeNo") String storeNo,
			@RequestParam("tableNo") String tableNo, @RequestParam("detail") JSONArray detail) {
		logger.info("createOrder接受参数:storeNo = " + storeNo + " tableNo = " + tableNo + " detail = " + detail);
		ResponeBody<JSONObject> responeBody = new ResponeBody<JSONObject>();
		if ("".equals(storeNo)) {
			responeBody.setCode(201);
			responeBody.setMsg("createOrder(201) 入参错误:店号不能为空");
			logger.error(responeBody.getMsg());
			return responeBody;
		}
		if ("".equals(tableNo)) {
			responeBody.setCode(202);
			responeBody.setMsg("createOrder(202) 入参错误:餐桌号不能为空");
			logger.error(responeBody.getMsg());
			return responeBody;
		}
		if (detail.isEmpty()) {
			responeBody.setCode(203);
			responeBody.setMsg("createOrder(203) 入参错误:订单内容不能为空");
			logger.error(responeBody.getMsg());
			return responeBody;
		}
		return this.foodService.createOrder(storeNo, tableNo, detail);
	}

	// 展示餐桌状态
	@RequestMapping(value = "foodList", method = RequestMethod.GET)
	public ResponeBody<JSONObject> foodList(@RequestBody JSONObject data) {
		ResponeBody<JSONObject> responeBody = new ResponeBody<JSONObject>();
		String storeNo = data.getString("storeNo");
		JSONObject json = new JSONObject();
		// List<Food> pic = this.foodService.findPic();
		List<Table> table = this.foodService.showTableStatus(storeNo);
		json.put("data", table);
		responeBody.setBody(json);
		return responeBody;
	}

	// 查询订单
	@RequestMapping(value = "orderDetail", method = RequestMethod.GET)
	public ResponeBody<JSONObject> ordersDetail(@RequestParam("storeNo") String storeNo,
			@RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate,
			@RequestParam("page") int page, @RequestParam("limit") int limit) {
		logger.info("ordersDetail接受参数:storeNo" + storeNo + " beginDate = " + beginDate + " endDate = " + endDate
				+ " page = " + page + " limit = " + limit);
		ResponeBody<JSONObject> responeBody = new ResponeBody<JSONObject>();
		try {
			if (page == 1) {
				page = 0;
			} else {
				page = (page - 1) * limit;
			}
			if ("".equals(storeNo)) {
				responeBody.setCode(204);
				responeBody.setMsg("orderDetail(204) 入参错误:店号不能为空");
				logger.error(responeBody.getMsg());
				return responeBody;
			}
			return this.foodService.getOrderList(storeNo, beginDate, endDate, page, limit);

		} catch (Exception e) {
			responeBody.setCode(205);
			responeBody.setMsg("orderDetail(205)异常:" + e.getLocalizedMessage());
			logger.error(responeBody.getMsg());
			return responeBody;
		}
	}

	// 订单详情
	@RequestMapping(value = "orderDetailList", method = RequestMethod.GET)
	public ResponeBody<JSONObject> orderDetailList(@RequestParam("storeNo") String storeNo,
			@RequestParam("orderNo") String orderNo) {
		logger.info("orderDetailList接受参数:storeNo = " + storeNo + " orderNo = " + orderNo);
		ResponeBody<JSONObject> responeBody = new ResponeBody<JSONObject>();
		try {
			if ("".equals(storeNo)) {
				responeBody.setCode(206);
				responeBody.setMsg("createOrder(206) 入参错误:店号不能为空");
				logger.error(responeBody.getMsg());
				return responeBody;
			}
			if ("".equals(orderNo)) {
				responeBody.setCode(207);
				responeBody.setMsg("createOrder(207) 入参错误:订单号不能为空");
				logger.error(responeBody.getMsg());
				return responeBody;
			}

			return this.foodService.getOrderDetailList(storeNo, orderNo);
		} catch (Exception e) {
			responeBody.setCode(208);
			responeBody.setMsg("orderDetailList(208)异常:" + e.getLocalizedMessage());
			logger.error(responeBody.getMsg());
			return responeBody;
		}
	}

}
