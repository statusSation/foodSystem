package com.food.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.food.entiy.ResponeBody;
import com.food.entiy.User;
import com.food.service.FoodLoginService;

//@Controller
@RestController
@RequestMapping(value = "/api/login")
public class FoodLoginController {

	private static final Logger logger = LoggerFactory.getLogger(FoodLoginController.class);

	@Autowired
	private FoodLoginService foodLoginService;

	@RequestMapping(value = "/loginUsr", method = { RequestMethod.GET })
	public ResponeBody<User> loginUsr(HttpServletRequest request, @RequestParam("storeNo") String storeNo,
			@RequestParam("usrId") String usrId, @RequestParam("password") String password) {
		logger.info("loginUsr接收参数:'storeNo = " + storeNo + " usrId = " + usrId + " password = " + password);
		ResponeBody<User> responeBody = new ResponeBody<User>();
		String flag = this.foodLoginService.getUsr(storeNo, usrId, password);
		if ("1".equals(flag)) {
			responeBody.setMsg("登录成功");
			logger.info("登录成功");
			request.getSession().setAttribute("usrId", usrId);
			request.getSession().setAttribute("storeNo", storeNo);
		} else {
			responeBody.setCode(109);
			responeBody.setMsg("登录失败");
			logger.info("登录失败");
			return responeBody;
		}
		return responeBody;
	}
}
