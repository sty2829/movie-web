<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
Map<String,String> movie = (Map<String,String>)request.getAttribute("movie");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
</head>
<body>
	<form method="post" action="/movie/update">
		<input type="hidden" name="mi_num" value="<%= movie.get("mi_num") %>">
		이름 : <input type="text" name="mi_name" value="<%= movie.get("mi_name") %>"><br>
		장르 : <input type="text" name="mi_genre" value="<%= movie.get("mi_genre") %>"><br>
		제작사 : <input type="text" name="mi_producer" value="<%= movie.get("mi_producer") %>"><br>
		감독 : <input type="text" name="mi_director" value="<%= movie.get("mi_director") %>"><br>
		개봉일 : <input type="date" name="mi_release_date" value="<%= movie.get("mi_release_date") %>"><br>
		줄거리 : <textarea name="mi_desc" cols="50" rows="8" style="resize:none"><%= movie.get("mi_desc") %></textarea><br>
		<button>수정</button>
	</form>
</body>
</html>