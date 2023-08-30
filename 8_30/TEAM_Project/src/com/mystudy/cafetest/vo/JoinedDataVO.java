package com.mystudy.cafetest.vo;

public class JoinedDataVO {
	private int orderid;
	private int custid;
	private String name;
	private int price;
	private int count;
	private String orderdate;
	private String custname;
	
	
	public JoinedDataVO() {}

	public JoinedDataVO(int orderid, String custname, String name, int price, int count, String orderdate) {
		super();
		this.orderid = orderid;
		this.custname = custname;
		this.name = name;
		this.price = price;
		this.count = count;
		this.orderdate = orderdate;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public JoinedDataVO(int orderid, String name, int price) {
		this.orderid = orderid;
		this.name = name;
		this.price = price;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getCustid() {
		return custid;
	}

	public void setCustid(int custid) {
		this.custid = custid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	@Override
	public String toString() {
		return "JoinedDataVO [orderid=" + orderid + ", name=" + name + ", price=" + price + ", count=" + count
				+ ", orderdate=" + orderdate + ", custname=" + custname + "]";
	}

	
	
}