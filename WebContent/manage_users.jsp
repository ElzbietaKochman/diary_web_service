<%@page import="pl.edu.pk.praca.ela.Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>Zarządzanie użytkownikami</h2>
<form class='form_class' id='manage_users_form'>
<h3>Wybierz opcję</h3>
<%=Utils.makeRadio("manage", "edit", "Edycja") %>
<%=Utils.makeRadio("manage", "delete", "Usunięcie") %>
<div id='next_form_fields'></div>
<div id='form_options'></div>
</form>
<div id='manage_users_answer'></div>

<script>
$(document).ready(function(){
	$("#manage_users_form input:radio").change(function(){
		$.ajax({
			  url: "Admin",
			  type: "post",
			  data: {
				  action: "manage_users_type",
				  type: $(this).val()
			  },
			  success: function(data){
				  $("#next_form_fields").html(data);
				  $("#form_options").html("");
			  }
		  });
	});
});
</script>