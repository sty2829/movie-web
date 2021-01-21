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

import com.movie.test.service.MovieService;
import com.movie.test.service.impl.MovieServiceImpl;

public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final String PREFIX = "/WEB-INF/views";
    private final String SUFFIX = ".jsp";
    private MovieService movieService = new MovieServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		int idx = uri.lastIndexOf("/");
		String cmd = uri.substring(idx+1);
		String path = "";
		if("list".equals(cmd)){
			path = "/movie/movie-list";
			List<Map<String,String>> movieList = movieService.selectMovieList();
			request.setAttribute("movieList", movieList);
		}else if("insert".equals(cmd)) {
			path = "/movie/movie-insert";
		}else if("update".equals(cmd)) {
			path = "/movie/movie-update";
			String miNum = request.getParameter("mi_num");
			Map<String, String> movie = movieService.selectMovie(Integer.parseInt(miNum));
			request.setAttribute("movie", movie);
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
		String path = "/movie/result";
		String msg = "";
		
		Map<String,String> movie = new HashMap<String,String>();
		movie.put("mi_num", request.getParameter("mi_num"));
		movie.put("mi_name", request.getParameter("mi_name"));
		movie.put("mi_genre", request.getParameter("mi_genre"));
		movie.put("mi_producer", request.getParameter("mi_producer"));
		movie.put("mi_director", request.getParameter("mi_director"));
		movie.put("mi_release_date", request.getParameter("mi_release_date"));
		movie.put("mi_desc", request.getParameter("mi_desc"));
		
		if("insert".equals(cmd)) {
			if(movieService.insertMovie(movie) != 1) {
				msg = "영화 등록에 실패하였습니다";
				request.setAttribute("msg", msg);
			}else {
				msg = "영화 등록에 성공하였습니다";
				request.setAttribute("msg", msg);
			}
		}else if("update".equals(cmd)) {
			if(movieService.updateMovie(movie) != 1) {
				msg = "영화 수정에 실패하였습니다";
				request.setAttribute("msg", msg);
			}else {
				msg = "영화 수정에 성공하였습니다";
				request.setAttribute("msg", msg);
			}
		}else if("delete".equals(cmd)) {
			String miNum = request.getParameter("mi_num");
			if(movieService.deleteMovie(Integer.parseInt(miNum)) != 1) {
				msg = "영화 삭제에 실패하였습니다";
				request.setAttribute("msg", msg);
			}else {
				msg = "영화 삭제에 성공하였습니다";
				request.setAttribute("msg", msg);
			}
		}
		
		RequestDispatcher requestDispatcher = 
				request.getRequestDispatcher(PREFIX + path + SUFFIX);
		requestDispatcher.forward(request, response);
		
	}

}
