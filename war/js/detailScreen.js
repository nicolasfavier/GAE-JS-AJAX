var listExo = new Array();

function getUrlParameter(sParam) {
	var sPageURL = window.location.search.substring(1);
	var sURLVariables = sPageURL.split('&');
	
	for (var i = 0; i < sURLVariables.length; i++) {
		var sParameterName = sURLVariables[i].split('=');
		if (sParameterName[0] == sParam) {
			return sParameterName[1];
		}
	}
	
	return null;
}

$(document).ready(function() {
	var trainningId = getUrlParameter('trainningId');
	
	if(trainningId){
		$.get("pendingTrainning", {
			id : trainningId
		}, function(data, status) {
			alert(data);
			var obj = jQuery.parseJSON(data);
			addTrainningByIdInHTML(obj);
			
			if (obj.pendingExercice.length > 0) {
				$.each(obj.pendingExercice, function(key, value) {
						addExerciseByIdInHTML(value)
						var clock =$('#flipcountdownbox' + value.id).FlipClock({
							 autoStart: false,
							    countdown: false
							});
						
						var exerciceWraper = {
								clock : clock,
								id : value.id
						};
						
						listExo.push(exerciceWraper);
					});
			}
		});
	}
});



function runChrono(id){
	$.each( listExo, function( key, exerciceWraper ) {
        if(exerciceWraper.id === id){
        	exerciceWraper.clock.start();       	
        }
	});
}

function stopChrono(id){
	$.each( listExo, function( key, exerciceWraper ) {
        if(exerciceWraper.id === id){
        	exerciceWraper.clock.stop();       	
        }
	});
}

function resetChrono(id){
	$.each( listExo, function( key, exerciceWraper ) {
        if(exerciceWraper.id === id){
        	exerciceWraper.clock.stop();
        	exerciceWraper.clock.setTime(0);  
        }
	});
}

function addTrainningByIdInHTML(trainning){

	$( "#tabExercicesUser" ).append('<h2>'+ trainning.title +'<span class=" margetitle glyphicon glyphicon-time"></span>'+ trainning.expectedTime +'<h2>')
			
}

function addExerciseByIdInHTML(exercice){
	i = exercice.id;
	$( "#tabExercicesUser" ).append( 
			
			'<tr>'+
			'<td class=" col-md-12 col-sm-12 col-xs-12">'+
			'<div class="row">'+
			    '<div class=" col-md-3 col-sm-12 col-xs-12 ">'+
			        '<h3>'+ exercice.title +'</h3>'+
			    '</div>'+
			    '<div class=" col-md-1 col-sm-2 col-xs-2 ">'+
			        '<p id="totalTimeValue" style="margin-top:25px"><span class="glyphicon glyphicon-time"></span>'+ exercice.duration +'</p>'+
			    '</div>'+
			'</div>'+
			'<div class="row">'+
			    '<div class=" col-md-1 col-sm-0 col-xs-0 " ></div>'+
			    '<div class=" col-md-6 col-sm-12 col-xs-12 ">'+
			        '<p>'+ exercice.description +'</p>'+
			    '</div>'+
			    '<div class=" col-md-5 col-sm-12 col-xs-12 ">'+
			        '<div class=" col-md-12 col-sm-12 col-xs-12 ">'+
			            '<div id="flipcountdownbox'+ i +'" ></div>'+
			        '</div>'+
			        '<div class=" col-md-12 col-sm-12 col-xs-12 centered">'+
			            '<div class="btn-group">'+
			                '<button onclick="runChrono('+ i +')" type="button" class="btn btn-default " ><span class="glyphicon glyphicon-play"></span> </button>'+
			                '<button onclick="stopChrono('+ i +')" type="button" class="btn btn-default " > <span class="glyphicon glyphicon-pause"></span> </button>'+
			            '</div>'+
			            '<button onclick="resetChrono('+ i +')" type="button" class="btn btn-default " > <span class="glyphicon glyphicon-repeat"></span> </button>'+
			     '</div>'+
			        
			    '</div>'+
			'</div>'+
			    '<div class=" col-md-2 ol-sm-5 col-xs-12 text-center" >'+
			        '<button type="submit" class="btn btn-success btn-lg"> <span class="glyphicon glyphicon-ok"></span> </button>   '+ 
			        '<button type="submit" class="btn btn-danger btn-sm"> <span class="glyphicon glyphicon-fast-forward"></span> </button>'+
			    '</div>'+
			    
			'</td>'+
			'</tr>'

	
	);
}

 $(document).ready(function(){
		$("#playButton").click(function(){
			clock.start();
	   });
	});	 