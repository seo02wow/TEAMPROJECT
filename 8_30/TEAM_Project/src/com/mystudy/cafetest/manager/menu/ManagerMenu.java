package com.mystudy.cafetest.manager.menu;


import java.util.List;
import java.util.Scanner;

import com.mystudy.cafetest.dao.MenuDAO;
import com.mystudy.cafetest.dao.OrdersdetailDAO;
import com.mystudy.cafetest.dao.SalesDAO;
import com.mystudy.cafetest.vo.JoinedDataVO;
import com.mystudy.cafetest.vo.MenuVO;
import com.mystudy.cafetest.vo.SalesVO;

public class ManagerMenu {
	static Scanner scan = new Scanner(System.in);
	static int menuId;
	static String menuName;
	static int price;
	static int select;
	static int ordersdetailSelect;
	static int salesSelect;
	static int menuSelect;
	static MenuDAO dao = new MenuDAO();
	static OrdersdetailDAO odtDao = new OrdersdetailDAO();
	static SalesDAO salesDao = new SalesDAO();
	static MenuVO menuVO = new MenuVO();
	static List<MenuVO> list = null;
	static List<JoinedDataVO> joinList = null;
	static List<SalesVO> salesList = null;
	
	//--------------------- 관리자 메뉴 운영 --------------------------
	public void runningManager() {
		showManagerMenu(); 
	}
	
	//관리자 메뉴 조회
	public void showManagerMenu () {
		System.out.println("4.주문상세조회 5.메뉴관리");
		System.out.print("> 작업할 메뉴를 선택하세요 : ");
		select = Integer.parseInt(scan.nextLine());
		
		selectManagerMenu();
		
		//작업이 끝나면 처음화면으로 돌아가기 ---> startCafe();
	}
	
	private void selectManagerMenu() {
		//3. 4번 '주문상세조회' 메뉴 선택시
		selectManagerMenu4();
		//3. 5번 '메뉴관리' 메뉴 선택시
		selectManagerMenu5();
	}
	
	//---------------------- 5.메뉴관리 메소드 --------------------------

	private void selectManagerMenu5() {
		if (select == 5) {
			System.out.println("======================");
			System.out.println("::: 5.메뉴관리");
			
			do {
				try {
					System.out.println("======================");
					System.out.println("1.추가 2.수정 3.삭제 4.조회");
					System.out.print("> 처리할 작업을 선택하세요 : ");
					menuSelect = Integer.parseInt(scan.nextLine());
					
					if (menuSelect < 0 || menuSelect > 4) {
						System.out.println("[예외발생] 범위를 벗어난 값입니다. MENUID(1 ~ 4) 입력 가능");
					}
				} catch (NumberFormatException e) {
					System.out.println("[예외발생] 문자입력불가. MENUID(1 ~ 4) 입력 가능");
				} 
			} while (menuSelect < 0 || menuSelect > 4);
			
			if (menuSelect == 1) {
				addMenu(); //메뉴추가
			}
			if (menuSelect == 2) {
				updateMenu(); //메뉴수정
			}
			if (menuSelect == 3) {
				deleteMenu(); //메뉴삭제
			}
			if (menuSelect == 4) {
				selectMenu(); //메뉴조회
			}
		}
	}
	
	//메뉴판 조회
	public void selectMenu() {
		System.out.println("======================");
		System.out.println("::: 조회결과");
		System.out.println("======================");
		showCoffeeMenu();
		
	}
	
	private void showCoffeeMenu() {
		list = dao.selectAll();
		System.out.println("MENUID" + "\t" + "NAME" + "\t" + "PRICE");
		for (MenuVO vo : list) {
			System.out.print(vo.getMenuId() + "\t" + vo.getName()
					+ "\t" + vo.getPrice());
			System.out.println();
		}
		
	}

	//메뉴 삭제
	public void deleteMenu() {
		System.out.println("======================");
		showCoffeeMenu();
		MenuIdExist(); //존재하지않는 id값 삭제하려고 할 때 오류처리, 입력작업반복

		System.out.println("::: " + menuId + "번 메뉴가 삭제되었습니다.");
		menuVO = new MenuVO(menuId);
		dao.delete(menuId);
		
		//처음화면으로 돌아가기 ---> startCafe();
	}
	
	//메뉴 수정
	public void updateMenu() {
		System.out.println("======================");
		showCoffeeMenu();
		System.out.println("----------------------");
		System.out.println("::: 수정할 정보를 입력하세요.");
		
		//MENUID
		MenuIdExist(); //존재하지않는 id값 수정하려고 할 때 오류처리, 입력작업반복

		//MENUNAME
		MenuName();

		//PRICE
		MenuPrice();
		
		//수정완료
		System.out.print("::: 메뉴 수정 완료");
		System.out.println(" [ " + menuId + "." 
					+ menuName + " " 
					+ price + "원 ]");
		
		menuVO = new MenuVO(menuId, menuName, price);
		dao.update(menuVO);
		
		//처음화면으로 돌아가기 ---> startCafe();
	}
	
	//메뉴 추가
	public void addMenu() {
		System.out.println("======================");
		showCoffeeMenu();
		System.out.println("----------------------");
		System.out.println("::: 추가할 정보를 입력하세요.");
		
		//MENUID
		MenuIdDup(); //입력id값이 중복될 때 오류처리, 입력작업반복
		
		//MENUNAME
		MenuName();
		
		//PRICE
		MenuPrice();
		
		//추가작업끝
		System.out.print("::: 메뉴 추가 완료");
		System.out.println(" [ " + menuId + "." 
				+ menuName + " " 
				+ price + "원 ]");
		
		menuVO = new MenuVO(menuId, menuName, price);
		dao.insert(menuVO);
		
	}
	
	//가격입력, 예외처리
	private void MenuPrice() {
		do {
			try {
				System.out.println("----------------------");
				System.out.print("PRICE : ");
				price = Integer.parseInt(scan.nextLine());
				
				if (price < 1 || price > 99999999) {
					System.out.println("[예외발생] 범위를 벗어난 값입니다. 다시 입력하세요.");
				} 
				
			} catch (NumberFormatException e) {
				System.out.println("[예외발생] 문자입력불가. 다시 입력하세요.");
			} 
		} while (price < 1 || price > 99999999);
	}
	
	//메뉴이름 입력, 예외처리
	private void MenuName() {
		do {
			System.out.println("----------------------");
			System.out.print("MENUNAME : ");
			menuName = scan.nextLine();

			if (menuName.isEmpty()) {
				System.out.println("[예외발생] 메뉴 이름을 입력하세요.");
			} else if (!menuName.matches("[a-zA-Z가-힣]+")) {
				System.out.println("[예외발생] 메뉴 이름은 한글과 영어 문자만 입력하세요.");
			}
		} while (menuName.isEmpty() || !menuName.matches("[a-zA-Z가-힣]+"));
		
	}
	
	//메뉴id입력, 예외처리
	private void MenuIdDup() {
		
		boolean result = true;
		while (result) { //true가 될 때까지 반복
			try {
				System.out.println("----------------------");
				System.out.print("MENUID : ");
				menuId = Integer.parseInt(scan.nextLine());

				if (menuId >= 1 && menuId <= 99) {
					result = dao.checkMenuIdExistence(menuId);
					if (result) {
						System.out.println("[예외발생] 중복 id값. 다시 입력하세요.");
					} else {
						break;
					}
				} else {
					System.out.println("[예외발생] 범위를 벗어난 값입니다. MENUID(1 ~ 99) 입력 가능");
				}
			} catch (NumberFormatException e) {
				System.out.println("[예외발생] 문자입력불가. MENUID(1 ~ 99) 입력 가능");
			} 
		}
	}
	
private void MenuIdExist() {
		boolean result = true;
		while (result) { //true가 될 때까지 반복
			try {
				System.out.println("----------------------");
				System.out.print("MENUID : ");
				menuId = Integer.parseInt(scan.nextLine());

				if (menuId >= 1 && menuId <= 99) {
					result = dao.checkMenuIdExistence2(menuId);
					if (result) {
						System.out.println("[예외발생] 존재하지 않는 id값. 다시 입력하세요.");
					} else {
						break;
					}
				} else {
					System.out.println("[예외발생] 범위를 벗어난 값입니다. MENUID(1 ~ 99) 입력 가능");
				}
			} catch (NumberFormatException e) {
				System.out.println("[예외발생] 문자입력불가. MENUID(1 ~ 99) 입력 가능");
			} 
		}
	}

//------------------------ 4.주문상세조회 메소드 -------------------------
private void selectManagerMenu4() {
	if (select == 4) {
		System.out.println("======================");
		System.out.println("::: 4.주문상세조회");
		do {
			try {
				System.out.println("======================");
				System.out.println("1.주문목록조회 2.매출조회");
				System.out.print("> 작업할 메뉴를 선택하세요 : ");
				ordersdetailSelect = Integer.parseInt(scan.nextLine());

				if (ordersdetailSelect < 0 || ordersdetailSelect > 2) {
					System.out.println("[예외발생] 범위를 벗어난 값입니다. MENUID(1 ~ 2) 입력 가능");
				}
			} catch (NumberFormatException e) {
				System.out.println("[예외발생] 문자입력불가. MENUID(1 ~ 2) 입력 가능");
			} 
		} while (ordersdetailSelect < 0 || ordersdetailSelect > 2);

		if (ordersdetailSelect == 1) {
			//1.주문목록조회
			System.out.println("======================");
			System.out.println("::: 주문 전체 데이터 출력");
			System.out.println("주문ID" + "\t" + "고객명" + "\t" + "메뉴명" 
					+ "\t" + "주문수량" + "\t" + "가격" + "\t" + "주문시간");

			joinList = odtDao.selectAll();
			for (JoinedDataVO vo : joinList) {
				System.out.println(vo.getOrderid() + "\t" + 
						vo.getCustname()+ "\t" + vo.getName()
						+ "\t" + vo.getCount() + "\t" + 
						vo.getPrice()  + "\t" + vo.getOrderdate());
			}
		}

		if (ordersdetailSelect == 2) {
			//2.매출조회
			do {
				try {
					System.out.println("======================");
					System.out.println("1.일매출 2.월매출 3.연매출");

					System.out.print("> 작업할 메뉴를 선택하세요 : ");
					salesSelect = Integer.parseInt(scan.nextLine());

					if (salesSelect < 0 || salesSelect > 3) {
						System.out.println("[예외발생] 범위를 벗어난 값입니다. MENUID(1 ~ 3) 입력 가능");
					}
				} catch (NumberFormatException e) {
					System.out.println("[예외발생] 문자입력불가. MENUID(1 ~ 3) 입력 가능");
				} 
			} while (salesSelect < 0 || salesSelect > 3);


			if (salesSelect == 1) {
				//1.일매출
				System.out.println("======================");
				System.out.println("::: 일매출");
				System.out.println("======================");
				System.out.println("연도/월/일" + " "
						+ "\t" + " " + "매출");
				salesList = salesDao.selectDay();
				printvo();
			}

			if (salesSelect == 2) {
				//2.월매출
				System.out.println("======================");
				System.out.println("::: 월매출");
				System.out.println("======================");
				System.out.println("연도/월" + " "
						+ "\t" + "\t" + " " + "매출");
				salesList = salesDao.selectMonth();
				printvo();
			}

			if (salesSelect == 3) {
				//3.연매출
				System.out.println("======================");
				System.out.println("::: 연매출");
				System.out.println("======================");
				System.out.println("연도별" + " "
						+ "\t" + " " + "매출");
				salesList = salesDao.selectYear();
				printvo();
			}
		}
	}


}

private void printvo() {
	for (SalesVO vo : salesList) {
		System.out.println(vo.getOrderDate() + " "
				+ "\t" + " " + vo.getTotalPrice());
	}
}
}
