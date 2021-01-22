package com.movie.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.movie.test.com.DBConn;
import com.movie.test.dao.MovieDAO;

public class MovieDAOImpl implements MovieDAO {

	@Override
	public List<Map<String, String>> selectMovieList(Map<String, String> pMovie) {
		List<Map<String, String>> movieList = new ArrayList<Map<String,String>>();
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select mi_num, mi_name, mi_genre, \r\n" + 
				"mi_producer, mi_director, \r\n" + 
				"to_char(to_date(mi_release_date), 'YYYY-MM-DD') mi_release_date, \r\n" + 
				"to_char(to_date(mi_credat), 'YYYY-MM-DD') mi_credat, mi_desc \r\n" + 
				"from movie_info";
		String search = pMovie.get("search");
		if(pMovie.get("mi_name") != null && !"".equals(pMovie.get("mi_name"))) {
			if("mi_name".equals(search) || "mi_genre".equals(search) || "mi_director".equals(search)) {
				sql += " where " + search + " like ?";
			}
		}
		sql += " order by mi_num";
		try {
			
			ps = con.prepareStatement(sql);
			if(pMovie.get("mi_name")!=null && !"".equals(pMovie.get("mi_name"))) {
				if("mi_name".equals(search) || "mi_genre".equals(search) || "mi_director".equals(search)) {
					ps.setString(1, "%" + pMovie.get("mi_name") + "%");
				}
			}
			rs = ps.executeQuery();
			while(rs.next()) {
				Map<String, String> movie = new HashMap<String,String>();
				
				movie.put("mi_num", rs.getString("mi_num"));
				movie.put("mi_name", rs.getString("mi_name"));
				movie.put("mi_genre", rs.getString("mi_genre"));
				movie.put("mi_producer", rs.getString("mi_producer"));
				movie.put("mi_director", rs.getString("mi_director"));
				movie.put("mi_release_date", rs.getString("mi_release_date"));
				movie.put("mi_credat", rs.getString("mi_credat"));
				movie.put("mi_desc", rs.getString("mi_desc"));
				
				movieList.add(movie);
			}
			
			return movieList;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, rs);
		}
		
		return null;
	}

	@Override
	public Map<String, String> selectMovie(int miNum) {
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String sql = "select \r\n" + 
					"mi_num, mi_name, mi_genre, \r\n" + 
					"mi_producer, mi_director, to_char(to_date(mi_release_date), 'YYYY-MM-DD') mi_release_date, \r\n" + 
					"mi_credat, mi_desc \r\n" + 
					"from movie_info where mi_num = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, miNum);
			rs = ps.executeQuery();
			if(rs.next()) {
				Map<String, String> movie = new HashMap<String,String>();
				
				movie.put("mi_num", rs.getString("mi_num"));
				movie.put("mi_name", rs.getString("mi_name"));
				movie.put("mi_genre", rs.getString("mi_genre"));
				movie.put("mi_producer", rs.getString("mi_producer"));
				movie.put("mi_director", rs.getString("mi_director"));
				movie.put("mi_release_date", rs.getString("mi_release_date"));
				movie.put("mi_credat", rs.getString("mi_credat"));
				movie.put("mi_desc", rs.getString("mi_desc"));
				
				return movie;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, rs);
		}
		
		return null;
	}

	@Override
	public int insertMoive(Map<String, String> movie) {
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		int cnt = 0;
		
		try {
			String sql = "insert into movie_info";
			sql += " (mi_num, mi_name, mi_genre, mi_producer, mi_director,";
			sql += " mi_release_date, mi_credat, mi_desc)";
			sql += " values (seq_mi_num.nextval, ?, ?, ?, ?, ?, to_char(sysdate, 'YYYYMMDD'), ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, movie.get("mi_name"));
			ps.setString(2, movie.get("mi_genre"));
			ps.setString(3, movie.get("mi_producer"));
			ps.setString(4, movie.get("mi_director"));
			ps.setString(5, movie.get("mi_release_date"));
			ps.setString(6, movie.get("mi_desc"));
			cnt = ps.executeUpdate();
			DBConn.commit(con);
			
		} catch (Exception e) {
			DBConn.rollback(con);
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps);
		}
		
		return cnt;
	}

	@Override
	public int updateMoive(Map<String, String> movie) {
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		int cnt = 0;
		
		try {
			String sql = "update movie_info\r\n" + 
					" set mi_name = ?,\r\n" + 
					" mi_genre = ?,\r\n" + 
					" mi_producer = ?,\r\n" + 
					" mi_director =  ?,\r\n" + 
					" mi_release_date = ?,\r\n" + 
					" mi_desc = ?\r\n" + 
					" where mi_num = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, movie.get("mi_name"));
			ps.setString(2, movie.get("mi_genre"));
			ps.setString(3, movie.get("mi_producer"));
			ps.setString(4, movie.get("mi_director"));
			ps.setString(5, movie.get("mi_release_date"));
			ps.setString(6, movie.get("mi_desc"));
			ps.setString(7, movie.get("mi_num"));
			cnt = ps.executeUpdate();
			DBConn.commit(con);
			
		} catch (Exception e) {
			DBConn.rollback(con);
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps);
		}
		
		return cnt;
	}

	@Override
	public int deleteMoive(int miNum) {
		Connection con = DBConn.getConn();
		PreparedStatement ps = null;
		int cnt = 0;
		
		try {
			String sql = "delete from movie_info where mi_num = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, miNum);
			cnt = ps.executeUpdate();
			DBConn.commit(con);
			
		} catch (Exception e) {
			DBConn.rollback(con);
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps);
		}
		
		return cnt;
	}

}
