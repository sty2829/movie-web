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

import com.movie.test.service.ParkService;
import com.movie.test.service.TheaterService;
import com.movie.test.service.impl.ParkServiceImpl;
import com.movie.test.service.impl.TheaterServiceImpl;

public class ParkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String PREFIX = "/WEB-INF/views";
	private final String SUFFIX = ".jsp";
	private ParkService parkService = new ParkServiceImpl();
	private TheaterService theaterService = new TheaterServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		int idx = uri.lastIndexOf("/");
		String cmd = uri.substring(idx+1);
		String path = "";
		
		if("list".equals(cmd)) {
			path = "/park/park-list";
			List<Map<String,String>> parkList = parkService.selectParkList();
			request.setAttribute("list", parkList);
		}else if("insert".equals(cmd)) {
			path = "/park/park-insert";
			List<Map<String,String>> theaterList = theaterService.selectTheaterList();
			request.setAttribute("theaterList", theaterList);
		}else if("update".equals(cmd)) {
			path = "/park/park-update";
			String tpNum = request.getParameter("tpNum");
			List<Map<String,String>> theaterList = theaterService.selectTheaterList();
			Map<String,String> park = parkService.selectPark(Integer.parseInt(tpNum));
			request.setAttribute("park", park);
			request.setAttribute("theaterList", theaterList);
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
		String path = "/park/result";
		String str = "";
		int cnt = 0;
		if("insert".equals(cmd)) {
			Map<String,String> park = new HashMap<String,String>();
			park.put("tp_name", request.getParameter("tp_name"));
			park.put("tp_address",  request.getParameter("tp_address"));
			park.put("tp_phone",  request.getParameter("tp_phone"));
			park.put("ti_num",  request.getParameter("ti_num"));
			cnt = parkService.insertPark(park);
			str = "등록";
		}else if("update".equals(cmd)) {
			Map<String,String> park = new HashMap<String,String>();
			park.put("tp_num", request.getParameter("tp_num"));
			park.put("tp_name", request.getParameter("tp_name"));
			park.put("tp_address",  request.getParameter("tp_address"));
			park.put("tp_phone",  request.getParameter("tp_phone"));
			park.put("ti_num",  request.getParameter("ti_num"));
			cnt = parkService.updatePark(park);
			str = "수정";
		}else if("delete".equals(cmd)) {
			String tpNum = request.getParameter("tp_num");
			cnt = parkService.deletePark(Integer.parseInt(tpNum));
			str = "삭제";
		}
		
		
		String msg = "주차장 " + str + "에 성공하였습니다. ";
		if(cnt!=1) {
			msg = "주차장 " + str + "에 실패하였습니다. ";
		}
		request.setAttribute("msg", msg);
		
		RequestDispatcher requestDispatcher = 
				request.getRequestDispatcher(PREFIX + path + SUFFIX);
		requestDispatcher.forward(request, response);
	}

}
