<%@page import="java.sql.CallableStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert 콜 here</title>
</head>
<body>
<%
String driverName = "oracle.jdbc.OracleDriver";
String url = "jdbc:oracle:thin:@localhost:1521/xe";
String id = "jtest";
String pwd = "ezen1234";

Class.forName(driverName);


Connection con = DriverManager.getConnection(url, id, pwd);
CallableStatement cs = null;
try{
	cs = con.prepareCall("{call prd_increase_sal(?, ?)}");
	cs.setInt(1, 5);
	cs.setDouble(2, 0.5);
	cs.executeUpdate();
	out.println("성공");
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