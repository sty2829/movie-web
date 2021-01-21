package com.movie.test.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConn {
	private static final String DRIVER_CLASS_NAME = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521/xe";
	private static final String USER = "jtest";
	private static final String PASSWORD = "ezen1234";
	
	static {
			try {
				Class.forName(DRIVER_CLASS_NAME);
				System.out.println("드라이버 로드성공!");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	public static Connection getConn() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			con.setAutoCommit(false);
			System.out.println("커넥션성공    : " + con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	public static void close(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void close(Statement st) {
		if(st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(PreparedStatement ps) {
		if(ps!=null) {
			try {
				ps.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(Connection con, Statement st) {
		close(st);
		close(con);
	}
	public static void close(Connection con, Statement st, ResultSet rs) {
		close(rs);
		close(st);
		close(con);
	}
	public static void close(Connection con, PreparedStatement ps) {
		close(ps);
		close(con);
	}
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		close(rs);
		close(ps);
		close(con);
	}
	
	public static void commit(Connection con) {
		if(con!=null) {
			try {
				con.commit();
				System.out.println("커밋 성공!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void rollback(Connection con) {
		if(con!=null) {
			try {
				con.rollback();
				System.out.println("롤백");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
