var listExo = new Array();
var id = 0;

//quand l'ajout d'un plan d'entrainement est demandé
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
		
		//post en ajax
		$.ajax({
			  type: "POST",
			  url: "/addtrainning",
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
		
		//pour remplacé par zero si la valeur est NAN
		m = m ? m : 0;
		h = h ? h : 0;
		
		//pour avoir les minutes
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


//création du code HTML dynamiquement
function addExerciseInHTML( exerciceWraper){
	$( "#tabExercises" ).append( '<tr id="message'+ exerciceWraper.id +'">'+
			"<td>"+ exerciceWraper.exercice.title + "</td>"+
			'<td class="hidden-xs"><p>'+ exerciceWraper.exercice.description + "</p></td>"+
			"<td>"+ exerciceWraper.exercice.duration +" min</td>"+
			'<td> <button  onClick="deleteExercice('+ exerciceWraper.id +')" class="btn btn-danger btn-sm"> <span class="glyphicon glyphicon-remove"></span> </button></td>'+
			"</tr>" );
}


//addition des temps de tout les exercices
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


//suprimer un exercice
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

