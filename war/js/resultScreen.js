function getUrlParameter(sParam) {
	var sPageURL = window.location.search.substring(1);
	var sURLVariables = sPageURL.split('&');
	for (var i = 0; i < sURLVariables.length; i++) {
		var sParameterName = sURLVariables[i].split('=');
		if (sParameterName[0] == sParam) {
			return sParameterName[1];
		}
	}
}

$(document).ready(function() {
	var search = getUrlParameter('search');
	if (search != null) {
		$.get("searchbyword", {
			search : search
		}, function(data, status) {
			//alert("json " + data);
			var obj = jQuery.parseJSON(data);

			$.each(obj.listTrainning, function(key, value) {
				addTrainningInHTML(value);
			});
			
			$.each(obj.ListExercice, function(key, value) {
				addExInHTML(value);
			});
			alert("ici");
			
			alert(obj.fluxrss.responseData);
			$.each(obj.fluxrss.responseData.feed.entries, function(key, value) {
				alert("news");
				addNewsInHTML(value);
			});
			
		});
	}

});

function addTrainningInHTML(trainning) {
	$("#trainningList")
			.append(
					'<div class=" col-md-6 col-sm-6 col-xs-6">'
							+ '<button type="submit" class="btn btn-link">'
							+ trainning.title
							+ '</button></div><div class=" col-md-6 col-sm-6 col-xs-6"><label class="btn"> <span class="glyphicon glyphicon-time"></span> '
							+ trainning.expectedTime + ' min.'
							+ '</label></div>');

}

function addExInHTML(exercices) {
	$("#exercicesList")
			.append(
					'<div class=" col-md-6 col-sm-6 col-xs-6">'
							+ '<button type="submit" class="btn btn-link">'
							+ exercices.title
							+ '</button></div><div class=" col-md-6 col-sm-6 col-xs-6"><label class="btn"> <span class="glyphicon glyphicon-time"></span> '
							+ exercices.durration + ' min.'
							+ '</label></div>');

}

function addNewsInHTML(news) {
	$("#newsList")
			.append('<p>'+news.title+' : ' + news.content +" (edit by: "+ news.author+")"+'</p>');

}
