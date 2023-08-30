package com.mystudy.cafetest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mystudy.cafetest.vo.CustomerVO;
import com.mystudy.cafetestcommon.CommonJDBCUtil;

public class CustomerDAO {
	
	// 전화번호로 custid 조회
	public static CustomerVO selectCustId (String pointNum) {
		CustomerVO vo = null;
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = CommonJDBCUtil.getConnection();
			StringBuilder sql = new StringBuilder(); 
			sql.append("SELECT * ");
			sql.append(" FROM CUSTOMER ");
			sql.append("	WHERE PHONE = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, pointNum);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				vo = new CustomerVO(
						rs.getInt("CUSTID"),
						rs.getString("CUSTNAME"),
						rs.getString("PASSWORD"),
						rs.getString("PHONE"));
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonJDBCUtil.close(conn, pstmt, rs);
		}
		return vo;
	}
	
	
	
	
	

}
