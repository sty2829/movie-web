<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
List<Map<String,String>> movieList = (List<Map<String,String>>)request.getAttribute("movieList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 리스트</title>
</head>
<body>
	<h3>영화 목록</h3>
	<form>
		<select name="search">
			<option value="mi_name" <%= "mi_name".equals(request.getParameter("search")) ? "selected" : "" %>>제목</option>
			<option value="mi_genre" <%= "mi_genre".equals(request.getParameter("search")) ? "selected" : "" %>>장르</option>
			<option value="mi_director" <%= "mi_director".equals(request.getParameter("search")) ? "selected" : "" %>>감독</option>
		</select> : <input type="text" name="mi_name" value="<%= (request.getParameter("mi_name")) != null ? request.getParameter("mi_name") : "" %>">
		<button>검색</button><br><br>
	</form>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>장르</th>
			<th>제작사</th>
			<th>감독</th>
			<th>개봉일</th>
			<th>입력날짜</th>
		</tr>
<%
for(int i=0; i<movieList.size(); i++){
	Map<String,String> movie = movieList.get(i);
%>
		<tr>
			<td><%= movie.get("mi_num") %></td>
			<td><a href="/movie/view?mi_num=<%= movie.get("mi_num") %>"><%= movie.get("mi_name") %></a></td>
			<td><%= movie.get("mi_genre") %></td>
			<td><%= movie.get("mi_producer") %></td>
			<td><%= movie.get("mi_director") %></td>
			<td><%= movie.get("mi_release_date") %></td>
			<td><%= movie.get("mi_credat") %></td>
		</tr>
<%	
}
%>
		<tr>
			<td colspan="8" align="right"><a href="/movie/insert">등록</a></td>
		</tr>
	</table>
</body>
</html>