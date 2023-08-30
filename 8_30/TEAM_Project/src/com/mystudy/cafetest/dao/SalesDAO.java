package com.mystudy.cafetest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.mystudy.cafetest.vo.OrdersdetailVO;
import com.mystudy.cafetest.vo.SalesVO;
import com.mystudy.cafetestcommon.CommonJDBCUtil;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SalesDAO {
	/*
	 ORDERNUM NUMBER(8) PRIMARY KEY, 
     ORDERID NUMBER(2) REFERENCES ORDERS(ORDERID),
     COUNT NUMBER(2) NOT NULL ,
     TOTALPRICE NUMBER(10) NOT NULL,
     ORDERDATE DATE NOT NULL
	 */
	
	public static int insert (SalesVO vo) {
		int count = 0;
		
		Connection conn =null;
		PreparedStatement pstmt = null;
		
		try {
			conn = CommonJDBCUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO SALES ");
			sql.append("	(ORDERNUM, ORDERID, COUNT, TOTALPRICE, ORDERDATE) ");
			sql.append(" VALUES (?, ?, ?, ?, SYSDATE)");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			int i = 1;
			pstmt.setInt(i++, vo.getOrderNum());
			pstmt.setInt(i++, vo.getOrderId());
			pstmt.setInt(i++, vo.getCount());
			pstmt.setInt(i++, vo.getTotalPrice());
			
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			count = -1;
			e.printStackTrace();
			System.out.println("[SalsesDAO] 작업 중 예외 발생 : " +e.getMessage());
		} finally {
			CommonJDBCUtil.close(conn, pstmt);
		}
		
		return count;
	}
	
	public List<SalesVO> selectAll() {
		List<SalesVO> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = CommonJDBCUtil.getConnection();
			StringBuilder sql = new StringBuilder(); 
			sql.append("SELECT ORDERNUM, ORDERID, COUNT, TOTALPRICE"
					+ ", ORDERDATE ");
			sql.append(" FROM SALES ");

			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			list = new ArrayList<SalesVO>();

			while (rs.next()) {
				SalesVO vo = new SalesVO(
						rs.getInt("ORDERNUM"),
						rs.getInt("ORDERID"),
						rs.getInt("COUNT"),
						rs.getInt("TOTALPRICE"),
						rs.getString("ORDERDATE"));
				list.add(vo);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[예외발생] : " + e.getMessage());
		} finally { 
			CommonJDBCUtil.close(conn, pstmt, rs);
		}
		
		return list;

	}

	// SELECT : 일별 매출데이터 조회
	public List<SalesVO> selectDay() {
		List<SalesVO> list = null;
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = CommonJDBCUtil.getConnection();
			StringBuilder sql = new StringBuilder(); 
			sql.append("SELECT TO_CHAR(S.ORDERDATE, 'YYYY/MM/DD') AS DAY"
					+ ", SUM(S.TOTALPRICE) AS DAILY_SALES ");
			sql.append("  FROM SALES S ");
			sql.append(" GROUP BY TO_CHAR(S.ORDERDATE, 'YYYY/MM/DD') ");

			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			list = new ArrayList<SalesVO>();

			while (rs.next()) {
				SalesVO vo = new SalesVO(
						rs.getInt("DAILY_SALES"),
						rs.getString("DAY"));
				list.add(vo);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[예외발생] : " + e.getMessage());
		} finally { 
			CommonJDBCUtil.close(conn, pstmt, rs);
		}
		
		return list;
		
	}
	
	// SELECT : 월별 매출데이터 조회
	public List<SalesVO> selectMonth() {
		List<SalesVO> list = null;
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = CommonJDBCUtil.getConnection();
			StringBuilder sql = new StringBuilder(); 
			sql.append("SELECT TO_CHAR(S.ORDERDATE, 'YYYY/MM') AS MONTH"
					+ ", SUM(S.TOTALPRICE) AS MONTH_SALES ");
			sql.append("  FROM SALES S ");
			sql.append(" GROUP BY TO_CHAR(S.ORDERDATE, 'YYYY/MM') ");

			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			list = new ArrayList<SalesVO>();

			while (rs.next()) {
				SalesVO vo = new SalesVO(
						rs.getInt("MONTH_SALES"),
						rs.getString("MONTH"));
				list.add(vo);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[예외발생] : " + e.getMessage());
		} finally { 
			CommonJDBCUtil.close(conn, pstmt, rs);
		}
		
		return list;
		
	}
	
	// SELECT : 연도별 매출데이터 조회
	public List<SalesVO> selectYear() {
		List<SalesVO> list = null;
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = CommonJDBCUtil.getConnection();
			StringBuilder sql = new StringBuilder(); 
			sql.append("SELECT TO_CHAR(S.ORDERDATE, 'YYYY') AS YEAR"
					+ ", SUM(S.TOTALPRICE) AS YEAR_SALES ");
			sql.append("  FROM SALES S ");
			sql.append(" GROUP BY TO_CHAR(S.ORDERDATE, 'YYYY') ");

			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			list = new ArrayList<SalesVO>();

			while (rs.next()) {
				SalesVO vo = new SalesVO(
						rs.getInt("YEAR_SALES"),
						rs.getString("YEAR"));
				list.add(vo);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[예외발생] : " + e.getMessage());
		} finally { 
			CommonJDBCUtil.close(conn, pstmt, rs);
		}
		
		return list;
		
	}
	


}
