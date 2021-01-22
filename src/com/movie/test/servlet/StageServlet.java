package com.movie.test.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.test.service.StageService;
import com.movie.test.service.TheaterService;
import com.movie.test.service.impl.StageServiceImpl;
import com.movie.test.service.impl.TheaterServiceImpl;

public class StageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String PREFIX = "/WEB-INF/views";
	private final String SUFFIX = ".jsp";
	private StageService stageService = new StageServiceImpl();
	private TheaterService theaterService = new TheaterServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		int idx = uri.lastIndexOf("/");
		String cmd = uri.substring(idx+1);
		String path = "/stage/stage-" + cmd;
		if("list".equals(cmd)) {
			request.setAttribute("stageList", stageService.selectStageList());
		}else if("insert".equals(cmd)) {
			request.setAttribute("theaterList", theaterService.selectTheaterList());
		}else if("update".equals(cmd)) {
			request.setAttribute("theaterList", theaterService.selectTheaterList());
			request.setAttribute("stage", stageService.selectStage(Integer.parseInt(request.getParameter("sti_num"))));
			
		}
		
		RequestDispatcher requestDispatcher = 
				request.getRequestDispatcher(PREFIX + path + SUFFIX);
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int idx = uri.lastIndexOf("/");
		String cmd = uri.substring(idx+1);
		String path = "/stage/result";
		String msg = "";
		Map<String,String> stage = new HashMap<>();
		stage.put("sti_num", request.getParameter("sti_num"));
		stage.put("sti_name", request.getParameter("sti_name"));
		stage.put("sti_floor", request.getParameter("sti_floor"));
		stage.put("sti_type", request.getParameter("sti_type"));
		stage.put("ti_num", request.getParameter("ti_num"));
		
		if("insert".equals(cmd)) {
			msg = "상영관 등록에 성공하였습니다";
			request.setAttribute("msg", msg);
			
			if(stageService.insertStage(stage) !=1 ) {
				msg = "상영관 등록에 실패하였습니다";
				request.setAttribute("msg", msg);
			}
		}else if("update".equals(cmd)) {
			msg = "상영관 수정에 성공하였습니다";
			request.setAttribute("msg", msg);
			
			if(stageService.updateStage(stage) !=1 ) {
				msg = "상영관 수정에 실패하였습니다";
				request.setAttribute("msg", msg);
			}
		}else if("delete".equals(cmd)) {
			msg = "상영관 삭제에 성공하였습니다";
			request.setAttribute("msg", msg);
			
			if(stageService.deleteStage(Integer.parseInt(stage.get("sti_num"))) !=1 ) {
				msg = "상영관 삭제에 실패하였습니다";
				request.setAttribute("msg", msg);
			}
		}
		
		RequestDispatcher requestDispatcher = 
				request.getRequestDispatcher(PREFIX + path + SUFFIX);
		requestDispatcher.forward(request, response);
	}

}
