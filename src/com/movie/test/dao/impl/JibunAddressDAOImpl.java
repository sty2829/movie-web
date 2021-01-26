package com.movie.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import com.movie.test.com.DBConn;
import com.movie.test.dao.JibunAddressDAO;

public class JibunAddressDAOImpl implements JibunAddressDAO {

	@Override
	public int insertJibunAddress(List<Map<String, String>> JibunAddressList) {
		String sql = "insert into jibun_address(dong_code," + 
				"sido_name," + 
				"gugun_name," + 
				"dong_name," + 
				"lee_name," + 
				"mot," + 
				"bunji," + 
				"ho," + 
				"load_code," + 
				"base," + 
				"build_num," + 
				"build_sub_num," + 
				"jibun," + 
				"move_code)";
		sql += " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		int cnt = 0; 
		try {
			ps = con.prepareStatement(sql);
			for(int i=0; i<JibunAddressList.size(); i++) {
				Map<String,String> JibunAddress = JibunAddressList.get(i);
				ps.setString(1, JibunAddress.get("dong_code"));
				ps.setString(2, JibunAddress.get("sido_name"));
				ps.setString(3, JibunAddress.get("gugun_name"));
				ps.setString(4, JibunAddress.get("dong_name"));
				ps.setString(5, JibunAddress.get("lee_name"));
				ps.setString(6, JibunAddress.get("mot"));
				ps.setString(7, JibunAddress.get("bunji"));
				ps.setString(8, JibunAddress.get("ho"));
				ps.setString(9, JibunAddress.get("load_code"));
				ps.setString(10, JibunAddress.get("base"));
				ps.setString(11, JibunAddress.get("build_num"));
				ps.setString(12, JibunAddress.get("build_sub_num"));
				ps.setString(13, JibunAddress.get("jibun"));
				ps.setString(14, JibunAddress.get("move_code"));
//				cnt += ps.executeUpdate();
				ps.addBatch();
				ps.clearParameters();
			}
			ps.executeBatch();
			con.commit();
			cnt = 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps);
		}
		
		return cnt;
	}

}
