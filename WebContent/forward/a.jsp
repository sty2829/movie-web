<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
나는a.jsp<br>
<%=request.getParameter("num") %>
<%
request.setAttribute("msg", "야 조심해~ ");
RequestDispatcher rd = request.getRequestDispatcher("/forward/b.jsp");
rd.forward(request, response);
%>
</body>
</html>