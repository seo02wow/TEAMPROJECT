package com.mystudy.cafetest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mystudy.cafetest.customer.order.Order_Method;
import com.mystudy.cafetest.vo.CartVO;
import com.mystudy.cafetest.vo.MenuVO;
import com.mystudy.cafetest.vo.StampVO;
import com.mystudy.cafetestcommon.CommonJDBCUtil;

public class StampDAO {

	public List<StampVO> selectAll() {
		List<StampVO> list = null;
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = CommonJDBCUtil.getConnection();
			StringBuilder sql = new StringBuilder(); 
			sql.append("SELECT STAMPID, STAMPCNT, CUSTID  ");
			sql.append(" FROM STAMP ");
			sql.append("ORDER BY STAMPID");
			
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			list = new ArrayList<StampVO>();
	
			while (rs.next()) {
				StampVO vo = new StampVO(
						rs.getString("STAMPID"),
						rs.getInt("STAMPCNT"),
						rs.getInt("CUSTID"));
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
	
	
	
	
	
	// 포인트 적립 
	public static int update (String pointNum, int selectCount) {
		int count = 0 ;
		
		Connection conn =null;
		PreparedStatement pstmt = null;
		
		try {
			conn = CommonJDBCUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE STAMP SET STAMPCNT ");
			sql.append(" = ? ");
			sql.append("WHERE CUSTID = (SELECT C.CUSTID ");
			sql.append("FROM CUSTOMER C,STAMP STP ");
			sql.append(" WHERE C.CUSTID = STP.CUSTID ");
			sql.append(" AND C.PHONE = ? )");
			
			// 포인트 번호로 보유 스탬프 개수 확인
			StampVO stampvo = new StampVO();
			stampvo = selectStampCnt(pointNum);
			//System.out.println("stampvo : "+ stampvo);
			int stampcnt = stampvo.getStampCnt();
			System.out.println("적립 전 보유 스탬프 : " + stampcnt);

			System.out.println("적립 예정 스탬프 : "+ selectCount);
			pstmt = conn.prepareStatement(sql.toString());
			int i = 1;
			pstmt.setInt(i++,(stampcnt + selectCount));
			pstmt.setString(i++, pointNum);
		
			count = pstmt.executeUpdate();
			System.out.println("StampDAO update 완료 : " + count);
			
		} catch (Exception e) {
			count = -1;
		} finally {
			CommonJDBCUtil.close(conn, pstmt);
		}
		
		
		return count;
		
	}
	
	// 
	
	// 스탬프개수 확인 메소드 
	public static StampVO selectStampCnt (String pointNum) {
		StampVO vo = null;
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = CommonJDBCUtil.getConnection();
			StringBuilder sql = new StringBuilder(); 
			sql.append("SELECT STP.* ");
			sql.append(" FROM CUSTOMER C,STAMP STP ");
			sql.append(" WHERE C.CUSTID = STP.CUSTID ");
			sql.append(" AND PHONE = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, pointNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new StampVO(
						rs.getString("STAMPID"),
						rs.getInt("STAMPCNT"),
						rs.getInt("CUSTID"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonJDBCUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}
}
