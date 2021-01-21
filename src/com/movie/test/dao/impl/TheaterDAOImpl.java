package com.movie.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.movie.test.com.DBConn;
import com.movie.test.dao.TheaterDAO;

public class TheaterDAOImpl implements TheaterDAO {

	@Override
	public List<Map<String, String>> selectTheaterList() {
		List<Map<String,String>> theaterList = new ArrayList<Map<String,String>>();
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from theater_info order by ti_num";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Map<String,String> theater = new HashMap<String,String>();
				
				theater.put("ti_num", rs.getString("ti_num"));
				theater.put("ti_name", rs.getString("ti_name"));
				theater.put("ti_address", rs.getString("ti_address"));
				theater.put("ti_phone1", rs.getString("ti_phone1") != null ? rs.getString("ti_phone1") : "준비중" );
				theater.put("ti_phone2", rs.getString("ti_phone2") != null ? rs.getString("ti_phone2") : "준비중");
			
				theaterList.add(theater);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, rs);
		}
		return theaterList;
	}

	@Override
	public Map<String, String> selectTheater(int tiNum) {
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from theater_info where ti_num = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, tiNum);
			rs = ps.executeQuery();
			if(rs.next()) {
				Map<String,String> theater = new HashMap<String,String>();
				theater.put("ti_num", rs.getString("ti_num"));
				theater.put("ti_name", rs.getString("ti_name"));
				theater.put("ti_address", rs.getString("ti_address"));
				theater.put("ti_phone1", rs.getString("ti_phone1") != null ? rs.getString("ti_phone1") : "");
				theater.put("ti_phone2", rs.getString("ti_phone2") != null ? rs.getString("ti_phone2") : "");
				return theater;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, rs);
		}
		
		return null;
	}

	@Override
	public int insertTheater(Map<String, String> theater) {
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		int cnt = 0;
		
		try {
			String sql = "insert into theater_info(ti_num, ti_name, ti_address, ti_phone1, ti_phone2)";
			sql += " values(seq_ti_num.nextval, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, theater.get("ti_name"));
			ps.setString(2, theater.get("ti_address"));
			ps.setString(3, theater.get("ti_phone1"));
			ps.setString(4, theater.get("ti_phone2"));
			cnt = ps.executeUpdate();
			DBConn.commit(con);
			return cnt;
			
		} catch (Exception e) {
			DBConn.rollback(con);
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps);
		}
		
		return cnt;
	}

	@Override
	public int updateTheater(Map<String, String> theater) {
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		int cnt = 0;
		
		try {
			String sql = "update theater_info";
			sql += " set ti_name = ?,";
			sql += " ti_address = ?,";
			sql += " ti_phone1 = ?,";
			sql += " ti_phone2 = ?";
			sql += " where ti_num = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, theater.get("ti_name"));
			ps.setString(2, theater.get("ti_address"));
			ps.setString(3, theater.get("ti_phone1"));
			ps.setString(4, theater.get("ti_phone2"));
			ps.setString(5, theater.get("ti_num"));
			cnt = ps.executeUpdate();
			DBConn.commit(con);
			return cnt;
			
		} catch (Exception e) {
			DBConn.rollback(con);
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps);
		}
		return cnt;
	}

	@Override
	public int deleteTheater(int tiNum) {
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		int cnt = 0;
		
		try {
			String sql = "delete from theater_info where ti_num = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, tiNum);
			cnt = ps.executeUpdate();
			DBConn.commit(con);
			return cnt;
			
		} catch (Exception e) {
			DBConn.rollback(con);
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps);
		}
		
		return cnt;
	}
	
	
	public static void main(String[] args) {
		TheaterDAO theaterDAO = new TheaterDAOImpl();
		Map<String,String> theater = new HashMap<String,String>();
		theater.put("ti_name", "천재극장");
		theater.put("ti_address", "천재주소");
		theater.put("ti_phone1", "02-5123-1234");
		theater.put("ti_phone2", "02-1122-2131");
		theater.put("ti_num", "7");
		int cnt = theaterDAO.updateTheater(theater);
		System.out.println(cnt);
		System.out.println(theaterDAO.selectTheaterList());
	}
}
