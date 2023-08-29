package com.mystudy.cafetest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.mystudy.cafetest.vo.OrdersVO;
import com.mystudy.cafetest.vo.OrdersdetailVO;
import com.mystudy.cafetestcommon.CommonJDBCUtil;

public class OrdersdetailDAO {
	/*
	MENUID NUMBER(2)REFERENCES MENU(MENUID),
    ORDERID NUMBER(2)REFERENCES ORDERS(ORDERID),
    COUNT NUMBER (2) NOT NULL
	 */

	public static int insert (OrdersdetailVO vo) {
		int count = 0;
		
		Connection conn =null;
		PreparedStatement pstmt = null;
		
		try {
			conn = CommonJDBCUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO ORDERSDETAIL ");
			sql.append("	(MENUID, ORDERID, COUNT) ");
			sql.append(" VALUES (?, ?, ?)");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			int i = 1;
			pstmt.setInt(i++, vo.getMenuId());
			pstmt.setInt(i++, vo.getOrderId());
			pstmt.setInt(i++, vo.getCount());
			
			count = pstmt.executeUpdate();
			System.out.println("OrdersdetailDAO insert 완료 : " + count);
			
		} catch (Exception e) {
			count = -1;
			e.printStackTrace();
			System.out.println("[OrdersdetailDAO] 작업 중 예외 발생 : " +e.getMessage());
		} finally {
			CommonJDBCUtil.close(conn, pstmt);
		}
		
		return count;
	}
	
}
