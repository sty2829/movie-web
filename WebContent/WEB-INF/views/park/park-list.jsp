<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주차장 목록</title>
</head>
<body>
	<h3>극장 주차장</h3>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>주차장명</th>
			<th>주소</th>
			<th>전화번호</th>
			<th>영화관명</th>
		</tr>
<%
List<Map<String,String>> parkList = (List<Map<String,String>>)request.getAttribute("list");
for(int i=0; i<parkList.size(); i++){
	Map<String,String> park = parkList.get(i);
%>	
	<tr align="center">
		<td><%=park.get("tp_num") %></td>
		<td><a href="/park/update?tpNum=<%=park.get("tp_num") %>"><%=park.get("tp_name") %></a></td>
		<td><%=park.get("tp_address") %></td>
		<td><%=park.get("tp_phone") %></td>
		<td><%=park.get("ti_name") %></td>
	</tr>
<%
}
%>
	<tr>
		<td colspan="5" align="right"><a href="/park/insert"><button>주차장 등록</button></a></td>
	</tr>
	</table>

</body>
</html>