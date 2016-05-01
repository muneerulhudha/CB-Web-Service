var loginCount = 0;

jQuery(document).ready(function($) {
  tab = $('.tabs h3 a');

  tab.on('click', function(event) {
    event.preventDefault();
    tab.removeClass('active');
    $(this).addClass('active');

    tab_content = $(this).attr('href');
    $('div[id$="tab-content"]').removeClass('active');
    $(tab_content).addClass('active');
  });


  $("#loginForm").submit(function(e){

    $.ajax({
      type: "POST",
      data: $("#loginForm").serialize(),
      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
      url: "rest/login", 
      success: function(result){
          console.log("Magizhchi");
          console.log(result.success);
          if(result.success == true){
              loginCount = 0;
              console.log("username=" + result.username);
              document.cookie = "username=" + result.username;

              window.location.replace("home.html");
          }
          if(result.success == false){
              loginCount = loginCount + 1;
              console.log("Failed login attempts: " + loginCount);
              $("#loginForm")[0].reset();
              $("#failurespan").html('<font color="#cc0000">Login failed. Please try again.</font>');
          }
      }
    });

    e.preventDefault();
    return false;

  });

  $("#signupForm").submit(function(e){

    $("#epicfail").html('<font color="#cc0000"></font>');
    var formData = $("#signupForm").serialize();

    $.ajax({
      type: "POST",
      data: formData,
      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
      url: "rest/signup", 
      success: function(result){
          console.log(result.success);
          if(result.success == true){
              $(".login-tab a").trigger("click");
          }
          if(result.success == false){
              $("#signupForm")[0].reset();
              $("#epicfail").html('<font color="#cc0000">Username or Email exists already.</font>');
          }
      }
    });

    e.preventDefault();
    return false;

  });

  $("#user_email").blur(function() 
  {
   var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;  
   var emailaddress = $("#user_email").val();
     if(!emailReg.test(emailaddress)) 
          $("#emailspan").html('<font color="#cc0000">Please enter valid Email address</font>');    
     else
          $("#emailspan").html('<font color="#cc0000"></font>');    
  });

});