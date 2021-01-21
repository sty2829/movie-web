<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="javax.crypto.Mac"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화관 목록</title>
</head>
<body>
	<table border="1">
		<tr >
			<th>번호</th>
			<th>이름</th>
			<th>주소</th>
			<th>전화번호1</th>
			<th>전화번호2</th>
		</tr>
<%
List<Map<String,String>> theaterList = (List<Map<String,String>>)request.getAttribute("list");
for(int i=0; i<theaterList.size(); i++){
	Map<String,String> theater = theaterList.get(i);
%>
		<tr>
			<td align="center"><%=theater.get("ti_num") %></td>
			<td><a href="/theater/update?tiNum=<%=theater.get("ti_num")%>"><%=theater.get("ti_name")%></a></td>
			<td><%=theater.get("ti_address") %></td>
			<td><%=theater.get("ti_phone1") %></td>
			<td><%=theater.get("ti_phone2") %></td>
		</tr>
<%	
}
%>	
		<tr>
			<td colspan="5" align="right"><a href="/theater/insert"><button>영화관 등록</button></a></td>
		</tr>
	</table>
</body>
</html>