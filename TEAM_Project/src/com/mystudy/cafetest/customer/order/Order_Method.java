package com.mystudy.cafetest.customer.order;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import com.mystudy.cafetest.dao.CustomerDAO;
import com.mystudy.cafetest.dao.MenuDAO;
import com.mystudy.cafetest.dao.OrdersDAO;
import com.mystudy.cafetest.dao.OrdersdetailDAO;
import com.mystudy.cafetest.dao.StampDAO;
import com.mystudy.cafetest.vo.CartVO;
import com.mystudy.cafetest.vo.CustomerVO;
import com.mystudy.cafetest.vo.MenuVO;
import com.mystudy.cafetest.vo.OrdersVO;
import com.mystudy.cafetest.vo.OrdersdetailVO;
import com.mystudy.cafetest.vo.StampVO;

public class Order_Method {
	static Scanner scan = new Scanner(System.in);
	static MenuDAO menuDao = new MenuDAO();
	static StampDAO stampDao = new StampDAO();
	static MenuVO menuvo = new MenuVO();
	static CustomerVO customerVo = new CustomerVO();
	static OrdersVO ordersVo= new OrdersVO();
	static OrdersdetailVO ordersdetailVo = new OrdersdetailVO();
	static CartVO cartVo= new CartVO();
	static StampVO stampVo= new StampVO();
	static List<CartVO> cartlist = new ArrayList<>();
	static List<StampVO> stamplist = new ArrayList<>();
	static List<MenuVO> list = menuDao.selectAll(); // 메뉴판 표시할 때 VO 리스트에 담아서 데이터 가져옴
	static int selectMenuId =0;
	static int selectPrice = 0;
	static int selectCount = 0;
	static int totPrice = 0;
	static int inMoney = 0;
	static int income = 0;
	static String pointNum = null;
	static int custId = 0;
	static int num = -1;
	//static boolean cafeStop = true;
	
	public static void startCafe () {
		boolean cafeStop = true;
		while (cafeStop) {
			if(num == 0) {
				System.out.println("■ 카페 운영을 종료합니다");
				cafeStop = false;
			}
			num = runningCafe();
			
		}
	}
	// 메인 메뉴 -------------------------------------
	public static void showMenu () {
		System.out.println("================= Cafe =================");
		System.out.println("[고객 - 1.주문하기 2. 주문 확인 3.회원가입]\n[관리자 - 4.주문 상세 조회 5.메뉴 관리 6.회원관리 0.종료]");
		System.out.println("========================================");
		System.out.print(">> ");
		
	}
	
	// 음료 메뉴판-----------------------------------
	public static void showCoffeeMenu () {

	System.out.println("======== 주문하기 ========");
	System.out.println("--------- Menu ---------");
	// 메뉴판 VO로 가져오기 
	for (MenuVO vo : list) {
		System.out.println(vo.getMenuid()+ ". " + vo.getName() + "\t" + vo.getPrice() + "원");
	}
	System.out.println("------------------------");
	
	}
	
	// 카페 운영 -----------------------------------
	public static int runningCafe () {
		// 메인 메뉴 표시
		showMenu();
		
		//int num = -1; // try문 밖에서도 사용하기 위해 임의의 값 넣어줌
		
		try {
			 num = Integer.parseInt(scan.nextLine());
		} catch (InputMismatchException e) { 
			// 숫자가 아닌 데이터(문자) 입력 시 예외 처리
			System.out.println("메뉴 숫자(1 ~ 5)만 입력하세요");
		}
		// 선택 작업
		if (num == 1) {
			coffeeChoice(); 
		} 
		if (num == 2) {
			
		}
		if (num == 3) {
			
		}
		if (num == 4) {
			
		}
		if (num == 5) {
			
		}
		if (num == 6) {
			
		}
		// 종료
		if (num == 0) {
			//return num;
			
		}
		
		return num;
	}
	
	// 커피 메뉴 선택 
	public static void coffeeChoice() {
		showCoffeeMenu();
		//List<CartVO> cartlist = new ArrayList<>();
		boolean continueAdding = true;
		
		while (continueAdding) {
			System.out.println("음료와 수량을 입력하세요");
			System.out.print(">> 음료 : ");
			selectMenuId = Integer.parseInt(scan.nextLine()); // 선택 음료 menuid
			menuvo = menuDao.selectPrice(selectMenuId); // menuid로 가격 조회 
			selectPrice = menuvo.getPrice(); // 고른 메뉴 가격
			
			System.out.print(">> 수량 : ");
			selectCount = Integer.parseInt(scan.nextLine()); // 수량
			totPrice += (selectPrice * selectCount); // 가격 * 수량
			
			
			cartVo= new CartVO(menuvo.getName(),selectCount); // 장바구니 데이터 추가
			cartlist.add(cartVo); // cartVO  추가
//			System.out.println(cartVo.getTotcount()); // 장바구니에서 수량 확인 
			
			System.out.println("장바구니에 담겼습니다.");
			System.out.println("바로 결제 : 1 / 더 담으러 가기 : 2");
			System.out.print(">> ");
			int plusSelect = Integer.parseInt(scan.nextLine());
			if (plusSelect == 1) {
				// 포인트 적립 후 계산 
				System.out.println("포인트 적립을 진행합니다.");
				System.out.println("전화번호를 입력하세요 ex)010-1234-5678");
				System.out.print(">> ");
				pointNum = scan.nextLine(); 
				
				// 전화번호에 해당하는 customerVO,custid 가져오기
				customerVo = CustomerDAO.selectCustId(pointNum);
				custId = customerVo.getCustid();
				System.out.println("포인트 적립 예정 id : " + custId);
				
				StampDAO.update(pointNum, selectCount); // 포인트 적립 메소드 (pointNum, selectCount 때문에 메소드 안 나눔) 
				
				insertOrders(selectMenuId,selectCount); // insert - orders,ordersdetail,sales
				
				// 스탬프 적립 후 보유 스탬프 확인하기 : 전화번호를 통해 stampvo에서 스탬프 개수 가져옴
				stampVo = StampDAO.selectStampCnt(pointNum);
				String stampId = stampVo.getStampId();
				int stampcnt = stampVo.getStampCnt();
				System.out.println("스탬프 id : " + stampId);
				System.out.println("적립 후 보유 스탬프 : " + stampcnt);
				payment();
				continueAdding = false;
			}
			if (plusSelect == 2) {
				coffeeChoice();
			}
			}
		}		
	
	
	// 장바구니 출력 
	public static void printCart(List<CartVO> cartlist) {
		cartVo= new CartVO(menuvo.getName(),selectCount);
		System.out.println("===== 나의 장바구니 내역 ====="); 
		for (CartVO cartvo : cartlist) {
			System.out.println("음료 : " + cartvo.getMenuname()
								+ "  수량 : " + cartvo.getDrinkcount());
		} 
		System.out.println("■ 총 금액 : " + totPrice);
		System.out.println("=========================="); 
	}
	
	// 결제 메소드 
	public static void payment() {
		printCart(cartlist); // 장바구니 내역 출력 
		System.out.println("■ 결제를 진행합니다.");
		System.out.print(">> 입금액 : ");
		inMoney = Integer.parseInt(scan.nextLine());
		int change = inMoney - totPrice; // 잔액
		
		if (change > 0) {
			System.out.println("■ 잔액을 가져가세요");
			System.out.println("잔액 : " + change);
		} 
		System.out.println("주문이 완료되었습니다.");
		// 판매금액 누적 (매출액)
		income += totPrice;
		startCafe (); // 주문 완료 후 처음 메인화면으로 돌아감 
	}
	
	public static void insertOrders(int selectMenuId, int selectCount) { // insert - Orders,ordersdetail,sales
		// orderid, custid, totalprice 넣으면 됨
		int orderId = (int)(Math.random() * 9999 + 1); // 1부터 9999까지의 랜덤값
		
		HashSet<Integer> ran = new HashSet<Integer>() ;
		for( ; ran.size() < 100 ; ) { // 100개 생성 
			int ranOrderId = (int)(Math.random() * 9999 + 1);
			ran.add(ranOrderId);
		}
		
		
		
		
		ordersVo = new OrdersVO(orderId, custId, totPrice);
		OrdersDAO.insert(ordersVo); 
	
		
		//MENUID,ORDERID,COUNT - menuid, count
		System.out.println("ordersdetailVo 넣을 데이터: " + selectMenuId+ " ," + orderId + " ," + selectCount);
		ordersdetailVo = new OrdersdetailVO(selectMenuId,orderId,selectCount);
		OrdersdetailDAO.insert(ordersdetailVo);
		
	}
	
	

}
