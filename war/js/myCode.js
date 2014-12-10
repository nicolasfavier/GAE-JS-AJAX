var listExo = new Array();
var id = 0;

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
		var listExercices = new Array();
		
		$.each( listExo, function( key, exerciceWraper ) {
			listExercices.push(exerciceWraper.exercice);
		});
		
		var trainning = {
				title : $("#inputTitle").val(),
				description : $("#inputDescription").val(),
				expectedTime : 20,
				kind : $("#e1").val(),
				exercices : listExercices
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
		
		var exerciceWraper = {
				exercice : exercice,
				id : id
		}
		
		id+=1;
		
		listExo.push(exerciceWraper);
		addExerciseInHTML(exerciceWraper);
		updateTime();
   });
});	  

function addExerciseInHTML( exerciceWraper){
	
	$( "#tabExercises" ).append( '<tr id="message'+ exerciceWraper.id +'">'+
			"<td>"+ exerciceWraper.exercice.title + "</td>"+
			'<td class="hidden-xs"><p>'+ exerciceWraper.exercice.description + "</p></td>"+
			"<td>"+ exerciceWraper.exercice.duration +" min</td>"+
			'<td> <button  onClick="deleteExercice('+ exerciceWraper.id +')" class="btn btn-danger btn-sm"> <span class="glyphicon glyphicon-remove"></span> </button></td>'+
			"</tr>" );
}

function updateTime(){ 

	var totalTimeM =0;
	var totalTimeH =0;
	
	$.each( listExo, function( key, exerciceWraper ) {
		totalTimeM += exerciceWraper.exercice.duration;
	});
	
	while (totalTimeM > 59)
	{
		totalTimeM -= 60;
		totalTimeH += 1;
	}
	
	var showTotalTime = totalTimeH + " : " + totalTimeM;
	$("#totalTimeValue").html(showTotalTime);
	
}

function deleteExercice(id){ 
	var del;
	
	$.each( listExo, function( key, exerciceWraper ) {
	        if(exerciceWraper.id === id){
	        	del = exerciceWraper;       	
	        }
		});
	
	listExo = jQuery.grep(listExo, function(value) {
		  return value != del;
		});
	
	$( "#message" + id ).remove();
	updateTime();
};




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