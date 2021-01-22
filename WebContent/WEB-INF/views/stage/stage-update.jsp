<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상영관 수정삭제가자</title>
<script>

</script>
</head>
<body>
<%
List<Map<String,String>> theaterList = (List<Map<String,String>>)request.getAttribute("theaterList");
Map<String,String> stage = (Map<String,String>)request.getAttribute("stage");
%>
	<form method="post" action="/stage/update">
		<input type = "hidden" name="sti_num" value="<%= stage.get("sti_num") %>">
		상영관명 : <input type="text" name="sti_name" value="<%= stage.get("sti_name") %>"><br>
		층 : <input type="text" name="sti_floor" value="<%= stage.get("sti_floor") %>"><br>
		종류 : <input type="text" name="sti_type" value="<%= stage.get("sti_type") %>"><br>
		영화관명 : 
		<select name="ti_num">
<%
for(int i=0; i<theaterList.size(); i++){
	Map<String,String> theater = theaterList.get(i);
	String selected = theater.get("ti_num").equals(stage.get("ti_num")) ? "selected" : "" ;
%>
			<option value="<%= theater.get("ti_num") %>" <%= selected %> ><%= theater.get("ti_name") %></option>
<%
}
%>
		</select>	
		<br>
		<button>수정</button>
	</form>
	<form method="post" action="/stage/delete">
		<input type = "hidden" name="sti_num" value="<%= stage.get("sti_num") %>">
		<button>삭제</button>
	</form>
</body>
</html>