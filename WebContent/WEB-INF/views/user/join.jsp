<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
이름 : 2글자이상 10글자 이하
아이디 : 4글자이상 20글자 이하 영문 + 숫자만 가능
비밀번호 : 6글자 이상 20글자 이하
주소: 4글자 이상 100글자 이하 
폰번호: 3픽스- 3자리이상 4자리이하 -4픽스
이메일: 4글자 이상 30글자 이하
 -->
  <form action="/user/join" method="post"><br>
  	이름 : <input type="text" name="name" id="name"><br>
  	아이디 : <input type="text" name="id" id="id"><br>
  	비밀번호  : <input type="password" name="pwd" id="pwd1"><br>
  	비밀번호 확인  : <input type="password" id="pwd2"><br>
  	주소  : <input type="text" name="address" id="address"><br>
  	폰번호:
  	<select name="ph1" id="ph1">
  		<option value="010">010</option>
  		<option value="011">011</option>
  		<option value="018">018</option>
  		<option value="019">019</option>
  	</select>
  	-
  	<input type="text" name="ph2" id="ph2" placeholder="숫자로만 입력하세요"><br>
  	이메일 : <input type="email" name="email" id="email"><br>
  	<button>회원가입</button>
  </form>
</body>
</html>