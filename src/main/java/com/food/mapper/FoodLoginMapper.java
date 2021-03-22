package com.food.mapper;

import org.apache.ibatis.annotations.Param;

public interface FoodLoginMapper {

	String getUsr(@Param("store_no") String store_no, @Param("usr_id") String usr_id,
			@Param("password") String password);

}
