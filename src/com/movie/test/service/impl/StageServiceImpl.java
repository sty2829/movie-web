package com.movie.test.service.impl;

import java.util.List;
import java.util.Map;

import com.movie.test.dao.StageDAO;
import com.movie.test.dao.impl.StageDAOImpl;
import com.movie.test.service.StageService;

public class StageServiceImpl implements StageService {
	
	private StageDAO stageDAO = new StageDAOImpl();

	@Override
	public List<Map<String, String>> selectStageList() {
		return stageDAO.selectStageList();
	}

	@Override
	public Map<String, String> selectStage(int stiNum) {
		return stageDAO.selectStage(stiNum);
	}

	@Override
	public int insertStage(Map<String, String> stage) {
		return stageDAO.insertStage(stage);
	}

	@Override
	public int updateStage(Map<String, String> stage) {
		return stageDAO.updateStage(stage);
	}

	@Override
	public int deleteStage(int stiNum) {
		return stageDAO.deleteStage(stiNum);
	}

}
