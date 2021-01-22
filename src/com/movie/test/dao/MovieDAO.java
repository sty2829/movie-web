package com.movie.test.dao;

import java.util.List;
import java.util.Map;

public interface MovieDAO {

	List<Map<String,String>> selectMovieList(Map<String,String> pMovie);
	
	Map<String,String> selectMovie(int miNum);
	
	int insertMoive(Map<String,String> movie);
	
	int updateMoive(Map<String,String> movie);
	
	int deleteMoive(int miNum);
}
