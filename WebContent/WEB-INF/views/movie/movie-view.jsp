<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나는 뷰야</title>
<style>
button{
	float:left;
	margin:4px;
}

</style>
</head>
<body>
<%
Map<String,String> movie = (Map<String,String>)request.getAttribute("movie");
%>

	<h3>영화 정보</h3>
	<table border="1">
		<tr>
			<th width="100">영화제목</th>
			<td><%= movie.get("mi_name") %></td>
		</tr>
		<tr>
			<th>장르</th>
			<td><%= movie.get("mi_genre") %></td>
		</tr>
		<tr>
			<th>제작사</th>
			<td><%= movie.get("mi_producer") %></td>
		</tr>
		<tr>
			<th>감독</th>
			<td><%= movie.get("mi_director") %></td>
		</tr>
		<tr>
			<th>개봉일</th>
			<td><%= movie.get("mi_release_date") %></td>
		</tr>
		<tr>
			<th>설명</th>
			<td><%= movie.get("mi_desc") %></td>
		</tr>
		<tr>
			<th colspan="2">
				<a href="/movie/update?mi_num=<%= movie.get("mi_num") %>"><button>수정</button></a>
				<form method="post" action="/movie/delete">
					<input type="hidden" name="mi_num" value="<%= movie.get("mi_num") %>">
					<button>삭제</button>
				</form>
			</th>	 
		</tr>
	</table>
</body>
</html>