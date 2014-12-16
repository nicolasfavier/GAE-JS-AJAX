$(document).ready(function() {
	var name;
	$.get("login", {}, function(data, status) {
		if (data.userNickname) {
			$("#showLogUser").show();
			var str ="user " + data.userNickname+" [<a href=\""
                + data.logoutUrl
                + "\">sign out</a>]";
			$("#showLogUser").html(str );
			$("#buttonLogin").hide();
		} else {
			$("#showLogUser").hide();
			$("#buttonLogin").show();
		}
		
	});
});

$(document).ready(function(){
	  $("#googleLogin").click(function(){
		  	$.post("login", {
		  		type:"Google"
		  	},
	    		  function(data,status){
	    		    window.location.replace( data.Url);
	    		  });
	  		});
	});

$(document).ready(function(){
	  $("#openIdLogin").click(function(){
		  	$.post("login", {
		  		type:"MyOpenId"
		  	},
	    		  function(data,status){
	    		    window.location.replace( data.Url);
	    		  });
	  		});
	});

$(document).ready(function(){
	  $("#yahooLogin").click(function(){
		  	$.post("login", {
		  		type:"Yahoo"
		  	},
	    		  function(data,status){
	    		    window.location.replace( data.Url);
	    		  });
	  		});
	});
