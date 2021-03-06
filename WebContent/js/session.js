function addCart(courseNumber){
	console.log("in Add cart");
	
	var x = document.cookie;
	var user = x.split("=")[1];
	
	var json = {
		"username": user,
		"courseno": courseNumber
	};
	
	$.ajax({
      type: "POST",
      data: json,
      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
      url: "rest/cart",
      success: function(result){
          console.log(result);
          if(result.success == "true"){
        	  //console.log("swal");
        	  swal("Added to Cart!");
        	  //$("#reviewForm")[0].reset();
          }
      }
    });
}

jQuery(document).ready(function($) {

	$("#logout").click(function(){
		document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC";
		window.location.replace("index.html");
	});
	
	$("#checkoutButton").click(function(){
		
		var x = document.cookie;
    	var user = x.split("=")[1];
		
    	var json = {
       			"username": user
        };
    	
		$.ajax({
	      type: "POST",
	      data: json,
	      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
	      url: "rest/checkout",
	      success: function(result){
	          console.log(result);
	          if(result.success == "true"){
	        	  //console.log("swal");
	        	 // swal("Review added successfully!");
	        	  window.location.replace("history.html");
	          }
	      }
	    });
		    
	});
	
	$("#searchForm").submit(function(e){
		
		$.ajax({
	      type: "POST",
	      data: $("#searchForm").serialize(),
	      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
	      url: "rest/search",
	      success: function(result){
	    	console.log(result);
	          
	    	var htmlAppend = "";
	    	
	    	$("#searchresult")[0].innerHTML = htmlAppend;
	    		
	    
	        htmlAppend += "<div><table id=\"myTable\" style=\"width: 100%\" class=\"tablesorter\"> <thead style= \"border-bottom: 20px;\"> <tr> <th>courseName</th> <th>courseNumber</th> <th>term</th> <th>classNumber</th> <th>classSection</th><th>profName</th><th>Add to Cart</th></tr> </thead> <tbody>";
            $.each (result, function (key, value) {
                htmlAppend += 
                "<tr><td><p class = \"courseName\">" + value.courseName + "</p></td>"+
                    "<td><p class = \"courseNumber\">" + value.courseNumber + "</p></td>" +
                "<td><p class = \"term\">" + value.term + "</p></td>"+
                    "<td><p class = \"classNumber\">" + value.classNumber + "</p></td>"+
                    "<td><p class = \"classSection\">" + value.classSection + "</p></td>"+
                    "<td><p class = \"profName\">" + value.profName + "</p></td>"+
                    "<td><button onclick=\"addCart('"+ value.classNumber +"')\" type=\"button\" class=\"btn-cart\">Add to cart!</button><td>"; 
                // htmlAppend += "<td><button type=\"button\">Add to cart!</button><td>";             
            });
	        htmlAppend+="</tbody></table></div>";
	        
	        $("#searchresult").append(htmlAppend);
	        
	        $("#myTable").tablesorter({widthFixed: true, widgets: ['zebra']});
	       
	      }
	    });
		
		e.preventDefault();
		return false;
		
	});
	
	$("#reviewForm").submit(function(e){

	    $.ajax({
	      type: "POST",
	      data: $("#reviewForm").serialize(),
	      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
	      url: "rest/review",
	      success: function(result){
	          console.log(result);
	          if(result.success == "true"){
	        	  //console.log("swal");
	        	  swal("Review added successfully!");
	        	  $("#reviewForm")[0].reset();
	          }
	      }
	    });
	    
	    e.preventDefault();
	    return false;
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