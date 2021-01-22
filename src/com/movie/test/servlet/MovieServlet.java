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
		String path = "/movie/movie-" + cmd;
		if("list".equals(cmd)){
			String miName = request.getParameter("mi_name");
			List<Map<String,String>> movieList = movieService.selectMovieList(miName);
			request.setAttribute("movieList", movieList);
		}else if("insert".equals(cmd)) {
		}else if("update".equals(cmd)) {
			request.setAttribute("movie", movieService.selectMovie(Integer.parseInt(request.getParameter("mi_num"))));
		}else if("view".equals(cmd)) {
			request.setAttribute("movie", movieService.selectMovie(Integer.parseInt(request.getParameter("mi_num"))));
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
		
		Map<String,String> movie = new HashMap<String,String>();
		movie.put("mi_num", request.getParameter("mi_num"));
		movie.put("mi_name", request.getParameter("mi_name"));
		movie.put("mi_genre", request.getParameter("mi_genre"));
		movie.put("mi_producer", request.getParameter("mi_producer"));
		movie.put("mi_director", request.getParameter("mi_director"));
		movie.put("mi_release_date", request.getParameter("mi_release_date"));
		movie.put("mi_desc", request.getParameter("mi_desc"));
		String miReleaseDate = request.getParameter("mi_release_date");
		if(miReleaseDate!=null) {
			miReleaseDate = miReleaseDate.replace("-", "");
			movie.put("mi_release_date", miReleaseDate);
		}
		
		if("insert".equals(cmd)) {
			request.setAttribute("msg", "영화 등록에 성공하였습니다");
			if(movieService.insertMovie(movie) != 1) {
				request.setAttribute("msg", "영화 등록에 실패하였습니다");
			}
		}else if("update".equals(cmd)) {
			request.setAttribute("msg", "영화 수정에 성공하였습니다");
			if(movieService.updateMovie(movie) != 1) {
				request.setAttribute("msg", "영화 수정에 실패하였습니다");
			}
		}else if("delete".equals(cmd)) {
			request.setAttribute("msg", "영화 삭제에 성공하였습니다");
			if(movieService.deleteMovie(Integer.parseInt(request.getParameter("mi_num"))) != 1) {
				request.setAttribute("msg", "영화 삭제에 실패하였습니다");
			}
		}
		
		RequestDispatcher requestDispatcher = 
				request.getRequestDispatcher(PREFIX + path + SUFFIX);
		requestDispatcher.forward(request, response);
		
	}

}
