package com.movie.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.movie.test.com.DBConn;
import com.movie.test.dao.ParkDAO;

public class ParkDAOImpl implements ParkDAO {

	@Override
	public List<Map<String, String>> selectParkList() {
		List<Map<String,String>> parkList = new ArrayList<Map<String,String>>();
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "select tp.*, ti.ti_name from theater_park tp, theater_info ti";
			sql += " where tp.ti_num = ti.ti_num order by tp_num";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Map<String,String> park = new HashMap<String,String>();
				
				park.put("tp_num", rs.getString("tp_num"));
				park.put("tp_name", rs.getString("tp_name"));
				park.put("tp_address", rs.getString("tp_address"));
				park.put("tp_phone", rs.getString("tp_phone"));
				park.put("ti_name", rs.getString("ti_name"));
				park.put("ti_num", rs.getString("ti_num"));
				
				parkList.add(park);
			}
			
			return parkList;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, rs);
		}
		
		return null;
	}

	@Override
	public Map<String, String> selectPark(int tpNum) {
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "select tp_num, tp_name, tp_address, tp_phone, ti_num,";
			sql += " (select ti_name from theater_info ti where ti.ti_num = tp.ti_num) as ti_name";
			sql += " from theater_park tp where tp_num = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, tpNum);
			rs = ps.executeQuery();
			if(rs.next()) {
				Map<String,String> park = new HashMap<String,String>();
				
				park.put("tp_num", rs.getString("tp_num"));
				park.put("tp_name", rs.getString("tp_name"));
				park.put("tp_address", rs.getString("tp_address"));
				park.put("tp_phone", rs.getString("tp_phone"));
				park.put("ti_name", rs.getString("ti_name"));
				park.put("ti_num", rs.getString("ti_num"));
				
				return park;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, rs);
		}
		
		return null;
	}

	@Override
	public int insertPark(Map<String, String> park) {
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		int cnt = 0;

		try {
			String sql = "insert into theater_park";
			sql += " (tp_num, tp_name, tp_address, tp_phone, ti_num)";
			sql += " values(seq_tp_num.nextval, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, park.get("tp_name"));
			ps.setString(2, park.get("tp_address"));
			ps.setString(3, park.get("tp_phone"));
			ps.setString(4, park.get("ti_num"));
			cnt = ps.executeUpdate();
			DBConn.commit(con);
			
		} catch (Exception e) {
			DBConn.rollback(con);
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps);
		}
		
		return cnt;
	}

	@Override
	public int updatePark(Map<String, String> park) {
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		int cnt = 0;

		try {
			String sql = "update theater_park";
			sql += " set tp_name = ?,";
			sql += " tp_address = ?,";
			sql += " tp_phone = ?,";
			sql += " ti_num = ? ";
			sql += " where tp_num = ? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, park.get("tp_name"));
			ps.setString(2, park.get("tp_address"));
			ps.setString(3, park.get("tp_phone"));
			ps.setString(4, park.get("ti_num"));
			ps.setString(5, park.get("tp_num"));
			cnt = ps.executeUpdate();
			DBConn.commit(con);
			
		} catch (Exception e) {
			DBConn.rollback(con);
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps);
		}
		
		return cnt;
	}

	@Override
	public int deletPark(int tpNUm) {
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		int cnt = 0;

		try {
			String sql = "delete from theater_park where tp_num = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, tpNUm);
			cnt = ps.executeUpdate();
			DBConn.commit(con);
			
		} catch (Exception e) {
			DBConn.rollback(con);
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps);
		}
		
		return cnt;
		
	}
}
