jQuery(document).ready(function($) {

	$("#logout").click(function(){
		document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC";
		window.location.replace("index.html");
	});
	
	var x = document.cookie;
	var urls = "rest/profile/" + x.split("=")[1];
	
	$.ajax({
      type: "GET",
      url: urls, 
      success: function(result){
    	  $("#name").val(result.name);
    	  $("#username").val(result.username);
    	  $("#address").val(result.address);
    	  $("#phoneno").val(result.phoneno);
    	  $("#dept").val(result.dept);
    	  $("#email").val(result.email);
      }
    });
	
	$("#profileForm").submit(function(e){

	    $.ajax({
	      type: "POST",
	      data: $("#profileForm").serialize(),
	      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
	      url: "rest/profile",
	      success: function(result){
	          console.log(result);
	          if(result.success == "true"){
	        	  console.log("swal");
	        	  swal("Successfully updated!");
	          }
	      }
	    });
	    
	    e.preventDefault();
	    return false;
	});
	
});