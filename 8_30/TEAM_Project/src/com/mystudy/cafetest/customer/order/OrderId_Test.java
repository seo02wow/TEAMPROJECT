package com.mystudy.cafetest.customer.order;

public class OrderId_Test {
	private  int orderId = 0;
	
	public int increaeorderid() {
		++orderId;
		return orderId;
	}

	public static void main(String[] args) {
		OrderId_Test ot = new OrderId_Test();
		
		int first = ot.increaeorderid();
		
		System.out.println(first);
		
		
	}


}
