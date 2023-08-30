package com.mystudy.cafetest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mystudy.cafetestcommon.CommonJDBCUtil;
import com.mystudy.cafetest.vo.MenuVO;

public class MenuDAO {

	// 메뉴id로 메뉴 가격 조회
		public MenuVO selectPrice(int selectMenuId) {
			MenuVO vo = null;
			Connection conn =null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = CommonJDBCUtil.getConnection();
				StringBuilder sql = new StringBuilder(); 
				sql.append("SELECT * ");
				sql.append(" FROM MENU ");
				sql.append("	WHERE MENUID = ? ");
				
				pstmt = conn.prepareStatement(sql.toString());
				
				int i =1;
				pstmt.setInt(i++, selectMenuId);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					vo = new MenuVO(
							rs.getInt("MENUID"),
							rs.getString("NAME"),
							rs.getInt("PRICE"));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CommonJDBCUtil.close(conn, pstmt, rs);
			}
			return vo;
		}
		
	// SELECT : 전체 데이터 조회
	public List<MenuVO> selectAll() {
		List<MenuVO> list = null;
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = CommonJDBCUtil.getConnection();
			StringBuilder sql = new StringBuilder(); 
			sql.append("SELECT MENUID, NAME, PRICE  ");
			sql.append(" FROM MENU ");
			sql.append("ORDER BY MENUID ");
			
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			list = new ArrayList<MenuVO>();
			
			while (rs.next()) {
				MenuVO vo = new MenuVO(
						rs.getInt("MENUID"),
						rs.getString("NAME"),
						rs.getInt("PRICE"));
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
	
	// SELECT : db에 특정 menuid가 존재하는지 확인하기 
	// (추가할 때 이미 존재하는 id값이면 안됨)
	public boolean checkMenuIdExistence(int menuId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = CommonJDBCUtil.getConnection();
			String sql = "SELECT COUNT(*) FROM MENU WHERE MENUID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, menuId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);
				return count > 0; 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonJDBCUtil.close(conn, pstmt, rs);
		}

		return false; 
	}
	
	// SELECT : db에 특정 menuid가 존재하는지 확인하기
	// (수정할 때 수정하려는 id값이 없으면 안됨)
		public boolean checkMenuIdExistence2(int menuId) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				conn = CommonJDBCUtil.getConnection();
				String sql = "SELECT COUNT(*) FROM MENU WHERE MENUID = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, menuId);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					int count = rs.getInt(1);
					return count == 0; 
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CommonJDBCUtil.close(conn, pstmt, rs);
			}

			return true; 
		}
	        
	
	


	// INSERT : 메뉴 vo 추가
	public int insert(MenuVO vo) {
		int count = 0 ;

		Connection conn =null;
		PreparedStatement pstmt = null;

		try {
			conn = CommonJDBCUtil.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO MENU ");
			sql.append("		(MENUID, NAME, PRICE) ");
			sql.append("VALUES (?, ?, ?)");

			pstmt = conn.prepareStatement(sql.toString());

			int i = 1;
			pstmt.setInt(i++, vo.getMenuId());
			pstmt.setString(i++, vo.getName());
			pstmt.setInt(i++, vo.getPrice());

			count = pstmt.executeUpdate();

		} catch (Exception e) {
			count = -1;
		} finally {
			CommonJDBCUtil.close(conn, pstmt);
		}

		return count;
	}
	
	// UPDATE : 메뉴 vo 수정
	public int update (MenuVO vo) {
		int count = 0;
		Connection conn =null;
		PreparedStatement pstmt = null;

		try {
			conn = CommonJDBCUtil.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE MENU ");
			sql.append("	SET NAME = ? ");
			sql.append("	, PRICE = ? ");
			sql.append("WHERE MENUID = ? ");

			pstmt = conn.prepareStatement(sql.toString());

			int i = 1;
			pstmt.setString(i++, vo.getName());
			pstmt.setInt(i++, vo.getPrice());
			pstmt.setInt(i++, vo.getMenuId());

			count = pstmt.executeUpdate();

		} catch (Exception e) {
			count = -1;
		} finally {
			CommonJDBCUtil.close(conn, pstmt);
		}

		return count;
	}

	// DELETE : menuid값으로 menu 데이터 삭제
	public int delete(int menuId) {
		int count = 0;
		Connection conn =null;
		PreparedStatement pstmt = null;

		try {
			//2. DB연결 - Connection 객체 생성 <- DriverManager
			conn = CommonJDBCUtil.getConnection();

			//3. Statement 문 실행(SQL 문 실행)
			String sql = "DELETE FROM MENU WHERE MENUID = ?";		

			pstmt = conn.prepareStatement(sql);

			// ? 값 설정 
			pstmt.setInt(1, menuId);


			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("[예외발생] 작업 중 예외 발생 : " +e.getMessage());
			count = -1; 
		} finally {
			CommonJDBCUtil.close(conn, pstmt);
		}

		return count;
	}
	
}