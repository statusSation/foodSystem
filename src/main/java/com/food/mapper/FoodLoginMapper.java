package com.food.mapper;

import org.apache.ibatis.annotations.Param;

public interface FoodLoginMapper {

	String getUsr(@Param("storeNo") String storeNo, @Param("usrId") String usrId, @Param("password") String password);

}
