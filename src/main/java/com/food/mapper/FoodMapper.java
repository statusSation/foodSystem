package com.food.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.food.entiy.Food;
import com.food.entiy.OrderItems;
import com.food.entiy.Orders;
import com.food.entiy.Table;

public interface FoodMapper {

	List<Food> findPic();

	void createOrder(@Param("ord_no") String ord_no, @Param("table_no") String table_no);

	boolean createOrderItems(@Param("store_no") String store_no, @Param("ord_no") String ord_no,
			@Param("item_no") String item_no, @Param("count") int count, @Param("sell_price") float sell_price,
			@Param("amount") float amount);

	List<Orders> getOrderList(@Param("storeNo") String storeNo, @Param("beginDate") String beginDate,
			@Param("endDate") String endDate, @Param("page") int page, @Param("limit") int limit);

	int getOrderListCount(@Param("storeNo") String storeNo, @Param("beginDate") String beginDate,
			@Param("endDate") String endDate);

	List<OrderItems> getOrderDetailList(@Param("storeNo") String storeNo, @Param("orderNo") String orderNo);

	List<Table> showTableStatus(@Param("storeNo") String storeNo);

}
