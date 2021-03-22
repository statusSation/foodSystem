package com.food.entiy;

public class Store {

	/**
	 * `store_no` tinyint(4) default 0 NOT NULL comment '店号', `name` varchar(20)
	 * default '' NOT NULL comment '店名', `status` tinyint(4) default 0 NOT NULL
	 * comment '状态', `create_time` datetime default now() NOT NULL comment '创建时间',
	 * `update_time` datetime default now() NOT NULL comment '修改时间',
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	private String name;
	private int status;
}
