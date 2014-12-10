var listExo = new Array();
var totalTimeh = 0;
var totalTimem = 0;

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

		var trainning = {
				title : $("#inputTitle").val(),
				description : $("#inputDescription").val(),
				expectedTime : 20,
				kind : $("#e1").val(),
				exercices : listExo
			};
		
		$.ajax({
			  type: "POST",
			  url: "/trainning",
			  dataType: "json",
			  contentType: "application/json;charset=utf-8",
			  traditional: true,
			  data: JSON.stringify(trainning),
			  success: function(data,status){
				    alert("Post Done new training added");
			  }
		});
   });
});	 

$(document).ready(function(){
	$("#btnAddExercice").click(function(){
		var m =  parseInt($("#minput").val(),10);
		var h =  parseInt($("#hinput").val(),10);
		
		m = m ? m : 0;
		h = h ? h : 0;
		
		var time = m + h *60;

		var exercice = {
				title : $("#titleExercice").val(),
				description : $("#DescriptionExercice").val(),
				duration : time
			};
		
		listExo.push(exercice);
		addExerciseInHTML(exercice);
		
		totalTimem += m;
		totalTimeh += h;
		
		while (totalTimem > 59)
			{
				totalTimem -= 60;
				totalTimeh += 1;
			}
			
		var showTotalTime = totalTimeh + " : " + totalTimem;
		 $("#totalTimeValue").html(showTotalTime);
		
   });
});	  

function addExerciseInHTML( exercice){
	
	$( "#tabExercises" ).append( "<tr>"+
			"<td>"+ exercice.title + "</td>"+
			'<td class="hidden-xs"><p>'+ exercice.description + "</p></td>"+
			"<td>"+ exercice.duration +" min</td>"+
			'<td> <button type="submit" class="btn btn-danger btn-sm"> <span class="glyphicon glyphicon-remove"></span> </button></td>'+
			"</tr>" );
}

/*
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
	});*/