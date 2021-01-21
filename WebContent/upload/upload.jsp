<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- ㅎㅎ -->
	<form method="post" enctype="multipart/form-data" action="/fileupload">
		글쓴이 : <input type="text" name="writer"><br>
		파일 : <input type="file" name="file1"><br>
		<button>전송</button>	
	</form>
</body>
</html>