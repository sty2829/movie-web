<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
 Map<String,String> theater = (Map<String,String>)request.getAttribute("theater");
 %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화관 업데이트</title>
</head>
<body>
	<form method="post" action="/theater/update">
		<input type="hidden" name="ti_num" value="<%=request.getParameter("tiNum")%>">
		이름 : <input type="text" name="ti_name" value="<%=theater.get("ti_name") %>"><br>
		주소 : <input type="text" name="ti_address" value="<%=theater.get("ti_address") %>"><br>
		전화번호1 : <input type="text" name="ti_phone1" value="<%=theater.get("ti_phone1") %>"><br>
		전화번호2 : <input type="text" name="ti_phone2" value="<%=theater.get("ti_phone2") %>"><br>
		<button>수정</button>
	</form>
	<form method="post" action="/theater/delete">
	<input type="hidden" name="ti_num" value="<%=request.getParameter("tiNum")%>">
		<button>삭제</button>
	</form>
</body>
</html>