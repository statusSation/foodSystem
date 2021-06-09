package com.food.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.food.api.service.WebStocketService;
import com.food.entiy.ResponeBody;
import com.food.entiy.User;

@RestController
@RequestMapping(value = "/api/push")
public class WebStocketController {
	
	/*
	 * code : 300---399
	 * 
	 * */

	private static final Logger logger = LoggerFactory.getLogger(FoodApiLoginController.class);

	@Autowired
	private WebStocketService webStockService;

	@RequestMapping(value = "/toSomeOne", method = { RequestMethod.POST })
	public ResponeBody<User> toSomeOne(HttpServletRequest request, @RequestParam("usrId") String usrId,
			@RequestParam("message") String message) {
		logger.info("toSomeOne接收参数:'usrId = " + usrId);
		ResponeBody<User> responeBody = new ResponeBody<>();
		try {
			if (!webStockService.SendMessageToSomeOne(usrId, message)) {
				responeBody.setCode(300);
				responeBody.setMsg("toSomeOne异常(300):没有找到对应用户的session");
				logger.info(responeBody.getMsg());
				return responeBody;
			}
		} catch (Exception e) {
			responeBody.setCode(301);
			responeBody.setMsg("toSomeOne异常(301):" + e.getLocalizedMessage());
			logger.info(responeBody.getMsg());
			return responeBody;
		}
		return responeBody;
	}

	@RequestMapping(value = "/toEveryone", method = { RequestMethod.POST })
	public ResponeBody<User> toEveryone(HttpServletRequest request, @RequestParam("message") String message) {
		logger.info("toEveryone接收参数:'message = " + message);
		ResponeBody<User> responeBody = new ResponeBody<>();
		try {
			WebStocketService.BroadCastInfo(message);
		} catch (Exception e) {
			responeBody.setCode(302);
			responeBody.setMsg("toEveryone异常(302):" + e.getLocalizedMessage());
			logger.info(responeBody.getMsg());
			return responeBody;
		}
		return responeBody;
	}
}
