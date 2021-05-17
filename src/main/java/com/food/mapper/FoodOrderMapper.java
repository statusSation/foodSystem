package com.food.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.food.entiy.Food;
import com.food.entiy.OrderItems;
import com.food.entiy.Orders;
import com.food.entiy.Table;

public interface FoodMapper {

	List<Food> findPic();

	void createOrder(@Param("ordNo") String ordNo, @Param("tableNo") String tableNo, @Param("storeNo") String storeNo);

	boolean createOrderItems(@Param("storeNo") String storeNo, @Param("ordNo") String ordNo,
			@Param("itemNo") String itemNo, @Param("count") int count, @Param("sellPrice") float sellPrice,
			@Param("amount") float amount);

	List<Orders> getOrderList(@Param("storeNo") String storeNo, @Param("beginDate") String beginDate,
			@Param("endDate") String endDate, @Param("page") int page, @Param("limit") int limit);

	int getOrderListCount(@Param("storeNo") String storeNo, @Param("beginDate") String beginDate,
			@Param("endDate") String endDate);

	List<OrderItems> getOrderDetailList(@Param("storeNo") String storeNo, @Param("orderNo") String orderNo);

	List<Table> showTableStatus(@Param("storeNo") String storeNo);

	String getItemName(@Param("itemNo") String itemNo);

}
