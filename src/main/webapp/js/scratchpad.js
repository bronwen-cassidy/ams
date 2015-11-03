$(document).ready(function() {

	/* Droppable forms should be configured with data elements of all the fields that will be 
	 * populated on a drop of a suitable droppable.
	 * This gets these fields for each droppable form and then amends data fields to all the
	 * form elements of the corresponding name. This is required to allow the fields to highlight
	 * to signify to the user which fields will be effected.
	 * The draggable is dropped onto the form. See $(".droppable-form").droppable
	 */
	$(".droppable-form").each(function(index){
		var dropData = $(this).data("dnd-drop");
		if(dropData){
			var dropForm = $(this);
			var formAccepts = [];
			
			$.each(dropData, function(key, value){
	//			var formField = $("input[name='" + value['dnd-field-to-copy'] + "']" , dropForm);
				var formField = $("#" + key , dropForm);
				if(formField){
					formField.addClass("droppable");
					formField.data("dnd-accepts", value['dnd-accepts']);
					// deep fields take no effect at the mo. All fields within a droppable are copied if available
					formField.data("dnd-field-to-copy", value['dnd-field-to-copy']);
					formField.data("dnd-deep-fields", value['dnd-deep-fields']);
					formAccepts.push(value['dnd-accepts']);

				}
				// XXX Add the accepts to the form even if the field is not there. To allow for embedded dropaables
			});
			dropForm.data("dnd-accepts", $.unique(formAccepts));
//			console.log("drop form config. Accepts draggables:" + dropForm.data("dnd-accepts"));
		}
	});	
	
	/* The form is what actually accepts the draggable. The fields just say they do to hightlight.
	 * Accepts the draggable if the accepts data attribute contains the draggable type name
	 */
	$(".droppable-form").droppable({
		accept : function(draggable) {
			var type = draggable.data("dnd-type");
			
			// This is weird!?! Get the element by id and it works. Here, draggable and tmp are different
			// See also the drop event and the other droppable methods
			// A very strange one this, it depends on where the draggables appear in the scratchpad - first list is ok, second not.
//			console.log(draggable.attr("id"));
//			console.log(draggable.data("dnd-type"));
			var tmp = $("#" + draggable.attr("id"));
//			console.log(tmp);
//			console.log(tmp.attr("id"));
//			console.log(tmp.data("dnd-type"));
			type = tmp.data("dnd-type");
			
			// inArray returns the index hence the check for not -1
			return ($.inArray(type, $(this).data("dnd-accepts")) != -1);
		},
		drop : function(event, ui) {
//			console.log("dropped on form");
			dropCopy($("#" + ui.draggable.attr("id")), $(this));
		}
	});
	
	/* Each effected field within a droppable form needs to highlight to show that it is
	 * effected by the drop. 
	 * 
	 */
	$(".droppable").droppable({
		accept : function(draggable) {
			var type = draggable.data("dnd-type");
			var tmp = $("#" + draggable.attr("id"));
			type = tmp.data("dnd-type");
			if($(this).data("dnd-accepts") == type)
				return true;
		},
		activeClass : "accepted-droppable",
		hoverClass : "ui-state-hover",
		over: function(event, ui) {
			highlightDeepCopy($("#" + ui.draggable.attr("id")), $(this));
		},
		out: function(event, ui) {
			removeDeepCopyHighlight($("#" + ui.draggable.attr("id")), $(this));
		},
		deactivate: function(event, ui) {
			removeDeepCopyHighlight($("#" + ui.draggable.attr("id")), $(this));
		},
		drop : function(event, ui) {
			// Does nothing on drop as the drop is down on the form
//			dasAutoFill(ui.draggable, $(this));
		}
	});

	// Scratchpad Toggle 
	
	$('.scratchpad-toggle').click(openScratch);

	// Scratchpad Search
	
	var page = 0;
	
	$('.scratchpad-header').click(function() {
		
		page = 0;
		
		checkPaginationButtons();
		
		if($(this).hasClass("collapsed")) {
			
			var scratchpad = $(this).parents('.panel');
			var searchURL = scratchpad.data('scratchpad-url');
			scratchpadSearch(scratchpad, searchURL, '', page);
			
		}
		
	});
	
	$('.scratchpad-header-preview').click(function() {
		
		checkPaginationButtons();
		
		if($(this).hasClass("collapsed")) {
					
			var scratchpad = $(this).parents('.panel');
			
			bucket.scratchpad.setSelectedScratchpad(scratchpad);

			if (bucket.rows.selected_row){
				bucket.scratchpad.scratchpadPreview();
			}
			else{
				$(this).children(".ul").append("<li>Please select item</li>");
			}
			
		}
	});
	
	$('.scratchpad-pagination a').click(function() {
	
		if(!$(this).hasClass('disabled')) {
			
			page = ($(this).hasClass('next')) ? page + 1 : page - 1;
			
			checkPaginationButtons();
			
			var scratchpad = $(this).parents('.panel');
			var searchURL = scratchpad.data('scratchpad-url');
			var query = scratchpad.find('.scratchpad-search:first').val();
			
			scratchpadSearch(scratchpad, searchURL, query, page);
			
		}
			
	});
	
	$('.scratchpad-search').keyup(function() {
		
		$this = $(this);
		
		delay(function() {
		
			checkPaginationButtons();
			
			var scratchpad = $this.parents('.panel');
			var searchURL = scratchpad.data('scratchpad-url');
			var query = $this.val();
			
			scratchpadSearch(scratchpad, searchURL, query, page);
		
		}, 800);
	
	});
	
	var prev_spId;
	var parent_spId;
	var prev_parent_spId;
	
	$('.scratchpad-trigger').keyup(function() {
		var spId=$(this).data('scratchpad-trigger-id');
		var spSearchInputId = spId + "_sp_search_input";
		console.log("onchange:" + spSearchInputId);
		$spInput = $('#' + spSearchInputId);
		$spInput.val($(this).val());
		
		delay(function() {
			checkPaginationButtons();
			
			var scratchpad = $spInput.parents('.panel');
			var searchURL = scratchpad.data('scratchpad-url');
			var query = $spInput.val();
			
			scratchpadSearch(scratchpad, searchURL, query, page);
		}, 800);
	});
		
	$('.scratchpad-trigger').focus(function() {
		$('.scratchpad-toggle').openScratch();
		var spId = $(this).data('scratchpad-trigger-id') + '_accordian';	
		var spHeading = $('#'+spId).closest('.panel').find('.panel-heading:first');
						
		if(spHeading.data('parent') !== '#panel-group-scratchpad') {
			console.log('is multi level');
			var spParent = spHeading.parents('.multi-level').find('.panel-heading:first');
			if(spParent.hasClass('collapsed'))
				spParent.trigger('click');
		}
		
		if(spHeading.hasClass('collapsed'))
			spHeading.trigger('click');
		
	});
	
	function checkPaginationButtons() {
		
		if(page === 0) {
			$('.scratchpad-pagination .prev').addClass('disabled');
			$('.scratchpad-pagination .next').removeClass('disabled');
		} else {
			$('.scratchpad-pagination .next').addClass('disabled');
			$('.scratchpad-pagination .prev').removeClass('disabled');
		}
		
	}

	var delay = (function(){
		var timer = 0;
		return function(callback, ms){
			clearTimeout (timer);
			timer = setTimeout(callback, ms);
		};
	})();
	
});

/*
 * When a draggable is dropped onto a droppable form then all the relavent fields are copied onto the specific 
 * form elements. This is effectively a deep copy.
 */
function dropCopy(draggable, droppableForm) 
{
	// for each element within form marked with accepts for this droppable. 
	var dropFormElements = droppableForm.find(":data(dnd-accepts)");
	// Only look at droppable form elements
	dropFormElements.each(function(){
		// Check that the form element accepts this type of draggable
		if($(this).data("dnd-accepts") == draggable.data("dnd-type")){
			// Get the field to copy from the draggable and set on the droppale field
			var fieldToCopy =  $(this).data("dnd-field-to-copy");
			var valueToCopy=draggable.data("dnd-" + fieldToCopy);
			$(this).val(valueToCopy);
		}
	});
	// trigger the change event to do the auto save
	droppableForm.trigger("change");
}

/**
 * Function to copy values from a source to a target (draggable to droppable). At the moment
 * uses the drag and drop, but could easily be used for predictive text as well.
 * 
 * Currently not used as done from the form. But kept as an implmentation of deep copy.
 * @param draggable
 * @param droppable
 */
function dasAutoFill(draggable, droppable) 
{
	var fieldToCopy = droppable.data("dnd-field-to-copy");
	
	var value = draggable.data("dnd-" + fieldToCopy);
	console.log("dasAutoFill:" + value);
	
	/*
	 * TODO Potentially need different logic depending on the input type and the copy value.
	 * eg. type conversion from a Boolean to checked 
	 */
//	if(droppable.is('select')) // If the option didn't exist then add a new one. - NO if didn't exist then no longer a valid option
//		droppable.prepend(new Option(value, value, true, true));
//	else
		droppable.val(value);

	var fields_to_copy = droppable.data("dnd-deep-fields");
	
	if(fields_to_copy){
//		console.log("all deep fields:" + fields_to_copy);
		fields_to_copy = fields_to_copy.split(",");
		$.each(fields_to_copy, function(index, value) {
			
			var deepDroppable = $('#' + value);
			
			var fieldToCopy = deepDroppable.data("dnd-field-to-copy");
			
			var valueToCopy=draggable.data("dnd-" + fieldToCopy);
//			console.log("deep copy. draggable (" + "dnd-" + fieldToCopy + "):" + valueToCopy);
			
//			if(deepDroppable.is('select'))
//				deepDroppable.prepend(new Option(draggable.data(value), draggable.data(value), true, true));
//			else
				deepDroppable.val(valueToCopy);
		});
	}
}

function highlightDeepCopy(draggable, droppable) {
	
	var fields_to_copy = droppable.data("dnd-deep-fields");
	
	if(fields_to_copy){
		fields_to_copy = fields_to_copy.split(" ");
		$.each(fields_to_copy, function(index, value) {
			
			if(draggable.data(value) != undefined) {
				$('#'+value).delay(100).addClass('valid-deepcopy');
			} 
			
		});		
	}
}

function removeDeepCopyHighlight(draggable, droppable) {
	
	var fields_to_copy = droppable.data("dnd-deep-fields");
	
	if(fields_to_copy){
		fields_to_copy = fields_to_copy.split(" ");
		$.each(fields_to_copy, function(index, value) {
			
			if(draggable.data(value) != undefined) {
				$('#'+value).removeClass('valid-deepcopy');
			} 
		});
	}
}

function scratchpadSearch(scratchpad, searchURL, query, page, attrs) {
	
	var limit = 10;
	var offset = page * limit;
	
	$.ajax({
		method: 'GET',
		url: base_url + searchURL,
		contentType: "application/json; charset=utf-8",
		data: {
			"attrs": JSON.stringify(attrs),
			"query": query,
			"limit": limit,
			"firstResult": offset
		},
		success: function(response) {
			scratchpad.find('.panel-body:first').empty().html(response);
			console.log('replaced');
		},
		error: function() {
		}
	});	
}

var scratchpad = 0;

$.fn.openScratch = function(){
	if($('.scratchpad-toggle').hasClass('open')){
		return;
	} else {
		openScratch();
	}
};


function openScratch(){
	
	if(scratchpad) {
		$('.scratchpad').animate({ right: '-15%' }, 200);
		$('.main-content > .row > .container').animate({ width: '98%' }, 200);
		scratchpad = 0;
	} else if(!scratchpad) {
		$('.scratchpad').animate({ right: '0%' }, 200);
		$('.main-content > .row > .container').animate({ width: '80%' }, 200);
		scratchpad = 1;
	}
	
	$('.scratchpad-toggle').toggleClass('open');
}