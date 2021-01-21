package com.movie.test.service;

import java.util.List;
import java.util.Map;

public interface TheaterService {
	
	List<Map<String,String>> selectTheaterList();
	
	Map<String,String> selectTheater(int tiNum);
	
	int insertTheater(Map<String,String> theater);
	
	int updateTheater(Map<String,String> theater);
	
	int deleteTheater(int tiNum);

}
