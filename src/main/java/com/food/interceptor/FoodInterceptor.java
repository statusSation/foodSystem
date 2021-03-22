package com.food.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class FoodInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//System.out.println("==========jin  ru pre  handle ====");
		String user = (String) request.getSession().getAttribute("usrId");
		String storeNo = (String) request.getSession().getAttribute("storeNo");
		request.setAttribute("user", user);
		request.setAttribute("storeNo", storeNo);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//System.out.println("================= jin ru  after ===============");
	}
}
