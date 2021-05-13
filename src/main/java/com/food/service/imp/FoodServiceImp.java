package com.food.service.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.food.entiy.Food;
import com.food.entiy.OrderItems;
import com.food.entiy.Orders;
import com.food.entiy.Table;
import com.food.mapper.FoodMapper;
import com.food.service.FoodService;
import com.food.utils.RedisUtil;

@Service
public class FoodServiceImp implements FoodService {

	@Autowired
	public FoodMapper foodMapper;
	
	@Autowired
	public RedisUtil redisUtil;

	@Override
	public List<Food> findPic() {
		// TODO Auto-generated method stub
		List<Food> pic = this.foodMapper.findPic();
		return pic;
	}

	@Override
	public String createOrder(JSONObject json_list) {
		// TODO Auto-generated method stub
		JSONArray arry = (JSONArray) json_list.get("data");
		String workDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String ordNo = workDate + System.currentTimeMillis();
		// System.out.println("生成的订单号=" + ordNo);
		// this.foodMapper.createOrder(ordNo);
		if(redisUtil.hasKey("orderNo")) {
			if(redisUtil.getExpire("orderNo")) {
				System.out.println("获取前:"+redisUtil.get("orderNo"));
				redisUtil.incr("orderNo", 1);
				
				System.out.println("更新后:"+redisUtil.get("orderNo"));
			}
		}
		redisUtil.set("orderNo",ordNo);
		String tableNo = json_list.getString("tableNo");
		String storeNo = json_list.getString("storeNo");
		System.out.println("店号:"+storeNo);
		try {
			// System.out.println("service=" + json_list);
			// System.out.println("service=" + arry);
			this.foodMapper.createOrder(ordNo, tableNo, storeNo);
			for (int i = 0; i < arry.size(); i++) {
				JSONObject tmp = new JSONObject();
				tmp = (JSONObject) arry.get(i);
				// String storeNo = tmp.getString("storeNo");
				String itemNo = tmp.getString("itemNo");
				int count = tmp.getIntValue("count");
				float sellPrice = tmp.getFloat("sellPrice");
				float amount = count * sellPrice;
				boolean flag = this.foodMapper.createOrderItems(storeNo, ordNo, itemNo, count, sellPrice, amount);
				System.out.println("插入=" + flag);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Orders> getOrderList(String storeNo, String beginDate, String endDate, int page, int limit) {
		// TODO Auto-generated method stub
		List<Orders> order = this.foodMapper.getOrderList(storeNo, beginDate, endDate, page, limit);
		System.out.println(order);
		return order;
	}

	@Override
	public int getOrderListCount(String storeNo, String beginDate, String endDate) {
		// TODO Auto-generated method stub
		int count = this.foodMapper.getOrderListCount(storeNo, beginDate, endDate);
		return count;
	}

	@Override
	public List<OrderItems> getOrderDetailList(String storeNo, String orderNo) {
		// TODO Auto-generated method stub
		List<OrderItems> order = this.foodMapper.getOrderDetailList(storeNo, orderNo);
		return order;
	}

	@Override
	public List<Table> showTableStatus(String storeNo) {
		// TODO Auto-generated method stub
		List<Table> table = this.foodMapper.showTableStatus(storeNo);
		return table;
	}

	@Override
	public String getItemName(String itemNo) {
		// TODO Auto-generated method stub
		return this.foodMapper.getItemName(itemNo);
	}

}
