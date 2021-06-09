package com.food.api.service.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.food.api.mapper.FoodApiOrderMapper;
import com.food.api.service.FoodApiOrderService;
import com.food.entiy.Food;
import com.food.entiy.OrderItems;
import com.food.entiy.Orders;
import com.food.entiy.ResponeBody;
import com.food.entiy.Table;
import com.food.utils.RedisUtil;

@Service
public class FoodApiOrderServiceImp implements FoodApiOrderService {

	private static final Logger logger = LoggerFactory.getLogger(FoodApiOrderServiceImp.class);

	@Autowired
	public FoodApiOrderMapper foodApiOrderMapper;

	@Autowired
	public RedisUtil redisUtil;

	@Override
	public List<Food> findPic() {
		List<Food> pic = this.foodApiOrderMapper.findPic();
		return pic;
	}

	@Override
	public ResponeBody<JSONObject> createOrder(String storeNo, String tableNo, JSONArray detail) {
		ResponeBody<JSONObject> responeBody = new ResponeBody<JSONObject>();
		String workDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String ordNo = workDate + storeNo + System.currentTimeMillis();
		/*
		 * if(redisUtil.hasKey("orderNo")) { if(redisUtil.getExpire("orderNo")) {
		 * System.out.println("获取前:"+redisUtil.get("orderNo"));
		 * redisUtil.incr("orderNo", 1);
		 * 
		 * System.out.println("更新后:"+redisUtil.get("orderNo")); } }
		 * redisUtil.set("orderNo",ordNo);
		 */
		try {
			this.foodApiOrderMapper.createOrder(ordNo, tableNo, storeNo);
			for (int i = 0; i < detail.size(); i++) {
				JSONObject tmpVal = new JSONObject();
				tmpVal = (JSONObject) detail.get(i);
				String itemNo = tmpVal.getString("itemNo");
				int count = tmpVal.getIntValue("count");
				Double sellPrice = tmpVal.getDouble("sellPrice");
				Double amount = count * sellPrice;
				this.foodApiOrderMapper.createOrderItems(storeNo, ordNo, itemNo, count, sellPrice, amount);
			}
			return responeBody;

		} catch (Exception e) {
			responeBody.setCode(2001);
			responeBody.setMsg("createOrder(2001)异常:" + e.getLocalizedMessage());
			logger.info(responeBody.getMsg());
			return responeBody;
		}
	}

	@Override
	public ResponeBody<JSONObject> getOrderList(String storeNo, String beginDate, String endDate, int page, int limit) {
		ResponeBody<JSONObject> responeBody = new ResponeBody<JSONObject>();
		try {
			List<Orders> order = this.foodApiOrderMapper.getOrderList(storeNo, beginDate, endDate, page, limit);
			int count = this.foodApiOrderMapper.getOrderListCount(storeNo, beginDate, endDate);
			JSONObject jobj = new JSONObject();
			jobj.put("data", order);
			jobj.put("count", count);
			responeBody.setBody(jobj);
			return responeBody;
		} catch (Exception e) {
			responeBody.setCode(2002);
			responeBody.setMsg("getOrderList(2002)异常:" + e.getLocalizedMessage());
			logger.info(responeBody.getMsg());
			return responeBody;
		}
	}

	@Override
	public ResponeBody<JSONObject> getOrderDetailList(String storeNo, String orderNo) {
		ResponeBody<JSONObject> responeBody = new ResponeBody<JSONObject>();
		try {
			JSONObject jobj = new JSONObject();
			List<OrderItems> order = this.foodApiOrderMapper.getOrderDetailList(storeNo, orderNo);
			for (int i = 0; i < order.size(); i++) {
				order.get(i).setName(foodApiOrderMapper.getItemName(order.get(i).getItemNo()));
			}
			jobj.put("data", order);
			responeBody.setBody(jobj);
			return responeBody;
		} catch (Exception e) {
			responeBody.setCode(2003);
			responeBody.setMsg("getOrderDetailList(2003)异常:" + e.getLocalizedMessage());
			logger.info(responeBody.getMsg());
			return responeBody;
		}
	}

	@Override
	public List<Table> showTableStatus(String storeNo) {
		// TODO Auto-generated method stub
		List<Table> table = this.foodApiOrderMapper.showTableStatus(storeNo);
		return table;
	}

	@Override
	public String getItemName(String itemNo) {
		// TODO Auto-generated method stub
		return this.foodApiOrderMapper.getItemName(itemNo);
	}

}
