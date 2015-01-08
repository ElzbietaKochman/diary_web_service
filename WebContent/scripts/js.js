/**
 * 
 */

$(document).ready(function(){
  $("#login_form").submit(function(event){
	  var login = $("#login").val();
	  var pass = $("#password").val();
	  event.preventDefault();
	  $.ajax({
		  url: "Login",
		  type: "post",
		  data: {
			  login: login,
			  pass: pass
		  },
		  success: function(data){
			  if(data == 1){
				  window.location.href="admin.jsp";
			  }
			  else if(data == 2){
				  $("#login_answer").text("Brak uprawnień");
			  }
			  else if(data == 3){
				  $("#login_answer").text("Nieprawidłowa nazwa użytkownika lub hasło");
			  }
		  }
	  });
  });
  $(".menu").find("a:last").css("margin-top","100px");
  $(".menu").find("a").click(function(){
	  isUserLoggedIn();
  });
  $("#add_user").click(function(){
	  $("#content").load("add_user.jsp");
  });
  $("#add_year").click(function(){
	  $("#content").load("add_year.jsp");
  });
  $("#make_teacher").click(function(){
	  $("#content").load("make_teacher.jsp");
  });
  $("#manage_users").click(function(){
	  $("#content").load("manage_users.jsp");
  });
  $("#assign_user").click(function(){
	  $("#content").load("assign_user.jsp");
  });
  
  
  $("#logout").click(function(){
	  $("#content").load("logout.jsp");
	  setTimeout(function(){
		  window.location.href="login.jsp";
	  }, 100);
  });
}); 

function confirm_add_user(){
	
	var form_values = $("#add_user_form").serializeArray();
	var values = new Array();
	

	$.each(form_values, function(index,element){
		values.push(element.value);
	});
	 $.ajax({
		  url: "Admin",
		  type: "post",
		  data: {
			  action: "confirm_add_user",
			  values: values
		  },
		  success: function(data){
			  if(data == 1){
				  $("#add_answer").html("<font color='green'>Pomyślnie dodano użytkownika</font>");
			  }
			  else if(data == 2){
				  $("#add_answer").html("<font color='red'>Nierawidłowa nazwa użytkownika</font>");
			  }
			  else if(data == 3){
				  $("#add_answer").html("<font color='red'>Użytkownik o podanym loginie już istnieje</font>");
			  }
		  }
	  });
	
}

function assign_user(){
	var form_values = $("#assign_user_form").serializeArray();
	var values = new Array();
	

	$.each(form_values, function(index,element){
		values.push(element.value);
	});
	 $.ajax({
		  url: "Admin",
		  type: "post",
		  data: {
			  action: "assign_user",
			  values: values
		  },
		  success: function(data){
			  if(data == 1){
				  $("#assign_answer").html("<font color='green'>Pomyślnie przypisano ucznia</font>");
			  }
			  else if(data == 0){
				  $("#assign_answer").html("<font color='red'>Błąd z bazą danych</font>");
			  }
		  }
	  });
}

function add_new_year(){
	var form_values = $("#new_year_form").serializeArray();
	var values = new Array();
	

	$.each(form_values, function(index,element){
		values.push(element.value);
	});
	 $.ajax({
		  url: "Admin",
		  type: "post",
		  data: {
			  action: "new_year",
			  values: values
		  },
		  success: function(data){
			  if(data == 1){
				  $("#year_answer").html("<font color='green'>Pomyślnie dodano rok szkolny</font>");
			  }
			  else if(data == 0){
				  $("#year_answer").html("<font color='red'>Błąd z bazą danych</font>");
			  }
		  }
	  });
}

function make_teacher(){
	var form_values = $("#make_teacher_form").serializeArray();
	var values = new Array();
	

	$.each(form_values, function(index,element){
		values.push(element.value);
	});
	 $.ajax({
		  url: "Admin",
		  type: "post",
		  data: {
			  action: "make_teacher",
			  values: values
		  },
		  success: function(data){
			  if(data == 1){
				  $("#teacher_answer").html("<font color='green'>Pomyślnie przypisano nauczyciela do grupy</font>");
			  }
			  else if(data == 0){
				  $("#teacher_answer").html("<font color='red'>Błąd z bazą danych</font>");
			  }
			  else if(data == 2){
				  $("#teacher_answer").html("<font color='red'>Zły format nauczyciela lub grupy</font>");
			  }
		  }
	  });
}

function isUserLoggedIn(){
	$.ajax({
		  url: "Admin",
		  type: "post",
		  data: {
			  action: "is_logged"
		  },
		  success: function(data){
			  if(data.length == 7){
				  window.location.reload();
			  }
		  }
	});
		  
}