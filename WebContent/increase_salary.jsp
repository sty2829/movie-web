<%@page import="java.sql.CallableStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>increase_salary</title>
</head>
<body>
<%
String driverName = "oracle.jdbc.OracleDriver";
String url = "jdbc:oracle:thin:@localhost:1521/xe";
String id = "jtest";
String pwd = "ezen1234";

Connection con = DriverManager.getConnection(url, id, pwd);
CallableStatement cs = null;
try{
	cs = con.prepareCall("{call increase_salary(?, ?)}");
	cs.setString(1, "blue");
	cs.setDouble(2, 0.5);
	cs.executeUpdate();
	out.println("dragon의 급여를 0.05 인상했습니다.");
}catch(Exception e){
	e.printStackTrace();
	out.println("실패");
}finally{
	if(cs!=null){
		cs.close();
	}
	if(con!=null){
		con.close();
	}
}
%>
</body>
</html>