package com.movie.test.service;

import java.util.List;
import java.util.Map;

public interface StageService {

	
List<Map<String,String>> selectStageList();
	
	Map<String,String> selectStage(int stiNum);
	
	int insertStage(Map<String,String> stage);
	
	int updateStage(Map<String,String> stage);
	
	int deleteStage(int stiNum);
	
}
