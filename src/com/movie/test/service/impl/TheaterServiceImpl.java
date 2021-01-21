package com.movie.test.service.impl;

import java.util.List;
import java.util.Map;

import com.movie.test.dao.TheaterDAO;
import com.movie.test.dao.impl.TheaterDAOImpl;
import com.movie.test.service.TheaterService;

public class TheaterServiceImpl implements TheaterService {
	private TheaterDAO theaterDAO = new TheaterDAOImpl();

	@Override
	public List<Map<String, String>> selectTheaterList() {
		return theaterDAO.selectTheaterList();
	}

	@Override
	public Map<String, String> selectTheater(int tiNum) {
		return theaterDAO.selectTheater(tiNum);
	}

	@Override
	public int insertTheater(Map<String, String> theater) {
		return theaterDAO.insertTheater(theater);
	}

	@Override
	public int updateTheater(Map<String, String> theater) {
		return theaterDAO.updateTheater(theater);
	}

	@Override
	public int deleteTheater(int tiNum) {
		return theaterDAO.deleteTheater(tiNum);
	}

}
