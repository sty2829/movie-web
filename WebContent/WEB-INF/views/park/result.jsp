<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성공 목록</title>
</head>
<body>
<%= request.getAttribute("msg") %>
<br>
<a href="/park/list">리스트 이동</a>
</body>
</html>