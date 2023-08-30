package com.mystudy.cafetest.manager.menu;

import java.util.Scanner;

public class ManagerMenuMain {

	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		System.out.println("==== main() 시작 =======");
		ManagerMenu menu = new ManagerMenu();
		menu.runningManager();
		
		
		System.out.println("==== main() 끝 =======");
	
	}



}