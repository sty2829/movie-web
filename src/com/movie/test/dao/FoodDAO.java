package com.movie.test.dao;

import java.util.List;
import java.util.Map;

public interface FoodDAO {
	
	List<Map<String,String>> selectFoodList();
	
	Map<String,String> selectFood(int fiNum);
	
	int insertFood(Map<String,String> food);
	
	int updateFood(Map<String,String> food);
	
	int deleteFood(int fiNum);
	
}
