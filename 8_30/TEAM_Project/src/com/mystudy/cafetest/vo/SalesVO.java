package com.mystudy.cafetest.vo;

public class SalesVO {
	protected int orderNum;
	protected int orderId;
	protected int count;
	protected int totalPrice;
	protected String orderDate;
	
	public SalesVO() {}
	
	public SalesVO(int totalPrice, String orderDate) {
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
	}
	
	public SalesVO(int orderNum, int orderId, int count, int totalPrice, String orderDate) {
		super();
		this.orderNum = orderNum;
		this.orderId = orderId;
		this.count = count;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
	
}
