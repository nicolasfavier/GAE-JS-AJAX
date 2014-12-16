var listExo = new Array();

for (i = 1; i <= 4; i++) { 
	

	addExerciseByIdInHTML(i)
	var clock =$('#flipcountdownbox' + i).FlipClock({
		 autoStart: false,
		    countdown: false
		});
	
	var exerciceWraper = {
			clock : clock,
			id : i
	}
	
	listExo.push(exerciceWraper);
}

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

function addExerciseByIdInHTML( i){
	
	$( "#tabExercicesUser" ).append( 
			
			'<tr>'+
			'<td class=" col-md-12 col-sm-12 col-xs-12">'+
			'<div class="row">'+
			    '<div class=" col-md-3 col-sm-12 col-xs-12 ">'+
			        '<h3>running warm-up</h3>'+
			    '</div>'+
			    '<div class=" col-md-1 col-sm-2 col-xs-2 ">'+
			        '<p id="totalTimeValue" style="margin-top:25px"><span class="glyphicon glyphicon-time"></span> 0:20:00</p>'+
			    '</div>'+
			'</div>'+
			'<div class="row">'+
			    '<div class=" col-md-1 col-sm-0 col-xs-0 " ></div>'+
			    '<div class=" col-md-6 col-sm-12 col-xs-12 ">'+
			        '<p>Ideoque fertur neminem aliquando ob haec vel similia poenae addictum oblato de more elogio revocari iussisse, quod inexorabiles quoque principes factitarunt. et exitiale hoc vitium, quod in aliis non numquam intepescit, in illo aetatis progressu effervescebat, obstinatum eius propositum accendente adulatorum cohorte.</p>'+
			    '</div>'+
			    '<div class=" col-md-3 col-sm-12 col-xs-12 ">'+
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