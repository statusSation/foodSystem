package com.food.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.food.service.FoodLoginService;

@Controller
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
	
	@RequestMapping("loginUsr")
	@ResponseBody
	public String loginUsr(HttpServletRequest request) {
		String storeNo = request.getParameter("storeNo");
		String usrId = request.getParameter("usrId");
		String password = request.getParameter("password");
		request.getSession().setAttribute("usrId", usrId);
		String flag = this.foodLoginService.getUsr(storeNo,usrId,password);
		if(flag.equals("1")) {
			logger.info("登录成功");
			request.getSession().setAttribute("storeNo", storeNo);
		}
		return flag;
	}
}
