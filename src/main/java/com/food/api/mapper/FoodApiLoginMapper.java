package com.food.api.mapper;

import org.apache.ibatis.annotations.Param;

public interface FoodApiLoginMapper {

	String getUsr(@Param("storeNo") String storeNo, @Param("usrId") String usrId, @Param("password") String password);

}
