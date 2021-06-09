package com.food.api.controller;

import com.food.api.service.FoodApiLoginService;
import com.food.entiy.ResponeBody;
import com.food.entiy.User;
import com.food.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//@Controller
@RestController
@RequestMapping(value = "/api/login")
public class FoodApiLoginController {
	
	/*
	 * code : 100---199
	 * 
	 * */

	private static final Logger logger = LoggerFactory.getLogger(FoodApiLoginController.class);

	@Autowired
	private FoodApiLoginService foodApiLoginService;

	@Autowired
	public RedisUtil redisUtil;

	@RequestMapping(value = "/loginUsr", method = { RequestMethod.GET })
	public ResponeBody<User> loginUsr(HttpServletRequest request, @RequestParam("storeNo") String storeNo,
			@RequestParam("usrId") String usrId, @RequestParam("password") String password) {
		logger.info("loginUsr接收参数:'storeNo = " + storeNo + " usrId = " + usrId + " password = " + password);
		ResponeBody<User> responeBody = new ResponeBody<>();
		String flag = this.foodApiLoginService.getUsr(storeNo, usrId, password);
		HttpSession session = request.getSession();
		//logger.info("登录前获取的session:"+redisUtil.get(usrId));
		try {
			if ("1".equals(flag)) {
				responeBody.setMsg("登录成功");
				logger.info(responeBody.getMsg());
				// 设置过期时间6小时
				session.setAttribute("loginUserId", usrId);
				//redis没过期就删除之前的把新的更新放进去
				if(redisUtil.getExpire(usrId)) {
					redisUtil.del(usrId);
					redisUtil.set(usrId,session.getId(), 3600);
				}else {
					redisUtil.set(session.getId(),usrId, 3600);
				}
				//System.out.println("获取usrId:"+redisUtil.get(session.getAttribute("loginUserId")));
				//logger.info("登录后获取的session:"+redisUtil.get(usrId));
			} else {
				responeBody.setCode(109);
				responeBody.setMsg("用户名或密码错误");
				logger.info(responeBody.getMsg());
				return responeBody;
			}
		} catch (Exception e) {
			responeBody.setCode(108);
			responeBody.setMsg("loginUsr异常(108):" + e.getLocalizedMessage());
			logger.info(responeBody.getMsg());
			return responeBody;
		}
		return responeBody;
	}
	
	@RequestMapping(value = "/loginOut", method = { RequestMethod.GET })
	public ResponeBody<User> loginOut(HttpServletRequest request) {
		ResponeBody<User> responeBody = new ResponeBody<>();
		logger.info("有访问");
		HttpSession session = request.getSession();
		try {
			if(redisUtil.get(session.getId()) != null) {
				redisUtil.del(session.getId());
			}else {
				responeBody.setCode(110);
				responeBody.setMsg("注销失败,没有登录");
				logger.info(responeBody.getMsg());
				return responeBody;
			}
		} catch (Exception e) {
			responeBody.setCode(111);
			responeBody.setMsg("loginOut异常(111):" + e.getLocalizedMessage());
			logger.info(responeBody.getMsg());
			return responeBody;
		}
		return responeBody;
	}
}
