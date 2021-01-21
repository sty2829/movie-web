<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>하이</title>
</head>
<body>
<%=request.getAttribute("msg")%>
	<br>
	<a href="/theater/list"><button>리스트</button></a>
</body>
</html>