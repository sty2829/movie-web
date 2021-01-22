<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상영관 목록</title>
</head>
<body>
<%
List<Map<String,String>> stageList = (List<Map<String,String>>)request.getAttribute("stageList");
%>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>상영관명</th>
			<th>층</th>
			<th>종류</th>
			<th>영화관명</th>
			<th>생성날짜</th>
		</tr>			
<%
for(int i=0; i<stageList.size(); i++){
	Map<String,String> stage = stageList.get(i);
%>
		<tr>
			<td><%= stage.get("sti_num") %></td>
			<td><a href="/stage/update?sti_num=<%= stage.get("sti_num") %>"><%= stage.get("sti_name") %></a></td>
			<td><%= stage.get("sti_floor") %></td>
			<td><%= stage.get("sti_type") %></td>
			<td><%= stage.get("ti_name") %></td>
			<td><%= stage.get("sti_credat") %></td>
		</tr>

<%
}
%>		
		<tr>
			<td colspan="6" align="right"><a href="/stage/insert">등록</a></td>
		</tr>
	</table>

</body>
</html>