package com.food.api.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.api.mapper.FoodApiLoginMapper;
import com.food.api.service.FoodApiLoginService;

@Service
public class FoodApiLoginServiceImp implements FoodApiLoginService{

	@Autowired
	private FoodApiLoginMapper foodApiLoginMapper;
	
	@Override
	public String getUsr(String store_no, String usr_id, String password) {
		String flag = this.foodApiLoginMapper.getUsr(store_no,usr_id,password);
		return flag;
	}

}
