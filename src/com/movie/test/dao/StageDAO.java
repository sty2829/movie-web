package com.movie.test.dao;

import java.util.List;
import java.util.Map;

public interface StageDAO {

List<Map<String,String>> selectStageList();
	
	Map<String,String> selectStage(int stiNum);
	
	int insertStage(Map<String,String> stage);
	
	int updateStage(Map<String,String> stage);
	
	int deleteStage(int stiNum);
	
}
