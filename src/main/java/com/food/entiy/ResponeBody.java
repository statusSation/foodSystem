package com.food.entiy;

public class ResponeBody<T> {
	
	private int code = 0;
	private T body;
	private String msg = "Success";
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
