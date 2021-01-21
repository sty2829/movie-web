package com.movie.test.dao;

import java.util.List;
import java.util.Map;

public interface ParkDAO {

List<Map<String,String>> selectParkList();
	
	Map<String,String> selectPark(int tpNum);
	
	int insertPark(Map<String,String> park);
	
	int updatePark(Map<String,String> park);
	
	int deletPark(int tpNUm);
}
