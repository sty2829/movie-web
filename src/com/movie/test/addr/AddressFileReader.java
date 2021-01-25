package com.movie.test.addr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddressFileReader {
	
	public static final String path = "C:\\study\\addr";
	
	public static void main(String[] args) {
		File root = new File(path);
		
		File[] files = root.listFiles();
		for(int i=0; i<files.length; i++) {
			File file = files[i];
			String fileName = file.getName();
			if(fileName.indexOf("jibun_sejong")==0) {
				try {
					InputStreamReader inputStream = new FileReader(file);
					BufferedReader bufferedReader = new BufferedReader(inputStream);
					String line = null;
					while((line=bufferedReader.readLine()) != null) {
						String[] addrs = line.split("\\|");
						System.out.println("법정코드 : " + addrs[0]);
						System.out.println("시도명 : " + addrs[1]);
						System.out.println("시군구명 : " + addrs[2]);
						System.out.println("법정읍면동명 : " + addrs[3]);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	
}
