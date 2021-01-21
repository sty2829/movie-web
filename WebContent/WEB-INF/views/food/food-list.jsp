<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
List<Map<String,String>> foodList = (List<Map<String,String>>)request.getAttribute("foodList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>푸드 짱</title>
</head>
<body>

	<h3>음식 리스트</h3>

	<table border="1">
		<tr>
			<th>음식번호</th>
			<th>음식이름</th>
			<th>음식가격</th>
			<th>음식종류</th>
			<th>생성날짜</th>
			<th>생성시간</th>
		</tr>
<%
for(int i=0; i<foodList.size(); i++){
	Map<String,String> food = foodList.get(i);
%>
		<tr>
			<td><%= food.get("fi_num") %></td>
			<td><a href="/food/update?fi_num=<%= food.get("fi_num") %>"><%= food.get("fi_name") %></a></td>
			<td><%= food.get("fi_price") %></td>
			<td><%= food.get("fi_type") %></td>
			<td><%= food.get("fi_credat") %></td>
			<td><%= food.get("fi_cretim") %></td>
		</tr>	
<%
}
%>	

		<tr>
			<td colspan="6" align="right"><a href="/food/insert"><button>음식등록</button></a></td>
		</tr>	
	</table>
	

</body>
</html>