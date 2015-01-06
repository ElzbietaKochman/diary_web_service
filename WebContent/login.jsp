<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="WEB-INF/headers.jsp"></jsp:include>
<title>Logowanie!</title>
</head>
<body>
<center>
	<div class="main2">
	<p class="podpis">Mobilny dzienniczek ucznia</p>
	<form action="Login" method="post" id="login_form">
	<p id="info" >Zaloguj się do systemu!</p>
	<table>
	<tr><td>Login: </td><td><input type="text" id="login" name="login"> </td>
	<tr>
	</tr>
	<tr><td>Hasło: </td><td><input type="password" id="password" name="password"> </td>
	<tr>
	</tr>
	<tr><td><p><input class="bottom" type="submit" id="login" value="Zaloguj"></p> </td>
	<tr>
	</table>
	</form>
	<div id='login_answer'></div>
	</div>
	</center>
</body>
</html>