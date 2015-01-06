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
  
  
  $("#add_user").click(function(){
	  
  });
  $("#add_year").click(function(){
	  
  });
  $("#make_teacher").click(function(){
	  
  });
  $("#manage_users").click(function(){
	  
  });
  $("#logout").click(function(){
	  $("#content").load("logout.jsp");
	  setTimeout(function(){
		  window.location.href="login.jsp";
	  }, 100);
  });
}); 