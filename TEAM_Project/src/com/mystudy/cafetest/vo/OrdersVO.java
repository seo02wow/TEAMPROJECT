package com.mystudy.cafetest.vo;

public class OrdersVO {
	private int orderId;
	private int custId;
	private int totalPrice;
	
	public OrdersVO() {}
	
	public OrdersVO(int orderId, int custId, int totalPrice) {
		super();
		this.orderId = orderId;
		this.custId = custId;
		this.totalPrice = totalPrice;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "OrdersVO [orderId=" + orderId + ", custId=" + custId + ", totalPrice=" + totalPrice + "]";
	}
	
	
	
	
}
