package com.food.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.food.utils.RedisUtil;

public class FoodInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	public RedisUtil redisUtil;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		HttpSession session = request.getSession();
		//ResponeBody<JSONObject> responeBody = new ResponeBody<>();
		//System.out.println("拦截器1:" + session.getAttribute("loginUserId"));
		//System.out.println("拦截器2:" + redisUtil.get(session.getAttribute("loginUserId")));
		JSONObject json = new JSONObject();
		//没有在redis里的都需要重新登录
		if (session.getAttribute("loginUserId") != null) {
			String loginSessionId = (String) redisUtil.get(session.getAttribute("loginUserId"));
			if (loginSessionId != null && loginSessionId.equals(session.getId())) {
				//setReturn(response,400,"用户未登录，请先登录");
				return true;
			} else {
				json.put("code", 600);
				json.put("msg", "用户未登录，请先登录");
				response.setCharacterEncoding("utf-8");
		        response.setContentType("application/json; charset=utf-8");
		        PrintWriter writer = response.getWriter();
		        writer.write(json.toString());
				return false;
			}
		}

		return false;
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// System.out.println("================= jin ru after ===============");
	}


}
