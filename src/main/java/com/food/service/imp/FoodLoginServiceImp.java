package com.food.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.mapper.FoodLoginMapper;
import com.food.service.FoodLoginService;

@Service
public class FoodLoginServiceImp implements FoodLoginService{

	@Autowired
	private FoodLoginMapper foodLoginMapper;
	
	@Override
	public String getUsr(String store_no, String usr_id, String password) {
		String flag = this.foodLoginMapper.getUsr(store_no,usr_id,password);
		return flag;
	}

}
