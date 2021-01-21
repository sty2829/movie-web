<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 등록 ㅎ</title>
</head>
<body>
	<form method="post" action="/movie/insert">
		이름 : <input type="text" name="mi_name"><br>
		장르 : <input type="text" name="mi_genre"><br>
		제작사 : <input type="text" name="mi_producer"><br>
		감독 : <input type="text" name="mi_director"><br>
		개봉일 : <input type="text" name="mi_release_date"><br>
		줄거리 : <input type="text" name="mi_desc"><br>
		<button>등록</button>
	</form>
</body>
</html>