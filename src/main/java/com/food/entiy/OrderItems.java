package com.food.entiy;

import java.math.BigDecimal;
import java.math.BigInteger;

public class OrderItems {
	/**
	 * 
	 * `order_no` bigint(18) default 0 NOT NULL comment '订单号', `sell_price`
	 * varchar(20) default '' NOT NULL comment '售价', `item_no` tinyint(4) default 0
	 * NOT NULL comment '编号', `amount` int(11) default 0 NOT NULL comment '金额',
	 * `qty` int(5) default 0 NOT NULL comment '数量', `create_time` datetime default
	 * now() NOT NULL comment '创建时间', `update_time` datetime default now() NOT NULL
	 * comment '修改时间',
	 */
	private String storeNo;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	public BigInteger getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(BigInteger orderNo) {
		this.orderNo = orderNo;
	}

	public String getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	private BigInteger orderNo;
	private String sellPrice;
	private String itemNo;
	private BigDecimal amount;
	private int qty;
}
