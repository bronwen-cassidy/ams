function dasSearch(type, query, order, limit, offset) {
	
	$.ajax({
		method: 'GET',
		url: base_url + '/das/search/generalSearch',
		contentType: "application/json; charset=utf-8",
		data: {
			"type": type,
			"query": query,
			"order": JSON.stringify(order),
			"limit": limit,
			"offset": offset
		},
		success: function(response) {
			return response;
		},
		error: function() {
		}
	});		
	
}