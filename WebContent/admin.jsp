<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="WEB-INF/headers.jsp"></jsp:include>
<title>Panel admina</title>
</head>
<body>
	<div class="main">
	<% try {if(session.getAttribute("user").equals("admin")){ %>

		<div class="podpis">
			<h2>Panel Administratora</h2>
		</div>
		<div class="menu">
		<h3>Menu:</h3>
		<ul>
		<li><a id="add_user" href="#">Dodaj użytkownika</a></li>
		<li><a id="add_year" href="#">Dodaj rok szkolny</a></li>
		<li><a id="make_teacher" href="#">Mianuj wychowawcę</a></li>
		<li><a id="manage_users" href="#">Zarządzaj użytkownikami</a></li>
		<li><a id="logout" href="#">Wyloguj</a></li>
		</ul>
		</div>
		<div id="content">
		
		</div>
	
<% } else { out.println("<h1>Nie masz uprawnień by tu przebywać!</h1>"); }
	} catch(NullPointerException e){
		out.println("<h1>Nie jesteś zalogowany!</h1>");
	}%>
	</div>
</body>
</html>