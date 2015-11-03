/*
 * The bucket holds information about how the user is 
 * currently interacting with the system, such as currently
 * selected scratchpad, currently selected table row
 * 
 * The scratchpad can then use this to better display
 * what the user requires.
 * 
 * Methods can be called with bucket.{sub-item}.methodName()
 * 
 * i.e. bucket.rows.setSelectedRow(row_object);
 * or	var id = bucket.rows.getSelectedRowId();
 */
var bucket = {
	
	rows: {
		// Entity type of tabular data on the page
		type: $('.table').data('type'),
		// Currently selected rows
		selected: [],
		// Last row selected
		selected_row: [],
		page_variables: [],
		getRowType: function() {
			return this.type;
		},
		setRowType: function(val) {
			this.type = val;
		},
		// Adds a row to the selected[] array
		addRowToBucket: function(row) {
			var data = row.data();
			var attrs = {};
			var type = row.data('bucket-type');
			
			attrs[type + 'id'] = row.data('bucket-id');
			
			$.each(data, function(index, value) {
				if(index == 'bucket-type' || index == 'bucket-id') return;
				attrs[index] = value;
			});
			this.selected.push(attrs);
		},
		// Removes a row from the selected[] array
		removeRowFromBucket: function(row) {
			for(var i = 0; i < this.selected.length; i++) {
				if(row.data('asset-id') == this.selected[i]) {
					this.selected.splice(i, 1);
				}
			}
		},
		// Empties the selected[] array
		removeAllRowsFromBucket: function() {
			this.selected = [];
		},
		// Sets the currently selected/last clicked row
		// Re runs the scratchpad preview
		setSelectedRow: function(row) {
			var data = row.data();
			var attrs = {};
			var type = row.data('bucket-type');
			
			attrs[type + 'id'] = row.data('bucket-id');
			
			$.each(data, function(index, value) {
				if(index == 'bucket-type') return;
				attrs[index] = value;
			});
			this.selected_row = attrs;
			if(bucket.scratchpad.selected_scratchpad)
				bucket.scratchpad.scratchpadPreview();
		},
		// Returns the entity id of selected_row
		getSelectedRowId: function() {
			return this.selected_row[0];
		},
		/* Returns an array of values of the selected rows attribute matching the given attrName */
		getSelectedRowsAttr: function(attrName){
			return $.map(this.selected, function(element){return element[attrName];});
		}
	},
	scratchpad: {
		// Currently selected scratchpad
		selected_scratchpad: '',		
		// AJAX search URL of the currently selected scratchpad
		searchURL: '',
		// Search attributes of the currently selected scratchpad
		search_attributes: '',
		getSelectedScratchpad: function() {
			return this.selected_scratchpad;
		},
		// Sets the currently selected scratchpad, url and attrs
		// Re runs the scratchpad preview
		setSelectedScratchpad: function(scratchpad) {
			this.selected_scratchpad = scratchpad;
			this.searchURL = scratchpad.data('scratchpad-url');
			this.scratchpadPreview();
			console.log('selected scratchpad');
		},
		// Shows the preview data of the currently selected row in the scratchpad
		scratchpadPreview: function() {
			var previewAttrNames = this.selected_scratchpad.data('preview-search');
			var attrs = {};
			$(previewAttrNames.split(" ")).each(function(index, value) {
				if(bucket.rows.selected_row[value] !== null)
					attrs[value] = bucket.rows.selected_row[value];
				else
					attrs[value] = bucket.rows.page_variables[value];
			});
			this.search_attributes = attrs;
			console.log(attrs);
			console.log('previewing scratchpad');
			scratchpadSearch(this.selected_scratchpad, this.searchURL, "", 0, this.search_attributes);
		}
	},
};

$(document).ready(function() {
	
	/**
	 * Functions to load and paginate table data over AJAX
	 */
	var page = 1,
		limit = 50,
		query = '',
		order_by = '',
		order_type = '',
		total_results = 0;
	
	// Filter dropdown event listener
	$('.asset-filter').change(function() {
		query = $(this).val();
		page = 0;
		populateAssetTable(page, limit, query, order_by, order_type, false);
		page = page + 1;
	});
	
	//XXX Load more assets function -- This needs to be hidden when no more assets can be loaded
	$('.load-more-assets').click(function() {
		populateAssetTable(page, limit, query, order_by, order_type, true);
		page = page + 1;
	});
	
	//XXX TO-DO Column ordering
	// function that toggles ascending and descending classes on the table headers
	
	// AJAX asset table function
	function populateAssetTable(page, limit, query, order_by, order_type, append) {
	
		var offset = page * limit;
		console.log("Page "+page);
	
		$.ajax({
			method: 'GET',
			url: base_url + '/das/assetRegister/assetLandingSearch',
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
					$('.asset-table tbody').append(response);
				else
					$('.asset-table tbody').html(response);
					row_selected = false;
				checkTotal();	
			},
			error: function(repsonse) {
	
			}
		});
			
	}
	
	function checkTotal() {
		
		total_results = $('.total-results').val();
		
		var results = $('.asset-table tbody tr').length;
		
		if(results <= total_results)
			$('.load-more-assets').hide();
		else
			$('.load-more-assets').show();
		
	}
	
	var row_selected = false,
		previous_row;
	
	/**
	 * Event listener for clicking a table row to multi select
	 * rows. This emulates file system CTRL & SHIFT clicking
	 * to select multiple entities
	 */
	$(document.body).on('click', '.multi-table tbody tr td', function(e) {
	    var row = $(this).parents('tr');
	    if(row_selected) {
	    	if(e.ctrlKey) {
	    		if(row.hasClass('selected-row')) {
	    			row.removeClass('selected-row').find('th input').prop('checked', false);
	    		} else {
	    			bucket.rows.addRowToBucket(row); bucket.rows.setSelectedRow(row);
	    			row.addClass('selected-row').find('th input').prop('checked', true);
	    		}
	    	} else if(e.shiftKey) {
	    		var rows = (row.index() > previous_row) ? range(previous_row, row.index()) : range(row.index(), previous_row);
	    		$.each(rows, function(index, value) {
	    			var row = $('.asset-table tbody tr:eq('+value+')');
	    			(index != 0) ? bucket.rows.addRowToBucket(row) : false;
	    			row.addClass('selected-row').find('th input').prop('checked', true);
	    		});
	    	} else {
	    		toggleAllSelected(0);
	    		bucket.rows.addRowToBucket(row);
	    		bucket.rows.setSelectedRow(row);
	    		row.addClass('selected-row').find('th input').prop('checked', true);
	    	}
	    } else {
	    	bucket.rows.addRowToBucket(row);
	    	bucket.rows.setSelectedRow(row);
		    row.addClass('selected-row').find('th input').prop('checked', true);
		    row_selected = true;
	    }
	    previous_row = row.index();
	});
	
	/**
	 * Event listener for selecting a row in a single select table - SINGLE SELECT
	 */
	$(document.body).on('click', '.single-table tbody tr td', function(e) {
	    var row = $(this).parents('tr');
	    if(!row.hasClass('selected-row')) {
	    	toggleAllSelected(0);
	    	bucket.rows.setSelectedRow(row);
	    	row.addClass('selected-row').find('th input').prop('checked', true);
	    }
	});

	/**
	 * Event listener for right clicking a table row - MULTI SELECT
	 */
	$(document.body).on('contextmenu', '.multi-table tbody tr td', function(e) {
	    var row = $(this).parents('tr');
	    if(!row.hasClass('selected-row')) {
	    	toggleAllSelected(0);
	    	bucket.rows.addRowToBucket(row);
	    	bucket.rows.setSelectedRow(row);
	    	row.addClass('selected-row').find('th input').prop('checked', true);
	    }
		previous_row = row.index();
	});

	
	/**
	 * Event listener for right clicking a table row - SINGLE SELECT
	 */
	$(document.body).on('contextmenu', '.single-table tbody tr td', function(e) {
	    var row = $(this).parents('tr');
	    if(!row.hasClass('selected-row')) {
	    	toggleAllSelected(0);
	    	bucket.rows.setSelectedRow(row);
	    	row.addClass('selected-row').find('th input').prop('checked', true);
	    }
	});
	
	/**
	 * Event listener for the table checkboxes on each row - MULTI SELECT
	 */
	$(document.body).on('click', '.multi-table tbody th input', function(e) {
	    var row = $(this).parents('tr');
	    if(!row.hasClass('selected-row')) {
	    	row.addClass('selected-row');
	    	bucket.rows.addRowToBucket(row);
	    	bucket.rows.setSelectedRow(row);
	    } else {
	    	row.removeClass('selected-row');
	    	bucket.rows.removeRowFromBucket(row);
	    	bucket.rows.selected_row = [];
	    }
	    previous_row = row.index();
	    console.log(bucket.rows);
	});
	
	
	/**
	 * Event listener for the table checkboxes on each row - SINGLE SELECT
	 */
	$(document.body).on('click', '.single-table tbody th input', function(e) {
	    var row = $(this).parents('tr');
	    if(!row.hasClass('selected-row')) {
	    	toggleAllSelected(0);
	    	row.addClass('selected-row');
	    	bucket.rows.setSelectedRow(row);
	    	$(this).prop('checked', true);
	    } else {
	    	row.removeClass('selected-row');
	    	bucket.rows.selected_row = [];
	    }
	});
		

	/**
	 * Event listener for the table header checkbox to select - MULTI SELECT
	 * or deselect all rows
	 */
	$(document.body).on('change', '.multi-table thead input', function(e) {
	    if($(this).is(':checked')) {
	        toggleAllSelected(1);
	    } else {
	        toggleAllSelected(0);
	    }
	});
	
	/**
	 * Either selects all rows in a table and adds them to the
	 * selected row bucket, or removes all of them and empties
	 * the bucket
	 */
	function toggleAllSelected(type) {
	    if(type)
	        $('table tbody tr').each(function() {
	            $(this).addClass('selected-row').find('th input').prop('checked', true);
	            bucket.rows.addRowToBucket($(this));
	        });
	    else
	        $('table tbody tr').each(function() {
	            $(this).removeClass('selected-row').find('th input').prop('checked', false);
	            bucket.rows.removeAllRowsFromBucket();
	        });
	}
	
	
	/**
	 * Creates an array of rows between two values, used
	 * to select multiple rows when shift clicking
	 */
	function range(start, stop) {
		var a=[start], b=start;
		while(b<stop){
			b+=1;
			a.push(b);
		}
		return a;
	}
	
});