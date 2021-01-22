<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상영관 등록</title>
</head>
<body>
<%
List<Map<String,String>> theaterList = (List<Map<String,String>>)request.getAttribute("theaterList");
%>
	<form method="post" action="/stage/insert">
		상영관명 : <input type="text" name="sti_name"><br>
		층 : <input type="text" name="sti_floor"><br>
		종류 : <input type="text" name="sti_type"><br>
		영화관명 : <select name="ti_num">
			<option>선택</option>
<%
for(int i=0; i<theaterList.size(); i++){
	Map<String,String> theater = theaterList.get(i);
%>
			<option value="<%= theater.get("ti_num") %>"><%= theater.get("ti_name") %></option>
<%
}
%>
		</select>	
		<br>
		<button>등록</button>
	</form>
</body>
</html>