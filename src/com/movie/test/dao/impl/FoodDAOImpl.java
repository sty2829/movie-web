package com.movie.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.movie.test.com.DBConn;
import com.movie.test.dao.FoodDAO;

public class FoodDAOImpl implements FoodDAO {

	@Override
	public List<Map<String, String>> selectFoodList() {
		List<Map<String,String>> foodList = new ArrayList<Map<String,String>>();
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "select * from food_info";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Map<String,String> food = new HashMap<String,String>();
				
				food.put("fi_num", rs.getString("fi_num"));
				food.put("fi_name", rs.getString("fi_name"));
				food.put("fi_price", rs.getString("fi_price"));
				food.put("fi_type", rs.getString("fi_type"));
				food.put("fi_credat", rs.getString("fi_credat"));
				food.put("fi_cretim", rs.getString("fi_cretim"));
				
				foodList.add(food);
			}
			
			return foodList;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, rs);
		}
		
		return null;
	}

	@Override
	public Map<String, String> selectFood(int fiNum) {
		
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "select * from food_info where fi_num = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, fiNum);
			rs = ps.executeQuery();
			if(rs.next()) {
				Map<String,String> food = new HashMap<String,String>();
				
				food.put("fi_num", rs.getString("fi_num"));
				food.put("fi_name", rs.getString("fi_name"));
				food.put("fi_price", rs.getString("fi_price"));
				food.put("fi_type", rs.getString("fi_type"));
				food.put("fi_credat", rs.getString("fi_credat"));
				food.put("fi_cretim", rs.getString("fi_cretim"));
				
				return food;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, rs);
		}
		return null;
	}

	@Override
	public int insertFood(Map<String, String> food) {
		
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		int cnt = 0;
		
		try {
			String sql = "insert into food_info(fi_num, fi_name, fi_price, fi_type, fi_credat, fi_cretim)";
			sql += " values(seq_fi_num.nextval, ?, ?, ?, to_char(sysdate, 'YYYYMMDD'), to_char(sysdate, 'HH24MISS'))";
			ps = con.prepareStatement(sql);
			ps.setString(1, food.get("fi_name"));
			ps.setString(2, food.get("fi_price"));
			ps.setString(3, food.get("fi_type"));
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
	public int updateFood(Map<String, String> food) {
		
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		int cnt = 0;
		
		try {
			String sql = "update food_info";
			sql += " set fi_name = ?,";
			sql += " fi_price = ?,";
			sql += " fi_type = ?";
			sql += " where fi_num = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, food.get("fi_name"));
			ps.setString(2, food.get("fi_price"));
			ps.setString(3, food.get("fi_type"));
			ps.setString(4, food.get("fi_num"));
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
	public int deleteFood(int fiNum) {
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		int cnt = 0;
		
		try {
			String sql = "delete from food_info where fi_num = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, fiNum);
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
