package com.mystudy.cafetest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mystudy.cafetest.vo.OrdersVO;
import com.mystudy.cafetestcommon.CommonJDBCUtil;

public class OrdersDAO {
	
	public static int insert (OrdersVO vo) {
		int count = 0;
		
		Connection conn =null;
		PreparedStatement pstmt = null;
		
		try {
			conn = CommonJDBCUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO ORDERS ");
			sql.append("	(ORDERID, CUSTID, TOTALPRICE) ");
			sql.append(" VALUES (?, ?, ?)");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			int i = 1;
			pstmt.setInt(i++, vo.getOrderId());
			pstmt.setInt(i++, vo.getCustId());
			pstmt.setInt(i++, vo.getTotalPrice());
			
			count = pstmt.executeUpdate();
			System.out.println("OrdersDAO insert 완료 " + count);
			
			
		} catch (Exception e) {
			count = -1;
			e.printStackTrace();
			System.out.println("[OrdersDAO] 작업 중 예외 발생 : " +e.getMessage());
		} finally {
			CommonJDBCUtil.close(conn, pstmt);
		}
		
		return count;
	}
	
	
	
	
	
	

}
