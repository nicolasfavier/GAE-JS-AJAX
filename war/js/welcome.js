
$(document).ready(function(){
	$.get("/welcome", function( data ) {
		$( "#contentWelcome" ).html( data );
	});
});
