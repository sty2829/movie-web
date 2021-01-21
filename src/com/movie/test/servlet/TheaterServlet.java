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

import com.movie.test.service.TheaterService;
import com.movie.test.service.impl.TheaterServiceImpl;

public class TheaterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String PREFIX = "/WEB-INF/views";
	private final String SUFFIX = ".jsp";
	private TheaterService theaterService = new TheaterServiceImpl();
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		int idx = uri.lastIndexOf("/");
		String cmd = uri.substring(idx+1);
		String path = "";
		if("list".equals(cmd)) {
			path = "/theater/theater-list";
			List<Map<String,String>> theaterList = theaterService.selectTheaterList();
			request.setAttribute("list", theaterList);
		}else if("insert".equals(cmd)) {
			path = "/theater/theater-insert";
			
		}else if("update".equals(cmd)) {
			path = "/theater/theater-update";
			Map<String,String> theater =
			theaterService.selectTheater(Integer.parseInt(request.getParameter("tiNum")));
			request.setAttribute("theater", theater);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(PREFIX + path + SUFFIX);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int idx = uri.lastIndexOf("/");
		String cmd = uri.substring(idx+1);
		String resultPath = "/theater/result";
		int cnt = 0;
		Map<String,String> theater = new HashMap<String,String>();
		
		if("insert".equals(cmd)) {
			theater.put("ti_name", request.getParameter("ti_name"));
			theater.put("ti_address", request.getParameter("ti_address"));
			theater.put("ti_phone1", request.getParameter("ti_phone1"));
			theater.put("ti_phone2", request.getParameter("ti_phone2"));
			cnt = theaterService.insertTheater(theater);

		}else if("update".equals(cmd)) {
			theater.put("ti_num", request.getParameter("ti_num"));
			theater.put("ti_name", request.getParameter("ti_name"));
			theater.put("ti_address", request.getParameter("ti_address"));
			theater.put("ti_phone1", request.getParameter("ti_phone1"));
			theater.put("ti_phone2", request.getParameter("ti_phone2"));
			cnt = theaterService.updateTheater(theater);
		}else if("delete".equals(cmd)) {
			cnt = theaterService.deleteTheater(Integer.parseInt(request.getParameter("ti_num")));
		}
		
		String msg = "영화관 " + cmd + " 에 성공하였습니다.";
		if(cnt!=1) {
			msg = "영화관 "+ cmd + " 에  실패하였습니다";
		}
		request.setAttribute("msg", msg);
		RequestDispatcher rd = request.getRequestDispatcher(PREFIX + resultPath + SUFFIX);
		rd.forward(request, response);
	}

}
