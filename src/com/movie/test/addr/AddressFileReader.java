package com.movie.test.addr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.movie.test.dao.JibunAddressDAO;
import com.movie.test.dao.impl.JibunAddressDAOImpl;

public class AddressFileReader {
	
	public static final String path = "C:\\study\\addr\\buildDB";
	
	public static void main(String[] args) {
		System.out.println("시작");
		long startTime = System.currentTimeMillis();
		String  col = "dong_code\r\n" + 
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
		String[] cols = col.split("\r\n");
		List<Map<String,String>> jaList = new ArrayList<>();
		JibunAddressDAO jibunAddressDAO = new JibunAddressDAOImpl();
		File root = new File(path);
		
		File[] files = root.listFiles();
		for(int i=0; i<files.length; i++) {
			File file = files[i];
			String fileName = file.getName();
			if(fileName.indexOf("jibun")==0) {
				System.out.println(fileName + " 작업 시작");
				try {
					InputStreamReader inputStream = new FileReader(file);
					BufferedReader bufferedReader = new BufferedReader(inputStream);
					String line = null;
					while((line=bufferedReader.readLine()) != null) {
						String[] addrs = line.split("\\|");
						Map<String,String> ja = new HashMap<>();
						for(int j=0; j<addrs.length; j++) {
							ja.put(cols[j], addrs[j]);
						}
						jaList.add(ja);
						if(jaList.size()%10000 == 0) {
							jibunAddressDAO.insertJibunAddress(jaList);
							jaList.clear();
						}
					}
					if(jaList.size() !=0) {
						jibunAddressDAO.insertJibunAddress(jaList);
						jaList.clear();
					}
					System.out.println(fileName + " 작업 종료");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		long endTime = System.currentTimeMillis();
		double executeTime = endTime - startTime;
		System.out.println("실행시간 : " + executeTime/1000 + "초") ;
	}
}