<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과요</title>
</head>
<body>
<%
out.print(request.getAttribute("msg"));

%>	
	<br>
	<a href="/food/list">리스트 이동</a>
</body>
</html>