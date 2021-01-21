package com.movie.test.service.impl;

import java.util.List;
import java.util.Map;

import com.movie.test.dao.ParkDAO;
import com.movie.test.dao.impl.ParkDAOImpl;
import com.movie.test.service.ParkService;

public class ParkServiceImpl implements ParkService {

	private ParkDAO parkDAO = new ParkDAOImpl();
	
	@Override
	public List<Map<String, String>> selectParkList() {
		return parkDAO.selectParkList();
	}

	@Override
	public Map<String, String> selectPark(int tpNum) {
		return parkDAO.selectPark(tpNum);
	}

	@Override
	public int insertPark(Map<String, String> park) {
		return parkDAO.insertPark(park);
	}

	@Override
	public int updatePark(Map<String, String> park) {
		return parkDAO.updatePark(park);
	}

	@Override
	public int deletPark(int tpNUm) {
		return parkDAO.deletPark(tpNUm);
	}

}
