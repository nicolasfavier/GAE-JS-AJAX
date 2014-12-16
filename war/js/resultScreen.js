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
			var obj = jQuery.parseJSON(data);

			if (obj.listTrainning.length > 0) {
				$.each(obj.listTrainning, function(key, value) {
					addTrainningInHTML(value);
				});
			} else {
				nullTrainningInHTML();
			}

			if (obj.listExercice.length > 0 ) {
				$.each(obj.listExercice, function(key, value) {
					addExInHTML(value);
				});
			} else {
				nullExInHTML();
			}
		
			$.each(obj.fluxrss.responseData.feed.entries, function(key, value) {
				addNewsInHTML(value);
			});

		});
	}

});

function addTrainningInHTML(trainning) {
	$("#trainningList")
			.append(
					'<div class=" col-md-6 col-sm-6 col-xs-6">'
							+ '<a href="ha-result-detail-screen.html?trainningId='+ trainning.id +'" class="btn btn-link">'
							+ trainning.title
							+ '</a></div><div class=" col-md-6 col-sm-6 col-xs-6"><label class="btn"> <span class="glyphicon glyphicon-time"></span> '
							+ trainning.expectedTime + ' min.'
							+ '</label></div>');

}

function nullTrainningInHTML() {
	$("#trainningList").append('there is no trainning for your search.');
}

function nullExInHTML() {
	$("#exercicesList").append('there is no exercices for your search.');
}

function addExInHTML(exercices) {
	$("#exercicesList")
			.append(
					'<div class=" col-md-6 col-sm-6 col-xs-6">'
							+ '<a href="ha-result-detail-screen.html?trainningId='+ exercices.trainningId +'" class="btn btn-link">'
							+ exercices.title
							+ '</a></div><div class=" col-md-6 col-sm-6 col-xs-6"><label class="btn"> <span class="glyphicon glyphicon-time"></span> '
							+ exercices.duration + ' min.' + '</label></div>');

}

function addNewsInHTML(news) {
	$("#newsList").append(
			'<p>' + news.title + ' : ' + news.content + " (edit by: "
					+ news.author + ")" + '</p>');

}
