package com.movie.test.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movie.test.service.FoodService;
import com.movie.test.service.impl.FoodServiceImpl;

public class FoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String PRIFIX = "/WEB-INF/views";
	private final String SUFFIX = ".jsp";
	private FoodService foodService = new FoodServiceImpl();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Uri = request.getRequestURI();
		int idx = Uri.lastIndexOf("/");
		String cmd = Uri.substring(idx+1);
		String path = "";
		if("list".equals(cmd)) {
			path = "/food/food-list";
			List<Map<String, String>> foodList = foodService.selectFoodList();
			request.setAttribute("foodList", foodList);
		}else if("insert".equals(cmd)) {
			path = "/food/food-insert";
		}else if("update".equals(cmd)) {
			path = "/food/food-update";
			String fiNum = request.getParameter("fi_num");
			Map<String, String> food = foodService.selectFood(Integer.parseInt(fiNum));
			request.setAttribute("food", food);
		}
		
		RequestDispatcher requestDispatcher = 
				request.getRequestDispatcher(PRIFIX + path + SUFFIX);
		
		requestDispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String Uri = request.getRequestURI();
		int idx = Uri.lastIndexOf("/");
		String cmd = Uri.substring(idx+1);
		String path = "/food/result";
		String msg = "";
		String str = "";
		int cnt = 0;
		if("insert".equals(cmd)) {
			Map<String,String> food = new HashMap<String, String>();
			food.put("fi_name", request.getParameter("fi_name"));
			food.put("fi_price", request.getParameter("fi_price"));
			food.put("fi_type", request.getParameter("fi_type"));
			cnt = foodService.insertFood(food);
			str = "등록";
		}else if("update".equals(cmd)) {
			Map<String,String> food = new HashMap<String, String>();
			food.put("fi_num", request.getParameter("fi_num"));
			food.put("fi_name", request.getParameter("fi_name"));
			food.put("fi_price", request.getParameter("fi_price"));
			food.put("fi_type", request.getParameter("fi_type"));
			cnt = foodService.updateFood(food);
			str = "수정";
		}else if("delete".equals(cmd)) {
			String fiNum = request.getParameter("fi_num");
			cnt = foodService.deleteFood(Integer.parseInt(fiNum));
			str = "삭제";
		}
		
		if(cnt!=1) {
			msg = "음식 " + str  + "에 실패하였습니다.";
			request.setAttribute("msg", msg);
		}
		msg = "음식 " + str  + "에 성공하였습니다.";
		request.setAttribute("msg", msg);
		
		RequestDispatcher requestDispatcher = 
				request.getRequestDispatcher(PRIFIX + path + SUFFIX);
		
		requestDispatcher.forward(request, response);
		
	}

}
