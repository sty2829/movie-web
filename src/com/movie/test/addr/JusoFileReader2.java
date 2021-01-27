package com.movie.test.addr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.movie.test.com.DBConn;
import com.movie.test.dao.JusoAddressDAO;
import com.movie.test.dao.impl.JusoAddressDAOImpl;

public class JusoFileReader2 {

	public static final String path = "C:\\study\\addr\\jusoDB";
	
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		String col = "code\r\n" + 
				"code_num\r\n" + 
				"dong_code\r\n" + 
				"sido_name\r\n" + 
				"gugun_name\r\n" + 
				"dong_name\r\n" + 
				"lee_name\r\n" + 
				"san\r\n" + 
				"jibun\r\n" + 
				"jibun_sub\r\n" + 
				"major";
		String[] cols = col.split("\r\n");
		String sql = "insert into test_addr(code," + 
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
		List<Map<String,String>> jusoList = new ArrayList<>();
		JusoAddressDAO jusoAddressDAO = new JusoAddressDAOImpl();
		File root = new File(path);
		File[] files = root.listFiles();
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		int totalCnt = 0;
		try {
			ps = con.prepareStatement(sql);
			for(int i=0; i<files.length; i++) {
				File file = files[i];
				String fileName = file.getName();
				if(fileName.indexOf("지번") == 0) {
					System.out.println(fileName + " 작업시작 ");
					try {
						InputStreamReader inputStreamReader = new FileReader(file);
						BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
						String line = null;
						while((line = bufferedReader.readLine()) != null) {
							String[] jusos = line.split("\\|");
							Map<String,String> juso = new HashMap<>();
							for(int j=0; j<jusos.length; j++) {
								juso.put(cols[j], jusos[j]);
							}
							jusoList.add(juso);
							if(jusoList.size() % 1000 ==0) {
								totalCnt += jusoList.size();
								jusoAddressDAO.insertJusoAddress(ps, jusoList);
								jusoList.clear();
							}
							if(totalCnt > 800000) {
								totalCnt = 0;
								ps.executeBatch();
								DBConn.commit(con);
							}
						}
						if(jusoList.size() != 0) {
							totalCnt += jusoList.size();
							jusoAddressDAO.insertJusoAddress(ps, jusoList);
							jusoList.clear();
						}
						System.out.println(fileName + " 작업 종료");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			ps.executeBatch();
			DBConn.commit(con);
		} catch(Exception e) { 
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps);
		}
		long endTime = System.currentTimeMillis();
		double executeTime = endTime - startTime;
		System.out.println("실행시간 : " + executeTime/1000 + "초") ;
	}
}
