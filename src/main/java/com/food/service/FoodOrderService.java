package com.food.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.food.entiy.Food;
import com.food.entiy.OrderItems;
import com.food.entiy.Orders;
import com.food.entiy.Table;

public interface FoodService {

	List<Food> findPic();

	String createOrder(JSONObject json_list);

	List<Orders> getOrderList(String storeNo, String beginDate, String endDate, int page, int limit);

	int getOrderListCount(String storeNo, String beginDate, String endDate);

	List<OrderItems> getOrderDetailList(String storeNo, String orderNo);

	List<Table> showTableStatus(String storeNo);

	String getItemName(String itemNo);
}
