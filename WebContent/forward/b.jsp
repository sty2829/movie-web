<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
나는 b.jsp<br>
<%=request.getParameter("num") %>
<%=request.getAttribute("msg") %>
	<form method="post" action="/forward/c.jsp?num=히히">
		<input type="hidden" name="test" value="테스트">
		<button>테스트</button>
	</form>
</body>
</html>