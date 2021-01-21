<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
Map<String,String> food = (Map<String,String>)request.getAttribute("food");

%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>푸드 수정 삭제</title>
</head>
<style>
	form>button{
		float : left;
		margin : 2px;		
	}
</style>
<body>
	
	<form method="post" action="/food/update">
		<input type="hidden" name="fi_num" value="<%= food.get("fi_num") %>">
		음식이름 : <input type="text" name="fi_name" value="<%= food.get("fi_name") %>"><br>
		음식가격 : <input type="text" name="fi_price" value="<%= food.get("fi_price") %>"><br>
		음식종류 : <input type="text" name="fi_type" value="<%= food.get("fi_type") %>"><br>
		<button>수정</button>
	</form>
	<form method="post" action="/food/delete">
		<input type="hidden" name="fi_num" value="<%= food.get("fi_num") %>">
		<button>삭제</button>
	</form>
</body>
</html>