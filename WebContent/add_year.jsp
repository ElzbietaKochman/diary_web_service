<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="pl.edu.pk.praca.ela.Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>Dodawanie nowego roku szkolnego</h2>
<form class='form_class' id='new_year_form'>
<% SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd"); %>
<%=Utils.makeInputTextReadOnly("pocz_roku", "Pocz. roku szkolnego", sdf.format(new Date())) %>
<%=Utils.makeInputTextReadOnly("koniec_zimowego", "Kon. sem. zimowego", sdf.format(new Date())) %>
<%=Utils.makeInputTextReadOnly("koniec_roku", "Kon. roku szkolenego", sdf.format(new Date())) %>
<%=Utils.makeButton("Dodaj", "add_new_year()", "button") %>
</form>
<div id="year_answer"></div>
<script>
$(document).ready(function(){
	$("#pocz_roku").datetimepicker({
		format: "Y-m-d",
		timepicker: false,
		lang: "pl"
	});
	$("#koniec_zimowego").datetimepicker({
		format: "Y-m-d",
		timepicker: false,
		lang: "pl"
	});
	$("#koniec_roku").datetimepicker({
		format: "Y-m-d",
		timepicker: false,
		lang: "pl"
	});
});
</script>