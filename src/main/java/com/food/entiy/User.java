package com.food.entiy;

public class User{
	/*
	 *   `store_no`      tinyint(4)     default 0                NOT NULL    comment       '店号',
  `name`		  varchar(10)    default '' 			  NOT NULL	  comment		  '名字',
  `usr_id`        int(11)		 default 0				  NOT NULL	  comment		  '用户编号',
  `create_time`   datetime        default now()           NOT NULL    comment       '创建时间',
  `update_time`   datetime        default now()           NOT NULL    comment       '修改时间',  
	 * 
	 * 
	 * */

	/**
	 * 
	 */
	private int storeNo;
	public int getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(int storeNo) {
		this.storeNo = storeNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUsrId() {
		return usrId;
	}
	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}
	private String name;
	private int usrId;
}
