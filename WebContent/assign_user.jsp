<%@page import="pl.edu.pk.praca.ela.Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>Przypisywanie ucznia do opiekuna</h2>
<form class='form_class' id='assign_user_form'>
<%=Utils.makeSelect("Opiekun", "opiekun", Utils.opiekunOptions())%>
<%=Utils.makeMultipleSelect("Uczeń/uczniowie", "opiekun_uczen", Utils.uczenOptions()) %>
<%=Utils.makeButton("Przypisz", "assign_user()", "button") %>
</form>
<div id='assign_answer'></div>