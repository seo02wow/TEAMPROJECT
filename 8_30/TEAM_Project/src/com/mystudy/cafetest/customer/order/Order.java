package com.mystudy.cafetest.customer.order;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.mystudy.cafetest.dao.MenuDAO;
import com.mystudy.cafetest.vo.MenuVO;

public class Order {

	public static void main(String[] args) {
		Order_Method method = new Order_Method();

		Scanner scan = new Scanner(System.in);
		
//		System.out.println("================= Cafe =================");
//		System.out.println("[1.주문하기 2. 주문 확인 3. 주문 변경 4.회원가입 5.관리자 0.종료]");
//		System.out.print(">> ");
		
		method.startCafe();

	}

}
