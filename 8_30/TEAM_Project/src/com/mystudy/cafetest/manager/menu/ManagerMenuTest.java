package com.mystudy.cafetest.manager.menu;

import java.util.Scanner;

import com.mystudy.cafetest.dao.OrdersdetailDAO;

public class ManagerMenuTest {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

			
		ManagerMenu menu = new ManagerMenu();
		OrdersdetailDAO dao = new OrdersdetailDAO();
		
		System.out.println("==== main() 시작 =======");

		menu.runningManager();

		System.out.println("==== main() 끝 =======");

	}



}
