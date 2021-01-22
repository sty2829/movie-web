package com.movie.test.service.impl;

import java.util.List;
import java.util.Map;

import com.movie.test.dao.MovieDAO;
import com.movie.test.dao.impl.MovieDAOImpl;
import com.movie.test.service.MovieService;

public class MovieServiceImpl implements MovieService {

	private MovieDAO movieDAO = new MovieDAOImpl();
	
	@Override
	public List<Map<String, String>> selectMovieList(Map<String,String> pMovie) {
		return movieDAO.selectMovieList(pMovie);
	}

	@Override
	public Map<String, String> selectMovie(int miNum) {
		return movieDAO.selectMovie(miNum);
	}

	@Override
	public int insertMovie(Map<String, String> movie) {
		return movieDAO.insertMoive(movie);
	}

	@Override
	public int updateMovie(Map<String, String> movie) {
		return movieDAO.updateMoive(movie);
	}

	@Override
	public int deleteMovie(int miNum) {
		return movieDAO.deleteMoive(miNum);
	}

}
