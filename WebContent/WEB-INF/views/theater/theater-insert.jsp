<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화관 등록</title>
</head>
<body>
	<form method="post" action="/theater/insert">
		이름 : <input type="text" name="ti_name"><br>
		주소 : <input type="text" name="ti_address"><br>
		전화번호1 : <input type="text" name="ti_phone1"><br>
		전화번호2 : <input type="text" name="ti_phone2"><br>
		<button>등록</button>
	</form>

</body>
</html>