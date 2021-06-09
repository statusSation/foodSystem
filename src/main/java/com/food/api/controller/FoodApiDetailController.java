package com.food.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.food.api.service.FoodApiDetailService;
import com.food.entiy.ResponeBody;

@RestController
@RequestMapping(value = "/api/foodDetail")
public class FoodApiDetailController {

	/*
	 * code : 400---499
	 * 
	 */

	private static final Logger logger = LoggerFactory.getLogger(FoodApiDetailController.class);

	@Autowired
	private FoodApiDetailService foodApiDetailService;

	@RequestMapping(value = "/upload", method = { RequestMethod.POST })
	public ResponeBody<Object> upload(HttpServletRequest request, @RequestParam("file") MultipartFile file,
			@RequestParam("picName") String picName, @RequestParam("sellPrice") String sellPrice) {
		logger.info("upload接收参数:picName=" + picName + " sellPrice=" + sellPrice);
		return foodApiDetailService.upload(file, picName, sellPrice);
	}

	@RequestMapping(value = "/getItemDetail", method = { RequestMethod.GET })
	public ResponeBody<Object> getItemDetail(HttpServletRequest request) {
		//logger.info("getItemDetail接收参数:storeId=" + storeId);
		return foodApiDetailService.getItemDetail();
	}

}
