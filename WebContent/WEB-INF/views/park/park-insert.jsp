<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주차장 등록</title>
</head>
<body>
	<form method="post" action="/park/insert">
		주차장명 : <input type="text" name="tp_name"><br>
		주소 : <input type="text" name="tp_address"><br>
		전화번호 : <input type="text" name="tp_phone"><br>
		영화관명 : 
		<select name="ti_num">
			<option>선택</option>
<%
List<Map<String,String>> theaterList = (List<Map<String,String>>)request.getAttribute("theaterList");
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