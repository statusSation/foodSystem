package com.food.entiy;

public class Orders {

	/**
	 * `order_no` String(18) default 0 NOT NULL comment '订单号', `table_no` tinyint(3)
	 * default 0 NOT NULL comment '桌号', `status` tinyint(4) default 0 NOT NULL
	 * comment '状态', `create_time` datetime default now() NOT NULL comment '创建时间',
	 * `update_time` datetime default now() NOT NULL comment '修改时间',
	 */

	private String orderNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	public int getTableNo() {
		return tableNo;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	private String storeNo;
	private int tableNo;
	private String status;
	private String createTime;

}
