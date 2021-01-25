<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 잘쓰는거.. -->
	<form>
		hidden : <input type="hidden"><br>
		number : <input type="number"><br>
		text : <input type="text"><br>
		password : <input type="password"><br>
		file : <input type="file"><br>
		date : <input type="date"><br>
		radio : <input type="radio" value="1" id="job1" name="job">
		<label for="job1">개발자</label>
		<input type="radio" value="2" id="job2" name="job">
		<label for="job2">디자니어</label>
		<input type="radio" value="3" id="job3" name="job">
		<label for="job3">기획자</label><br>
		checkbox : <input type="checkbox" value="1" id="hobby1" name="hobby">
		<label for="hobby1">영화</label>
		<input type="checkbox" value="2" id="hobby2" name="hobby">
		<label for="hobby2">음악</label>
		<input type="checkbox" value="3" id="hobby3" name="hobby">
		<label for="hobby3">독서</label><br>
		<!-- 잘안쓰긴함.. -->
		<input type="button" value="일반버튼"><br>
		<input type="submit" value="전송버튼"><br>
		<input type="reset" value="취소버튼"><br>
		color : <input type="color"><br>
		email : <input type="email"><br>
		range : <input type="range"><br>
	</form>

</body>
</html>