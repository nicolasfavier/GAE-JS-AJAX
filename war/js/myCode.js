$(document).ready(function(){
  $("#btnCommitId").click(function(){
	  	$.post("register",
    		  {
    		    cmd:"Register",
    		    email:$("#inputEmailId").val(),
    		    pwd:$("#inputPwdId").val(),
    		  },
    		  function(data,status){
    		    alert("Post Done new user added, id: " + data.userid + "\nStatus: " + status);
    		  });
  		});
});

$(document).ready(function(){
	  $("#btnCreateTraining").click(function(){
		  	$.post("/trainning",
	    		  {
	    		    title:$("#inputTitle").val(),
	    		    description:$("#inputDescription").val(),
	    		    expectedTime:$("#totalTimeValue").val(),
	    		    kind:$("#e1").val()
	    		    
	    		  },
	    		  function(data,status){
	    		    alert("Post Done new training added");
	    		  });
	  		});
	});