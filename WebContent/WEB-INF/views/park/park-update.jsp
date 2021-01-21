<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주차장 업데이트</title>
</head>
<body>
<%
Map<String,String> park = (Map<String,String>)request.getAttribute("park");
List<Map<String,String>> theaterList = (List<Map<String,String>>)request.getAttribute("theaterList");
%>

	<form method="post" action="/park/update">
		<input type="hidden" name="tp_num" value="<%= park.get("tp_num") %>"><br>
		주차장명 : <input type="text" name="tp_name" value="<%= park.get("tp_name") %>"><br>
		주소 : <input type="text" name="tp_address" value="<%= park.get("tp_address") %>"><br>
		전화번호 : <input type="text" name="tp_phone" value="<%= park.get("tp_phone") %>"><br>
		극장명 : 
		<select name="ti_num">
<%
for(int i=0; i<theaterList.size(); i++){
	Map<String,String> theater = theaterList.get(i);
	String str = park.get("ti_num").equals(theater.get("ti_num")) ? "selected" : "";
%>
			<option value="<%= theater.get("ti_num") %>" <%= str %>><%= theater.get("ti_name") %></option>
<%	
}
%>		
		</select>
		<br>
		<button>수정</button>
	</form>
	<form method="post" action="/park/delete">
		<input type="hidden" name="tp_num" value="<%= park.get("tp_num") %>">
		<button>삭제</button>
	</form>
</body>
</html>