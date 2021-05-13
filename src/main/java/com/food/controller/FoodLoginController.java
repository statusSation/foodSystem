package com.food.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.food.entiy.ResponeBody;
import com.food.entiy.User;
import com.food.service.FoodLoginService;

@Controller
//@RestController
@RequestMapping(value = "/login")
public class FoodLoginController {

	private static final Logger logger = LoggerFactory.getLogger(FoodLoginController.class);

	@Autowired
	private FoodLoginService foodLoginService;

	@RequestMapping("index")
	public String index() {
		// log.info("111");
		return "login";
	}

	@RequestMapping("main")
	public String main() {
		return "redirect:/food/index";
	}

	@RequestMapping(value = "/loginUsr", method = { RequestMethod.POST })
	@ResponseBody
	public ResponeBody<User> loginUsr(@RequestBody JSONObject data) {
		ResponeBody<User> responeBody = new ResponeBody<User>();
		String storeNo = data.getString("storeNo");
		String usrId = data.getString("usrId");
		String password = data.getString("password");
		//request.getSession().setAttribute("usrId", usrId);
		String flag = this.foodLoginService.getUsr(storeNo, usrId, password);
		if ("1".equals(flag)) {
			logger.info("登录成功");
			//request.getSession().setAttribute("storeNo", storeNo);
		}
		responeBody.setBody(null);
		return responeBody;
	}
}
