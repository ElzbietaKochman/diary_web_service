<%@page import="pl.edu.pk.praca.ela.Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>Przypisanie nauczyciela do klasy</h2>
<form class='form_class' id='make_teacher_form'>
<%=Utils.makeSelect("Nauczyciel", "teacher", Utils.nauczycielOptions()) %>
<%=Utils.makeInputText("group_id", "Numer grupy", "") %>
<%=Utils.makeButton("Przypisz", "make_teacher()", "button") %>
</form>
<div id='teacher_answer'></div>