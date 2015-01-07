<%@page import="pl.edu.pk.praca.ela.Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>Dodawanie nowego użytkownika</h2>
<form class='form_class' id="add_user_form">
<%=Utils.makeInputText("user_name", "Nazwisko i imię", "") %>
<%=Utils.makeInputText("user_login", "Login", "") %> <!-- password generowany - nazwisko+pierwsza litera imienia -->
<br/><i>Hasło generowane - nazwisko+pierwsza litera imienia</i>
<h4>Typ</h4>
<%=Utils.makeRadio("user_type", "uczen", "Uczeń") %>
<%=Utils.makeRadio("user_type", "nauczyciel", "Nauczyciel") %>
<%=Utils.makeRadio("user_type", "opiekun", "Opiekun") %>
<%=Utils.makeRadio("user_type", "admin", "Admin") %>
<div id='next_forms'></div>
</form>
<div id='add_answer'></div>
<script>
$(document).ready(function(){
	$("input[name='user_type']").change(function(){
		var val = $(this).val();
		$.ajax({
			url: "Admin",
			type: "post",
			data: {
				action: "add_user_form",
				value: val
			},
			success: function(data){
				$("#next_forms").html(data);
			}
		});
		
		
		
	});
});
</script>