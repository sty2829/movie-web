package com.movie.test.service.impl;

import java.util.List;
import java.util.Map;

import com.movie.test.dao.FoodDAO;
import com.movie.test.dao.impl.FoodDAOImpl;
import com.movie.test.service.FoodService;

public class FoodServiceImpl implements FoodService {
	
	private FoodDAO foodDAO = new FoodDAOImpl();

	@Override
	public List<Map<String, String>> selectFoodList() {
		return foodDAO.selectFoodList();
	}

	@Override
	public Map<String, String> selectFood(int fiNum) {
		return foodDAO.selectFood(fiNum);
	}

	@Override
	public int insertFood(Map<String, String> food) {
		return foodDAO.insertFood(food);
	}

	@Override
	public int updateFood(Map<String, String> food) {
		return foodDAO.updateFood(food);
	}

	@Override
	public int deleteFood(int fiNum) {
		return foodDAO.deleteFood(fiNum);
	}

}
