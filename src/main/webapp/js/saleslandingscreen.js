$(document).ready(function() {
	
	/**
	 * Functions to load and paginate table data over AJAX
	 */
	var page = 1,
		limit = 50,
		query = '',
		order_by = '',
		order_type = '';
	
	// Filter dropdown event listener
	$('.sales-filter').change(function() {
		query = $(this).val();
		page = 0;
		populateSalesTable(page, limit, query, order_by, order_type, false);
		page = page + 1;
	});
	
	//XXX Load more sales function -- This needs to be hidden when no more sales can be loaded
	$('.load-more-sales').click(function() {
		populateSalesTable(page, limit, query, order_by, order_type, true);
		page = page + 1;
	});
	
	//XXX TO-DO Column ordering
	// function that toggles ascending and descending classes on the table headers
	
	// AJAX sales table function
	function populateSalesTable(page, limit, query, order_by, order_type, append) {
	
		var offset = page * limit;
		console.log("Page "+page);
	
		$.ajax({
			method: 'GET',
			url: base_url + '/das/proposal/salesLandingSearch',
			contentType: 'application/json; charset=utf-8',
			data: {
				"limit": limit,
				"offset": offset,
				"query": query,
				"order_by": JSON.stringify(order_by),
				"order_type": order_type
			},
			success: function(response) {
				if(append)
					$('.sales-table tbody').append(response);
				else
					$('.sales-table tbody').html(response);
					row_selected = false;
				checkTotal();	
			},
			error: function(repsonse) {
	
			}
		});
			
	}
	
	function checkTotal() {
		
		total_results = $('.total-results').val();
		
		var results = $('.sales-table tbody tr').length;
		
		if(results <= total_results)
			$('.load-more-sales').hide();
		else
			$('.load-more-sales').show();
		
	}
});