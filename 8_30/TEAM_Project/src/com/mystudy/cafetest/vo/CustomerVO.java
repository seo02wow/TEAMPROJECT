package com.mystudy.cafetest.vo;

public class CustomerVO {
	private int custid;
	private String custname;
	private String password;
	private String phone;
	
	public CustomerVO() {}
	
	public CustomerVO(int custid, String custname, String password, String phone) {
		super();
		this.custid = custid;
		this.custname = custname;
		this.password = password;
		this.phone = phone;
	}

	public int getCustid() {
		return custid;
	}

	public void setCustid(int custid) {
		this.custid = custid;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "CustomerVO [custid=" + custid + ", custname=" + custname + ", password=" + password + ", phone=" + phone
				+ "]";
	}
	
	
}