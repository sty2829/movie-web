package com.movie.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import com.movie.test.com.DBConn;
import com.movie.test.dao.JusoAddressDAO;

public class JusoAddressDAOImpl implements JusoAddressDAO {

	@Override
	public int insertJusoAddress(List<Map<String, String>> JusoAddressList) {
		String sql = "insert into jibun_addr(code," + 
				"code_num," + 
				"dong_code," + 
				"sido_name," + 
				"gugun_name," + 
				"dong_name," + 
				"lee_name," + 
				"san," + 
				"jibun," + 
				"jibun_sub," + 
				"major)";
		
		sql += " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		int cnt = 0; 
		try {
			ps = con.prepareStatement(sql);
			for(int i=0; i<JusoAddressList.size(); i++) {
				Map<String,String> JibunAddress = JusoAddressList.get(i);
				ps.setString(1, JibunAddress.get("code"));
				ps.setString(2, JibunAddress.get("code_num"));
				ps.setString(3, JibunAddress.get("dong_code"));
				ps.setString(4, JibunAddress.get("sido_name"));
				ps.setString(5, JibunAddress.get("gugun_name"));
				ps.setString(6, JibunAddress.get("dong_name"));
				ps.setString(7, JibunAddress.get("lee_name"));
				ps.setString(8, JibunAddress.get("san"));
				ps.setString(9, JibunAddress.get("jibun"));
				ps.setString(10, JibunAddress.get("jibun_sub"));
				ps.setString(11, JibunAddress.get("major"));
//				cnt += ps.executeUpdate();
				ps.addBatch();
				ps.clearParameters();
			}
			ps.executeBatch();
			DBConn.commit(con);
			cnt = 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps);
		}
		
		return cnt;
	}

	@Override
	public void insertJusoAddress(PreparedStatement ps, List<Map<String, String>> JusoAddressList) {

		try {

			for(int i=0; i<JusoAddressList.size(); i++) {
				Map<String,String> JibunAddress = JusoAddressList.get(i);
				ps.setString(1, JibunAddress.get("code"));
				ps.setString(2, JibunAddress.get("code_num"));
				ps.setString(3, JibunAddress.get("dong_code"));
				ps.setString(4, JibunAddress.get("sido_name"));
				ps.setString(5, JibunAddress.get("gugun_name"));
				ps.setString(6, JibunAddress.get("dong_name"));
				ps.setString(7, JibunAddress.get("lee_name"));
				ps.setString(8, JibunAddress.get("san"));
				ps.setString(9, JibunAddress.get("jibun"));
				ps.setString(10, JibunAddress.get("jibun_sub"));
				ps.setString(11, JibunAddress.get("major"));
				ps.addBatch();
				ps.clearParameters();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
