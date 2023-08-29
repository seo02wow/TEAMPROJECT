package com.mystudy.cafetest.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.mystudy.cafetest.dao.MenuDAO;
import com.mystudy.cafetest.vo.CustomerVO;
import com.mystudy.cafetest.vo.MenuVO;
import com.mystudy.cafetest.vo.StampVO;


public class CafetestDAO_Test {

	public static void main(String[] args) {
		HashSet<Integer> ran = new HashSet<Integer>() ;
		for( ; ran.size() < 100 ; ) { // 100개 생성 
			int ranOrderId = (int)(Math.random() * 9999 + 1);
			ran.add(ranOrderId);
		}
		
		// 100개의 랜덤값이 있는 리스트 -> 여기서 하나씩 꺼내어 orderid 적용 ?
		ArrayList<Integer> ranlist = new ArrayList<Integer>(ran);
		System.out.println(ranlist);  
		
		Iterator<Integer> iterator = ranlist.iterator();
		while (iterator.hasNext()) {
		    int orderId = iterator.next();
		    System.out.println("Using orderId: " + orderId);
		}
		
//		MenuDAO dao = new MenuDAO();
//		List<MenuVO> list = dao.selectAll();
//		
//		StampDAO stampdao = new StampDAO();
//		
//		List<StampVO> stampList = stampdao.selectAll();
//		
//		for (StampVO vo : stampList) {
//			System.out.print(vo);
//		}
//		
//		System.out.println("메뉴 가격 조회");
//		int selectMenuId = 1;
//		MenuVO stu = dao.selectPrice(selectMenuId);
//		int selectPrice = stu.getPrice();
//		System.out.println("선택 메뉴 가격 : " + selectPrice);
//		
//		System.out.println();
		// 단순 조회
//		for (MenuVO vo : list) {
//			System.out.print(vo);
//		}
		
//		for (MenuVO vo : list) {
//		System.out.println(vo.getName() + "\t" + "\t" + vo.getPrice() );
//	}
		
//		System.out.println("--- insert(vo) ---");
//		MenuVO memu = new MenuVO(4, "블루베리 스무디", 6500);
//		int insertCount = dao.insert(memu);
//		System.out.println("입력 건수 : " + insertCount);
//		
//		System.out.println("--- update(vo) ---");
//		memu = new MenuVO(4, "딸기 스무디", 6000);
//		int updateCount = dao.update(memu);
//		System.out.println("입력 건수 : " + updateCount);
//		
//		System.out.println("--- delete(menuid) ---");
//		System.out.println("삭제할 MENUID : " );

	}

}
