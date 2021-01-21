package com.movie.test.service;

import java.util.List;
import java.util.Map;

public interface MovieService {

	List<Map<String,String>> selectMovieList();
	
	Map<String,String> selectMovie(int miNum);
	
	int insertMovie(Map<String,String> movie);
	
	int updateMovie(Map<String,String> movie);
	
	int deleteMovie(int miNum);
}
