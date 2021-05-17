package com.food.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.food.entiy.Food;
import com.food.entiy.OrderItems;
import com.food.entiy.Orders;
import com.food.entiy.ResponeBody;
import com.food.entiy.Table;
import com.food.service.FoodService;

@Controller
@RequestMapping(value = "/food")
public class FoodController {
	
	private static final Logger logger = LoggerFactory.getLogger(FoodController.class);
	
	@Autowired
	private FoodService foodService;

	@RequestMapping("index")
	public String index(HttpServletRequest request) {
		//log.info("111");
		//String user = request.getAttribute("now_user").toString();
		//System.out.println(user);
		//request.setAttribute("user", user);
		return "test";
	}

	@RequestMapping(value = "foodList",method = RequestMethod.POST)
	public ResponeBody<JSONObject> foodList(HttpServletRequest request,@RequestBody JSONObject data) {
		ResponeBody<JSONObject> responeBody = new ResponeBody<JSONObject>();
		JSONObject json = new JSONObject();
		List<Food> pic = this.foodService.findPic();
		request.setAttribute("img", pic);
		String storeNo = request.getParameter("storeNo");
		String tableNo = request.getParameter("tableNo");
		//logger.info(storeNo);
		//logger.info(tableNo);
		List<Table> table = this.foodService.showTableStatus(storeNo);
		System.out.println(table.toString());
		json.put("data", table);
		responeBody.setBody(json);
		request.setAttribute("tableNo", request.getParameter("tableNo"));
		request.setAttribute("table", table);
		System.out.println(responeBody);
		return responeBody;
	}
	
	@RequestMapping("createOrder")
	@ResponseBody
	public JSONObject createOrder(@RequestBody String obj) {
		String responseRtn = null;
		JSONObject json_list = JSONObject.parseObject(obj);
		System.out.println(json_list);
		String flag = this.foodService.createOrder(json_list);
	    //BigInteger no = new BigInteger("1000");
	    //BigInteger zero = new BigInteger("0");
	    //BigInteger seq_no = new BigInteger(new SimpleDateFormat("yyyyMMdd").format(new Date())).multiply(no);
	    //BigInteger no2 = new BigInteger("1");
	    boolean isOk;
	    //if (s2.compareTo(zero) == 0) {
	    //  for (int i = 0; i < 1; i++) {
//	        seq_no = seq_no.add(no2);
//	        isOk = this.foodService.insertStockWhitMemo(work_date, seq_no, store_no, type, amount, memo);
//	        jobj.put("code", isOk);
//	      }
//	    } else {
//	      s2 = s2.add(no2);
//	      isOk = this.foodService.insertStockWhitMemo(work_date, s2, store_no, type, amount, memo);
//	      jobj.put("code", isOk);
//	    }
//		json_test.put("code", 0);
	    
		//System.out.println(obj);
	    logger.info(obj);
		json_list.put("code", 0);
		return json_list;
	}

	@RequestMapping("addFood")
	public String addFood() {
		return "addFood";
	}

	@RequestMapping("orderInfo")
	public String orderInfo() {
		return "orderInfo";
	}
	
	@RequestMapping("orderDetail")
	@ResponseBody
	public JSONObject ordersDetail(HttpServletRequest request) {
		String storeNo = request.getParameter("storeNo");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		if (page == 1) {
			page = 0;
		} else {
			page = (page - 1) * limit;
		}
		List<Orders> order = this.foodService.getOrderList(storeNo,beginDate,endDate,page,limit);
		int count = this.foodService.getOrderListCount(storeNo,beginDate,endDate);
		System.out.println(order);
		System.out.println(count);
		
		JSONObject jobj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		if(order.size() != 0) {
			for(int i = 0 ; i < order.size() ; i ++) {
				JSONObject jo = new JSONObject();
				jo.put("storeNo", order.get(i).getStoreNo());
				jo.put("tableNo", order.get(i).getTableNo());
				jo.put("status", order.get(i).getStatus());
				jo.put("orderNo", order.get(i).getOrderNo());
				jo.put("createTime", order.get(i).getCreateTime());
				jsonArray.add(jo);
			}
		}
		jobj.put("code", 0);
		jobj.put("msg", "success");
		jobj.put("count", count);
		jobj.put("data", jsonArray);
		return jobj;
	}
	
	@RequestMapping("orderDetailList")
	@ResponseBody
	public JSONObject orderDetailList(HttpServletRequest request) {
		String storeNo = request.getParameter("storeNo");
		String orderNo = request.getParameter("orderNo");
		List<OrderItems> order = this.foodService.getOrderDetailList(storeNo,orderNo);
		System.out.println(order);
		
		JSONObject jobj = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		if(order.size() != 0) {
			for(int i = 0 ; i < order.size() ; i ++) {
				JSONObject jo = new JSONObject();
				jo.put("name", foodService.getItemName(order.get(i).getItemNo()));
				jo.put("storeNo", order.get(i).getStoreNo());
				jo.put("orderNo", order.get(i).getOrderNo());
				jo.put("itemNo", order.get(i).getItemNo());
				jo.put("amount", order.get(i).getAmount());
				jo.put("qty", order.get(i).getQty());
				jsonArray.add(jo);
			}
		}
		jobj.put("code", 0);
		jobj.put("msg", "success");
		jobj.put("count", order.size());
		jobj.put("data", jsonArray);
		return jobj;
	}
	

	@RequestMapping("b1")
	public String b1(HttpServletRequest request) {
		//String storeNo = request.getParameter("storeNo");
		String storeNo = (String) request.getSession().getAttribute("storeNo");
		System.out.println(storeNo);
		List<Table> table = this.foodService.showTableStatus(storeNo);
		System.out.println(table);
		request.setAttribute("table", table);
		return "b1";
	}

	@RequestMapping("fPayExpendTest")
	public String fPayExpendTest(HttpServletRequest request,@Param(value = "hhh") String hhh) {
		System.out.println(hhh);
		return "fPayExpendTest";
	}


}
