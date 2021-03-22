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

@Service
public class FoodServiceImp implements FoodService {

	@Autowired
	public FoodMapper foodMapper;

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
		String work_date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String ord_no = work_date + System.currentTimeMillis();
		// System.out.println("生成的订单号=" + ord_no);
		// this.foodMapper.createOrder(ord_no);
		String table_no = json_list.getString("table_no");
		try {
			// System.out.println("service=" + json_list);
			// System.out.println("service=" + arry);
			this.foodMapper.createOrder(ord_no, table_no);
			for (int i = 0; i < arry.size(); i++) {
				JSONObject tmp = new JSONObject();
				tmp = (JSONObject) arry.get(i);
				// String store_no = tmp.getString("store_no");
				String store_no = "1";
				String item_no = tmp.getString("item_no");
				int count = tmp.getIntValue("count");
				float sell_price = tmp.getFloat("sell_price");
				float amount = count * sell_price;
				boolean flag = this.foodMapper.createOrderItems(store_no, ord_no, item_no, count, sell_price, amount);
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
		List<OrderItems> order = this.foodMapper.getOrderDetailList(storeNo,orderNo);
		return order;
	}

	@Override
	public List<Table> showTableStatus(String storeNo) {
		// TODO Auto-generated method stub
		List<Table> table = this.foodMapper.showTableStatus(storeNo);
		return table;
	}

}
