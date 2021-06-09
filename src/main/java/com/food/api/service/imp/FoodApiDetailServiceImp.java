package com.food.api.service.imp;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.food.api.mapper.FoodApiDetailMapper;
import com.food.api.service.FoodApiDetailService;
import com.food.entiy.Category;
import com.food.entiy.Food;
import com.food.entiy.ResponeBody;

@Service
public class FoodApiDetailServiceImp implements FoodApiDetailService {

	private static final Logger logger = LoggerFactory.getLogger(FoodApiDetailServiceImp.class);

	@Autowired
	private FoodApiDetailMapper foodApiDetailMapper;

	public ResponeBody<Object> upload(MultipartFile file, String picName, String sellPrice) {
		ResponeBody<Object> responeBody = new ResponeBody<>();
		JSONObject json = new JSONObject();
		try {
			String uuid = String.valueOf(UUID.randomUUID());
			String filename = uuid + ".jpg";// 生成唯一图片路径
			if (!file.isEmpty()) {
				File fileDir = new File("/usr/upload/imgs");
				File filepath = new File(fileDir + "/" + filename);
				if (!fileDir.exists())
					fileDir.mkdirs();
				// 转存文件
				logger.info("保存图片路径:"+filepath);
				file.transferTo(filepath);
				Integer itemNo = this.foodApiDetailMapper.getItemNo();
				itemNo = itemNo + 1;
				this.foodApiDetailMapper.insertPic(picName, itemNo, sellPrice, "/upload/imgs/" + filename);
				json.put("data", filename);
				responeBody.setBody(json);
			}

		} catch (Exception e) {
			responeBody.setCode(400);
			responeBody.setMsg("upload异常(400):" + e.getLocalizedMessage());
			logger.info(responeBody.getMsg());
			return responeBody;
		}
		return responeBody;
	}

	public ResponeBody<Object> getItemDetail() {
		ResponeBody<Object> responeBody = new ResponeBody<>();
		JSONObject json = new JSONObject();
		try {
			List<Food> itemDetail = this.foodApiDetailMapper.getItemDetail();
			List<Category> category = this.foodApiDetailMapper.getCategory();
			json.put("data", itemDetail);
			json.put("category", category);
			responeBody.setBody(json);
		} catch (Exception e) {
			responeBody.setCode(401);
			responeBody.setMsg("getItemDetail异常(401):" + e.getLocalizedMessage());
			logger.info(responeBody.getMsg());
			return responeBody;
		}
		return responeBody;
	}

}
