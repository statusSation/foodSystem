package com.food.api.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.food.entiy.Food;
import com.food.entiy.ResponeBody;
import com.food.entiy.Table;

public interface FoodApiOrderService {

	List<Food> findPic();

	ResponeBody<JSONObject> createOrder(String storeNo, String tableNo, JSONArray detail);

	ResponeBody<JSONObject> getOrderList(String storeNo, String beginDate, String endDate, int page, int limit);

	ResponeBody<JSONObject> getOrderDetailList(String storeNo, String orderNo);

	List<Table> showTableStatus(String storeNo);

	String getItemName(String itemNo);

}
