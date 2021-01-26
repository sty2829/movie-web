package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.movie.test.com.DBConn;

public class BackUp {

		private final String  col = "dong_code\r\n" + 
				"sido_name\r\n" + 
				"gugun_name\r\n" + 
			
				"dong_name\r\n" + 
				"lee_name\r\n" + 
				"mot\r\n" + 
				"bunji\r\n" + 
				"ho\r\n" + 
				"load_code\r\n" + 
				"base\r\n" + 
				"build_num\r\n" + 
				"build_sub_num\r\n" + 
				"jibun\r\n" + 
				"move_code";
		private final String[] cols = col.split("\r\n");
		
		public List<Map<String, String>> selectJibunAddressList() {
			List<Map<String,String>> JibunAddressList = new ArrayList();
			Connection con = DBConn.getConn();
			PreparedStatement ps =null;
			ResultSet rs = null;
			String sql = "select * from jibun_address";
			
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					Map<String,String> JibunAddress = new HashMap<>();
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBConn.close(con, ps, rs);
			}
			
			return null;
		}

		public Map<String, String> selectJibunAddress(Map<String, String> JibunAddress) {
			return null;
		}

		public int insertJibunAddress(List<Map<String, String>> JibunAddressList) {
			
			int cnt = 0;
			String sql = "insert into jibun_address(";
			String values = " values(";
			for(int i=0; i<cols.length; i++) {
				sql += cols[i] + ", ";
				values += "?, ";
			}
			sql = sql.substring(0, sql.length()-2) + ")";
			values = values.substring(0, values.length()-2) + ")";
			sql = sql + values;
			Connection con = DBConn.getConn();
			PreparedStatement ps = null;
			try {
				ps = con.prepareStatement(sql);
				for(int i=0; i<JibunAddressList.size(); i++) {
					Map<String,String> JibunAddress = JibunAddressList.get(i);
					for(int j=0; j<cols.length; j++) {
						ps.setString((j+1), JibunAddress.get(cols[j]));
					}
					if(ps.executeUpdate() != 1) {
						return cnt;
					}
					DBConn.commit(con);
				}
				
				cnt = 1;
				
			} catch (Exception e) {
				DBConn.rollback(con);
				e.printStackTrace();
			} finally {
				DBConn.close(con, ps);
			}
			
			return cnt;
		}
	}

