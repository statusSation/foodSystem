package com.food.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.food.entiy.Food;
import com.food.entiy.OrderItems;
import com.food.entiy.ResponeBody;
import com.food.entiy.Table;

public interface FoodOrderService {

	List<Food> findPic();

	ResponeBody<JSONObject> createOrder(String storeNo, String tableNo, JSONArray detail);

	ResponeBody<JSONObject> getOrderList(String storeNo, String beginDate, String endDate, int page, int limit);

	List<OrderItems> getOrderDetailList(String storeNo, String orderNo);

	List<Table> showTableStatus(String storeNo);

	String getItemName(String itemNo);

}
