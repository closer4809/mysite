package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;

public class BoardDao {
	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@3.36.114.215:1521:xe";
	private String id = "webdb2";
	private String pw = "webdb2";

	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			// System.out.println("접속성공");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	public void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
		
	public List<BoardVo> getBoardList(){
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		
		getConnection();
		
		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  bo.no as no, ";
			query += "         bo.title as title, ";
			query += "         us.name as name, ";
			query += "         bo.user_no as uno, ";
			query += "         bo.hit as hit, ";
			query += "         to_char(bo.reg_date,'yy-mm-dd') as rdate ";			
			query += " from    users us ,  board bo ";
			query += " where us.no = bo.user_no ";
			
			

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String name = rs.getString("name");
				String date = rs.getString("rdate");
				int hit = rs.getInt("hit");
				int uno = rs.getInt("uno");
				
				BoardVo boardVo = new BoardVo(no, title, hit, date, uno, name);
				boardList.add(boardVo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return boardList;
	}
		
}
	

