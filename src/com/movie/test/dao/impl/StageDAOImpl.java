package com.movie.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.movie.test.com.DBConn;
import com.movie.test.dao.StageDAO;

public class StageDAOImpl implements StageDAO {

	@Override
	public List<Map<String, String>> selectStageList() {
		List<Map<String, String>> stageList = new ArrayList<>();
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select sti_num, sti_name, sti_floor, \r\n" + 
				" sti_type, ti_num, \r\n" + 
				" (select ti_name from theater_info ti where ti.ti_num = sti.ti_num) ti_name, \r\n" + 
				" to_char(to_date(sti_credat), 'YYYY-MM-DD') sti_credat \r\n" + 
				" from stage_info sti order by sti_num";
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Map<String, String> stage = new HashMap<>();
				stage.put("sti_num", rs.getString("sti_num"));
				stage.put("sti_name", rs.getString("sti_name"));
				stage.put("sti_floor", rs.getString("sti_floor"));
				stage.put("sti_type", rs.getString("sti_type"));
				stage.put("ti_num", rs.getString("ti_num"));
				stage.put("ti_name", rs.getString("ti_name"));
				stage.put("sti_credat", rs.getString("sti_credat"));
				
				stageList.add(stage);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, rs);
		}
		return stageList;
	}

	@Override
	public Map<String, String> selectStage(int stiNum) {
		Map<String, String> stage = new HashMap<>();
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " select sti_num, sti_name, sti_floor, \r\n" + 
				" sti_type, ti_num, \r\n" + 
				" (select ti_name from theater_info ti where ti.ti_num = sti.ti_num) ti_name, \r\n" + 
				" to_char(to_date(sti_credat), 'YYYY-MM-DD') sti_credat \r\n" + 
				" from stage_info sti where sti_num = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, stiNum);
			rs = ps.executeQuery();
			if(rs.next()) {
				stage.put("sti_num", rs.getString("sti_num"));
				stage.put("sti_name", rs.getString("sti_name"));
				stage.put("sti_floor", rs.getString("sti_floor"));
				stage.put("sti_type", rs.getString("sti_type"));
				stage.put("ti_num", rs.getString("ti_num"));
				stage.put("ti_name", rs.getString("ti_name"));
				stage.put("sti_credat", rs.getString("sti_credat"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, rs);
		}
		return stage;
	}

	@Override
	public int insertStage(Map<String, String> stage) {
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		int cnt = 0;
		System.out.println(stage);
		System.out.println(stage.get("sti_type"));
		String sql = "insert into stage_info(\r\n" + 
				" sti_num, sti_name, sti_floor, \r\n" + 
				" ti_num, sti_credat, sti_type)\r\n" + 
				" values(\r\n" + 
				" seq_sti_num.nextval, ?, ?,\r\n" + 
				" ?, to_char(sysdate, 'YYYYMMDD'), ?)";
		if(stage.get("sti_type") == null || "".equals(stage.get("sti_type"))) {
			sql = "insert into stage_info(\r\n" + 
					" sti_num, sti_name, sti_floor, \r\n" + 
					" ti_num, sti_credat)\r\n" + 
					" values(\r\n" + 
					" seq_sti_num.nextval, ?, ?,\r\n" + 
					" ?, to_char(sysdate, 'YYYYMMDD'))";
		}	
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, stage.get("sti_name"));
			ps.setString(2, stage.get("sti_floor"));
			ps.setString(3, stage.get("ti_num"));
			if(stage.get("sti_type") != null && !"".equals(stage.get("sti_type"))) {
				ps.setString(4, stage.get("sti_type"));
			}
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
	public int updateStage(Map<String, String> stage) {
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		int cnt = 0;
		String sql = "update stage_info\r\n" + 
				" set sti_name = ?, sti_floor = ?,\r\n" + 
				" sti_type= ?, ti_num = ?\r\n" + 
				" where sti_num = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, stage.get("sti_name"));
			ps.setString(2, stage.get("sti_floor"));
			ps.setString(3, stage.get("sti_type") != null && !"".equals(stage.get("sti_type")) ? stage.get("sti_type") : "2D");
			ps.setString(4, stage.get("ti_num"));
			ps.setString(5, stage.get("sti_num"));
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
	public int deleteStage(int stiNum) {
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		int cnt = 0;
		String sql = "delete from stage_info where sti_num = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, stiNum);
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
	
	public static void main(String[] args) {
		
	}
}
